package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.q;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Set;

public final class a<O extends d> {
    private final a<?, O> a;
    private final i<?, O> b = null;
    private final g<?> c;
    private final j<?> d;
    private final String e;

    public interface d {

        public interface c extends d {
        }

        public interface d extends d {
        }

        public interface e extends c, d {
        }

        public interface a extends c, d {
            Account a();
        }

        public interface b extends c {
            GoogleSignInAccount a();
        }
    }

    @KeepForSdk
    @VisibleForTesting
    public static abstract class e<T extends b, O> {
    }

    @KeepForSdk
    @VisibleForTesting
    public static abstract class a<T extends f, O> extends e<T, O> {
        @KeepForSdk
        public abstract T a(Context context, Looper looper, com.google.android.gms.common.internal.g gVar, O o, com.google.android.gms.common.api.e.a aVar, com.google.android.gms.common.api.e.b bVar);
    }

    @KeepForSdk
    public interface b {
    }

    @KeepForSdk
    public static class c<C extends b> {
    }

    @KeepForSdk
    public interface f extends b {
        void a();

        void a(com.google.android.gms.common.internal.e.d dVar);

        void a(com.google.android.gms.common.internal.e.j jVar);

        void a(q qVar, Set<Scope> set);

        boolean b();

        boolean c();

        boolean d();

        String e();

        int f();

        Feature[] g();
    }

    @KeepForSdk
    @VisibleForTesting
    public static final class g<C extends f> extends c<C> {
    }

    public interface h<T extends IInterface> extends b {
        String a();

        String b();

        T c();
    }

    @VisibleForTesting
    public static abstract class i<T extends h, O> extends e<T, O> {
    }

    @VisibleForTesting
    public static final class j<C extends h> extends c<C> {
    }

    public <C extends f> a(String str, a<C, O> aVar, g<C> gVar) {
        ab.a((Object) aVar, (Object) "Cannot construct an Api with a null ClientBuilder");
        ab.a((Object) gVar, (Object) "Cannot construct an Api with a null ClientKey");
        this.e = str;
        this.a = aVar;
        this.c = gVar;
        this.d = null;
    }

    public final a<?, O> a() {
        ab.a(this.a != null, (Object) "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.a;
    }

    public final c<?> b() {
        if (this.c != null) {
            return this.c;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }

    public final String c() {
        return this.e;
    }
}
