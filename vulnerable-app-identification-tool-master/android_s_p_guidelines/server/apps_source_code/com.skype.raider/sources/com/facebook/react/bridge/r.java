package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.List;

public final class r implements al, aq {
    private final List a;

    public static r a(List list) {
        return new r(list);
    }

    private r(List list) {
        this.a = new ArrayList(list);
    }

    public r() {
        this.a = new ArrayList();
    }

    public final int size() {
        return this.a.size();
    }

    public final boolean isNull(int index) {
        return this.a.get(index) == null;
    }

    public final double getDouble(int index) {
        return ((Double) this.a.get(index)).doubleValue();
    }

    public final int getInt(int index) {
        return ((Integer) this.a.get(index)).intValue();
    }

    public final String getString(int index) {
        return (String) this.a.get(index);
    }

    public final boolean getBoolean(int index) {
        return ((Boolean) this.a.get(index)).booleanValue();
    }

    public final ReadableType getType(int index) {
        Object object = this.a.get(index);
        if (object == null) {
            return ReadableType.Null;
        }
        if (object instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if ((object instanceof Double) || (object instanceof Float) || (object instanceof Integer)) {
            return ReadableType.Number;
        }
        if (object instanceof String) {
            return ReadableType.String;
        }
        if (object instanceof al) {
            return ReadableType.Array;
        }
        if (object instanceof am) {
            return ReadableType.Map;
        }
        return null;
    }

    public final void pushBoolean(boolean value) {
        this.a.add(Boolean.valueOf(value));
    }

    public final void pushDouble(double value) {
        this.a.add(Double.valueOf(value));
    }

    public final void pushInt(int value) {
        this.a.add(Integer.valueOf(value));
    }

    public final void pushString(String value) {
        this.a.add(value);
    }

    public final void pushArray(aq array) {
        this.a.add(array);
    }

    public final void pushMap(ar map) {
        this.a.add(map);
    }

    public final void pushNull() {
        this.a.add(null);
    }

    public final ArrayList<Object> toArrayList() {
        return new ArrayList(this.a);
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
        r that = (r) o;
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

    public final /* synthetic */ am getMap(int i) {
        return (s) this.a.get(i);
    }

    public final /* synthetic */ al getArray(int i) {
        return (r) this.a.get(i);
    }
}
