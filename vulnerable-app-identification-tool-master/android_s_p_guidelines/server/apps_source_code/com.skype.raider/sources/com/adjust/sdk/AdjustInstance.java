package com.adjust.sdk;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

public class AdjustInstance {
    private IActivityHandler activityHandler;
    private String basePath;
    private List<IRunActivityHandler> preLaunchActionsArray;
    private String pushToken;
    private Boolean startEnabled = null;
    private boolean startOffline = false;

    public void onCreate(AdjustConfig adjustConfig) {
        if (adjustConfig == null) {
            AdjustFactory.getLogger().error("AdjustConfig missing", new Object[0]);
        } else if (!adjustConfig.isValid()) {
            AdjustFactory.getLogger().error("AdjustConfig not initialized correctly", new Object[0]);
        } else if (this.activityHandler != null) {
            AdjustFactory.getLogger().error("Adjust already initialized", new Object[0]);
        } else {
            adjustConfig.preLaunchActionsArray = this.preLaunchActionsArray;
            adjustConfig.pushToken = this.pushToken;
            adjustConfig.startEnabled = this.startEnabled;
            adjustConfig.startOffline = this.startOffline;
            adjustConfig.basePath = this.basePath;
            this.activityHandler = AdjustFactory.getActivityHandler(adjustConfig);
            setSendingReferrersAsNotSent(adjustConfig.context);
        }
    }

    public void trackEvent(AdjustEvent event) {
        if (checkActivityHandler()) {
            this.activityHandler.trackEvent(event);
        }
    }

    public void onResume() {
        if (checkActivityHandler()) {
            this.activityHandler.onResume();
        }
    }

    public void onPause() {
        if (checkActivityHandler()) {
            this.activityHandler.onPause();
        }
    }

    public void setEnabled(boolean enabled) {
        this.startEnabled = Boolean.valueOf(enabled);
        if (checkActivityHandler(enabled, "enabled mode", "disabled mode")) {
            this.activityHandler.setEnabled(enabled);
        }
    }

    public boolean isEnabled() {
        if (checkActivityHandler()) {
            return this.activityHandler.isEnabled();
        }
        return isInstanceEnabled();
    }

    public void appWillOpenUrl(Uri url) {
        if (checkActivityHandler()) {
            this.activityHandler.readOpenUrl(url, System.currentTimeMillis());
        }
    }

    public void sendReferrer(String rawReferrer, Context context) {
        long clickTime = System.currentTimeMillis();
        if (rawReferrer != null && rawReferrer.length() != 0) {
            saveRawReferrer(rawReferrer, clickTime, context);
            if (checkActivityHandler(Constants.REFERRER) && this.activityHandler.isEnabled()) {
                this.activityHandler.sendReftagReferrer();
            }
        }
    }

    public void setOfflineMode(boolean enabled) {
        if (checkActivityHandler(enabled, "offline mode", "online mode")) {
            this.activityHandler.setOfflineMode(enabled);
        } else {
            this.startOffline = enabled;
        }
    }

    public void sendFirstPackages() {
        if (checkActivityHandler()) {
            this.activityHandler.sendFirstPackages();
        }
    }

    public void addSessionCallbackParameter(final String key, final String value) {
        if (checkActivityHandler("adding session callback parameter")) {
            this.activityHandler.addSessionCallbackParameter(key, value);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler(this) {
            final /* synthetic */ AdjustInstance c;

            public final void run(ActivityHandler activityHandler) {
                activityHandler.addSessionCallbackParameterI(key, value);
            }
        });
    }

    public void addSessionPartnerParameter(final String key, final String value) {
        if (checkActivityHandler("adding session partner parameter")) {
            this.activityHandler.addSessionPartnerParameter(key, value);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler(this) {
            final /* synthetic */ AdjustInstance c;

            public final void run(ActivityHandler activityHandler) {
                activityHandler.addSessionPartnerParameterI(key, value);
            }
        });
    }

    public void removeSessionCallbackParameter(final String key) {
        if (checkActivityHandler("removing session callback parameter")) {
            this.activityHandler.removeSessionCallbackParameter(key);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler(this) {
            final /* synthetic */ AdjustInstance b;

            public final void run(ActivityHandler activityHandler) {
                activityHandler.removeSessionCallbackParameterI(key);
            }
        });
    }

    public void removeSessionPartnerParameter(final String key) {
        if (checkActivityHandler("removing session partner parameter")) {
            this.activityHandler.removeSessionPartnerParameter(key);
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler(this) {
            final /* synthetic */ AdjustInstance b;

            public final void run(ActivityHandler activityHandler) {
                activityHandler.removeSessionPartnerParameterI(key);
            }
        });
    }

