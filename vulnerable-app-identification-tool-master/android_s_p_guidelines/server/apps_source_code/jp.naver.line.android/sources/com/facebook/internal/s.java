package com.facebook.internal;

import java.util.EnumSet;
import java.util.Map;
import org.json.JSONArray;

public final class s {
    private boolean a;
    private String b;
    private boolean c;
    private boolean d;
    private int e;
    private EnumSet<bh> f;
    private Map<String, Map<String, t>> g;
    private boolean h;
    private p i;
    private String j;
    private String k;
    private boolean l;
    private boolean m;
    private String n;
    private JSONArray o;

    public s(boolean z, String str, boolean z2, boolean z3, int i, EnumSet<bh> enumSet, Map<String, Map<String, t>> map, boolean z4, p pVar, String str2, String str3, boolean z5, boolean z6, JSONArray jSONArray, String str4) {
        this.a = z;
        this.b = str;
        this.c = z2;
        this.d = z3;
        this.g = map;
        this.i = pVar;
        this.e = i;
        this.h = z4;
        this.f = enumSet;
        this.j = str2;
        this.k = str3;
        this.l = z5;
        this.m = z6;
        this.o = jSONArray;
        this.n = str4;
    }

    public final boolean a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final boolean c() {
        return this.c;
    }

    public final boolean d() {
        return this.d;
    }

    public final int e() {
        return this.e;
    }

    public final boolean f() {
        return this.h;
    }

    public final EnumSet<bh> g() {
        return this.f;
    }

    public final p h() {
        return this.i;
    }

    public final boolean i() {
        return this.l;
    }

    public final boolean j() {
        return this.m;
    }

    public final JSONArray k() {
        return this.o;
    }

    public final String l() {
        return this.n;
    }

    public static t a(String str, String str2, String str3) {
        if (bj.a(str2) || bj.a(str3)) {
            return null;
        }
        s a = u.a(str);
        if (a != null) {
            Map map = (Map) a.g.get(str2);
            if (map != null) {
                return (t) map.get(str3);
            }
        }
        return null;
    }
}
