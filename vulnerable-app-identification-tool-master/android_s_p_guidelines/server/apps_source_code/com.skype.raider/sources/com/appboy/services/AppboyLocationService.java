package com.appboy.services;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import com.appboy.a;
import com.appboy.b;
import com.appboy.f.c;

@SuppressLint({"MissingPermission"})
public class AppboyLocationService extends Service {
    private static final String a = c.a(AppboyLocationService.class);
    private LocationListener b;
    private LocationManager c;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            c.d(a, "Null intent received. Initializing Appboy.");
            a.a(getApplicationContext());
        } else {
            String action = intent.getAction();
            if (action == null) {
                a(intent);
            } else if (action.equals(getPackageName() + ".REQUEST_APPBOY_LOCATION_UPDATES")) {
                b(intent);
            } else if (action.contains(getPackageName() + ".REQUEST_REMOVE_APPBOY_LOCATION_UPDATES")) {
                c(intent);
            } else {
                d(intent);
            }
        }
        return 1;
    }

    private void a(Intent intent) {
        c.f(a, "Null intent action received in Braze location service: " + intent.getDataString());
    }

    private void b(Intent intent) {
        c.b(a, "Requesting background location updates: " + intent.getAction());
        if (this.c == null) {
            this.c = (LocationManager) getApplicationContext().getSystemService("location");
        }
        if (this.b == null) {
            this.b = c();
        }
        float floatExtra = intent.getFloatExtra("distance", 50.0f);
        long longExtra = intent.getLongExtra("time", 3600000);
        if (this.b != null) {
            try {
                this.c.requestLocationUpdates("passive", longExtra, floatExtra, this.b);
                c.d(a, "Collecting locations using passive provider with time interval " + (longExtra / 1000) + "s and update distance " + floatExtra + "m.");
                return;
            } catch (Throwable e) {
                c.c(a, "Could not request background location updates. Security exception from insufficient permissions", e);
                return;
            }
        }
        c.f(a, "Could not request background location updates. Braze location listener was null.");
    }

    private void c(Intent intent) {
        c.b(a, "Removing current location updates: " + intent.getAction());
        b();
    }

    private void d(Intent intent) {
        c.f(a, "Unknown intent received: " + intent.getAction());
    }

    private void b() {
        if (this.b != null) {
            try {
                this.c.removeUpdates(this.b);
            } catch (Throwable e) {
                c.c(a, "Could not remove background location updates. Security exception from insufficient permissions", e);
            }
        }
    }

    private LocationListener c() {
        return new LocationListener(this) {
            final /* synthetic */ AppboyLocationService a;

            {
                this.a = r1;
            }

            public void onLocationChanged(Location location) {
                if (location != null) {
                    c.b(AppboyLocationService.a, "Requesting single location update.");
                    Intent intent = new Intent(this.a.getApplicationContext().getPackageName() + ".SINGLE_APPBOY_LOCATION_UPDATE");
                    intent.putExtra("location", location);
                    intent.putExtra("origin", "Appboy location service");
                    try {
                        this.a.c.requestSingleUpdate("passive", PendingIntent.getBroadcast(this.a.getApplicationContext(), 0, intent, 134217728));
                    } catch (Throwable e) {
                        c.c(AppboyLocationService.a, "Could not request single location update. Security exception from insufficient permissions", e);
                    }
                }
            }

            public void onStatusChanged(String provider, int status, Bundle bundle) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
                if (provider != null && provider.equals("passive")) {
                    this.a.b();
                }
            }
        };
    }

    public void onDestroy() {
        super.onDestroy();
        b();
    }

    public static void requestInitialization(Context context) {
        c.b(a, "Location permissions were granted. Requesting initialization of location service and geofence initialization.");
        context.sendBroadcast(new Intent(context.getPackageName() + ".REQUEST_INIT_APPBOY_LOCATION_SERVICE"));
        b.a(context);
    }
}
