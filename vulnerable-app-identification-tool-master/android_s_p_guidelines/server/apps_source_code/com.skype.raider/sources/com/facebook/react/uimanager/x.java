package com.facebook.react.uimanager;

import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.g;
import javax.annotation.Nullable;

public final class x {
    final am a;

    public x(am props) {
        this.a = props;
    }

    public final boolean a(String name) {
        return this.a.hasKey(name);
    }

    public final boolean b(String name) {
        return this.a.isNull(name);
    }

    public final boolean a(String name, boolean restoreNullToDefaultValue) {
        return this.a.isNull(name) ? restoreNullToDefaultValue : this.a.getBoolean(name);
    }

    public final double a(String name, double restoreNullToDefaultValue) {
        return this.a.isNull(name) ? restoreNullToDefaultValue : this.a.getDouble(name);
    }

    public final float a(String name, float restoreNullToDefaultValue) {
        if (this.a.isNull(name)) {
            return restoreNullToDefaultValue;
        }
        return (float) this.a.getDouble(name);
    }

    public final int a(String name, int restoreNullToDefaultValue) {
        return this.a.isNull(name) ? restoreNullToDefaultValue : this.a.getInt(name);
    }

    @Nullable
    public final String c(String name) {
        return this.a.getString(name);
    }

    @Nullable
    public final al d(String key) {
        return this.a.getArray(key);
    }

    @Nullable
    public final am e(String key) {
        return this.a.getMap(key);
    }

    @Nullable
    public final g f(String key) {
        return this.a.getDynamic(key);
    }

    public final String toString() {
        return "{ " + getClass().getSimpleName() + ": " + this.a.toString() + " }";
    }
}
