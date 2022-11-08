package com.facebook.fresco.animation.bitmap.b;

import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.bitmap.a;

public class d implements a {
    private static final Class<?> a = d.class;
    private final int b;

    public d() {
        this(3);
    }

    public d(int framesToPrepare) {
        this.b = framesToPrepare;
    }

    public final void a(b bitmapFramePreparer, a bitmapFrameCache, com.facebook.fresco.animation.a.a animationBackend, int lastDrawnFrameNumber) {
        for (int i = 1; i <= this.b; i++) {
            int nextFrameNumber = (lastDrawnFrameNumber + i) % animationBackend.d();
            if (FLog.isLoggable(2)) {
                FLog.v(a, "Preparing frame %d, last drawn: %d", Integer.valueOf(nextFrameNumber), Integer.valueOf(lastDrawnFrameNumber));
            }
            bitmapFramePreparer.a(bitmapFrameCache, animationBackend, nextFrameNumber);
        }
    }
}
