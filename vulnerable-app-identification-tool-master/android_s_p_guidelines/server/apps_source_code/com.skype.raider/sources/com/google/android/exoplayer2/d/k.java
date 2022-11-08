package com.google.android.exoplayer2.d;

import java.nio.charset.Charset;

public final class k {
    public byte[] a;
    private int b;
    private int c;

    public k(int limit) {
        this.a = new byte[limit];
        this.c = limit;
    }

    public k(byte[] data) {
        this.a = data;
        this.c = data.length;
    }

    public k(byte[] data, int limit) {
        this.a = data;
        this.c = limit;
    }

    public final void a(int limit) {
        a(e() < limit ? new byte[limit] : this.a, limit);
    }

    public final void a(byte[] data, int limit) {
        this.a = data;
        this.c = limit;
        this.b = 0;
    }

    public final void a() {
        this.b = 0;
        this.c = 0;
    }

    public final int b() {
        return this.c - this.b;
    }

    public final int c() {
        return this.c;
    }

    public final void b(int limit) {
        boolean z = limit >= 0 && limit <= this.a.length;
        a.a(z);
        this.c = limit;
    }

    public final int d() {
        return this.b;
    }

    public final int e() {
        return this.a == null ? 0 : this.a.length;
    }

    public final void c(int position) {
        boolean z = position >= 0 && position <= this.c;
        a.a(z);
        this.b = position;
    }

    public final void d(int bytes) {
        c(this.b + bytes);
    }

    public final void a(j bitArray, int length) {
        a(bitArray.a, 0, length);
        bitArray.a(0);
    }

    public final void a(byte[] buffer, int offset, int length) {
        System.arraycopy(this.a, this.b, buffer, offset, length);
        this.b += length;
    }

    public final char f() {
        return (char) (((this.a[this.b] & 255) << 8) | (this.a[this.b + 1] & 255));
    }

    public final int g() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }

    public final int h() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        return i2 | (bArr2[i3] & 255);
    }

    public final int i() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = bArr[i] & 255;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        return i2 | ((bArr2[i3] & 255) << 8);
    }

    public final short j() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        return (short) (i2 | (bArr2[i3] & 255));
    }

    public final int k() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 16;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 8;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        return i2 | (bArr2[i3] & 255);
    }

    public final long l() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = (((long) bArr[i]) & 255) << 24;
        byte[] bArr2 = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 16;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 8;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        return j | (((long) bArr2[i2]) & 255);
    }

    public final long m() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = ((long) bArr[i]) & 255;
        byte[] bArr2 = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 8;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 16;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        return j | ((((long) bArr2[i2]) & 255) << 24);
    }

    public final int n() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 24;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 16;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 8;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        return i2 | (bArr2[i3] & 255);
    }

    public final int o() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = bArr[i] & 255;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 8;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 16;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        return i2 | ((bArr2[i3] & 255) << 24);
    }

    public final long p() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = (((long) bArr[i]) & 255) << 56;
        byte[] bArr2 = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 48;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 40;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 32;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 24;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 16;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 8;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        return j | (((long) bArr2[i2]) & 255);
    }

    public final long q() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = ((long) bArr[i]) & 255;
        byte[] bArr2 = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 8;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 16;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 24;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 32;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 40;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        j |= (((long) bArr2[i2]) & 255) << 48;
        bArr2 = this.a;
        i2 = this.b;
        this.b = i2 + 1;
        return j | ((((long) bArr2[i2]) & 255) << 56);
    }

    public final int r() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        int result = i2 | (bArr2[i3] & 255);
        this.b += 2;
        return result;
    }

    public final int s() {
        return (((g() << 21) | (g() << 14)) | (g() << 7)) | g();
    }

    public final int t() {
        int result = n();
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("Top bit not zero: " + result);
    }

    public final int u() {
        int result = o();
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("Top bit not zero: " + result);
    }

    public final long v() {
        long result = p();
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("Top bit not zero: " + result);
    }

    public final String e(int length) {
        return a(length, Charset.defaultCharset());
    }

    public final String a(int length, Charset charset) {
        String result = new String(this.a, this.b, length, charset);
        this.b += length;
        return result;
    }

    public final String f(int length) {
        if (length == 0) {
            return "";
        }
        int stringLength = length;
        int lastIndex = (this.b + length) - 1;
        if (lastIndex < this.c && this.a[lastIndex] == (byte) 0) {
            stringLength--;
        }
        String result = new String(this.a, this.b, stringLength);
        this.b += length;
        return result;
    }

    public final String w() {
        if (b() == 0) {
            return null;
        }
        int stringLimit = this.b;
        while (stringLimit < this.c && this.a[stringLimit] != (byte) 0) {
            stringLimit++;
        }
        String string = new String(this.a, this.b, stringLimit - this.b);
        this.b = stringLimit;
        if (this.b >= this.c) {
            return string;
        }
        this.b++;
        return string;
    }

    public final String x() {
        if (b() == 0) {
            return null;
        }
        int lineLimit = this.b;
        while (lineLimit < this.c && !s.a(this.a[lineLimit])) {
            lineLimit++;
        }
        if (lineLimit - this.b >= 3 && this.a[this.b] == (byte) -17 && this.a[this.b + 1] == (byte) -69 && this.a[this.b + 2] == (byte) -65) {
            this.b += 3;
        }
        String line = new String(this.a, this.b, lineLimit - this.b);
        this.b = lineLimit;
        if (this.b == this.c) {
            return line;
        }
        if (this.a[this.b] == (byte) 13) {
            this.b++;
            if (this.b == this.c) {
                return line;
            }
        }
        if (this.a[this.b] != (byte) 10) {
            return line;
        }
        this.b++;
        return line;
    }

    public final long y() {
        int i;
        int x;
        int length = 0;
        long value = (long) this.a[this.b];
        for (int j = 7; j >= 0; j--) {
            if ((((long) (1 << j)) & value) == 0) {
                if (j < 6) {
                    value &= (long) ((1 << j) - 1);
                    length = 7 - j;
                } else if (j == 7) {
                    length = 1;
                }
                if (length != 0) {
                    throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + value);
                }
                for (i = 1; i < length; i++) {
                    x = this.a[this.b + i];
                    if ((x & 192) == 128) {
                        throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + value);
                    }
                    value = (value << 6) | ((long) (x & 63));
                }
                this.b += length;
                return value;
            }
        }
        if (length != 0) {
            while (i < length) {
                x = this.a[this.b + i];
                if ((x & 192) == 128) {
                    value = (value << 6) | ((long) (x & 63));
                } else {
                    throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + value);
                }
            }
            this.b += length;
            return value;
        }
        throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + value);
    }
}
