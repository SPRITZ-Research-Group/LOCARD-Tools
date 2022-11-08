package com.applovin.impl.adview;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class c implements OnDismissListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.a.contractAd();
    }
}
