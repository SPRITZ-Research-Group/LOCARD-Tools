package defpackage;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* renamed from: pxm */
final class pxm implements Runnable {
    final ozm a;
    private final long b;
    private final ConcurrentLinkedQueue<pxo> c;
    private final ScheduledExecutorService d;
    private final Future<?> e;
    private final ThreadFactory f;

    pxm(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
        Future scheduleWithFixedDelay;
        this.b = timeUnit != null ? timeUnit.toNanos(j) : 0;
        this.c = new ConcurrentLinkedQueue();
        this.a = new ozm();
        this.f = threadFactory;
        ScheduledExecutorService scheduledExecutorService = null;
        if (timeUnit != null) {
            scheduledExecutorService = Executors.newScheduledThreadPool(1, pxl.c);
            scheduleWithFixedDelay = scheduledExecutorService.scheduleWithFixedDelay(this, this.b, this.b, TimeUnit.NANOSECONDS);
        } else {
            scheduleWithFixedDelay = null;
        }
        this.d = scheduledExecutorService;
        this.e = scheduleWithFixedDelay;
    }

    final pxo a() {
        if (this.a.isDisposed()) {
            return pxl.d;
        }
        while (!this.c.isEmpty()) {
            pxo pxo = (pxo) this.c.poll();
            if (pxo != null) {
                return pxo;
            }
        }
        ozn pxo2 = new pxo(this.f);
        this.a.a(pxo2);
        return pxo2;
    }

    final void b() {
        this.a.dispose();
        if (this.e != null) {
            this.e.cancel(true);
        }
        if (this.d != null) {
            this.d.shutdownNow();
        }
    }

    public final void run() {
        if (!this.c.isEmpty()) {
            long nanoTime = System.nanoTime();
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                pxo pxo = (pxo) it.next();
                if (pxo.a() > nanoTime) {
                    return;
                }
                if (this.c.remove(pxo)) {
                    this.a.b(pxo);
                }
            }
        }
    }

    final void a(pxo pxo) {
        pxo.a(System.nanoTime() + this.b);
        this.c.offer(pxo);
    }
}
