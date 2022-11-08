package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.applovin.impl.sdk.ee;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class ax implements Runnable {
    final /* synthetic */ ar a;

    ax(ar arVar) {
        this.a = arVar;
    }

    public void run() {
        try {
            if (this.a.h == null) {
                this.a.c();
            }
            this.a.h.setVisibility(0);
            this.a.h.bringToFront();
            ee eeVar = new ee(this.a.b);
            Animation alphaAnimation = new AlphaAnimation(BitmapDescriptorFactory.HUE_RED, 1.0f);
            alphaAnimation.setDuration(eeVar.Q());
            alphaAnimation.setAnimationListener(new ay(this));
            this.a.h.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.a.c.e("ExpandedAdDialog", "Unable to fade in close button", th);
            this.a.c();
        }
    }
}
