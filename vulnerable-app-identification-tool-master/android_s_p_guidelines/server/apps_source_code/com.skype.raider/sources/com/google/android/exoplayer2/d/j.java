package com.google.android.exoplayer2.d;

public final class j {
    public byte[] a;
    private int b;
    private int c;
    private int d;

    public j(byte[] data) {
        this(data, data.length);
    }

    public j(byte[] data, int limit) {
        this.a = data;
        this.d = limit;
    }

    public final void a(byte[] data, int limit) {
        this.a = data;
        this.b = 0;
        this.c = 0;
        this.d = limit;
    }

    public final int a() {
        return ((this.d - this.b) * 8) - this.c;
    }

    public final int b() {
        return (this.b * 8) + this.c;
    }

    public final int c() {
        a.b(this.c == 0);
        return this.b;
    }

    public final void a(int position) {
        this.b = position / 8;
        this.c = position - (this.b * 8);
        f();
    }

    public final void b(int n) {
        this.b += n / 8;
        this.c += n % 8;
        if (this.c > 7) {
            this.b++;
            this.c -= 8;
        }
        f();
    }

    public final boolean d() {
        return c(1) == 1;
    }

    public final int c(int numBits) {
        if (numBits == 0) {
            return 0;
        }
        int returnValue = 0;
        int wholeBytes = numBits / 8;
        for (int i = 0; i < wholeBytes; i++) {
            int byteValue;
            if (this.c != 0) {
                byteValue = ((this.a[this.b] & 255) << this.c) | ((this.a[this.b + 1] & 255) >>> (8 - this.c));
            } else {
                byteValue = this.a[this.b];
            }
            numBits -= 8;
            returnValue |= (byteValue & 255) << numBits;
            this.b++;
        }
        if (numBits > 0) {
            int nextBit = this.c + numBits;
            byte writeMask = (byte) (255 >> (8 - numBits));
            if (nextBit > 8) {
                returnValue |= (((this.a[this.b] & 255) << (nextBit - 8)) | ((this.a[this.b + 1] & 255) >> (16 - nextBit))) & writeMask;
                this.b++;
            } else {
                returnValue |= ((this.a[this.b] & 255) >> (8 - nextBit)) & writeMask;
                if (nextBit == 8) {
                    this.b++;
                }
            }
            this.c = nextBit % 8;
        }
        f();
        return returnValue;
    }

    public final void e() {
        if (this.c != 0) {
            this.c = 0;
            this.b++;
            f();
        }
    }

    public final void b(byte[] buffer, int length) {
        boolean z;
        if (this.c == 0) {
            z = true;
        } else {
            z = false;
        }
        a.b(z);
        System.arraycopy(this.a, this.b, buffer, 0, length);
        this.b += length;
        f();
    }

    public final void d(int length) {
        a.b(this.c == 0);
        this.b += length;
        f();
    }

    private void f() {
        boolean z = this.b >= 0 && this.c >= 0 && this.c < 8 && (this.b < this.d || (this.b == this.d && this.c == 0));
        a.b(z);
    }
}
