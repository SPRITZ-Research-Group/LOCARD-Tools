package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinSdk;

final class bw implements Runnable {
    final /* synthetic */ AppLovinAdDisplayListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;

    bw(AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdDisplayListener;
        this.b = appLovinAd;
        this.c = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.adDisplayed(bv.b(this.b));
        } catch (Throwable th) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being displayed", th);
        }
    }
}
