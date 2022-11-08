package com.google.a.b;

import java.util.Arrays;

public final class a implements Cloneable {
    private int[] a;
    private int b;

    public a() {
        this.b = 0;
        this.a = new int[1];
    }

    private a(int[] bits, int size) {
        this.a = bits;
        this.b = size;
    }

    public final int a() {
        return this.b;
    }

    public final int b() {
        return (this.b + 7) / 8;
    }

    private void b(int size) {
        if (size > (this.a.length << 5)) {
            int[] newBits = new int[((size + 31) / 32)];
            System.arraycopy(this.a, 0, newBits, 0, this.a.length);
            this.a = newBits;
        }
    }

    public final boolean a(int i) {
        return (this.a[i / 32] & (1 << (i & 31))) != 0;
    }

    public final void a(boolean bit) {
        b(this.b + 1);
        if (bit) {
            int[] iArr = this.a;
            int i = this.b / 32;
            iArr[i] = iArr[i] | (1 << (this.b & 31));
        }
        this.b++;
    }

    public final void a(int value, int numBits) {
        if (numBits < 0 || numBits > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        b(this.b + numBits);
        for (int numBitsLeft = numBits; numBitsLeft > 0; numBitsLeft--) {
            a(((value >> (numBitsLeft + -1)) & 1) == 1);
        }
    }

    public final void a(a other) {
        int otherSize = other.b;
        b(this.b + otherSize);
        for (int i = 0; i < otherSize; i++) {
            a(other.a(i));
        }
    }

    public final void b(a other) {
        if (this.b != other.b) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i = 0; i < this.a.length; i++) {
            int[] iArr = this.a;
            iArr[i] = iArr[i] ^ other.a[i];
        }
    }

    public final void a(int bitOffset, byte[] array, int numBytes) {
        for (int i = 0; i < numBytes; i++) {
            int theByte = 0;
            for (int j = 0; j < 8; j++) {
                if (a(bitOffset)) {
                    theByte |= 1 << (7 - j);
                }
                bitOffset++;
            }
            array[i + 0] = (byte) theByte;
        }
    }

    public final boolean equals(Object o) {
        if (!(o instanceof a)) {
            return false;
        }
        a other = (a) o;
        if (this.b == other.b && Arrays.equals(this.a, other.a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.b * 31) + Arrays.hashCode(this.a);
    }

    public final String toString() {
        StringBuilder result = new StringBuilder(this.b);
        for (int i = 0; i < this.b; i++) {
            if ((i & 7) == 0) {
                result.append(' ');
            }
            result.append(a(i) ? 'X' : '.');
        }
        return result.toString();
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new a((int[]) this.a.clone(), this.b);
    }
}
