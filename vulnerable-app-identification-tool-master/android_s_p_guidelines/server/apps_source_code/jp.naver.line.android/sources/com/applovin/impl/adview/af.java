package com.applovin.impl.adview;

import android.webkit.WebSettings;

class af implements Runnable {
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Boolean b;
    final /* synthetic */ n c;

    af(n nVar, WebSettings webSettings, Boolean bool) {
        this.c = nVar;
        this.a = webSettings;
        this.b = bool;
    }

    public void run() {
        this.a.setLoadWithOverviewMode(this.b.booleanValue());
    }
}
