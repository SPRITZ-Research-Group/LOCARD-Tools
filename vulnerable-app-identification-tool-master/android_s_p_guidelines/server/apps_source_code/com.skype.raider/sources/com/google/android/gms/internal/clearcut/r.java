package com.google.android.gms.internal.clearcut;

final class r extends q {
    private final byte[] a;
    private final boolean b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;

    private r(byte[] bArr, int i) {
        super();
        this.g = Integer.MAX_VALUE;
        this.a = bArr;
        this.c = i + 0;
        this.e = 0;
        this.f = this.e;
        this.b = false;
    }

    /* synthetic */ r(byte[] bArr, int i, byte b) {
        this(bArr, i);
    }

    public final int a() {
        return this.e - this.f;
    }

    public final int a(int i) throws an {
        if (i < 0) {
            throw new an("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int a = a() + i;
        int i2 = this.g;
        if (a > i2) {
            throw an.a();
        }
        this.g = a;
        this.c += this.d;
        a = this.c - this.f;
        if (a > this.g) {
            this.d = a - this.g;
            this.c -= this.d;
        } else {
            this.d = 0;
        }
        return i2;
    }
}
