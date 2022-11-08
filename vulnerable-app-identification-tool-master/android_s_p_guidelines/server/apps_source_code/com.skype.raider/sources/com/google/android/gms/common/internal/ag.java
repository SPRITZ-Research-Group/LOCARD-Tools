package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.api.a.h;

public final class ag<T extends IInterface> extends l<T> {
    private final h<T> e;

    protected final T a(IBinder iBinder) {
        return this.e.c();
    }

    public final int f() {
        return super.f();
    }

    protected final String h() {
        return this.e.a();
    }

    protected final String i() {
        return this.e.b();
    }

    public final h<T> s() {
        return this.e;
    }
}
