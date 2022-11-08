package com.google.a.e.a;

import java.lang.reflect.Array;

public final class a {
    private final b[] a;
    private int b;
    private final int c;
    private final int d;

    a(int height, int width) {
        this.a = new b[height];
        int matrixLength = this.a.length;
        for (int i = 0; i < matrixLength; i++) {
            this.a[i] = new b(((width + 4) * 17) + 1);
        }
        this.d = width * 17;
        this.c = height;
        this.b = -1;
    }

    final void a() {
        this.b++;
    }

    final b b() {
        return this.a[this.b];
    }

    public final byte[][] a(int xScale, int yScale) {
        byte[][] matrixOut = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.c * yScale, this.d * xScale});
        int yMax = this.c * yScale;
        for (int i = 0; i < yMax; i++) {
            matrixOut[(yMax - i) - 1] = this.a[i / yScale].a(xScale);
        }
        return matrixOut;
    }
}
