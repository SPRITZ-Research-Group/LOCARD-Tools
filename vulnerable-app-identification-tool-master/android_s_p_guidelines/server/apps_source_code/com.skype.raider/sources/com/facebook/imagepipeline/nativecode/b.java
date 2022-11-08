package com.facebook.imagepipeline.nativecode;

import android.support.annotation.Nullable;
import com.facebook.imagepipeline.transcoder.c;

public final class b implements c {
    private final int a;
    private final boolean b;

    public b(int maxBitmapSize, boolean useDownSamplingRatio) {
        this.a = maxBitmapSize;
        this.b = useDownSamplingRatio;
    }

    @Nullable
    public final com.facebook.imagepipeline.transcoder.b a(com.facebook.imageformat.c imageFormat, boolean isResizingEnabled) {
        if (imageFormat != com.facebook.imageformat.b.a) {
            return null;
        }
        return new NativeJpegTranscoder(isResizingEnabled, this.a, this.b);
    }
}
