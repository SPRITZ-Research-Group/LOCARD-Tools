package com.facebook.imagepipeline.memory;

import com.facebook.common.e.d;
import com.facebook.common.f.c;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class n {
    @VisibleForTesting
    final a a;
    private final c<byte[]> b;

    @VisibleForTesting
    static class a extends o {
        public a(d memoryTrimmableRegistry, ad poolParams, ae poolStatsTracker) {
            super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        }

        final f<byte[]> e(int bucketedSize) {
            return new z(bucketedSize, this.b.g);
        }
    }

    public n(d memoryTrimmableRegistry, ad params) {
        h.a(params.g > 0);
        this.a = new a(memoryTrimmableRegistry, params, y.a());
        this.b = new c<byte[]>(this) {
            final /* synthetic */ n a;

            {
                this.a = this$0;
            }

            public final /* bridge */ /* synthetic */ void a(Object obj) {
                this.a.a.a((Object) (byte[]) obj);
            }
        };
    }

    public final com.facebook.common.f.a<byte[]> a(int size) {
        return com.facebook.common.f.a.a(this.a.a(size), this.b);
    }
}
