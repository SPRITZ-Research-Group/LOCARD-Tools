package a.a;

import com.appboy.e.e;
import com.appboy.f.c;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class cv extends JSONObject {
    private static final String a = c.a(cv.class);

    public static <T> JSONArray a(Collection<? extends e<T>> collection) {
        JSONArray jSONArray = new JSONArray();
        for (e h : collection) {
            jSONArray.put(h.h());
        }
        return jSONArray;
    }

    public static <T> JSONArray a(T[] tArr) {
        JSONArray jSONArray = new JSONArray();
        for (Object put : tArr) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    public static String a(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optString(str, null);
    }

    public static Map<String, String> a(JSONObject jSONObject, Map<String, String> map) {
        if (jSONObject != null) {
            map = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                map.put(str, jSONObject.getString(str));
            }
        }
        return map;
    }

    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            String str;
            JSONObject jSONObject3 = new JSONObject();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                jSONObject3.put(str, jSONObject.get(str));
            }
            keys = jSONObject2.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                jSONObject3.put(str, jSONObject2.get(str));
            }
            return jSONObject3;
        } catch (Throwable e) {
            c.d(a, "Caught exception merging Json objects.", e);
            return null;
        }
    }

    public static String a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        try {
            return jSONObject.toString(2);
        } catch (Throwable e) {
            c.d(a, "Caught JSONException while generating pretty printed json. Returning standard toString().", e);
            return jSONObject.toString();
        }
    }

    public static <TargetEnum extends Enum<TargetEnum>> TargetEnum a(JSONObject jSONObject, String str, Class<TargetEnum> cls, TargetEnum targetEnum) {
        try {
            return Enum.valueOf(cls, jSONObject.getString(str).toUpperCase(Locale.US));
        } catch (Exception e) {
            return targetEnum;
        }
    }
}
