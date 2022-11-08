package com.facebook.react.bridge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class s implements am, ar {
    private final Map a;

    public static s a(Object... keysAndValues) {
        return new s(keysAndValues);
    }

    private s(Object... keysAndValues) {
        if (keysAndValues.length % 2 != 0) {
            throw new IllegalArgumentException("You must provide the same number of keys and values");
        }
        this.a = new HashMap();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            this.a.put(keysAndValues[i], keysAndValues[i + 1]);
        }
    }

    public s() {
        this.a = new HashMap();
    }

    public final boolean hasKey(String name) {
        return this.a.containsKey(name);
    }

    public final boolean isNull(String name) {
        return this.a.get(name) == null;
    }

    public final boolean getBoolean(String name) {
        return ((Boolean) this.a.get(name)).booleanValue();
    }

    public final double getDouble(String name) {
        return ((Double) this.a.get(name)).doubleValue();
    }

    public final int getInt(String name) {
        return ((Integer) this.a.get(name)).intValue();
    }

    public final String getString(String name) {
        return (String) this.a.get(name);
    }

    public final g getDynamic(String name) {
        return i.a(this, name);
    }

    public final ReadableType getType(String name) {
        Object value = this.a.get(name);
        if (value == null) {
            return ReadableType.Null;
        }
        if (value instanceof Number) {
            return ReadableType.Number;
        }
        if (value instanceof String) {
            return ReadableType.String;
        }
        if (value instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if (value instanceof am) {
            return ReadableType.Map;
        }
        if (value instanceof al) {
            return ReadableType.Array;
        }
        if (value instanceof g) {
            return ((g) value).d();
        }
        throw new IllegalArgumentException("Invalid value " + value.toString() + " for key " + name + "contained in JavaOnlyMap");
    }

    public final ReadableMapKeySetIterator keySetIterator() {
        return new ReadableMapKeySetIterator(this) {
            Iterator<String> a = this.b.a.keySet().iterator();
            final /* synthetic */ s b;

            {
                this.b = this$0;
            }

            public final boolean hasNextKey() {
                return this.a.hasNext();
            }

            public final String nextKey() {
                return (String) this.a.next();
            }
        };
    }

    public final void putBoolean(String key, boolean value) {
        this.a.put(key, Boolean.valueOf(value));
    }

    public final void putDouble(String key, double value) {
        this.a.put(key, Double.valueOf(value));
    }

    public final void putInt(String key, int value) {
        this.a.put(key, Integer.valueOf(value));
    }

    public final void putString(String key, String value) {
        this.a.put(key, value);
    }

    public final void putNull(String key) {
        this.a.put(key, null);
    }

    public final void putMap(String key, ar value) {
        this.a.put(key, value);
    }

    public final void merge(am source) {
        this.a.putAll(((s) source).a);
    }

    public final void putArray(String key, aq value) {
        this.a.put(key, value);
    }

    public final HashMap<String, Object> toHashMap() {
        return new HashMap(this.a);
    }

    public final String toString() {
        return this.a.toString();
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        s that = (s) o;
        if (this.a != null) {
            if (this.a.equals(that.a)) {
                return true;
            }
        } else if (that.a == null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.a != null ? this.a.hashCode() : 0;
    }

    public final /* synthetic */ am getMap(String str) {
        return (s) this.a.get(str);
    }

    public final /* synthetic */ al getArray(String str) {
        return (r) this.a.get(str);
    }
}
