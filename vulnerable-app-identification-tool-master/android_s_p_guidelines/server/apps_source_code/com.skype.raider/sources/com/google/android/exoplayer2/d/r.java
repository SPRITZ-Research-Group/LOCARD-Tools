package com.google.android.exoplayer2.d;

import android.os.Trace;

public final class r {
    public static void a(String sectionName) {
        if (s.a >= 18) {
            Trace.beginSection(sectionName);
        }
    }

    public static void a() {
        if (s.a >= 18) {
            Trace.endSection();
        }
    }
}
