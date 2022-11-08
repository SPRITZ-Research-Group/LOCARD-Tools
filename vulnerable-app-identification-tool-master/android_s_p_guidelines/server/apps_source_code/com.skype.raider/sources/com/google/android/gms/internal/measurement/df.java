package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.a;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import java.util.Map;

public final class df extends ct {
    @VisibleForTesting
    protected de a;
    private volatile de b;
    private de c;
    private long d;
    private final Map<Activity, de> e = new a();
    private de f;
    private String g;

    public df(bx bxVar) {
        super(bxVar);
    }

    @VisibleForTesting
    private static String a(String str) {
        String[] split = str.split("\\.");
        String str2 = split.length > 0 ? split[split.length - 1] : "";
        return str2.length() > 100 ? str2.substring(0, 100) : str2;
    }

    @MainThread
    private final void a(Activity activity, de deVar, boolean z) {
        de deVar2 = this.b == null ? this.c : this.b;
        if (deVar.b == null) {
            deVar = new de(deVar.a, a(activity.getClass().getCanonicalName()), deVar.c);
        }
        this.c = this.b;
        this.d = j().b();
        this.b = deVar;
        p().a(new dg(this, z, deVar2, deVar));
    }

    public static void a(de deVar, Bundle bundle, boolean z) {
        if (bundle != null && deVar != null && (!bundle.containsKey("_sc") || z)) {
            if (deVar.a != null) {
                bundle.putString("_sn", deVar.a);
            } else {
                bundle.remove("_sn");
            }
            bundle.putString("_sc", deVar.b);
            bundle.putLong("_si", deVar.c);
        } else if (bundle != null && deVar == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    @MainThread
    private final de d(@NonNull Activity activity) {
        ab.a((Object) activity);
        de deVar = (de) this.e.get(activity);
        if (deVar != null) {
            return deVar;
        }
        deVar = new de(null, a(activity.getClass().getCanonicalName()), n().v());
        this.e.put(activity, deVar);
        return deVar;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    @MainThread
    public final void a(Activity activity) {
        a(activity, d(activity), false);
        cs d = d();
        d.p().a(new q(d, d.j().b()));
    }

    @MainThread
    public final void a(Activity activity, Bundle bundle) {
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.firebase.analytics.screen_service");
            if (bundle2 != null) {
                this.e.put(activity, new de(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
            }
        }
    }

    @MainThread
    public final void a(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        p();
        if (!bs.v()) {
            q().y().a("setCurrentScreen must be called from the main thread");
        } else if (this.b == null) {
            q().y().a("setCurrentScreen cannot be called while no activity active");
        } else if (this.e.get(activity) == null) {
            q().y().a("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = a(activity.getClass().getCanonicalName());
            }
            boolean equals = this.b.b.equals(str2);
            boolean b = ew.b(this.b.a, str);
            if (equals && b) {
                q().z().a("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                q().y().a("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                Object obj;
                ax C = q().C();
                String str3 = "Setting current screen to name, class";
                if (str == null) {
                    obj = "null";
                } else {
                    String obj2 = str;
                }
                C.a(str3, obj2, str2);
                de deVar = new de(str, str2, n().v());
                this.e.put(activity, deVar);
                a(activity, deVar, true);
            } else {
                q().y().a("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    @WorkerThread
    public final void a(String str, de deVar) {
        c();
        synchronized (this) {
            if (this.g == null || this.g.equals(str) || deVar != null) {
                this.g = str;
                this.f = deVar;
            }
        }
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @MainThread
    public final void b(Activity activity) {
        de d = d(activity);
        this.c = this.b;
        this.d = j().b();
        this.b = null;
        p().a(new dh(this, d));
    }

    @MainThread
    public final void b(Activity activity, Bundle bundle) {
        if (bundle != null) {
            de deVar = (de) this.e.get(activity);
            if (deVar != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong("id", deVar.c);
                bundle2.putString("name", deVar.a);
                bundle2.putString("referrer_name", deVar.b);
                bundle.putBundle("com.google.firebase.analytics.screen_service", bundle2);
            }
        }
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    @MainThread
    public final void c(Activity activity) {
        this.e.remove(activity);
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

    @WorkerThread
    public final de v() {
        G();
        c();
        return this.a;
    }

    public final de w() {
        return this.b;
    }

    static /* synthetic */ void a(df dfVar, de deVar) {
        dfVar.d().a(dfVar.j().b());
        if (dfVar.o().a(deVar.d)) {
            deVar.d = false;
        }
    }
}
