package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class j {
    protected volatile int b = -1;

    protected int a() {
        return 0;
    }

    public abstract j a(a aVar) throws IOException;

    public void a(b bVar) throws IOException {
    }

    public j b() throws CloneNotSupportedException {
        return (j) super.clone();
    }

    public final int c() {
        if (this.b < 0) {
            d();
        }
        return this.b;
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    public final int d() {
        int a = a();
        this.b = a;
        return a;
    }

    public String toString() {
        return k.a(this);
    }
}
