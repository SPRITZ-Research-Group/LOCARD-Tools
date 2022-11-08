package com.applovin.impl.adview;

import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;

class ad implements Runnable {
    final /* synthetic */ WebSettings a;
    final /* synthetic */ PluginState b;
    final /* synthetic */ n c;

    ad(n nVar, WebSettings webSettings, PluginState pluginState) {
        this.c = nVar;
        this.a = webSettings;
        this.b = pluginState;
    }

    public void run() {
        this.a.setPluginState(this.b);
    }
}
