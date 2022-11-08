package com.google.a.d;

import com.google.a.a;
import com.google.a.b.b;
import com.google.a.c;
import com.google.a.h;
import java.util.Map;

public final class l extends n {
    private static final int[] a = new int[]{1, 1, 1, 1};
    private static final int[] b = new int[]{3, 1, 1};
    private static final int[][] c = new int[][]{new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    public final b a(String contents, a format, int width, int height, Map<c, ?> hints) throws h {
        if (format == a.ITF) {
            return super.a(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode ITF, but got " + format);
    }

    public final boolean[] a(String contents) {
        int length = contents.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        } else if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        } else {
            boolean[] result = new boolean[((length * 9) + 9)];
            int pos = n.a(result, 0, a, true);
            for (int i = 0; i < length; i += 2) {
                int one = Character.digit(contents.charAt(i), 10);
                int two = Character.digit(contents.charAt(i + 1), 10);
                int[] encoding = new int[10];
                for (int j = 0; j < 5; j++) {
                    encoding[j * 2] = c[one][j];
                    encoding[(j * 2) + 1] = c[two][j];
                }
                pos += n.a(result, pos, encoding, true);
            }
            n.a(result, pos, b, true);
            return result;
        }
    }
}
