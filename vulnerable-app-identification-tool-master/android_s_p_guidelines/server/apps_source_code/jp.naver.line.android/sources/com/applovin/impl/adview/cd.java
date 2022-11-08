package com.applovin.impl.adview;

import android.content.Context;

class cd implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ cb b;

    cd(cb cbVar, Context context) {
        this.b = cbVar;
        this.a = context;
    }

    public void run() {
        this.b.a(this.a);
    }
}
