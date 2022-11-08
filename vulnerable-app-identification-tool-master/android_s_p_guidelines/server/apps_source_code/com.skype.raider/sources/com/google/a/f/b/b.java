package com.google.a.f.b;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class b {
    private final byte[][] a;
    private final int b;
    private final int c;

    public b(int width, int height) {
        this.a = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{height, width});
        this.b = width;
        this.c = height;
    }

    public final int a() {
        return this.c;
    }

    public final int b() {
        return this.b;
    }

    public final byte a(int x, int y) {
        return this.a[y][x];
    }

    public final byte[][] c() {
        return this.a;
    }

    public final void a(int x, int y, int value) {
        this.a[y][x] = (byte) value;
    }

    public final void a(int x, int y, boolean value) {
        this.a[y][x] = (byte) (value ? 1 : 0);
    }

    public final void d() {
        for (byte[] fill : this.a) {
            Arrays.fill(fill, (byte) -1);
        }
    }

    public final String toString() {
        StringBuilder result = new StringBuilder(((this.b * 2) * this.c) + 2);
        for (int y = 0; y < this.c; y++) {
            byte[] bytesY = this.a[y];
            for (int x = 0; x < this.b; x++) {
                switch (bytesY[x]) {
                    case (byte) 0:
                        result.append(" 0");
                        break;
                    case (byte) 1:
                        result.append(" 1");
                        break;
                    default:
                        result.append("  ");
                        break;
                }
            }
            result.append(10);
        }
        return result.toString();
    }
}
