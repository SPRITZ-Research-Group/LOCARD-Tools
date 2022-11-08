package com.facebook.imagepipeline.c;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.memory.d;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public final class a extends f {
    private final d a;

    public a(d bitmapPool) {
        this.a = bitmapPool;
    }

    public final com.facebook.common.f.a<Bitmap> a(int width, int height, Config bitmapConfig) {
        Bitmap bitmap = (Bitmap) this.a.a(com.facebook.imageutils.a.a(width, height, bitmapConfig));
        h.a(bitmap.getAllocationByteCount() >= (width * height) * com.facebook.imageutils.a.a(bitmapConfig));
        bitmap.reconfigure(width, height, bitmapConfig);
        return com.facebook.common.f.a.a(bitmap, this.a);
    }
}
