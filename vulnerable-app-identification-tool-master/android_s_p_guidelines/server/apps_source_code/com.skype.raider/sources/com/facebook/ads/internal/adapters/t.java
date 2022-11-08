package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.c;
import com.facebook.ads.a;

public class t extends BroadcastReceiver {
    private String a;
    private Context b;
    private e c;
    private d d;

    public t(Context context, String str, d dVar, e eVar) {
        this.b = context;
        this.a = str;
        this.c = eVar;
        this.d = dVar;
    }

    public final void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.impression.logged:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.dismissed:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.clicked:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.error:" + this.a);
        intentFilter.addAction("com.facebook.ads.interstitial.activity_destroyed:" + this.a);
        c.a(this.b).a(this, intentFilter);
    }

    public final void b() {
        try {
            c.a(this.b).a((BroadcastReceiver) this);
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        Object obj = intent.getAction().split(":")[0];
        if (this.c != null && obj != null) {
            if ("com.facebook.ads.interstitial.clicked".equals(obj)) {
                this.c.a(null);
            } else if ("com.facebook.ads.interstitial.dismissed".equals(obj)) {
                this.c.c();
            } else if ("com.facebook.ads.interstitial.displayed".equals(obj)) {
                this.c.b();
            } else if ("com.facebook.ads.interstitial.impression.logged".equals(obj)) {
                this.c.a();
            } else if ("com.facebook.ads.interstitial.error".equals(obj)) {
                this.c.a(this.d, a.e);
            } else if ("com.facebook.ads.interstitial.activity_destroyed".equals(obj)) {
                this.c.d();
            }
        }
    }
}
