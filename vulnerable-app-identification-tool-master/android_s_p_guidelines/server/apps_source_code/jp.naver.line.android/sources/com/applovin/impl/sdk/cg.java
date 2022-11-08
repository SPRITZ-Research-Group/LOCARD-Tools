package com.applovin.impl.sdk;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdk;

final class cg implements Runnable {
    final /* synthetic */ AppLovinAdViewEventListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinAdView c;
    final /* synthetic */ AppLovinSdk d;

    cg(AppLovinAdViewEventListener appLovinAdViewEventListener, AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdViewEventListener;
        this.b = appLovinAd;
        this.c = appLovinAdView;
        this.d = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.adOpenedFullscreen(bv.b(this.b), this.c);
        } catch (Throwable th) {
            this.d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about fullscreen opened event", th);
        }
    }
}
