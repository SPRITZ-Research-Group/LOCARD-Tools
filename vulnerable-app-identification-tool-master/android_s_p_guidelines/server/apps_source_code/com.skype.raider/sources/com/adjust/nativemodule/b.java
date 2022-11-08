package com.adjust.nativemodule;

import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustFactory;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

final class b {
    public static Map<String, Object> a(@Nullable am readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        if (!iterator.hasNextKey()) {
            return null;
        }
        Map<String, Object> result = new HashMap();
        while (iterator.hasNextKey()) {
            Object obj;
            String key = iterator.nextKey();
            if (readableMap != null) {
                switch (readableMap.getType(key)) {
                    case Null:
                        obj = null;
                        break;
                    case Boolean:
                        obj = Boolean.valueOf(readableMap.getBoolean(key));
                        break;
                    case Number:
                        double d = readableMap.getDouble(key);
                        if (d != ((double) ((int) d))) {
                            obj = Double.valueOf(d);
                            break;
                        }
                        obj = Integer.valueOf((int) d);
                        break;
                    case String:
                        obj = readableMap.getString(key);
                        break;
                    case Map:
                        obj = a(readableMap.getMap(key));
                        break;
                    case Array:
                        obj = a(readableMap.getArray(key));
                        break;
                    default:
                        AdjustFactory.getLogger().error("Could not convert object with key: " + key + ".", new Object[0]);
                        obj = null;
                        break;
                }
            }
            obj = null;
            String value = obj.toString();
            if (value == null) {
                AdjustFactory.getLogger().warn("Null parameter inside key-value pair with key: " + key, new Object[0]);
            } else {
                result.put(key, value);
            }
        }
        return result;
    }

    private static List<Object> a(@Nullable al readableArray) {
        if (readableArray == null) {
            return null;
        }
        List<Object> result = new ArrayList(readableArray.size());
        for (int index = 0; index < readableArray.size(); index++) {
            switch (readableArray.getType(index)) {
                case Null:
                    break;
                case Boolean:
                    result.add(Boolean.valueOf(readableArray.getBoolean(index)));
                    break;
                case Number:
                    double tmp = readableArray.getDouble(index);
                    if (tmp != ((double) ((int) tmp))) {
                        result.add(Double.valueOf(tmp));
                        break;
                    }
                    result.add(Integer.valueOf((int) tmp));
                    break;
                case String:
                    result.add(readableArray.getString(index));
                    break;
                case Map:
                    result.add(a(readableArray.getMap(index)));
                    break;
                case Array:
                    result = a(readableArray.getArray(index));
                    break;
                default:
                    AdjustFactory.getLogger().error("Could not convert object with index: " + index + ".", new Object[0]);
                    break;
            }
        }
        return result;
    }

    public static ar a(AdjustAttribution attribution) {
        ar map = new WritableNativeMap();
        if (attribution != null) {
            String str;
            map.putString("trackerToken", attribution.trackerToken != null ? attribution.trackerToken : "");
            map.putString("trackerName", attribution.trackerName != null ? attribution.trackerName : "");
            map.putString("network", attribution.network != null ? attribution.network : "");
            map.putString("campaign", attribution.campaign != null ? attribution.campaign : "");
            map.putString("adgroup", attribution.adgroup != null ? attribution.adgroup : "");
            map.putString("creative", attribution.creative != null ? attribution.creative : "");
            map.putString("clickLabel", attribution.clickLabel != null ? attribution.clickLabel : "");
            String str2 = "adid";
            if (attribution.adid != null) {
                str = attribution.adid;
            } else {
                str = "";
            }
            map.putString(str2, str);
        }
        return map;
    }
}
