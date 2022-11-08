package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinSdk;

final class cd implements Runnable {
    final /* synthetic */ AppLovinAdClickListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;

    cd(AppLovinAdClickListener appLovinAdClickListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdClickListener;
        this.b = appLovinAd;
        this.c = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.adClicked(bv.b(this.b));
        } catch (Throwable th) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad event listener about ad being clicked", th);
        }
    }
}
