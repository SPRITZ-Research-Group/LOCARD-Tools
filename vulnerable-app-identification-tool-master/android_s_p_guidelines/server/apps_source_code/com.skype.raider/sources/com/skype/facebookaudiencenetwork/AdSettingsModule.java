package com.skype.facebookaudiencenetwork;

import com.facebook.ads.c;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "AdSettingsModule")
public class AdSettingsModule extends ReactContextBaseJavaModule {
    static final String RN_CLASS = "AdSettingsModule";
    private static final String TAG = "AdSettingsModule";

    public AdSettingsModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "AdSettingsModule";
    }

    @ReactMethod
    public void addTestDevice(String deviceHashedId) {
        FLog.i("AdSettingsModule", "addTestDevice " + deviceHashedId);
        c.a(deviceHashedId);
    }

    @ReactMethod
    public void clearTestDevices() {
        FLog.i("AdSettingsModule", "clearTestDevices ");
        c.a();
    }

    @ReactMethod
    public void setIsChildDirected(boolean isDirected) {
        FLog.i("AdSettingsModule", "setIsChildDirected " + isDirected);
        c.d(isDirected);
    }

    @ReactMethod
    public void setMediationService(String mediationService) {
        FLog.i("AdSettingsModule", "setMediationService " + mediationService);
        c.c(mediationService);
    }

    @ReactMethod
    public void setUrlPrefix(String urlPrefix) {
        FLog.i("AdSettingsModule", "setUrlPrefix " + urlPrefix);
        c.b(urlPrefix);
    }

    @ReactMethod
    public void setDebugBuild(boolean debugBuild) {
        FLog.i("AdSettingsModule", "setDebugBuild " + debugBuild);
        c.a(debugBuild);
    }

    @ReactMethod
    public void setVideoAutoplay(boolean autoplay) {
        FLog.i("AdSettingsModule", "setVideoAutoplay " + autoplay);
        c.b(autoplay);
    }

    @ReactMethod
    public void setVideoAutoplayOnMobile(boolean autoplay) {
        FLog.i("AdSettingsModule", "setVideoAutoplayOnMobile " + autoplay);
        c.c(autoplay);
    }
}
