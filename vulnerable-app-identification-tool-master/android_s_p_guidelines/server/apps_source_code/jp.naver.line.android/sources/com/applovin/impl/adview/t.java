package com.applovin.impl.adview;

import android.webkit.WebSettings;

class t implements Runnable {
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Boolean b;
    final /* synthetic */ n c;

    t(n nVar, WebSettings webSettings, Boolean bool) {
        this.c = nVar;
        this.a = webSettings;
        this.b = bool;
    }

    public void run() {
        this.a.setGeolocationEnabled(this.b.booleanValue());
    }
}
