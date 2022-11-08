package com.facebook.react.bridge;

import android.support.v4.util.j.b;
import javax.annotation.Nullable;

public final class i implements g {
    private static final b<i> a = new b(10);
    @Nullable
    private am b;
    @Nullable
    private String c;

    private i() {
    }

    public static i a(am map, String name) {
        i dynamic = (i) a.a();
        if (dynamic == null) {
            dynamic = new i();
        }
        dynamic.b = map;
        dynamic.c = name;
        return dynamic;
    }

    public final void e() {
        this.b = null;
        this.c = null;
        a.a(this);
    }

    public final boolean a() {
        if (this.b != null && this.c != null) {
            return this.b.isNull(this.c);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public final double b() {
        if (this.b != null && this.c != null) {
            return this.b.getDouble(this.c);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public final String c() {
        if (this.b != null && this.c != null) {
            return this.b.getString(this.c);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public final ReadableType d() {
        if (this.b != null && this.c != null) {
            return this.b.getType(this.c);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }
}
