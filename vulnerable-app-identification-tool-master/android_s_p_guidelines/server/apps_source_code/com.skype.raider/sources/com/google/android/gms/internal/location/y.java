package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.c.b;
import com.google.android.gms.location.g;

final class y extends m {
    private b<Status> a;

    public y(b<Status> bVar) {
        this.a = bVar;
    }

    public final void a(int i) {
        if (this.a != null) {
            this.a.a(g.b(g.a(i)));
            this.a = null;
        }
    }

    public final void b(int i) {
    }

    public final void c(int i) {
    }
}
