package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.d;
import com.google.android.exoplayer2.r;
import com.google.android.exoplayer2.r.b;
import com.google.android.exoplayer2.source.e.a;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public final class MergingMediaSource implements e {
    private final e[] a;
    private final ArrayList<e> b;
    private final b c;
    private a d;
    private r e;
    private Object f;
    private int g;
    private IllegalMergeException h;

    public static final class IllegalMergeException extends IOException {
        public final int a;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalMergeException(int reason) {
            this.a = reason;
        }
    }

    static /* synthetic */ void a(MergingMediaSource x0, int x1, r x2, Object x3) {
        if (x0.h == null) {
            IllegalMergeException illegalMergeException;
            int b = x2.b();
            for (int i = 0; i < b; i++) {
                if (x2.a(i, x0.c, 0).e) {
                    illegalMergeException = new IllegalMergeException(0);
                    break;
                }
            }
            if (x0.g == -1) {
                x0.g = x2.c();
            } else if (x2.c() != x0.g) {
                illegalMergeException = new IllegalMergeException(1);
                x0.h = illegalMergeException;
            }
            illegalMergeException = null;
            x0.h = illegalMergeException;
        }
        if (x0.h == null) {
            x0.b.remove(x0.a[x1]);
            if (x1 == 0) {
                x0.e = x2;
                x0.f = x3;
            }
            if (x0.b.isEmpty()) {
                x0.d.a(x0.e, x0.f);
            }
        }
    }

    public final void a(d player, a listener) {
        this.d = listener;
        for (int i = 0; i < this.a.length; i++) {
            final int sourceIndex = i;
            this.a[sourceIndex].a(player, new a(this) {
                final /* synthetic */ MergingMediaSource b;

                public final void a(r timeline, Object manifest) {
                    MergingMediaSource.a(this.b, sourceIndex, timeline, manifest);
                }
            });
        }
    }

    public final void a() throws IOException {
        if (this.h != null) {
            throw this.h;
        }
        for (e a : this.a) {
            a.a();
        }
    }

    public final d a(int index, com.google.android.exoplayer2.upstream.b allocator, long positionUs) {
        d[] periods = new d[this.a.length];
        for (int i = 0; i < periods.length; i++) {
            periods[i] = this.a[i].a(index, allocator, positionUs);
        }
        return new f(periods);
    }

    public final void a(d mediaPeriod) {
        f mergingPeriod = (f) mediaPeriod;
        for (int i = 0; i < this.a.length; i++) {
            this.a[i].a(mergingPeriod.a[i]);
        }
    }

    public final void b() {
        for (e b : this.a) {
            b.b();
        }
    }
}
