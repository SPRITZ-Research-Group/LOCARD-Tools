package com.facebook.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class e {
    private static final Map<Class<?>, f> a;

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put(Boolean.class, new f() {
            public final void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            }

            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(Integer.class, new f() {
            public final void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putInt(str, ((Integer) obj).intValue());
            }

            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(Long.class, new f() {
            public final void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putLong(str, ((Long) obj).longValue());
            }

            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(Double.class, new f() {
            public final void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            }

            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(String.class, new f() {
            public final void a(Bundle bundle, String str, Object obj) throws JSONException {
                bundle.putString(str, (String) obj);
            }

            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(String[].class, new f() {
            public final void a(Bundle bundle, String str, Object obj) throws JSONException {
                throw new IllegalArgumentException("Unexpected type from JSON");
            }

            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                JSONArray jSONArray = new JSONArray();
                for (Object put : (String[]) obj) {
                    jSONArray.put(put);
                }
                jSONObject.put(str, jSONArray);
            }
        });
        a.put(JSONArray.class, new f() {
            public final void a(Bundle bundle, String str, Object obj) throws JSONException {
                JSONArray jSONArray = (JSONArray) obj;
                ArrayList arrayList = new ArrayList();
                if (jSONArray.length() == 0) {
                    bundle.putStringArrayList(str, arrayList);
                    return;
                }
                int i = 0;
                while (i < jSONArray.length()) {
                    Object obj2 = jSONArray.get(i);
                    if (obj2 instanceof String) {
                        arrayList.add((String) obj2);
                        i++;
                    } else {
                        StringBuilder stringBuilder = new StringBuilder("Unexpected type in an array: ");
                        stringBuilder.append(obj2.getClass());
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
                bundle.putStringArrayList(str, arrayList);
            }

            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
            }
        });
    }

    public static JSONObject a(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                if (obj instanceof List) {
                    JSONArray jSONArray = new JSONArray();
                    for (String put : (List) obj) {
                        jSONArray.put(put);
                    }
                    jSONObject.put(str, jSONArray);
                } else if (obj instanceof Bundle) {
                    jSONObject.put(str, a((Bundle) obj));
                } else {
                    f fVar = (f) a.get(obj.getClass());
                    if (fVar != null) {
                        fVar.a(jSONObject, str, obj);
                    } else {
                        StringBuilder stringBuilder = new StringBuilder("Unsupported type: ");
                        stringBuilder.append(obj.getClass());
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
            }
        }
        return jSONObject;
    }

    public static Bundle a(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (!(obj == null || obj == JSONObject.NULL)) {
                if (obj instanceof JSONObject) {
                    bundle.putBundle(str, a((JSONObject) obj));
                } else {
                    f fVar = (f) a.get(obj.getClass());
                    if (fVar != null) {
                        fVar.a(bundle, str, obj);
                    } else {
                        StringBuilder stringBuilder = new StringBuilder("Unsupported type: ");
                        stringBuilder.append(obj.getClass());
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
            }
        }
        return bundle;
    }
}
