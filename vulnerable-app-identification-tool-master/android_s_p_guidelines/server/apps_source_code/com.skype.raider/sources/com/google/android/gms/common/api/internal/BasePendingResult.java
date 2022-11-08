package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Pair;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.f;
import com.google.android.gms.common.api.g;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.j;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.r;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepName
@KeepForSdk
public abstract class BasePendingResult<R extends i> extends f<R> {
    static final ThreadLocal<Boolean> a = new av();
    private final Object b;
    private final a<R> c;
    private final WeakReference<e> d;
    private final CountDownLatch e;
    private final ArrayList<com.google.android.gms.common.api.f.a> f;
    private j<? super R> g;
    private final AtomicReference<Object> h;
    private R i;
    private Status j;
    private volatile boolean k;
    private boolean l;
    private boolean m;
    @KeepName
    private b mResultGuardian;
    private r n;
    private volatile ai<R> o;
    private boolean p;

    @VisibleForTesting
    public static class a<R extends i> extends Handler {
        public a() {
            this(Looper.getMainLooper());
        }

        public a(Looper looper) {
            super(looper);
        }

        public final void a(j<? super R> jVar, R r) {
            sendMessage(obtainMessage(1, new Pair(jVar, r)));
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    j jVar = (j) pair.first;
                    i iVar = (i) pair.second;
                    try {
                        jVar.a(iVar);
                        return;
                    } catch (RuntimeException e) {
                        BasePendingResult.b(iVar);
                        throw e;
                    }
                case 2:
                    ((BasePendingResult) message.obj).b(Status.d);
                    return;
                default:
                    new StringBuilder(45).append("Don't know how to handle message: ").append(message.what);
                    Exception exception = new Exception();
                    return;
            }
        }
    }

    private final class b {
        private final /* synthetic */ BasePendingResult a;

        private b(BasePendingResult basePendingResult) {
            this.a = basePendingResult;
        }

        /* synthetic */ b(BasePendingResult basePendingResult, byte b) {
            this(basePendingResult);
        }

        protected final void finalize() throws Throwable {
            BasePendingResult.b(this.a.i);
            super.finalize();
        }
    }

    @Deprecated
    BasePendingResult() {
        this.b = new Object();
        this.e = new CountDownLatch(1);
        this.f = new ArrayList();
        this.h = new AtomicReference();
        this.p = false;
        this.c = new a(Looper.getMainLooper());
        this.d = new WeakReference(null);
    }

    @KeepForSdk
    protected BasePendingResult(e eVar) {
        this.b = new Object();
        this.e = new CountDownLatch(1);
        this.f = new ArrayList();
        this.h = new AtomicReference();
        this.p = false;
        this.c = new a(eVar != null ? eVar.a() : Looper.getMainLooper());
        this.d = new WeakReference(eVar);
    }

    public static void b(i iVar) {
        if (!(iVar instanceof g)) {
        }
    }

    @KeepForSdk
    private boolean c() {
        return this.e.getCount() == 0;
    }

    private final R d() {
        R r;
        boolean z = true;
        synchronized (this.b) {
            if (this.k) {
                z = false;
            }
            ab.a(z, (Object) "Result has already been consumed.");
            ab.a(c(), (Object) "Result is not ready.");
            r = this.i;
            this.i = null;
            this.g = null;
            this.k = true;
        }
        this.h.getAndSet(null);
        return r;
    }

    public final R a(TimeUnit timeUnit) {
        boolean z = true;
        if (0 > 0) {
            ab.c("await must not be called on the UI thread when time is greater than zero.");
        }
        ab.a(!this.k, (Object) "Result has already been consumed.");
        if (this.o != null) {
            z = false;
        }
        ab.a(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.e.await(0, timeUnit)) {
                b(Status.d);
            }
        } catch (InterruptedException e) {
            b(Status.b);
        }
        ab.a(c(), (Object) "Result is not ready.");
        return d();
    }

    public final void a(com.google.android.gms.common.api.f.a aVar) {
        ab.b(true, "Callback cannot be null.");
        synchronized (this.b) {
            if (c()) {
                aVar.a(this.j);
            } else {
                this.f.add(aVar);
            }
        }
    }

    @KeepForSdk
    public final void a(R r) {
        boolean z = true;
        synchronized (this.b) {
            if (this.m || this.l) {
                return;
            }
            if (c()) {
            }
            ab.a(!c(), (Object) "Results have already been set");
            if (this.k) {
                z = false;
            }
            ab.a(z, (Object) "Result has already been consumed");
            this.i = r;
            this.n = null;
            this.e.countDown();
            this.j = this.i.a();
            if (this.l) {
                this.g = null;
            } else if (this.g != null) {
                this.c.removeMessages(2);
                this.c.a(this.g, d());
            } else if (this.i instanceof g) {
                this.mResultGuardian = new b();
            }
            ArrayList arrayList = this.f;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((com.google.android.gms.common.api.f.a) obj).a(this.j);
            }
            this.f.clear();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @KeepForSdk
    public final void a(j<? super R> jVar) {
        boolean z = true;
        synchronized (this.b) {
            if (jVar == null) {
                this.g = null;
                return;
            }
            ab.a(!this.k, (Object) "Result has already been consumed.");
            if (this.o != null) {
                z = false;
            }
            ab.a(z, (Object) "Cannot set callbacks if then() has been called.");
            if (a()) {
            } else if (c()) {
                this.c.a(jVar, d());
            } else {
                this.g = jVar;
            }
        }
    }

    public final boolean a() {
        boolean z;
        synchronized (this.b) {
            z = this.l;
        }
        return z;
    }

    public final void b() {
        boolean z = this.p || ((Boolean) a.get()).booleanValue();
        this.p = z;
    }

    public final void b(Status status) {
        synchronized (this.b) {
            if (!c()) {
                a(c(status));
                this.m = true;
            }
        }
    }

    @KeepForSdk
    @NonNull
    protected abstract R c(Status status);
}
