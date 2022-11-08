package com.facebook.imagepipeline.transcoder;

import com.facebook.imageformat.c;

public final class g implements c {
    private final int a;

    public g(int maxBitmapSize) {
        this.a = maxBitmapSize;
    }

    public final b a(c imageFormat, boolean isResizingEnabled) {
        return new f(isResizingEnabled, this.a);
    }
}
