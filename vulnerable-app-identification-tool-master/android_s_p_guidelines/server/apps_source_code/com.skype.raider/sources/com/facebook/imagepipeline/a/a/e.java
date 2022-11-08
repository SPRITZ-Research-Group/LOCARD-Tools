package com.facebook.imagepipeline.a.a;

import android.graphics.Bitmap;
import com.facebook.common.f.a;
import com.facebook.common.internal.h;
import java.util.List;
import javax.annotation.Nullable;

public final class e {
    private final c a;
    private final int b;
    @Nullable
    private a<Bitmap> c;
    @Nullable
    private List<a<Bitmap>> d;

    e(f builder) {
        this.a = (c) h.a(builder.a());
        this.b = builder.c();
        this.c = builder.b();
        this.d = builder.d();
    }

    private e(c image) {
        this.a = (c) h.a((Object) image);
        this.b = 0;
    }

    public static e a(c image) {
        return new e(image);
    }

    public static f b(c image) {
        return new f(image);
    }

    public final c a() {
        return this.a;
    }

    public final synchronized void b() {
        a.c(this.c);
        this.c = null;
        a.a(this.d);
        this.d = null;
    }
}
