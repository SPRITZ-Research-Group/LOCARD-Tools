package a.a;

import com.appboy.e.a;
import com.appboy.f.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class cp {
    private static final String a = c.a(cp.class);

    public static List<a> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        List<a> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                try {
                    c.f(a, "Received null or blank geofence Json. Not parsing.");
                } catch (Throwable e) {
                    c.c(a, "Failed to deserialize geofence Json due to JSONException: " + optJSONObject, e);
                } catch (Throwable e2) {
                    c.d(a, "Failed to deserialize geofence Json:" + optJSONObject, e2);
                }
            } else {
                arrayList.add(new a(optJSONObject));
            }
        }
        return arrayList;
    }
}
