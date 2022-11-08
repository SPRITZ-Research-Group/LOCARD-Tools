package com.facebook.imageformat;

import com.facebook.common.internal.f;
import com.facebook.common.internal.h;
import com.facebook.common.j.b;
import javax.annotation.Nullable;

public final class a implements com.facebook.imageformat.c.a {
    private static final byte[] b = new byte[]{(byte) -1, (byte) -40, (byte) -1};
    private static final int c = 3;
    private static final byte[] d = new byte[]{(byte) -119, (byte) 80, (byte) 78, (byte) 71, (byte) 13, (byte) 10, (byte) 26, (byte) 10};
    private static final int e = 8;
    private static final byte[] f = e.a("GIF87a");
    private static final byte[] g = e.a("GIF89a");
    private static final byte[] h;
    private static final int i;
    private static final byte[] j = new byte[]{(byte) 0, (byte) 0, (byte) 1, (byte) 0};
    private static final int k = 4;
    private static final String[] l = new String[]{"heic", "heix", "hevc", "hevx", "mif1", "msf1"};
    private static final int m = e.a("ftyp" + l[0]).length;
    final int a = f.a(21, 20, c, e, 6, i, k, m);

    public final int a() {
        return this.a;
    }

    @Nullable
    public final c a(byte[] headerBytes, int headerSize) {
        Object obj = 1;
        h.a((Object) headerBytes);
        if (b.b(headerBytes, headerSize)) {
            h.a(b.b(headerBytes, headerSize));
            if (b.b(headerBytes)) {
                return b.f;
            }
            if (b.c(headerBytes)) {
                return b.g;
            }
            if (!b.a(headerBytes, headerSize)) {
                return c.a;
            }
            if (b.a(headerBytes)) {
                return b.j;
            }
            if (b.d(headerBytes)) {
                return b.i;
            }
            return b.h;
        }
        Object obj2;
        if (headerSize < b.length || !e.a(headerBytes, b)) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        if (obj2 != null) {
            return b.a;
        }
        if (headerSize < d.length || !e.a(headerBytes, d)) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        if (obj2 != null) {
            return b.b;
        }
        if (headerSize < 6 || !(e.a(headerBytes, f) || e.a(headerBytes, g))) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        if (obj2 != null) {
            return b.c;
        }
        boolean z;
        if (headerSize < h.length) {
            z = false;
        } else {
            z = e.a(headerBytes, h);
        }
        if (z) {
            return b.d;
        }
        if (headerSize < j.length) {
            z = false;
        } else {
            z = e.a(headerBytes, j);
        }
        if (z) {
            return b.e;
        }
        if (headerSize >= m && headerBytes[3] >= (byte) 8) {
            for (String str : l) {
                if (e.a(headerBytes, headerBytes.length, e.a("ftyp" + str), m) >= 0) {
                    break;
                }
            }
        }
        obj = null;
        if (obj != null) {
            return b.k;
        }
        return c.a;
    }

    static {
        byte[] a = e.a("BM");
        h = a;
        i = a.length;
    }
}
