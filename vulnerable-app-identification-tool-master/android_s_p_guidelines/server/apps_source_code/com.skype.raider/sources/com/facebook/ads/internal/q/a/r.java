package com.facebook.ads.internal.q.a;

import java.util.Locale;

public final class r {
    public static String a(double d) {
        return String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(d)});
    }

    public static String a(long j) {
        return a(((double) j) / 1000.0d);
    }
}
