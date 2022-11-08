package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinSdk;

final class cc implements Runnable {
    final /* synthetic */ AppLovinAdDisplayListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;

    cc(AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdDisplayListener;
        this.b = appLovinAd;
        this.c = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.adHidden(bv.b(this.b));
        } catch (Throwable th) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being hidden", th);
        }
    }
}
