package com.applovin.impl.sdk;

import android.os.Handler;
import android.os.Looper;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.lang.ref.WeakReference;
import java.util.Map;

public class h {
    private static final Handler a = new Handler(Looper.getMainLooper());
    private WeakReference<AppLovinAdDisplayListener> b = new WeakReference(null);
    private WeakReference<AppLovinAdClickListener> c = new WeakReference(null);
    private WeakReference<AppLovinAdRewardListener> d = new WeakReference(null);
    private AppLovinAdDisplayListener e;
    private AppLovinAdClickListener f;

    void a(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener) this.b.get();
        if (appLovinAdDisplayListener != null) {
            a.post(new i(this, appLovinAdDisplayListener, appLovinAd));
        }
        if (this.e != null) {
            this.e.adDisplayed(appLovinAd);
        }
    }

    public void a(AppLovinAdClickListener appLovinAdClickListener) {
        this.c = new WeakReference(appLovinAdClickListener);
    }

    public void a(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.b = new WeakReference(appLovinAdDisplayListener);
    }

    public void a(AppLovinAdRewardListener appLovinAdRewardListener) {
        this.d = new WeakReference(appLovinAdRewardListener);
    }

    void a(Map<String, String> map, ck ckVar) {
        AppLovinAdRewardListener appLovinAdRewardListener = (AppLovinAdRewardListener) this.d.get();
        if (appLovinAdRewardListener != null) {
            appLovinAdRewardListener.userRewardVerified(ckVar, map);
        }
    }

    void b(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener appLovinAdDisplayListener = (AppLovinAdDisplayListener) this.b.get();
        if (appLovinAdDisplayListener != null) {
            a.post(new j(this, appLovinAdDisplayListener, appLovinAd));
        }
        if (this.e != null) {
            this.e.adHidden(appLovinAd);
        }
    }

    void b(AppLovinAdClickListener appLovinAdClickListener) {
        this.f = appLovinAdClickListener;
    }

    void b(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.e = appLovinAdDisplayListener;
    }

    void b(Map<String, String> map, ck ckVar) {
        AppLovinAdRewardListener appLovinAdRewardListener = (AppLovinAdRewardListener) this.d.get();
        if (appLovinAdRewardListener != null) {
            appLovinAdRewardListener.userRewardRejected(ckVar, map);
        }
    }

    void c(AppLovinAd appLovinAd) {
        AppLovinAdClickListener appLovinAdClickListener = (AppLovinAdClickListener) this.c.get();
        if (appLovinAdClickListener != null) {
            a.post(new k(this, appLovinAdClickListener, appLovinAd));
        }
        if (this.f != null) {
            this.f.adClicked(appLovinAd);
        }
    }
}
