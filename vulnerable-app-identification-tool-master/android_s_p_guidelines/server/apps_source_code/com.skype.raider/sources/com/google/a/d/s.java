package com.google.a.d;

import com.google.a.a;
import com.google.a.b.b;
import com.google.a.c;
import com.google.a.d;
import com.google.a.h;
import java.util.Map;

public final class s extends q {
    public final b a(String contents, a format, int width, int height, Map<c, ?> hints) throws h {
        if (format == a.UPC_E) {
            return super.a(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode UPC_E, but got " + format);
    }

    public final boolean[] a(String contents) {
        int length = contents.length();
        switch (length) {
            case 7:
                try {
                    contents = contents + p.b(r.a(contents));
                    break;
                } catch (d fe) {
                    throw new IllegalArgumentException(fe);
                }
            case 8:
                try {
                    if (!p.a(contents)) {
                        throw new IllegalArgumentException("Contents do not pass checksum");
                    }
                } catch (d e) {
                    throw new IllegalArgumentException("Illegal contents");
                }
                break;
            default:
                throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + length);
        }
        int firstDigit = Character.digit(contents.charAt(0), 10);
        if (firstDigit == 0 || firstDigit == 1) {
            int parities = r.a[firstDigit][Character.digit(contents.charAt(7), 10)];
            boolean[] result = new boolean[51];
            int pos = n.a(result, 0, p.b, true) + 0;
            for (int i = 1; i <= 6; i++) {
                int digit = Character.digit(contents.charAt(i), 10);
                if (((parities >> (6 - i)) & 1) == 1) {
                    digit += 10;
                }
                pos += n.a(result, pos, p.f[digit], false);
            }
            n.a(result, pos, p.d, false);
            return result;
        }
        throw new IllegalArgumentException("Number system must be 0 or 1");
    }
}
