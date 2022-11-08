package com.airbnb.lottie.c.b;

import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.o;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.l;
import com.airbnb.lottie.c.c.a;

public final class k implements b {
    private final String a;
    private final b b;
    private final b c;
    private final l d;

    public k(String name, b copies, b offset, l transform) {
        this.a = name;
        this.b = copies;
        this.c = offset;
        this.d = transform;
    }

    public final String a() {
        return this.a;
    }

    public final b b() {
        return this.b;
    }

    public final b c() {
        return this.c;
    }

    public final l d() {
        return this.d;
    }

    @Nullable
    public final com.airbnb.lottie.a.a.b a(LottieDrawable drawable, a layer) {
        return new o(drawable, layer, this);
    }
}
