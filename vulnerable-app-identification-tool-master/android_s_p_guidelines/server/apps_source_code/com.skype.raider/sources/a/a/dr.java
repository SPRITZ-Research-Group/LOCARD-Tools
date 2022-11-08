package a.a;

import com.appboy.f.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class dr implements dj {
    private static final String b = c.a(dr.class);
    ds a;

    public /* synthetic */ Object h() {
        return a();
    }

    public dr(JSONObject jSONObject) {
        JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("property_filters");
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray jSONArray2 = jSONArray.getJSONArray(i);
            List arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                arrayList2.add(new dm(jSONArray2.getJSONObject(i2)));
            }
            arrayList.add(new du(arrayList2));
        }
        this.a = new ds(arrayList);
    }

    public boolean a(ed edVar) {
        return this.a.a(edVar);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("property_filters", this.a.a());
            jSONObject.put("data", jSONObject2);
        } catch (Throwable e) {
            c.d(b, "Caught exception creating Json.", e);
        }
        return jSONObject;
    }
}
