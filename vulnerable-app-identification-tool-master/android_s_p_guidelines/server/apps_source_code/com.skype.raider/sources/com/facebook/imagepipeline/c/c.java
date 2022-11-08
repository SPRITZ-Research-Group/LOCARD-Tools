package com.facebook.imagepipeline.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.f.a;

public final class c extends f {
    public final a<Bitmap> a(int width, int height, Config bitmapConfig) {
        return a.a(Bitmap.createBitmap(width, height, bitmapConfig), g.a());
    }
}
