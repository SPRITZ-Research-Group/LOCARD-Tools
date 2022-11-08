package com.facebook.react.bridge;

import android.support.v4.util.j.b;
import javax.annotation.Nullable;

public final class h implements g {
    private static final b<h> a = new b(10);
    @Nullable
    private al b;
    private int c = -1;

    private h() {
    }

    public static h a(al array, int index) {
        h dynamic = (h) a.a();
        if (dynamic == null) {
            dynamic = new h();
        }
        dynamic.b = array;
        dynamic.c = index;
        return dynamic;
    }

    public final void e() {
        this.b = null;
        this.c = -1;
        a.a(this);
    }

    public final boolean a() {
        if (this.b != null) {
            return this.b.isNull(this.c);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public final double b() {
        if (this.b != null) {
            return this.b.getDouble(this.c);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public final String c() {
        if (this.b != null) {
            return this.b.getString(this.c);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public final ReadableType d() {
        if (this.b != null) {
            return this.b.getType(this.c);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }
}
