package com.google.android.gms.common.util;

import android.os.SystemClock;

public final class f implements e {
    private static final f a = new f();

    private f() {
    }

    public static e d() {
        return a;
    }

    public final long a() {
        return System.currentTimeMillis();
    }

    public final long b() {
        return SystemClock.elapsedRealtime();
    }

    public final long c() {
        return System.nanoTime();
    }
}
