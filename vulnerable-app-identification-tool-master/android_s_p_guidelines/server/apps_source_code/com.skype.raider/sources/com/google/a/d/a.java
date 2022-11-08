package com.google.a.d;

public final class a extends m {
    static final char[] a = "0123456789-$:/.+ABCD".toCharArray();
    static final int[] b = new int[]{3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final char[] c = new char[]{'A', 'B', 'C', 'D'};

    static boolean a(char[] array, char key) {
        if (array == null) {
            return false;
        }
        for (char c : array) {
            if (c == key) {
                return true;
            }
        }
        return false;
    }
}
