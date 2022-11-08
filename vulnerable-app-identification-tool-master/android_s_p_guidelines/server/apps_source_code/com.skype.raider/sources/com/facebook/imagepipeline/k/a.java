package com.facebook.imagepipeline.k;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import com.facebook.cache.a.c;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import javax.annotation.Nullable;

public abstract class a implements d {
    public static final Config a = Config.ARGB_8888;

    public final String b() {
        return "Unknown postprocessor";
    }

    public final com.facebook.common.f.a<Bitmap> a(Bitmap sourceBitmap, f bitmapFactory) {
        Config sourceBitmapConfig = sourceBitmap.getConfig();
        int width = sourceBitmap.getWidth();
        int height = sourceBitmap.getHeight();
        if (sourceBitmapConfig == null) {
            sourceBitmapConfig = a;
        }
        com.facebook.common.f.a<Bitmap> destBitmapRef = bitmapFactory.a(width, height, sourceBitmapConfig);
        try {
            a((Bitmap) destBitmapRef.a(), sourceBitmap);
            com.facebook.common.f.a<Bitmap> b = com.facebook.common.f.a.b(destBitmapRef);
            return b;
        } finally {
            com.facebook.common.f.a.c(destBitmapRef);
        }
    }

    public void a(Bitmap destBitmap, Bitmap sourceBitmap) {
        if (destBitmap.getConfig() == sourceBitmap.getConfig()) {
            Bitmaps.copyBitmap(destBitmap, sourceBitmap);
        } else {
            new Canvas(destBitmap).drawBitmap(sourceBitmap, 0.0f, 0.0f, null);
        }
        a(destBitmap);
    }

    public void a(Bitmap bitmap) {
    }

    @Nullable
    public c a() {
        return null;
    }
}
