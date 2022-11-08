package com.applovin.impl.adview;

import android.webkit.WebSettings;

class x implements Runnable {
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Integer b;
    final /* synthetic */ n c;

    x(n nVar, WebSettings webSettings, Integer num) {
        this.c = nVar;
        this.a = webSettings;
        this.b = num;
    }

    public void run() {
        this.a.setMixedContentMode(this.b.intValue());
    }
}
