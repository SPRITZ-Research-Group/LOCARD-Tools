package com.facebook.ads.internal.n;

import android.support.annotation.Nullable;
import net.hockeyapp.android.j;
import org.json.JSONObject;

public final class f {
    private final String a;
    private final int b;
    private final int c;

    public f(String str, int i, int i2) {
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    @Nullable
    public static f a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString(j.FRAGMENT_URL);
        return optString != null ? new f(optString, jSONObject.optInt("width", 0), jSONObject.optInt("height", 0)) : null;
    }

    public final String a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final int c() {
        return this.c;
    }
}
