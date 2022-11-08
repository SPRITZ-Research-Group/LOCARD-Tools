package com.facebook.imagepipeline.i;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory.Options;
import android.support.v4.util.j.c;
import com.facebook.imagepipeline.memory.d;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public final class a extends b {
    public a(d bitmapPool, int maxNumThreads, c decodeBuffers) {
        super(bitmapPool, maxNumThreads, decodeBuffers);
    }

    public final int a(int width, int height, Options options) {
        return com.facebook.imageutils.a.a(width, height, options.inPreferredConfig);
    }
}
