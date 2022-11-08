package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.a.d.b;
import com.google.android.gms.common.api.a.f;
import com.google.android.gms.common.api.internal.ad;
import com.google.android.gms.common.api.internal.ap;
import com.google.android.gms.common.api.internal.j;
import com.google.android.gms.common.api.internal.y;
import com.google.android.gms.common.internal.ab;
import java.util.Collection;
import java.util.Collections;

@KeepForSdk
public class d<O extends com.google.android.gms.common.api.a.d> {
    protected final com.google.android.gms.common.api.internal.d a;
    private final Context b;
    private final a<O> c;
    private final O d;
    private final ap<O> e;
    private final Looper f;
    private final int g;
    private final e h;
    private final j i;

    @KeepForSdk
    public static class a {
        @KeepForSdk
        public static final a a = new a().a();
        public final j b;
        public final Looper c;

        @KeepForSdk
        public static class a {
            private j a;
            private Looper b;

            @KeepForSdk
            public final a a(j jVar) {
                ab.a((Object) jVar, (Object) "StatusExceptionMapper must not be null.");
                this.a = jVar;
                return this;
            }

            @KeepForSdk
            public final a a() {
                if (this.a == null) {
                    this.a = new com.google.android.gms.common.api.internal.a();
                }
                if (this.b == null) {
                    this.b = Looper.getMainLooper();
                }
                return new a(this.a, this.b, (byte) 0);
            }
        }

        @KeepForSdk
        private a(j jVar, Looper looper) {
            this.b = jVar;
            this.c = looper;
        }

        /* synthetic */ a(j jVar, Looper looper, byte b) {
            this(jVar, looper);
        }
    }

    @KeepForSdk
    private d(@NonNull Context context, a<O> aVar, a aVar2) {
        ab.a((Object) context, (Object) "Null context is not permitted.");
        ab.a((Object) aVar, (Object) "Api must not be null.");
        ab.a((Object) aVar2, (Object) "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.b = context.getApplicationContext();
        this.c = aVar;
        this.d = null;
        this.f = aVar2.c;
        this.e = ap.a(this.c, this.d);
        this.h = new y(this);
        this.a = com.google.android.gms.common.api.internal.d.a(this.b);
        this.g = this.a.a();
        this.i = aVar2.b;
        this.a.a(this);
    }

    @KeepForSdk
    @Deprecated
    public d(@NonNull Context context, a<O> aVar, j jVar) {
        this(context, (a) aVar, new a().a(jVar).a());
    }

    @KeepForSdk
    private com.google.android.gms.common.internal.g.a e() {
        GoogleSignInAccount a;
        Account a2;
        Collection b;
        com.google.android.gms.common.internal.g.a aVar = new com.google.android.gms.common.internal.g.a();
        if (this.d instanceof b) {
            a = ((b) this.d).a();
            if (a != null) {
                a2 = a.a();
                aVar = aVar.a(a2);
                if (this.d instanceof b) {
                    a = ((b) this.d).a();
                    if (a != null) {
                        b = a.b();
                        return aVar.a(b).b(this.b.getClass().getName()).a(this.b.getPackageName());
                    }
                }
                b = Collections.emptySet();
                return aVar.a(b).b(this.b.getClass().getName()).a(this.b.getPackageName());
            }
        }
        a2 = this.d instanceof com.google.android.gms.common.api.a.d.a ? ((com.google.android.gms.common.api.a.d.a) this.d).a() : null;
        aVar = aVar.a(a2);
        if (this.d instanceof b) {
            a = ((b) this.d).a();
            if (a != null) {
                b = a.b();
                return aVar.a(b).b(this.b.getClass().getName()).a(this.b.getPackageName());
            }
        }
        b = Collections.emptySet();
        return aVar.a(b).b(this.b.getClass().getName()).a(this.b.getPackageName());
    }

    @WorkerThread
    public final f a(Looper looper, com.google.android.gms.common.api.internal.d.a<O> aVar) {
        return this.c.a().a(this.b, looper, e().a(), this.d, aVar, aVar);
    }

    public final ad a(Context context, Handler handler) {
        return new ad(context, handler, e().a());
    }

    public final ap<O> a() {
        return this.e;
    }

    public final int b() {
        return this.g;
    }

    @KeepForSdk
    public final e c() {
        return this.h;
    }

    @KeepForSdk
    public final Looper d() {
        return this.f;
    }

    @KeepForSdk
    public final <A extends a.b, T extends com.google.android.gms.common.api.internal.c.a<? extends i, A>> T a(@NonNull T t) {
        t.b();
        this.a.a(this, (com.google.android.gms.common.api.internal.c.a) t);
        return t;
    }
}
