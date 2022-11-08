package com.adjust.sdk;

import com.skype.camera.imagefilter.ImageFilterManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

public class SdkClickHandler implements ISdkClickHandler {
    private static final double MILLISECONDS_TO_SECONDS_DIVISOR = 1000.0d;
    private static final String SCHEDULED_EXECUTOR_SOURCE = "SdkClickHandler";
    private static final String SOURCE_INSTALL_REFERRER = "install_referrer";
    private static final String SOURCE_REFTAG = "reftag";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private BackoffStrategy backoffStrategy = AdjustFactory.getSdkClickBackoffStrategy();
    private String basePath;
    private ILogger logger = AdjustFactory.getLogger();
    private List<ActivityPackage> packageQueue;
    private boolean paused;
    private CustomScheduledExecutor scheduledExecutor = new CustomScheduledExecutor(SCHEDULED_EXECUTOR_SOURCE, false);

    public SdkClickHandler(IActivityHandler activityHandler, boolean startsSending) {
        init(activityHandler, startsSending);
    }

    public void init(IActivityHandler activityHandler, boolean startsSending) {
        this.paused = !startsSending;
        this.packageQueue = new ArrayList();
        this.activityHandlerWeakRef = new WeakReference(activityHandler);
        this.basePath = activityHandler.getBasePath();
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
        sendNextSdkClick();
    }

    public void sendSdkClick(final ActivityPackage sdkClick) {
        this.scheduledExecutor.submit(new Runnable(this) {
            final /* synthetic */ SdkClickHandler b;

            public final void run() {
                this.b.packageQueue.add(sdkClick);
                this.b.logger.debug("Added sdk_click %d", Integer.valueOf(this.b.packageQueue.size()));
                this.b.logger.verbose("%s", sdkClick.getExtendedString());
                this.b.sendNextSdkClick();
            }
        });
    }

    public void sendReftagReferrers() {
        this.scheduledExecutor.submit(new Runnable(this) {
            final /* synthetic */ SdkClickHandler a;

            {
                this.a = this$0;
            }

            public final void run() {
                IActivityHandler activityHandler = (IActivityHandler) this.a.activityHandlerWeakRef.get();
                SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(activityHandler.getContext());
                try {
                    JSONArray rawReferrerArray = sharedPreferencesManager.getRawReferrerArray();
                    boolean hasRawReferrersBeenChanged = false;
                    for (int i = 0; i < rawReferrerArray.length(); i++) {
                        JSONArray savedRawReferrer = rawReferrerArray.getJSONArray(i);
                        if (savedRawReferrer.optInt(2, -1) == 0) {
                            String savedRawReferrerString = savedRawReferrer.optString(0, null);
                            long savedClickTime = savedRawReferrer.optLong(1, -1);
                            savedRawReferrer.put(2, 1);
                            hasRawReferrersBeenChanged = true;
                            this.a.sendSdkClick(PackageFactory.buildReftagSdkClickPackage(savedRawReferrerString, savedClickTime, activityHandler.getActivityState(), activityHandler.getAdjustConfig(), activityHandler.getDeviceInfo(), activityHandler.getSessionParameters()));
                        }
                    }
                    if (hasRawReferrersBeenChanged) {
                        sharedPreferencesManager.saveRawReferrerArray(rawReferrerArray);
                    }
                } catch (JSONException e) {
                    this.a.logger.error("Send saved raw referrers error (%s)", e.getMessage());
                }
            }
        });
    }

