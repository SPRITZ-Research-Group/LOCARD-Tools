package a.a;

import com.appboy.e.a.a;
import com.appboy.e.a.b;
import com.appboy.e.a.d;
import com.appboy.e.a.e;
import com.appboy.e.a.f;
import com.appboy.f.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ay {
    private static final String a = c.a(ay.class);

    public static <T> List<T> a(JSONArray jSONArray, Class<T> cls, ag agVar, cf cfVar) {
        List<T> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object a;
                String optString = jSONArray.optString(i);
                if (cls.equals(String.class)) {
                    a = cn.a(optString, cls);
                } else if (cls.equals(com.appboy.e.a.c.class)) {
                    JSONObject jSONObject = new JSONObject(optString);
                    optString = jSONObject.getString("type");
                    if ("banner_image".equals(optString)) {
                        a = new a(jSONObject, agVar, cfVar);
                    } else if ("captioned_image".equals(optString)) {
                        a = new b(jSONObject, agVar, cfVar);
                    } else if ("cross_promotion_small".equals(optString)) {
                        a = new d(jSONObject, agVar, cfVar);
                    } else if ("short_news".equals(optString)) {
                        a = new e(jSONObject, agVar, cfVar);
                    } else if ("text_announcement".equals(optString)) {
                        a = new f(jSONObject, agVar, cfVar);
                    } else {
                        throw new JSONException("Failed to construct java object of type " + optString + " from JSON [" + jSONObject.toString() + "]");
                    }
                    a = cn.a(a, cls);
                } else {
                    throw new JSONException("Failed to construct java object " + optString + ", target class " + cls.toString() + " isn'tString nor Card. Please update the createObject in ModelFactory to handle extra class type.");
                }
                if (a != null) {
                    arrayList.add(a);
                }
            } catch (Throwable e) {
                c.a(a, "Unable to cast JSON to [" + cls.getName() + "] in array. Ignoring.", e);
            }
        }
        return arrayList;
    }
}
