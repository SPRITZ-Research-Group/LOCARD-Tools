package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinLogger;

abstract class dx implements Runnable {
    final String c;
    protected final AppLovinSdkImpl d;
    final AppLovinLogger e;
    final Context f;
    protected boolean g;

    dx(String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null) {
            this.d = appLovinSdkImpl;
            if (str == null) {
                str = getClass().getSimpleName();
            }
            this.c = str;
            this.e = appLovinSdkImpl.getLogger();
            this.f = appLovinSdkImpl.getApplicationContext();
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    String a() {
        return this.c;
    }
}
