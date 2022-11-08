package com.airbnb.lottie.c.b;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.b;
import com.airbnb.lottie.a.a.c;
import com.airbnb.lottie.c.c.a;
import java.util.Arrays;
import java.util.List;

public final class n implements b {
    private final String a;
    private final List<b> b;

    public n(String name, List<b> items) {
        this.a = name;
        this.b = items;
    }

    public final String a() {
        return this.a;
    }

    public final List<b> b() {
        return this.b;
    }

    public final b a(LottieDrawable drawable, a layer) {
        return new c(drawable, layer, this);
    }

    public final String toString() {
        return "ShapeGroup{name='" + this.a + "' Shapes: " + Arrays.toString(this.b.toArray()) + '}';
    }
}
