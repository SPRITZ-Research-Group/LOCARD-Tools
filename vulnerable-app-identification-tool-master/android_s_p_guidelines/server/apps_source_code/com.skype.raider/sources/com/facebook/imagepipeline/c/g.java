package com.facebook.imagepipeline.c;

import android.graphics.Bitmap;
import com.facebook.common.f.c;

public final class g implements c<Bitmap> {
    private static g a;

    public final /* synthetic */ void a(Object obj) {
        ((Bitmap) obj).recycle();
    }

    public static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    private g() {
    }
}
