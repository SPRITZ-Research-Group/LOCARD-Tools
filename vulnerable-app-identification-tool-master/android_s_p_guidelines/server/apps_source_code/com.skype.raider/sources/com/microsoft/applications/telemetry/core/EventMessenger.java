package com.microsoft.applications.telemetry.core;

import android.content.Context;
import android.os.AsyncTask;
import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.LogConfiguration;
import com.microsoft.applications.telemetry.TransmitProfile;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import com.microsoft.applications.telemetry.datamodels.EventBase;
import com.microsoft.applications.telemetry.datamodels.Record;
import com.microsoft.applications.telemetry.datamodels.RecordType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

final class EventMessenger implements IEventMessenger {
    private static final int CORE_POOL_SIZE = (Runtime.getRuntime().availableProcessors() + 1);
    private static final String LOG_TAG = ("[ACT]:" + EventMessenger.class.getSimpleName().toUpperCase());
    private static final String THREAD_PREFIX = "Aria-Queue";
    private LogConfiguration configuration;
    private EventsHandler eventsHandler;
    private long firstLaunchTimeInMillis = 0;
    private HttpClientManager httpClientManager;
    private InboundQueuesManager inboundQueuesManager;
    private boolean isPaused = false;
    private boolean isPausedByUser = false;
    private boolean isTearDownCompleted;
    private final Calendar lastValidDate = new GregorianCalendar(2000, 1, 1);
    private PersistentStorageManager persistentStorageManager;
    private TransmitPolicyManager policyManager;
    private final Lock readLock = this.readWriteLock.readLock();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private RecordClassifier recordClassifier;
    private String sdkUUID;
    private StatsManager statsManager;
    private final ExecutorService threadPoolExecutor;
    private final HashSet<String> validAppTokens = new HashSet();
    private final Lock writeLock = this.readWriteLock.writeLock();

    private class AddEventToQueueTask extends AsyncTask<RecordWithMetadata, Void, Void> {
        private AddEventToQueueTask() {
        }

        protected Void doInBackground(RecordWithMetadata... records) {
            EventMessenger.this.readLock.lock();
            try {
                EventMessenger.this.processRecord(records[0]);
                return null;
            } finally {
                EventMessenger.this.readLock.unlock();
            }
        }
    }

    EventMessenger(LogConfiguration configuration, Context context) {
        this.configuration = (LogConfiguration) Preconditions.isNotNull(configuration, "log configuration cannot be null.");
        this.eventsHandler = new EventsHandler(configuration.getTenantToken());
        long maxRecordBatchSizeInBytes = DataModelHelper.computeMaxRecordBatchSizeInBytes(this.configuration.getSource());
        this.persistentStorageManager = new PersistentStorageManager(this.eventsHandler, this.configuration, context);
        loadKVPsFromPersistentStorage();
        this.threadPoolExecutor = Executors.newFixedThreadPool(CORE_POOL_SIZE, new AriaThreadFactory(THREAD_PREFIX));
        this.httpClientManager = new HttpClientManager(this, this.eventsHandler, this.configuration);
        this.inboundQueuesManager = new InboundQueuesManager(this.eventsHandler, this.persistentStorageManager, this.httpClientManager, this.configuration.getSource());
        this.recordClassifier = new RecordClassifier(this.inboundQueuesManager, this.httpClientManager, this.configuration, this.eventsHandler, maxRecordBatchSizeInBytes);
        this.policyManager = new TransmitPolicyManager(this.recordClassifier, this.httpClientManager, this.eventsHandler);
    }

    final void start() {
        Log.i(LOG_TAG, "Create stats manager and start TPM...");
        if (this.configuration.getCollectorUrl().equals(LogConfiguration.COLLECTOR_URL_IN_PRODUCTION)) {
            this.statsManager = new StatsManager();
            registerStatManager(this.statsManager);
        }
        this.policyManager.start();
    }

    public final void pauseTransmission(boolean isPausedByUser) {
        if (isPausedByUser) {
            this.isPausedByUser = true;
        }
        this.policyManager.pause();
        this.isPaused = true;
    }

    public final void resumeTransmission(boolean isResumedByUser) {
        if (isResumedByUser) {
            this.isPausedByUser = false;
        }
        if (!this.isPausedByUser) {
            this.policyManager.resume(true);
            this.isPaused = false;
        }
    }

    public final void backoffTPM() {
        this.policyManager.backoff();
    }

    public final void clearTPMBackoff() {
        this.policyManager.clearBackoff();
    }

