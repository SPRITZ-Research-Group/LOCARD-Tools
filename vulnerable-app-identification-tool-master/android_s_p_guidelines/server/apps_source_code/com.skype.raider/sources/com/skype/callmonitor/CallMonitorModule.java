package com.skype.callmonitor;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.c;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.common.e;
import com.facebook.react.common.e.a;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.skype.slimcore.video.VideoViewManagerProvider;
import java.lang.ref.WeakReference;
import java.util.Map;

public class CallMonitorModule extends ReactContextBaseJavaModule {
    private static final String RN_CLASS = "CallMonitor";
    private RCTNativeAppEventEmitter eventEmitter;
    private boolean isServiceStarted = false;
    private BroadcastReceiver onReceive = new BroadcastReceiver(this) {
        final /* synthetic */ CallMonitorModule a;

        {
            this.a = this$0;
        }

        public final void onReceive(Context ctxt, Intent i) {
            String stringExtra = i.getStringExtra("actiontype");
            Object obj = -1;
            switch (stringExtra.hashCode()) {
                case -2082820760:
                    if (stringExtra.equals("monitorPressed")) {
                        obj = 6;
                        break;
                    }
                    break;
                case -1607757351:
                    if (stringExtra.equals("endCall")) {
                        obj = 3;
                        break;
                    }
                    break;
                case -840405966:
                    if (stringExtra.equals("unmute")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -454293902:
                    if (stringExtra.equals("localVideoStart")) {
                        obj = 4;
                        break;
                    }
                    break;
                case -291748878:
                    if (stringExtra.equals("localVideoStop")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 3363353:
                    if (stringExtra.equals("mute")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 1719956585:
                    if (stringExtra.equals("monitorReady")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    this.a.getEmitter().emit("CallMonitor-MonitorReady", null);
                    return;
                case 1:
                    this.a.getEmitter().emit("CallMonitor-MuteEvent", null);
                    return;
                case 2:
                    this.a.getEmitter().emit("CallMonitor-UnMuteEvent", null);
                    return;
                case 3:
                    this.a.getEmitter().emit("CallMonitor-EndCallEvent", null);
                    return;
                case 4:
                    this.a.startActivity();
                    this.a.getEmitter().emit("CallMonitor-LocalVideoStartEvent", null);
                    return;
                case 5:
                    this.a.startActivity();
                    this.a.getEmitter().emit("CallMonitor-LocalVideoStopEvent", null);
                    return;
                case 6:
                    this.a.startActivity();
                    this.a.getEmitter().emit("CallMonitor-CallMonitorPressed", null);
                    return;
                default:
                    return;
            }
        }
    };

    public CallMonitorModule(ag reactContext, WeakReference<VideoViewManagerProvider> videoViewManagerProvider) {
        super(reactContext);
        CallMonitorStorage.b().a(videoViewManagerProvider);
        c.a(getReactApplicationContext()).a(this.onReceive, new IntentFilter("callmonitormodule"));
    }

    public String getName() {
        return RN_CLASS;
    }

    public Map<String, Object> getConstants() {
        a<String, Object> builder = e.a();
        builder.a("OnMonitorReady", "CallMonitor-MonitorReady");
        builder.a("MuteEvent", "CallMonitor-MuteEvent");
        builder.a("UnmuteEvent", "CallMonitor-UnMuteEvent");
        builder.a("EndCallEvent", "CallMonitor-EndCallEvent");
        builder.a("LocalVideoStartEvent", "CallMonitor-LocalVideoStartEvent");
        builder.a("LocalVideoStopEvent", "CallMonitor-LocalVideoStopEvent");
        builder.a("MonitorPressedEvent", "CallMonitor-CallMonitorPressed");
        return builder.a();
    }

    @ReactMethod
    public void show() {
        FLog.i("CallMonitorService", "CallMonitorModule:show");
        if (!this.isServiceStarted) {
            startService();
        }
        LocalBroadcastHelper.a(getReactApplicationContext(), LocalBroadcastHelper.a("show"));
    }

    @ReactMethod
    public void hide() {
        if (this.isServiceStarted) {
            FLog.i("CallMonitorService", "CallMonitorModule:hide");
            LocalBroadcastHelper.a(getReactApplicationContext(), LocalBroadcastHelper.a("hide"));
        }
    }

    @ReactMethod
    public void stop() {
        FLog.i("CallMonitorService", "CallMonitorModule:stop");
        stopService();
    }

    @ReactMethod
    public void attachVideo(int id) {
        FLog.i("CallMonitorService", "CallMonitorModule:attachVideo");
        Intent intent = LocalBroadcastHelper.a("attachVideo");
        intent.putExtra("id", id);
        LocalBroadcastHelper.a(getReactApplicationContext(), intent);
    }

    @ReactMethod
    public void detachVideo() {
        FLog.i("CallMonitorService", "CallMonitorModule:detachVideo");
        LocalBroadcastHelper.a(getReactApplicationContext(), LocalBroadcastHelper.a("detachVideo"));
    }

    @ReactMethod
    public void updateMuted(boolean isMuted) {
        FLog.i("CallMonitorService", "CallMonitorModule:updateMuted isMuted: %b", Boolean.valueOf(isMuted));
        Intent intent = LocalBroadcastHelper.a("isMuted");
        intent.putExtra("isMutedValue", isMuted);
        LocalBroadcastHelper.a(getReactApplicationContext(), intent);
    }

    @ReactMethod
    public void updateVideoOn(boolean isVideoOn) {
        FLog.i("CallMonitorService", "CallMonitorModule:updateVideoOn isVideoOn: %b", Boolean.valueOf(isVideoOn));
        Intent intent = LocalBroadcastHelper.a("isVideoOn");
        intent.putExtra("isVideoOnValue", isVideoOn);
        LocalBroadcastHelper.a(getReactApplicationContext(), intent);
    }

    @ReactMethod
    public void conversationUpdated(am data) {
        FLog.i("CallMonitorService", "CallMonitorModule:conversationUpdated data: %s", data.toString());
        Intent intent = LocalBroadcastHelper.a("conversationUpdated");
        intent.putExtra("avatarUrl", data.hasKey("avatarUrl") ? data.getString("avatarUrl") : null);
        LocalBroadcastHelper.a(getReactApplicationContext(), intent);
    }

    @ReactMethod
    public void updateStrings(am data) {
        FLog.i("CallMonitorService", "CallMonitorModule:updateStrings data: %s", data.toString());
        Intent intent = LocalBroadcastHelper.a("updateStrings");
        intent.putExtra("map", data.toHashMap());
        LocalBroadcastHelper.a(getReactApplicationContext(), intent);
    }

    @ReactMethod
    public void showPortrait(boolean portrait) {
        FLog.i("CallMonitorService", "CallMonitorModule:showPortrait: %b", Boolean.valueOf(portrait));
        Intent intent = LocalBroadcastHelper.a("showPortrait");
        intent.putExtra("portrait", portrait);
        LocalBroadcastHelper.a(getReactApplicationContext(), intent);
    }

    private void startService() {
        if (getReactApplicationContext().startService(new Intent(getReactApplicationContext(), CallMonitorService.class)) != null) {
            this.isServiceStarted = true;
        } else {
            FLog.w("CallMonitorService", "CallMonitorModule:startService Unable to start service");
        }
    }

    private void stopService() {
        if (getReactApplicationContext().stopService(new Intent(getReactApplicationContext(), CallMonitorService.class))) {
            this.isServiceStarted = false;
        } else {
            FLog.w("CallMonitorService", "CallMonitorModule:stopService Unable to stop service");
        }
    }

    private void startActivity() {
        Context context = getReactApplicationContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.startActivity(intent);
        } else {
            context.startActivity(intent);
        }
    }

    private RCTNativeAppEventEmitter getEmitter() {
        if (this.eventEmitter == null) {
            this.eventEmitter = (RCTNativeAppEventEmitter) getReactApplicationContext().a(RCTNativeAppEventEmitter.class);
        }
        return this.eventEmitter;
    }
}
