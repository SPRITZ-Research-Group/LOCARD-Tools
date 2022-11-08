package defpackage;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: pxs */
public final class pxs extends AtomicLong implements ThreadFactory {
    private static final long serialVersionUID = -7789753024099756196L;
    final String a;
    final int b;
    final boolean c;

    public pxs(String str) {
        this(str, 5, false);
    }

    public pxs(String str, int i) {
        this(str, i, false);
    }

    public pxs(String str, int i, boolean z) {
        this.a = str;
        this.b = i;
        this.c = z;
    }

    public final Thread newThread(Runnable runnable) {
        StringBuilder stringBuilder = new StringBuilder(this.a);
        stringBuilder.append('-');
        stringBuilder.append(incrementAndGet());
        String stringBuilder2 = stringBuilder.toString();
        Thread pxt = this.c ? new pxt(runnable, stringBuilder2) : new Thread(runnable, stringBuilder2);
        pxt.setPriority(this.b);
        pxt.setDaemon(true);
        return pxt;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("RxThreadFactory[");
        stringBuilder.append(this.a);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
