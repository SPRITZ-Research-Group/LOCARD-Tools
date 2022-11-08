package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.a.d;
import com.google.android.gms.common.internal.z;
import java.util.Arrays;

public final class ap<O extends d> {
    private final boolean a = false;
    private final int b;
    private final a<O> c;
    private final O d;

    private ap(a<O> aVar, O o) {
        this.c = aVar;
        this.d = o;
        this.b = Arrays.hashCode(new Object[]{this.c, this.d});
    }

    public static <O extends d> ap<O> a(a<O> aVar, O o) {
        return new ap(aVar, o);
    }

    public final String a() {
        return this.c.c();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ap)) {
            return false;
        }
        ap apVar = (ap) obj;
        return !this.a && !apVar.a && z.a(this.c, apVar.c) && z.a(this.d, apVar.d);
    }

    public final int hashCode() {
        return this.b;
    }
}
