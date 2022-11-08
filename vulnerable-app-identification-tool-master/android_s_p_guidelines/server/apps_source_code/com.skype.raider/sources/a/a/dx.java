package a.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class dx implements dv {
    private final int a;

    public final /* synthetic */ Object h() {
        return d();
    }

    public dx(JSONObject jSONObject) {
        this.a = jSONObject.optInt("re_eligibility", -1);
    }

    public final boolean a() {
        return this.a == 0;
    }

    public final boolean b() {
        return this.a == -1;
    }

    public final Integer c() {
        if (this.a > 0) {
            return Integer.valueOf(this.a);
        }
        return null;
    }

    private JSONObject d() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("re_eligibility", this.a);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
