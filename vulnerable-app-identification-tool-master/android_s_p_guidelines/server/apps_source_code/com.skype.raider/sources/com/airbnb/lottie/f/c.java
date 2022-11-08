package com.airbnb.lottie.f;

import android.support.annotation.Nullable;
import com.airbnb.lottie.a.b.a;

public final class c<T> {
    @Nullable
    a<?, ?> a;
    @Nullable
    protected T b = null;
    private final b<T> c = new b();

    public c(@Nullable T staticValue) {
        this.b = staticValue;
    }

    public final T a(float startFrame, float endFrame, T startValue, T endValue, float linearKeyframeProgress, float interpolatedKeyframeProgress, float overallProgress) {
        this.c.a(startFrame, endFrame, startValue, endValue, linearKeyframeProgress, interpolatedKeyframeProgress, overallProgress);
        return this.b;
    }

    public final void a(@Nullable a<?, ?> animation) {
        this.a = animation;
    }
}
