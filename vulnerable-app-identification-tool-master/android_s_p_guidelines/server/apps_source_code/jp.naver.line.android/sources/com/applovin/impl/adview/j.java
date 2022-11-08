package com.applovin.impl.adview;

import com.applovin.impl.sdk.ar;
import com.applovin.impl.sdk.g;
import com.applovin.sdk.AppLovinAdSize;

class j implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private j(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    /* synthetic */ j(AdViewControllerImpl adViewControllerImpl, a aVar) {
        this(adViewControllerImpl);
    }

    public void run() {
        if (this.a.p != null) {
            StringBuilder stringBuilder;
            if (this.a.l != null) {
                stringBuilder = new StringBuilder("Rendering advertisement ad for #");
                stringBuilder.append(this.a.p.getAdIdNumber());
                stringBuilder.append(" over placement: \"");
                stringBuilder.append(this.a.h);
                stringBuilder.append("\"...");
                this.a.e.d("AppLovinAdView", stringBuilder.toString());
                AdViewControllerImpl.b(this.a.l, this.a.p.getSize());
                this.a.l.a(this.a.p, this.a.h);
                if (!(this.a.p.getSize() == AppLovinAdSize.INTERSTITIAL || this.a.x || (this.a.p instanceof ar))) {
                    this.a.i = new g(this.a.p, this.a.c);
                    this.a.i.a();
                    this.a.l.a(this.a.i);
                    return;
                }
            }
            stringBuilder = new StringBuilder("Unable to render advertisement for ad #");
            stringBuilder.append(this.a.p.getAdIdNumber());
            stringBuilder.append(". Please make sure you are not calling AppLovinAdView.destroy() prematurely.");
            this.a.c.getLogger().userError("AppLovinAdView", stringBuilder.toString());
        }
    }
}
