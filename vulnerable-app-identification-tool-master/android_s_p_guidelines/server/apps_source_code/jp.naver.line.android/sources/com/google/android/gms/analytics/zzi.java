package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

@VisibleForTesting
public abstract class zzi<T extends zzi> {
    public abstract void zzb(T t);

    public static String zza(Map map) {
        return zza(map, 1);
    }

    public static String zza(Object obj) {
        return zza(obj, 0);
    }

    private static String zza(Object obj, int i) {
        if (i > 10) {
            return "ERROR: Recursive toString calls";
        }
        if (obj == null) {
            return "";
        }
        StringBuilder stringBuilder;
        if (obj instanceof String) {
            if (TextUtils.isEmpty((String) obj)) {
                return "";
            }
            return obj.toString();
        } else if (obj instanceof Integer) {
            if (((Integer) obj).intValue() == 0) {
                return "";
            }
            return obj.toString();
        } else if (obj instanceof Long) {
            if (((Long) obj).longValue() == 0) {
                return "";
            }
            return obj.toString();
        } else if (obj instanceof Double) {
            if (((Double) obj).doubleValue() == 0.0d) {
                return "";
            }
            return obj.toString();
        } else if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                return obj.toString();
            }
            return "";
        } else if (obj instanceof List) {
            stringBuilder = new StringBuilder();
            if (i > 0) {
                stringBuilder.append("[");
            }
            List list = (List) obj;
            int length = stringBuilder.length();
            for (Object next : list) {
                if (stringBuilder.length() > length) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(zza(next, i + 1));
            }
            if (i > 0) {
                stringBuilder.append("]");
            }
            return stringBuilder.toString();
        } else if (!(obj instanceof Map)) {
            return obj.toString();
        } else {
            stringBuilder = new StringBuilder();
            Object obj2 = null;
            int i2 = 0;
            for (Entry entry : new TreeMap((Map) obj).entrySet()) {
                Object zza = zza(entry.getValue(), i + 1);
                if (!TextUtils.isEmpty(zza)) {
                    if (i > 0 && obj2 == null) {
                        stringBuilder.append("{");
                        i2 = stringBuilder.length();
                        obj2 = 1;
                    }
                    if (stringBuilder.length() > i2) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append((String) entry.getKey());
                    stringBuilder.append('=');
                    stringBuilder.append(zza);
                }
            }
            if (obj2 != null) {
                stringBuilder.append("}");
            }
            return stringBuilder.toString();
        }
    }
}
