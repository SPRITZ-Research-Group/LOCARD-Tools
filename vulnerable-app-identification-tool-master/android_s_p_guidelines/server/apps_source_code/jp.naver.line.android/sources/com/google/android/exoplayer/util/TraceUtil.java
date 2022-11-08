package com.google.android.exoplayer.util;

import android.annotation.TargetApi;
import android.os.Trace;

public final class TraceUtil {
    public static void beginSection(String str) {
    }

    public static void endSection() {
    }

    private TraceUtil() {
    }

    @TargetApi(18)
    private static void beginSectionV18(String str) {
        Trace.beginSection(str);
    }

    @TargetApi(18)
    private static void endSectionV18() {
        Trace.endSection();
    }
}
