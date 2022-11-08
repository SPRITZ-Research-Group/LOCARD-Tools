package com.appboy.e;

import a.a.ex;
import a.a.ey;
import a.a.ez;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.location.c;
import org.json.JSONException;
import org.json.JSONObject;

public final class a implements e<JSONObject>, Comparable<a> {
    @VisibleForTesting
    final int a;
    @VisibleForTesting
    final boolean b;
    @VisibleForTesting
    final boolean c;
    @VisibleForTesting
    final int d;
    @VisibleForTesting
    double e;
    private final JSONObject f;
    private final String g;
    private final double h;
    private final double i;
    private final int j;
    private final int k;
    private final boolean l;
    private final boolean m;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return (this.e == -1.0d || this.e >= ((a) obj).e) ? 1 : -1;
    }

    public a(JSONObject object) {
        JSONObject jSONObject = object;
        this(jSONObject, object.getString("id"), object.getDouble("latitude"), object.getDouble("longitude"), object.getInt("radius"), object.getInt("cooldown_enter"), object.getInt("cooldown_exit"), object.getBoolean("analytics_enabled_enter"), object.getBoolean("analytics_enabled_exit"), object.optBoolean("enter_events", true), object.optBoolean("exit_events", true), object.optInt("notification_responsiveness", 30000));
    }

    private a(JSONObject object, String geofenceId, double latitude, double longitude, int radius, int cooldownEnterSeconds, int cooldownExitSeconds, boolean analyticsEnabledEnter, boolean analyticsEnabledExit, boolean enterEvents, boolean exitEvents, int notificationResponsiveness) {
        this.e = -1.0d;
        this.f = object;
        this.g = geofenceId;
        this.h = latitude;
        this.i = longitude;
        this.a = radius;
        this.j = cooldownEnterSeconds;
        this.k = cooldownExitSeconds;
        this.m = analyticsEnabledEnter;
        this.l = analyticsEnabledExit;
        this.b = enterEvents;
        this.c = exitEvents;
        this.d = notificationResponsiveness;
    }

    public final String a() {
        return this.g;
    }

    public final boolean b() {
        return this.m;
    }

    public final boolean c() {
        return this.l;
    }

    public final int d() {
        return this.j;
    }

    public final int e() {
        return this.k;
    }

    public final double f() {
        return this.h;
    }

    public final double g() {
        return this.i;
    }

    public final void a(double distance) {
        this.e = distance;
    }

    public final c i() {
        com.google.android.gms.location.c.a aVar = new com.google.android.gms.location.c.a();
        aVar.a(this.g).a(this.h, this.i, (float) this.a).b(this.d).a();
        int i = 0;
        if (this.b) {
            i = 1;
        }
        if (this.c) {
            i |= 2;
        }
        aVar.a(i);
        return aVar.b();
    }

    public final boolean a(a otherGeofence) {
        try {
            ez a = ex.a(otherGeofence.f, this.f, ey.LENIENT);
            if (!a.b()) {
                return true;
            }
            throw new AssertionError(a.c());
        } catch (AssertionError e) {
            return false;
        } catch (JSONException e2) {
            return false;
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("AppboyGeofence{");
        stringBuilder.append("id=").append(this.g);
        stringBuilder.append(", latitude='").append(this.h);
        stringBuilder.append(", longitude=").append(this.i);
        stringBuilder.append(", radiusMeters=").append(this.a);
        stringBuilder.append(", cooldownEnterSeconds=").append(this.j);
        stringBuilder.append(", cooldownExitSeconds=").append(this.k);
        stringBuilder.append(", analyticsEnabledEnter=").append(this.m);
        stringBuilder.append(", analyticsEnabledExit=").append(this.l);
        stringBuilder.append(", enterEvents=").append(this.b);
        stringBuilder.append(", exitEvents=").append(this.c);
        stringBuilder.append(", notificationResponsivenessMs=").append(this.d);
        stringBuilder.append(", distanceFromGeofenceRefresh=").append(this.e);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public final JSONObject j() {
        return this.f;
    }

    public final /* bridge */ /* synthetic */ Object h() {
        return this.f;
    }
}
