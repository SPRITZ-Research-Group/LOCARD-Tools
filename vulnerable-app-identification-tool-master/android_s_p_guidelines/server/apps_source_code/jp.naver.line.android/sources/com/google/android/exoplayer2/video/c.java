package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.y;
import defpackage.bdp;
import defpackage.bdt;
import java.util.Collections;
import java.util.List;

public final class c {
    public final List<byte[]> a;
    public final int b;

    public static c a(bdt bdt) throws y {
        try {
            int i;
            int h;
            List list;
            bdt.d(21);
            int g = bdt.g() & 3;
            int g2 = bdt.g();
            int d = bdt.d();
            int i2 = 0;
            int i3 = 0;
            while (i2 < g2) {
                bdt.d(1);
                int h2 = bdt.h();
                i = i3;
                for (i3 = 0; i3 < h2; i3++) {
                    h = bdt.h();
                    i += h + 4;
                    bdt.d(h);
                }
                i2++;
                i3 = i;
            }
            bdt.c(d);
            Object obj = new byte[i3];
            i2 = 0;
            i = 0;
            while (i2 < g2) {
                bdt.d(1);
                h = bdt.h();
                int i4 = i;
                for (i = 0; i < h; i++) {
                    int h3 = bdt.h();
                    System.arraycopy(bdp.a, 0, obj, i4, bdp.a.length);
                    i4 += bdp.a.length;
                    System.arraycopy(bdt.a, bdt.d(), obj, i4, h3);
                    i4 += h3;
                    bdt.d(h3);
                }
                i2++;
                i = i4;
            }
            if (i3 == 0) {
                list = null;
            } else {
                list = Collections.singletonList(obj);
            }
            return new c(list, g + 1);
        } catch (Throwable e) {
            throw new y("Error parsing HEVC config", e);
        }
    }

    private c(List<byte[]> list, int i) {
        this.a = list;
        this.b = i;
    }
}
