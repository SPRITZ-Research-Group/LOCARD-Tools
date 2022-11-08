package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.h;
import javax.annotation.Nullable;

public final class ad {
    public final int a;
    public final int b;
    public final SparseIntArray c;
    public final int d;
    public final int e;
    public boolean f;
    public final int g;

    public ad(int maxSizeSoftCap, int maxSizeHardCap, @Nullable SparseIntArray bucketSizes) {
        this(maxSizeSoftCap, maxSizeHardCap, bucketSizes, 0, Integer.MAX_VALUE, -1);
    }

    public ad(int maxSizeSoftCap, int maxSizeHardCap, @Nullable SparseIntArray bucketSizes, int minBucketSize, int maxBucketSize, int maxNumThreads) {
        boolean z = maxSizeSoftCap >= 0 && maxSizeHardCap >= maxSizeSoftCap;
        h.b(z);
        this.b = maxSizeSoftCap;
        this.a = maxSizeHardCap;
        this.c = bucketSizes;
        this.d = minBucketSize;
        this.e = maxBucketSize;
        this.g = maxNumThreads;
    }
}
