package a.a;

import com.appboy.f.i;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class dk implements dj {
    private String a;
    private Set<String> b = new HashSet();

    public final /* synthetic */ Object h() {
        return a();
    }

    public dk(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        this.a = jSONObject2.getString("id");
        JSONArray optJSONArray = jSONObject2.optJSONArray("buttons");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.b.add(optJSONArray.getString(i));
            }
        }
    }

    public final boolean a(ed edVar) {
        if (!(edVar instanceof ef)) {
            return false;
        }
        ef efVar = (ef) edVar;
        if (i.c(efVar.a()) || !efVar.a().equals(this.a)) {
            return false;
        }
        if (this.b.size() <= 0) {
            return i.c(efVar.f());
        }
        if (i.c(efVar.f()) || !this.b.contains(efVar.f())) {
            return false;
        }
        return true;
    }

    private JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "iam_click");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", this.a);
            if (this.b.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (String put : this.b) {
                    jSONArray.put(put);
                }
                jSONObject2.put("buttons", jSONArray);
            }
            jSONObject.put("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
