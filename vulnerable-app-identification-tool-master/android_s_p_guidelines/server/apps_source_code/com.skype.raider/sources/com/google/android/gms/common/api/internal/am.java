package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.b;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.internal.c.a;

public final class am<A extends a<? extends i, b>> extends r {
    private final A a;

    public am(A a) {
        super(1);
        this.a = a;
    }

    public final void a(@NonNull Status status) {
        this.a.a(status);
    }

    public final void a(d.a<?> aVar) throws DeadObjectException {
        try {
            this.a.a(aVar.d());
        } catch (RuntimeException e) {
            a(e);
        }
    }

    public final void a(@NonNull l lVar, boolean z) {
        lVar.a(this.a, z);
    }

    public final void a(@NonNull RuntimeException runtimeException) {
        String simpleName = runtimeException.getClass().getSimpleName();
        String localizedMessage = runtimeException.getLocalizedMessage();
        this.a.a(new Status(10, new StringBuilder((String.valueOf(simpleName).length() + 2) + String.valueOf(localizedMessage).length()).append(simpleName).append(": ").append(localizedMessage).toString()));
    }
}
