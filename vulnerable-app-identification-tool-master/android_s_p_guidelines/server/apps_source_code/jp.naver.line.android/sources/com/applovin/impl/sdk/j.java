package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class j implements Runnable {
    final /* synthetic */ AppLovinAdDisplayListener a;
    final /* synthetic */ AppLovinAd b;
    final /* synthetic */ h c;

    j(h hVar, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAd appLovinAd) {
        this.c = hVar;
        this.a = appLovinAdDisplayListener;
        this.b = appLovinAd;
    }

    public void run() {
        this.a.adHidden(this.b);
    }
}
