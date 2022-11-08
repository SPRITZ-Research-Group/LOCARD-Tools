package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;

class bb implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ ba b;

    bb(ba baVar, AppLovinAd appLovinAd) {
        this.b = baVar;
        this.a = appLovinAd;
    }

    public void run() {
        this.b.b.adReceived(this.a);
    }
}
