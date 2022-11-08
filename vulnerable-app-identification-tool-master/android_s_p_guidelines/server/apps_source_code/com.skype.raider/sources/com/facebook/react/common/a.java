package com.facebook.react.common;

public final class a<T> implements android.support.v4.util.j.a<T> {
    private final Object[] a = new Object[1024];
    private int b = 0;

    public final synchronized T a() {
        T toReturn = null;
        synchronized (this) {
            if (this.b != 0) {
                this.b--;
                int lastIndex = this.b;
                toReturn = this.a[lastIndex];
                this.a[lastIndex] = null;
            }
        }
        return toReturn;
    }

    public final synchronized boolean a(T obj) {
        boolean z;
        if (this.b == this.a.length) {
            z = false;
        } else {
            this.a[this.b] = obj;
            this.b++;
            z = true;
        }
        return z;
    }

    public final synchronized void b() {
        for (int i = 0; i < this.b; i++) {
            this.a[i] = null;
        }
        this.b = 0;
    }
}
