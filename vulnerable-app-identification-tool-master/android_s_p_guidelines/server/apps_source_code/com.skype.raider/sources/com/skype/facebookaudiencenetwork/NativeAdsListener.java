package com.skype.facebookaudiencenetwork;

import com.facebook.ads.j.a;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;

public class NativeAdsListener implements a {
    private final ag a;
    private final String b;
    private NativeAdsManager c;

    public NativeAdsListener(ag reactContext, String placementId, NativeAdsManager nativeAdsManager) {
        this.a = reactContext;
        this.b = placementId;
        this.c = nativeAdsManager;
    }

    public final void a() {
        FLog.d("NativeAdListener", "onAdsLoaded");
        ar adsManagerState = new WritableNativeMap();
        aq availableAds = new WritableNativeArray();
        for (String availableAd : this.c.c()) {
            availableAds.pushString(availableAd);
        }
        adsManagerState.putInt("uniqueNativeAdCount", this.c.a());
        adsManagerState.putBoolean("isLoaded", this.c.b());
        adsManagerState.putArray("availableAds", availableAds);
        a("NativeAdsManager_AdsLoaded_" + this.b, adsManagerState);
    }

    public final void a(com.facebook.ads.a adError) {
        FLog.w("NativeAdListener", "onAdError. code: " + adError.a() + " message: " + adError.b());
        ar adsManagerState = new WritableNativeMap();
        adsManagerState.putInt("errorCode", adError.a());
        adsManagerState.putString("errorMessage", adError.b());
        a("NativeAdsManager_AdsError_" + this.b, adsManagerState);
    }

    private void a(String eventName, Object params) {
        if (this.a != null && this.a.b()) {
            ((RCTNativeAppEventEmitter) this.a.a(RCTNativeAppEventEmitter.class)).emit(eventName, params);
        }
    }
}
