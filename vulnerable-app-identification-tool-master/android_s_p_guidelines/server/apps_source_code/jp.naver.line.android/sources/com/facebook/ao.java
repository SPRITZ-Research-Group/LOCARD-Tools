package com.facebook;

import android.os.Handler;

final class ao {
    private final GraphRequest a;
    private final Handler b;
    private final long c = s.i();
    private long d;
    private long e;
    private long f;

    ao(Handler handler, GraphRequest graphRequest) {
        this.a = graphRequest;
        this.b = handler;
    }

    final void a(long j) {
        this.d += j;
        if (this.d >= this.e + this.c || this.d >= this.f) {
            a();
        }
    }

    final void b(long j) {
        this.f += j;
    }

    final void a() {
        if (this.d > this.e) {
            x e = this.a.e();
            if (this.f > 0 && (e instanceof z)) {
                final long j = this.d;
                final long j2 = this.f;
                final z zVar = (z) e;
                if (this.b != null) {
                    this.b.post(new Runnable(this) {
                        final /* synthetic */ ao d;

                        public final void run() {
                        }
                    });
                }
                this.e = this.d;
            }
        }
    }
}
