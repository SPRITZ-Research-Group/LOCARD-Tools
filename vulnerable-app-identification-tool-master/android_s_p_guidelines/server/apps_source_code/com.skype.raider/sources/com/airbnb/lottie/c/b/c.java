package com.airbnb.lottie.c.b;

import com.airbnb.lottie.e.b;

public final class c {
    private final float[] a;
    private final int[] b;

    public c(float[] positions, int[] colors) {
        this.a = positions;
        this.b = colors;
    }

    public final float[] a() {
        return this.a;
    }

    public final int[] b() {
        return this.b;
    }

    public final int c() {
        return this.b.length;
    }

    public final void a(c gc1, c gc2, float progress) {
        if (gc1.b.length != gc2.b.length) {
            throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + gc1.b.length + " vs " + gc2.b.length + ")");
        }
        for (int i = 0; i < gc1.b.length; i++) {
            float[] fArr = this.a;
            float f = gc1.a[i];
            fArr[i] = f + ((gc2.a[i] - f) * progress);
            this.b[i] = b.a(progress, gc1.b[i], gc2.b[i]);
        }
    }
}
