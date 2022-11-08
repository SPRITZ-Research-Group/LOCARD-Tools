package com.facebook.imagepipeline.j;

import android.graphics.Bitmap;
import com.facebook.cache.a.c;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.nativecode.NativeBlurFilter;
import javax.annotation.Nullable;

public final class a extends com.facebook.imagepipeline.k.a {
    private final int b;
    private final int c;
    private c d;

    public a(int blurRadius) {
        this(blurRadius, (byte) 0);
    }

    private a(int blurRadius, byte b) {
        boolean z = true;
        h.a(true);
        if (blurRadius <= 0) {
            z = false;
        }
        h.a(z);
        this.b = 3;
        this.c = blurRadius;
    }

    public final void a(Bitmap bitmap) {
        NativeBlurFilter.iterativeBoxBlur(bitmap, this.b, this.c);
    }

    @Nullable
    public final c a() {
        if (this.d == null) {
            this.d = new com.facebook.cache.a.h(String.format(null, "i%dr%d", new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c)}));
        }
        return this.d;
    }
}
