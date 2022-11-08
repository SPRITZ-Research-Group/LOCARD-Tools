package com.facebook.imagepipeline.i;

import android.annotation.TargetApi;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.support.v4.util.j.c;
import com.facebook.imagepipeline.memory.d;
import com.facebook.imageutils.a;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(26)
@ThreadSafe
public final class e extends b {
    public e(d bitmapPool, int maxNumThreads, c decodeBuffers) {
        super(bitmapPool, maxNumThreads, decodeBuffers);
    }

    public final int a(int width, int height, Options options) {
        Object obj;
        if (options.outColorSpace == null || !options.outColorSpace.isWideGamut() || options.inPreferredConfig == Config.RGBA_F16) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            return (width * height) * 8;
        }
        return a.a(width, height, options.inPreferredConfig);
    }
}
