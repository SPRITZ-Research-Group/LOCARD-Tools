package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.a.c;
import com.google.android.gms.common.internal.ab;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
public class l {
    private static l a;
    private final Context b;

    private l(Context context) {
        this.b = context.getApplicationContext();
    }

    private static a a(PackageInfo packageInfo, a... aVarArr) {
        int i = 0;
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            return null;
        }
        p pVar = new p(packageInfo.signatures[0].toByteArray());
        while (i < aVarArr.length) {
            if (aVarArr[i].equals(pVar)) {
                return aVarArr[i];
            }
            i++;
        }
        return null;
    }

    public static l a(Context context) {
        ab.a((Object) context);
        synchronized (l.class) {
            if (a == null) {
                f.a(context);
                a = new l(context);
            }
        }
        return a;
    }

    private final u a(String str) {
        try {
            PackageInfo b = c.a(this.b).b(str, 64);
            boolean c = k.c(this.b);
            if (b == null) {
                return u.a("null pkg");
            }
            if (b.signatures.length != 1) {
                return u.a("single cert required");
            }
            a pVar = new p(b.signatures[0].toByteArray());
            String str2 = b.packageName;
            u a = f.a(str2, pVar, c);
            return (!a.a || b.applicationInfo == null || (b.applicationInfo.flags & 2) == 0) ? a : (!c || f.a(str2, pVar, false).a) ? u.a("debuggable release cert app rejected") : a;
        } catch (NameNotFoundException e) {
            String str3 = "no pkg ";
            String valueOf = String.valueOf(str);
            return u.a(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
    }

    public static boolean a(PackageInfo packageInfo, boolean z) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            a a;
            if (z) {
                a = a(packageInfo, r.a);
            } else {
                a = a(packageInfo, r.a[0]);
            }
            if (a != null) {
                return true;
            }
        }
        return false;
    }

    public final boolean a(PackageInfo packageInfo) {
        return packageInfo == null ? false : a(packageInfo, false) ? true : a(packageInfo, true) && k.c(this.b);
    }

    public final boolean a(int i) {
        u uVar;
        String[] a = c.a(this.b).a(i);
        if (a != null && a.length != 0) {
            uVar = null;
            for (String a2 : a) {
                uVar = a(a2);
                if (uVar.a) {
                    break;
                }
            }
        } else {
            uVar = u.a("no pkgs");
        }
        uVar.c();
        return uVar.a;
    }
}
