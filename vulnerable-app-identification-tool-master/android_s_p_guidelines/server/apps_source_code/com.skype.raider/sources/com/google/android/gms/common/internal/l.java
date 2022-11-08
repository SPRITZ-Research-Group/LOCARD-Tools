package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.a.f;
import com.google.android.gms.common.api.e.a;
import com.google.android.gms.common.api.e.b;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Set;

public abstract class l<T extends IInterface> extends e<T> implements f {
    private final g e;
    private final Set<Scope> f;
    private final Account g;

    protected l(Context context, Looper looper, int i, g gVar, a aVar, b bVar) {
        this(context, looper, m.a(context), GoogleApiAvailability.a(), i, gVar, (a) ab.a((Object) aVar), (b) ab.a((Object) bVar));
    }

    public int f() {
        return super.f();
    }

    public final Account l() {
        return this.g;
    }

    public final Feature[] m() {
        return new Feature[0];
    }

    protected final Set<Scope> r() {
        return this.f;
    }

    @VisibleForTesting
    private l(Context context, Looper looper, m mVar, GoogleApiAvailability googleApiAvailability, int i, g gVar, a aVar, b bVar) {
        super(context, looper, mVar, googleApiAvailability, i, aVar == null ? null : new am(aVar), bVar == null ? null : new an(bVar), gVar.f());
        this.e = gVar;
        this.g = gVar.a();
        Set<Scope> d = gVar.d();
        for (Scope contains : d) {
            if (!d.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.f = d;
    }
}
