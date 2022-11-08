package com.facebook.ads.internal.p.b.a;

public final class g extends e {
    private final long a;

    public g() {
        if (67108864 <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.a = 67108864;
    }

    protected final boolean a(long j) {
        return j <= this.a;
    }
}
