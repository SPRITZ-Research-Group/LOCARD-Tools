package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class a {
    private final byte[] a;
    private final int b;
    private final int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h = Integer.MAX_VALUE;
    private int i;
    private int j = 64;
    private int k = 67108864;

    private a(byte[] bArr, int i) {
        this.a = bArr;
        this.b = 0;
        int i2 = i + 0;
        this.d = i2;
        this.c = i2;
        this.f = 0;
    }

    public static a a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static a a(byte[] bArr, int i) {
        return new a(bArr, i);
    }

    private void e(int i) throws i {
        if (this.g != i) {
            throw new i("Protocol message end-group tag did not match expected tag.");
        }
    }

    private final void f(int i) throws IOException {
        if (i < 0) {
            throw i.b();
        } else if (this.f + i > this.h) {
            f(this.h - this.f);
            throw i.a();
        } else if (i <= this.d - this.f) {
            this.f += i;
        } else {
            throw i.a();
        }
    }

    private final void j() {
        this.d += this.e;
        int i = this.d;
        if (i > this.h) {
            this.e = i - this.h;
            this.d -= this.e;
            return;
        }
        this.e = 0;
    }

    private final byte k() throws IOException {
        if (this.f == this.d) {
            throw i.a();
        }
        byte[] bArr = this.a;
        int i = this.f;
        this.f = i + 1;
        return bArr[i];
    }

    public final int a() throws IOException {
        if (this.f == this.d) {
            this.g = 0;
            return 0;
        }
        this.g = d();
        if (this.g != 0) {
            return this.g;
        }
        throw new i("Protocol message contained an invalid tag (zero).");
    }

    public final void a(j jVar) throws IOException {
        int d = d();
        if (this.i >= this.j) {
            throw i.d();
        }
        d = b(d);
        this.i++;
        jVar.a(this);
        e(0);
        this.i--;
        c(d);
    }

    public final void a(j jVar, int i) throws IOException {
        if (this.i >= this.j) {
            throw i.d();
        }
        this.i++;
        jVar.a(this);
        e((i << 3) | 4);
        this.i--;
    }

    public final boolean a(int i) throws IOException {
        switch (i & 7) {
            case 0:
                d();
                return true;
            case 1:
                g();
                return true;
            case 2:
                f(d());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                f();
                return true;
            default:
                throw new i("Protocol message tag had invalid wire type.");
        }
        int a;
        do {
            a = a();
            if (a != 0) {
            }
            e(((i >>> 3) << 3) | 4);
            return true;
        } while (a(a));
        e(((i >>> 3) << 3) | 4);
        return true;
    }

    public final byte[] a(int i, int i2) {
        if (i2 == 0) {
            return m.d;
        }
        Object obj = new byte[i2];
        System.arraycopy(this.a, this.b + i, obj, 0, i2);
        return obj;
    }

    public final int b(int i) throws i {
        if (i < 0) {
            throw i.b();
        }
        int i2 = this.f + i;
        int i3 = this.h;
        if (i2 > i3) {
            throw i.a();
        }
        this.h = i2;
        j();
        return i3;
    }

    final void b(int i, int i2) {
        if (i > this.f - this.b) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.f - this.b));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.f = this.b + i;
            this.g = i2;
        }
    }

    public final boolean b() throws IOException {
        return d() != 0;
    }

    public final String c() throws IOException {
        int d = d();
        if (d < 0) {
            throw i.b();
        } else if (d > this.d - this.f) {
            throw i.a();
        } else {
            String str = new String(this.a, this.f, d, h.a);
            this.f = d + this.f;
            return str;
        }
    }

    public final void c(int i) {
        this.h = i;
        j();
    }

    public final int d() throws IOException {
        byte k = k();
        if (k >= (byte) 0) {
            return k;
        }
        int i = k & 127;
        byte k2 = k();
        if (k2 >= (byte) 0) {
            return i | (k2 << 7);
        }
        i |= (k2 & 127) << 7;
        k2 = k();
        if (k2 >= (byte) 0) {
            return i | (k2 << 14);
        }
        i |= (k2 & 127) << 14;
        k2 = k();
        if (k2 >= (byte) 0) {
            return i | (k2 << 21);
        }
        i |= (k2 & 127) << 21;
        k2 = k();
        i |= k2 << 28;
        if (k2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (k() >= (byte) 0) {
                return i;
            }
        }
        throw i.c();
    }

    public final void d(int i) {
        b(i, this.g);
    }

    public final long e() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte k = k();
            j |= ((long) (k & 127)) << i;
            if ((k & 128) == 0) {
                return j;
            }
        }
        throw i.c();
    }

    public final int f() throws IOException {
        return (((k() & 255) | ((k() & 255) << 8)) | ((k() & 255) << 16)) | ((k() & 255) << 24);
    }

    public final long g() throws IOException {
        byte k = k();
        byte k2 = k();
        return ((((((((((long) k2) & 255) << 8) | (((long) k) & 255)) | ((((long) k()) & 255) << 16)) | ((((long) k()) & 255) << 24)) | ((((long) k()) & 255) << 32)) | ((((long) k()) & 255) << 40)) | ((((long) k()) & 255) << 48)) | ((((long) k()) & 255) << 56);
    }

    public final int h() {
        if (this.h == Integer.MAX_VALUE) {
            return -1;
        }
        return this.h - this.f;
    }

    public final int i() {
        return this.f - this.b;
    }
}
