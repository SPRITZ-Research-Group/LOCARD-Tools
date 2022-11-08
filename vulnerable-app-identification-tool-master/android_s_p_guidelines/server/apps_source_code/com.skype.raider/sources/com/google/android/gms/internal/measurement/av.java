package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.e;
import com.google.android.gms.measurement.AppMeasurement;

public final class av extends ct {
    private char a = 0;
    private long b = -1;
    @GuardedBy("this")
    private String c;
    private final ax d = new ax(this, 6, false, false);
    private final ax e = new ax(this, 6, true, false);
    private final ax f = new ax(this, 6, false, true);
    private final ax g = new ax(this, 5, false, false);
    private final ax h = new ax(this, 5, true, false);
    private final ax i = new ax(this, 5, false, true);
    private final ax j = new ax(this, 4, false, false);
    private final ax k = new ax(this, 3, false, false);
    private final ax l = new ax(this, 2, false, false);

    av(bx bxVar) {
        super(bxVar);
    }

    private final String J() {
        String str;
        synchronized (this) {
            if (this.c == null) {
                this.c = (String) al.b.b();
            }
            str = this.c;
        }
        return str;
    }

    protected static Object a(String str) {
        return str == null ? null : new ay(str);
    }

    @VisibleForTesting
    private static String a(boolean z, Object obj) {
        if (obj == null) {
            return "";
        }
        Object valueOf = obj instanceof Integer ? Long.valueOf((long) ((Integer) obj).intValue()) : obj;
        if (valueOf instanceof Long) {
            if (!z) {
                return String.valueOf(valueOf);
            }
            if (Math.abs(((Long) valueOf).longValue()) < 100) {
                return String.valueOf(valueOf);
            }
            String str = String.valueOf(valueOf).charAt(0) == '-' ? "-" : "";
            String valueOf2 = String.valueOf(Math.abs(((Long) valueOf).longValue()));
            return new StringBuilder((String.valueOf(str).length() + 43) + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, (double) (valueOf2.length() - 1)))).append("...").append(str).append(Math.round(Math.pow(10.0d, (double) valueOf2.length()) - 1.0d)).toString();
        } else if (valueOf instanceof Boolean) {
            return String.valueOf(valueOf);
        } else {
            if (!(valueOf instanceof Throwable)) {
                return valueOf instanceof ay ? ((ay) valueOf).a : z ? "-" : String.valueOf(valueOf);
            } else {
                Throwable th = (Throwable) valueOf;
                StringBuilder stringBuilder = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String b = b(AppMeasurement.class.getCanonicalName());
                String b2 = b(bx.class.getCanonicalName());
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (!stackTraceElement.isNativeMethod()) {
                        String className = stackTraceElement.getClassName();
                        if (className != null) {
                            className = b(className);
                            if (className.equals(b) || className.equals(b2)) {
                                stringBuilder.append(": ");
                                stringBuilder.append(stackTraceElement);
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                return stringBuilder.toString();
            }
        }
    }

    static String a(boolean z, String str, Object obj, Object obj2, Object obj3) {
        Object str2;
        if (str2 == null) {
            str2 = "";
        }
        Object a = a(z, obj);
        Object a2 = a(z, obj2);
        Object a3 = a(z, obj3);
        StringBuilder stringBuilder = new StringBuilder();
        String str3 = "";
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(str2);
            str3 = ": ";
        }
        if (!TextUtils.isEmpty(a)) {
            stringBuilder.append(str3);
            stringBuilder.append(a);
            str3 = ", ";
        }
        if (!TextUtils.isEmpty(a2)) {
            stringBuilder.append(str3);
            stringBuilder.append(a2);
            str3 = ", ";
        }
        if (!TextUtils.isEmpty(a3)) {
            stringBuilder.append(str3);
            stringBuilder.append(a3);
        }
        return stringBuilder.toString();
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    public final ax A() {
        return this.j;
    }

    public final ax B() {
        return this.k;
    }

    public final ax C() {
        return this.l;
    }

    @VisibleForTesting
    protected final void D() {
        J();
    }

    public final String E() {
        Pair a = r().b.a();
        if (a == null || a == bf.a) {
            return null;
        }
        String valueOf = String.valueOf(a.second);
        String str = (String) a.first;
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    protected final void a(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        int i2 = 0;
        if (!z && a(i)) {
            a(false, str, obj, obj2, obj3);
            J();
        }
        if (!z2 && i >= 5) {
            ab.a((Object) str);
            ct h = this.q.h();
            if (h == null) {
                J();
            } else if (h.F()) {
                if (i >= 0) {
                    i2 = i;
                }
                if (i2 >= 9) {
                    i2 = 8;
                }
                h.a(new aw(this, i2, str, obj, obj2, obj3));
            } else {
                J();
            }
        }
    }

    @VisibleForTesting
    protected final boolean a(int i) {
        return Log.isLoggable(J(), i);
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
        return false;
    }

    public final ax v() {
        return this.d;
    }

    public final ax w() {
        return this.e;
    }

    public final ax x() {
        return this.f;
    }

    public final ax y() {
        return this.g;
    }

    public final ax z() {
        return this.i;
    }
}
