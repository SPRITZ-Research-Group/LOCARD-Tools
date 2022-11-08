package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat {
    private static final d a;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RestrictBackgroundStatus {
    }

    interface d {
        boolean a(ConnectivityManager connectivityManager);
    }

    static class c implements d {
        c() {
        }

        public boolean a(ConnectivityManager cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) {
                return true;
            }
            switch (info.getType()) {
                case 1:
                case 7:
                case 9:
                    return false;
                default:
                    return true;
            }
        }
    }

    @RequiresApi(16)
    static class a extends c {
        a() {
        }

        public final boolean a(ConnectivityManager cm) {
            return cm.isActiveNetworkMetered();
        }
    }

    @RequiresApi(24)
    static class b extends a {
        b() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            a = new b();
        } else if (VERSION.SDK_INT >= 16) {
            a = new a();
        } else {
            a = new c();
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean a(ConnectivityManager cm) {
        return a.a(cm);
    }
}
