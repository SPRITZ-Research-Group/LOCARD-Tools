package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.c.h;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.b;
import com.google.android.gms.common.api.internal.d.a;

public final class an<ResultT> extends r {
    private final k<b, ResultT> a;
    private final h<ResultT> b;
    private final j c;

    public final void a(@NonNull Status status) {
        this.b.b(this.c.a(status));
    }

    public final void a(a<?> aVar) throws DeadObjectException {
    }

    public final void a(@NonNull l lVar, boolean z) {
        lVar.a(this.b, z);
    }

    public final void a(@NonNull RuntimeException runtimeException) {
        this.b.b((Exception) runtimeException);
    }

    @Nullable
    public final Feature[] a() {
        return this.a.a();
    }

    public final boolean b() {
        return this.a.b();
    }
}
