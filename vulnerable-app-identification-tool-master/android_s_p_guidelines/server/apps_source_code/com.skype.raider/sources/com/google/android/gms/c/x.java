package com.google.android.gms.c;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ab;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class x<TResult> extends g<TResult> {
    private final Object a = new Object();
    private final v<TResult> b = new v();
    @GuardedBy("mLock")
    private boolean c;
    private volatile boolean d;
    @GuardedBy("mLock")
    private TResult e;
    @GuardedBy("mLock")
    private Exception f;

    x() {
    }

    @GuardedBy("mLock")
    private final void g() {
        ab.a(this.c, (Object) "Task is not yet complete");
    }

    @GuardedBy("mLock")
    private final void h() {
        ab.a(!this.c, (Object) "Task is already complete");
    }

    @GuardedBy("mLock")
    private final void i() {
        if (this.d) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void j() {
        synchronized (this.a) {
            if (this.c) {
                this.b.a((g) this);
                return;
            }
        }
    }

    @NonNull
    public final g<TResult> a(@NonNull c<TResult> cVar) {
        return a(i.a, (c) cVar);
    }

    @NonNull
    public final g<TResult> a(@NonNull d dVar) {
        return a(i.a, dVar);
    }

    @NonNull
    public final g<TResult> a(@NonNull e<? super TResult> eVar) {
        return a(i.a, (e) eVar);
    }

    @NonNull
    public final <TContinuationResult> g<TContinuationResult> a(@NonNull Executor executor, @NonNull a<TResult, TContinuationResult> aVar) {
        g xVar = new x();
        this.b.a(new k(executor, aVar, xVar));
        j();
        return xVar;
    }

    @NonNull
    public final g<TResult> a(@NonNull Executor executor, @NonNull b bVar) {
        this.b.a(new m(executor, bVar));
        j();
        return this;
    }

    @NonNull
    public final g<TResult> a(@NonNull Executor executor, @NonNull c<TResult> cVar) {
        this.b.a(new o(executor, cVar));
        j();
        return this;
    }

    @NonNull
    public final g<TResult> a(@NonNull Executor executor, @NonNull d dVar) {
        this.b.a(new q(executor, dVar));
        j();
        return this;
    }

    @NonNull
    public final g<TResult> a(@NonNull Executor executor, @NonNull e<? super TResult> eVar) {
        this.b.a(new s(executor, eVar));
        j();
        return this;
    }

    public final <X extends Throwable> TResult a(@NonNull Class<X> cls) throws Throwable {
        TResult tResult;
        synchronized (this.a) {
            g();
            i();
            if (cls.isInstance(this.f)) {
                throw ((Throwable) cls.cast(this.f));
            } else if (this.f != null) {
                throw new f(this.f);
            } else {
                tResult = this.e;
            }
        }
        return tResult;
    }

    public final void a(@NonNull Exception exception) {
        ab.a((Object) exception, (Object) "Exception must not be null");
        synchronized (this.a) {
            h();
            this.c = true;
            this.f = exception;
        }
        this.b.a((g) this);
    }

    public final void a(TResult tResult) {
        synchronized (this.a) {
            h();
            this.c = true;
            this.e = tResult;
        }
        this.b.a((g) this);
    }

    public final boolean a() {
        boolean z;
        synchronized (this.a) {
            z = this.c;
        }
        return z;
    }

    public final boolean b() {
        boolean z;
        synchronized (this.a) {
            z = this.c && !this.d && this.f == null;
        }
        return z;
    }

    public final boolean b(@NonNull Exception exception) {
        boolean z = true;
        ab.a((Object) exception, (Object) "Exception must not be null");
        synchronized (this.a) {
            if (this.c) {
                z = false;
            } else {
                this.c = true;
                this.f = exception;
                this.b.a((g) this);
            }
        }
        return z;
    }

    public final boolean b(TResult tResult) {
        boolean z = true;
        synchronized (this.a) {
            if (this.c) {
                z = false;
            } else {
                this.c = true;
                this.e = tResult;
                this.b.a((g) this);
            }
        }
        return z;
    }

    public final boolean c() {
        return this.d;
    }

    public final TResult d() {
        TResult tResult;
        synchronized (this.a) {
            g();
            i();
            if (this.f != null) {
                throw new f(this.f);
            }
            tResult = this.e;
        }
        return tResult;
    }

    @Nullable
    public final Exception e() {
        Exception exception;
        synchronized (this.a) {
            exception = this.f;
        }
        return exception;
    }

    public final boolean f() {
        boolean z = true;
        synchronized (this.a) {
            if (this.c) {
                z = false;
            } else {
                this.c = true;
                this.d = true;
                this.b.a((g) this);
            }
        }
        return z;
    }
}
