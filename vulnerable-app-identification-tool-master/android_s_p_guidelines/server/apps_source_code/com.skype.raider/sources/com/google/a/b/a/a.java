package com.google.a.b.a;

import com.skype.Defines;

public final class a {
    public static final a a = new a(4201, 4096, 1);
    public static final a b = new a(1033, 1024, 1);
    public static final a c = new a(67, 64, 1);
    public static final a d = new a(19, 16, 1);
    public static final a e = new a(285, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 0);
    public static final a f;
    public static final a g;
    public static final a h = c;
    private final int[] i;
    private final int[] j;
    private final b k;
    private final b l;
    private final int m;
    private final int n;
    private final int o;

    static {
        a aVar = new a(301, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, 1);
        f = aVar;
        g = aVar;
    }

    private a(int primitive, int size, int b) {
        int i;
        this.n = primitive;
        this.m = size;
        this.o = b;
        this.i = new int[size];
        this.j = new int[size];
        int x = 1;
        for (i = 0; i < size; i++) {
            this.i[i] = x;
            x <<= 1;
            if (x >= size) {
                x = (x ^ primitive) & (size - 1);
            }
        }
        for (i = 0; i < size - 1; i++) {
            this.j[this.i[i]] = i;
        }
        this.k = new b(this, new int[]{0});
        this.l = new b(this, new int[]{1});
    }

    final b a() {
        return this.k;
    }

    final b a(int degree, int coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException();
        } else if (coefficient == 0) {
            return this.k;
        } else {
            int[] coefficients = new int[(degree + 1)];
            coefficients[0] = coefficient;
            return new b(this, coefficients);
        }
    }

    static int b(int a, int b) {
        return a ^ b;
    }

    final int a(int a) {
        return this.i[a];
    }

    final int b(int a) {
        if (a != 0) {
            return this.j[a];
        }
        throw new IllegalArgumentException();
    }

    final int c(int a) {
        if (a != 0) {
            return this.i[(this.m - this.j[a]) - 1];
        }
        throw new ArithmeticException();
    }

    final int c(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return this.i[(this.j[a] + this.j[b]) % (this.m - 1)];
    }

    public final int b() {
        return this.o;
    }

    public final String toString() {
        return "GF(0x" + Integer.toHexString(this.n) + ',' + this.m + ')';
    }
}
