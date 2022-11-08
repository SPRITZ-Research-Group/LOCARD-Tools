package a.a;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import com.appboy.a.a;
import com.appboy.f.c;
import com.appboy.f.h;
import com.appboy.f.i;
import com.appboy.services.AppboyLocationService;

@SuppressLint({"MissingPermission"})
public final class af implements ap {
    private static final String a = c.a(af.class);
    private final Context b;
    private final String c;
    private final LocationManager d;
    private final am e;
    private final boolean f;
    private final boolean g;
    private boolean h = false;
    private long i = 3600000;
    private float j = 50.0f;
    private String k;

    public af(Context context, am amVar, a aVar, ck ckVar) {
        boolean z;
        Object obj;
        this.b = context;
        this.c = context.getPackageName();
        this.e = amVar;
        this.d = (LocationManager) context.getSystemService("location");
        this.f = a(aVar);
        if (ckVar.c()) {
            if (ckVar.d()) {
                c.d(a, "Background location collection enabled via server configuration.");
                z = true;
            } else {
                c.d(a, "Background location collection disabled via server configuration.");
                z = false;
            }
        } else if (aVar.f()) {
            c.d(a, "Background location collection enabled via sdk configuration.");
            z = true;
        } else {
            c.d(a, "Background location collection disabled via sdk configuration.");
            z = false;
        }
        this.h = z;
        Context context2 = this.b;
        if (context2.getPackageManager().queryIntentServices(new Intent().setClass(context2, AppboyLocationService.class), 65536).size() > 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            z = true;
        } else {
            c.d(a, "Appboy location service is not available. Declare <service android:name=\"com.appboy.services.AppboyLocationService\"/> in your AndroidManifest.xml to enable Braze location service.");
            z = false;
        }
        this.g = z;
        if (ckVar.h() >= 0) {
            this.i = ckVar.h();
            c.d(a, "Time interval override set via server configuration for background location collection: " + (this.i / 1000) + "s.");
        } else if (aVar.g() > 300000) {
            this.i = aVar.g();
            c.d(a, "Time interval override set via local configuration for background location collection: " + (this.i / 1000) + "s.");
        } else {
            this.i = 3600000;
            c.d(a, "Time interval override set to default for background location collection: " + (this.i / 1000) + "s.");
        }
        if (ckVar.j() >= 0.0f) {
            this.j = ckVar.j();
            c.d(a, "Distance threshold override set via server configuration for background location collection: " + this.j + "m.");
        } else if (aVar.h() > 50.0f) {
            this.j = aVar.h();
            c.d(a, "Distance threshold override set via local configuration for background location collection: " + this.j + "m.");
        } else {
            this.j = 50.0f;
            c.d(a, "Distance threshold override set to default for background location collection: " + this.j + "m.");
        }
        BroadcastReceiver anonymousClass1 = new BroadcastReceiver(this) {
            final /* synthetic */ af a;

            {
                this.a = r1;
            }

            public final void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    c.g(af.a, "Location broadcast receiver received null intent.");
                    return;
                }
                String action = intent.getAction();
                if (action.endsWith(".SINGLE_APPBOY_LOCATION_UPDATE")) {
                    af.a(this.a, intent);
                } else if (action.endsWith(".REQUEST_INIT_APPBOY_LOCATION_SERVICE")) {
                    this.a.c();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(this.c + ".SINGLE_APPBOY_LOCATION_UPDATE");
        intentFilter.addAction(this.c + ".REQUEST_INIT_APPBOY_LOCATION_SERVICE");
        this.b.registerReceiver(anonymousClass1, intentFilter);
        if (!h.a(this.b, "android.permission.ACCESS_FINE_LOCATION")) {
            e();
        }
    }

    public final void a() {
        e();
    }

    private boolean a(String str) {
        if (this.g) {
            Intent intent = new Intent(str).setClass(this.b, AppboyLocationService.class);
            if (str.equals(this.c + ".REQUEST_APPBOY_LOCATION_UPDATES")) {
                intent.putExtra("distance", this.j);
                intent.putExtra("time", this.i);
            }
            this.b.startService(intent);
            return true;
        }
        c.d(a, "Appboy Location service is not available. Did not send intent to service: " + str);
        return false;
    }

    private void e() {
        if (this.g) {
            c.d(a, "Stopping Braze location service if currently running.");
            this.b.stopService(new Intent().setClass(this.b, AppboyLocationService.class));
            return;
        }
        c.d(a, "Did not attempt to stop service. Braze Location service is not available.");
    }

    private boolean a(aw awVar) {
        try {
            this.e.a(be.a(awVar));
            return true;
        } catch (Throwable e) {
            c.c(a, "Failed to log location recorded event.", e);
            return false;
        }
    }

    public final boolean b() {
        if (!this.f) {
            c.d(a, "Did not request single location update. Location collection is disabled.");
            return false;
        } else if (h.a(this.b, "android.permission.ACCESS_FINE_LOCATION") || h.a(this.b, "android.permission.ACCESS_COARSE_LOCATION")) {
            String str;
            if (h.a(this.b, "android.permission.ACCESS_FINE_LOCATION")) {
                str = "passive";
            } else if (this.k != null) {
                str = this.k;
            } else {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(2);
                criteria.setPowerRequirement(1);
                this.k = this.d.getBestProvider(criteria, true);
                str = this.k;
            }
            if (i.c(str)) {
                c.b(a, "Could not request single location update. Android location provider not found.");
                return false;
            }
            try {
                c.b(a, "Requesting single location update.");
                Intent intent = new Intent(this.c + ".SINGLE_APPBOY_LOCATION_UPDATE");
                intent.putExtra("origin", "Appboy location manager");
                this.d.requestSingleUpdate(str, PendingIntent.getBroadcast(this.b, 0, intent, 134217728));
                return true;
            } catch (Throwable e) {
                c.c(a, "Failed to request single location update due to security exception from insufficient permissions.", e);
                return false;
            } catch (Throwable e2) {
                c.c(a, "Failed to request single location update due to exception.", e2);
                return false;
            }
        } else {
            c.d(a, "Did not request single location update. Fine grained location permissions not found.");
            return false;
        }
    }

    public final void a(ba baVar) {
        if (baVar == null) {
            c.f(a, "Could not reset background location collection interval. Server config was null.");
            return;
        }
        if (baVar.h() >= 0) {
            this.i = baVar.h();
            c.d(a, "Time interval override reset via server configuration for background location collection: " + (this.i / 1000) + "s.");
        }
        if (baVar.i() >= 0.0f) {
            this.j = baVar.i();
            c.d(a, "Distance threshold override reset via server configuration for background location collection: " + this.j + "m.");
        }
        if (!baVar.g()) {
            return;
        }
        if (baVar.f()) {
            this.h = true;
            c.d(a, "Background location collection enabled via server configuration. Requesting location updates.");
            c();
            return;
        }
        this.h = false;
        c.d(a, "Background location collection disabled via server configuration. Stopping any active Braze location service.");
        e();
    }

    public final boolean c() {
        boolean z = false;
        if (!this.f) {
            c.d(a, "Did not request background location updates. Location collection is disabled.");
            return z;
        } else if (!this.h) {
            c.d(a, "Did not request background location updates. Background location collection is disabled.");
            return z;
        } else if (h.a(this.b, "android.permission.ACCESS_FINE_LOCATION")) {
            try {
                a(this.c + ".REQUEST_REMOVE_APPBOY_LOCATION_UPDATES");
                return a(this.c + ".REQUEST_APPBOY_LOCATION_UPDATES");
            } catch (Throwable e) {
                c.c(a, "Could not request location updates due to exception.", e);
                return z;
            }
        } else {
            c.d(a, "Did not request background location updates. Fine grained location permissions not found.");
            return z;
        }
    }

    public static boolean a(a aVar) {
        if (aVar.e()) {
            c.d(a, "Location collection enabled via sdk configuration.");
            return true;
        }
        c.d(a, "Location collection disabled via sdk configuration.");
        return false;
    }

    static /* synthetic */ void a(af afVar, Intent intent) {
        try {
            c.d(a, "Single location update received from " + intent.getStringExtra("origin") + ": " + intent.getAction());
            Location location = (Location) intent.getExtras().get("location");
            if (location != null) {
                afVar.a(new bf(location.getLatitude(), location.getLongitude(), Double.valueOf(location.getAltitude()), Double.valueOf((double) location.getAccuracy())));
            } else {
                c.f(a, "Failed to process location update. Received location was null.");
            }
        } catch (Throwable e) {
            c.d(a, "Failed to process location update.", e);
        }
    }
}
