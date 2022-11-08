package com.google.a.d;

import com.google.a.a;
import com.google.a.b.b;
import com.google.a.c;
import com.google.a.d;
import com.google.a.h;
import java.util.Map;

public final class j extends q {
    public final b a(String contents, a format, int width, int height, Map<c, ?> hints) throws h {
        if (format == a.EAN_13) {
            return super.a(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got " + format);
    }

    public final boolean[] a(String contents) {
        int i;
        int length = contents.length();
        switch (length) {
            case 12:
                try {
                    contents = contents + p.b(contents);
                    break;
                } catch (d fe) {
                    throw new IllegalArgumentException(fe);
                }
            case 13:
                try {
                    if (!p.a(contents)) {
                        throw new IllegalArgumentException("Contents do not pass checksum");
                    }
                } catch (d e) {
                    throw new IllegalArgumentException("Illegal contents");
                }
                break;
            default:
                throw new IllegalArgumentException("Requested contents should be 12 or 13 digits long, but got " + length);
        }
        int parities = i.a[Character.digit(contents.charAt(0), 10)];
        boolean[] result = new boolean[95];
        int pos = n.a(result, 0, p.b, true) + 0;
        for (i = 1; i <= 6; i++) {
            int digit = Character.digit(contents.charAt(i), 10);
            if (((parities >> (6 - i)) & 1) == 1) {
                digit += 10;
            }
            pos += n.a(result, pos, p.f[digit], false);
        }
        pos += n.a(result, pos, p.c, false);
        for (i = 7; i <= 12; i++) {
            pos += n.a(result, pos, p.e[Character.digit(contents.charAt(i), 10)], true);
        }
        n.a(result, pos, p.b, true);
        return result;
    }
}
