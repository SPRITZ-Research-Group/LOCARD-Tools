package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.LogConfiguration;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import com.microsoft.applications.telemetry.datamodels.Record;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpClientManager implements IHttpClientManager {
    private static final String LOG_TAG = ("[ACT]:" + HttpClientManager.class.getSimpleName().toUpperCase());
    private static final int MAX_CONNECTIONS = 2;
    private static final String THREAD_PREFIX = "Aria-HTTP";
    ClockSkewManager clockSkewManager;
    private final LogConfiguration configuration;
    private AtomicInteger currentActiveConnections = new AtomicInteger(0);
    IHttpSender dataPacakgeSender;
    private final IEventMessenger eventMessenger;
    private final EventsHandler eventsHandler;
    KillSwitchManager killSwitchManager;
    private final ExponentialRetryPolicy retryPolicy;
    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    boolean tramissionPaused = false;

    private class SendRequest implements Runnable {
        private final DataPackageCollection request;

        SendRequest(DataPackageCollection request) {
            this.request = request;
        }

        private void requestDropped(String reason, int statusCode) {
            ArrayList<Long> rowIds = new ArrayList();
            for (Entry<String, HashMap<DataPackage, EventPriority>> tokenToDataPackages : this.request.getTokenToDataPackages().entrySet()) {
                for (Entry<DataPackage, EventPriority> kvp : ((HashMap) tokenToDataPackages.getValue()).entrySet()) {
                    Iterator it = ((DataPackage) kvp.getKey()).getRecords().iterator();
                    while (it.hasNext()) {
                        Record record = (Record) it.next();
                        TraceHelper.TraceInformation(HttpClientManager.LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, request id=%s, reason = %s", new Object[]{record.getEventType(), kvp.getValue(), record.getId(), DataModelHelper.getTenantId((String) tokenToDataPackages.getKey()), this.request.getId(), reason}));
                    }
                }
                rowIds.addAll((Collection) this.request.getTokenToRowIds().get(tokenToDataPackages.getKey()));
                HttpClientManager.this.eventsHandler.requestSendFailed((HashMap) tokenToDataPackages.getValue(), (String) tokenToDataPackages.getKey(), statusCode);
            }
            HttpClientManager.this.eventMessenger.removeRecordsFromStorage(rowIds);
        }

        private void packagesDropped(String reason, HashMap<DataPackage, EventPriority> dataPackages, String tenantToken) {
            for (Entry<DataPackage, EventPriority> kvp : dataPackages.entrySet()) {
                Iterator it = ((DataPackage) kvp.getKey()).getRecords().iterator();
                while (it.hasNext()) {
                    Record record = (Record) it.next();
                    TraceHelper.TraceInformation(HttpClientManager.LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, request id=%s, reason = %s", new Object[]{record.getEventType(), kvp.getValue(), record.getId(), DataModelHelper.getTenantId(tenantToken), this.request.getId(), reason}));
                }
            }
            HttpClientManager.this.eventsHandler.requestSendFailed(dataPackages, tenantToken, Integer.MIN_VALUE);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            try {
                String token;
                HttpClientManager.this.currentActiveConnections.incrementAndGet();
                ArrayList<String> tokensKilled = new ArrayList();
                ArrayList<Long> rowIdsKilled = new ArrayList();
                for (Entry<String, HashMap<DataPackage, EventPriority>> kvp : this.request.getTokenToDataPackages().entrySet()) {
                    if (HttpClientManager.this.killSwitchManager.isTenantKilled((String) kvp.getKey())) {
                        packagesDropped("Tenant is killed", (HashMap) kvp.getValue(), (String) kvp.getKey());
                        tokensKilled.add(kvp.getKey());
                    }
                }
                Iterator it = tokensKilled.iterator();
                while (it.hasNext()) {
                    token = (String) it.next();
                    rowIdsKilled.addAll((Collection) this.request.getTokenToRowIds().get(token));
                    this.request.removeDataPackages(token);
                }
                HttpClientManager.this.eventMessenger.removeRecordsFromStorage(rowIdsKilled);
                if (!HttpClientManager.this.tramissionPaused || this.request.isImmediateRequest()) {
                    if (this.request.getRetryCount() == 0) {
                        for (Entry<String, HashMap<DataPackage, EventPriority>> kvp2 : this.request.getTokenToDataPackages().entrySet()) {
                            HttpClientManager.this.eventsHandler.requestSendAttempted((HashMap) kvp2.getValue(), (String) kvp2.getKey());
                        }
                    }
                    HttpSenderResponse httpResponse = HttpClientManager.this.dataPacakgeSender.sendToCollector(this.request, true);
                    if (this.request.isFirstRequest()) {
                        HttpClientManager.this.submitRequestsQueuedByCSM(HttpClientManager.this.clockSkewManager.determineClockSkewAndReturnQueuedRequests(httpResponse.ResponseHeaders));
                    }
                    List<String> tokensKilledForThisRequest = HttpClientManager.this.killSwitchManager.setKillSwitchTenants(httpResponse.ResponseHeaders);
                    if (httpResponse.StatusCode == 200) {
                        HttpClientManager.this.eventMessenger.clearTPMBackoff();
                        ArrayList<Long> rowIdsSent = new ArrayList();
                        for (Entry<String, HashMap<DataPackage, EventPriority>> tokenToDataPackages : this.request.getTokenToDataPackages().entrySet()) {
                            for (Entry<DataPackage, EventPriority> kvp3 : ((HashMap) tokenToDataPackages.getValue()).entrySet()) {
                                Iterator it2 = ((DataPackage) kvp3.getKey()).getRecords().iterator();
                                while (it2.hasNext()) {
                                    Record record = (Record) it2.next();
                                    String access$000 = HttpClientManager.LOG_TAG;
                                    TraceHelper.TraceInformation(access$000, String.format("Stage End: event name=%s, event priority=%s, id=%s, tenantId=%s, request id=%s", new Object[]{record.getEventType(), kvp3.getValue(), record.getId(), DataModelHelper.getTenantId((String) tokenToDataPackages.getKey()), this.request.getId()}));
                                }
                            }
                            rowIdsSent.addAll((Collection) this.request.getTokenToRowIds().get(tokenToDataPackages.getKey()));
                            HttpClientManager.this.eventsHandler.requestSent((HashMap) tokenToDataPackages.getValue(), (String) tokenToDataPackages.getKey());
                        }
                        HttpClientManager.this.eventMessenger.removeRecordsFromStorage(rowIdsSent);
                        HttpClientManager.this.currentActiveConnections.decrementAndGet();
                        return;
                    } else if (HttpClientManager.this.retryPolicy.shouldRetryForStatus(httpResponse.StatusCode)) {
                        if (tokensKilledForThisRequest != null) {
                            for (String token2 : tokensKilledForThisRequest) {
                                if (this.request.getTokenToDataPackages().containsKey(token2)) {
                                    packagesDropped("Tenant is killed", (HashMap) this.request.getTokenToDataPackages().get(token2), token2);
                                    this.request.removeDataPackages(token2);
                                }
                            }
                        }
                        if (HttpClientManager.this.retryPolicy.maxRetriesExceeded(this.request.getRetryCount())) {
                            HttpClientManager.this.eventMessenger.backoffTPM();
                            HttpClientManager.this.eventMessenger.addRecordsBackToStorage(this.request);
                        } else {
                            retryRequest();
                        }
                    } else {
                        requestDropped("Should not be retried.", httpResponse.StatusCode);
                    }
                } else {
                    if (this.request.isFirstRequest()) {
                        HttpClientManager.this.clockSkewManager.reset();
                    }
                    HttpClientManager.this.eventMessenger.addRecordsBackToStorage(this.request);
                }
                HttpClientManager.this.currentActiveConnections.decrementAndGet();
            } catch (Exception e) {
                HttpClientManager.this.eventsHandler.logException(e);
                if (this.request.getRetryCount() > 0) {
                    requestDropped(e.getLocalizedMessage(), Integer.MIN_VALUE);
                } else {
                    if (this.request.isFirstRequest()) {
                        HttpClientManager.this.submitRequestsQueuedByCSM(HttpClientManager.this.clockSkewManager.disableClockSkewAndReturnQueuedRequests());
                    }
                    retryRequest();
                }
                TraceHelper.TraceError(HttpClientManager.LOG_TAG, String.format("Caught Exception while trying to send request. Exception: " + e.getLocalizedMessage(), new Object[0]));
            } catch (Throwable th) {
                HttpClientManager.this.currentActiveConnections.decrementAndGet();
            }
        }

        private void retryRequest() {
            int sleepDurationBeforeRetryMillis = HttpClientManager.this.retryPolicy.getMillisToBackoffForRetry(this.request.getRetryCount());
            this.request.incrementRetryCount();
            if (this.request.isFirstRequest()) {
                this.request.setFirstRequest(false);
            }
            for (Entry<String, HashMap<DataPackage, EventPriority>> kvp : this.request.getTokenToDataPackages().entrySet()) {
                HttpClientManager.this.eventsHandler.requestSendRetrying((HashMap) kvp.getValue(), (String) kvp.getKey());
            }
            HttpClientManager.this.scheduledThreadPoolExecutor.schedule(new SendRequest(this.request), (long) sleepDurationBeforeRetryMillis, TimeUnit.MILLISECONDS);
        }
    }

    HttpClientManager(IEventMessenger eventMessenger, EventsHandler eventsHandler, LogConfiguration configuration) {
        this.eventMessenger = (IEventMessenger) Preconditions.isNotNull(eventMessenger, "eventMessenger cannot be null.");
        this.eventsHandler = (EventsHandler) Preconditions.isNotNull(eventsHandler, "eventsHandler cannot be null.");
        this.configuration = (LogConfiguration) Preconditions.isNotNull(configuration, "log configuration cannot be null.");
        this.killSwitchManager = new KillSwitchManager();
        this.clockSkewManager = new ClockSkewManager();
        this.dataPacakgeSender = new HttpSender(this.configuration, this.clockSkewManager);
        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2, new AriaThreadFactory(THREAD_PREFIX));
        this.retryPolicy = new ExponentialRetryPolicy(1, LogConfiguration.BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS, LogConfiguration.MAX_BACKOFF_FOR_SENDING_RETRIES_MILLIS);
    }

    public void sendRequest(DataPackageCollection request) {
        request = this.clockSkewManager.processRequest(request);
        if (request != null) {
            this.scheduledThreadPoolExecutor.execute(new SendRequest(request));
        }
    }

    public void transmissionPaused() {
        this.tramissionPaused = true;
        storeRequestsQueuedByCSM(this.clockSkewManager.returnQueuedRequests());
    }

    public void transmissionResumed() {
        this.tramissionPaused = false;
    }

    public boolean canAcceptRequests() {
        boolean isConnectionsBusy;
        boolean canAcceptRequests;
        boolean isClockSkewBlocking = this.clockSkewManager.areRequestsBlocked();
        if (this.currentActiveConnections.get() >= 2) {
            isConnectionsBusy = true;
        } else {
            isConnectionsBusy = false;
        }
        if (isClockSkewBlocking || isConnectionsBusy) {
            canAcceptRequests = false;
        } else {
            canAcceptRequests = true;
        }
        TraceHelper.TraceInformation(LOG_TAG, String.format("Can accept requests = %s, csm blocking = %s, connection busy = %s", new Object[]{Boolean.valueOf(canAcceptRequests), Boolean.valueOf(isClockSkewBlocking), Boolean.valueOf(isConnectionsBusy)}));
        return canAcceptRequests;
    }

    void submitRequestsQueuedByCSM(Queue<DataPackageCollection> requestQueue) {
        if (requestQueue != null) {
            while (!requestQueue.isEmpty()) {
                this.scheduledThreadPoolExecutor.execute(new SendRequest((DataPackageCollection) requestQueue.remove()));
            }
        }
    }

    void storeRequestsQueuedByCSM(Queue<DataPackageCollection> requestQueue) {
        if (requestQueue != null) {
            while (!requestQueue.isEmpty()) {
                this.eventMessenger.addRecordsBackToStorage((DataPackageCollection) requestQueue.remove());
            }
        }
    }

    void stop() {
        this.scheduledThreadPoolExecutor.shutdown();
    }
}
