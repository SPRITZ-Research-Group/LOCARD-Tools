package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import defpackage.bdt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class d {
    public final long a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final long e;
    public final List<c> f;
    public final boolean g;
    public final long h;
    public final int i;
    public final int j;
    public final int k;

    private d(long j, boolean z, boolean z2, boolean z3, List<c> list, long j2, boolean z4, long j3, int i, int i2, int i3) {
        this.a = j;
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.f = Collections.unmodifiableList(list);
        this.e = j2;
        this.g = z4;
        this.h = j3;
        this.i = i;
        this.j = i2;
        this.k = i3;
    }

    private d(Parcel parcel) {
        this.a = parcel.readLong();
        boolean z = false;
        this.b = parcel.readByte() == (byte) 1;
        this.c = parcel.readByte() == (byte) 1;
        this.d = parcel.readByte() == (byte) 1;
        int readInt = parcel.readInt();
        List arrayList = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            arrayList.add(new c(parcel.readInt(), parcel.readLong()));
        }
        this.f = Collections.unmodifiableList(arrayList);
        this.e = parcel.readLong();
        if (parcel.readByte() == (byte) 1) {
            z = true;
        }
        this.g = z;
        this.h = parcel.readLong();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
    }

    static /* synthetic */ d a(bdt bdt) {
        List list;
        long j;
        boolean z;
        boolean z2;
        int i;
        int i2;
        int i3;
        long j2;
        long k = bdt.k();
        boolean z3 = false;
        boolean z4 = (bdt.g() & 128) != 0;
        ArrayList arrayList = new ArrayList();
        if (z4) {
            list = arrayList;
            j = -9223372036854775807L;
            z = false;
            z2 = false;
            i = 0;
            i2 = 0;
            i3 = 0;
            j2 = -9223372036854775807L;
        } else {
            int g = bdt.g();
            z = (g & 128) != 0;
            z2 = (g & 64) != 0;
            Object obj = (g & 32) != 0 ? 1 : null;
            long k2 = z2 ? bdt.k() : -9223372036854775807L;
            if (!z2) {
                int g2 = bdt.g();
                ArrayList arrayList2 = new ArrayList(g2);
                for (int i4 = 0; i4 < g2; i4++) {
                    arrayList2.add(new c(bdt.g(), bdt.k(), (byte) 0));
                }
                arrayList = arrayList2;
            }
            if (obj != null) {
                long g3 = (long) bdt.g();
                if ((128 & g3) != 0) {
                    z3 = true;
                }
                j2 = ((((g3 & 1) << 32) | bdt.k()) * 1000) / 90;
            } else {
                j2 = -9223372036854775807L;
            }
            int h = bdt.h();
            int g4 = bdt.g();
            i3 = bdt.g();
            j = k2;
            i = h;
            i2 = g4;
            list = arrayList;
            boolean z5 = z;
            z = z3;
            z3 = z5;
        }
        return new d(k, z4, z3, z2, list, j, z, j2, i, i2, i3);
    }
}
