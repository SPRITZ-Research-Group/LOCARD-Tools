package defpackage;

import java.util.concurrent.TimeUnit;

/* renamed from: oyy */
final class oyy implements Runnable {
    final Runnable a;
    final paw b;
    final long c;
    long d;
    long e;
    long f;
    final /* synthetic */ oyx g;

    oyy(oyx oyx, long j, Runnable runnable, long j2, paw paw, long j3) {
        this.g = oyx;
        this.a = runnable;
        this.b = paw;
        this.c = j3;
        this.e = j2;
        this.f = j;
    }

    public final void run() {
        this.a.run();
        if (!this.b.isDisposed()) {
            long j;
            long a = oyx.a(TimeUnit.NANOSECONDS);
            long j2;
            if (oyu.a + a < this.e || a >= (this.e + this.c) + oyu.a) {
                j = this.c + a;
                j2 = this.c;
                long j3 = this.d + 1;
                this.d = j3;
                this.f = j - (j2 * j3);
            } else {
                j = this.f;
                j2 = this.d + 1;
                this.d = j2;
                j += j2 * this.c;
            }
            this.e = a;
            pas.c(this.b, this.g.a(this, j - a, TimeUnit.NANOSECONDS));
        }
    }
}
