package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public final class l<T> {
    public static Executor a = Executors.newCachedThreadPool();
    private Thread b;
    private final Set<i<T>> c;
    private final Set<i<Throwable>> d;
    private final Handler e;
    private final FutureTask<k<T>> f;
    private volatile k<T> g;

    public l(Callable<k<T>> callable) {
        this(callable, (byte) 0);
    }

    private l(Callable<k<T>> callable, byte b) {
        this.c = new LinkedHashSet(1);
        this.d = new LinkedHashSet(1);
        this.e = new Handler(Looper.getMainLooper());
        this.g = null;
        this.f = new FutureTask(callable);
        a.execute(this.f);
        a();
    }

    public final synchronized l<T> a(i<T> iVar) {
        if (!(this.g == null || this.g.a() == null)) {
            iVar.a(this.g.a());
        }
        this.c.add(iVar);
        a();
        return this;
    }

    public final synchronized l<T> b(i<T> iVar) {
        this.c.remove(iVar);
        b();
        return this;
    }

    public final synchronized l<T> c(i<Throwable> iVar) {
        if (!(this.g == null || this.g.b() == null)) {
            iVar.a(this.g.b());
        }
        this.d.add(iVar);
        a();
        return this;
    }

    public final synchronized l<T> d(i<Throwable> iVar) {
        this.d.remove(iVar);
        b();
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a() {
        if (!c() && this.g == null) {
            this.b = new Thread(this, "LottieTaskObserver") {
                final /* synthetic */ l a;
                private boolean b = false;

                public final void run() {
                    while (!isInterrupted() && !this.b) {
                        if (this.a.f.isDone()) {
                            try {
                                l.a(this.a, (k) this.a.f.get());
                            } catch (Throwable e) {
                                l.a(this.a, new k(e));
                            }
                            this.b = true;
                            this.a.b();
                        }
                    }
                }
            };
            this.b.start();
            c.a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void b() {
        if (!c()) {
            return;
        }
        if (this.c.isEmpty() || this.g != null) {
            this.b.interrupt();
            this.b = null;
            c.a();
        }
    }

    private boolean c() {
        return this.b != null && this.b.isAlive();
    }

    static /* synthetic */ void a(l lVar, Object obj) {
        for (i a : new ArrayList(lVar.c)) {
            a.a(obj);
        }
    }

    static /* synthetic */ void a(l lVar, Throwable th) {
        List<i> arrayList = new ArrayList(lVar.d);
        if (arrayList.isEmpty()) {
            Log.w("LOTTIE", "Lottie encountered an error but no failure listener was added.", th);
            return;
        }
        for (i a : arrayList) {
            a.a(th);
        }
    }

    static /* synthetic */ void a(l lVar, k kVar) {
        if (lVar.g == null) {
            lVar.g = kVar;
            lVar.e.post(new Runnable(lVar) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public final void run() {
                    if (this.a.g != null && !this.a.f.isCancelled()) {
                        k a = this.a.g;
                        if (a.a() != null) {
                            l.a(this.a, a.a());
                        } else {
                            l.a(this.a, a.b());
                        }
                    }
                }
            });
            return;
        }
        throw new IllegalStateException("A task may only be set once.");
    }
}
