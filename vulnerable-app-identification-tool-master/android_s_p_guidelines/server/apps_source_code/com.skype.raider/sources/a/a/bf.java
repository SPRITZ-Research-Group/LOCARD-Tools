package a.a;

import com.appboy.f.c;
import org.json.JSONObject;

public final class bf implements aw {
    private static final String a = c.a(bf.class);
    private final double b;
    private final double c;
    private final Double d;
    private final Double e;

    public final /* synthetic */ Object h() {
        return e();
    }

    public bf(double d, double d2, Double d3, Double d4) {
        if (d > 90.0d || d < -90.0d || d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("Unable to create AppboyLocation. Latitude and longitude values are bounded by ±90 and ±180 respectively");
        }
        this.b = d;
        this.c = d2;
        this.d = d3;
        this.e = d4;
    }

    public final double a() {
        return this.b;
    }

    public final double b() {
        return this.c;
    }

    public final Double c() {
        return this.d;
    }

    public final Double d() {
        return this.e;
    }

    private JSONObject e() {
        Object obj = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            Object obj2;
            jSONObject.put("latitude", this.b);
            jSONObject.put("longitude", this.c);
            if (this.d != null) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 != null) {
                jSONObject.put("altitude", this.d);
            }
            if (this.e == null) {
                obj = null;
            }
            if (obj != null) {
                jSONObject.put("ll_accuracy", this.e);
            }
        } catch (Throwable e) {
            c.d(a, "Caught exception creating location Json.", e);
        }
        return jSONObject;
    }
}
