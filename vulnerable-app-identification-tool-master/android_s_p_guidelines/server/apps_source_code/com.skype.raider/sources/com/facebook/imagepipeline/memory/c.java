package com.facebook.imagepipeline.memory;

import com.facebook.infer.annotation.ThreadSafe;

public class c {
    public static final int a;
    private static final Class<?> b = c.class;
    private static int c = 384;
    private static volatile b d;

    static {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (((long) min) > 16777216) {
            min = (min / 4) * 3;
        } else {
            min /= 2;
        }
        a = min;
    }

    @ThreadSafe
    public static b a() {
        if (d == null) {
            synchronized (c.class) {
                if (d == null) {
                    d = new b(c, a);
                }
            }
        }
        return d;
    }
}