    public final void addRecordsBackToStorage(DataPackageCollection request) {
        this.readLock.lock();
        try {
            if (!this.isTearDownCompleted) {
                for (Entry<String, HashMap<DataPackage, EventPriority>> tokenToDataPackages : request.getTokenToDataPackages().entrySet()) {
                    for (Entry<DataPackage, EventPriority> kvp : ((HashMap) tokenToDataPackages.getValue()).entrySet()) {
                        this.eventsHandler.transition(EventTransition.FLIGHT_TO_OFFLINE, ((DataPackage) kvp.getKey()).getRecords().size(), (EventPriority) kvp.getValue(), (String) tokenToDataPackages.getKey());
                    }
                }
                this.inboundQueuesManager.returnRecordsToStorage(request);
                if (!this.isPaused && this.policyManager.hasNetwork() && this.policyManager.isPaused()) {
                    this.policyManager.resume(false);
                }
            }
            this.readLock.unlock();
        } catch (Throwable th) {
            this.readLock.unlock();
        }
    }

    public final void removeRecordsFromStorage(ArrayList<Long> rowIds) {
        if (!this.isTearDownCompleted) {
            this.inboundQueuesManager.removeRecords(rowIds);
        }
    }

    protected final void processRecord(RecordWithMetadata record) {
        if (!this.isTearDownCompleted) {
            this.inboundQueuesManager.sendRecord(record);
            if (!this.isPaused && this.policyManager.hasNetwork() && this.policyManager.isPaused()) {
                this.policyManager.resume(false);
            }
        }
    }

    public final boolean isPaused() {
        return this.isPaused;
    }

    public final EventsHandler getEventsHandler() {
        return this.eventsHandler;
    }

    public final synchronized void flushAndTeardown() {
        Log.d(LOG_TAG, "flushAndTearDown");
        this.writeLock.lock();
        try {
            if (!this.isTearDownCompleted) {
                this.policyManager.stop();
                this.httpClientManager.stop();
                if (this.statsManager != null) {
                    deregisterStatManager(this.statsManager);
                    this.statsManager.stopAndFlushStats();
                }
                this.persistentStorageManager.closeSQLiteDatabase();
                this.isTearDownCompleted = true;
            }
            this.writeLock.unlock();
        } catch (Throwable th) {
            this.writeLock.unlock();
        }
    }

    public final void sendRecord(EventBase event, EventPriority priority, String appToken) {
        Log.d(LOG_TAG, "sendRecord");
        Preconditions.isNotNull(event, "event cannot be null");
        if (!this.validAppTokens.contains(appToken)) {
            try {
                appToken = Preconditions.isValidToken(appToken, String.format("The application token (%s) is invalid.", new Object[]{appToken}));
            } catch (IllegalArgumentException e) {
                this.eventsHandler.eventDropped(event, priority, appToken, EventDropReason.BAD_TENANT);
                if (BuildConfig.DEBUG) {
                    throw e;
                }
            }
            this.validAppTokens.add(appToken);
        }
        sendRecordWithMetadata(new RecordWithMetadata(convertEventBaseToRecord(event), priority, appToken));
    }

    final void registerEventListener(ITransmissionEvents eventListener) {
        this.eventsHandler.addEventListener(eventListener);
    }

    final void deregisterEventListener(ITransmissionEvents eventListener) {
        this.eventsHandler.removeEventListener(eventListener);
    }

    final void registerStatManager(StatsManager statsManager) {
        this.eventsHandler.addStatsManager(statsManager);
    }

    final void deregisterStatManager(StatsManager statsManager) {
        this.eventsHandler.removeStatsManager(statsManager);
    }

    final void setTransmitProfile(TransmitProfile transmitProfile) {
        this.policyManager.setTransmitProfile(transmitProfile.toString());
    }

    final boolean setTransmitProfile(String profileName) {
        return this.policyManager.setTransmitProfile(profileName);
    }

    final boolean loadTransmitProfiles(String profilesJson) {
        return this.policyManager.loadTransmitProfiles(profilesJson);
    }

    final void resetTransmitProfiles() {
        this.policyManager.resetTransmitProfiles();
    }

    final void resetPersistentStorage() {
        this.persistentStorageManager.deleteAndCreateDatabase();
    }

    public final String getSdkUID() {
        return this.sdkUUID;
    }

    public final long getFirstLaunchTimeInMillis() {
        return this.firstLaunchTimeInMillis;
    }

