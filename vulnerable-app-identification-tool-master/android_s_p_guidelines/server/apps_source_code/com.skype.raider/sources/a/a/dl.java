package a.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class dl implements dj {
    public final /* synthetic */ Object h() {
        return a();
    }

    public final boolean a(ed edVar) {
        return edVar instanceof eg;
    }

    private static JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "open");
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
