package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.e.d;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public final class h extends a<Bitmap> implements d {
    protected final /* synthetic */ void b(Object obj) {
        obj = (Bitmap) obj;
        com.facebook.common.internal.h.a(obj);
        obj.recycle();
    }

    protected final /* synthetic */ int c(Object obj) {
        obj = (Bitmap) obj;
        com.facebook.common.internal.h.a(obj);
        return obj.getAllocationByteCount();
    }

    protected final /* synthetic */ boolean d(Object obj) {
        obj = (Bitmap) obj;
        com.facebook.common.internal.h.a(obj);
        return !obj.isRecycled() && obj.isMutable();
    }

    public h(d memoryTrimmableRegistry, ad poolParams, ae poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        a();
    }

    protected final int c(int requestSize) {
        return requestSize;
    }

    protected final int d(int bucketedSize) {
        return bucketedSize;
    }

    protected final /* synthetic */ Object b(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Config.RGB_565);
    }

    @Nullable
    protected final /* synthetic */ Object a(f fVar) {
        Bitmap bitmap = (Bitmap) super.a(fVar);
        if (bitmap != null) {
            bitmap.eraseColor(0);
        }
        return bitmap;
    }
}
