package com.adjust.sdk;

import android.net.Uri;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

public class AttributionHandler implements IAttributionHandler {
    private static final String ATTRIBUTION_TIMER_NAME = "Attribution timer";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private ActivityPackage attributionPackage;
    private String basePath;
    private ILogger logger = AdjustFactory.getLogger();
    private boolean paused;
    private CustomScheduledExecutor scheduledExecutor = new CustomScheduledExecutor("AttributionHandler", false);
    private TimerOnce timer = new TimerOnce(new Runnable(this) {
        final /* synthetic */ AttributionHandler a;

        {
            this.a = this$0;
        }

        public final void run() {
            this.a.sendAttributionRequest();
        }
    }, ATTRIBUTION_TIMER_NAME);

    public void teardown() {
        this.logger.verbose("AttributionHandler teardown", new Object[0]);
        if (this.timer != null) {
            this.timer.teardown();
        }
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.activityHandlerWeakRef != null) {
            this.activityHandlerWeakRef.clear();
        }
        this.scheduledExecutor = null;
        this.activityHandlerWeakRef = null;
        this.logger = null;
        this.attributionPackage = null;
        this.timer = null;
    }

    public AttributionHandler(IActivityHandler activityHandler, ActivityPackage attributionPackage, boolean startsSending) {
        this.basePath = activityHandler.getBasePath();
        init(activityHandler, attributionPackage, startsSending);
    }

    public void init(IActivityHandler activityHandler, ActivityPackage attributionPackage, boolean startsSending) {
        this.activityHandlerWeakRef = new WeakReference(activityHandler);
        this.attributionPackage = attributionPackage;
        this.paused = !startsSending;
    }

    public void getAttribution() {
        this.scheduledExecutor.submit(new Runnable(this) {
            final /* synthetic */ AttributionHandler a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.getAttributionI(0);
            }
        });
    }

    public void checkSessionResponse(final SessionResponseData sessionResponseData) {
        this.scheduledExecutor.submit(new Runnable(this) {
            final /* synthetic */ AttributionHandler b;

            public final void run() {
                IActivityHandler activityHandler = (IActivityHandler) this.b.activityHandlerWeakRef.get();
                if (activityHandler != null) {
                    this.b.checkSessionResponseI(activityHandler, sessionResponseData);
                }
            }
        });
    }

    public void checkSdkClickResponse(final SdkClickResponseData sdkClickResponseData) {
        this.scheduledExecutor.submit(new Runnable(this) {
            final /* synthetic */ AttributionHandler b;

            public final void run() {
                IActivityHandler activityHandler = (IActivityHandler) this.b.activityHandlerWeakRef.get();
                if (activityHandler != null) {
                    this.b.checkSdkClickResponseI(activityHandler, sdkClickResponseData);
                }
            }
        });
    }

    public void checkAttributionResponse(final AttributionResponseData attributionResponseData) {
        this.scheduledExecutor.submit(new Runnable(this) {
            final /* synthetic */ AttributionHandler b;

            public final void run() {
                IActivityHandler activityHandler = (IActivityHandler) this.b.activityHandlerWeakRef.get();
                if (activityHandler != null) {
                    this.b.checkAttributionResponseI(activityHandler, attributionResponseData);
                }
            }
        });
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
    }

    public void sendAttributionRequest() {
        this.scheduledExecutor.submit(new Runnable(this) {
            final /* synthetic */ AttributionHandler a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.sendAttributionRequestI();
            }
        });
    }

    private void getAttributionI(long delayInMilliseconds) {
        if (this.timer.getFireIn() <= delayInMilliseconds) {
            if (delayInMilliseconds != 0) {
                String secondsString = Util.SecondsDisplayFormat.format(((double) delayInMilliseconds) / 1000.0d);
                this.logger.debug("Waiting to query attribution in %s seconds", secondsString);
            }
            this.timer.startIn(delayInMilliseconds);
        }
    }

    private void checkAttributionI(IActivityHandler activityHandler, ResponseData responseData) {
        if (responseData.jsonResponse != null) {
            long timerMilliseconds = responseData.jsonResponse.optLong("ask_in", -1);
            if (timerMilliseconds >= 0) {
                activityHandler.setAskingAttribution(true);
                getAttributionI(timerMilliseconds);
                return;
            }
            activityHandler.setAskingAttribution(false);
            responseData.attribution = AdjustAttribution.fromJson(responseData.jsonResponse.optJSONObject("attribution"), responseData.adid);
        }
    }

    private void checkSessionResponseI(IActivityHandler activityHandler, SessionResponseData sessionResponseData) {
        checkAttributionI(activityHandler, sessionResponseData);
        activityHandler.launchSessionResponseTasks(sessionResponseData);
    }

    private void checkSdkClickResponseI(IActivityHandler activityHandler, SdkClickResponseData sdkClickResponseData) {
        checkAttributionI(activityHandler, sdkClickResponseData);
        activityHandler.launchSdkClickResponseTasks(sdkClickResponseData);
    }

    private void checkAttributionResponseI(IActivityHandler activityHandler, AttributionResponseData attributionResponseData) {
        checkAttributionI(activityHandler, attributionResponseData);
        checkDeeplinkI(attributionResponseData);
        activityHandler.launchAttributionResponseTasks(attributionResponseData);
    }

    private void checkDeeplinkI(AttributionResponseData attributionResponseData) {
        if (attributionResponseData.jsonResponse != null) {
            JSONObject attributionJson = attributionResponseData.jsonResponse.optJSONObject("attribution");
            if (attributionJson != null) {
                String deeplinkString = attributionJson.optString(Constants.DEEPLINK, null);
                if (deeplinkString != null) {
                    attributionResponseData.deeplink = Uri.parse(deeplinkString);
                }
            }
        }
    }

    private void sendAttributionRequestI() {
        if (this.paused) {
            this.logger.debug("Attribution handler is paused", new Object[0]);
            return;
        }
        this.logger.verbose("%s", this.attributionPackage.getExtendedString());
        try {
            ResponseData responseData = UtilNetworking.createGETHttpsURLConnection(this.attributionPackage, this.basePath);
            if (responseData instanceof AttributionResponseData) {
                checkAttributionResponse((AttributionResponseData) responseData);
            }
        } catch (Exception e) {
            this.logger.error("Failed to get attribution (%s)", e.getMessage());
        }
    }
}
