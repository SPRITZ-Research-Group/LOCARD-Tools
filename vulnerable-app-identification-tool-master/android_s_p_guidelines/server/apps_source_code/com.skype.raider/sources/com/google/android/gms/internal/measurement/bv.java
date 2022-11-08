package com.google.android.gms.internal.measurement;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ab;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class bv<V> extends FutureTask<V> implements Comparable<bv> {
    final boolean a;
    private final long b = bs.j.getAndIncrement();
    private final String c;
    private final /* synthetic */ bs d;

    bv(bs bsVar, Runnable runnable, String str) {
        this.d = bsVar;
        super(runnable, null);
        ab.a((Object) str);
        this.c = str;
        this.a = false;
        if (this.b == Long.MAX_VALUE) {
            bsVar.q().v().a("Tasks index overflow");
        }
    }

    bv(bs bsVar, Callable<V> callable, boolean z, String str) {
        this.d = bsVar;
        super(callable);
        ab.a((Object) str);
        this.c = str;
        this.a = z;
        if (this.b == Long.MAX_VALUE) {
            bsVar.q().v().a("Tasks index overflow");
        }
    }

    public final /* synthetic */ int compareTo(@NonNull Object obj) {
        bv bvVar = (bv) obj;
        if (this.a != bvVar.a) {
            return this.a ? -1 : 1;
        } else {
            if (this.b < bvVar.b) {
                return -1;
            }
            if (this.b > bvVar.b) {
                return 1;
            }
            this.d.q().w().a("Two tasks share the same index. index", Long.valueOf(this.b));
            return 0;
        }
    }

    protected final void setException(Throwable th) {
        this.d.q().v().a(this.c, th);
        if (th instanceof bt) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
