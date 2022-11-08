package com.applovin.impl.adview;

import android.webkit.WebSettings;

class u implements Runnable {
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Boolean b;
    final /* synthetic */ n c;

    u(n nVar, WebSettings webSettings, Boolean bool) {
        this.c = nVar;
        this.a = webSettings;
        this.b = bool;
    }

    public void run() {
        this.a.setNeedInitialFocus(this.b.booleanValue());
    }
}
