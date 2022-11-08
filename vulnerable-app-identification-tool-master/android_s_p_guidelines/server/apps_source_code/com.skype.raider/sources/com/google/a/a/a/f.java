package com.google.a.a.a;

import com.google.a.b.a;
import java.util.Deque;
import java.util.LinkedList;

final class f {
    static final f a = new f(g.a, 0, 0, 0);
    private final int b;
    private final g c;
    private final int d;
    private final int e;

    private f(g token, int mode, int binaryBytes, int bitCount) {
        this.c = token;
        this.b = mode;
        this.d = binaryBytes;
        this.e = bitCount;
    }

    final int a() {
        return this.b;
    }

    final int b() {
        return this.d;
    }

    final int c() {
        return this.e;
    }

    final f a(int mode, int value) {
        int bitCount = this.e;
        g token = this.c;
        if (mode != this.b) {
            int latch = d.b[this.b][mode];
            token = token.a(65535 & latch, latch >> 16);
            bitCount += latch >> 16;
        }
        int latchModeBitCount = mode == 2 ? 4 : 5;
        return new f(token.a(value, latchModeBitCount), mode, 0, bitCount + latchModeBitCount);
    }

    final f b(int mode, int value) {
        int thisModeBitCount;
        g token = this.c;
        if (this.b == 2) {
            thisModeBitCount = 4;
        } else {
            thisModeBitCount = 5;
        }
        return new f(token.a(d.c[this.b][mode], thisModeBitCount).a(value, 5), this.b, 0, (this.e + thisModeBitCount) + 5);
    }

    final f a(int index) {
        g token = this.c;
        int mode = this.b;
        int bitCount = this.e;
        if (this.b == 4 || this.b == 2) {
            int latch = d.b[mode][0];
            token = token.a(65535 & latch, latch >> 16);
            bitCount += latch >> 16;
            mode = 0;
        }
        int deltaBitCount = (this.d == 0 || this.d == 31) ? 18 : this.d == 62 ? 9 : 8;
        f result = new f(token, mode, this.d + 1, bitCount + deltaBitCount);
        if (result.d == 2078) {
            return result.b(index + 1);
        }
        return result;
    }

    final f b(int index) {
        return this.d == 0 ? this : new f(new b(this.c, index - this.d, this.d), this.b, 0, this.e);
    }

    final boolean a(f other) {
        int mySize = this.e + (d.b[this.b][other.b] >> 16);
        if (other.d > 0 && (this.d == 0 || this.d > other.d)) {
            mySize += 10;
        }
        return mySize <= other.e;
    }

    final a a(byte[] text) {
        Deque<g> symbols = new LinkedList();
        for (g token = b(text.length).c; token != null; token = token.a()) {
            symbols.addFirst(token);
        }
        a bitArray = new a();
        for (g a : symbols) {
            a.a(bitArray, text);
        }
        return bitArray;
    }

    public final String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{d.a[this.b], Integer.valueOf(this.e), Integer.valueOf(this.d)});
    }
}
