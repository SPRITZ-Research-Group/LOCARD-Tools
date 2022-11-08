package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;

final class ca implements Runnable {
    final /* synthetic */ AppLovinAdRewardListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ int c;
    final /* synthetic */ AppLovinSdk d;

    ca(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, int i, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdRewardListener;
        this.b = appLovinAd;
        this.c = i;
        this.d = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.validationRequestFailed(bv.b(this.b), this.c);
        } catch (Throwable th) {
            this.d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about reward validation request failing", th);
        }
    }
}
