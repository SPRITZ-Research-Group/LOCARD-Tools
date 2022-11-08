package a.a;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.adjust.sdk.Constants;
import com.appboy.e.a;
import com.appboy.f.c;
import com.google.android.gms.c.d;
import com.google.android.gms.c.e;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.internal.aa;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressLint({"MissingPermission"})
public final class cr {
    private static final String a = c.a(cr.class);

    public static void a(Context context) {
        c.b(a, "Deleting registered geofence cache.");
        Editor edit = context.getSharedPreferences("com.appboy.support.geofences", 0).edit();
        edit.clear();
        edit.apply();
    }

    public static void a(Context context, List<a> list, PendingIntent pendingIntent) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.appboy.support.geofences", 0);
            List<a> a = cq.a(sharedPreferences);
            List arrayList;
            if (list.isEmpty()) {
                arrayList = new ArrayList();
                for (a aVar : a) {
                    arrayList.add(aVar.a());
                    c.b(a, "Obsolete geofence will be un-registered: " + aVar.a());
                }
                if (arrayList.isEmpty()) {
                    c.b(a, "No new geofences to register. No geofences are currently registered.");
                    return;
                }
                LocationServices.a(context).a(arrayList);
                sharedPreferences.edit().clear().apply();
                c.b(a, "No new geofences to register. Cleared " + a.size() + " previously registered geofences.");
                return;
            }
            Editor edit;
            List<a> arrayList2 = new ArrayList();
            Set hashSet = new HashSet();
            for (a aVar2 : list) {
                hashSet.add(aVar2.a());
                Object obj = 1;
                for (a aVar3 : a) {
                    Object obj2;
                    if (aVar2.a().equals(aVar3.a()) && aVar2.a(aVar3)) {
                        obj2 = null;
                    } else {
                        obj2 = obj;
                    }
                    obj = obj2;
                }
                if (obj != null) {
                    c.b(a, "New geofence will be registered: " + aVar2.a());
                    arrayList2.add(aVar2);
                }
            }
            List<String> arrayList3 = new ArrayList();
            for (a aVar22 : a) {
                if (!hashSet.contains(aVar22.a())) {
                    arrayList3.add(aVar22.a());
                    c.b(a, "Obsolete geofence will be un-registered: " + aVar22.a());
                }
            }
            if (arrayList3.isEmpty()) {
                c.b(a, "No obsolete geofences need to be unregistered from Google Play Services.");
            } else {
                edit = sharedPreferences.edit();
                for (String remove : arrayList3) {
                    edit.remove(remove);
                }
                edit.apply();
                c.b(a, "Un-registering " + arrayList3.size() + " obsolete geofences from Google Play Services.");
                LocationServices.a(context).a(arrayList3);
            }
            if (arrayList2.isEmpty()) {
                c.b(a, "No new geofences need to be registered with Google Play Services.");
                return;
            }
            arrayList = new ArrayList();
            edit = sharedPreferences.edit();
            for (a aVar222 : arrayList2) {
                arrayList.add(aVar222.i());
                edit.putString(aVar222.a(), aVar222.j().toString());
            }
            edit.apply();
            c.b(a, "Registering " + arrayList2.size() + " new geofences with Google Play Services.");
            aa.a(LocationServices.c.a(LocationServices.a(context).c(), new GeofencingRequest.a().a(arrayList).a().b(), pendingIntent)).a(new e<Void>() {
                public final /* synthetic */ void a() {
                    c.b(cr.a, "Geofences successfully registered with Google Play Services.");
                }
            }).a(new d() {
                public final void a(@NonNull Exception geofenceError) {
                    if (geofenceError instanceof b) {
                        int a = ((b) geofenceError).a();
                        switch (a) {
                            case 0:
                                c.b(cr.a, "Received Geofence registration success code in failure block with Google Play Services.");
                                return;
                            case Constants.ONE_SECOND /*1000*/:
                                c.f(cr.a, "Geofences not registered with Google Play Services due to GEOFENCE_NOT_AVAILABLE: " + a);
                                return;
                            case 1001:
                                c.f(cr.a, "Geofences not registered with Google Play Services due to GEOFENCE_TOO_MANY_GEOFENCES: " + a);
                                return;
                            case 1002:
                                c.f(cr.a, "Geofences not registered with Google Play Services due to GEOFENCE_TOO_MANY_PENDING_INTENTS: " + a);
                                return;
                            default:
                                c.f(cr.a, "Geofence pending result returned unknown status code: " + a);
                                return;
                        }
                    }
                    c.d(cr.a, "Geofence exception encountered while adding geofences.", geofenceError);
                }
            });
        } catch (Throwable e) {
            c.d(a, "Security exception while adding geofences.", e);
        } catch (Throwable e2) {
            c.d(a, "Exception while adding geofences.", e2);
        }
    }

    public static void a(Context context, PendingIntent pendingIntent) {
        try {
            c.b(a, "Requesting single location update from Google Play Services.");
            LocationRequest a = LocationRequest.a();
            a.b();
            a.c();
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            aa.a(LocationServices.b.a(LocationServices.b(context).c(), a, pendingIntent));
        } catch (Throwable e) {
            c.c(a, "Failed to request location update due to security exception from insufficient permissions.", e);
        } catch (Throwable e2) {
            c.c(a, "Failed to request location update due to exception.", e2);
        }
    }
}
