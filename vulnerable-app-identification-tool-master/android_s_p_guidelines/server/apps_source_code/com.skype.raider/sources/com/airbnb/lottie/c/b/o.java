package com.airbnb.lottie.c.b;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.b;
import com.airbnb.lottie.a.a.p;
import com.airbnb.lottie.c.a.h;
import com.airbnb.lottie.c.c.a;

public final class o implements b {
    private final String a;
    private final int b;
    private final h c;

    public o(String name, int index, h shapePath) {
        this.a = name;
        this.b = index;
        this.c = shapePath;
    }

    public final String a() {
        return this.a;
    }

    public final h b() {
        return this.c;
    }

    public final b a(LottieDrawable drawable, a layer) {
        return new p(drawable, layer, this);
    }

    public final String toString() {
        return "ShapePath{name=" + this.a + ", index=" + this.b + '}';
    }
}
