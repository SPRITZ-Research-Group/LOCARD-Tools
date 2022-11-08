package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.e.a;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.g;
import com.google.android.gms.signin.c;
import com.google.android.gms.signin.internal.SignInResponse;
import com.google.android.gms.signin.internal.b;
import java.util.Set;

public final class ad extends b implements a, e.b {
    private static com.google.android.gms.common.api.a.a<? extends com.google.android.gms.signin.b, c> a = com.google.android.gms.signin.a.c;
    private final Context b;
    private final Handler c;
    private final com.google.android.gms.common.api.a.a<? extends com.google.android.gms.signin.b, c> d;
    private Set<Scope> e;
    private g f;
    private com.google.android.gms.signin.b g;
    private ah h;

    @WorkerThread
    public ad(Context context, Handler handler, @NonNull g gVar) {
        this(context, handler, gVar, a);
    }

    @WorkerThread
    private ad(Context context, Handler handler, @NonNull g gVar, com.google.android.gms.common.api.a.a<? extends com.google.android.gms.signin.b, c> aVar) {
        this.b = context;
        this.c = handler;
        this.f = (g) ab.a((Object) gVar, (Object) "ClientSettings must not be null");
        this.e = gVar.c();
        this.d = aVar;
    }

    @WorkerThread
    public final void a() {
        this.g.a(this);
    }

    @WorkerThread
    public final void a(@NonNull ConnectionResult connectionResult) {
        this.h.b(connectionResult);
    }

    @WorkerThread
    public final void a(ah ahVar) {
        if (this.g != null) {
            this.g.a();
        }
        this.f.a(Integer.valueOf(System.identityHashCode(this)));
        this.g = (com.google.android.gms.signin.b) this.d.a(this.b, this.c.getLooper(), this.f, this.f.g(), this, this);
        this.h = ahVar;
        if (this.e == null || this.e.isEmpty()) {
            this.c.post(new ae(this));
        } else {
            this.g.v_();
        }
    }

    @BinderThread
    public final void a(SignInResponse signInResponse) {
        this.c.post(new ag(this, signInResponse));
    }

    @WorkerThread
    public final void b() {
        this.g.a();
    }

    public final void c() {
        if (this.g != null) {
            this.g.a();
        }
    }

    static /* synthetic */ void a(ad adVar, SignInResponse signInResponse) {
        ConnectionResult a = signInResponse.a();
        if (a.b()) {
            ResolveAccountResponse b = signInResponse.b();
            ConnectionResult b2 = b.b();
            if (b2.b()) {
                adVar.h.a(b.a(), adVar.e);
            } else {
                String valueOf = String.valueOf(b2);
                new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf);
                Exception exception = new Exception();
                adVar.h.b(b2);
            }
        } else {
            adVar.h.b(a);
        }
        adVar.g.a();
    }
}
