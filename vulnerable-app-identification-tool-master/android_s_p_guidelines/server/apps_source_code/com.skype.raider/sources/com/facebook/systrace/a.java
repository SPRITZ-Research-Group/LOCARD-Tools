package com.facebook.systrace;

import android.os.Build.VERSION;
import android.os.Trace;

public final class a {
    public static void a(String sectionName) {
        if (VERSION.SDK_INT >= 18) {
            Trace.beginSection(sectionName);
        }
    }

    public static void a() {
        if (VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}
