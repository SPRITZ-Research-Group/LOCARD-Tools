package com.facebook.ads.internal.r;

import java.io.Serializable;

public enum g implements Serializable {
    BANNER_320_50(320, 50),
    INTERSTITIAL(0, 0),
    BANNER_HEIGHT_50(-1, 50),
    BANNER_HEIGHT_90(-1, 90),
    RECTANGLE_HEIGHT_250(-1, 250);
    
    private final int f;
    private final int g;

    private g(int i, int i2) {
        this.f = i;
        this.g = i2;
    }

    public final int a() {
        return this.f;
    }

    public final int b() {
        return this.g;
    }
}
