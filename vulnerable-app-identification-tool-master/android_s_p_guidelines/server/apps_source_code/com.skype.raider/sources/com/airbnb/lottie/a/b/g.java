package com.airbnb.lottie.a.b;

import android.graphics.Path;
import com.airbnb.lottie.c.b.l;
import java.util.ArrayList;
import java.util.List;

public final class g {
    private final List<a<l, Path>> a;
    private final List<a<Integer, Integer>> b;
    private final List<com.airbnb.lottie.c.b.g> c;

    public g(List<com.airbnb.lottie.c.b.g> masks) {
        this.c = masks;
        this.a = new ArrayList(masks.size());
        this.b = new ArrayList(masks.size());
        for (int i = 0; i < masks.size(); i++) {
            this.a.add(((com.airbnb.lottie.c.b.g) masks.get(i)).b().a());
            this.b.add(((com.airbnb.lottie.c.b.g) masks.get(i)).c().a());
        }
    }

    public final List<com.airbnb.lottie.c.b.g> a() {
        return this.c;
    }

    public final List<a<l, Path>> b() {
        return this.a;
    }

    public final List<a<Integer, Integer>> c() {
        return this.b;
    }
}
