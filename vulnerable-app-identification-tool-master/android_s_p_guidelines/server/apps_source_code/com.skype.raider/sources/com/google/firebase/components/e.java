package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class e {
    private final Class<?> a;
    private final int b = 1;
    private final int c = 0;

    private e(Class<?> cls) {
        this.a = (Class) q.a((Object) cls, "Null dependency interface.");
    }

    @KeepForSdk
    public static e a(Class<?> cls) {
        return new e(cls);
    }

    public final Class<?> a() {
        return this.a;
    }

    public final boolean b() {
        return this.b == 1;
    }

    public final boolean c() {
        return this.c == 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.a == eVar.a && this.b == eVar.b && this.c == eVar.c;
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c;
    }

    public final String toString() {
        boolean z = true;
        StringBuilder append = new StringBuilder("Dependency{interface=").append(this.a).append(", required=").append(this.b == 1).append(", direct=");
        if (this.c != 0) {
            z = false;
        }
        return append.append(z).append("}").toString();
    }
}