    public void resetSessionCallbackParameters() {
        if (checkActivityHandler("resetting session callback parameters")) {
            this.activityHandler.resetSessionCallbackParameters();
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler(this) {
            final /* synthetic */ AdjustInstance a;

            {
                this.a = this$0;
            }

            public final void run(ActivityHandler activityHandler) {
                activityHandler.resetSessionCallbackParametersI();
            }
        });
    }

    public void resetSessionPartnerParameters() {
        if (checkActivityHandler("resetting session partner parameters")) {
            this.activityHandler.resetSessionPartnerParameters();
            return;
        }
        if (this.preLaunchActionsArray == null) {
            this.preLaunchActionsArray = new ArrayList();
        }
        this.preLaunchActionsArray.add(new IRunActivityHandler(this) {
            final /* synthetic */ AdjustInstance a;

            {
                this.a = this$0;
            }

            public final void run(ActivityHandler activityHandler) {
                activityHandler.resetSessionPartnerParametersI();
            }
        });
    }

    public void teardown() {
        if (checkActivityHandler()) {
            this.activityHandler.teardown();
            this.activityHandler = null;
        }
    }

    public void setPushToken(String token) {
        if (checkActivityHandler("push token")) {
            this.activityHandler.setPushToken(token, false);
        } else {
            this.pushToken = token;
        }
    }

    public void setPushToken(String token, Context context) {
        savePushToken(token, context);
        if (checkActivityHandler("push token") && this.activityHandler.isEnabled()) {
            this.activityHandler.setPushToken(token, true);
        }
    }

    public String getAdid() {
        if (checkActivityHandler()) {
            return this.activityHandler.getAdid();
        }
        return null;
    }

    public AdjustAttribution getAttribution() {
        if (checkActivityHandler()) {
            return this.activityHandler.getAttribution();
        }
        return null;
    }

    private boolean checkActivityHandler() {
        return checkActivityHandler(null);
    }

    private boolean checkActivityHandler(boolean status, String trueMessage, String falseMessage) {
        if (status) {
            return checkActivityHandler(trueMessage);
        }
        return checkActivityHandler(falseMessage);
    }

    private boolean checkActivityHandler(String savedForLaunchWarningSuffixMessage) {
        if (this.activityHandler != null) {
            return true;
        }
        if (savedForLaunchWarningSuffixMessage != null) {
            AdjustFactory.getLogger().warn("Adjust not initialized, but %s saved for launch", savedForLaunchWarningSuffixMessage);
            return false;
        }
        AdjustFactory.getLogger().error("Adjust not initialized correctly", new Object[0]);
        return false;
    }

    private void saveRawReferrer(String rawReferrer, long clickTime, Context context) {
        final Context context2 = context;
        final String str = rawReferrer;
        final long j = clickTime;
        Util.runInBackground(new Runnable(this) {
            final /* synthetic */ AdjustInstance d;

            public final void run() {
                new SharedPreferencesManager(context2).saveRawReferrer(str, j);
            }
        });
    }

    private void savePushToken(final String pushToken, final Context context) {
        Util.runInBackground(new Runnable(this) {
            final /* synthetic */ AdjustInstance c;

            public final void run() {
                new SharedPreferencesManager(context).savePushToken(pushToken);
            }
        });
    }

    private void setSendingReferrersAsNotSent(final Context context) {
        Util.runInBackground(new Runnable(this) {
            final /* synthetic */ AdjustInstance b;

            public final void run() {
                new SharedPreferencesManager(context).setSendingReferrersAsNotSent();
            }
        });
    }

    private boolean isInstanceEnabled() {
        return this.startEnabled == null || this.startEnabled.booleanValue();
    }

    public void setTestOptions(AdjustTestOptions testOptions) {
        if (testOptions.basePath != null) {
            this.basePath = testOptions.basePath;
        }
        if (testOptions.baseUrl != null) {
            AdjustFactory.setBaseUrl(testOptions.baseUrl);
        }
        if (testOptions.useTestConnectionOptions != null && testOptions.useTestConnectionOptions.booleanValue()) {
            AdjustFactory.useTestConnectionOptions();
        }
        if (testOptions.timerIntervalInMilliseconds != null) {
            AdjustFactory.setTimerInterval(testOptions.timerIntervalInMilliseconds.longValue());
        }
        if (testOptions.timerStartInMilliseconds != null) {
            AdjustFactory.setTimerStart(testOptions.timerIntervalInMilliseconds.longValue());
        }
        if (testOptions.sessionIntervalInMilliseconds != null) {
            AdjustFactory.setSessionInterval(testOptions.sessionIntervalInMilliseconds.longValue());
        }
        if (testOptions.subsessionIntervalInMilliseconds != null) {
            AdjustFactory.setSubsessionInterval(testOptions.subsessionIntervalInMilliseconds.longValue());
        }
        if (testOptions.tryInstallReferrer != null) {
            AdjustFactory.setTryInstallReferrer(testOptions.tryInstallReferrer.booleanValue());
        }
    }
}
