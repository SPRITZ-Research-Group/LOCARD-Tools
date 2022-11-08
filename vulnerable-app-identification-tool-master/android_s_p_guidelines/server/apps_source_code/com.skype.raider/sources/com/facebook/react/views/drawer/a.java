package com.facebook.react.views.drawer;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.d;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.bridge.ai;
import com.facebook.react.uimanager.events.e;

class a extends DrawerLayout {
    private int c = 8388611;
    private int d = -1;

    public a(ai reactContext) {
        super(reactContext);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!super.onInterceptTouchEvent(ev)) {
            return false;
        }
        e.a(this, ev);
        return true;
    }

    final void c() {
        c(this.c);
    }

    final void d() {
        d(this.c);
    }

    final void e(int drawerPosition) {
        this.c = drawerPosition;
        e();
    }

    final void f(int drawerWidthInPx) {
        this.d = drawerWidthInPx;
        e();
    }

    final void e() {
        if (getChildCount() == 2) {
            View drawerView = getChildAt(1);
            d layoutParams = (d) drawerView.getLayoutParams();
            layoutParams.a = this.c;
            layoutParams.width = this.d;
            drawerView.setLayoutParams(layoutParams);
            drawerView.setClickable(true);
        }
    }
}
