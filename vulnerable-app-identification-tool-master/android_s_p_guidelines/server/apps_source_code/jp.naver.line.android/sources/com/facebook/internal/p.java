package com.facebook.internal;

import com.facebook.q;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;

public final class p {
    private static p g;
    private final Map<Integer, Set<Integer>> a;
    private final Map<Integer, Set<Integer>> b;
    private final Map<Integer, Set<Integer>> c;
    private final String d;
    private final String e;
    private final String f;

    private p(Map<Integer, Set<Integer>> map, Map<Integer, Set<Integer>> map2, Map<Integer, Set<Integer>> map3, String str, String str2, String str3) {
        this.a = map;
        this.b = map2;
        this.c = map3;
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    public final String a(q qVar) {
        switch (qVar) {
            case OTHER:
                return this.d;
            case LOGIN_RECOVERABLE:
                return this.f;
            case TRANSIENT:
                return this.e;
            default:
                return null;
        }
    }

    public final q a(int i, int i2, boolean z) {
        if (z) {
            return q.TRANSIENT;
        }
        Set set;
        if (this.a != null && this.a.containsKey(Integer.valueOf(i))) {
            set = (Set) this.a.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return q.OTHER;
            }
        }
        if (this.c != null && this.c.containsKey(Integer.valueOf(i))) {
            set = (Set) this.c.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return q.LOGIN_RECOVERABLE;
            }
        }
        if (this.b != null && this.b.containsKey(Integer.valueOf(i))) {
            Set set2 = (Set) this.b.get(Integer.valueOf(i));
            if (set2 == null || set2.contains(Integer.valueOf(i2))) {
                return q.TRANSIENT;
            }
        }
        return q.OTHER;
    }

    public static synchronized p a() {
        p pVar;
        synchronized (p.class) {
            if (g == null) {
                g = new p(null, new HashMap<Integer, Set<Integer>>() {
                    {
                        put(Integer.valueOf(2), null);
                        put(Integer.valueOf(4), null);
                        put(Integer.valueOf(9), null);
                        put(Integer.valueOf(17), null);
                        put(Integer.valueOf(341), null);
                    }
                }, new HashMap<Integer, Set<Integer>>() {
                    {
                        put(Integer.valueOf(102), null);
                        put(Integer.valueOf(190), null);
                        put(Integer.valueOf(HttpStatus.SC_PRECONDITION_FAILED), null);
                    }
                }, null, null, null);
            }
            pVar = g;
        }
        return pVar;
    }

    private static Map<Integer, Set<Integer>> a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("items");
        if (optJSONArray.length() == 0) {
            return null;
        }
        Map<Integer, Set<Integer>> hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("code");
                if (optInt != 0) {
                    Object obj;
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                    if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                        obj = null;
                    } else {
                        obj = new HashSet();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            int optInt2 = optJSONArray2.optInt(i2);
                            if (optInt2 != 0) {
                                obj.add(Integer.valueOf(optInt2));
                            }
                        }
                    }
                    hashMap.put(Integer.valueOf(optInt), obj);
                }
            }
        }
        return hashMap;
    }

    public static p a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        Map map = null;
        Map map2 = map;
        Map map3 = map2;
        String str = map3;
        String str2 = str;
        String str3 = str2;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("name");
                if (optString != null) {
                    if (optString.equalsIgnoreCase("other")) {
                        str = optJSONObject.optString("recovery_message", null);
                        map = a(optJSONObject);
                    } else if (optString.equalsIgnoreCase("transient")) {
                        str2 = optJSONObject.optString("recovery_message", null);
                        map2 = a(optJSONObject);
                    } else if (optString.equalsIgnoreCase("login_recoverable")) {
                        str3 = optJSONObject.optString("recovery_message", null);
                        map3 = a(optJSONObject);
                    }
                }
            }
        }
        return new p(map, map2, map3, str, str2, str3);
    }
}
