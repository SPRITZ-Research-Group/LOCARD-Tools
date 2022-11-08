package jp.naver.line.android.util;

import defpackage.aciu;
import defpackage.udx;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

abstract class ar extends AbstractExecutorService {
    public volatile boolean a = true;
    protected volatile boolean b = true;
    protected int c;
    boolean d = true;
    private volatile dn e;

    protected abstract Runnable a();

    protected abstract void a(bq<?> bqVar) throws RejectedExecutionException;

    ar() {
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        if (isTerminated()) {
            return true;
        }
        j = timeUnit.toMillis(j);
        if (j <= 0) {
            j = 1;
        }
        Thread.sleep(j);
        return isTerminated();
    }

    public final void execute(Runnable runnable) throws RejectedExecutionException, IllegalArgumentException {
        if (runnable != null) {
            bq bqVar;
            if (runnable instanceof bq) {
                bqVar = (bq) runnable;
            } else {
                bqVar = (bq) a(runnable, null, false);
            }
            a(bqVar);
            return;
        }
        throw new IllegalArgumentException("command is null");
    }

    public final boolean isShutdown() {
        return !this.b;
    }

    public final boolean isTerminated() {
        boolean z = false;
        if (this.b) {
            return false;
        }
        synchronized (this) {
            if (this.c == 0) {
                z = true;
            }
        }
        return z;
    }

    public final List<Runnable> shutdownNow() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    protected final void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    protected final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return a(runnable, (Object) t, true);
    }

    protected <T> RunnableFuture<T> a(Runnable runnable, T t, boolean z) {
        return new bq(runnable, t, z);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new bq(callable);
    }

    protected final void a(Runnable runnable) {
        dn dnVar = this.e;
        if (dnVar != null && (runnable instanceof bq)) {
            dnVar.a((bq) runnable);
        }
    }

    protected final void b(Runnable runnable) {
        dn dnVar = this.e;
        if (dnVar != null && (runnable instanceof bq)) {
            dnVar.b((bq) runnable);
        }
    }

    protected static void a(Throwable th, Object obj, boolean z) {
        String th2 = th.toString();
        StringBuilder b = aciu.a().b();
        b.append("LExecutorService ");
        b.append(obj.getClass().getName());
        b.append(' ');
        b.append(th.toString());
        String stringBuilder = b.toString();
        aciu.a().a(b);
        if (z) {
            udx.e(th, th2, stringBuilder, null);
        } else {
            udx.d(th, th2, stringBuilder, null);
        }
    }

    protected final synchronized void b() {
        this.c--;
    }

    public final synchronized int c() {
        return this.c;
    }

    public final void a(dn dnVar) {
        this.e = dnVar;
    }
}
