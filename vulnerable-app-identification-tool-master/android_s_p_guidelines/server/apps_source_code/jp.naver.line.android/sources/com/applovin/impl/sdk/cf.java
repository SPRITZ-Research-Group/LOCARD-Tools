package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

final class cf implements Runnable {
    final /* synthetic */ AppLovinAdVideoPlaybackListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ double c;
    final /* synthetic */ boolean d;
    final /* synthetic */ AppLovinSdk e;

    cf(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAd appLovinAd, double d, boolean z, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdVideoPlaybackListener;
        this.b = appLovinAd;
        this.c = d;
        this.d = z;
        this.e = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.videoPlaybackEnded(bv.b(this.b), this.c, this.d);
        } catch (Throwable th) {
            this.e.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad playback ended", th);
        }
    }
}
