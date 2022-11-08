package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Trace;

public final class d {
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
