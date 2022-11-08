package a.a;

import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.VisibleForTesting;
import com.appboy.e.a;
import com.appboy.f.c;
import com.appboy.f.h;
import com.google.android.gms.common.internal.aa;
import com.google.android.gms.location.LocationServices;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ad {
    private static final String j = c.a(ad.class);
    @VisibleForTesting
    final am a;
    @VisibleForTesting
    final SharedPreferences b;
    @VisibleForTesting
    final List<a> c;
    @VisibleForTesting
    final PendingIntent d;
    @VisibleForTesting
    final PendingIntent e;
    @VisibleForTesting
    ae f;
    @VisibleForTesting
    aw g;
    @VisibleForTesting
    boolean h = false;
    @VisibleForTesting
    int i;
    private final Context k;
    private final com.appboy.a.a l;
    private final ck m;
    private final Object n = new Object();

    public ad(Context context, String str, am amVar, com.appboy.a.a aVar, ck ckVar) {
        boolean z = false;
        this.k = context.getApplicationContext();
        this.a = amVar;
        this.b = context.getSharedPreferences("com.appboy.managers.geofences.storage." + str, 0);
        this.l = aVar;
        this.m = ckVar;
        if (cq.a(this.m) && a(context)) {
            z = true;
        }
        this.h = z;
        this.i = cq.b(this.m);
        this.c = cq.a(this.b);
        this.d = cq.a(context);
        this.e = cq.b(context);
        this.f = new ae(context, str, ckVar);
        b(true);
    }

    public final void a() {
        boolean z;
        c.b(j, "Request to set up geofences received.");
        if (cq.a(this.m) && a(this.k)) {
            z = true;
        } else {
            z = false;
        }
        this.h = z;
        b(false);
        a(true);
    }

    @VisibleForTesting
    private boolean a(Context context) {
        if (!af.a(this.l)) {
            c.b(j, "Location collection not available. Geofences not enabled.");
            return false;
        } else if (!h.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            c.d(j, "Fine grained location permissions not found. Geofences not enabled.");
            return false;
        } else if (cs.a(context)) {
            try {
                if (Class.forName("com.google.android.gms.location.LocationServices", false, ad.class.getClassLoader()) != null) {
                    return true;
                }
                throw new RuntimeException("com.google.android.gms.location.LocationServices not found.");
            } catch (Exception e) {
                c.b(j, "Google Play Services Location API not found. Geofences not enabled.");
                return false;
            }
        } else {
            c.b(j, "Google Play Services not available. Geofences not enabled.");
            return false;
        }
    }

    @VisibleForTesting
    private void b(boolean z) {
        if (this.h) {
            c.b(j, "Location permissions and Google Play Services available. Location collection and Geofencing enabled via config.");
            if (z) {
                synchronized (this.n) {
                    cr.a(this.k, this.c, this.d);
                }
                return;
            }
            return;
        }
        c.b(j, "Appboy geofences not enabled. Geofences not set up.");
    }

    @VisibleForTesting
    private void a(PendingIntent pendingIntent) {
        c.b(j, "Tearing down geofences.");
        if (pendingIntent != null) {
            c.b(j, "Unregistering any Braze geofences from Google Play Services.");
            aa.a(LocationServices.c.a(LocationServices.a(this.k).c(), pendingIntent));
        }
        synchronized (this.n) {
            c.b(j, "Deleting locally stored geofences.");
            Editor edit = this.b.edit();
            edit.clear();
            this.c.clear();
            edit.apply();
        }
    }

    @VisibleForTesting
    private boolean b(String str, ft ftVar) {
        boolean b;
        synchronized (this.n) {
            a a = a(str);
            if (a != null) {
                if (ftVar.equals(ft.ENTER)) {
                    b = a.b();
                } else if (ftVar.equals(ft.EXIT)) {
                    b = a.c();
                }
            }
            b = false;
        }
        return b;
    }

    @VisibleForTesting
    private a a(String str) {
        a aVar;
        synchronized (this.n) {
            for (a aVar2 : this.c) {
                if (aVar2.a().equals(str)) {
                    break;
                }
            }
            aVar2 = null;
        }
        return aVar2;
    }

    public final void a(String str, ft ftVar) {
        if (this.h) {
            try {
                av c = be.c(str, ftVar.toString().toLowerCase(Locale.US));
                if (b(str, ftVar)) {
                    this.a.a(c);
                }
                if (this.f.a(co.a(), a(str), ftVar)) {
                    this.a.b(c);
                    return;
                }
                return;
            } catch (Throwable e) {
                c.c(j, "Failed to record geofence transition.", e);
                return;
            }
        }
        c.f(j, "Appboy geofences not enabled. Not posting geofence report.");
    }

    public final void a(aw awVar) {
        if (!this.h) {
            c.b(j, "Appboy geofences not enabled. Not requesting geofences.");
        } else if (awVar != null) {
            this.g = new bf(awVar.a(), awVar.b(), awVar.c(), awVar.d());
            this.a.a(this.g);
        }
    }

    public final void a(boolean z) {
        if (!this.h) {
            c.b(j, "Appboy geofences not enabled. Not requesting geofences.");
        } else if (this.f.a(z, co.a())) {
            cr.a(this.k, this.e);
        }
    }

    public final void a(ba baVar) {
        if (baVar == null) {
            c.f(j, "Could not configure geofence manager from server config. Server config was null.");
            return;
        }
        boolean m = baVar.m();
        c.b(j, "Geofences enabled server config value " + m + " received.");
        m = m && a(this.k);
        if (m != this.h) {
            this.h = m;
            c.d(j, "Geofences enabled status newly set to " + this.h + " during server config update.");
            if (this.h) {
                b(false);
                a(true);
            } else {
                a(this.d);
            }
        } else {
            c.b(j, "Geofences enabled status " + this.h + " unchanged during server config update.");
        }
        int l = baVar.l();
        if (l >= 0) {
            this.i = l;
            c.d(j, "Max number to register newly set to " + this.i + " via server config.");
        }
        this.f.a(baVar);
    }

    public final void a(List<a> list) {
        if (list == null) {
            c.f(j, "Appboy geofence list was null. Not adding new geofences to local storage.");
        } else if (this.h) {
            a aVar;
            if (this.g != null) {
                for (a aVar2 : list) {
                    double a = this.g.a();
                    double b = this.g.b();
                    double f = aVar2.f();
                    double g = aVar2.g();
                    double toRadians = Math.toRadians(f - a);
                    b = Math.toRadians(g - b);
                    a = Math.toRadians(a);
                    f = Math.toRadians(f);
                    aVar2.a((Math.asin(Math.sqrt(((Math.cos(a) * Math.pow(Math.sin(b / 2.0d), 2.0d)) * Math.cos(f)) + Math.pow(Math.sin(toRadians / 2.0d), 2.0d))) * 2.0d) * 6371000.0d);
                }
                Collections.sort(list);
            }
            synchronized (this.n) {
                c.b(j, "Received new geofence list of size: " + list.size());
                Editor edit = this.b.edit();
                edit.clear();
                this.c.clear();
                int i = 0;
                Iterator it = list.iterator();
                while (true) {
                    int i2 = i;
                    if (!it.hasNext()) {
                        break;
                    }
                    aVar2 = (a) it.next();
                    if (i2 == this.i) {
                        c.b(j, "Reached maximum number of new geofences: " + this.i);
                        break;
                    }
                    this.c.add(aVar2);
                    c.b(j, "Adding new geofence to local storage: " + aVar2.toString());
                    edit.putString(aVar2.a(), aVar2.j().toString());
                    i = i2 + 1;
                }
                edit.apply();
                c.b(j, "Added " + this.c.size() + " new geofences to local storage.");
            }
            this.f.a((List) list);
            b(true);
        } else {
            c.f(j, "Appboy geofences not enabled. Not adding new geofences to local storage.");
        }
    }

    public final void b() {
        if (this.h) {
            c.b(j, "Tearing down all geofences.");
            a(this.d);
            return;
        }
        c.b(j, "Appboy geofences not enabled. Not un-registering geofences.");
    }
}
