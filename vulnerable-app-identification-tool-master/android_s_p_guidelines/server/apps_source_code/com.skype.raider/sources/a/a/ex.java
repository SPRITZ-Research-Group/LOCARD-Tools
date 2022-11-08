package a.a;

import org.json.JSONArray;
import org.json.JSONObject;

public final class ex {
    private static fe a(ey eyVar) {
        return new fd(eyVar);
    }

    private static ez a(JSONObject jSONObject, JSONObject jSONObject2, fe feVar) {
        return feVar.a(jSONObject, jSONObject2);
    }

    public static ez a(String str, String str2, ey eyVar) {
        fe a = a(eyVar);
        Object a2 = fa.a(str);
        Object a3 = fa.a(str2);
        if ((a2 instanceof JSONObject) && (a3 instanceof JSONObject)) {
            return a((JSONObject) a2, (JSONObject) a3, a);
        }
        if ((a2 instanceof JSONArray) && (a3 instanceof JSONArray)) {
            return a.a((JSONArray) a2, (JSONArray) a3);
        }
        if ((a2 instanceof ev) && (a3 instanceof ev)) {
            ev evVar = (ev) a2;
            ev evVar2 = (ev) a3;
            ez ezVar = new ez();
            if (!evVar.a().equals(evVar2.a())) {
                ezVar.a("");
            }
            return ezVar;
        } else if (a2 instanceof JSONObject) {
            return new ez().a("", a2, a3);
        } else {
            return new ez().a("", a2, a3);
        }
    }

    public static ez a(JSONObject jSONObject, JSONObject jSONObject2, ey eyVar) {
        return a(jSONObject, jSONObject2, a(eyVar));
    }
}
