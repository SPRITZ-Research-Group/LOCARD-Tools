package com.google.android.gms.internal.clearcut;

final class k extends o {
    private final int c;
    private final int d;

    k(byte[] bArr, int i, int i2) {
        super(bArr);
        h.a(i, i + i2, bArr.length);
        this.c = i;
        this.d = i2;
    }

    public final byte a(int i) {
        int a = a();
        if (((a - (i + 1)) | i) >= 0) {
            return this.b[this.c + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + a);
    }

    public final int a() {
        return this.d;
    }

    protected final int e() {
        return this.c;
    }
}
