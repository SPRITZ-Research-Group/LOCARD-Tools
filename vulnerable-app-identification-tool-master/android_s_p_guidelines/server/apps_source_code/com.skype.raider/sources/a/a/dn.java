package a.a;

import com.appboy.f.i;
import org.json.JSONException;
import org.json.JSONObject;

public final class dn implements dj {
    String a;

    public final /* synthetic */ Object h() {
        return a();
    }

    dn() {
    }

    public dn(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null && !optJSONObject.isNull("product_id")) {
            this.a = optJSONObject.optString("product_id", null);
        }
    }

    public final boolean a(ed edVar) {
        if (edVar instanceof eh) {
            if (i.c(this.a)) {
                return true;
            }
            eh ehVar = (eh) edVar;
            if (!i.c(ehVar.a()) && ehVar.a().equals(this.a)) {
                return true;
            }
        }
        return false;
    }

    private JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "purchase");
            if (this.a == null) {
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("product_id", this.a);
            jSONObject.putOpt("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
