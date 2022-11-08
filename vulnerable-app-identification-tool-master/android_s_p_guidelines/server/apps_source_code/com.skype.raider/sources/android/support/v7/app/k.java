package android.support.v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.PermissionChecker;
import java.util.Calendar;

final class k {
    private static k a;
    private final Context b;
    private final LocationManager c;
    private final a d = new a();

    private static class a {
        boolean a;
        long b;
        long c;
        long d;
        long e;
        long f;

        a() {
        }
    }

    static k a(@NonNull Context context) {
        if (a == null) {
            context = context.getApplicationContext();
            a = new k(context, (LocationManager) context.getSystemService("location"));
        }
        return a;
    }

    @VisibleForTesting
    private k(@NonNull Context context, @NonNull LocationManager locationManager) {
        this.b = context;
        this.c = locationManager;
    }

    final boolean a() {
        Object obj;
        a state = this.d;
        if (this.d == null || this.d.f <= System.currentTimeMillis()) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            return state.a;
        }
        Location location = null;
        Location location2 = null;
        if (PermissionChecker.a(this.b, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            location = a("network");
        }
        if (PermissionChecker.a(this.b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location2 = a("gps");
        }
        if (location2 == null || location == null) {
            if (location2 == null) {
                location2 = location;
            }
        } else if (location2.getTime() <= location.getTime()) {
            location2 = location;
        }
        if (location2 != null) {
            boolean z;
            long j;
            a aVar = this.d;
            long currentTimeMillis = System.currentTimeMillis();
            j a = j.a();
            a.a(currentTimeMillis - 86400000, location2.getLatitude(), location2.getLongitude());
            long j2 = a.a;
            a.a(currentTimeMillis, location2.getLatitude(), location2.getLongitude());
            if (a.c == 1) {
                z = true;
            } else {
                z = false;
            }
            long j3 = a.b;
            long j4 = a.a;
            a.a(86400000 + currentTimeMillis, location2.getLatitude(), location2.getLongitude());
            long j5 = a.b;
            if (j3 == -1 || j4 == -1) {
                j = 43200000 + currentTimeMillis;
            } else {
                if (currentTimeMillis > j4) {
                    j = 0 + j5;
                } else if (currentTimeMillis > j3) {
                    j = 0 + j4;
                } else {
                    j = 0 + j3;
                }
                j += 60000;
            }
            aVar.a = z;
            aVar.b = j2;
            aVar.c = j3;
            aVar.d = j4;
            aVar.e = j5;
            aVar.f = j;
            return state.a;
        }
        int hour = Calendar.getInstance().get(11);
        return hour < 6 || hour >= 22;
    }

    private Location a(String provider) {
        if (this.c != null) {
            try {
                if (this.c.isProviderEnabled(provider)) {
                    return this.c.getLastKnownLocation(provider);
                }
            } catch (Exception e) {
            }
        }
        return null;
    }
}
