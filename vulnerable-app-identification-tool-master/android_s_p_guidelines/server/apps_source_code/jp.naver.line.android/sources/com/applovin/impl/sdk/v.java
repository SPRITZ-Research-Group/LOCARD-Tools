package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdUpdateListener;
import java.util.Collection;
import java.util.HashSet;

class v {
    final Object a;
    AppLovinAd b;
    long c;
    boolean d;
    private final Collection<AppLovinAdUpdateListener> e;
    private final Collection<AppLovinAdLoadListener> f;

    private v() {
        this.a = new Object();
        this.e = new HashSet();
        this.f = new HashSet();
    }

    /* synthetic */ v(r rVar) {
        this();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AdLoadState{loadedAd=");
        stringBuilder.append(this.b);
        stringBuilder.append(", loadedAdExpiration=");
        stringBuilder.append(this.c);
        stringBuilder.append(", isWaitingForAd=");
        stringBuilder.append(this.d);
        stringBuilder.append(", updateListeners=");
        stringBuilder.append(this.e);
        stringBuilder.append(", pendingAdListeners=");
        stringBuilder.append(this.f);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
