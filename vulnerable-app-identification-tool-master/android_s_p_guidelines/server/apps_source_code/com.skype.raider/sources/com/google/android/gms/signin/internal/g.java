package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.e.a;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.signin.b;
import com.google.android.gms.signin.c;

public final class g extends l<e> implements b {
    private final boolean e;
    private final com.google.android.gms.common.internal.g f;
    private final Bundle g;
    private Integer h;

    public g(Context context, Looper looper, com.google.android.gms.common.internal.g gVar, a aVar, e.b bVar) {
        c g = gVar.g();
        Integer h = gVar.h();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", gVar.a());
        if (h != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", h.intValue());
        }
        if (g != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", g.a());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", g.b());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", g.c());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", g.d());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", g.e());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", g.f());
            if (g.g() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", g.g().longValue());
            }
            if (g.h() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", g.h().longValue());
            }
        }
        this(context, looper, true, gVar, bundle, aVar, bVar);
    }

    public g(Context context, Looper looper, boolean z, com.google.android.gms.common.internal.g gVar, Bundle bundle, a aVar, e.b bVar) {
        super(context, looper, 44, gVar, aVar, bVar);
        this.e = z;
        this.f = gVar;
        this.g = bundle;
        this.h = gVar.h();
    }

    public final void a(d dVar) {
        ab.a((Object) dVar, (Object) "Expecting a valid ISignInCallbacks");
        try {
            Account b = this.f.b();
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(b.name)) {
                googleSignInAccount = com.google.android.gms.auth.api.signin.internal.c.a(k()).a();
            }
            ((e) q()).a(new SignInRequest(new ResolveAccountRequest(b, this.h.intValue(), googleSignInAccount)), dVar);
        } catch (RemoteException e) {
            try {
                dVar.a(new SignInResponse());
            } catch (RemoteException e2) {
            }
        }
    }

    public final boolean d() {
        return this.e;
    }

    public final int f() {
        return 12451000;
    }

    protected final String h() {
        return "com.google.android.gms.signin.service.START";
    }

    protected final String i() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    protected final Bundle n() {
        if (!k().getPackageName().equals(this.f.e())) {
            this.g.putString("com.google.android.gms.signin.internal.realClientPackageName", this.f.e());
        }
        return this.g;
    }

    public final void v_() {
        a(new g(this));
    }
}
