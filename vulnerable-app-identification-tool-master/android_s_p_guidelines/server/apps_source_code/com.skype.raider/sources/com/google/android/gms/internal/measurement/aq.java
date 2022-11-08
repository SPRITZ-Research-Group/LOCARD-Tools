package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.a.a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.e;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

public final class aq extends ct {
    private String a;
    private String b;
    private int c;
    private String d;
    private String e;
    private long f;
    private long g;
    private int h;
    private String i;

    aq(bx bxVar) {
        super(bxVar);
    }

    @WorkerThread
    private final String A() {
        String str = null;
        c();
        if (s().h(this.a) && !this.q.y()) {
            return str;
        }
        try {
            return FirebaseInstanceId.getInstance().getId();
        } catch (IllegalStateException e) {
            q().y().a("Failed to retrieve Firebase Instance Id");
            return str;
        }
    }

    @WorkerThread
    final zzdz a(String str) {
        c();
        String w = w();
        String x = x();
        G();
        String str2 = this.b;
        long y = (long) y();
        G();
        String str3 = this.d;
        G();
        c();
        if (this.f == 0) {
            this.f = this.q.n().b(k(), k().getPackageName());
        }
        long j = this.f;
        boolean y2 = this.q.y();
        boolean z = !r().p;
        String A = A();
        G();
        long z2 = this.q.z();
        int z3 = z();
        Boolean b = s().b("google_analytics_adid_collection_enabled");
        boolean z4 = b == null || b.booleanValue();
        boolean booleanValue = Boolean.valueOf(z4).booleanValue();
        b = s().b("google_analytics_ssaid_collection_enabled");
        z4 = b == null || b.booleanValue();
        return new zzdz(w, x, str2, y, str3, 12451, j, str, y2, z, A, 0, z2, z3, booleanValue, Boolean.valueOf(z4).booleanValue(), r().z());
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
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
        return true;
    }

    protected final void t_() {
        int i = 1;
        String str = "unknown";
        String str2 = "Unknown";
        int i2 = Integer.MIN_VALUE;
        String str3 = "Unknown";
        String packageName = k().getPackageName();
        PackageManager packageManager = k().getPackageManager();
        if (packageManager == null) {
            q().v().a("PackageManager is null, app identity information might be inaccurate. appId", av.a(packageName));
        } else {
            try {
                str = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                q().v().a("Error retrieving app installer package name. appId", av.a(packageName));
            }
            if (str == null) {
                str = "manual_install";
            } else if ("com.android.vending".equals(str)) {
                str = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(k().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    if (!TextUtils.isEmpty(applicationLabel)) {
                        str3 = applicationLabel.toString();
                    }
                    str2 = packageInfo.versionName;
                    i2 = packageInfo.versionCode;
                }
            } catch (NameNotFoundException e2) {
                q().v().a("Error retrieving package info. appId, appName", av.a(packageName), str3);
            }
        }
        this.a = packageName;
        this.d = str;
        this.b = str2;
        this.c = i2;
        this.e = str3;
        this.f = 0;
        Status a = com.google.android.gms.common.api.internal.e.a(k());
        int i3 = (a == null || !a.d()) ? 0 : 1;
        if (i3 == 0) {
            if (a == null) {
                q().v().a("GoogleService failed to initialize (no status)");
            } else {
                q().v().a("GoogleService failed to initialize, status", Integer.valueOf(a.e()), a.b());
            }
        }
        if (i3 != 0) {
            Boolean b = s().b("firebase_analytics_collection_enabled");
            if (s().u()) {
                q().A().a("Collection disabled with firebase_analytics_collection_deactivated=1");
                i3 = 0;
            } else if (b != null && !b.booleanValue()) {
                q().A().a("Collection disabled with firebase_analytics_collection_enabled=0");
                i3 = 0;
            } else if (b == null && com.google.android.gms.common.api.internal.e.b()) {
                q().A().a("Collection disabled with google_app_measurement_enable=0");
                i3 = 0;
            } else {
                q().C().a("Collection enabled");
                i3 = 1;
            }
        } else {
            i3 = 0;
        }
        this.i = "";
        this.g = 0;
        try {
            String a2 = com.google.android.gms.common.api.internal.e.a();
            if (TextUtils.isEmpty(a2)) {
                a2 = "";
            }
            this.i = a2;
            if (i3 != 0) {
                q().C().a("App package, google app id", this.a, this.i);
            }
        } catch (IllegalStateException e3) {
            q().v().a("getGoogleAppId or isMeasurementEnabled failed with exception. appId", av.a(packageName), e3);
        }
        if (VERSION.SDK_INT >= 16) {
            if (!a.a(k())) {
                i = 0;
            }
            this.h = i;
            return;
        }
        this.h = 0;
    }

    @WorkerThread
    final String v() {
        n().w().nextBytes(new byte[16]);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, r0)});
    }

    final String w() {
        G();
        return this.a;
    }

    final String x() {
        G();
        return this.i;
    }

    final int y() {
        G();
        return this.c;
    }

    final int z() {
        G();
        return this.h;
    }
}
