package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import net.hockeyapp.android.e.b;
import net.hockeyapp.android.e.c;
import net.hockeyapp.android.f.a;
import net.hockeyapp.android.f.j;

public final class l {
    private static b a = null;

    public static void a(Activity activity, String appIdentifier) {
        String str = "https://sdk.hockeyapp.net/";
        String c = j.c(appIdentifier);
        a.a(activity);
        WeakReference weakReference = new WeakReference(activity);
        Activity activity2 = (Activity) weakReference.get();
        Object obj = activity2 != null ? activity2.getFragmentManager().findFragmentByTag(j.FRAGMENT_TAG) != null ? 1 : null : null;
        if (obj == null && !a(weakReference)) {
            if (a == null || a.getStatus() == Status.FINISHED) {
                AsyncTask cVar = new c(weakReference, str, c);
                a = cVar;
                a.a(cVar);
                return;
            }
            a.a(weakReference);
        }
    }

    private static boolean a(WeakReference<? extends Context> weakContext) {
        Context context = (Context) weakContext.get();
        if (context == null) {
            return false;
        }
        try {
            String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            if (TextUtils.isEmpty(installer)) {
                return false;
            }
            boolean result = true;
            if (VERSION.SDK_INT >= 24 && (TextUtils.equals(installer, "com.google.android.packageinstaller") || TextUtils.equals(installer, "com.android.packageinstaller"))) {
                result = false;
            }
            if (TextUtils.equals(installer, "adb")) {
                return false;
            }
            return result;
        } catch (Throwable th) {
            return false;
        }
    }
}
