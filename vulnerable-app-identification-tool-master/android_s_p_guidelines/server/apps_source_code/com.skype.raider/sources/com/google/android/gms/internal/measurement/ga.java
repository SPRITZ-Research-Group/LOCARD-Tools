package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.v4.content.PermissionChecker;
import javax.annotation.Nullable;

public abstract class ga<T> {
    private static final Object b = new Object();
    @SuppressLint({"StaticFieldLeak"})
    private static Context c = null;
    private static boolean d = false;
    private static volatile Boolean e = null;
    private static volatile Boolean f = null;
    final String a;
    private final gk g;
    private final String h;
    private final T i;
    private T j;
    private volatile fy k;
    private volatile SharedPreferences l;

    private ga(gk gkVar, String str, T t) {
        this.j = null;
        this.k = null;
        this.l = null;
        if (gkVar.b == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.g = gkVar;
        String valueOf = String.valueOf(gkVar.c);
        String valueOf2 = String.valueOf(str);
        this.h = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        valueOf = String.valueOf(gkVar.d);
        valueOf2 = String.valueOf(str);
        this.a = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        this.i = t;
    }

    /* synthetic */ ga(gk gkVar, String str, Object obj, byte b) {
        this(gkVar, str, obj);
    }

    private static <V> V a(gj<V> gjVar) {
        long clearCallingIdentity;
        try {
            return gjVar.a();
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V a = gjVar.a();
            return a;
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    public static void a(Context context) {
        synchronized (b) {
            if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
            }
            if (c != context) {
                e = null;
            }
            c = context;
        }
        d = false;
    }

    static boolean b(String str) {
        try {
            return d() ? ((Boolean) a(new gd(str))).booleanValue() : false;
        } catch (SecurityException e) {
            return false;
        }
    }

    @Nullable
    private final T c() {
        if (d()) {
            String str;
            try {
                str = (String) a(new gc(this));
                if (str != null) {
                    return a(str);
                }
            } catch (SecurityException e) {
                str = "Unable to read GServices for flag: ";
                String valueOf = String.valueOf(this.a);
                if (valueOf.length() != 0) {
                    str.concat(valueOf);
                } else {
                    valueOf = new String(str);
                }
            }
        }
        return null;
    }

    private static boolean d() {
        if (e == null) {
            if (c == null) {
                return false;
            }
            Context context = c;
            e = Boolean.valueOf(PermissionChecker.a(context, "com.google.android.providers.gsf.permission.READ_GSERVICES", Binder.getCallingPid(), Binder.getCallingUid(), Binder.getCallingPid() == Process.myPid() ? context.getPackageName() : null) == 0);
        }
        return e.booleanValue();
    }

    public final T a() {
        if (c == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        T t;
        String str;
        if (b("gms:phenotype:phenotype_flag:debug_bypass_phenotype")) {
            str = "Bypass reading Phenotype values for flag: ";
            String valueOf = String.valueOf(this.a);
            if (valueOf.length() != 0) {
                str.concat(valueOf);
            } else {
                valueOf = new String(str);
            }
            t = null;
        } else {
            if (this.g.b != null) {
                if (this.k == null) {
                    this.k = fy.a(c.getContentResolver(), this.g.b);
                }
                str = (String) a(new gb(this, this.k));
                if (str != null) {
                    t = a(str);
                }
            }
            t = null;
        }
        if (t != null) {
            return t;
        }
        t = c();
        return t == null ? this.i : t;
    }

    protected abstract T a(String str);

    final /* synthetic */ String b() {
        return fw.a(c.getContentResolver(), this.h);
    }
}
