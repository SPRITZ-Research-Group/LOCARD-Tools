package com.facebook.yoga;

public final class d {
    public static long a(int i, int i2) {
        float f = (float) i2;
        i = Float.floatToRawIntBits((float) i);
        return ((long) Float.floatToRawIntBits(f)) | (((long) i) << 32);
    }
}
