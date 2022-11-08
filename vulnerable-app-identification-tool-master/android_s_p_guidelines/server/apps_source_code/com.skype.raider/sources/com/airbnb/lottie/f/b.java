package com.airbnb.lottie.f;

public final class b<T> {
    private float a;
    private float b;
    private T c;
    private T d;
    private float e;
    private float f;
    private float g;

    public final b<T> a(float startFrame, float endFrame, T startValue, T endValue, float linearKeyframeProgress, float interpolatedKeyframeProgress, float overallProgress) {
        this.a = startFrame;
        this.b = endFrame;
        this.c = startValue;
        this.d = endValue;
        this.e = linearKeyframeProgress;
        this.f = interpolatedKeyframeProgress;
        this.g = overallProgress;
        return this;
    }
}
