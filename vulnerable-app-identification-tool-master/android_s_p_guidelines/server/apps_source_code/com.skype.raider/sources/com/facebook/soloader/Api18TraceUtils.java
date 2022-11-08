package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Trace;

@DoNotOptimize
@TargetApi(18)
class Api18TraceUtils {
    Api18TraceUtils() {
    }

    public static void a(String sectionName) {
        Trace.beginSection(sectionName);
    }

    public static void a() {
        Trace.endSection();
    }
}
