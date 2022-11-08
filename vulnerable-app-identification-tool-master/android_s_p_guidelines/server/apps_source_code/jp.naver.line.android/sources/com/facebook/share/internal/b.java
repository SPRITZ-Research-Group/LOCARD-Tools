package com.facebook.share.internal;

import com.facebook.share.model.CameraEffectArguments;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class b {
    private static final Map<Class<?>, c> a;

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put(String.class, new c() {
            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                jSONObject.put(str, obj);
            }
        });
        a.put(String[].class, new c() {
            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                JSONArray jSONArray = new JSONArray();
                for (Object put : (String[]) obj) {
                    jSONArray.put(put);
                }
                jSONObject.put(str, jSONArray);
            }
        });
        a.put(JSONArray.class, new c() {
            public final void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
                throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
            }
        });
    }

    public static JSONObject a(CameraEffectArguments cameraEffectArguments) throws JSONException {
        if (cameraEffectArguments == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : cameraEffectArguments.a()) {
            Object a = cameraEffectArguments.a(str);
            if (a != null) {
                c cVar = (c) a.get(a.getClass());
                if (cVar != null) {
                    cVar.a(jSONObject, str, a);
                } else {
                    StringBuilder stringBuilder = new StringBuilder("Unsupported type: ");
                    stringBuilder.append(a.getClass());
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
        }
        return jSONObject;
    }
}
