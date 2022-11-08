package a.a;

import com.appboy.e.e;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bj implements ax, e<JSONArray> {
    private final JSONObject a;
    private final JSONArray b = new JSONArray();

    public bj(JSONObject jSONObject) {
        this.a = jSONObject;
        this.b.put(this.a);
    }

    public final JSONObject a() {
        return this.a;
    }

    public final JSONArray c() {
        return this.b;
    }

    public final boolean b() {
        if (this.a == null || this.a.length() == 0) {
            return true;
        }
        if (this.a.length() == 1 && this.a.has("user_id")) {
            return true;
        }
        return false;
    }

    public final /* synthetic */ Object h() {
        return this.b;
    }
}
