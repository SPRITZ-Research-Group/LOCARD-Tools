package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v4.util.a;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.e;
import java.util.Map;

public final class n extends cs {
    private final Map<String, Long> a = new a();
    private final Map<String, Integer> b = new a();
    private long c;

    public n(bx bxVar) {
        super(bxVar);
    }

    @WorkerThread
    private final void a(long j, de deVar) {
        if (deVar == null) {
            q().C().a("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            q().C().a("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            df.a(deVar, bundle, true);
            e().a("am", "_xa", bundle);
        }
    }

    @WorkerThread
    private final void a(String str, long j, de deVar) {
        if (deVar == null) {
            q().C().a("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            q().C().a("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            df.a(deVar, bundle, true);
            e().a("am", "_xu", bundle);
        }
    }

    @WorkerThread
    private final void b(long j) {
        for (String put : this.a.keySet()) {
            this.a.put(put, Long.valueOf(j));
        }
        if (!this.a.isEmpty()) {
            this.c = j;
        }
    }

    static /* synthetic */ void b(n nVar, String str, long j) {
        nVar.c();
        ab.a(str);
        Integer num = (Integer) nVar.b.get(str);
        if (num != null) {
            de v = nVar.i().v();
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                nVar.b.remove(str);
                Long l = (Long) nVar.a.get(str);
                if (l == null) {
                    nVar.q().v().a("First ad unit exposure time was never set");
                } else {
                    long longValue = j - l.longValue();
                    nVar.a.remove(str);
                    nVar.a(str, longValue, v);
                }
                if (!nVar.b.isEmpty()) {
                    return;
                }
                if (nVar.c == 0) {
                    nVar.q().v().a("First ad exposure time was never set");
                    return;
                }
                nVar.a(j - nVar.c, v);
                nVar.c = 0;
                return;
            }
            nVar.b.put(str, Integer.valueOf(intValue));
            return;
        }
        nVar.q().v().a("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @WorkerThread
    public final void a(long j) {
        de v = i().v();
        for (String str : this.a.keySet()) {
            a(str, j - ((Long) this.a.get(str)).longValue(), v);
        }
        if (!this.a.isEmpty()) {
            a(j - this.c, v);
        }
        b(j);
    }

    public final void a(String str) {
        if (str == null || str.length() == 0) {
            q().v().a("Ad unit id must be a non-empty string");
            return;
        }
        p().a(new o(this, str, j().b()));
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final void b(String str) {
        if (str == null || str.length() == 0) {
            q().v().a("Ad unit id must be a non-empty string");
            return;
        }
        p().a(new p(this, str, j().b()));
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

    static /* synthetic */ void a(n nVar, String str, long j) {
        nVar.c();
        ab.a(str);
        if (nVar.b.isEmpty()) {
            nVar.c = j;
        }
        Integer num = (Integer) nVar.b.get(str);
        if (num != null) {
            nVar.b.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (nVar.b.size() >= 100) {
            nVar.q().y().a("Too many ads visible");
        } else {
            nVar.b.put(str, Integer.valueOf(1));
            nVar.a.put(str, Long.valueOf(j));
        }
    }
}
