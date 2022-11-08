package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class be implements Runnable {
    final /* synthetic */ az a;

    be(az azVar) {
        this.a = azVar;
    }

    public void run() {
        try {
            if (!(this.a.l || this.a.A == null)) {
                this.a.l = true;
                this.a.A.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(BitmapDescriptorFactory.HUE_RED, 1.0f);
                alphaAnimation.setDuration((long) this.a.settingsProxy.d());
                alphaAnimation.setRepeatCount(0);
                this.a.A.startAnimation(alphaAnimation);
                if (this.a.n() && this.a.B != null) {
                    this.a.B.setVisibility(0);
                    this.a.B.bringToFront();
                }
            }
        } catch (Throwable th) {
            this.a.logger.w("InterActivity", "Unable to show skip button: ".concat(String.valueOf(th)));
        }
    }
}
