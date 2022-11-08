package a.a;

import org.json.JSONException;
import org.json.JSONObject;

public final class dq implements dj {
    public final /* synthetic */ Object h() {
        return a();
    }

    public final boolean a(ed edVar) {
        return edVar instanceof ej;
    }

    private static JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "test");
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
