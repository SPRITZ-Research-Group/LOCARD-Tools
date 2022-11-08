package com.microsoft.skypemessagetextinput.module;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

public class RNModule extends ReactContextBaseJavaModule {
    private static final String GLOBAL_DATA_UPDATE_EVENT_NAME = "RNSkypeMessageTextInput-DataUpdateEvent";
    private static final String RN_CLASS = "SKPSkypeMessageTextInputModule";
    private static RNModule _sharedInstance;

    public static RNModule sharedInstance() {
        return _sharedInstance;
    }

    public RNModule(ag reactContext) {
        super(reactContext);
        _sharedInstance = this;
    }

    public void sendGlobalDataUpdateEvent(am event) {
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(GLOBAL_DATA_UPDATE_EVENT_NAME, event);
    }

    public String getName() {
        return RN_CLASS;
    }
}
