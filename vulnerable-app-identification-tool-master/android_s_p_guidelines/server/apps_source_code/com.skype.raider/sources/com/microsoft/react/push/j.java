package com.microsoft.react.push;

import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class j {
    static String a(am reactObj) throws JSONException {
        return b(reactObj).toString();
    }

    static ar a(String str) throws JSONException {
        return a(new JSONObject(str));
    }

    private static JSONObject b(am reactObj) throws JSONException {
        JSONObject json = new JSONObject();
        ReadableMapKeySetIterator it = reactObj.keySetIterator();
        while (it.hasNextKey()) {
            String key = it.nextKey();
            switch (reactObj.getType(key)) {
                case String:
                    json.put(key, reactObj.getString(key));
                    break;
                case Number:
                    json.put(key, reactObj.getDouble(key));
                    break;
                case Map:
                    json.put(key, b(reactObj.getMap(key)));
                    break;
                case Array:
                    json.put(key, a(reactObj.getArray(key)));
                    break;
                case Boolean:
                    json.put(key, reactObj.getBoolean(key));
                    break;
                case Null:
                    json.put(key, JSONObject.NULL);
                    break;
                default:
                    break;
            }
        }
        return json;
    }

    private static JSONArray a(al reactArr) throws JSONException {
        JSONArray json = new JSONArray();
        for (int i = 0; i < reactArr.size(); i++) {
            switch (reactArr.getType(i)) {
                case String:
                    json.put(reactArr.getString(i));
                    break;
                case Number:
                    json.put(reactArr.getDouble(i));
                    break;
                case Map:
                    json.put(b(reactArr.getMap(i)));
                    break;
                case Array:
                    json.put(a(reactArr.getArray(i)));
                    break;
                case Boolean:
                    json.put(reactArr.getBoolean(i));
                    break;
                case Null:
                    json.put(JSONObject.NULL);
                    break;
                default:
                    break;
            }
        }
        return json;
    }

    private static ar a(JSONObject jsonObject) throws JSONException {
        ar reactObj = new WritableNativeMap();
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = jsonObject.get(key);
            if (value instanceof String) {
                reactObj.putString(key, jsonObject.getString(key));
            } else if ((value instanceof Float) || (value instanceof Double)) {
                reactObj.putDouble(key, jsonObject.getDouble(key));
            } else if (value instanceof Number) {
                reactObj.putDouble(key, (double) jsonObject.getLong(key));
            } else if (value instanceof JSONObject) {
                reactObj.putMap(key, a(jsonObject.getJSONObject(key)));
            } else if (value instanceof JSONArray) {
                reactObj.putArray(key, a(jsonObject.getJSONArray(key)));
            } else if (value instanceof Boolean) {
                reactObj.putBoolean(key, jsonObject.getBoolean(key));
            } else if (value == JSONObject.NULL) {
                reactObj.putNull(key);
            }
        }
        return reactObj;
    }

    private static aq a(JSONArray jsonArray) throws JSONException {
        aq reactArray = new WritableNativeArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof String) {
                reactArray.pushString(jsonArray.getString(i));
            } else if ((value instanceof Float) || (value instanceof Double)) {
                reactArray.pushDouble(jsonArray.getDouble(i));
            } else if (value instanceof Number) {
                reactArray.pushDouble((double) jsonArray.getLong(i));
            } else if (value instanceof JSONObject) {
                reactArray.pushMap(a(jsonArray.getJSONObject(i)));
            } else if (value instanceof JSONArray) {
                reactArray.pushArray(a(jsonArray.getJSONArray(i)));
            } else if (value instanceof Boolean) {
                reactArray.pushBoolean(jsonArray.getBoolean(i));
            } else if (value == JSONObject.NULL) {
                reactArray.pushNull();
            }
        }
        return reactArray;
    }
}
