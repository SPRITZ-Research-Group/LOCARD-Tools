package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.c;
import com.facebook.ads.internal.view.f.a.a;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.f;
import com.facebook.ads.internal.view.f.b.g;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.p;
import com.facebook.ads.internal.view.j;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.io.Serializable;

public class ae extends BroadcastReceiver {
    private Context a;
    private j b;
    private boolean c = false;

    public ae(j jVar, Context context) {
        this.b = jVar;
        this.a = context.getApplicationContext();
    }

    public final void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.b.E());
        intentFilter.addAction("videoInterstitalEvent:" + this.b.E());
        intentFilter.addAction("performCtaClick:" + this.b.E());
        c.a(this.a).a(this, intentFilter);
    }

    public final void b() {
        try {
            c.a(this.a).a((BroadcastReceiver) this);
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        String[] split = intent.getAction().split(":");
        if (split.length != 2 || !split[1].equals(this.b.E())) {
            return;
        }
        if (split[0].equals("com.facebook.ads.interstitial.displayed")) {
            if (this.b.D() != null) {
                this.b.D();
                this.b.D().a();
            }
        } else if (split[0].equals("videoInterstitalEvent")) {
            Serializable serializableExtra = intent.getSerializableExtra(EventsEntry.COLUMN_NAME_EVENT);
            if (serializableExtra instanceof p) {
                if (this.b.D() != null) {
                    this.b.D();
                    this.b.D().a();
                }
                if (this.c) {
                    this.b.a(1);
                } else {
                    this.b.a(((p) serializableExtra).b());
                }
                this.b.setVisibility(0);
                this.b.a(a.USER_STARTED);
            } else if (serializableExtra instanceof f) {
                if (this.b.D() != null) {
                    this.b.D();
                }
            } else if (serializableExtra instanceof g) {
                if (this.b.D() != null) {
                    this.b.D();
                }
            } else if (serializableExtra instanceof b) {
                if (this.b.D() != null) {
                    this.b.D();
                }
                this.c = true;
            } else if (serializableExtra instanceof com.facebook.ads.internal.view.f.b.j) {
                if (this.b.D() != null) {
                    this.b.D();
                }
                this.c = false;
            } else if ((serializableExtra instanceof h) && this.b.D() != null) {
                this.b.D();
            }
        } else if (split[0].equals("performCtaClick")) {
            this.b.C();
        }
    }
}
