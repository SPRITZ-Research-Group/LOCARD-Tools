package com.facebook.react.animated;

import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;

final class h extends d {
    private long e = -1;
    private final double[] f;
    private final double g;
    private double h;
    private int i;
    private int j;

    h(am config) {
        int i;
        boolean z = true;
        al frames = config.getArray("frames");
        int numberOfFrames = frames.size();
        this.f = new double[numberOfFrames];
        for (int i2 = 0; i2 < numberOfFrames; i2++) {
            this.f[i2] = frames.getDouble(i2);
        }
        this.g = config.getDouble("toValue");
        if (config.hasKey("iterations")) {
            i = config.getInt("iterations");
        } else {
            i = 1;
        }
        this.i = i;
        this.j = 1;
        if (this.i != 0) {
            z = false;
        }
        this.a = z;
    }

    public final void a(long frameTimeNanos) {
        if (this.e < 0) {
            this.e = frameTimeNanos;
            this.h = this.b.e;
        }
        int frameIndex = (int) (((double) ((frameTimeNanos - this.e) / 1000000)) / 16.666666666666668d);
        if (frameIndex < 0) {
            throw new IllegalStateException("Calculated frame index should never be lower than 0");
        } else if (!this.a) {
            double nextValue;
            if (frameIndex >= this.f.length - 1) {
                nextValue = this.g;
                if (this.i == -1 || this.j < this.i) {
                    this.e = frameTimeNanos;
                    this.j++;
                } else {
                    this.a = true;
                }
            } else {
                nextValue = this.h + (this.f[frameIndex] * (this.g - this.h));
            }
            this.b.e = nextValue;
        }
    }
}
