package com.google.a.a.a;

import com.google.a.b.a;

final class e extends g {
    private final short b;
    private final short c;

    e(g previous, int value, int bitCount) {
        super(previous);
        this.b = (short) value;
        this.c = (short) bitCount;
    }

    final void a(a bitArray, byte[] text) {
        bitArray.a(this.b, this.c);
    }

    public final String toString() {
        return "<" + Integer.toBinaryString((1 << this.c) | ((this.b & ((1 << this.c) - 1)) | (1 << this.c))).substring(1) + '>';
    }
}
