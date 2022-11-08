package com.facebook.share.internal;

import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ae {
    public static JSONObject a(ShareOpenGraphAction shareOpenGraphAction, af afVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphAction.b()) {
            jSONObject.put(str, a(shareOpenGraphAction.a(str), afVar));
        }
        return jSONObject;
    }

    private static JSONObject a(ShareOpenGraphObject shareOpenGraphObject, af afVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphObject.b()) {
            jSONObject.put(str, a(shareOpenGraphObject.a(str), afVar));
        }
        return jSONObject;
    }

    private static JSONArray a(List list, af afVar) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object a : list) {
            jSONArray.put(a(a, afVar));
        }
        return jSONArray;
    }

    private static Object a(Object obj, af afVar) throws JSONException {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long)) {
            return obj;
        }
        if (obj instanceof SharePhoto) {
            return afVar != null ? afVar.a((SharePhoto) obj) : null;
        } else {
            if (obj instanceof ShareOpenGraphObject) {
                return a((ShareOpenGraphObject) obj, afVar);
            }
            if (obj instanceof List) {
                return a((List) obj, afVar);
            }
            StringBuilder stringBuilder = new StringBuilder("Invalid object found for JSON serialization: ");
            stringBuilder.append(obj.toString());
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
