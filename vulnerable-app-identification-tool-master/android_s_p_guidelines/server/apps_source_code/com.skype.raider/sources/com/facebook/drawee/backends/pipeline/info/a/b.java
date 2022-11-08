package com.facebook.drawee.backends.pipeline.info.a;

import com.facebook.drawee.backends.pipeline.info.f;
import com.facebook.drawee.backends.pipeline.info.g;

public final class b implements com.facebook.drawee.backends.pipeline.info.b {
    private final g a;
    private final f b;

    public b(g imagePerfState, f imagePerfMonitor) {
        this.a = imagePerfState;
        this.b = imagePerfMonitor;
    }

    public final void a(String controllerId, int imageOrigin, boolean successful) {
        this.a.b(imageOrigin);
        this.b.a(this.a, 1);
    }
}
