package com.microsoft.react.push;

import com.facebook.react.bridge.am;
import java.io.Serializable;

final class b implements d, Serializable {
    private String b;
    private String c;
    private a d;
    private b e;
    private String f;
    private String g;

    enum a {
        FOREGROUND,
        BACKGROUND
    }

    enum b {
        DEFAULT,
        TEXT_INPUT
    }

    private b() {
    }

    static b a(am map) {
        String str = null;
        if (!map.hasKey("identifier") || !map.hasKey("title")) {
            return null;
        }
        String string;
        b info = new b();
        info.b = map.getString("identifier");
        info.c = map.getString("title");
        if (map.hasKey("textInputLabel")) {
            string = map.getString("textInputLabel");
        } else {
            string = null;
        }
        info.f = string;
        int behaviorIndex = map.hasKey("behavior") ? map.getInt("behavior") : -1;
        if (behaviorIndex < 0 || behaviorIndex >= b.values().length) {
            info.e = b.DEFAULT;
        } else {
            info.e = b.values()[behaviorIndex];
        }
        int index = map.getInt("activationMode");
        if (index < 0 || index >= a.values().length) {
            info.d = a.FOREGROUND;
        } else {
            info.d = a.values()[index];
        }
        if (map.hasKey("icon")) {
            str = map.getString("icon");
        }
        info.g = str;
        return info;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    final a c() {
        return this.d;
    }

    final String d() {
        return this.f;
    }

    public final b e() {
        return this.e;
    }

    public final String f() {
        return this.g;
    }
}
