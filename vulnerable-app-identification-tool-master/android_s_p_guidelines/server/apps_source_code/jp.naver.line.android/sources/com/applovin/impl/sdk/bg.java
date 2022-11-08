package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Timer;

class bg {
    private final AppLovinSdkImpl a;
    private final ax b;
    private final Activity c;
    private final Runnable d;
    private final AppLovinAdRewardListener e;
    private final Timer f;

    private bg(bl blVar) {
        this.a = blVar.a;
        this.b = blVar.b;
        this.c = blVar.c;
        this.d = blVar.e;
        this.e = blVar.d;
        this.f = new Timer("IncentivizedAdLauncher");
    }

    /* synthetic */ bg(bl blVar, bh bhVar) {
        this(blVar);
    }

    static bl a() {
        return new bl();
    }

    void a(AppLovinAd appLovinAd) {
        this.c.runOnUiThread(new bh(this, appLovinAd));
    }
}
