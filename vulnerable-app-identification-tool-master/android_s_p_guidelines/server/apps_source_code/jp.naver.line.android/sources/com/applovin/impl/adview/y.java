package com.applovin.impl.adview;

import android.webkit.WebSettings;

class y implements Runnable {
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Boolean b;
    final /* synthetic */ n c;

    y(n nVar, WebSettings webSettings, Boolean bool) {
        this.c = nVar;
        this.a = webSettings;
        this.b = bool;
    }

    public void run() {
        this.a.setOffscreenPreRaster(this.b.booleanValue());
    }
}