    private void loadKVPsFromPersistentStorage() {
        this.firstLaunchTimeInMillis = this.persistentStorageManager.getKVPLongValue("FirstLaunchTime");
        if (this.firstLaunchTimeInMillis <= 0) {
            this.firstLaunchTimeInMillis = System.currentTimeMillis();
            this.persistentStorageManager.putKVPLongValue("FirstLaunchTime", this.firstLaunchTimeInMillis);
        }
        this.sdkUUID = this.persistentStorageManager.getKVPStringValue("SDKUid");
        if (this.sdkUUID == null || this.sdkUUID.isEmpty()) {
            this.sdkUUID = UUID.randomUUID().toString();
            this.persistentStorageManager.putKVPStringValue("SDKUid", this.sdkUUID);
        }
    }

    private String validateRecord(RecordWithMetadata recordWithMetadata) {
        String reason = "";
        Record record = recordWithMetadata.getRecord();
        if (record.getId() == null || record.getId().trim().isEmpty()) {
            return String.format("Guid was null or empty or white space only: %s", new Object[]{record.getId()});
        } else if (!Preconditions.isValidNameAndType(record.getEventType())) {
            return "Event name does not conform to regular expression ^[a-zA-Z0-9]([a-zA-Z0-9]|_){2,98}[a-zA-Z0-9]$";
        } else {
            if (this.lastValidDate.getTimeInMillis() <= record.getTimestamp()) {
                return reason;
            }
            return String.format("Timestamp was older than %d, it was: %d", new Object[]{Long.valueOf(this.lastValidDate.getTimeInMillis()), Long.valueOf(record.getTimestamp())});
        }
    }

    private void sendRecordWithMetadata(RecordWithMetadata recordWithMetadata) {
        if (!validateRecord(recordWithMetadata).isEmpty()) {
            TraceHelper.TraceInformation(LOG_TAG, String.format("Stage End Fail: event name=%s, event priority=%s, id=%s, tenantId=%s, reason=Invalid record", new Object[]{recordWithMetadata.getRecord().getEventType(), recordWithMetadata.getPriority(), recordWithMetadata.getRecord().getId(), DataModelHelper.getTenantId(recordWithMetadata.getTenantToken())}));
            this.eventsHandler.eventRejected(recordWithMetadata.getRecord(), recordWithMetadata.getPriority(), recordWithMetadata.getTenantToken(), EventRejectedReason.VALIDATION_FAIL);
            if (BuildConfig.DEBUG) {
                throw new IllegalArgumentException(String.format("Invalid Record! Id: %s, Timestamp: %d, Type: %s, EventType: %s. Reason: %s", new Object[]{recordWithMetadata.getRecord().getId(), Long.valueOf(recordWithMetadata.getRecord().getTimestamp()), recordWithMetadata.getRecord().getType(), recordWithMetadata.getRecord().getEventType(), validationFailureReason}));
            }
        } else if (recordWithMetadata.getTenantToken().equals("7434683b182f4b49bc52295c8152518d-e1d93d3d-7e05-4dd3-94d4-eb44bc27b601-7301") || recordWithMetadata.getTenantToken().equals("ff4c325c9413441694c3290e97291533-d1bc1297-cb94-400f-9d68-b53ff97f06a5-6705")) {
            processRecord(recordWithMetadata);
        } else {
            try {
                new AddEventToQueueTask().executeOnExecutor(this.threadPoolExecutor, new RecordWithMetadata[]{recordWithMetadata});
            } catch (RejectedExecutionException e) {
                throw new IllegalArgumentException("Too many tasks!");
            }
        }
    }

    private Record convertEventBaseToRecord(EventBase event) {
        Record record = new Record();
        record.setId(event.getId());
        record.setType(event.getType());
        record.setEventType(event.getEventType());
        record.setTimestamp(event.getTimestamp());
        record.setExtension(event.getExtension());
        changeNullValuesToEmptyInExtension(record);
        record.setPIIExtensions(event.getPIIExtensions());
        record.setRecordType(RecordType.Event);
        record.setTypedExtensionDouble(event.getTypedExtensionDouble());
        record.setTypedExtensionInt64(event.getTypedExtensionInt64());
        record.setTypedExtensionBoolean(event.getTypedExtensionBoolean());
        record.setTypedExtensionDateTime(event.getTypedExtensionDateTime());
        record.setTypedExtensionGuid(event.getTypedExtensionGuid());
        return record;
    }

    private void changeNullValuesToEmptyInExtension(Record record) {
        for (Entry<String, String> entry : record.getExtension().entrySet()) {
            if (entry.getValue() == null) {
                entry.setValue("");
            }
        }
    }
}
