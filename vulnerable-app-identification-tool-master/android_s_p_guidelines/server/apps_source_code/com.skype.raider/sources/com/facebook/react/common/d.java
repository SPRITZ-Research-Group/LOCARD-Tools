package com.facebook.react.common;

public final class d {
    private long[] a = new long[20];
    private int b = 0;

    public static d a() {
        return new d();
    }

    private d() {
    }

    public final void a(long value) {
        if (this.b == this.a.length) {
            Object obj = new long[Math.max(this.b + 1, (int) (((double) this.b) * 1.8d))];
            System.arraycopy(this.a, 0, obj, 0, this.b);
            this.a = obj;
        }
        long[] jArr = this.a;
        int i = this.b;
        this.b = i + 1;
        jArr[i] = value;
    }

    public final long a(int index) {
        if (index < this.b) {
            return this.a[index];
        }
        throw new IndexOutOfBoundsException(index + " >= " + this.b);
    }

    public final void a(int index, long value) {
        if (index >= this.b) {
            throw new IndexOutOfBoundsException(index + " >= " + this.b);
        }
        this.a[index] = value;
    }

    public final int b() {
        return this.b;
    }

    public final void b(int n) {
        if (n > this.b) {
            throw new IndexOutOfBoundsException("Trying to drop " + n + " items from array of length " + this.b);
        }
        this.b -= n;
    }
}
