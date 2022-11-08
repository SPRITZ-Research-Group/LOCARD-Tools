package com.facebook.ads.internal.s;

import java.util.HashMap;
import java.util.Map;

public final class b {
    private c a;
    private float b;
    private Map<String, String> c;

    public b(c cVar) {
        this(cVar, 0.0f);
    }

    public b(c cVar, float f) {
        this(cVar, f, null);
    }

    public b(c cVar, float f, Map<String, String> map) {
        this.a = cVar;
        this.b = f;
        if (map != null) {
            this.c = map;
        } else {
            this.c = new HashMap();
        }
    }

    public final boolean a() {
        return this.a == c.IS_VIEWABLE;
    }

    public final int b() {
        return this.a.a();
    }

    public final float c() {
        return this.b;
    }

    public final Map<String, String> d() {
        return this.c;
    }
}
