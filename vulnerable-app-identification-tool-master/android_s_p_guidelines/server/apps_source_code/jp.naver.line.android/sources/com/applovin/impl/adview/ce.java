package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;

class ce implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ cb b;

    ce(cb cbVar, AppLovinAd appLovinAd) {
        this.b = cbVar;
        this.a = appLovinAd;
    }

    public void run() {
        if (this.b.h != null) {
            this.b.h.adReceived(this.a);
        }
    }
}
