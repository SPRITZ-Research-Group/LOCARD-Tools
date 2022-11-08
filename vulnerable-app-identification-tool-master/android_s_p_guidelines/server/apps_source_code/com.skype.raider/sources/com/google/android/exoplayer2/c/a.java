package com.google.android.exoplayer2.c;

import com.google.android.exoplayer2.source.j;
import com.google.android.exoplayer2.upstream.d;

public final class a extends b {
    private final d d;
    private final int e;
    private final long f;
    private final long g;
    private final long h;
    private final float i;
    private int j;
    private int k;

    public static final class a implements com.google.android.exoplayer2.c.f.a {
        private final d a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;
        private final float f;

        public a(d bandwidthMeter) {
            this(bandwidthMeter, (byte) 0);
        }

        private a(d bandwidthMeter, byte b) {
            this.a = bandwidthMeter;
            this.b = 800000;
            this.c = 10000;
            this.d = 25000;
            this.e = 25000;
            this.f = 0.75f;
        }

        public final /* synthetic */ f a(j jVar, int[] iArr) {
            return new a(jVar, iArr, this.a, this.b, (long) this.c, (long) this.d, (long) this.e, this.f);
        }
    }

    public a(j group, int[] tracks, d bandwidthMeter, int maxInitialBitrate, long minDurationForQualityIncreaseMs, long maxDurationForQualityDecreaseMs, long minDurationToRetainAfterDiscardMs, float bandwidthFraction) {
        super(group, tracks);
        this.d = bandwidthMeter;
        this.e = maxInitialBitrate;
        this.f = 1000 * minDurationForQualityIncreaseMs;
        this.g = 1000 * maxDurationForQualityDecreaseMs;
        this.h = 1000 * minDurationToRetainAfterDiscardMs;
        this.i = bandwidthFraction;
        long a = this.d.a();
        a = a == -1 ? (long) this.e : (long) (((float) a) * this.i);
        int i = 0;
        int i2 = 0;
        while (i2 < this.b) {
            if (Long.MIN_VALUE == Long.MIN_VALUE || !c(i2)) {
                if (((long) a(i2).b) <= a) {
                    break;
                }
                i = i2;
            }
            i2++;
        }
        i2 = i;
        this.j = i2;
        this.k = 1;
    }
}
