package com.facebook.ads.internal.n;

public enum k {
    HEIGHT_100(100),
    HEIGHT_120(120),
    HEIGHT_300(300),
    HEIGHT_400(400);
    
    private final int e;
    private final int f;

    private k(int i) {
        this.e = -1;
        this.f = i;
    }

    public final int a() {
        switch (this.f) {
            case 100:
                return 1;
            case 120:
                return 2;
            case 300:
                return 3;
            case 400:
                return 4;
            default:
                return -1;
        }
    }
}
