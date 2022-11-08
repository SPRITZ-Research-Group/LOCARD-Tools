package com.facebook.imagepipeline.d;

import com.facebook.common.e.b;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.d.h.a;

public final class s implements a {
    public final double a(b trimType) {
        switch (trimType) {
            case OnCloseToDalvikHeapLimit:
                return 0.0d;
            case OnAppBackgrounded:
            case OnSystemLowMemoryWhileAppInForeground:
            case OnSystemLowMemoryWhileAppInBackground:
                return 1.0d;
            default:
                FLog.wtf("NativeMemoryCacheTrimStrategy", "unknown trim type: %s", trimType);
                return 0.0d;
        }
    }
}
