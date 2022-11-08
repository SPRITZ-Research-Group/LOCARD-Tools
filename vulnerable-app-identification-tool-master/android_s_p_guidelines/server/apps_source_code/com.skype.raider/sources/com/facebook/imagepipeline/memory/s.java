package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.e.d;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.memory.a.b;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class s extends a<r> {
    private final int[] g;

    protected abstract r f(int i);

    protected /* synthetic */ Object b(int i) {
        return f(i);
    }

    protected final /* synthetic */ void b(Object obj) {
        obj = (r) obj;
        h.a(obj);
        obj.close();
    }

    protected final /* synthetic */ int c(Object obj) {
        obj = (r) obj;
        h.a(obj);
        return obj.getSize();
    }

    protected final /* synthetic */ boolean d(Object obj) {
        obj = (r) obj;
        h.a(obj);
        return !obj.isClosed();
    }

    s(d memoryTrimmableRegistry, ad poolParams, ae memoryChunkPoolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, memoryChunkPoolStatsTracker);
        SparseIntArray bucketSizes = poolParams.c;
        this.g = new int[bucketSizes.size()];
        for (int i = 0; i < this.g.length; i++) {
            this.g[i] = bucketSizes.keyAt(i);
        }
        a();
    }

    final int b() {
        return this.g[0];
    }

    protected final int d(int bucketedSize) {
        return bucketedSize;
    }

    protected final int c(int requestSize) {
        if (requestSize <= 0) {
            throw new b(Integer.valueOf(requestSize));
        }
        for (int bucketedSize : this.g) {
            if (bucketedSize >= requestSize) {
                return bucketedSize;
            }
        }
        return requestSize;
    }
}
