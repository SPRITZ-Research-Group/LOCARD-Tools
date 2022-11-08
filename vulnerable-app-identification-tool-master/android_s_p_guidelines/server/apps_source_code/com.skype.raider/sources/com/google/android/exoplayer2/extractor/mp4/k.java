package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.s;

final class k {
    public final int a;
    public final long[] b;
    public final int[] c;
    public final int d;
    public final long[] e;
    public final int[] f;

    public k(long[] offsets, int[] sizes, int maximumSize, long[] timestampsUs, int[] flags) {
        boolean z;
        boolean z2 = true;
        a.a(sizes.length == timestampsUs.length);
        if (offsets.length == timestampsUs.length) {
            z = true;
        } else {
            z = false;
        }
        a.a(z);
        if (flags.length != timestampsUs.length) {
            z2 = false;
        }
        a.a(z2);
        this.b = offsets;
        this.c = sizes;
        this.d = maximumSize;
        this.e = timestampsUs;
        this.f = flags;
        this.a = offsets.length;
    }

    public final int a(long timeUs) {
        for (int i = s.a(this.e, timeUs, false); i >= 0; i--) {
            if ((this.f[i] & 1) != 0) {
                return i;
            }
        }
        return -1;
    }

    public final int b(long timeUs) {
        for (int i = s.a(this.e, timeUs, true, false); i < this.e.length; i++) {
            if ((this.f[i] & 1) != 0) {
                return i;
            }
        }
        return -1;
    }
}
