package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.g;
import java.io.IOException;

final class e {
    private static final long[] a = new long[]{128, 64, 32, 16, 8, 4, 2, 1};
    private final byte[] b = new byte[8];
    private int c;
    private int d;

    public final void a() {
        this.c = 0;
        this.d = 0;
    }

    public final long a(g input, boolean allowEndOfInput, boolean removeLengthMask, int maximumAllowedLength) throws IOException, InterruptedException {
        if (this.c == 0) {
            if (!input.a(this.b, 0, 1, allowEndOfInput)) {
                return -1;
            }
            this.d = a(this.b[0] & 255);
            if (this.d == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.c = 1;
        }
        if (this.d > maximumAllowedLength) {
            this.c = 0;
            return -2;
        }
        if (this.d != 1) {
            input.b(this.b, 1, this.d - 1);
        }
        this.c = 0;
        return a(this.b, this.d, removeLengthMask);
    }

    public final int b() {
        return this.d;
    }

    public static int a(int firstByte) {
        for (int i = 0; i < a.length; i++) {
            if ((a[i] & ((long) firstByte)) != 0) {
                return i + 1;
            }
        }
        return -1;
    }

    public static long a(byte[] varintBytes, int varintLength, boolean removeLengthMask) {
        long varint = ((long) varintBytes[0]) & 255;
        if (removeLengthMask) {
            varint &= a[varintLength - 1] ^ -1;
        }
        for (int i = 1; i < varintLength; i++) {
            varint = (varint << 8) | (((long) varintBytes[i]) & 255);
        }
        return varint;
    }
}
