package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class i implements Runnable {
    final /* synthetic */ AppLovinAdDisplayListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ h c;

    i(h hVar, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd) {
        this.c = hVar;
        this.a = appLovinAdDisplayListener;
        this.b = appLovinAd;
    }

    public void run() {
        this.a.adDisplayed(this.b);
    }
}
