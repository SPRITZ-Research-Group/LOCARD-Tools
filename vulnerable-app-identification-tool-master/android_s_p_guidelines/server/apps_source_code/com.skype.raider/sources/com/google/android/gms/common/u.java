package com.google.android.gms.common;

import android.support.annotation.NonNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
class u {
    private static final u b = new u(true, null, null);
    final boolean a;
    private final String c;
    private final Throwable d;

    u(boolean z, @Nullable String str, @Nullable Throwable th) {
        this.a = z;
        this.c = str;
        this.d = th;
    }

    static u a() {
        return b;
    }

    static u a(@NonNull String str) {
        return new u(false, str, null);
    }

    static u a(String str, a aVar, boolean z, boolean z2) {
        return new v(str, aVar, z, z2, (byte) 0);
    }

    static u a(@NonNull String str, @NonNull Throwable th) {
        return new u(false, str, th);
    }

    @Nullable
    String b() {
        return this.c;
    }

    final void c() {
        if (!this.a) {
            if (this.d != null) {
                b();
            } else {
                b();
            }
        }
    }
}
