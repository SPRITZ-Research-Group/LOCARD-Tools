package com.airbnb.lottie.a.a;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a;
import com.airbnb.lottie.c.b.p;
import com.airbnb.lottie.f.c;
import com.airbnb.lottie.g;

public final class q extends a {
    private final String b;
    private final a<Integer, Integer> c;
    @Nullable
    private a<ColorFilter, ColorFilter> d;

    public q(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, p stroke) {
        super(lottieDrawable, layer, stroke.g().a(), stroke.h().a(), stroke.c(), stroke.d(), stroke.e(), stroke.f());
        this.b = stroke.a();
        this.c = stroke.b().a();
        this.c.a((a.a) this);
        layer.a(this.c);
    }

    public final void a(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.a.setColor(((Integer) this.c.e()).intValue());
        super.a(canvas, parentMatrix, parentAlpha);
    }

    public final String b() {
        return this.b;
    }

    public final <T> void a(T property, @Nullable c<T> callback) {
        super.a((Object) property, (c) callback);
        if (property == g.b) {
            this.c.a((c) callback);
        } else if (property != g.x) {
        } else {
            if (callback == null) {
                this.d = null;
            } else {
                this.d = new com.airbnb.lottie.a.b.p(callback);
            }
        }
    }
}
