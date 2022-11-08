package com.google.android.exoplayer2;

public final class i extends IllegalStateException {
    public final r a;
    public final int b;
    public final long c;

    public i(r timeline, int windowIndex, long positionMs) {
        this.a = timeline;
        this.b = windowIndex;
        this.c = positionMs;
    }
}
