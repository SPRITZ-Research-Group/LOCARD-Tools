package com.google.android.exoplayer2.d;

import java.util.Arrays;

public final class f {
    private int a;
    private long[] b;

    public f() {
        this((byte) 0);
    }

    private f(byte b) {
        this.b = new long[32];
    }

    public final void a(long value) {
        if (this.a == this.b.length) {
            this.b = Arrays.copyOf(this.b, this.a * 2);
        }
        long[] jArr = this.b;
        int i = this.a;
        this.a = i + 1;
        jArr[i] = value;
    }

    public final long a(int index) {
        if (index >= 0 && index < this.a) {
            return this.b[index];
        }
        throw new IndexOutOfBoundsException("Invalid index " + index + ", size is " + this.a);
    }

    public final int a() {
        return this.a;
    }

    public final long[] b() {
        return Arrays.copyOf(this.b, this.a);
    }
}
