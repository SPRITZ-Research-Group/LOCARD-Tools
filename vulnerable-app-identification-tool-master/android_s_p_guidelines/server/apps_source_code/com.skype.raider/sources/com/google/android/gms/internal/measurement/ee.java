package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;

public final class ee extends ct {
    private Handler a;
    @VisibleForTesting
    private long b = j().b();
    private final ad c = new ef(this, this.q);
    private final ad d = new eg(this, this.q);

    ee(bx bxVar) {
        super(bxVar);
    }

    static /* synthetic */ void a(ee eeVar, long j) {
        eeVar.c();
        eeVar.v();
        eeVar.c.c();
        eeVar.d.c();
        eeVar.q().C().a("Activity resumed, time", Long.valueOf(j));
        eeVar.b = j;
        if (eeVar.j().a() - eeVar.r().l.a() > eeVar.r().n.a()) {
            eeVar.r().m.a(true);
            eeVar.r().o.a(0);
        }
        if (eeVar.r().m.a()) {
            eeVar.c.a(Math.max(0, eeVar.r().k.a() - eeVar.r().o.a()));
        } else {
            eeVar.d.a(Math.max(0, 3600000 - eeVar.r().o.a()));
        }
    }

    private final void v() {
        synchronized (this) {
            if (this.a == null) {
                this.a = new Handler(Looper.getMainLooper());
            }
        }
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @WorkerThread
    public final boolean a(boolean z) {
        c();
        G();
        long b = j().b();
        r().n.a(j().a());
        long j = b - this.b;
        if (z || j >= 1000) {
            r().o.a(j);
            q().C().a("Recording user engagement, ms", Long.valueOf(j));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            df.a(i().v(), bundle, true);
            e().a("auto", "_e", bundle);
            this.b = b;
            this.d.c();
            this.d.a(Math.max(0, 3600000 - r().o.a()));
            return true;
        }
        q().C().a("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j));
        return false;
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    public final /* bridge */ /* synthetic */ af g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ di h() {
        return super.h();
    }

    public final /* bridge */ /* synthetic */ df i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ e j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ Context k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ar l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ at m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ ew n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ee o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ bs p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ av q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ bf r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    protected final boolean t() {
        return false;
    }

    static /* synthetic */ void a(ee eeVar) {
        eeVar.c();
        eeVar.a(false);
        eeVar.d().a(eeVar.j().b());
    }

    static /* synthetic */ void b(ee eeVar, long j) {
        eeVar.c();
        eeVar.v();
        eeVar.c.c();
        eeVar.d.c();
        eeVar.q().C().a("Activity paused, time", Long.valueOf(j));
        if (eeVar.b != 0) {
            eeVar.r().o.a(eeVar.r().o.a() + (j - eeVar.b));
        }
    }
}
