package com.facebook.react.bridge;

import android.os.Bundle;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public final class b {
    private static Object b(final Object object) {
        if (object == null) {
            return null;
        }
        if ((object instanceof Float) || (object instanceof Long) || (object instanceof Byte) || (object instanceof Short)) {
            return new Double(((Number) object).doubleValue());
        }
        if (object.getClass().isArray()) {
            if (object == null) {
                return new WritableNativeArray();
            }
            return a(new AbstractList() {
                public final int size() {
                    return Array.getLength(object);
                }

                public final Object get(int index) {
                    return Array.get(object, index);
                }
            });
        } else if (object instanceof List) {
            return a((List) object);
        } else {
            if (object instanceof Map) {
                return a((Map) object);
            }
            if (object instanceof Bundle) {
                return b((Bundle) object);
            }
            return object;
        }
    }

    private static WritableNativeArray a(List objects) {
        WritableNativeArray nativeArray = new WritableNativeArray();
        if (objects != null) {
            for (Object b : objects) {
                Object elem = b(b);
                if (elem == null) {
                    nativeArray.pushNull();
                } else if (elem instanceof Boolean) {
                    nativeArray.pushBoolean(((Boolean) elem).booleanValue());
                } else if (elem instanceof Integer) {
                    nativeArray.pushInt(((Integer) elem).intValue());
                } else if (elem instanceof Double) {
                    nativeArray.pushDouble(((Double) elem).doubleValue());
                } else if (elem instanceof String) {
                    nativeArray.pushString((String) elem);
                } else if (elem instanceof WritableNativeArray) {
                    nativeArray.pushArray((WritableNativeArray) elem);
                } else if (elem instanceof WritableNativeMap) {
                    nativeArray.pushMap((WritableNativeMap) elem);
                } else {
                    throw new IllegalArgumentException("Could not convert " + elem.getClass());
                }
            }
        }
        return nativeArray;
    }

    private static void a(WritableNativeMap nativeMap, String key, Object value) {
        value = b(value);
        if (value == null) {
            nativeMap.putNull(key);
        } else if (value instanceof Boolean) {
            nativeMap.putBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof Integer) {
            nativeMap.putInt(key, ((Integer) value).intValue());
        } else if (value instanceof Number) {
            nativeMap.putDouble(key, ((Number) value).doubleValue());
        } else if (value instanceof String) {
            nativeMap.putString(key, (String) value);
        } else if (value instanceof WritableNativeArray) {
            nativeMap.putArray(key, (WritableNativeArray) value);
        } else if (value instanceof WritableNativeMap) {
            nativeMap.putMap(key, (WritableNativeMap) value);
        } else {
            throw new IllegalArgumentException("Could not convert " + value.getClass());
        }
    }

    public static WritableNativeMap a(Map<String, Object> objects) {
        WritableNativeMap nativeMap = new WritableNativeMap();
        if (objects != null) {
            for (Entry<String, Object> entry : objects.entrySet()) {
                a(nativeMap, (String) entry.getKey(), entry.getValue());
            }
        }
        return nativeMap;
    }

    private static WritableNativeMap b(Bundle bundle) {
        WritableNativeMap nativeMap = new WritableNativeMap();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                a(nativeMap, key, bundle.get(key));
            }
        }
        return nativeMap;
    }

    public static WritableNativeArray a(Object[] args) {
        WritableNativeArray arguments = new WritableNativeArray();
        for (Object argument : args) {
            if (argument == null) {
                arguments.pushNull();
            } else {
                Class argumentClass = argument.getClass();
                if (argumentClass == Boolean.class) {
                    arguments.pushBoolean(((Boolean) argument).booleanValue());
                } else if (argumentClass == Integer.class) {
                    arguments.pushDouble(((Integer) argument).doubleValue());
                } else if (argumentClass == Double.class) {
                    arguments.pushDouble(((Double) argument).doubleValue());
                } else if (argumentClass == Float.class) {
                    arguments.pushDouble(((Float) argument).doubleValue());
                } else if (argumentClass == String.class) {
                    arguments.pushString(argument.toString());
                } else if (argumentClass == WritableNativeMap.class) {
                    arguments.pushMap((WritableNativeMap) argument);
                } else if (argumentClass == WritableNativeArray.class) {
                    arguments.pushArray((WritableNativeArray) argument);
                } else {
                    throw new RuntimeException("Cannot convert argument of type " + argumentClass);
                }
            }
        }
        return arguments;
    }

    @Nullable
    public static Bundle a(@Nullable am readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        Bundle bundle = new Bundle();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    bundle.putString(key, null);
                    break;
                case Boolean:
                    bundle.putBoolean(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    bundle.putDouble(key, readableMap.getDouble(key));
                    break;
                case String:
                    bundle.putString(key, readableMap.getString(key));
                    break;
                case Map:
                    bundle.putBundle(key, a(readableMap.getMap(key)));
                    break;
                case Array:
                    throw new UnsupportedOperationException("Arrays aren't supported yet.");
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        return bundle;
    }

    public static aq a(Object array) {
        int i = 0;
        aq catalystArray = new WritableNativeArray();
        int length;
        if (array instanceof String[]) {
            String[] strArr = (String[]) array;
            length = strArr.length;
            while (i < length) {
                catalystArray.pushString(strArr[i]);
                i++;
            }
        } else if (array instanceof Bundle[]) {
            Bundle[] bundleArr = (Bundle[]) array;
            length = bundleArr.length;
            while (i < length) {
                catalystArray.pushMap(a(bundleArr[i]));
                i++;
            }
        } else if (array instanceof int[]) {
            int[] iArr = (int[]) array;
            length = iArr.length;
            while (i < length) {
                catalystArray.pushInt(iArr[i]);
                i++;
            }
        } else if (array instanceof float[]) {
            float[] fArr = (float[]) array;
            length = fArr.length;
            while (i < length) {
                catalystArray.pushDouble((double) fArr[i]);
                i++;
            }
        } else if (array instanceof double[]) {
            double[] dArr = (double[]) array;
            length = dArr.length;
            while (i < length) {
                catalystArray.pushDouble(dArr[i]);
                i++;
            }
        } else if (array instanceof boolean[]) {
            boolean[] zArr = (boolean[]) array;
            length = zArr.length;
            while (i < length) {
                catalystArray.pushBoolean(zArr[i]);
                i++;
            }
        } else {
            throw new IllegalArgumentException("Unknown array type " + array.getClass());
        }
        return catalystArray;
    }

    public static ar a(Bundle bundle) {
        ar map = new WritableNativeMap();
        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            if (value == null) {
                map.putNull(key);
            } else if (value.getClass().isArray()) {
                map.putArray(key, a(value));
            } else if (value instanceof String) {
                map.putString(key, (String) value);
            } else if (value instanceof Number) {
                if (value instanceof Integer) {
                    map.putInt(key, ((Integer) value).intValue());
                } else {
                    map.putDouble(key, ((Number) value).doubleValue());
                }
            } else if (value instanceof Boolean) {
                map.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Bundle) {
                map.putMap(key, a((Bundle) value));
            } else {
                throw new IllegalArgumentException("Could not convert " + value.getClass());
            }
        }
        return map;
    }
}
