package a.a;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class fc implements fe {
    public final ez a(JSONObject jSONObject, JSONObject jSONObject2) {
        ez ezVar = new ez();
        a("", jSONObject, jSONObject2, ezVar);
        return ezVar;
    }

    public final ez a(JSONArray jSONArray, JSONArray jSONArray2) {
        ez ezVar = new ez();
        e("", jSONArray, jSONArray2, ezVar);
        return ezVar;
    }

    protected final void a(String str, JSONArray jSONArray, JSONArray jSONArray2, ez ezVar) {
        String a = ff.a(jSONArray);
        if (a == null || !ff.a(a, jSONArray2)) {
            d(str, jSONArray, jSONArray2, ezVar);
            return;
        }
        Map a2 = ff.a(jSONArray, a);
        Map a3 = ff.a(jSONArray2, a);
        for (Object next : a2.keySet()) {
            if (a3.containsKey(next)) {
                a(ff.a(str, a, next), (Object) (JSONObject) a2.get(next), (Object) (JSONObject) a3.get(next), ezVar);
            } else {
                ezVar.a(ff.a(str, a, next), a2.get(next));
            }
        }
        for (Object next2 : a3.keySet()) {
            if (!a2.containsKey(next2)) {
                ezVar.b(ff.a(str, a, next2), a3.get(next2));
            }
        }
    }

    protected static void b(String str, JSONArray jSONArray, JSONArray jSONArray2, ez ezVar) {
        Map a = ff.a(ff.b(jSONArray));
        Map a2 = ff.a(ff.b(jSONArray2));
        for (Object next : a.keySet()) {
            if (!a2.containsKey(next)) {
                ezVar.a(str + "[]", next);
            } else if (!((Integer) a2.get(next)).equals(a.get(next))) {
                ezVar.a(str + "[]: Expected " + a.get(next) + " occurrence(s) of " + next + " but got " + a2.get(next) + " occurrence(s)");
            }
        }
        for (Object next2 : a2.keySet()) {
            if (!a.containsKey(next2)) {
                ezVar.b(str + "[]", next2);
            }
        }
    }

    protected final void c(String str, JSONArray jSONArray, JSONArray jSONArray2, ez ezVar) {
        for (int i = 0; i < jSONArray.length(); i++) {
            a(str + "[" + i + "]", jSONArray.get(i), jSONArray2.get(i), ezVar);
        }
    }

    protected final void d(String str, JSONArray jSONArray, JSONArray jSONArray2, ez ezVar) {
        Set hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj;
            Object obj2 = jSONArray.get(i);
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                Object obj3 = jSONArray2.get(i2);
                if (!hashSet.contains(Integer.valueOf(i2)) && obj3.getClass().equals(obj2.getClass())) {
                    int obj4;
                    if (obj2 instanceof JSONObject) {
                        if (a((JSONObject) obj2, (JSONObject) obj3).a()) {
                            hashSet.add(Integer.valueOf(i2));
                            obj4 = 1;
                            break;
                        }
                    } else if (obj2 instanceof JSONArray) {
                        if (a((JSONArray) obj2, (JSONArray) obj3).a()) {
                            hashSet.add(Integer.valueOf(i2));
                            obj4 = 1;
                            break;
                        }
                    } else if (obj2.equals(obj3)) {
                        hashSet.add(Integer.valueOf(i2));
                        obj4 = 1;
                        break;
                    }
                }
            }
            obj4 = null;
            if (obj4 == null) {
                ezVar.a(str + "[" + i + "] Could not find match for element " + obj2);
                return;
            }
        }
    }
}
