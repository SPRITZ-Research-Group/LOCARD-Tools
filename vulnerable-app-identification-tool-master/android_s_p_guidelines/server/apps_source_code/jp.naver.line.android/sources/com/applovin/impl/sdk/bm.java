package com.applovin.impl.sdk;

import android.app.AlertDialog;
import com.applovin.impl.adview.az;

public class bm {
    private final AppLovinSdkImpl a;
    private final az b;
    private AlertDialog c;

    public bm(az azVar, AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = azVar;
    }

    public void a() {
        this.b.runOnUiThread(new bn(this));
    }

    public void b() {
        this.b.runOnUiThread(new bo(this));
    }

    public void c() {
        this.b.runOnUiThread(new br(this));
    }

    public boolean d() {
        return this.c != null ? this.c.isShowing() : false;
    }
}
