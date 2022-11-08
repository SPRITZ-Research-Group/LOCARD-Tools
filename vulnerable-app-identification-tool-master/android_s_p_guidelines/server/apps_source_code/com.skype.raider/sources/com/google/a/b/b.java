package com.google.a.b;

import java.util.Arrays;

public final class b implements Cloneable {
    private final int a;
    private final int b;
    private final int c;
    private final int[] d;

    public b(int dimension) {
        this(dimension, dimension);
    }

    public b(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.a = width;
        this.b = height;
        this.c = (width + 31) / 32;
        this.d = new int[(this.c * height)];
    }

    private b(int width, int height, int rowSize, int[] bits) {
        this.a = width;
        this.b = height;
        this.c = rowSize;
        this.d = bits;
    }

    public final boolean a(int x, int y) {
        return ((this.d[(this.c * y) + (x / 32)] >>> (x & 31)) & 1) != 0;
    }

    public final void b(int x, int y) {
        int offset = (this.c * y) + (x / 32);
        int[] iArr = this.d;
        iArr[offset] = iArr[offset] | (1 << (x & 31));
    }

    public final void a() {
        int max = this.d.length;
        for (int i = 0; i < max; i++) {
            this.d[i] = 0;
        }
    }

    public final void a(int left, int top, int width, int height) {
        if (top < 0 || left < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int right = left + width;
            int bottom = top + height;
            if (bottom > this.b || right > this.a) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            for (int y = top; y < bottom; y++) {
                int offset = y * this.c;
                for (int x = left; x < right; x++) {
                    int[] iArr = this.d;
                    int i = (x / 32) + offset;
                    iArr[i] = iArr[i] | (1 << (x & 31));
                }
            }
        }
    }

    public final int b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof b)) {
            return false;
        }
        b other = (b) o;
        if (this.a == other.a && this.b == other.b && this.c == other.c && Arrays.equals(this.d, other.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((((((this.a * 31) + this.a) * 31) + this.b) * 31) + this.c) * 31) + Arrays.hashCode(this.d);
    }

    public final String toString() {
        String str = "X ";
        String str2 = "  ";
        String str3 = "\n";
        StringBuilder stringBuilder = new StringBuilder(this.b * (this.a + 1));
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.a; i2++) {
                String str4;
                if (a(i2, i)) {
                    str4 = str;
                } else {
                    str4 = str2;
                }
                stringBuilder.append(str4);
            }
            stringBuilder.append(str3);
        }
        return stringBuilder.toString();
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new b(this.a, this.b, this.c, (int[]) this.d.clone());
    }
}
