package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;
import com.applovin.sdk.AppLovinAd;

class bh implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ bg b;

    bh(bg bgVar, AppLovinAd appLovinAd) {
        this.b = bgVar;
        this.a = appLovinAd;
    }

    public void run() {
        Builder builder = new Builder(this.b.c);
        builder.setTitle((CharSequence) this.b.a.get(ea.V));
        builder.setMessage((CharSequence) this.b.a.get(ea.W));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.b.a.get(ea.X), new bi(this));
        builder.setNegativeButton((CharSequence) this.b.a.get(ea.Y), new bk(this));
        builder.show();
    }
}
