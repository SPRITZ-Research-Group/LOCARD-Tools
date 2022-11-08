package com.facebook.imagepipeline.common;

import android.graphics.Bitmap.Config;
import com.facebook.imagepipeline.f.c;
import com.facebook.imagepipeline.m.a;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class b {
    private static final b j = new b(new c());
    public final int a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final Config g;
    @Nullable
    public final c h;
    @Nullable
    public final a i;

    private b(c b) {
        this.a = b.a();
        this.b = b.b();
        this.c = b.c();
        this.d = b.d();
        this.e = b.f();
        this.g = b.g();
        this.h = b.e();
        this.f = b.h();
        this.i = b.i();
    }

    public static b a() {
        return j;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        b that = (b) o;
        if (this.b != that.b) {
            return false;
        }
        if (this.c != that.c) {
            return false;
        }
        if (this.d != that.d) {
            return false;
        }
        if (this.e != that.e) {
            return false;
        }
        if (this.f != that.f) {
            return false;
        }
        if (this.g != that.g) {
            return false;
        }
        if (this.h != that.h) {
            return false;
        }
        if (this.i != that.i) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        int i2 = 1;
        int i3 = 0;
        int i4 = ((this.a * 31) + (this.b ? 1 : 0)) * 31;
        if (this.c) {
            i = 1;
        } else {
            i = 0;
        }
        i4 = (i4 + i) * 31;
        if (this.d) {
            i = 1;
        } else {
            i = 0;
        }
        i4 = (i4 + i) * 31;
        if (this.e) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i4 + i) * 31;
        if (!this.f) {
            i2 = 0;
        }
        i2 = (((i + i2) * 31) + this.g.ordinal()) * 31;
        if (this.h != null) {
            i = this.h.hashCode();
        } else {
            i = 0;
        }
        i = (i2 + i) * 31;
        if (this.i != null) {
            i3 = this.i.hashCode();
        }
        return i + i3;
    }

    public final String toString() {
        return String.format(null, "%d-%b-%b-%b-%b-%b-%s-%s-%s", new Object[]{Integer.valueOf(this.a), Boolean.valueOf(this.b), Boolean.valueOf(this.c), Boolean.valueOf(this.d), Boolean.valueOf(this.e), Boolean.valueOf(this.f), this.g.name(), this.h, this.i});
    }
}
