package com.facebook.imagepipeline.d;

import android.os.Build.VERSION;
import com.facebook.common.e.b;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.d.h.a;

public final class d implements a {
    public final double a(b trimType) {
        switch (trimType) {
            case OnCloseToDalvikHeapLimit:
                if (VERSION.SDK_INT >= 21) {
                    return b.OnCloseToDalvikHeapLimit.a();
                }
                return 0.0d;
            case OnAppBackgrounded:
            case OnSystemLowMemoryWhileAppInForeground:
            case OnSystemLowMemoryWhileAppInBackground:
                return 1.0d;
            default:
                FLog.wtf("BitmapMemoryCacheTrimStrategy", "unknown trim type: %s", trimType);
                return 0.0d;
        }
    }
}
