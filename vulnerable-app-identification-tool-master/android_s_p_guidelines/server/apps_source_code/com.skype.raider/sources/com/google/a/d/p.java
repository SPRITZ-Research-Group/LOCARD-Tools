package com.google.a.d;

import com.google.a.d;

public abstract class p extends m {
    static final int[] b = new int[]{1, 1, 1};
    static final int[] c = new int[]{1, 1, 1, 1, 1};
    static final int[] d = new int[]{1, 1, 1, 1, 1, 1};
    static final int[][] e = new int[][]{new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    static final int[][] f = new int[20][];

    static {
        System.arraycopy(e, 0, f, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] widths = e[i - 10];
            int[] reversedWidths = new int[widths.length];
            for (int j = 0; j < widths.length; j++) {
                reversedWidths[j] = widths[(widths.length - j) - 1];
            }
            f[i] = reversedWidths;
        }
    }

    static boolean a(CharSequence s) throws d {
        int length = s.length();
        if (length != 0 && b(s.subSequence(0, length - 1)) == Character.digit(s.charAt(length - 1), 10)) {
            return true;
        }
        return false;
    }

    static int b(CharSequence s) throws d {
        int i;
        int digit;
        int length = s.length();
        int sum = 0;
        for (i = length - 1; i >= 0; i -= 2) {
            digit = s.charAt(i) - 48;
            if (digit < 0 || digit > 9) {
                throw d.a();
            }
            sum += digit;
        }
        sum *= 3;
        for (i = length - 2; i >= 0; i -= 2) {
            digit = s.charAt(i) - 48;
            if (digit < 0 || digit > 9) {
                throw d.a();
            }
            sum += digit;
        }
        return (1000 - sum) % 10;
    }
}
