package com.facebook.imagepipeline.a.a;

public final class b {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final a f;
    public final b g;

    public enum a {
        BLEND_WITH_PREVIOUS,
        NO_BLEND
    }

    public enum b {
        DISPOSE_DO_NOT,
        DISPOSE_TO_BACKGROUND,
        DISPOSE_TO_PREVIOUS
    }

    public b(int frameNumber, int xOffset, int yOffset, int width, int height, a blendOperation, b disposalMethod) {
        this.a = frameNumber;
        this.b = xOffset;
        this.c = yOffset;
        this.d = width;
        this.e = height;
        this.f = blendOperation;
        this.g = disposalMethod;
    }
}
