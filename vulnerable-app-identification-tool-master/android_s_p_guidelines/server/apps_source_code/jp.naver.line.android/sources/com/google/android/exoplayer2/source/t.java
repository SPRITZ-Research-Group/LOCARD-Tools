package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.s;
import defpackage.ath;
import defpackage.auh;
import defpackage.bcz;
import defpackage.beg;

final class t {
    private int a = 1000;
    private int[] b = new int[this.a];
    private long[] c = new long[this.a];
    private int[] d = new int[this.a];
    private int[] e = new int[this.a];
    private long[] f = new long[this.a];
    private auh[] g = new auh[this.a];
    private Format[] h = new Format[this.a];
    private int i;
    private int j;
    private int k;
    private int l;
    private long m = Long.MIN_VALUE;
    private long n = Long.MIN_VALUE;
    private boolean o = true;
    private boolean p = true;
    private Format q;
    private int r;

    public final void a(boolean z) {
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.o = true;
        this.m = Long.MIN_VALUE;
        this.n = Long.MIN_VALUE;
        if (z) {
            this.q = null;
            this.p = true;
        }
    }

    public final void a(int i) {
        this.r = i;
    }

    public final int a() {
        return this.j + this.l;
    }

    public final int b() {
        return c() ? this.b[d(this.l)] : this.r;
    }

    public final synchronized boolean c() {
        return this.l != this.i;
    }

    public final synchronized Format d() {
        if (this.p) {
            return null;
        }
        return this.q;
    }

    public final synchronized long e() {
        return this.n;
    }

    public final synchronized void f() {
        this.l = 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int a(s sVar, ath ath, boolean z, boolean z2, Format format, u uVar) {
        if (c()) {
            int d = d(this.l);
            if (z || this.h[d] != format) {
                sVar.a = this.h[d];
                return -5;
            } else if (ath.f()) {
                return -3;
            } else {
                ath.c = this.f[d];
                ath.a_(this.e[d]);
                uVar.a = this.d[d];
                uVar.b = this.c[d];
                uVar.c = this.g[d];
                this.l++;
                return -4;
            }
        } else if (z2) {
            ath.a_(4);
            return -4;
        } else if (this.q != null && (z || this.q != format)) {
            sVar.a = this.q;
            return -5;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int a(long j, boolean z) {
        int d = d(this.l);
        if (c() && j >= this.f[d] && (j <= this.n || z)) {
            int a = a(d, this.i - this.l, j, true);
            if (a == -1) {
                return -1;
            }
            this.l += a;
            return a;
        }
    }

    public final synchronized int g() {
        int i;
        i = this.i - this.l;
        this.l = this.i;
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long a(long j, boolean z, boolean z2) {
        if (this.i != 0 && j >= this.f[this.k]) {
            int i = (!z2 || this.l == this.i) ? this.i : this.l + 1;
            int a = a(this.k, i, j, z);
            if (a == -1) {
                return -1;
            }
            return b(a);
        }
    }

    public final synchronized long h() {
        if (this.i == 0) {
            return -1;
        }
        return b(this.i);
    }

    public final synchronized boolean a(Format format) {
        if (format == null) {
            this.p = true;
            return false;
        }
        this.p = false;
        if (beg.a((Object) format, this.q)) {
            return false;
        }
        this.q = format;
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(long j, int i, long j2, int i2, auh auh) {
        if (this.o) {
            if ((i & 1) != 0) {
                this.o = false;
            } else {
                return;
            }
        }
        bcz.b(this.p ^ 1);
        b(j);
        int d = d(this.i);
        this.f[d] = j;
        this.c[d] = j2;
        this.d[d] = i2;
        this.e[d] = i;
        this.g[d] = auh;
        this.h[d] = this.q;
        this.b[d] = this.r;
        this.i++;
        if (this.i == this.a) {
            int i3 = this.a + 1000;
            Object obj = new int[i3];
            Object obj2 = new long[i3];
            Object obj3 = new long[i3];
            Object obj4 = new int[i3];
            Object obj5 = new int[i3];
            Object obj6 = new auh[i3];
            Object obj7 = new Format[i3];
            int i4 = this.a - this.k;
            System.arraycopy(this.c, this.k, obj2, 0, i4);
            System.arraycopy(this.f, this.k, obj3, 0, i4);
            System.arraycopy(this.e, this.k, obj4, 0, i4);
            System.arraycopy(this.d, this.k, obj5, 0, i4);
            System.arraycopy(this.g, this.k, obj6, 0, i4);
            System.arraycopy(this.h, this.k, obj7, 0, i4);
            System.arraycopy(this.b, this.k, obj, 0, i4);
            int i5 = this.k;
            System.arraycopy(this.c, 0, obj2, i4, i5);
            System.arraycopy(this.f, 0, obj3, i4, i5);
            System.arraycopy(this.e, 0, obj4, i4, i5);
            System.arraycopy(this.d, 0, obj5, i4, i5);
            System.arraycopy(this.g, 0, obj6, i4, i5);
            System.arraycopy(this.h, 0, obj7, i4, i5);
            System.arraycopy(this.b, 0, obj, i4, i5);
            this.c = obj2;
            this.f = obj3;
            this.e = obj4;
            this.d = obj5;
            this.g = obj6;
            this.h = obj7;
            this.b = obj;
            this.k = 0;
            this.i = this.a;
            this.a = i3;
        }
    }

    private synchronized void b(long j) {
        this.n = Math.max(this.n, j);
    }

    public final synchronized boolean a(long j) {
        boolean z = false;
        if (this.i == 0) {
            return j > this.m;
        } else {
            if (Math.max(this.m, c(this.l)) >= j) {
                return false;
            }
            int i = this.i;
            int d = d(this.i - 1);
            while (i > this.l && this.f[d] >= j) {
                i--;
                d--;
                if (d == -1) {
                    d = this.a - 1;
                }
            }
            int i2 = (this.j + this.i) - (this.j + i);
            if (i2 >= 0 && i2 <= this.i - this.l) {
                z = true;
            }
            bcz.a(z);
            this.i -= i2;
            this.n = Math.max(this.m, c(this.i));
            return true;
        }
    }

    private int a(int i, int i2, long j, boolean z) {
        int i3 = i;
        int i4 = -1;
        for (i = 0; i < i2 && this.f[i3] <= j; i++) {
            if (!(z && (this.e[i3] & 1) == 0)) {
                i4 = i;
            }
            i3++;
            if (i3 == this.a) {
                i3 = 0;
            }
        }
        return i4;
    }

    private long b(int i) {
        this.m = Math.max(this.m, c(i));
        this.i -= i;
        this.j += i;
        this.k += i;
        if (this.k >= this.a) {
            this.k -= this.a;
        }
        this.l -= i;
        if (this.l < 0) {
            this.l = 0;
        }
        if (this.i != 0) {
            return this.c[this.k];
        }
        i = (this.k == 0 ? this.a : this.k) - 1;
        return this.c[i] + ((long) this.d[i]);
    }

    private long c(int i) {
        long j = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int d = d(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            j = Math.max(j, this.f[d]);
            if ((this.e[d] & 1) != 0) {
                break;
            }
            d--;
            if (d == -1) {
                d = this.a - 1;
            }
        }
        return j;
    }

    private int d(int i) {
        int i2 = this.k + i;
        return i2 < this.a ? i2 : i2 - this.a;
    }
}