    public void teardown() {
        this.logger.verbose("SdkClickHandler teardown", new Object[0]);
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.packageQueue != null) {
            this.packageQueue.clear();
        }
        if (this.activityHandlerWeakRef != null) {
            this.activityHandlerWeakRef.clear();
        }
        this.logger = null;
        this.packageQueue = null;
        this.backoffStrategy = null;
        this.scheduledExecutor = null;
    }

    private void sendNextSdkClick() {
        this.scheduledExecutor.submit(new Runnable(this) {
            final /* synthetic */ SdkClickHandler a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.sendNextSdkClickI();
            }
        });
    }

    private void sendNextSdkClickI() {
        if (!this.paused && !this.packageQueue.isEmpty()) {
            final ActivityPackage sdkClickPackage = (ActivityPackage) this.packageQueue.remove(0);
            int retries = sdkClickPackage.getRetries();
            Runnable runnable = new Runnable(this) {
                final /* synthetic */ SdkClickHandler b;

                public final void run() {
                    this.b.sendSdkClickI(sdkClickPackage);
                    this.b.sendNextSdkClick();
                }
            };
            if (retries <= 0) {
                runnable.run();
                return;
            }
            long waitTimeMilliSeconds = Util.getWaitingTime(retries, this.backoffStrategy);
            String secondsString = Util.SecondsDisplayFormat.format(((double) waitTimeMilliSeconds) / MILLISECONDS_TO_SECONDS_DIVISOR);
            this.logger.verbose("Waiting for %s seconds before retrying sdk_click for the %d time", secondsString, Integer.valueOf(retries));
            this.scheduledExecutor.schedule(runnable, waitTimeMilliSeconds, TimeUnit.MILLISECONDS);
        }
    }

    private void sendSdkClickI(ActivityPackage sdkClickPackage) {
        IActivityHandler activityHandler = (IActivityHandler) this.activityHandlerWeakRef.get();
        String source = (String) sdkClickPackage.getParameters().get(ImageFilterManager.PROP_SOURCE);
        boolean isReftag = source != null && source.equals("reftag");
        String rawReferrerString = (String) sdkClickPackage.getParameters().get("raw_referrer");
        if (isReftag) {
            if (new SharedPreferencesManager(activityHandler.getContext()).getRawReferrer(rawReferrerString, sdkClickPackage.getClickTimeInMilliseconds()) == null) {
                return;
            }
        }
        boolean isInstallReferrer = source != null && source.equals("install_referrer");
        long clickTime = -1;
        long installBegin = -1;
        String installReferrer = null;
        if (isInstallReferrer) {
            clickTime = sdkClickPackage.getClickTimeInSeconds();
            installBegin = sdkClickPackage.getInstallBeginTimeInSeconds();
            installReferrer = (String) sdkClickPackage.getParameters().get(Constants.REFERRER);
        }
        String url = AdjustFactory.getBaseUrl();
        if (this.basePath != null) {
            url = url + this.basePath;
        }
        try {
            SdkClickResponseData responseData = (SdkClickResponseData) UtilNetworking.createPOSTHttpsURLConnection(url + sdkClickPackage.getPath(), sdkClickPackage, this.packageQueue.size() - 1);
            if (responseData.jsonResponse == null) {
                retrySendingI(sdkClickPackage);
            } else if (activityHandler != null) {
                if (isReftag) {
                    new SharedPreferencesManager(activityHandler.getContext()).removeRawReferrer(rawReferrerString, sdkClickPackage.getClickTimeInMilliseconds());
                }
                if (isInstallReferrer) {
                    responseData.clickTime = clickTime;
                    responseData.installBegin = installBegin;
                    responseData.installReferrer = installReferrer;
                    responseData.isInstallReferrer = true;
                }
                activityHandler.finishedTrackingActivity(responseData);
            }
        } catch (UnsupportedEncodingException e) {
            logErrorMessageI(sdkClickPackage, "Sdk_click failed to encode parameters", e);
        } catch (SocketTimeoutException e2) {
            logErrorMessageI(sdkClickPackage, "Sdk_click request timed out. Will retry later", e2);
            retrySendingI(sdkClickPackage);
        } catch (IOException e3) {
            logErrorMessageI(sdkClickPackage, "Sdk_click request failed. Will retry later", e3);
            retrySendingI(sdkClickPackage);
        } catch (Throwable e4) {
            logErrorMessageI(sdkClickPackage, "Sdk_click runtime exception", e4);
        }
    }

    private void retrySendingI(ActivityPackage sdkClickPackage) {
        int retries = sdkClickPackage.increaseRetries();
        this.logger.error("Retrying sdk_click package for the %d time", Integer.valueOf(retries));
        sendSdkClick(sdkClickPackage);
    }

    private void logErrorMessageI(ActivityPackage sdkClickPackage, String message, Throwable throwable) {
        String packageMessage = sdkClickPackage.getFailureMessage();
        String reasonString = Util.getReasonString(message, throwable);
        this.logger.error(Util.formatString("%s. (%s)", packageMessage, reasonString), new Object[0]);
    }
}
