package com.google.a.a.a;

import com.google.a.b.a;

final class b extends g {
    private final short b;
    private final short c;

    b(g previous, int binaryShiftStart, int binaryShiftByteCount) {
        super(previous);
        this.b = (short) binaryShiftStart;
        this.c = (short) binaryShiftByteCount;
    }

    public final void a(a bitArray, byte[] text) {
        short i = (short) 0;
        while (i < this.c) {
            if (i == (short) 0 || (i == (short) 31 && this.c <= (short) 62)) {
                bitArray.a(31, 5);
                if (this.c > (short) 62) {
                    bitArray.a(this.c - 31, 16);
                } else if (i == (short) 0) {
                    bitArray.a(Math.min(this.c, 31), 5);
                } else {
                    bitArray.a(this.c - 31, 5);
                }
            }
            bitArray.a(text[this.b + i], 8);
            i++;
        }
    }

    public final String toString() {
        return "<" + this.b + "::" + ((this.b + this.c) - 1) + '>';
    }
}
