package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

final class ce implements Runnable {
    final /* synthetic */ AppLovinAdVideoPlaybackListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;

    ce(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdVideoPlaybackListener;
        this.b = appLovinAd;
        this.c = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.videoPlaybackBegan(bv.b(this.b));
        } catch (Throwable th) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback began", th);
        }
    }
}
