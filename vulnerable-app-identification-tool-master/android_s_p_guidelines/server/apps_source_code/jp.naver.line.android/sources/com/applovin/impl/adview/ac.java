package com.applovin.impl.adview;

import android.webkit.WebView;

class ac implements Runnable {
    final /* synthetic */ n a;

    ac(n nVar) {
        this.a = nVar;
    }

    public void run() {
        WebView.setWebContentsDebuggingEnabled(true);
    }
}
