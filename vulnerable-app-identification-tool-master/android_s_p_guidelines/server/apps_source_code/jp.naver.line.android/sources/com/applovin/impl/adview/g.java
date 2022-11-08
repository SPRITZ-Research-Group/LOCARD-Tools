package com.applovin.impl.adview;

import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.bv;
import com.applovin.sdk.AppLovinAd;

class g implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    g(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        if (this.a.t != null || this.a.s != null) {
            AppLovinAd a;
            if (this.a.t != null) {
                a = this.a.t.a();
                this.a.t.dismiss();
                this.a.t = null;
            } else if (this.a.s != null) {
                a = this.a.s.a();
                this.a.s.dismiss();
                this.a.s = null;
            } else {
                a = null;
            }
            bv.b(this.a.B, a, (AppLovinAdView) this.a.b, this.a.c);
        }
    }
}
