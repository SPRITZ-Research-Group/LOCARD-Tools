package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdRewardListener;

class bl {
    private AppLovinSdkImpl a;
    private ax b;
    private Activity c;
    private AppLovinAdRewardListener d;
    private Runnable e;

    private bl() {
    }

    /* synthetic */ bl(bh bhVar) {
        this();
    }

    bg a() {
        return new bg();
    }

    bl a(Activity activity) {
        this.c = activity;
        return this;
    }

    bl a(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        return this;
    }

    bl a(ax axVar) {
        this.b = axVar;
        return this;
    }

    bl a(AppLovinAdRewardListener appLovinAdRewardListener) {
        this.d = appLovinAdRewardListener;
        return this;
    }

    bl a(Runnable runnable) {
        this.e = runnable;
        return this;
    }
}
