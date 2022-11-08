package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class s extends g {
    private static final Logger b = Logger.getLogger(s.class.getName());
    private static final boolean c = cx.a();
    t a = this;

    static class a extends s {
        private final byte[] b;
        private final int c;
        private final int d;
        private int e;

        a(byte[] bArr, int i) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if (((i | 0) | (bArr.length - (i + 0))) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(0), Integer.valueOf(i)}));
            } else {
                this.b = bArr;
                this.c = 0;
                this.e = 0;
                this.d = i + 0;
            }
        }

        public final void a(byte b) throws IOException {
            try {
                byte[] bArr = this.b;
                int i = this.e;
                this.e = i + 1;
                bArr[i] = b;
            } catch (Throwable e) {
                throw new b(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e);
            }
        }

        public final void a(int i) throws IOException {
            if (i >= 0) {
                b(i);
            } else {
                a((long) i);
            }
        }

        public final void a(int i, int i2) throws IOException {
            b((i << 3) | i2);
        }

        public final void a(int i, long j) throws IOException {
            a(i, 0);
            a(j);
        }

        public final void a(int i, bk bkVar) throws IOException {
            a(1, 3);
            c(2, i);
            a(bkVar);
            a(1, 4);
        }

        final void a(int i, bk bkVar, bz bzVar) throws IOException {
            a(i, 2);
            b bVar = (b) bkVar;
            int b = bVar.b();
            if (b == -1) {
                b = bzVar.b(bVar);
                bVar.a(b);
            }
            b(b);
            bzVar.a((Object) bkVar, this.a);
        }

        public final void a(int i, h hVar) throws IOException {
            a(i, 2);
            a(hVar);
        }

        public final void a(int i, String str) throws IOException {
            a(i, 2);
            a(str);
        }

        public final void a(int i, boolean z) throws IOException {
            int i2 = 0;
            a(i, 0);
            if (z) {
                i2 = 1;
            }
            a((byte) i2);
        }

        public final void a(long j) throws IOException {
            byte[] bArr;
            int i;
            if (!s.c || h() < 10) {
                while ((j & -128) != 0) {
                    try {
                        bArr = this.b;
                        i = this.e;
                        this.e = i + 1;
                        bArr[i] = (byte) ((((int) j) & 127) | 128);
                        j >>>= 7;
                    } catch (Throwable e) {
                        throw new b(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e);
                    }
                }
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) j);
                return;
            }
            while ((j & -128) != 0) {
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                cx.a(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            bArr = this.b;
            i = this.e;
            this.e = i + 1;
            cx.a(bArr, (long) i, (byte) ((int) j));
        }

        public final void a(bk bkVar) throws IOException {
            a(3, 2);
            b(bkVar);
        }

        public final void a(h hVar) throws IOException {
            b(hVar.a());
            hVar.a((g) this);
        }

        public final void a(String str) throws IOException {
            int i = this.e;
            try {
                int n = s.n(str.length() * 3);
                int n2 = s.n(str.length());
                if (n2 == n) {
                    this.e = i + n2;
                    n = cz.a(str, this.b, this.e, h());
                    this.e = i;
                    b((n - i) - n2);
                    this.e = n;
                    return;
                }
                b(cz.a((CharSequence) str));
                this.e = cz.a(str, this.b, this.e, h());
            } catch (dc e) {
                this.e = i;
                a(str, e);
            } catch (Throwable e2) {
                throw new b(e2);
            }
        }

        public final void a(byte[] bArr, int i) throws IOException {
            b(i);
            b(bArr, 0, i);
        }

        public final void a(byte[] bArr, int i, int i2) throws IOException {
            b(bArr, i, i2);
        }

        public final void b(int i) throws IOException {
            byte[] bArr;
            int i2;
            if (!s.c || h() < 10) {
                while ((i & -128) != 0) {
                    try {
                        bArr = this.b;
                        i2 = this.e;
                        this.e = i2 + 1;
                        bArr[i2] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (Throwable e) {
                        throw new b(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e);
                    }
                }
                bArr = this.b;
                i2 = this.e;
                this.e = i2 + 1;
                bArr[i2] = (byte) i;
                return;
            }
            while ((i & -128) != 0) {
                bArr = this.b;
                i2 = this.e;
                this.e = i2 + 1;
                cx.a(bArr, (long) i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            bArr = this.b;
            i2 = this.e;
            this.e = i2 + 1;
            cx.a(bArr, (long) i2, (byte) i);
        }

        public final void b(int i, int i2) throws IOException {
            a(i, 0);
            a(i2);
        }

        public final void b(int i, h hVar) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, hVar);
            a(1, 4);
        }

        public final void b(bk bkVar) throws IOException {
            b(bkVar.d());
            bkVar.a(this);
        }

        public final void b(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.b, this.e, i2);
                this.e += i2;
            } catch (Throwable e) {
                throw new b(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(i2)}), e);
            }
        }

        public final void c(int i, int i2) throws IOException {
            a(i, 0);
            b(i2);
        }

        public final void c(int i, long j) throws IOException {
            a(i, 1);
            c(j);
        }

        public final void c(long j) throws IOException {
            try {
                byte[] bArr = this.b;
                int i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) j);
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) (j >> 8));
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) (j >> 16));
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) (j >> 24));
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) (j >> 32));
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) (j >> 40));
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) (j >> 48));
                bArr = this.b;
                i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) (j >> 56));
            } catch (Throwable e) {
                throw new b(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e);
            }
        }

        public final void d(int i) throws IOException {
            try {
                byte[] bArr = this.b;
                int i2 = this.e;
                this.e = i2 + 1;
                bArr[i2] = (byte) i;
                bArr = this.b;
                i2 = this.e;
                this.e = i2 + 1;
                bArr[i2] = (byte) (i >> 8);
                bArr = this.b;
                i2 = this.e;
                this.e = i2 + 1;
                bArr[i2] = (byte) (i >> 16);
                bArr = this.b;
                i2 = this.e;
                this.e = i2 + 1;
                bArr[i2] = i >> 24;
            } catch (Throwable e) {
                throw new b(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e);
            }
        }

        public final void e(int i, int i2) throws IOException {
            a(i, 5);
            d(i2);
        }

        public final int h() {
            return this.d - this.e;
        }
    }

    public static class b extends IOException {
        b() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        b(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }

        b(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    private s() {
    }

    /* synthetic */ s(byte b) {
        this();
    }

    public static int a() {
        return 4;
    }

    public static int a(at atVar) {
        int b = atVar.b();
        return b + n(b);
    }

    static int a(bk bkVar, bz bzVar) {
        b bVar = (b) bkVar;
        int b = bVar.b();
        if (b == -1) {
            b = bzVar.b(bVar);
            bVar.a(b);
        }
        return b + n(b);
    }

    public static s a(byte[] bArr) {
        return new a(bArr, bArr.length);
    }

    public static int b() {
        return 4;
    }

    public static int b(h hVar) {
        int a = hVar.a();
        return a + n(a);
    }

    public static int b(String str) {
        int a;
        try {
            a = cz.a((CharSequence) str);
        } catch (dc e) {
            a = str.getBytes(aj.a).length;
        }
        return a + n(a);
    }

    public static int b(byte[] bArr) {
        int length = bArr.length;
        return length + n(length);
    }

    public static int c() {
        return 8;
    }

    public static int c(bk bkVar) {
        int d = bkVar.d();
        return d + n(d);
    }

    public static int d() {
        return 8;
    }

    public static int d(long j) {
        return e(j);
    }

    @Deprecated
    public static int d(bk bkVar) {
        return bkVar.d();
    }

    public static int e() {
        return 4;
    }

    public static int e(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        long j2;
        int i = 2;
        if ((-34359738368L & j) != 0) {
            i = 6;
            j2 = j >>> 28;
        } else {
            j2 = j;
        }
        if ((-2097152 & j2) != 0) {
            i += 2;
            j2 >>>= 14;
        }
        return (j2 & -16384) != 0 ? i + 1 : i;
    }

    public static int f() {
        return 8;
    }

    public static int f(long j) {
        return e(g(j));
    }

    public static int g() {
        return 1;
    }

    private static long g(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int l(int i) {
        return n(i << 3);
    }

    public static int m(int i) {
        return i >= 0 ? n(i) : 10;
    }

    public static int n(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int o(int i) {
        return n(q(i));
    }

    public static int p(int i) {
        return m(i);
    }

    private static int q(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public abstract void a(byte b) throws IOException;

    public final void a(double d) throws IOException {
        c(Double.doubleToRawLongBits(d));
    }

    public final void a(float f) throws IOException {
        d(Float.floatToRawIntBits(f));
    }

    public abstract void a(int i) throws IOException;

    public final void a(int i, double d) throws IOException {
        c(i, Double.doubleToRawLongBits(d));
    }

    public final void a(int i, float f) throws IOException {
        e(i, Float.floatToRawIntBits(f));
    }

    public abstract void a(int i, int i2) throws IOException;

    public abstract void a(int i, long j) throws IOException;

    public abstract void a(int i, bk bkVar) throws IOException;

    abstract void a(int i, bk bkVar, bz bzVar) throws IOException;

    public abstract void a(int i, h hVar) throws IOException;

    public abstract void a(int i, String str) throws IOException;

    public abstract void a(int i, boolean z) throws IOException;

    public abstract void a(long j) throws IOException;

    public abstract void a(bk bkVar) throws IOException;

    public abstract void a(h hVar) throws IOException;

    public abstract void a(String str) throws IOException;

    final void a(String str, dc dcVar) throws IOException {
        b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", dcVar);
        byte[] bytes = str.getBytes(aj.a);
        try {
            b(bytes.length);
            a(bytes, 0, bytes.length);
        } catch (Throwable e) {
            throw new b(e);
        } catch (b e2) {
            throw e2;
        }
    }

    public final void a(boolean z) throws IOException {
        a((byte) (z ? 1 : 0));
    }

    abstract void a(byte[] bArr, int i) throws IOException;

    public abstract void b(int i) throws IOException;

    public abstract void b(int i, int i2) throws IOException;

    public final void b(int i, long j) throws IOException {
        a(i, g(j));
    }

    public abstract void b(int i, h hVar) throws IOException;

    public final void b(long j) throws IOException {
        a(g(j));
    }

    public abstract void b(bk bkVar) throws IOException;

    public abstract void b(byte[] bArr, int i, int i2) throws IOException;

    public final void c(int i) throws IOException {
        b(q(i));
    }

    public abstract void c(int i, int i2) throws IOException;

    public abstract void c(int i, long j) throws IOException;

    public abstract void c(long j) throws IOException;

    public abstract void d(int i) throws IOException;

    public final void d(int i, int i2) throws IOException {
        c(i, q(i2));
    }

    public abstract void e(int i, int i2) throws IOException;

    public abstract int h();

    public static int f(int i, int i2) {
        return n(i << 3) + m(i2);
    }

    public static int g(int i, int i2) {
        return n(i << 3) + n(i2);
    }

    public static int h(int i, int i2) {
        return n(i << 3) + n(q(i2));
    }

    public static int e(int i) {
        return n(i << 3) + 4;
    }

    public static int f(int i) {
        return n(i << 3) + 4;
    }

    public static int d(int i, long j) {
        return n(i << 3) + e(j);
    }

    public static int e(int i, long j) {
        return n(i << 3) + e(j);
    }

    public static int f(int i, long j) {
        return n(i << 3) + e(g(j));
    }

    public static int g(int i) {
        return n(i << 3) + 8;
    }

    public static int h(int i) {
        return n(i << 3) + 8;
    }

    public static int i(int i) {
        return n(i << 3) + 4;
    }

    public static int j(int i) {
        return n(i << 3) + 8;
    }

    public static int k(int i) {
        return n(i << 3) + 1;
    }

    public static int i(int i, int i2) {
        return n(i << 3) + m(i2);
    }

    public static int b(int i, String str) {
        return n(i << 3) + b(str);
    }

    public static int c(int i, h hVar) {
        int n = n(i << 3);
        int a = hVar.a();
        return n + (a + n(a));
    }

    public static int a(int i, at atVar) {
        int n = n(i << 3);
        int b = atVar.b();
        return n + (b + n(b));
    }

    static int b(int i, bk bkVar, bz bzVar) {
        return n(i << 3) + a(bkVar, bzVar);
    }

    public static int b(int i, bk bkVar) {
        return ((n(8) << 1) + g(2, i)) + (n(24) + c(bkVar));
    }

    public static int d(int i, h hVar) {
        return ((n(8) << 1) + g(2, i)) + c(3, hVar);
    }

    public static int b(int i, at atVar) {
        return ((n(8) << 1) + g(2, i)) + a(3, atVar);
    }

    @Deprecated
    static int c(int i, bk bkVar, bz bzVar) {
        int n = n(i << 3) << 1;
        b bVar = (b) bkVar;
        int b = bVar.b();
        if (b == -1) {
            b = bzVar.b(bVar);
            bVar.a(b);
        }
        return b + n;
    }
}
