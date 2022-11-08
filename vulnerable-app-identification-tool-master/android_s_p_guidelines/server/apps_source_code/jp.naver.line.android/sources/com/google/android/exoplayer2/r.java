package com.google.android.exoplayer2;

import java.util.HashSet;

public final class r {
    private static final HashSet<String> a = new HashSet();
    private static String b = "goog.exo.core";

    public static synchronized String a() {
        String str;
        synchronized (r.class) {
            str = b;
        }
        return str;
    }

    public static synchronized void a(String str) {
        synchronized (r.class) {
            if (a.add(str)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(b);
                stringBuilder.append(", ");
                stringBuilder.append(str);
                b = stringBuilder.toString();
            }
        }
    }
}
