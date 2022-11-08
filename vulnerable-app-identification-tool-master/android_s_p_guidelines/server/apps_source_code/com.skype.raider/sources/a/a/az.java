package a.a;

import android.support.annotation.NonNull;
import com.appboy.e.e;
import com.appboy.f.c;
import org.json.JSONObject;

public class az implements e<JSONObject> {
    private static final String a = c.a(az.class);
    private final bc b;
    private final double c;
    private volatile Double d;
    private volatile boolean e;

    public final /* synthetic */ Object h() {
        return g();
    }

    public az(bc bcVar, double d) {
        this(bcVar, d, (byte) 0);
    }

    private az(bc bcVar, double d, byte b) {
        this.e = false;
        this.b = bcVar;
        this.c = d;
        this.e = false;
        this.d = null;
    }

    public az(@NonNull JSONObject jSONObject) {
        this.e = false;
        this.b = bc.a(jSONObject.getString("session_id"));
        this.c = jSONObject.getDouble("start_time");
        this.e = jSONObject.getBoolean("is_sealed");
        if (jSONObject.has("end_time")) {
            this.d = Double.valueOf(jSONObject.getDouble("end_time"));
        }
    }

    public final bc a() {
        return this.b;
    }

    public final double b() {
        return this.c;
    }

    public final Double c() {
        return this.d;
    }

    public final void a(Double d) {
        this.d = d;
    }

    public final boolean d() {
        return this.e;
    }

    public final void e() {
        this.e = true;
        this.d = Double.valueOf(co.b());
    }

    public final long f() {
        if (this.d == null) {
            return -1;
        }
        long doubleValue = (long) (this.d.doubleValue() - this.c);
        if (doubleValue >= 0) {
            return doubleValue;
        }
        c.f(a, "End time '" + this.d + "' for session is less than the start time '" + this.c + "' for this session.");
        return doubleValue;
    }

    public final JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("session_id", this.b);
            jSONObject.put("start_time", this.c);
            jSONObject.put("is_sealed", this.e);
            if (this.d != null) {
                jSONObject.put("end_time", this.d);
            }
        } catch (Throwable e) {
            c.d(a, "Caught exception creating Session Json.", e);
        }
        return jSONObject;
    }
}
