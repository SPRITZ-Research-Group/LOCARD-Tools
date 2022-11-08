package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;

final class cb implements Runnable {
    final /* synthetic */ AppLovinAdRewardListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ AppLovinSdk c;

    cb(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdRewardListener;
        this.b = appLovinAd;
        this.c = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.userDeclinedToViewAd(bv.b(this.b));
        } catch (Throwable th) {
            this.c.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about user declining to view ad", th);
        }
    }
}
