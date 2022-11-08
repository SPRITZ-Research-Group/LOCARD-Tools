package com.facebook.imagepipeline.common;

import com.facebook.common.i.b;
import com.facebook.common.internal.h;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class a {
    @Nullable
    private static Pattern c;
    public final int a;
    public final int b;

    public a(int from, int to) {
        this.a = from;
        this.b = to;
    }

    public final String a() {
        return String.format(null, "bytes=%s-%s", new Object[]{a(this.a), a(this.b)});
    }

    public final String toString() {
        return String.format(null, "%s-%s", new Object[]{a(this.a), a(this.b)});
    }

    private static String a(int n) {
        if (n == Integer.MAX_VALUE) {
            return "";
        }
        return Integer.toString(n);
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof a)) {
            return false;
        }
        a that = (a) other;
        if (this.a == that.a && this.b == that.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return b.a(this.a, this.b);
    }

    @Nullable
    public static a a(@Nullable String header) throws IllegalArgumentException {
        if (header == null) {
            return null;
        }
        if (c == null) {
            c = Pattern.compile("[-/ ]");
        }
        try {
            boolean z;
            String[] headerParts = c.split(header);
            if (headerParts.length == 4) {
                z = true;
            } else {
                z = false;
            }
            h.a(z);
            h.a(headerParts[0].equals("bytes"));
            int from = Integer.parseInt(headerParts[1]);
            int to = Integer.parseInt(headerParts[2]);
            int length = Integer.parseInt(headerParts[3]);
            if (to > from) {
                z = true;
            } else {
                z = false;
            }
            h.a(z);
            if (length > to) {
                z = true;
            } else {
                z = false;
            }
            h.a(z);
            if (to < length - 1) {
                return new a(from, to);
            }
            return new a(from, Integer.MAX_VALUE);
        } catch (IllegalArgumentException x) {
            throw new IllegalArgumentException(String.format(null, "Invalid Content-Range header value: \"%s\"", new Object[]{header}), x);
        }
    }
}
