package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.p;

public final class d {
    private static final String a = p.a("PackageManagerHelper");

    public static void a(Context context, Class cls, boolean z) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), z ? 1 : 2, 1);
            p.a();
            String str = "%s %s";
            Object[] objArr = new Object[2];
            objArr[0] = cls.getName();
            objArr[1] = z ? "enabled" : "disabled";
            String.format(str, objArr);
            Throwable[] thArr = new Throwable[0];
        } catch (Exception e) {
            p.a();
            String str2 = "%s could not be %s";
            Object[] objArr2 = new Object[2];
            objArr2[0] = cls.getName();
            objArr2[1] = z ? "enabled" : "disabled";
            String.format(str2, objArr2);
            new Throwable[1][0] = e;
        }
    }
}
