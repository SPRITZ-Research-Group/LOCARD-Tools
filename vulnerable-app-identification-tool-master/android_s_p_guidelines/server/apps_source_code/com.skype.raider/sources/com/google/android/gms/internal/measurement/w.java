package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.a.c;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import com.google.android.gms.common.util.n;
import java.lang.reflect.InvocationTargetException;

public final class w extends cs {
    @NonNull
    private y a = x.a;
    private Boolean b;

    w(bx bxVar) {
        super(bxVar);
    }

    public static long v() {
        return ((Long) al.E.b()).longValue();
    }

    public static long w() {
        return ((Long) al.e.b()).longValue();
    }

    public static boolean y() {
        return ((Boolean) al.a.b()).booleanValue();
    }

    @WorkerThread
    public final int a(@Size(min = 1) String str) {
        return b(str, al.p);
    }

    @WorkerThread
    public final long a(String str, @NonNull am<Long> amVar) {
        if (str == null) {
            return ((Long) amVar.b()).longValue();
        }
        Object a = this.a.a(str, amVar.a());
        if (TextUtils.isEmpty(a)) {
            return ((Long) amVar.b()).longValue();
        }
        try {
            return ((Long) amVar.a(Long.valueOf(Long.parseLong(a)))).longValue();
        } catch (NumberFormatException e) {
            return ((Long) amVar.b()).longValue();
        }
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    final void a(@NonNull y yVar) {
        this.a = yVar;
    }

    @WorkerThread
    public final int b(String str, @NonNull am<Integer> amVar) {
        if (str == null) {
            return ((Integer) amVar.b()).intValue();
        }
        Object a = this.a.a(str, amVar.a());
        if (TextUtils.isEmpty(a)) {
            return ((Integer) amVar.b()).intValue();
        }
        try {
            return ((Integer) amVar.a(Integer.valueOf(Integer.parseInt(a)))).intValue();
        } catch (NumberFormatException e) {
            return ((Integer) amVar.b()).intValue();
        }
    }

    @Nullable
    @VisibleForTesting
    final Boolean b(@Size(min = 1) String str) {
        ab.a(str);
        try {
            if (k().getPackageManager() == null) {
                q().v().a("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo a = c.a(k()).a(k().getPackageName(), 128);
            if (a == null) {
                q().v().a("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (a.metaData != null) {
                return a.metaData.containsKey(str) ? Boolean.valueOf(a.metaData.getBoolean(str)) : null;
            } else {
                q().v().a("Failed to load metadata: Metadata bundle is null");
                return null;
            }
        } catch (NameNotFoundException e) {
            q().v().a("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final boolean c(String str) {
        return "1".equals(this.a.a(str, "gaia_collection_enabled"));
    }

    @WorkerThread
    public final boolean c(String str, @NonNull am<Boolean> amVar) {
        if (str == null) {
            return ((Boolean) amVar.b()).booleanValue();
        }
        Object a = this.a.a(str, amVar.a());
        return TextUtils.isEmpty(a) ? ((Boolean) amVar.b()).booleanValue() : ((Boolean) amVar.a(Boolean.valueOf(Boolean.parseBoolean(a)))).booleanValue();
    }

    public final /* bridge */ /* synthetic */ n d() {
        return super.d();
    }

    public final boolean d(String str) {
        return "1".equals(this.a.a(str, "measurement.event_sampling_enabled"));
    }

    public final /* bridge */ /* synthetic */ cw e() {
        return super.e();
    }

    @WorkerThread
    final boolean e(String str) {
        return c(str, al.N);
    }

    public final /* bridge */ /* synthetic */ aq f() {
        return super.f();
    }

    @WorkerThread
    final boolean f(String str) {
        return c(str, al.P);
    }

    public final /* bridge */ /* synthetic */ af g() {
        return super.g();
    }

    @WorkerThread
    final boolean g(String str) {
        return c(str, al.Q);
    }

    public final /* bridge */ /* synthetic */ di h() {
        return super.h();
    }

    final boolean h(String str) {
        return c(str, al.R);
    }

    public final /* bridge */ /* synthetic */ df i() {
        return super.i();
    }

    @WorkerThread
    final boolean i(String str) {
        return c(str, al.S);
    }

    public final /* bridge */ /* synthetic */ e j() {
        return super.j();
    }

    @WorkerThread
    final boolean j(String str) {
        return c(str, al.U);
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

    public final boolean t() {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    ApplicationInfo applicationInfo = k().getApplicationInfo();
                    String a = n.a();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = str != null && str.equals(a);
                        this.b = Boolean.valueOf(z);
                    }
                    if (this.b == null) {
                        this.b = Boolean.TRUE;
                        q().v().a("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.b.booleanValue();
    }

    public final boolean u() {
        Boolean b = b("firebase_analytics_collection_deactivated");
        return b != null && b.booleanValue();
    }

    public final String x() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            q().v().a("Could not find SystemProperties class", e);
        } catch (NoSuchMethodException e2) {
            q().v().a("Could not find SystemProperties.get() method", e2);
        } catch (IllegalAccessException e3) {
            q().v().a("Could not access SystemProperties.get()", e3);
        } catch (InvocationTargetException e4) {
            q().v().a("SystemProperties.get() threw an exception", e4);
        }
        return "";
    }
}
