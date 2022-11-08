package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.f.c;
import com.appboy.f.i;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class es implements ep {
    private static final String a = c.a(es.class);
    private final SharedPreferences b;
    private Map<String, Long> c = a();

    public es(Context context, String str, String str2) {
        this.b = context.getSharedPreferences("com.appboy.storage.triggers.re_eligibility" + i.a(context, str, str2), 0);
    }

    public final void a(List<dc> list) {
        Set hashSet = new HashSet();
        for (dc b : list) {
            hashSet.add(b.b());
        }
        Set<String> hashSet2 = new HashSet(this.c.keySet());
        Editor edit = this.b.edit();
        for (String str : hashSet2) {
            if (hashSet.contains(str)) {
                c.b(a, "Retaining triggered action " + str + " in re-eligibility list.");
            } else {
                c.b(a, "Deleting outdated triggered action id " + str + " from re-eligibility list.");
                this.c.remove(str);
                edit.remove(str);
            }
        }
        edit.apply();
    }

    public final boolean a(dc dcVar) {
        dv f = dcVar.c().f();
        if (f.a()) {
            c.b(a, "Triggered action id " + dcVar.b() + " always eligible via configuration. Returning true for eligibility status");
            return true;
        } else if (!this.c.containsKey(dcVar.b())) {
            c.b(a, "Triggered action id " + dcVar.b() + " always eligible via never having been triggered. Returning true for eligibility status");
            return true;
        } else if (f.b()) {
            c.b(a, "Triggered action id " + dcVar.b() + " no longer eligible due to having been triggered in the past");
            return false;
        } else {
            long a = co.a() - ((Long) this.c.get(dcVar.b())).longValue();
            if (a > ((long) f.c().intValue())) {
                c.b(a, "Trigger action is re-eligible for display since " + a + " seconds have passed since the last time it was triggered (minimum interval: " + f.c() + ").");
                return true;
            }
            c.b(a, "Trigger action is not re-eligible for display since only " + a + " seconds have passed since the last time it was triggered (minimum interval: " + f.c() + ").");
            return false;
        }
    }

    public final void a(dc dcVar, long j) {
        c.b(a, "Updating re-eligibility for action Id " + dcVar.b() + " to time " + j + ".");
        this.c.put(dcVar.b(), Long.valueOf(j));
        Editor edit = this.b.edit();
        edit.putLong(dcVar.b(), j);
        edit.apply();
    }

    private Map<String, Long> a() {
        Map<String, Long> concurrentHashMap = new ConcurrentHashMap();
        Map all = this.b.getAll();
        if (all == null || all.size() == 0) {
            return concurrentHashMap;
        }
        Set<String> keySet = all.keySet();
        if (keySet == null || keySet.size() == 0) {
            return concurrentHashMap;
        }
        try {
            for (String str : keySet) {
                long j = this.b.getLong(str, 0);
                c.b(a, "Retrieving triggered action id " + str + " eligibility information from local storage.");
                concurrentHashMap.put(str, Long.valueOf(j));
            }
        } catch (Throwable e) {
            c.d(a, "Encountered unexpected exception while parsing stored re-eligibility information.", e);
        }
        return concurrentHashMap;
    }
}
