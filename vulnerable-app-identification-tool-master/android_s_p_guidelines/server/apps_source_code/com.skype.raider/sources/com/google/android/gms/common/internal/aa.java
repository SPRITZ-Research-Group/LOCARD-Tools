package com.google.android.gms.common.internal;

import com.google.android.gms.c.g;
import com.google.android.gms.c.h;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.f;
import com.google.android.gms.common.api.i;

public final class aa {
    private static final b a = new aq();

    public interface a<R extends i, T> {
    }

    public interface b {
        com.google.android.gms.common.api.b a(Status status);
    }

    public static <R extends i> g<Void> a(f<R> fVar) {
        a asVar = new as();
        b bVar = a;
        h hVar = new h();
        fVar.a(new ar(fVar, hVar, asVar, bVar));
        return hVar.a();
    }
}
