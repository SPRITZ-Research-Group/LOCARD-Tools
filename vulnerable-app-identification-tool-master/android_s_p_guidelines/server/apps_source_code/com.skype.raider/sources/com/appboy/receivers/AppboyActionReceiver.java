package com.appboy.receivers;

import a.a.bf;
import a.a.ft;
import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.appboy.b;
import com.appboy.f.c;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.f;
import java.util.List;

public class AppboyActionReceiver extends BroadcastReceiver {
    private static final String a = c.a(AppboyActionReceiver.class);

    @VisibleForTesting
    static class a implements Runnable {
        private final String a;
        private final Context b;
        private final PendingResult c;
        private final Intent d;

        a(Context context, @NonNull Intent intent, PendingResult pendingResult) {
            this.b = context;
            this.d = intent;
            this.a = intent.getAction();
            this.c = pendingResult;
        }

        @VisibleForTesting
        private static boolean a(Context context, LocationResult locationResult) {
            try {
                Location a = locationResult.a();
                b.a(context, new bf(a.getLatitude(), a.getLongitude(), Double.valueOf(a.getAltitude()), Double.valueOf((double) a.getAccuracy())));
                return true;
            } catch (Throwable e) {
                c.d(AppboyActionReceiver.a, "Exception while processing location result", e);
                return false;
            }
        }

        @VisibleForTesting
        private static boolean a(Context context, f fVar) {
            if (fVar.a()) {
                c.g(AppboyActionReceiver.a, "AppboyLocation Services error: " + fVar.b());
                return false;
            }
            int c = fVar.c();
            List<com.google.android.gms.location.c> d = fVar.d();
            if (1 == c) {
                for (com.google.android.gms.location.c a : d) {
                    b.a(context, a.a(), ft.ENTER);
                }
                return true;
            } else if (2 == c) {
                for (com.google.android.gms.location.c a2 : d) {
                    b.a(context, a2.a(), ft.EXIT);
                }
                return true;
            } else {
                c.f(AppboyActionReceiver.a, "Unsupported transition type received: " + c);
                return false;
            }
        }

        public final void run() {
            try {
                if (this.a == null) {
                    AppboyActionReceiver.a;
                } else {
                    AppboyActionReceiver.a;
                    new StringBuilder("Received intent with action ").append(this.a);
                    if (this.a.equals("com.appboy.action.receiver.DATA_SYNC")) {
                        c.c(AppboyActionReceiver.a, "Requesting immediate data flush from AppboyActionReceiver.");
                        com.appboy.a.a(this.b).e();
                    } else if (this.a.equals("com.appboy.action.receiver.APPBOY_GEOFENCE_LOCATION_UPDATE")) {
                        if (LocationResult.a(this.d)) {
                            c.b(AppboyActionReceiver.a, "AppboyActionReceiver received intent with location result: " + this.a);
                            a(this.b, LocationResult.b(this.d));
                        } else {
                            c.f(AppboyActionReceiver.a, "AppboyActionReceiver received intent without location result: " + this.a);
                        }
                    } else if (this.a.equals("com.appboy.action.receiver.APPBOY_GEOFENCE_UPDATE")) {
                        c.b(AppboyActionReceiver.a, "AppboyActionReceiver received intent with geofence transition: " + this.a);
                        a(this.b, f.a(this.d));
                    } else {
                        c.f(AppboyActionReceiver.a, "Unknown intent received in AppboyActionReceiver with action: " + this.a);
                    }
                }
            } catch (Throwable e) {
                c.d(AppboyActionReceiver.a, "Caught exception while performing the AppboyActionReceiver work. Action: " + this.a + " Intent: " + this.d, e);
            }
            this.c.finish();
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            c.f(a, "AppboyActionReceiver received null intent. Doing nothing.");
        } else {
            new Thread(new a(context.getApplicationContext(), intent, goAsync())).start();
        }
    }
}
