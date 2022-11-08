package com.facebook.fresco.animation.bitmap.c;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.bitmap.a;
import com.facebook.imagepipeline.a.c.d;
import javax.annotation.Nullable;

public class b implements com.facebook.fresco.animation.bitmap.b {
    private static final Class<?> a = b.class;
    private final a b;
    private com.facebook.imagepipeline.a.a.a c;
    private d d;
    private final d.a e = new d.a(this) {
        final /* synthetic */ b a;

        {
            this.a = this$0;
        }

        @Nullable
        public final com.facebook.common.f.a<Bitmap> a(int frameNumber) {
            return this.a.b.a(frameNumber);
        }
    };

    public b(a bitmapFrameCache, com.facebook.imagepipeline.a.a.a animatedDrawableBackend) {
        this.b = bitmapFrameCache;
        this.c = animatedDrawableBackend;
        this.d = new d(this.c, this.e);
    }

    public final void a(@Nullable Rect bounds) {
        com.facebook.imagepipeline.a.a.a newBackend = this.c.a(bounds);
        if (newBackend != this.c) {
            this.c = newBackend;
            this.d = new d(this.c, this.e);
        }
    }

    public final int a() {
        return this.c.c();
    }

    public final int b() {
        return this.c.d();
    }

    public final boolean a(int frameNumber, Bitmap targetBitmap) {
        try {
            this.d.a(frameNumber, targetBitmap);
            return true;
        } catch (Throwable exception) {
            FLog.e(a, exception, "Rendering of frame unsuccessful. Frame number: %d", Integer.valueOf(frameNumber));
            return false;
        }
    }
}
