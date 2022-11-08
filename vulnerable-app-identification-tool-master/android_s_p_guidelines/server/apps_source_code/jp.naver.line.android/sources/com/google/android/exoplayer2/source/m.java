package com.google.android.exoplayer2.source;

public final class m {
    public final Object a;
    public final int b;
    public final int c;
    public final long d;
    public final long e;

    public m(Object obj) {
        this(obj, (byte) 0);
    }

    private m(Object obj, byte b) {
        this(obj, -1, -1, -1, Long.MIN_VALUE);
    }

    public m(Object obj, long j, long j2) {
        this(obj, -1, -1, j, j2);
    }

    public m(Object obj, int i, int i2, long j) {
        this(obj, i, i2, j, Long.MIN_VALUE);
    }

    private m(Object obj, int i, int i2, long j, long j2) {
        this.a = obj;
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = j2;
    }

    public final boolean a() {
        return this.b != -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        return this.a.equals(mVar.a) && this.b == mVar.b && this.c == mVar.c && this.d == mVar.d && this.e == mVar.e;
    }

    public final int hashCode() {
        return ((((((((this.a.hashCode() + 527) * 31) + this.b) * 31) + this.c) * 31) + ((int) this.d)) * 31) + ((int) this.e);
    }
}
