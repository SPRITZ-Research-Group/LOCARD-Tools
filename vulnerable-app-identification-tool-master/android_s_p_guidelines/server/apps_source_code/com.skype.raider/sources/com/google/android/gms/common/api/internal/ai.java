package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.f;
import com.google.android.gms.common.api.i;
import com.google.android.gms.common.api.j;
import com.google.android.gms.common.api.k;
import com.google.android.gms.common.api.l;
import com.google.android.gms.common.api.m;
import com.google.android.gms.common.internal.ab;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;

public final class ai<R extends i> extends m<R> implements j<R> {
    private l<? super R, ? extends i> a;
    private ai<? extends i> b;
    private volatile k<? super R> c;
    private f<R> d;
    private final Object e;
    private Status f;
    private final WeakReference<e> g;
    private final ak h;
    private boolean i;

    private final void a(Status status) {
        synchronized (this.e) {
            this.f = status;
            b(this.f);
        }
    }

    @GuardedBy("mSyncToken")
    private final boolean a() {
        return (this.c == null || ((e) this.g.get()) == null) ? false : true;
    }

    private final void b(Status status) {
        synchronized (this.e) {
            if (this.a != null) {
                ab.a((Object) status, (Object) "onFailure must not return null");
                this.b.a(status);
            } else if (a()) {
                k kVar = this.c;
            }
        }
    }

    public final void a(f<?> fVar) {
        synchronized (this.e) {
            this.d = fVar;
            if (!(this.a == null && this.c == null)) {
                e eVar = (e) this.g.get();
                if (!(this.i || this.a == null || eVar == null)) {
                    eVar.b();
                    this.i = true;
                }
                if (this.f != null) {
                    b(this.f);
                } else if (this.d != null) {
                    this.d.a((j) this);
                }
            }
        }
    }

    public final void a(R r) {
        synchronized (this.e) {
            if (!r.a().d()) {
                a(r.a());
            } else if (this.a != null) {
                ab.a().submit(new aj(this, r));
            } else if (a()) {
                k kVar = this.c;
            }
        }
    }
}
