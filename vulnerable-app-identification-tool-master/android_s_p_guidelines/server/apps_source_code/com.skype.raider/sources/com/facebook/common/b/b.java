package com.facebook.common.b;

import com.facebook.common.logging.FLog;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class b extends AbstractExecutorService {
    private static final Class<?> a = b.class;
    private final String b;
    private final Executor c;
    private volatile int d = 1;
    private final BlockingQueue<Runnable> e;
    private final a f;
    private final AtomicInteger g;
    private final AtomicInteger h;

    private class a implements Runnable {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        /* synthetic */ a(b x0, byte b) {
            this(x0);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            int workers;
            try {
                Runnable runnable = (Runnable) this.a.e.poll();
                if (runnable != null) {
                    runnable.run();
                } else {
                    FLog.v(b.a, "%s: Worker has nothing to run", this.a.b);
                }
                workers = this.a.g.decrementAndGet();
                if (this.a.e.isEmpty()) {
                    FLog.v(b.a, "%s: worker finished; %d workers left", this.a.b, Integer.valueOf(workers));
                }
                this.a.b();
            } catch (Throwable th) {
                workers = this.a.g.decrementAndGet();
                if (this.a.e.isEmpty()) {
                    FLog.v(b.a, "%s: worker finished; %d workers left", this.a.b, Integer.valueOf(workers));
                } else {
                    this.a.b();
                }
            }
        }
    }

    public b(String name, Executor executor, BlockingQueue<Runnable> workQueue) {
        this.b = name;
        this.c = executor;
        this.e = workQueue;
        this.f = new a();
        this.g = new AtomicInteger(0);
        this.h = new AtomicInteger(0);
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("runnable parameter is null");
        } else if (this.e.offer(runnable)) {
            int queueSize = this.e.size();
            int maxSize = this.h.get();
            if (queueSize > maxSize && this.h.compareAndSet(maxSize, queueSize)) {
                FLog.v(a, "%s: max pending work in queue = %d", this.b, Integer.valueOf(queueSize));
            }
            b();
        } else {
            throw new RejectedExecutionException(this.b + " queue is full, size=" + this.e.size());
        }
    }

    private void b() {
        int currentCount = this.g.get();
        while (currentCount < this.d) {
            int updatedCount = currentCount + 1;
            if (this.g.compareAndSet(currentCount, updatedCount)) {
                FLog.v(a, "%s: starting worker %d of %d", this.b, Integer.valueOf(updatedCount), Integer.valueOf(this.d));
                this.c.execute(this.f);
                return;
            }
            FLog.v(a, "%s: race in startWorkerIfNeeded; retrying", this.b);
            currentCount = this.g.get();
        }
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }
}
