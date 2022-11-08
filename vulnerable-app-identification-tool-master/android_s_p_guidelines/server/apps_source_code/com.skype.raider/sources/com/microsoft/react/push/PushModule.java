package com.microsoft.react.push;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.common.e;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.microsoft.react.push.adm.ADMPushRegistration;
import com.microsoft.react.push.adm.AdmTokenImpl;
import com.microsoft.react.push.b.a;
import com.microsoft.react.push.c.b;
import com.microsoft.react.push.gcm.RegistrationService;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class PushModule extends ReactContextBaseJavaModule implements d {
    private static final String RN_CLASS = "RNPushAndroid";
    private final Context context;
    private final Queue<Intent> delayedIntentQueue;
    private RCTNativeAppEventEmitter eventEmitter;
    private final int instanceId = new Random().nextInt();
    private volatile boolean javascriptModuleInitialized;
    private final b tokenProvider;

    public PushModule(ag reactContext, Queue<Intent> delayedIntentQueue) {
        b aVar;
        super(reactContext);
        this.delayedIntentQueue = delayedIntentQueue;
        this.context = reactContext.getApplicationContext();
        Context context = this.context;
        if (a.a(context)) {
            aVar = new com.microsoft.react.push.gcm.a();
        } else if (ADMPushRegistration.getInstance().isSupported(context)) {
            aVar = new AdmTokenImpl();
        } else {
            aVar = new com.microsoft.react.push.a.a();
        }
        this.tokenProvider = aVar;
        g.a().a((Context) reactContext);
    }

    public String getName() {
        return RN_CLASS;
    }

    public Map<String, Object> getConstants() {
        e.a<String, Object> builder = e.a();
        builder.a("NotificationsRegisteredEventName", "remoteNotificationsRegistered");
        builder.a("NotificationsUnregisteredEventName", "remoteNotificationsUnregistered");
        builder.a("NotificationReceivedEventName", "remoteNotificationReceived");
        builder.a("NotificationActionReceivedEventName", "LocalNotificationActionReceived");
        return builder.a();
    }

    @ReactMethod
    public void requestTokenAndCheckPermissions(am permissions, ae promise) {
        boolean isNotificationEnabled = NotificationManagerCompat.from(this.context).areNotificationsEnabled();
        String token = this.tokenProvider.getToken(this.context);
        FLog.i(RN_CLASS, "requestPermissions: registered: " + (!TextUtils.isEmpty(token)) + " isNotificationEnabled: " + isNotificationEnabled);
        FLog.d(RN_CLASS, "token: " + token);
        if (TextUtils.isEmpty(token)) {
            RegistrationService.a(this.context);
        } else {
            ar map = new WritableNativeMap();
            map.putString("deviceToken", token);
            getEmitter().emit("remoteNotificationsRegistered", map);
        }
        if (!this.javascriptModuleInitialized) {
            this.javascriptModuleInitialized = true;
            processQueue();
        }
        promise.a(Boolean.valueOf(isNotificationEnabled));
    }

    @ReactMethod
    public void abandonPermissions() {
        FLog.i(RN_CLASS, "abandonPermissions");
        RegistrationService.b(this.context);
    }

    @ReactMethod
    public void checkPermissions(ae promise) {
        boolean isNotificationEnabled = NotificationManagerCompat.from(this.context).areNotificationsEnabled();
        boolean registered = !TextUtils.isEmpty(this.tokenProvider.getToken(this.context));
        FLog.i(RN_CLASS, "checkPermissions registered: " + registered + " isNotificationEnabled: " + isNotificationEnabled);
        Object info = new WritableNativeMap();
        info.putBoolean("alert", registered);
        info.putBoolean("badge", registered);
        info.putBoolean("sound", registered);
        info.putBoolean("notification", isNotificationEnabled);
        promise.a(info);
    }

    @ReactMethod
    public void uninitialize() {
        FLog.i(RN_CLASS, "Clearing token locally");
        this.tokenProvider.setToken(this.context, null);
    }

    @ReactMethod
    public void registerCategories(al categories) {
        FLog.i(RN_CLASS, "registerCategories");
        f.a(c.a(categories));
    }

    @ReactMethod
    public void presentLocalNotification(am details, ae promise) {
        FLog.i(RN_CLASS, "presentLocalNotification");
        f.a(this.context, details);
        promise.a(null);
    }

    @ReactMethod
    public void cancelLocalNotification(String notificationId) {
        FLog.i(RN_CLASS, "cancelLocalNotification");
        if (notificationId != null) {
            NotificationManagerCompat.from(this.context).cancel(notificationId.hashCode());
        }
    }

    @ReactMethod
    public void cancelScheduledLocalNotification(String notificationId) {
        FLog.i(RN_CLASS, "cancelScheduledLocalNotification");
        if (notificationId != null) {
            ScheduledNotificationReceiver.a(this.context, notificationId);
        }
    }

    @ReactMethod
    public void cancelAllLocalNotifications() {
        FLog.i(RN_CLASS, "cancelAllLocalNotifications");
        NotificationManagerCompat.from(this.context).cancelAll();
    }

    @ReactMethod
    public void notificationProcessingCompleted(int notificationProcessingId) {
        if (notificationProcessingId != Integer.MIN_VALUE) {
            FLog.i(RN_CLASS, "finishPushProcessing for notificationProcessingId:" + notificationProcessingId);
            f.a(getReactApplicationContext(), notificationProcessingId);
            return;
        }
        FLog.e(RN_CLASS, "invalid notificationProcessingId has been passed");
    }

    @ReactMethod
    public void acknowledgeNotificationReceived(String correlationId, ae promise) {
        FLog.i(RN_CLASS, "acknowledgeNotificationReceived %s", (Object) correlationId);
        g.a().a(correlationId);
        promise.a(null);
    }

    @ReactMethod
    public void getUnacknowledgedNotifications(ae promise) {
        Object results = getFilteredUnacknowledgedNotifications();
        FLog.i(RN_CLASS, "getUnacknowledgedNotifications, count %d", Integer.valueOf(results.size()));
        promise.a(results);
    }

    @ReactMethod
    public void clearUnacknowledgedNotifications(ae promise) {
        Object results = getFilteredUnacknowledgedNotifications();
        FLog.i(RN_CLASS, "clearUnacknowledgedNotifications, count %d", Integer.valueOf(results.size()));
        g.a().c();
        promise.a(results);
    }

    @ReactMethod
    public void mostRecentPendingNotification(ae promise) {
        promise.a(intMostRecentPendingNotification());
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public ar mostRecentPendingNotificationSync() {
        return intMostRecentPendingNotification();
    }

    @ReactMethod
    public void supportsStackedNotifications(ae promise) {
        promise.a(Boolean.valueOf(f.a()));
    }

    private ar intMostRecentPendingNotification() {
        return notificationFromIntent((Intent) this.delayedIntentQueue.peek());
    }

    public void handleIntent(Intent intent) {
        if (this.javascriptModuleInitialized) {
            String action = intent.getAction();
            FLog.i(RN_CLASS, "handleIntent with action=" + action);
            if (action.equals("com.microsoft.react.push.PushConstants.ACTION_REGISTER")) {
                FLog.i(RN_CLASS, "handleIntent NOTIFICATION_REGISTER_EVENT");
                ar map = new WritableNativeMap();
                String token = intent.getStringExtra("com.microsoft.react.push.PushConstants.extra.token");
                map.putString("deviceToken", token);
                this.tokenProvider.setToken(this.context, token);
                getEmitter().emit("remoteNotificationsRegistered", map);
                return;
            } else if (action.equals("com.microsoft.react.push.PushConstants.ACTION_UNREGISTER")) {
                FLog.i(RN_CLASS, "handleIntent NOTIFICATION_UNREGISTER_EVENT");
                this.tokenProvider.setToken(this.context, null);
                getEmitter().emit("remoteNotificationsUnregistered", null);
                return;
            } else if (action.equals("com.microsoft.react.push.PushConstants.ACTION_MESSAGE_RECEIVED")) {
                am reactObject = notificationFromIntent(intent);
                try {
                    String strObject = j.a(reactObject);
                    g.a().a(reactObject.getString("correlationId"), strObject);
                } catch (JSONException e) {
                    FLog.e(RN_CLASS, "Failed to convert push notification to string, caching will be skipped");
                }
                getEmitter().emit("remoteNotificationReceived", reactObject);
                return;
            } else if (action.equals("DeleteNotificationActionReceived") || action.equals("LocalNotificationActionReceived")) {
                FLog.i(RN_CLASS, "handleIntent ACTION_LOCAL_NOTIFICATION_ACTION_RECEIVED");
                int notificationProcessingId = intent.getIntExtra("com.microsoft.react.push.PushConstants.notificationProcessingId", Integer.MIN_VALUE);
                String inlineReply = intent.getStringExtra("com.microsoft.react.push.PushConstants.extra.inline.reply.value");
                ar data = com.facebook.react.bridge.b.a(intent.getExtras());
                data.putInt("notificationProcessingId", notificationProcessingId);
                data.putString("textInputValue", inlineReply);
                getEmitter().emit("LocalNotificationActionReceived", data);
                return;
            } else if (action.equals("android.intent.action.MY_PACKAGE_REPLACED")) {
                RegistrationService.a(this.context);
                notificationProcessingCompleted(intent.getIntExtra("com.microsoft.react.push.PushConstants.notificationProcessingId", Integer.MIN_VALUE));
                return;
            } else {
                return;
            }
        }
        FLog.i(RN_CLASS, "handleIntent delaying message until JS is initialized");
        this.delayedIntentQueue.add(intent);
    }

    private ar notificationFromIntent(@Nullable Intent intent) {
        if (intent == null) {
            return null;
        }
        ar data = new WritableNativeMap();
        int notificationProcessingId = intent.getIntExtra("com.microsoft.react.push.PushConstants.notificationProcessingId", Integer.MIN_VALUE);
        long receivedTimeMs = intent.getLongExtra("receivedTime", System.currentTimeMillis());
        String correlationId = intent.getStringExtra("correlationId");
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        data.putInt("instanceId", this.instanceId);
        data.putString("correlationId", correlationId);
        data.putDouble("receivedTimeMs", (double) receivedTimeMs);
        data.putInt("notificationProcessingId", notificationProcessingId);
        data.putMap("data", com.facebook.react.bridge.b.a(intent.getExtras()));
        return data;
    }

    private RCTNativeAppEventEmitter getEmitter() {
        if (this.eventEmitter == null) {
            this.eventEmitter = (RCTNativeAppEventEmitter) getReactApplicationContext().a(RCTNativeAppEventEmitter.class);
        }
        return this.eventEmitter;
    }

    private void processQueue() {
        if (this.javascriptModuleInitialized && this.delayedIntentQueue != null) {
            FLog.i(RN_CLASS, "processing the queue of size: " + this.delayedIntentQueue.size());
            while (!this.delayedIntentQueue.isEmpty()) {
                handleIntent((Intent) this.delayedIntentQueue.poll());
            }
        }
    }

    private aq getFilteredUnacknowledgedNotifications() {
        aq results = new WritableNativeArray();
        Map<String, String> notifications = g.a().b();
        for (String key : notifications.keySet()) {
            String notification = (String) notifications.get(key);
            if (!notification.isEmpty()) {
                try {
                    ar reactObj = j.a(notification);
                    if (new JSONObject(notification).getInt("instanceId") != this.instanceId) {
                        results.pushMap(reactObj);
                    }
                } catch (JSONException e) {
                    FLog.e(RN_CLASS, "Failed to convert cached push notification");
                    g.a().a(key);
                } catch (NoSuchKeyException e2) {
                    FLog.e(RN_CLASS, "Failed to convert cached push notification");
                    g.a().a(key);
                }
            }
        }
        return results;
    }
}
