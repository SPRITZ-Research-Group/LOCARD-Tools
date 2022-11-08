package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.e;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public final class bs extends ct {
    private static final AtomicLong j = new AtomicLong(Long.MIN_VALUE);
    private bw a;
    private bw b;
    private final PriorityBlockingQueue<bv<?>> c = new PriorityBlockingQueue();
    private final BlockingQueue<bv<?>> d = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler e = new bu(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler f = new bu(this, "Thread death: Uncaught exception on network thread");
    private final Object g = new Object();
    private final Semaphore h = new Semaphore(2);
    private volatile boolean i;

    bs(bx bxVar) {
        super(bxVar);
    }

    private final void a(bv<?> bvVar) {
        synchronized (this.g) {
            this.c.add(bvVar);
            if (this.a == null) {
                this.a = new bw(this, "Measurement Worker", this.c);
                this.a.setUncaughtExceptionHandler(this.e);
                this.a.start();
            } else {
                this.a.a();
            }
        }
    }

    public static boolean v() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public final <V> Future<V> a(Callable<V> callable) throws IllegalStateException {
        G();
        ab.a((Object) callable);
        bv bvVar = new bv(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.a) {
            if (!this.c.isEmpty()) {
                q().y().a("Callable skipped the worker queue.");
            }
            bvVar.run();
        } else {
            a(bvVar);
        }
        return bvVar;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final void a(Runnable runnable) throws IllegalStateException {
        G();
        ab.a((Object) runnable);
        a(new bv(this, runnable, "Task exception on worker thread"));
    }

    public final <V> Future<V> b(Callable<V> callable) throws IllegalStateException {
        G();
        ab.a((Object) callable);
        bv bvVar = new bv(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.a) {
            bvVar.run();
        } else {
            a(bvVar);
        }
        return bvVar;
    }

    public final void b() {
        if (Thread.currentThread() != this.b) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final void b(Runnable runnable) throws IllegalStateException {
        G();
        ab.a((Object) runnable);
        bv bvVar = new bv(this, runnable, "Task exception on network thread");
        synchronized (this.g) {
            this.d.add(bvVar);
            if (this.b == null) {
                this.b = new bw(this, "Measurement Network", this.d);
                this.b.setUncaughtExceptionHandler(this.f);
                this.b.start();
            } else {
                this.b.a();
            }
        }
    }

    public final void c() {
        if (Thread.currentThread() != this.a) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    public final /* bridge */ /* synthetic */ af g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ di h() {
        return super.h();
    }

    public final /* bridge */ /* synthetic */ df i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ e j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ Context k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ar l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ at m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ ew n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ee o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ bs p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ av q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ bf r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ w s() {
        return super.s();
    }

    protected final boolean t() {
        return false;
    }

    public final boolean w() {
        return Thread.currentThread() == this.a;
    }
}
