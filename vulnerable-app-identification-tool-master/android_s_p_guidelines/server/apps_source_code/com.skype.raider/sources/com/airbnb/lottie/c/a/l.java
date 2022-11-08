package com.airbnb.lottie.c.a;

import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.o;
import com.airbnb.lottie.c.b.b;
import com.airbnb.lottie.c.c.a;

public final class l implements b {
    private final e a;
    private final m<PointF, PointF> b;
    private final g c;
    private final b d;
    private final d e;
    @Nullable
    private final b f;
    @Nullable
    private final b g;

    public l() {
        this(new e(), new e(), new g(), new b(), new d(), new b(), new b());
    }

    public l(e anchorPoint, m<PointF, PointF> position, g scale, b rotation, d opacity, @Nullable b startOpacity, @Nullable b endOpacity) {
        this.a = anchorPoint;
        this.b = position;
        this.c = scale;
        this.d = rotation;
        this.e = opacity;
        this.f = startOpacity;
        this.g = endOpacity;
    }

    public final e a() {
        return this.a;
    }

    public final m<PointF, PointF> b() {
        return this.b;
    }

    public final g c() {
        return this.c;
    }

    public final b d() {
        return this.d;
    }

    public final d e() {
        return this.e;
    }

    @Nullable
    public final b f() {
        return this.f;
    }

    @Nullable
    public final b g() {
        return this.g;
    }

    public final o h() {
        return new o(this);
    }

    @Nullable
    public final com.airbnb.lottie.a.a.b a(LottieDrawable drawable, a layer) {
        return null;
    }
}
