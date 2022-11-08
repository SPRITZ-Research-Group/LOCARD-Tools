package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.h;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class NativeRoundingFilter {
    @DoNotStrip
    private static native void nativeToCircleFilter(Bitmap bitmap, boolean z);

    @DoNotStrip
    private static native void nativeToCircleWithBorderFilter(Bitmap bitmap, int i, int i2, boolean z);

    public static void toCircle(Bitmap bitmap) {
        toCircle(bitmap, false);
    }

    public static void toCircle(Bitmap bitmap, boolean antiAliased) {
        h.a((Object) bitmap);
        nativeToCircleFilter(bitmap, antiAliased);
    }

    public static void toCircleWithBorder(Bitmap bitmap, int colorARGB, int borderWidthPx, boolean antiAliased) {
        h.a((Object) bitmap);
        nativeToCircleWithBorderFilter(bitmap, colorARGB, borderWidthPx, antiAliased);
    }

    static {
        SoLoader.a("native-filters");
    }
}
