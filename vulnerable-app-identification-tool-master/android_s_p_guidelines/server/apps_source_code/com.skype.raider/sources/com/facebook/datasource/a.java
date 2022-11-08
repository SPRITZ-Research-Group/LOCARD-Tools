package com.facebook.datasource;

import android.util.Pair;
import com.facebook.common.internal.h;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public abstract class a<T> implements c<T> {
    @GuardedBy("this")
    private a a = a.IN_PROGRESS;
    @GuardedBy("this")
    private boolean b = false;
    @GuardedBy("this")
    @Nullable
    private T c = null;
    @GuardedBy("this")
    private Throwable d = null;
    @GuardedBy("this")
    private float e = 0.0f;
    private final ConcurrentLinkedQueue<Pair<e<T>, Executor>> f = new ConcurrentLinkedQueue();

    private enum a {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    protected a() {
    }

    public final synchronized boolean a() {
        return this.b;
    }

    public final synchronized boolean b() {
        return this.a != a.IN_PROGRESS;
    }

    public synchronized boolean c() {
        return this.c != null;
    }

    @Nullable
    public synchronized T d() {
        return this.c;
    }

    public final synchronized boolean e() {
        return this.a == a.FAILURE;
    }

    @Nullable
    public final synchronized Throwable f() {
        return this.d;
    }

    public final synchronized float g() {
        return this.e;
    }

    public boolean h() {
        boolean z = true;
        synchronized (this) {
            if (this.b) {
                z = false;
            } else {
                this.b = true;
                Object resultToClose = this.c;
                this.c = null;
                if (resultToClose != null) {
                    a(resultToClose);
                }
                if (!b()) {
                    i();
                }
                synchronized (this) {
                    this.f.clear();
                }
            }
        }
        return z;
    }

    protected void a(@Nullable T t) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(e<T> dataSubscriber, Executor executor) {
        h.a((Object) dataSubscriber);
        h.a((Object) executor);
        synchronized (this) {
            if (this.b) {
                return;
            }
            if (this.a == a.IN_PROGRESS) {
                this.f.add(Pair.create(dataSubscriber, executor));
            }
            boolean shouldNotify = c() || b() || j();
        }
    }

    private void i() {
        boolean isFailure = e();
        boolean isCancellation = j();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            Pair<e<T>, Executor> pair = (Pair) it.next();
            a((e) pair.first, (Executor) pair.second, isFailure, isCancellation);
        }
    }

    private void a(final e<T> dataSubscriber, Executor executor, final boolean isFailure, final boolean isCancellation) {
        executor.execute(new Runnable(this) {
            final /* synthetic */ a d;

            public final void run() {
                if (isFailure) {
                    dataSubscriber.b(this.d);
                } else if (isCancellation) {
                    dataSubscriber.c(this.d);
                } else {
                    dataSubscriber.a(this.d);
                }
            }
        });
    }

    private synchronized boolean j() {
        boolean z;
        z = a() && !b();
        return z;
    }

    protected boolean a(@Nullable T value, boolean isLast) {
        boolean result = b(value, isLast);
        if (result) {
            i();
        }
        return result;
    }

    protected boolean a(Throwable throwable) {
        boolean result = b(throwable);
        if (result) {
            i();
        }
        return result;
    }

    protected boolean a(float progress) {
        boolean result = b(progress);
        if (result) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                final e eVar = (e) pair.first;
                ((Executor) pair.second).execute(new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        eVar.d(this.b);
                    }
                });
            }
        }
        return result;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(@Nullable T value, boolean isLast) {
        Object resultToClose = null;
        try {
            synchronized (this) {
                if (this.b || this.a != a.IN_PROGRESS) {
                    resultToClose = value;
                } else {
                    if (isLast) {
                        this.a = a.SUCCESS;
                        this.e = 1.0f;
                    }
                    if (this.c != value) {
                        resultToClose = this.c;
                        this.c = value;
                    }
                }
            }
            return false;
        } finally {
            if (resultToClose != null) {
                a(resultToClose);
            }
        }
    }

    private synchronized boolean b(Throwable throwable) {
        boolean z;
        if (this.b || this.a != a.IN_PROGRESS) {
            z = false;
        } else {
            this.a = a.FAILURE;
            this.d = throwable;
            z = true;
        }
        return z;
    }

    private synchronized boolean b(float progress) {
        boolean z = false;
        synchronized (this) {
            if (!this.b && this.a == a.IN_PROGRESS) {
                if (progress >= this.e) {
                    this.e = progress;
                    z = true;
                }
            }
        }
        return z;
    }
}
