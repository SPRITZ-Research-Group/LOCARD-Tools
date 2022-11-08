package a.a;

import com.appboy.e.e;
import com.appboy.f.c;
import org.json.JSONObject;

public class bh implements e<JSONObject> {
    private static final String a = c.a(bh.class);
    private final long b;

    public final /* synthetic */ Object h() {
        return a();
    }

    public bh(long j) {
        this.b = j;
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("config_time", this.b);
            return jSONObject;
        } catch (Throwable e) {
            c.a(a, "Caught exception creating config params json.", e);
            return null;
        }
    }
}
