package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.c.b;
import com.google.android.gms.location.g;

final class z extends m {
    private b<Status> a;

    public z(b<Status> bVar) {
        this.a = bVar;
    }

    private final void d(int i) {
        if (this.a != null) {
            this.a.a(g.b(g.a(i)));
            this.a = null;
        }
    }

    public final void a(int i) {
    }

    public final void b(int i) {
        d(i);
    }

    public final void c(int i) {
        d(i);
    }
}
