package com.microsoft.react.videofxp;

final class r {
    private double a;
    private long b;

    public r(double progress, long estimatedSize) {
        this.a = progress;
        this.b = estimatedSize;
    }

    public final double a() {
        return this.a;
    }

    public final long b() {
        return this.b;
    }
}
