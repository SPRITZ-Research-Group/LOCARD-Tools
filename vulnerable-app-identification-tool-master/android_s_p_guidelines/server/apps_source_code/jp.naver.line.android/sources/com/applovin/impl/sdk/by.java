package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;
import java.util.Map;

final class by implements Runnable {
    final /* synthetic */ AppLovinAdRewardListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ Map c;
    final /* synthetic */ AppLovinSdk d;

    by(AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAd appLovinAd, Map map, AppLovinSdk appLovinSdk) {
        this.a = appLovinAdRewardListener;
        this.b = appLovinAd;
        this.c = map;
        this.d = appLovinSdk;
    }

    public final void run() {
        try {
            this.a.userOverQuota(bv.b(this.b), this.c);
        } catch (Throwable th) {
            this.d.getLogger().userError("ListenerCallbackInvoker", "Unable to notify ad reward listener about exceeding quota", th);
        }
    }
}
