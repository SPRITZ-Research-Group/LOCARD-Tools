package com.facebook.common.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class a {
    public static long a(InputStream from, OutputStream to) throws IOException {
        h.a((Object) from);
        h.a((Object) to);
        byte[] buf = new byte[4096];
        long total = 0;
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                return total;
            }
            to.write(buf, 0, r);
            total += (long) r;
        }
    }

    public static int a(InputStream in, byte[] b, int len) throws IOException {
        h.a((Object) in);
        h.a((Object) b);
        if (len < 0) {
            throw new IndexOutOfBoundsException("len is negative");
        }
        int total = 0;
        while (total < len) {
            int result = in.read(b, total + 0, len - total);
            if (result == -1) {
                break;
            }
            total += result;
        }
        return total;
    }
}
