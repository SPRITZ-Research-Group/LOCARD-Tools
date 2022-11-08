package com.facebook.imagepipeline.common;

import com.facebook.common.i.b;
import com.facebook.common.internal.h;

public final class e {
    public final int a;
    public final int b;
    public final float c;
    public final float d;

    public e(int width, int height) {
        this(width, height, (byte) 0);
    }

    private e(int width, int height, byte b) {
        this(width, height, 0);
    }

    private e(int width, int height, char c) {
        boolean z = true;
        h.a(width > 0);
        if (height <= 0) {
            z = false;
        }
        h.a(z);
        this.a = width;
        this.b = height;
        this.c = 2048.0f;
        this.d = 0.6666667f;
    }

    public final int hashCode() {
        return b.a(this.a, this.b);
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof e)) {
            return false;
        }
        e that = (e) other;
        if (this.a == that.a && this.b == that.b) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return String.format(null, "%dx%d", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)});
    }
}
