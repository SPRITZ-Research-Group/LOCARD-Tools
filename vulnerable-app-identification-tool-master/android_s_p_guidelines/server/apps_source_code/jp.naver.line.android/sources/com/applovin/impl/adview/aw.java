package com.applovin.impl.adview;

import android.view.View;
import android.view.View.OnClickListener;

class aw implements OnClickListener {
    final /* synthetic */ ar a;

    aw(ar arVar) {
        this.a = arVar;
    }

    public void onClick(View view) {
        if (this.a.h.isClickable()) {
            this.a.h.performClick();
        }
    }
}
