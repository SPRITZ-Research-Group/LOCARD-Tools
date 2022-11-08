package com.facebook.imagepipeline.image;

import com.facebook.imagepipeline.a.a.e;

public final class a extends c {
    private e a;

    public a(e imageResult) {
        this.a = imageResult;
    }

    public final synchronized int a() {
        return c() ? 0 : this.a.a().getWidth();
    }

    public final synchronized int b() {
        return c() ? 0 : this.a.a().getHeight();
    }

    public final void close() {
        synchronized (this) {
            if (this.a == null) {
                return;
            }
            e imageResult = this.a;
            this.a = null;
            imageResult.b();
        }
    }

    public final synchronized boolean c() {
        return this.a == null;
    }

    public final synchronized int d() {
        return c() ? 0 : this.a.a().getSizeInBytes();
    }

    public final boolean e() {
        return true;
    }

    public final synchronized e f() {
        return this.a;
    }
}
