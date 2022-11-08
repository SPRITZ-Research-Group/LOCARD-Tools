package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.source.TrackGroupArray;

public final class i {
    @Deprecated
    public final int a = this.b;
    private final int b;
    private final int[] c;
    private final TrackGroupArray[] d;
    private final int[] e;
    private final int[][][] f;
    private final TrackGroupArray g;

    i(int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
        this.c = iArr;
        this.d = trackGroupArrayArr;
        this.f = iArr3;
        this.e = iArr2;
        this.g = trackGroupArray;
        this.b = iArr.length;
    }

    public final int a() {
        return this.b;
    }

    public final int a(int i) {
        return this.c[i];
    }

    public final TrackGroupArray b(int i) {
        return this.d[i];
    }
}
