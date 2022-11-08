package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;

public final class b {
    public final int a;
    public final long b;
    public final long c;

    /* synthetic */ b(int i, long j, long j2, byte b) {
        this(i, j, j2);
    }

    private b(int i, long j, long j2) {
        this.a = i;
        this.b = j;
        this.c = j2;
    }

    public static b a(Parcel parcel) {
        return new b(parcel.readInt(), parcel.readLong(), parcel.readLong());
    }
}
