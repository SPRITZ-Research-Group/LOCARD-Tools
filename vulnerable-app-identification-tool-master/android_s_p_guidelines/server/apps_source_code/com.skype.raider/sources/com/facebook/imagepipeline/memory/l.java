package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public final class l {
    public static final int a = Runtime.getRuntime().availableProcessors();

    private static SparseIntArray a(int numThreads) {
        SparseIntArray buckets = new SparseIntArray();
        for (int i = 131072; i <= 4194304; i *= 2) {
            buckets.put(i, numThreads);
        }
        return buckets;
    }

    public static ad a() {
        return new ad(4194304, a * 4194304, a(a), 131072, 4194304, a);
    }
}
