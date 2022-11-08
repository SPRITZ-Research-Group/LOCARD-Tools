package com.airbnb.lottie.c.c;

import android.support.annotation.Nullable;
import com.airbnb.lottie.c.a.j;
import com.airbnb.lottie.c.a.k;
import com.airbnb.lottie.c.a.l;
import com.airbnb.lottie.c.b.g;
import com.airbnb.lottie.e;
import java.util.List;
import java.util.Locale;

public final class d {
    private final List<com.airbnb.lottie.c.b.b> a;
    private final e b;
    private final String c;
    private final long d;
    private final a e;
    private final long f;
    @Nullable
    private final String g;
    private final List<g> h;
    private final l i;
    private final int j;
    private final int k;
    private final int l;
    private final float m;
    private final float n;
    private final int o;
    private final int p;
    @Nullable
    private final j q;
    @Nullable
    private final k r;
    @Nullable
    private final com.airbnb.lottie.c.a.b s;
    private final List<com.airbnb.lottie.f.a<Float>> t;
    private final b u;

    public enum a {
        PreComp,
        Solid,
        Image,
        Null,
        Shape,
        Text,
        Unknown
    }

    public enum b {
        None,
        Add,
        Invert,
        Unknown
    }

    public d(List<com.airbnb.lottie.c.b.b> shapes, e composition, String layerName, long layerId, a layerType, long parentId, @Nullable String refId, List<g> masks, l transform, int solidWidth, int solidHeight, int solidColor, float timeStretch, float startFrame, int preCompWidth, int preCompHeight, @Nullable j text, @Nullable k textProperties, List<com.airbnb.lottie.f.a<Float>> inOutKeyframes, b matteType, @Nullable com.airbnb.lottie.c.a.b timeRemapping) {
        this.a = shapes;
        this.b = composition;
        this.c = layerName;
        this.d = layerId;
        this.e = layerType;
        this.f = parentId;
        this.g = refId;
        this.h = masks;
        this.i = transform;
        this.j = solidWidth;
        this.k = solidHeight;
        this.l = solidColor;
        this.m = timeStretch;
        this.n = startFrame;
        this.o = preCompWidth;
        this.p = preCompHeight;
        this.q = text;
        this.r = textProperties;
        this.t = inOutKeyframes;
        this.u = matteType;
        this.s = timeRemapping;
    }

    final e a() {
        return this.b;
    }

    final float b() {
        return this.m;
    }

    final float c() {
        return this.n / this.b.k();
    }

    final List<com.airbnb.lottie.f.a<Float>> d() {
        return this.t;
    }

    public final long e() {
        return this.d;
    }

    final String f() {
        return this.c;
    }

    @Nullable
    final String g() {
        return this.g;
    }

    final int h() {
        return this.o;
    }

    final int i() {
        return this.p;
    }

    final List<g> j() {
        return this.h;
    }

    public final a k() {
        return this.e;
    }

    final b l() {
        return this.u;
    }

    final long m() {
        return this.f;
    }

    final List<com.airbnb.lottie.c.b.b> n() {
        return this.a;
    }

    final l o() {
        return this.i;
    }

    final int p() {
        return this.l;
    }

    final int q() {
        return this.k;
    }

    final int r() {
        return this.j;
    }

    @Nullable
    final j s() {
        return this.q;
    }

    @Nullable
    final k t() {
        return this.r;
    }

    @Nullable
    final com.airbnb.lottie.c.a.b u() {
        return this.s;
    }

    public final String toString() {
        return a("");
    }

    public final String a(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(this.c).append("\n");
        d parent = this.b.a(this.f);
        if (parent != null) {
            sb.append("\t\tParents: ").append(parent.c);
            parent = this.b.a(parent.f);
            while (parent != null) {
                sb.append("->").append(parent.c);
                parent = this.b.a(parent.f);
            }
            sb.append(prefix).append("\n");
        }
        if (!this.h.isEmpty()) {
            sb.append(prefix).append("\tMasks: ").append(this.h.size()).append("\n");
        }
        if (!(this.j == 0 || this.k == 0)) {
            sb.append(prefix).append("\tBackground: ").append(String.format(Locale.US, "%dx%d %X\n", new Object[]{Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l)}));
        }
        if (!this.a.isEmpty()) {
            sb.append(prefix).append("\tShapes:\n");
            for (Object shape : this.a) {
                sb.append(prefix).append("\t\t").append(shape).append("\n");
            }
        }
        return sb.toString();
    }
}
