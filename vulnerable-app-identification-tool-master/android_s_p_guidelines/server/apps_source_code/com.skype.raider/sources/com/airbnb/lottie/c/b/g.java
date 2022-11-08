package com.airbnb.lottie.c.b;

import com.airbnb.lottie.c.a.d;
import com.airbnb.lottie.c.a.h;

public final class g {
    private final a a;
    private final h b;
    private final d c;

    public enum a {
        MaskModeAdd,
        MaskModeSubtract,
        MaskModeIntersect
    }

    public g(a maskMode, h maskPath, d opacity) {
        this.a = maskMode;
        this.b = maskPath;
        this.c = opacity;
    }

    public final a a() {
        return this.a;
    }

    public final h b() {
        return this.b;
    }

    public final d c() {
        return this.c;
    }
}
