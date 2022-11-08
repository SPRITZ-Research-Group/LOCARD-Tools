package com.google.android.gms.common;

import java.util.Arrays;

final class p extends a {
    private final byte[] a;

    p(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.a = bArr;
    }

    final byte[] a() {
        return this.a;
    }
}
