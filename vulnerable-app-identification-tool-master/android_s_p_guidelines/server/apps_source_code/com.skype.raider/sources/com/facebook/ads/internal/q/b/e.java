package com.facebook.ads.internal.q.b;

import android.graphics.Bitmap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class e {
    static final int a;
    static final ExecutorService b;
    private static volatile boolean c = true;
    private final Bitmap d;
    private Bitmap e;
    private final a f = new d();

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        a = availableProcessors;
        b = Executors.newFixedThreadPool(availableProcessors);
    }

    public e(Bitmap bitmap) {
        this.d = bitmap;
    }

    public final Bitmap a() {
        return this.e;
    }

    public final Bitmap a(int i) {
        this.e = this.f.a(this.d, (float) i);
        return this.e;
    }
}
