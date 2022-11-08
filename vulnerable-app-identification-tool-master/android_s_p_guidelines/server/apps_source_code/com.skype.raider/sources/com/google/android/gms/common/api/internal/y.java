package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.a.b;
import com.google.android.gms.common.api.a.d;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.internal.c.a;

public final class y<O extends d> extends q {
    private final com.google.android.gms.common.api.d<O> a;

    public y(com.google.android.gms.common.api.d<O> dVar) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.a = dVar;
    }

    public final Looper a() {
        return this.a.d();
    }

    public final <A extends b, T extends a<? extends i, A>> T a(@NonNull T t) {
        return this.a.a(t);
    }

    public final void b() {
    }

    public final void c() {
    }
}
