package com.google.ads.interactivemedia.v3.impl.data;

import com.google.obf.km;

@km(a = g.class)
public abstract class j {
    public abstract double end();

    public abstract boolean played();

    public abstract double start();

    private static j create(double d, double d2, boolean z) {
        return new g(d, d2, z);
    }
}
