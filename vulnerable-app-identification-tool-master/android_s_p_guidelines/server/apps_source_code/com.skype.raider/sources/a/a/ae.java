package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.VisibleForTesting;
import com.appboy.e.a;
import com.appboy.f.c;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ae {
    private static final String h = c.a(ae.class);
    @VisibleForTesting
    final SharedPreferences a;
    @VisibleForTesting
    final SharedPreferences b;
    @VisibleForTesting
    Map<String, Long> c = a(this.b);
    @VisibleForTesting
    long d = this.a.getLong("last_request_global", 0);
    @VisibleForTesting
    long e = this.a.getLong("last_report_global", 0);
    @VisibleForTesting
    int f;
    @VisibleForTesting
    int g;

    ae(Context context, String str, ck ckVar) {
        this.a = context.getSharedPreferences("com.appboy.managers.geofences.eligibility.global." + str, 0);
        this.b = context.getSharedPreferences("com.appboy.managers.geofences.eligibility.individual." + str, 0);
        this.f = ckVar.e();
        this.g = ckVar.f();
    }

    final void a(List<a> list) {
        Set hashSet = new HashSet();
        for (a a : list) {
            hashSet.add(a.a());
        }
        Set<String> hashSet2 = new HashSet(this.c.keySet());
        Editor edit = this.b.edit();
        for (String str : hashSet2) {
            if (hashSet.contains(a(str))) {
                c.b(h, "Retaining re-eligibility id " + str + " in re-eligibility list.");
            } else {
                c.b(h, "Deleting outdated re-eligibility id " + str + " from re-eligibility list.");
                this.c.remove(str);
                edit.remove(str);
            }
        }
        edit.apply();
    }

    final boolean a(long j, a aVar, ft ftVar) {
        if (aVar == null) {
            c.f(h, "Geofence passed into getReportEligible() was null.");
            return false;
        }
        String a = aVar.a();
        String str = ftVar.toString().toLowerCase(Locale.US) + "_" + a;
        int d = ftVar.equals(ft.ENTER) ? aVar.d() : aVar.e();
        long j2 = j - this.e;
        if (((long) this.g) > j2) {
            c.b(h, "Geofence report suppressed since only " + j2 + " seconds have passed since the last time geofences were reported globally (minimum interval: " + this.g + "). id:" + a);
            return false;
        }
        if (this.c.containsKey(str)) {
            long longValue = j - ((Long) this.c.get(str)).longValue();
            if (((long) d) > longValue) {
                c.b(h, "Geofence report suppressed since only " + longValue + " seconds have passed since the last time this geofence/transition combination was reported (minimum interval: " + d + "). id:" + a + " transition:" + ftVar);
                return false;
            }
            c.b(h, longValue + " seconds have passed since the last time this geofence/transition combination was reported (minimum interval: " + d + "). id:" + a + " transition:" + ftVar);
        } else {
            c.b(h, "Geofence report eligible since this geofence/transition combination has never reported. id:" + a + " " + ftVar);
        }
        c.b(h, "Geofence report eligible since " + j2 + " seconds have passed since the last time geofences were reported globally (minimum interval: " + this.g + "). id:" + a);
        this.c.put(str, Long.valueOf(j));
        Editor edit = this.b.edit();
        edit.putLong(str, j);
        edit.apply();
        this.e = j;
        edit = this.a.edit();
        edit.putLong("last_report_global", j);
        edit.apply();
        return true;
    }

    protected final boolean a(boolean z, long j) {
        long j2 = j - this.d;
        if (z || ((long) this.f) <= j2) {
            if (z) {
                c.b(h, "Geofence request eligible. Ignoring rate limit for this geofence request. Elapsed time since last request:" + j2);
            } else {
                c.b(h, "Geofence request eligible since " + j2 + " seconds have passed since the last time geofences were requested (minimum interval: " + this.f + ").");
            }
            this.d = j;
            Editor edit = this.a.edit();
            edit.putLong("last_request_global", this.d);
            edit.apply();
            return true;
        }
        c.b(h, "Geofence request suppressed since only " + j2 + " seconds have passed since the last time geofences were requested (minimum interval: " + this.f + ").");
        return false;
    }

    @VisibleForTesting
    private static String a(String str) {
        try {
            return str.split("_", 2)[1];
        } catch (Throwable e) {
            c.b(h, "Exception trying to parse re-eligibility id: " + str, e);
            return null;
        }
    }

    final void a(ba baVar) {
        int j = baVar.j();
        if (j >= 0) {
            this.f = j;
            c.d(h, "Min time since last geofence request reset via server configuration: " + j + "s.");
        }
        j = baVar.k();
        if (j >= 0) {
            this.g = j;
            c.d(h, "Min time since last geofence report reset via server configuration: " + j + "s.");
        }
    }

    @VisibleForTesting
    private static Map<String, Long> a(SharedPreferences sharedPreferences) {
        Map<String, Long> concurrentHashMap = new ConcurrentHashMap();
        Map all = sharedPreferences.getAll();
        if (all == null || all.size() == 0) {
            return concurrentHashMap;
        }
        Set<String> keySet = all.keySet();
        if (keySet.size() == 0) {
            return concurrentHashMap;
        }
        for (String str : keySet) {
            long j = sharedPreferences.getLong(str, 0);
            c.b(h, "Retrieving geofence id " + a(str) + " eligibility information from local storage.");
            concurrentHashMap.put(str, Long.valueOf(j));
        }
        return concurrentHashMap;
    }
}
