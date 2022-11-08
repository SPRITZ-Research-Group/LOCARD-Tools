package com.google.a.d;

import com.google.a.a;
import com.google.a.b.b;
import com.google.a.c;
import java.util.Map;

public final class h extends n {
    public final b a(String contents, a format, int width, int height, Map<c, ?> hints) throws com.google.a.h {
        if (format == a.CODE_93) {
            return super.a(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode CODE_93, but got " + format);
    }

    public final boolean[] a(String contents) {
        int length = contents.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        }
        int[] widths = new int[9];
        int codeWidth = (((contents.length() + 2) + 2) * 9) + 1;
        a(g.a[47], widths);
        boolean[] result = new boolean[codeWidth];
        int pos = a(result, 0, widths);
        for (int i = 0; i < length; i++) {
            a(g.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents.charAt(i))], widths);
            pos += a(result, pos, widths);
        }
        int check1 = a(contents, 20);
        a(g.a[check1], widths);
        pos += a(result, pos, widths);
        a(g.a[a(contents + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(check1), 15)], widths);
        pos += a(result, pos, widths);
        a(g.a[47], widths);
        result[pos + a(result, pos, widths)] = true;
        return result;
    }

    private static void a(int a, int[] toReturn) {
        for (int i = 0; i < 9; i++) {
            int i2;
            if ((a & (1 << (8 - i))) == 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            toReturn[i] = i2;
        }
    }

    private static int a(boolean[] target, int pos, int[] pattern) {
        int i = 0;
        int pos2 = pos;
        while (i < 9) {
            boolean z;
            pos = pos2 + 1;
            if (pattern[i] != 0) {
                z = true;
            } else {
                z = false;
            }
            target[pos2] = z;
            i++;
            pos2 = pos;
        }
        return 9;
    }

    private static int a(String contents, int maxWeight) {
        int weight = 1;
        int total = 0;
        for (int i = contents.length() - 1; i >= 0; i--) {
            total += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(contents.charAt(i)) * weight;
            weight++;
            if (weight > maxWeight) {
                weight = 1;
            }
        }
        return total % 47;
    }
}
