package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.h;

@DoNotStrip
public class Bitmaps {
    @DoNotStrip
    private static native void nativeCopyBitmap(Bitmap bitmap, int i, Bitmap bitmap2, int i2, int i3);

    static {
        a.a();
    }

    public static void copyBitmap(Bitmap dest, Bitmap src) {
        boolean z;
        boolean z2 = true;
        h.a(src.getConfig() == dest.getConfig());
        h.a(dest.isMutable());
        if (dest.getWidth() == src.getWidth()) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (dest.getHeight() != src.getHeight()) {
            z2 = false;
        }
        h.a(z2);
        nativeCopyBitmap(dest, dest.getRowBytes(), src, src.getRowBytes(), dest.getHeight());
    }
}
