package com.facebook.react.modules.appstate;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

@ReactModule(name = "AppState")
public class AppStateModule extends ReactContextBaseJavaModule implements v {
    public static final String APP_STATE_ACTIVE = "active";
    public static final String APP_STATE_BACKGROUND = "background";
    protected static final String NAME = "AppState";
    private String mAppState = "uninitialized";

    public AppStateModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return NAME;
    }

    public void initialize() {
        getReactApplicationContext().a((v) this);
    }

    @ReactMethod
    public void getCurrentAppState(d success, d error) {
        success.invoke(createAppStateEventMap());
    }

    public void onHostResume() {
        this.mAppState = APP_STATE_ACTIVE;
        sendAppStateChangeEvent();
    }

    public void onHostPause() {
        this.mAppState = APP_STATE_BACKGROUND;
        sendAppStateChangeEvent();
    }

    public void onHostDestroy() {
    }

    private void sendAppStateChangeEvent() {
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("appStateDidChange", createAppStateEventMap());
    }

    private ar createAppStateEventMap() {
        ar appState = new WritableNativeMap();
        appState.putString("app_state", this.mAppState);
        return appState;
    }
}
