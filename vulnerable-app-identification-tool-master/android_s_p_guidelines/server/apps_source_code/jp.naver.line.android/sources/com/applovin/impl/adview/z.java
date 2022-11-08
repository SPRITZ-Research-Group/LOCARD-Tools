package com.applovin.impl.adview;

import android.view.View;
import android.view.View.OnLongClickListener;

class z implements OnLongClickListener {
    final /* synthetic */ n a;

    z(n nVar) {
        this.a = nVar;
    }

    public boolean onLongClick(View view) {
        this.a.a.d("AdWebView", "Received a LongClick event.");
        return true;
    }
}
