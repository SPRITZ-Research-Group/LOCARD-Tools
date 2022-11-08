package a.a;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.appboy.e.a;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.receivers.AppboyActionReceiver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public final class cq {
    private static final String a = c.a(cq.class);

    public static PendingIntent a(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent("com.appboy.action.receiver.APPBOY_GEOFENCE_UPDATE").setClass(context, AppboyActionReceiver.class), 134217728);
    }

    public static PendingIntent b(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent("com.appboy.action.receiver.APPBOY_GEOFENCE_LOCATION_UPDATE").setClass(context, AppboyActionReceiver.class), 134217728);
    }

    public static boolean a(ck ckVar) {
        if (!ckVar.a()) {
            c.d(a, "Geofences implicitly disabled via server configuration.");
            return false;
        } else if (ckVar.b()) {
            c.d(a, "Geofences enabled in server configuration.");
            return true;
        } else {
            c.d(a, "Geofences explicitly disabled via server configuration.");
            return false;
        }
    }

    public static int b(ck ckVar) {
        if (ckVar.g() > 0) {
            return ckVar.g();
        }
        return 20;
    }

    public static List<a> a(SharedPreferences sharedPreferences) {
        List<a> arrayList = new ArrayList();
        Map all = sharedPreferences.getAll();
        if (all == null || all.size() == 0) {
            c.b(a, "Did not find stored geofences.");
            return arrayList;
        }
        Set<String> keySet = all.keySet();
        if (keySet == null || keySet.size() == 0) {
            c.f(a, "Failed to find stored geofence keys.");
            return arrayList;
        }
        for (String str : keySet) {
            String string = sharedPreferences.getString(str, null);
            try {
                if (i.c(string)) {
                    c.f(a, "Received null or blank serialized  geofence string for geofence id " + str + " from shared preferences. Not parsing.");
                } else {
                    arrayList.add(new a(new JSONObject(string)));
                }
            } catch (Throwable e) {
                c.d(a, "Encountered Json exception while parsing stored geofence: " + string, e);
            } catch (Throwable e2) {
                c.d(a, "Encountered unexpected exception while parsing stored geofence: " + string, e2);
            }
        }
        return arrayList;
    }
}
