package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class k implements Runnable {
    final /* synthetic */ AppLovinAdClickListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ h c;

    k(h hVar, AppLovinAdClickListener appLovinAdClickListener, AppLovinAd appLovinAd) {
        this.c = hVar;
        this.a = appLovinAdClickListener;
        this.b = appLovinAd;
    }

    public void run() {
        this.a.adClicked(this.b);
    }
}
