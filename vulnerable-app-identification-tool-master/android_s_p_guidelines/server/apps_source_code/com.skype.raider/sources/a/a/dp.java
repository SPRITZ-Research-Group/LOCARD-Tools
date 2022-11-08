package a.a;

import com.appboy.f.i;
import org.json.JSONException;
import org.json.JSONObject;

public final class dp implements dj {
    private String a;

    public final /* synthetic */ Object h() {
        return a();
    }

    dp() {
    }

    public dp(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null && !optJSONObject.isNull("campaign_id")) {
            this.a = optJSONObject.optString("campaign_id", null);
        }
    }

    public final boolean a(ed edVar) {
        if (edVar instanceof ei) {
            if (i.c(this.a)) {
                return true;
            }
            ei eiVar = (ei) edVar;
            if (!i.c(eiVar.a()) && eiVar.a().equals(this.a)) {
                return true;
            }
        }
        return false;
    }

    private JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "push_click");
            if (this.a == null) {
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("campaign_id", this.a);
            jSONObject.putOpt("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
