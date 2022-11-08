package com.skype.facebookaudiencenetwork;

import com.facebook.ads.h.b;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ReactModule(name = "NativeAdsManagersModule")
public class NativeAdsManagersModule extends ReactContextBaseJavaModule {
    static final String RN_CLASS = "NativeAdsManagersModule";
    private static final String TAG = "NativeAdsManagersModule";
    private final Map<String, NativeAdsManager> adsManagerMap = new ConcurrentHashMap();

    public NativeAdsManagersModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "NativeAdsManagersModule";
    }

    @ReactMethod
    public void init(String placementId, int adsToRequest, ae promise) {
        FLog.i("NativeAdsManagersModule", "init NativeAdsManager for placementId=" + placementId + " with " + adsToRequest + " ads");
        if (this.adsManagerMap.containsKey(placementId)) {
            FLog.w("NativeAdsManagersModule", "Trying to init NativeAdsManager using a placementId that was already initialized");
            promise.a(Boolean.valueOf(false));
            return;
        }
        ag reactContext = getReactApplicationContext();
        NativeAdsManager nativeAdsManager = new NativeAdsManager(reactContext, placementId, adsToRequest);
        nativeAdsManager.a(new NativeAdsListener(reactContext, placementId, nativeAdsManager));
        this.adsManagerMap.put(placementId, nativeAdsManager);
        nativeAdsManager.a(b.ALL);
        promise.a(Boolean.valueOf(true));
    }

    @ReactMethod
    public void loadAds(String placementId, ae promise) {
        FLog.i("NativeAdsManagersModule", "loadAds for NativeAdsManager for placementId=" + placementId);
        if (this.adsManagerMap.containsKey(placementId)) {
            ((NativeAdsManager) this.adsManagerMap.get(placementId)).a(b.ALL);
            promise.a(Boolean.valueOf(true));
            return;
        }
        FLog.w("NativeAdsManagersModule", "Trying to loadAds for NativeAdsManager using a placementId that have not been initialized yet");
        promise.a(Boolean.valueOf(false));
    }

    public NativeAdsManager getAdsManager(String placementId) {
        return (NativeAdsManager) this.adsManagerMap.get(placementId);
    }
}
