package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.d.a;
import java.util.Arrays;

final class m {
    public byte[] a = new byte[131];
    public int b;
    private final int c;
    private boolean d;
    private boolean e;

    public m(int targetType) {
        this.c = targetType;
        this.a[2] = (byte) 1;
    }

    public final void a() {
        this.d = false;
        this.e = false;
    }

    public final boolean b() {
        return this.e;
    }

    public final void a(int type) {
        boolean z = true;
        a.b(!this.d);
        if (type != this.c) {
            z = false;
        }
        this.d = z;
        if (this.d) {
            this.b = 3;
            this.e = false;
        }
    }

    public final void a(byte[] data, int offset, int limit) {
        if (this.d) {
            int readLength = limit - offset;
            if (this.a.length < this.b + readLength) {
                this.a = Arrays.copyOf(this.a, (this.b + readLength) * 2);
            }
            System.arraycopy(data, offset, this.a, this.b, readLength);
            this.b += readLength;
        }
    }

    public final boolean b(int discardPadding) {
        if (!this.d) {
            return false;
        }
        this.b -= discardPadding;
        this.d = false;
        this.e = true;
        return true;
    }
}
