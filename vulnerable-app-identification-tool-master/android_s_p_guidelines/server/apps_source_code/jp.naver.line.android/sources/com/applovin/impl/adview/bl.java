package com.applovin.impl.adview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class bl implements AnimationListener {
    final /* synthetic */ View a;
    final /* synthetic */ boolean b;
    final /* synthetic */ az c;

    bl(az azVar, View view, boolean z) {
        this.c = azVar;
        this.a = view;
        this.b = z;
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.b) {
            this.a.setVisibility(4);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        this.a.setVisibility(0);
    }
}
