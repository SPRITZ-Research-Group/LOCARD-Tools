package com.facebook.imagepipeline.memory;

import android.support.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public abstract class q<T> implements aa<T> {
    private final Set<T> a = new HashSet();
    private final g<T> b = new g();

    @Nullable
    public T a(int size) {
        return c(this.b.a(size));
    }

    public void a(T item) {
        boolean wasAdded;
        synchronized (this) {
            wasAdded = this.a.add(item);
        }
        if (wasAdded) {
            this.b.a(b(item), item);
        }
    }

    @Nullable
    public final T a() {
        return c(this.b.a());
    }

    private T c(@Nullable T t) {
        if (t != null) {
            synchronized (this) {
                this.a.remove(t);
            }
        }
        return t;
    }
}
