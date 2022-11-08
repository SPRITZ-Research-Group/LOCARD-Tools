package a.a;

import com.appboy.f.c;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class ba {
    private static final String a = c.a(ba.class);
    private long b;
    private Set<String> c;
    private Set<String> d;
    private Set<String> e;
    private boolean f = false;
    private boolean g = false;
    private long h = -1;
    private float i = -1.0f;
    private int j = -1;
    private int k = -1;
    private int l = -1;
    private boolean m = false;
    private boolean n = false;
    private long o = -1;
    private boolean p = false;

    public ba(JSONObject jSONObject) {
        this.c = a(jSONObject, "events_blacklist");
        this.d = a(jSONObject, "attributes_blacklist");
        this.e = a(jSONObject, "purchases_blacklist");
        this.b = jSONObject.optLong("time", 0);
        this.o = jSONObject.optLong("messaging_session_timeout", -1);
        JSONObject optJSONObject = jSONObject.optJSONObject("location");
        if (optJSONObject != null) {
            try {
                this.g = optJSONObject.getBoolean("enabled");
                this.f = true;
            } catch (Throwable e) {
                c.d(a, "Required location collection fields were null. Using defaults.", e);
                this.f = false;
            }
            long optLong = optJSONObject.optLong("time", -1);
            if (optLong >= 0) {
                this.h = optLong * 1000;
            }
            this.i = (float) optJSONObject.optDouble("distance", -1.0d);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("geofences");
        if (optJSONObject2 != null) {
            try {
                this.j = optJSONObject2.getInt("min_time_since_last_request");
                this.k = optJSONObject2.getInt("min_time_since_last_report");
                this.n = optJSONObject2.getBoolean("enabled");
                this.m = true;
                this.l = optJSONObject2.optInt("max_num_to_register", 20);
            } catch (Throwable e2) {
                c.d(a, "Required geofence fields were null. Using defaults.", e2);
                this.j = -1;
                this.k = -1;
                this.l = -1;
                this.n = false;
                this.m = false;
            }
        }
        optJSONObject2 = jSONObject.optJSONObject("test_user");
        if (optJSONObject2 != null) {
            try {
                this.p = optJSONObject2.getBoolean("device_logging_enabled");
            } catch (Throwable e22) {
                c.d(a, "Required test user fields were null. Using defaults", e22);
                this.p = false;
            }
        }
    }

    public final long a() {
        return this.b;
    }

    public final Set<String> b() {
        return this.c;
    }

    public final Set<String> c() {
        return this.d;
    }

    public final Set<String> d() {
        return this.e;
    }

    public final long e() {
        return this.o;
    }

    public final boolean f() {
        return this.g;
    }

    public final boolean g() {
        return this.f;
    }

    public final long h() {
        return this.h;
    }

    public final float i() {
        return this.i;
    }

    public final int j() {
        return this.j;
    }

    public final int k() {
        return this.k;
    }

    public final int l() {
        return this.l;
    }

    public final boolean m() {
        return this.n;
    }

    public final boolean n() {
        return this.m;
    }

    public final boolean o() {
        return this.p;
    }

    public final void a(long j) {
        this.b = j;
    }

    public final void a(Set<String> set) {
        this.c = set;
    }

    public final void b(Set<String> set) {
        this.d = set;
    }

    public final void c(Set<String> set) {
        this.e = set;
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void b(boolean z) {
        this.f = z;
    }

    public final void b(long j) {
        this.h = j;
    }

    public final void c(long j) {
        this.o = j;
    }

    public final void a(float f) {
        this.i = f;
    }

    public final void a(int i) {
        this.j = i;
    }

    public final void b(int i) {
        this.k = i;
    }

    public final void c(int i) {
        this.l = i;
    }

    public final void c(boolean z) {
        this.n = z;
    }

    public final void d(boolean z) {
        this.m = z;
    }

    public final void e(boolean z) {
        this.p = z;
    }

    private static Set<String> a(JSONObject jSONObject, String str) {
        if (jSONObject.optJSONArray(str) == null) {
            return null;
        }
        Set<String> hashSet = new HashSet();
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        for (int i = 0; i < optJSONArray.length(); i++) {
            hashSet.add(optJSONArray.getString(i));
        }
        return hashSet;
    }
}
