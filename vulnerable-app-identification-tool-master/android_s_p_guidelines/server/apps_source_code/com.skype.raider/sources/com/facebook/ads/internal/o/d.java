package com.facebook.ads.internal.o;

import android.text.TextUtils;
import com.facebook.ads.internal.h.a;
import com.facebook.ads.internal.h.c;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class d {
    private static d a = new d();

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            dVar = a;
        }
        return dVar;
    }

    public static e a(String str) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            Object obj = -1;
            switch (optString.hashCode()) {
                case 96432:
                    if (optString.equals("ads")) {
                        obj = null;
                        break;
                    }
                    break;
                case 96784904:
                    if (optString.equals("error")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return a(jSONObject);
                case 1:
                    return b(jSONObject);
                default:
                    JSONObject optJSONObject = jSONObject.optJSONObject("error");
                    if (optJSONObject != null) {
                        return c(optJSONObject);
                    }
                    break;
            }
        }
        return new e(a.UNKNOWN);
    }

    private static f a(JSONObject jSONObject) {
        int i = 0;
        JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
        c cVar = new c(com.facebook.ads.internal.h.d.a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"));
        if (jSONObject2.has("ads")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ads");
            while (i < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                cVar.a(new a(jSONObject3.optString("adapter"), jSONObject3.optJSONObject("data"), jSONObject3.optJSONArray("trackers")));
                i++;
            }
        }
        return new f(cVar, jSONObject.optString("server_request_id"), jSONObject.optString("server_response"), jSONObject.optString("an_validation_uuid"));
    }

    private static g b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
            return new g(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), new c(com.facebook.ads.internal.h.d.a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config")));
        } catch (JSONException e) {
            return c(jSONObject);
        }
    }

    private static g c(JSONObject jSONObject) {
        return new g(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), null);
    }
}
