package com.facebook.internal;

import com.facebook.s;
import java.util.concurrent.Executor;

public class bt {
    static final /* synthetic */ boolean a = (bt.class.desiredAssertionStatus() ^ 1);
    private final Object b;
    private bv c;
    private final int d;
    private final Executor e;
    private bv f;
    private int g;

    public bt() {
        this(8);
    }

    public bt(int i) {
        this(i, s.d());
    }

    private bt(int i, Executor executor) {
        this.b = new Object();
        this.f = null;
        this.g = 0;
        this.d = i;
        this.e = executor;
    }

    public final bu a(Runnable runnable) {
        bu bvVar = new bv(this, runnable);
        synchronized (this.b) {
            this.c = bvVar.a(this.c, true);
        }
        a(null);
        return bvVar;
    }

    private void a(bv bvVar) {
        synchronized (this.b) {
            if (bvVar != null) {
                this.f = bvVar.a(this.f);
                this.g--;
            }
            if (this.g < this.d) {
                bvVar = this.c;
                if (bvVar != null) {
                    this.c = bvVar.a(this.c);
                    this.f = bvVar.a(this.f, false);
                    this.g++;
                    bvVar.d();
                }
            } else {
                bvVar = null;
            }
        }
        if (bvVar != null) {
            b(bvVar);
        }
    }

    private void b(final bv bvVar) {
        this.e.execute(new Runnable(this) {
            final /* synthetic */ bt b;

            public final void run() {
                try {
                    bvVar.c().run();
                } finally {
                    this.b.a(bvVar);
                }
            }
        });
    }
}
