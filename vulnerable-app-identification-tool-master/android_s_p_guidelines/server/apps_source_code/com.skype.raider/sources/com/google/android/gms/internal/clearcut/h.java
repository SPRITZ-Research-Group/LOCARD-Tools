package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class h implements Serializable, Iterable<Byte> {
    public static final h a = new o(aj.b);
    private static final l b = (f.a() ? new p() : new j());
    private int c = 0;

    h() {
    }

    static int a(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((((i | i2) | i4) | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        } else {
            throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
        }
    }

    public static h a(String str) {
        return new o(str.getBytes(aj.a));
    }

    static m c(int i) {
        return new m(i, (byte) 0);
    }

    public abstract byte a(int i);

    public abstract int a();

    protected abstract int a(int i, int i2);

    protected abstract String a(Charset charset);

    abstract void a(g gVar) throws IOException;

    public abstract h b(int i);

    public final String b() {
        return a() == 0 ? "" : a(aj.a);
    }

    public abstract boolean c();

    protected final int d() {
        return this.c;
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.c;
        if (i == 0) {
            i = a();
            i = a(i, i);
            if (i == 0) {
                i = 1;
            }
            this.c = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new i(this);
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(a())});
    }
}
