package com.facebook.react.uimanager;

import com.facebook.react.bridge.ai;
import com.facebook.react.modules.core.a.a;

public abstract class d extends a {
    private final ai a;

    protected abstract void a(long j);

    protected d(ai reactContext) {
        this.a = reactContext;
    }

    public final void b(long frameTimeNanos) {
        try {
            a(frameTimeNanos);
        } catch (RuntimeException e) {
            this.a.a(e);
        }
    }
}
