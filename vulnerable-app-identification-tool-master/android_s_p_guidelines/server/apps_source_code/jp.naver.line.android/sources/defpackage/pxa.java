package defpackage;

import java.util.concurrent.ThreadFactory;

/* renamed from: pxa */
final class pxa {
    final int a;
    final pxb[] b;
    long c;

    pxa(int i, ThreadFactory threadFactory) {
        this.a = i;
        this.b = new pxb[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.b[i2] = new pxb(threadFactory);
        }
    }

    public final pxb a() {
        int i = this.a;
        if (i == 0) {
            return pwy.e;
        }
        pxb[] pxbArr = this.b;
        long j = this.c;
        this.c = 1 + j;
        return pxbArr[(int) (j % ((long) i))];
    }

    public final void b() {
        for (pxb dispose : this.b) {
            dispose.dispose();
        }
    }
}
