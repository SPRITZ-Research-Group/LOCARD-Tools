package com.google.a.f.a;

public enum a {
    L(1),
    M(0),
    Q(3),
    H(2);
    
    private static final a[] e = null;
    private final int f;

    static {
        e = new a[]{M, L, H, Q};
    }

    private a(int bits) {
        this.f = bits;
    }

    public final int a() {
        return this.f;
    }
}
