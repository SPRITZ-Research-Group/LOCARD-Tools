package com.airbnb.lottie.f;

import android.graphics.PointF;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.animation.Interpolator;
import com.airbnb.lottie.e;

public class a<T> {
    @Nullable
    public final T a;
    @Nullable
    public final T b;
    @Nullable
    public final Interpolator c;
    public final float d;
    @Nullable
    public Float e;
    public PointF f;
    public PointF g;
    @Nullable
    private final e h;
    private float i;
    private float j;

    public a(e composition, @Nullable T startValue, @Nullable T endValue, @Nullable Interpolator interpolator, float startFrame, @Nullable Float endFrame) {
        this.i = Float.MIN_VALUE;
        this.j = Float.MIN_VALUE;
        this.f = null;
        this.g = null;
        this.h = composition;
        this.a = startValue;
        this.b = endValue;
        this.c = interpolator;
        this.d = startFrame;
        this.e = endFrame;
    }

    public a(T value) {
        this.i = Float.MIN_VALUE;
        this.j = Float.MIN_VALUE;
        this.f = null;
        this.g = null;
        this.h = null;
        this.a = value;
        this.b = value;
        this.c = null;
        this.d = Float.MIN_VALUE;
        this.e = Float.valueOf(Float.MAX_VALUE);
    }

    public final float b() {
        if (this.h == null) {
            return 0.0f;
        }
        if (this.i == Float.MIN_VALUE) {
            this.i = (this.d - this.h.d()) / this.h.k();
        }
        return this.i;
    }

    public final float c() {
        if (this.h == null) {
            return 1.0f;
        }
        if (this.j == Float.MIN_VALUE) {
            if (this.e == null) {
                this.j = 1.0f;
            } else {
                this.j = b() + ((this.e.floatValue() - this.d) / this.h.k());
            }
        }
        return this.j;
    }

    public final boolean d() {
        return this.c == null;
    }

    public final boolean a(@FloatRange(from = 0.0d, to = 1.0d) float progress) {
        return progress >= b() && progress < c();
    }

    public String toString() {
        return "Keyframe{startValue=" + this.a + ", endValue=" + this.b + ", startFrame=" + this.d + ", endFrame=" + this.e + ", interpolator=" + this.c + '}';
    }
}
