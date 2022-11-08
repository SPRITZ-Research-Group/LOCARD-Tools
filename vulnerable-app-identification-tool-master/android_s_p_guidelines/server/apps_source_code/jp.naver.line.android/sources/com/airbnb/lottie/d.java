package com.airbnb.lottie;

import android.graphics.Rect;
import android.util.Log;
import defpackage.by;
import defpackage.ch;
import defpackage.tk;
import defpackage.tl;
import defpackage.ve;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public final class d {
    private final m a = new m();
    private final HashSet<String> b = new HashSet();
    private Map<String, List<ve>> c;
    private Map<String, h> d;
    private Map<String, tk> e;
    private ch<tl> f;
    private by<ve> g;
    private List<ve> h;
    private Rect i;
    private float j;
    private float k;
    private float l;

    public final void a(Rect rect, float f, float f2, float f3, List<ve> list, by<ve> byVar, Map<String, List<ve>> map, Map<String, h> map2, ch<tl> chVar, Map<String, tk> map3) {
        this.i = rect;
        this.j = f;
        this.k = f2;
        this.l = f3;
        this.h = list;
        this.g = byVar;
        this.c = map;
        this.d = map2;
        this.f = chVar;
        this.e = map3;
    }

    public final void a(String str) {
        Log.w("LOTTIE", str);
        this.b.add(str);
    }

    public final void a(boolean z) {
        this.a.a(z);
    }

    public final m a() {
        return this.a;
    }

    public final ve a(long j) {
        return (ve) this.g.a(j);
    }

    public final Rect b() {
        return this.i;
    }

    public final float c() {
        return (float) ((long) ((k() / this.l) * 1000.0f));
    }

    public final float d() {
        return this.j;
    }

    public final float e() {
        return this.k;
    }

    public final float f() {
        return this.l;
    }

    public final List<ve> g() {
        return this.h;
    }

    public final List<ve> b(String str) {
        return (List) this.c.get(str);
    }

    public final ch<tl> h() {
        return this.f;
    }

    public final Map<String, tk> i() {
        return this.e;
    }

    public final Map<String, h> j() {
        return this.d;
    }

    public final float k() {
        return this.k - this.j;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("LottieComposition:\n");
        for (ve a : this.h) {
            stringBuilder.append(a.a("\t"));
        }
        return stringBuilder.toString();
    }
}
