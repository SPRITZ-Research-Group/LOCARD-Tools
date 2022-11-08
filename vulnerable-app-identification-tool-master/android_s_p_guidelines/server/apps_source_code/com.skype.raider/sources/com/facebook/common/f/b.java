package com.facebook.common.f;

import java.lang.ref.SoftReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class b<T> {
    SoftReference<T> a = null;
    SoftReference<T> b = null;
    SoftReference<T> c = null;

    public final void a(@Nonnull T hardReference) {
        this.a = new SoftReference(hardReference);
        this.b = new SoftReference(hardReference);
        this.c = new SoftReference(hardReference);
    }

    @Nullable
    public final T a() {
        return this.a == null ? null : this.a.get();
    }

    public final void b() {
        if (this.a != null) {
            this.a.clear();
            this.a = null;
        }
        if (this.b != null) {
            this.b.clear();
            this.b = null;
        }
        if (this.c != null) {
            this.c.clear();
            this.c = null;
        }
    }
}
