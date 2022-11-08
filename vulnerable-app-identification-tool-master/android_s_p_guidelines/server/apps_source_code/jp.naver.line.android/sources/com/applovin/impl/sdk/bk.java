package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bk implements OnClickListener {
    final /* synthetic */ bh a;

    bk(bh bhVar) {
        this.a = bhVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.b.b.a(this.a.a, this.a.b.e);
    }
}
