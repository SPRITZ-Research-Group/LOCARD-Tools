package com.airbnb.lottie.a.a;

import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.c.b.q;
import java.util.ArrayList;
import java.util.List;

public final class r implements b, a {
    private final String a;
    private final List<a> b = new ArrayList();
    private final q.a c;
    private final com.airbnb.lottie.a.b.a<?, Float> d;
    private final com.airbnb.lottie.a.b.a<?, Float> e;
    private final com.airbnb.lottie.a.b.a<?, Float> f;

    public r(com.airbnb.lottie.c.c.a layer, q trimPath) {
        this.a = trimPath.a();
        this.c = trimPath.b();
        this.d = trimPath.d().a();
        this.e = trimPath.c().a();
        this.f = trimPath.e().a();
        layer.a(this.d);
        layer.a(this.e);
        layer.a(this.f);
        this.d.a((a) this);
        this.e.a((a) this);
        this.f.a((a) this);
    }

    public final void a() {
        for (int i = 0; i < this.b.size(); i++) {
            ((a) this.b.get(i)).a();
        }
    }

    public final void a(List<b> list, List<b> list2) {
    }

    public final String b() {
        return this.a;
    }

    final void a(a listener) {
        this.b.add(listener);
    }

    final q.a c() {
        return this.c;
    }

    public final com.airbnb.lottie.a.b.a<?, Float> d() {
        return this.d;
    }

    public final com.airbnb.lottie.a.b.a<?, Float> e() {
        return this.e;
    }

    public final com.airbnb.lottie.a.b.a<?, Float> f() {
        return this.f;
    }
}
