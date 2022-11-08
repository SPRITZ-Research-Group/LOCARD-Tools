package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.e;

final class ej {
    private final e a;
    private long b;

    public ej(e eVar) {
        ab.a((Object) eVar);
        this.a = eVar;
    }

    public final void a() {
        this.b = this.a.b();
    }

    public final void b() {
        this.b = 0;
    }

    public final boolean c() {
        return this.b == 0 || this.a.b() - this.b >= 3600000;
    }
}
