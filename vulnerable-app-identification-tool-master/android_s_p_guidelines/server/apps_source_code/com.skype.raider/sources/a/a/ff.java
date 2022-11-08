package a.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ff {
    private static Integer a = new Integer(1);

    public static Map<Object, JSONObject> a(JSONArray jSONArray, String str) {
        Map<Object, JSONObject> hashMap = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= jSONArray.length()) {
                return hashMap;
            }
            JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
            hashMap.put(jSONObject.get(str), jSONObject);
            i = i2 + 1;
        }
    }

    public static String a(JSONArray jSONArray) {
        for (String str : a((JSONObject) jSONArray.get(0))) {
            if (a(str, jSONArray)) {
                return str;
            }
        }
        return null;
    }

    public static boolean a(String str, JSONArray jSONArray) {
        Set hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (!(obj instanceof JSONObject)) {
                return false;
            }
            JSONObject jSONObject = (JSONObject) obj;
            if (!jSONObject.has(str)) {
                return false;
            }
            obj = jSONObject.get(str);
            if (!a(obj) || hashSet.contains(obj)) {
                return false;
            }
            hashSet.add(obj);
        }
        return true;
    }

    public static List<Object> b(JSONArray jSONArray) {
        List<Object> arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.get(i));
        }
        return arrayList;
    }

    public static boolean c(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!a(jSONArray.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(Object obj) {
        return ((obj instanceof JSONObject) || (obj instanceof JSONArray)) ? false : true;
    }

    public static boolean d(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!(jSONArray.get(i) instanceof JSONObject)) {
                return false;
            }
        }
        return true;
    }

    public static Set<String> a(JSONObject jSONObject) {
        Set<String> treeSet = new TreeSet();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            treeSet.add((String) keys.next());
        }
        return treeSet;
    }

    public static String a(String str, String str2) {
        return "".equals(str) ? str2 : str + "." + str2;
    }

    public static String a(String str, String str2, Object obj) {
        return str + "[" + str2 + "=" + obj + "]";
    }

    public static <T> Map<T, Integer> a(Collection<T> collection) {
        Map<T, Integer> hashMap = new HashMap();
        for (Object next : collection) {
            Integer num = (Integer) hashMap.get(next);
            if (num == null) {
                hashMap.put(next, a);
            } else {
                hashMap.put(next, new Integer(num.intValue() + 1));
            }
        }
        return hashMap;
    }
}
