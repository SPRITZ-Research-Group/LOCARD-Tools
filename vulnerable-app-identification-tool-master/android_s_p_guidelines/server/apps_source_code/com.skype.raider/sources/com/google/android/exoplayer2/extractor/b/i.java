package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.d.a;

final class i {
    public final byte[] a;
    private final int b;
    private int c;
    private int d;

    public i(byte[] data) {
        this(data, data.length);
    }

    private i(byte[] data, int limit) {
        this.a = data;
        this.b = limit * 8;
    }

    public final boolean a() {
        return a(1) == 1;
    }

    public final int a(int numBits) {
        a.b(b() + numBits <= this.b);
        if (numBits == 0) {
            return 0;
        }
        int result = 0;
        int bitCount = 0;
        if (this.d != 0) {
            bitCount = Math.min(numBits, 8 - this.d);
            result = (this.a[this.c] >>> this.d) & (255 >>> (8 - bitCount));
            this.d += bitCount;
            if (this.d == 8) {
                this.c++;
                this.d = 0;
            }
        }
        if (numBits - bitCount > 7) {
            int numBytes = (numBits - bitCount) / 8;
            for (int i = 0; i < numBytes; i++) {
                long j = (long) result;
                byte[] bArr = this.a;
                int i2 = this.c;
                this.c = i2 + 1;
                result = (int) (j | ((((long) bArr[i2]) & 255) << bitCount));
                bitCount += 8;
            }
        }
        if (numBits > bitCount) {
            int bitsOnNextByte = numBits - bitCount;
            result |= (this.a[this.c] & (255 >>> (8 - bitsOnNextByte))) << bitCount;
            this.d += bitsOnNextByte;
        }
        return result;
    }

    public final void b(int numberOfBits) {
        a.b(b() + numberOfBits <= this.b);
        this.c += numberOfBits / 8;
        this.d += numberOfBits % 8;
        if (this.d > 7) {
            this.c++;
            this.d -= 8;
        }
    }

    public final int b() {
        return (this.c * 8) + this.d;
    }
}
