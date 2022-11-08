package com.google.android.exoplayer2.d;

public final class l {
    private byte[] a;
    private int b;
    private int c;
    private int d;

    public l(byte[] data, int offset, int limit) {
        a(data, offset, limit);
    }

    public final void a(byte[] data, int offset, int limit) {
        this.a = data;
        this.c = offset;
        this.b = limit;
        this.d = 0;
        f();
    }

    public final void a(int n) {
        int oldByteOffset = this.c;
        this.c += n / 8;
        this.d += n % 8;
        if (this.d > 7) {
            this.c++;
            this.d -= 8;
        }
        int i = oldByteOffset + 1;
        while (i <= this.c) {
            if (d(i)) {
                this.c++;
                i += 2;
            }
            i++;
        }
        f();
    }

    public final boolean b(int n) {
        int oldByteOffset = this.c;
        int newByteOffset = this.c + (n / 8);
        int newBitOffset = this.d + (n % 8);
        if (newBitOffset > 7) {
            newByteOffset++;
            newBitOffset -= 8;
        }
        int i = oldByteOffset + 1;
        while (i <= newByteOffset && newByteOffset < this.b) {
            if (d(i)) {
                newByteOffset++;
                i += 2;
            }
            i++;
        }
        return newByteOffset < this.b || (newByteOffset == this.b && newBitOffset == 0);
    }

    public final boolean a() {
        return c(1) == 1;
    }

    public final int c(int numBits) {
        if (numBits == 0) {
            return 0;
        }
        int nextByteOffset;
        int returnValue = 0;
        int wholeBytes = numBits / 8;
        for (int i = 0; i < wholeBytes; i++) {
            int byteValue;
            nextByteOffset = d(this.c + 1) ? this.c + 2 : this.c + 1;
            if (this.d != 0) {
                byteValue = ((this.a[this.c] & 255) << this.d) | ((this.a[nextByteOffset] & 255) >>> (8 - this.d));
            } else {
                byteValue = this.a[this.c];
            }
            numBits -= 8;
            returnValue |= (byteValue & 255) << numBits;
            this.c = nextByteOffset;
        }
        if (numBits > 0) {
            int nextBit = this.d + numBits;
            byte writeMask = (byte) (255 >> (8 - numBits));
            nextByteOffset = d(this.c + 1) ? this.c + 2 : this.c + 1;
            if (nextBit > 8) {
                returnValue |= (((this.a[this.c] & 255) << (nextBit - 8)) | ((this.a[nextByteOffset] & 255) >> (16 - nextBit))) & writeMask;
                this.c = nextByteOffset;
            } else {
                returnValue |= ((this.a[this.c] & 255) >> (8 - nextBit)) & writeMask;
                if (nextBit == 8) {
                    this.c = nextByteOffset;
                }
            }
            this.d = nextBit % 8;
        }
        f();
        return returnValue;
    }

    public final boolean b() {
        boolean hitLimit;
        int initialByteOffset = this.c;
        int initialBitOffset = this.d;
        int leadingZeros = 0;
        while (this.c < this.b && !a()) {
            leadingZeros++;
        }
        if (this.c == this.b) {
            hitLimit = true;
        } else {
            hitLimit = false;
        }
        this.c = initialByteOffset;
        this.d = initialBitOffset;
        if (hitLimit || !b((leadingZeros * 2) + 1)) {
            return false;
        }
        return true;
    }

    public final int c() {
        return e();
    }

    public final int d() {
        int codeNum = e();
        return (codeNum % 2 == 0 ? -1 : 1) * ((codeNum + 1) / 2);
    }

    private int e() {
        int leadingZeros = 0;
        while (!a()) {
            leadingZeros++;
        }
        return (leadingZeros > 0 ? c(leadingZeros) : 0) + ((1 << leadingZeros) - 1);
    }

    private boolean d(int offset) {
        return 2 <= offset && offset < this.b && this.a[offset] == (byte) 3 && this.a[offset - 2] == (byte) 0 && this.a[offset - 1] == (byte) 0;
    }

    private void f() {
        boolean z = this.c >= 0 && this.d >= 0 && this.d < 8 && (this.c < this.b || (this.c == this.b && this.d == 0));
        a.b(z);
    }
}
