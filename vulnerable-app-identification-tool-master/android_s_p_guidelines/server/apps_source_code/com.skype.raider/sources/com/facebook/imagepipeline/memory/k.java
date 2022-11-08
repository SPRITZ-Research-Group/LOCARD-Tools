package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public final class k {
    private static final SparseIntArray a = new SparseIntArray(0);

    public static ad a() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min > 16777216) {
            min = (min / 4) * 3;
        } else {
            min /= 2;
        }
        return new ad(0, min, a);
    }
}
