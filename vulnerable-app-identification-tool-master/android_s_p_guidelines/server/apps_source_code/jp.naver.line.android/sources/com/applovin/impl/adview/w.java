package com.applovin.impl.adview;

import android.webkit.WebSettings;

class w implements Runnable {
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Boolean b;
    final /* synthetic */ n c;

    w(n nVar, WebSettings webSettings, Boolean bool) {
        this.c = nVar;
        this.a = webSettings;
        this.b = bool;
    }

    public void run() {
        this.a.setAllowUniversalAccessFromFileURLs(this.b.booleanValue());
    }
}
