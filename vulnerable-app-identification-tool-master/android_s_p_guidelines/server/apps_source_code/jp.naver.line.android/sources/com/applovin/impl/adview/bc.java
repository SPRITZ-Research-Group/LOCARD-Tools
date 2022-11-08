package com.applovin.impl.adview;

import android.view.View;
import android.view.View.OnClickListener;

class bc implements OnClickListener {
    final /* synthetic */ az a;

    bc(az azVar) {
        this.a = azVar;
    }

    public void onClick(View view) {
        this.a.toggleMute();
    }
}
