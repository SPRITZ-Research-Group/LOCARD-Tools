package a.a;

import com.appboy.e.a;
import com.appboy.e.b;
import com.appboy.e.o;
import com.appboy.f.c;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class at {
    private static final String a = c.a(at.class);
    private final JSONArray b;
    private final b c;
    private final List<dc> d;
    private final ba e;
    private final List<a> f;
    private final o g;

    public at(JSONObject jSONObject, am amVar) {
        ba baVar;
        String optString = jSONObject.optString("error", null);
        if (optString != null) {
            this.g = new o(optString);
        } else {
            this.g = null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("feed");
        if (optJSONArray != null) {
            this.b = optJSONArray;
        } else {
            this.b = null;
        }
        this.d = eu.a(jSONObject.optJSONArray("triggers"), amVar);
        JSONObject optJSONObject = jSONObject.optJSONObject("config");
        if (optJSONObject != null) {
            try {
                baVar = new ba(optJSONObject);
            } catch (Throwable e) {
                c.c(a, "Encountered JSONException processing server config: " + optJSONObject.toString(), e);
                baVar = null;
            } catch (Throwable e2) {
                c.c(a, "Encountered Exception processing server config: " + optJSONObject.toString(), e2);
            }
            this.e = baVar;
            this.c = eu.a(jSONObject.optJSONObject("templated_message"), amVar);
            this.f = cp.a(jSONObject.optJSONArray("geofences"));
        }
        baVar = null;
        this.e = baVar;
        this.c = eu.a(jSONObject.optJSONObject("templated_message"), amVar);
        this.f = cp.a(jSONObject.optJSONArray("geofences"));
    }

    public final boolean a() {
        return this.b != null;
    }

    public final boolean b() {
        return this.c != null;
    }

    public final boolean c() {
        return this.e != null;
    }

    public final boolean d() {
        return this.d != null;
    }

    public final boolean e() {
        return this.g != null;
    }

    public final boolean f() {
        return this.f != null;
    }

    public final JSONArray g() {
        return this.b;
    }

    public final b h() {
        return this.c;
    }

    public final ba i() {
        return this.e;
    }

    public final List<dc> j() {
        return this.d;
    }

    public final List<a> k() {
        return this.f;
    }

    public final o l() {
        return this.g;
    }
}
