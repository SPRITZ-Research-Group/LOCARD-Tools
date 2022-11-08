package com.appboy.e.b;

import android.support.annotation.VisibleForTesting;
import com.appboy.e.e;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.f.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public final class a implements e<JSONObject> {
    private static final String a = c.a(a.class);
    private JSONObject b = new JSONObject();

    public a(JSONObject jsonObject) {
        this.b = jsonObject;
        List<String> arrayList = new ArrayList();
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            arrayList.add(keys.next());
        }
        for (String str : arrayList) {
            if (a(str)) {
                try {
                    if (jsonObject.get(str) instanceof String) {
                        if (!b(jsonObject.getString(str))) {
                            this.b.remove(str);
                        }
                    } else if (jsonObject.get(str) == JSONObject.NULL) {
                        this.b.remove(str);
                    }
                } catch (Throwable e) {
                    c.d(a, "Caught json exception validating property with key name: " + str, e);
                }
            } else {
                this.b.remove(str);
            }
        }
    }

    public final a a(String key, int value) {
        if (a(key)) {
            try {
                this.b.put(j.c(key), value);
            } catch (Throwable e) {
                c.d(a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final a a(String key, double value) {
        if (a(key)) {
            try {
                this.b.put(j.c(key), value);
            } catch (Throwable e) {
                c.d(a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final a a(String key, boolean value) {
        if (a(key)) {
            try {
                this.b.put(j.c(key), value);
            } catch (Throwable e) {
                c.d(a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final a a(String key, String value) {
        if (a(key) && b(value)) {
            try {
                this.b.put(j.c(key), j.c(value));
            } catch (Throwable e) {
                c.d(a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final int a() {
        return this.b.length();
    }

    @VisibleForTesting
    private static boolean a(String str) {
        if (i.c(str)) {
            c.f(a, "The AppboyProperties key cannot be null or contain only whitespaces. Not adding property.");
            return false;
        } else if (!str.startsWith("$")) {
            return true;
        } else {
            c.f(a, "The leading character in the key string may not be '$'. Not adding property.");
            return false;
        }
    }

    @VisibleForTesting
    private static boolean b(String str) {
        if (!i.c(str)) {
            return true;
        }
        c.f(a, "The AppboyProperties value cannot be null or contain only whitespaces. Not adding property.");
        return false;
    }

    public final JSONObject b() {
        return this.b;
    }

    public final /* bridge */ /* synthetic */ Object h() {
        return this.b;
    }
}
