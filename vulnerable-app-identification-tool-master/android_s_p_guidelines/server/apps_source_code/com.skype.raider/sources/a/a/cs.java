package a.a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.appboy.f.c;
import com.google.android.gms.common.GoogleApiAvailability;

public final class cs {
    private static final String a = c.a(cs.class);

    public static boolean a(Context context) {
        try {
            Class.forName("com.google.android.gms.common.GoogleApiAvailability");
            int a = GoogleApiAvailability.a().a(context);
            if (a == 0) {
                c.b(a, "Google Play Services is available.");
                return true;
            }
            c.d(a, "Google Play Services is unavailable. Connection result: " + a);
            return false;
        } catch (Throwable e) {
            c.b(a, "Google Play Services Availability API not found. Google Play Services not enabled.", e);
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        } catch (Exception e2) {
            c.g(a, "Unexpected exception while checking for com.google.android.gsf");
            return false;
        }
    }
}
