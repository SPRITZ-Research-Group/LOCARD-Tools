package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;

public final class f implements aa {
    public final void a(Exception e) {
        if (e instanceof c) {
            FLog.e("React", "SoftAssert", (Throwable) e);
        } else if (e instanceof RuntimeException) {
            throw ((RuntimeException) e);
        } else {
            throw new RuntimeException(e);
        }
    }
}
