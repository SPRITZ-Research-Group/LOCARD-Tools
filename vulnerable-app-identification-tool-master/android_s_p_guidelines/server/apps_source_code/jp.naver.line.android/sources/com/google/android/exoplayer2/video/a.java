package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.y;
import defpackage.bdb;
import defpackage.bdp;
import defpackage.bdr;
import defpackage.bdt;
import java.util.ArrayList;
import java.util.List;

public final class a {
    public final List<byte[]> a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    public static a a(bdt bdt) throws y {
        try {
            bdt.d(4);
            int g = (bdt.g() & 3) + 1;
            if (g != 3) {
                int i;
                int i2;
                float f;
                int i3;
                List arrayList = new ArrayList();
                int g2 = bdt.g() & 31;
                for (i = 0; i < g2; i++) {
                    arrayList.add(b(bdt));
                }
                i = bdt.g();
                for (i2 = 0; i2 < i; i2++) {
                    arrayList.add(b(bdt));
                }
                if (g2 > 0) {
                    bdr a = bdp.a((byte[]) arrayList.get(0), g, ((byte[]) arrayList.get(0)).length);
                    g2 = a.e;
                    int i4 = a.f;
                    f = a.g;
                    i2 = g2;
                    i3 = i4;
                } else {
                    i2 = -1;
                    i3 = -1;
                    f = 1.0f;
                }
                return new a(arrayList, g, i2, i3, f);
            }
            throw new IllegalStateException();
        } catch (Throwable e) {
            throw new y("Error parsing AVC config", e);
        }
    }

    private a(List<byte[]> list, int i, int i2, int i3, float f) {
        this.a = list;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = f;
    }

    private static byte[] b(bdt bdt) {
        int h = bdt.h();
        int d = bdt.d();
        bdt.d(h);
        return bdb.a(bdt.a, d, h);
    }
}
