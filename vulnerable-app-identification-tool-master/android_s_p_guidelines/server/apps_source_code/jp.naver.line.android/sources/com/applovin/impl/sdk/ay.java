package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import java.lang.ref.SoftReference;

class ay implements Runnable {
    final /* synthetic */ q a;
    final /* synthetic */ Context b;
    final /* synthetic */ AppLovinAdRewardListener c;
    final /* synthetic */ AppLovinAdVideoPlaybackListener d;
    final /* synthetic */ AppLovinAdDisplayListener e;
    final /* synthetic */ AppLovinAdClickListener f;
    final /* synthetic */ ax g;

    ay(ax axVar, q qVar, Context context, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.g = axVar;
        this.a = qVar;
        this.b = context;
        this.c = appLovinAdRewardListener;
        this.d = appLovinAdVideoPlaybackListener;
        this.e = appLovinAdDisplayListener;
        this.f = appLovinAdClickListener;
    }

    public void run() {
        AppLovinAd a = gd.a(this.a, this.g.a);
        if (a != null) {
            AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(this.g.a, this.b);
            AppLovinAdRewardListener bdVar = new bd(this.g, this.b, this.c, this.d, this.e, this.f, null);
            create.setAdDisplayListener(bdVar);
            create.setAdVideoPlaybackListener(bdVar);
            create.setAdClickListener(bdVar);
            create.showAndRender(a, this.g.d);
            this.g.k = new SoftReference(create);
            if (a instanceof an) {
                this.g.a((an) a, bdVar);
            }
            return;
        }
        this.g.a(this.a, this.d, this.e);
    }
}
