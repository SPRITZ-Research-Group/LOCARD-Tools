package com.facebook.ads.internal.m;

import android.text.TextUtils;
import com.facebook.ads.internal.q.a.k;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class a {
    private final String a;
    private final double b = (((double) System.currentTimeMillis()) / 1000.0d);
    private final double c;
    private final String d;
    private final Map<String, String> e;
    private final e f;
    private final f g;
    private final boolean h;

    public static class a {
        private String a;
        private double b;
        private String c;
        private Map<String, String> d;
        private e e;
        private f f;
        private boolean g;

        public final a a(double d) {
            this.b = d;
            return this;
        }

        public final a a(e eVar) {
            this.e = eVar;
            return this;
        }

        public final a a(f fVar) {
            this.f = fVar;
            return this;
        }

        public final a a(String str) {
            this.a = str;
            return this;
        }

        public final a a(Map<String, String> map) {
            this.d = map;
            return this;
        }

        public final a a(boolean z) {
            this.g = z;
            return this;
        }

        public final a a() {
            return new a(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        public final a b(String str) {
            this.c = str;
            return this;
        }
    }

    public a(String str, double d, String str2, Map<String, String> map, e eVar, f fVar, boolean z) {
        this.a = str;
        this.c = d;
        this.d = str2;
        this.f = eVar;
        this.g = fVar;
        this.h = z;
        Map hashMap = new HashMap();
        if (!(map == null || map.isEmpty())) {
            hashMap.putAll(map);
        }
        if (f()) {
            hashMap.put("analog", k.a(com.facebook.ads.internal.g.a.a()));
        }
        this.e = a(hashMap);
    }

    private static Map<String, String> a(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 != null) {
                hashMap.put(str, str2);
            }
        }
        return hashMap;
    }

    public final String a() {
        return this.a;
    }

    public final double b() {
        return this.b;
    }

    public final double c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final Map<String, String> e() {
        return this.e;
    }

    final boolean f() {
        return this.f == e.IMMEDIATE;
    }

    final boolean g() {
        return !TextUtils.isEmpty(this.a);
    }

    public final e h() {
        return this.f;
    }

    public final f i() {
        return this.g;
    }
}
