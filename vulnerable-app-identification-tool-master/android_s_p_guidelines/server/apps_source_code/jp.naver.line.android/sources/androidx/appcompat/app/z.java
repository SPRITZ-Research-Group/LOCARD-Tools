package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.core.content.d;
import com.google.android.exoplayer.hls.HlsChunkSource;
import java.util.Calendar;

final class z {
    private static z a;
    private final Context b;
    private final LocationManager c;
    private final aa d = new aa();

    static z a(Context context) {
        if (a == null) {
            context = context.getApplicationContext();
            a = new z(context, (LocationManager) context.getSystemService("location"));
        }
        return a;
    }

    private z(Context context, LocationManager locationManager) {
        this.b = context;
        this.c = locationManager;
    }

    final boolean a() {
        aa aaVar = this.d;
        boolean z = false;
        if ((this.d.f > System.currentTimeMillis() ? 1 : null) != null) {
            return aaVar.a;
        }
        Location location = null;
        Location a = d.a(r0.b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? a("network") : null;
        if (d.a(r0.b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location = a("gps");
        }
        if (location == null || a == null ? location == null : location.getTime() <= a.getTime()) {
            a = location;
        }
        if (a != null) {
            aa aaVar2 = r0.d;
            long currentTimeMillis = System.currentTimeMillis();
            y a2 = y.a();
            y yVar = a2;
            yVar.a(currentTimeMillis - 86400000, a.getLatitude(), a.getLongitude());
            long j = a2.a;
            yVar.a(currentTimeMillis, a.getLatitude(), a.getLongitude());
            if (a2.c == 1) {
                z = true;
            }
            long j2 = a2.b;
            long j3 = a2.a;
            long j4 = currentTimeMillis + 86400000;
            double latitude = a.getLatitude();
            double longitude = a.getLongitude();
            aa aaVar3 = aaVar;
            long j5 = j3;
            aa aaVar4 = aaVar2;
            long j6 = j2;
            a2.a(j4, latitude, longitude);
            long j7 = a2.b;
            if (j6 == -1 || j5 == -1) {
                j4 = 43200000 + currentTimeMillis;
            } else {
                j4 = currentTimeMillis > j5 ? 0 + j7 : currentTimeMillis > j6 ? 0 + j5 : 0 + j6;
                j4 += HlsChunkSource.DEFAULT_PLAYLIST_BLACKLIST_MS;
            }
            aa aaVar5 = aaVar4;
            aaVar5.a = z;
            aaVar5.b = j;
            aaVar5.c = j6;
            aaVar5.d = j5;
            aaVar5.e = j7;
            aaVar5.f = j4;
            return aaVar3.a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    private android.location.Location a(java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.appcompat.app.z.a(java.lang.String):android.location.Location. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r1 = this;
        r0 = r1.c;	 Catch:{ Exception -> 0x000f }
        r0 = r0.isProviderEnabled(r2);	 Catch:{ Exception -> 0x000f }
        if (r0 == 0) goto L_0x000f;	 Catch:{ Exception -> 0x000f }
    L_0x0008:
        r0 = r1.c;	 Catch:{ Exception -> 0x000f }
        r2 = r0.getLastKnownLocation(r2);	 Catch:{ Exception -> 0x000f }
        return r2;
    L_0x000f:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.z.a(java.lang.String):android.location.Location");
    }
}
