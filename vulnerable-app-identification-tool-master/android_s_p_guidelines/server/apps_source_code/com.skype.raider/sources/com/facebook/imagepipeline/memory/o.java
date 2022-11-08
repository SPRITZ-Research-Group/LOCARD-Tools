package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.e.a;
import com.facebook.common.e.d;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.memory.a.b;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class o extends a<byte[]> implements a {
    private final int[] g;

    protected final /* synthetic */ void b(Object obj) {
        h.a((byte[]) obj);
    }

    protected final /* synthetic */ int c(Object obj) {
        obj = (byte[]) obj;
        h.a(obj);
        return obj.length;
    }

    public o(d memoryTrimmableRegistry, ad poolParams, ae poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        SparseIntArray bucketSizes = poolParams.c;
        this.g = new int[bucketSizes.size()];
        for (int i = 0; i < bucketSizes.size(); i++) {
            this.g[i] = bucketSizes.keyAt(i);
        }
        a();
    }

    protected final int d(int bucketedSize) {
        return bucketedSize;
    }

    protected final int c(int requestSize) {
        int intRequestSize = requestSize;
        if (requestSize <= 0) {
            throw new b(Integer.valueOf(requestSize));
        }
        for (int bucketedSize : this.g) {
            if (bucketedSize >= intRequestSize) {
                return bucketedSize;
            }
        }
        return requestSize;
    }
}
