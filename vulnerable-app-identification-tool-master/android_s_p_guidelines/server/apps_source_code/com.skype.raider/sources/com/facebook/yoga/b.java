package com.facebook.yoga;

public final class b {
    public static long a(float width, float height) {
        return (((long) Float.floatToRawIntBits(width)) << 32) | ((long) Float.floatToRawIntBits(height));
    }
}
