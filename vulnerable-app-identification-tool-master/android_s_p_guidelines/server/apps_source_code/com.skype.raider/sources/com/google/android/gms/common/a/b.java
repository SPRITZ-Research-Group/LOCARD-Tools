package com.google.android.gms.common.a;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Process;
import com.google.android.gms.common.util.m;

public final class b {
    private final Context a;

    public b(Context context) {
        this.a = context;
    }

    public final int a(String str) {
        return this.a.checkCallingOrSelfPermission(str);
    }

    public final ApplicationInfo a(String str, int i) throws NameNotFoundException {
        return this.a.getPackageManager().getApplicationInfo(str, i);
    }

    public final boolean a() {
        if (Binder.getCallingUid() == Process.myUid()) {
            return a.a(this.a);
        }
        if (m.d()) {
            String nameForUid = this.a.getPackageManager().getNameForUid(Binder.getCallingUid());
            if (nameForUid != null) {
                return this.a.getPackageManager().isInstantApp(nameForUid);
            }
        }
        return false;
    }

    public final String[] a(int i) {
        return this.a.getPackageManager().getPackagesForUid(i);
    }

    public final PackageInfo b(String str, int i) throws NameNotFoundException {
        return this.a.getPackageManager().getPackageInfo(str, i);
    }

    public final CharSequence b(String str) throws NameNotFoundException {
        return this.a.getPackageManager().getApplicationLabel(this.a.getPackageManager().getApplicationInfo(str, 0));
    }

    @TargetApi(19)
    public final boolean a(int i, String str) {
        if (VERSION.SDK_INT >= 19) {
            try {
                ((AppOpsManager) this.a.getSystemService("appops")).checkPackage(i, str);
                return true;
            } catch (SecurityException e) {
                return false;
            }
        }
        String[] packagesForUid = this.a.getPackageManager().getPackagesForUid(i);
        if (str == null || packagesForUid == null) {
            return false;
        }
        for (Object equals : packagesForUid) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
