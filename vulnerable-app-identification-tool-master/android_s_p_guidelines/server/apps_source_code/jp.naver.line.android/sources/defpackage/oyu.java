package defpackage;

import java.util.concurrent.TimeUnit;

/* renamed from: oyu */
public abstract class oyu {
    static final long a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    public abstract oyx a();

    public void b() {
    }

    public static long a(TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public ozn a(Runnable runnable) {
        return a(runnable, 0, TimeUnit.NANOSECONDS);
    }

    public ozn a(Runnable runnable, long j, TimeUnit timeUnit) {
        oyx a = a();
        Object oyv = new oyv(pzv.a(runnable), a);
        a.a(oyv, j, timeUnit);
        return oyv;
    }

    public ozn a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        oyx a = a();
        ozn oyw = new oyw(pzv.a(runnable), a);
        ozn a2 = a.a(oyw, j, j2, timeUnit);
        return a2 == pat.INSTANCE ? a2 : oyw;
    }
}
