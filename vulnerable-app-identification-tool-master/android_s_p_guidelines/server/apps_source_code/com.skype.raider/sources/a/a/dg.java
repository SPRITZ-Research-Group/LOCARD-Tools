package a.a;

import com.appboy.f.i;
import org.json.JSONException;
import org.json.JSONObject;

public final class dg implements dj {
    private String a;

    public final /* synthetic */ Object h() {
        return a();
    }

    public dg(JSONObject jSONObject) {
        this.a = jSONObject.getJSONObject("data").getString("event_name");
    }

    public final boolean a(ed edVar) {
        if (edVar instanceof ec) {
            ec ecVar = (ec) edVar;
            if (!i.c(ecVar.a()) && ecVar.a().equals(this.a)) {
                return true;
            }
        }
        return false;
    }

    private JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "custom_event");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("event_name", this.a);
            jSONObject.put("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
