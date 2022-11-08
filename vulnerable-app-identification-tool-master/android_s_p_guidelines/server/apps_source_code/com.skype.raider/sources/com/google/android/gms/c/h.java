package com.google.android.gms.c;

import android.support.annotation.NonNull;

public final class h<TResult> {
    private final x<TResult> a = new x();

    @NonNull
    public final g<TResult> a() {
        return this.a;
    }

    public final void a(@NonNull Exception exception) {
        this.a.a(exception);
    }

    public final void a(TResult tResult) {
        this.a.a((Object) tResult);
    }

    public final boolean b(@NonNull Exception exception) {
        return this.a.b(exception);
    }

    public final boolean b(TResult tResult) {
        return this.a.b((Object) tResult);
    }
}
