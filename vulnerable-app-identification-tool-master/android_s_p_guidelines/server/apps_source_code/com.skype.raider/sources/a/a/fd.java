package a.a;

import org.json.JSONArray;
import org.json.JSONObject;

public final class fd extends fc {
    ey a;

    public fd(ey eyVar) {
        this.a = eyVar;
    }

    public final void a(String str, Object obj, Object obj2, ez ezVar) {
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            if (((Number) obj).doubleValue() != ((Number) obj2).doubleValue()) {
                ezVar.a(str, obj, obj2);
            }
        } else if (!obj.getClass().isAssignableFrom(obj2.getClass())) {
            ezVar.a(str, obj, obj2);
        } else if (obj instanceof JSONArray) {
            e(str, (JSONArray) obj, (JSONArray) obj2, ezVar);
        } else if (obj instanceof JSONObject) {
            a(str, (JSONObject) obj, (JSONObject) obj2, ezVar);
        } else if (!obj.equals(obj2)) {
            ezVar.a(str, obj, obj2);
        }
    }

    public final void e(String str, JSONArray jSONArray, JSONArray jSONArray2, ez ezVar) {
        if (jSONArray.length() != jSONArray2.length()) {
            ezVar.a(str + "[]: Expected " + jSONArray.length() + " values but got " + jSONArray2.length());
        } else if (jSONArray.length() == 0) {
        } else {
            if (this.a.b()) {
                c(str, jSONArray, jSONArray2, ezVar);
            } else if (ff.c(jSONArray)) {
                fc.b(str, jSONArray, jSONArray2, ezVar);
            } else if (ff.d(jSONArray)) {
                a(str, jSONArray, jSONArray2, ezVar);
            } else {
                d(str, jSONArray, jSONArray2, ezVar);
            }
        }
    }

    public final void a(String str, JSONObject jSONObject, JSONObject jSONObject2, ez ezVar) {
        for (String str2 : ff.a(jSONObject)) {
            Object obj = jSONObject.get(str2);
            if (jSONObject2.has(str2)) {
                a(ff.a(str, str2), obj, jSONObject2.get(str2), ezVar);
            } else {
                ezVar.a(str, str2);
            }
        }
        if (!this.a.a()) {
            for (String str22 : ff.a(jSONObject2)) {
                if (!jSONObject.has(str22)) {
                    ezVar.b(str, str22);
                }
            }
        }
    }
}
