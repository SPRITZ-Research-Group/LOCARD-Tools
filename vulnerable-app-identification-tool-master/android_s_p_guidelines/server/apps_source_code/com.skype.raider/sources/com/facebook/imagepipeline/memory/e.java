package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.imageutils.a;

public final class e extends q<Bitmap> {
    public final /* synthetic */ int b(Object obj) {
        return a.a((Bitmap) obj);
    }

    private static boolean a(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }
        if (bitmap.isRecycled()) {
            FLog.wtf("BitmapPoolBackend", "Cannot reuse a recycled bitmap: %s", bitmap);
            return false;
        } else if (bitmap.isMutable()) {
            return true;
        } else {
            FLog.wtf("BitmapPoolBackend", "Cannot reuse an immutable bitmap: %s", bitmap);
            return false;
        }
    }

    @Nullable
    public final /* synthetic */ Object a(int i) {
        Bitmap bitmap = (Bitmap) super.a(i);
        if (bitmap == null || !a(bitmap)) {
            return null;
        }
        bitmap.eraseColor(0);
        return bitmap;
    }
}
