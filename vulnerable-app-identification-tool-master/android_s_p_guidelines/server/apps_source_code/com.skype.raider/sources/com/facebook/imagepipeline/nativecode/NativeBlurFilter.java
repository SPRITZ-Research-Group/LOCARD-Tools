package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.h;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class NativeBlurFilter {
    @DoNotStrip
    private static native void nativeIterativeBoxBlur(Bitmap bitmap, int i, int i2);

    public static void iterativeBoxBlur(Bitmap bitmap, int iterations, int blurRadius) {
        boolean z;
        boolean z2 = true;
        h.a((Object) bitmap);
        if (iterations > 0) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (blurRadius <= 0) {
            z2 = false;
        }
        h.a(z2);
        nativeIterativeBoxBlur(bitmap, iterations, blurRadius);
    }

    static {
        SoLoader.a("native-filters");
    }
}
