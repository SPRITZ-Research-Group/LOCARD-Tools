package a.a;

import com.appboy.f.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class df implements dc {
    private static final String a = c.a(df.class);
    private final String b;
    private final dw c;
    private final List<dj> d = new ArrayList();
    private boolean e;

    public /* synthetic */ Object h() {
        return e();
    }

    protected df(JSONObject jSONObject) {
        this.b = jSONObject.getString("id");
        this.c = new dy(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("trigger_condition");
        if (jSONArray != null && jSONArray.length() > 0) {
            this.d.addAll(eu.a(jSONArray));
        }
        this.e = jSONObject.optBoolean("prefetch", true);
    }

    public final boolean a(ed edVar) {
        Object obj = (this.c.a() == -1 || co.a() > this.c.a()) ? 1 : null;
        if (obj != null) {
            if (this.c.b() == -1 || co.a() < this.c.b()) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                obj = 1;
                if (obj != null) {
                    c.b(a, "Triggered action " + this.b + "not eligible to be triggered by " + edVar.b() + " event. Current device time outside triggered action time window.");
                    return false;
                }
                for (dj a : this.d) {
                    if (a.a(edVar)) {
                        return true;
                    }
                }
                return false;
            }
        }
        obj = null;
        if (obj != null) {
            while (r3.hasNext()) {
                if (a.a(edVar)) {
                    return true;
                }
            }
            return false;
        }
        c.b(a, "Triggered action " + this.b + "not eligible to be triggered by " + edVar.b() + " event. Current device time outside triggered action time window.");
        return false;
    }

    public final boolean a() {
        return this.e;
    }

    public final dw c() {
        return this.c;
    }

    public final String b() {
        return this.b;
    }

    public JSONObject e() {
        try {
            JSONObject jSONObject = (JSONObject) this.c.h();
            jSONObject.put("id", this.b);
            if (this.d == null) {
                return jSONObject;
            }
            JSONArray jSONArray = new JSONArray();
            for (dj h : this.d) {
                jSONArray.put(h.h());
            }
            jSONObject.put("trigger_condition", jSONArray);
            jSONObject.put("prefetch", this.e);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
