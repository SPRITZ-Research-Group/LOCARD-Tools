package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.bv;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.m;

class b implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    b(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        if (this.a.s == null && (this.a.p instanceof m) && this.a.l != null) {
            m mVar = (m) this.a.p;
            Context f = this.a.a;
            if (!(f instanceof Activity)) {
                f = gd.a(this.a.l, this.a.c);
            }
            if (f instanceof Activity) {
                if (this.a.b != null) {
                    this.a.b.removeView(this.a.l);
                }
                this.a.s = new ar(mVar, this.a.h, this.a.l, (Activity) f, this.a.c);
                this.a.s.setOnDismissListener(new c(this));
                this.a.s.show();
                bv.a(this.a.B, this.a.p, (AppLovinAdView) this.a.b, this.a.c);
                if (this.a.i != null) {
                    this.a.i.d();
                    return;
                }
            }
            this.a.e.userError("AppLovinAdView", "Unable to expand ad. No Activity found.");
            Uri g = mVar.g();
            ee eeVar = new ee(this.a.c);
            if (g != null && eeVar.ah()) {
                this.a.d.trackAndLaunchClick(mVar, this.a.h, this.a.getParentView(), this.a, g);
                if (this.a.i != null) {
                    this.a.i.b();
                }
            }
            this.a.l.a("javascript:al_onFailedExpand();");
        }
    }
}
