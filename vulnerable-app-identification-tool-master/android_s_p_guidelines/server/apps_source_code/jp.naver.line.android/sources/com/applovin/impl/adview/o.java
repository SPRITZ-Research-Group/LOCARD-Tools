package com.applovin.impl.adview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class o implements OnTouchListener {
    final /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!view.hasFocus()) {
            view.requestFocus();
        }
        return false;
    }
}
