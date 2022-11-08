package com.google.android.exoplayer2.extractor.c;

final class b {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private long g;
    private long h;

    public b(int numChannels, int sampleRateHz, int averageBytesPerSecond, int blockAlignment, int bitsPerSample, int encoding) {
        this.a = numChannels;
        this.b = sampleRateHz;
        this.c = averageBytesPerSecond;
        this.d = blockAlignment;
        this.e = bitsPerSample;
        this.f = encoding;
    }

    public final long a() {
        return ((this.h / ((long) this.d)) * 1000000) / ((long) this.b);
    }

    public final int b() {
        return this.d;
    }

    public final int c() {
        return (this.b * this.e) * this.a;
    }

    public final int d() {
        return this.b;
    }

    public final int e() {
        return this.a;
    }

    public final long a(long timeUs) {
        return Math.min((((((long) this.c) * timeUs) / 1000000) / ((long) this.d)) * ((long) this.d), this.h - ((long) this.d)) + this.g;
    }

    public final long b(long position) {
        return (1000000 * position) / ((long) this.c);
    }

    public final boolean f() {
        return (this.g == 0 || this.h == 0) ? false : true;
    }

    public final void a(long dataStartPosition, long dataSize) {
        this.g = dataStartPosition;
        this.h = dataSize;
    }

    public final int g() {
        return this.f;
    }
}
