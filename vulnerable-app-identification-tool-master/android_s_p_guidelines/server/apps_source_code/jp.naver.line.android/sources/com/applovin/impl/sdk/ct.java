package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

class ct implements Runnable {
    final /* synthetic */ ck a;
    final /* synthetic */ cp b;

    ct(cp cpVar, ck ckVar) {
        this.b = cpVar;
        this.a = ckVar;
    }

    public void run() {
        this.b.b(this.a);
        if (this.a.getType() == AppLovinAdType.REGULAR) {
            if (this.a.getSize() == AppLovinAdSize.INTERSTITIAL) {
                this.b.b.prepareInterstitialAd(this.b.e, this.b.c.getApplicationContext());
            }
        } else if (this.a.getType() == AppLovinAdType.INCENTIVIZED) {
            this.b.b.prepareIncentivizedAd(this.b.e, this.b.c.getApplicationContext());
        }
    }
}
