package com.google.android.gms.internal.clearcut;

final class m {
    private final s a;
    private final byte[] b;

    private m(int i) {
        this.b = new byte[i];
        this.a = s.a(this.b);
    }

    /* synthetic */ m(int i, byte b) {
        this(i);
    }

    public final h a() {
        if (this.a.h() == 0) {
            return new o(this.b);
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final s b() {
        return this.a;
    }
}
