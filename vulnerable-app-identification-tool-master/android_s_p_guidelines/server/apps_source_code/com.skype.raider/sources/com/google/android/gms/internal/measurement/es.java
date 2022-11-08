package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.ab;
import java.util.ArrayList;
import java.util.List;

final class es implements ab {
    fm a;
    List<Long> b;
    List<fj> c;
    private long d;
    private final /* synthetic */ eo e;

    private es(eo eoVar) {
        this.e = eoVar;
    }

    /* synthetic */ es(eo eoVar, byte b) {
        this(eoVar);
    }

    private static long a(fj fjVar) {
        return ((fjVar.e.longValue() / 1000) / 60) / 60;
    }

    public final void a(fm fmVar) {
        ab.a((Object) fmVar);
        this.a = fmVar;
    }

    public final boolean a(long j, fj fjVar) {
        ab.a((Object) fjVar);
        if (this.c == null) {
            this.c = new ArrayList();
        }
        if (this.b == null) {
            this.b = new ArrayList();
        }
        if (this.c.size() > 0 && a((fj) this.c.get(0)) != a(fjVar)) {
            return false;
        }
        long d = this.d + ((long) fjVar.d());
        if (d >= ((long) Math.max(0, ((Integer) al.j.b()).intValue()))) {
            return false;
        }
        this.d = d;
        this.c.add(fjVar);
        this.b.add(Long.valueOf(j));
        return this.c.size() < Math.max(1, ((Integer) al.k.b()).intValue());
    }
}
