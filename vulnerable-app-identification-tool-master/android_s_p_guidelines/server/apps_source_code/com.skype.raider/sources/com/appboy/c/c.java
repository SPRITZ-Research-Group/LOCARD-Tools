package com.appboy.c;

import a.a.cv;
import com.appboy.e.b;
import org.json.JSONObject;

public final class c {
    private static final String a = com.appboy.f.c.a(c.class);
    private final b b;
    private final String c;

    public c(b inAppMessage, String userId) {
        this.c = userId;
        if (inAppMessage == null) {
            throw new NullPointerException();
        }
        this.b = inAppMessage;
    }

    public final b a() {
        return this.b;
    }

    public final String toString() {
        return cv.a((JSONObject) this.b.h());
    }
}
