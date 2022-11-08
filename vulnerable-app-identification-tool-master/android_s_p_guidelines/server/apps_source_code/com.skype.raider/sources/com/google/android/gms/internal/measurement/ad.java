package com.google.android.gms.internal.measurement;

import android.os.Handler;
import com.google.android.gms.common.internal.ab;

abstract class ad {
    private static volatile Handler b;
    private final cu a;
    private final Runnable c;
    private volatile long d;

    ad(cu cuVar) {
        ab.a((Object) cuVar);
        this.a = cuVar;
        this.c = new ae(this, cuVar);
    }

    private final Handler d() {
        if (b != null) {
            return b;
        }
        Handler handler;
        synchronized (ad.class) {
            if (b == null) {
                b = new Handler(this.a.k().getMainLooper());
            }
            handler = b;
        }
        return handler;
    }

    public abstract void a();

    public final void a(long j) {
        c();
        if (j >= 0) {
            this.d = this.a.j().a();
            if (!d().postDelayed(this.c, j)) {
                this.a.q().v().a("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final boolean b() {
        return this.d != 0;
    }

    final void c() {
        this.d = 0;
        d().removeCallbacks(this.c);
    }
}
