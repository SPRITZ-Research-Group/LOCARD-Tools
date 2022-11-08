package com.facebook;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.facebook.internal.bn;
import defpackage.lj;

public abstract class g {
    private static final String a = "g";
    private final BroadcastReceiver b;
    private final lj c;
    private boolean d = false;

    protected abstract void a(AccessToken accessToken);

    public g() {
        bn.a();
        this.b = new h();
        this.c = lj.a(s.f());
        a();
    }

    public final void a() {
        if (!this.d) {
            e();
            this.d = true;
        }
    }

    public final void b() {
        if (this.d) {
            this.c.a(this.b);
            this.d = false;
        }
    }

    public final boolean c() {
        return this.d;
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        this.c.a(this.b, intentFilter);
    }
}
