package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.common.a;
import com.facebook.imagepipeline.image.e;
import javax.annotation.Nullable;

public class s {
    private final Consumer<e> a;
    private final ak b;
    private long c = 0;
    private int d;
    @Nullable
    private a e;

    public s(Consumer<e> consumer, ak context) {
        this.a = consumer;
        this.b = context;
    }

    public final Consumer<e> a() {
        return this.a;
    }

    public final ak b() {
        return this.b;
    }

    public final String c() {
        return this.b.b();
    }

    public final am d() {
        return this.b.c();
    }

    public final Uri e() {
        return this.b.a().b();
    }

    public final long f() {
        return this.c;
    }

    public final void a(long lastIntermediateResultTimeMs) {
        this.c = lastIntermediateResultTimeMs;
    }

    public final int g() {
        return this.d;
    }

    public final void h() {
        this.d = 8;
    }

    @Nullable
    public final a i() {
        return this.e;
    }

    public final void a(a bytesRange) {
        this.e = bytesRange;
    }
}
