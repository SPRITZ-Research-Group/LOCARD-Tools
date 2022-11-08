package com.skype.react.upgrade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.skype.react.activationExperiment.ActivationExperiment;
import com.skype.react.activationExperiment.models.AppLaunchState;
import com.skype.react.activationExperiment.models.EventOptions;
import com.skype.react.activationExperiment.models.WakeUpReason;
import com.skype.react.common.OnWakeCompleteCallback;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class AppUpgradeModule extends ReactContextBaseJavaModule implements UpgradeConstants {
    private static final String RN_CLASS = "RNAppUpgrade";
    private static boolean initialized;
    private Context appContext;
    private Queue<Intent> delayedIntentQueue;
    private RCTNativeAppEventEmitter eventEmitter;
    private int minWakeLockTaskId = -1;
    private ag reactContext;

    AppUpgradeModule(ag reactContext, Queue<Intent> delayedIntentQueue) {
        super(reactContext);
        this.reactContext = reactContext;
        this.appContext = reactContext.getApplicationContext();
        this.delayedIntentQueue = delayedIntentQueue;
    }

    public String getName() {
        return RN_CLASS;
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put("AppUpgradeEventName", "appUpgradeEvent");
        constants.put("AppRtcWakeEventName", "appRtcWakeEvent");
        constants.put("AppUpgradeScheduledNotificationEventName", "appUpgradeScheduledNotificationEvent");
        constants.put("NotificationClickedEventName", "notificationClickedEvent");
        constants.put("AppLaunchedByNotification", Boolean.valueOf(isAppLaunchAttribution()));
        constants.put("HasOldSessionPreferences", Boolean.valueOf(hasOldSessionPreferences()));
        return constants;
    }

    @ReactMethod
    public void initialize(ae promise) {
        FLog.i(RN_CLASS, "initialize");
        if (!initialized) {
            initialized = true;
            setUpMinWakeLockTask();
            processPendingIntents();
        }
        promise.a(null);
    }

    @ReactMethod
    public void finishTaskProcessing(int taskId, ae promise) {
        UpgradeContext.a(this.appContext, taskId);
        promise.a(null);
    }

    @ReactMethod
    public void delay(int timeInMs, final ae promise) {
        final int taskId = UpgradeContext.a(this.appContext);
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ AppUpgradeModule c;

            public final void run() {
                promise.a(null);
                UpgradeContext.a(this.c.appContext, taskId);
            }
        }, (long) timeInMs);
    }

    @ReactMethod
    public void hasUserDatabase(ae promise) {
        FLog.i(RN_CLASS, "hasUserDatabase:begin");
        for (String startsWith : this.reactContext.databaseList()) {
            if (startsWith.startsWith("s4l-")) {
                FLog.i(RN_CLASS, "hasUserDatabase:resolve");
                promise.a(null);
                return;
            }
        }
        FLog.i(RN_CLASS, "hasUserDatabase:reject");
        promise.a("NO_USER_DB", "There is no database created for a user");
    }

    private boolean hasOldSessionPreferences() {
        boolean preferenceExists = false;
        try {
            preferenceExists = !this.appContext.getSharedPreferences("HockeyApp", 0).getAll().isEmpty();
        } catch (Exception e) {
            FLog.i(RN_CLASS, "Error while checking old preferences for heuristics:" + e.getLocalizedMessage());
        }
        FLog.i(RN_CLASS, "hasOldSessionPreferences:" + preferenceExists);
        return preferenceExists;
    }

    @ReactMethod
    public void onLaunchStateChange(String launchState, final ae promise) {
        FLog.i(RN_CLASS, "onLaunchStateChange %s", (Object) launchState);
        if (AppLaunchState.SignedIn.toString().equals(launchState)) {
            ActivationExperiment.a(this.appContext, new OnWakeCompleteCallback(this) {
                final /* synthetic */ AppUpgradeModule b;

                public final void a(ar data) {
                    if (data != null) {
                        data.putString("wakeUpReason", WakeUpReason.UserSignIn.toString());
                    }
                    promise.a((Object) data);
                }
            });
        } else {
            promise.a(null);
        }
    }

    @ReactMethod
    public void onNotificationClicked(am options, final ae promise) {
        FLog.i(RN_CLASS, "onNotificationClicked %d");
        ActivationExperiment.c(this.appContext, options, new OnWakeCompleteCallback(this) {
            final /* synthetic */ AppUpgradeModule b;

            public final void a(ar data) {
                if (data != null) {
                    data.putString("wakeUpReason", WakeUpReason.NotificationClick.toString());
                }
                promise.a((Object) data);
            }
        });
    }

    @ReactMethod
    public void onUpgradeWakeUpForActivationExperiment(am data, final ae promise) {
        FLog.i(RN_CLASS, "onUpgradeWakeUpForActivationExperiment");
        ActivationExperiment.a(this.appContext, data, new OnWakeCompleteCallback(this) {
            final /* synthetic */ AppUpgradeModule b;

            public final void a(ar data) {
                if (data != null) {
                    data.putString("wakeUpReason", WakeUpReason.Upgrade.toString());
                }
                promise.a((Object) data);
            }
        });
    }

    @ReactMethod
    public void onRtcWakeUpForActivationExperiment(am data, final ae promise) {
        FLog.i(RN_CLASS, "scheduleWakeUpForActivationExperiment");
        ActivationExperiment.b(this.appContext, data, new OnWakeCompleteCallback(this) {
            final /* synthetic */ AppUpgradeModule b;

            public final void a(ar data) {
                if (data != null) {
                    data.putString("wakeUpReason", WakeUpReason.RtcWakeUp.toString());
                }
                promise.a((Object) data);
            }
        });
    }

    private void setUpMinWakeLockTask() {
        if (this.minWakeLockTaskId == -1) {
            this.minWakeLockTaskId = UpgradeContext.a(this.appContext);
            new Timer().schedule(new TimerTask(this) {
                final /* synthetic */ AppUpgradeModule a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    UpgradeContext.a(this.a.appContext, this.a.minWakeLockTaskId);
                    this.a.minWakeLockTaskId = -1;
                }
            }, 15000);
        }
    }

    private void processPendingIntents() {
        FLog.i(RN_CLASS, "processPendingIntents");
        if (this.delayedIntentQueue != null && initialized) {
            FLog.i(RN_CLASS, "processPendingIntents:delayedIntentQueue size(%d)", Integer.valueOf(this.delayedIntentQueue.size()));
            while (!this.delayedIntentQueue.isEmpty()) {
                Intent intent = (Intent) this.delayedIntentQueue.poll();
                FLog.i(RN_CLASS, "processPendingIntent with action: %s", intent.getAction());
                handleIntent(intent);
            }
        }
    }

    void handleIntent(Intent intent) {
        if (intent != null) {
            FLog.i(RN_CLASS, "App Received Intent with action - " + intent.getAction());
            if (initialized) {
                String action = intent.getAction();
                EventOptions eventOptions = new EventOptions(intent.getIntExtra("WakeEventReceiver.APP_WAKE_TASK_ID", 0));
                if ("android.intent.action.MY_PACKAGE_REPLACED".equals(action)) {
                    getEmitter().emit("appUpgradeEvent", eventOptions.a());
                } else if ("WakeEventReceiver.ACTION_NOTIFICATION_CLICKED".equals(action)) {
                    eventOptions.b((long) intent.getIntExtra("notificationId", -1));
                    getEmitter().emit("notificationClickedEvent", eventOptions.a());
                } else if ("WakeEventReceiver.ACTION_OEM_RTC_WAKE".equals(action)) {
                    eventOptions.a(intent.getLongExtra("scheduledWakeUpTime", -1));
                    getEmitter().emit("appRtcWakeEvent", eventOptions.a());
                }
                FLog.i(RN_CLASS, "Emitted event for intent with action %s with eventOptions: %s", intent.getAction(), eventOptions.toString());
                return;
            }
            FLog.i(RN_CLASS, "handleIntent when not initialized, %s", intent.getAction());
            this.delayedIntentQueue.add(intent);
        }
    }

    private RCTNativeAppEventEmitter getEmitter() {
        if (this.eventEmitter == null) {
            this.eventEmitter = (RCTNativeAppEventEmitter) getReactApplicationContext().a(RCTNativeAppEventEmitter.class);
        }
        return this.eventEmitter;
    }

    public static void raiseNotificationClickEvent(Context context, Intent intent) {
        UpgradeContext.a(context, intent);
    }

    private boolean isAppLaunchAttribution() {
        boolean attribution = PreferenceManager.getDefaultSharedPreferences(this.appContext).getBoolean("AppUpgradeModule.appLaunchByNotification", false);
        FLog.i(RN_CLASS, "Getting app launch attribution state = " + attribution);
        return attribution;
    }

    public static void setAppLaunchAttribution(Context context, boolean attribution) {
        FLog.i(RN_CLASS, "Saving app launch attribution state = " + attribution);
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("AppUpgradeModule.appLaunchByNotification", attribution);
        editor.apply();
    }
}
