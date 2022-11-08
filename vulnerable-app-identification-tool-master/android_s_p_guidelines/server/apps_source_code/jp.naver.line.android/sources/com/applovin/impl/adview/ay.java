package com.applovin.impl.adview;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ay implements AnimationListener {
    final /* synthetic */ ax a;

    ay(ax axVar) {
        this.a = axVar;
    }

    public void onAnimationEnd(Animation animation) {
        this.a.a.h.setClickable(true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
