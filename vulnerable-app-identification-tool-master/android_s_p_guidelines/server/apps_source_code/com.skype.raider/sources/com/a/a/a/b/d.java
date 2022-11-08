package com.a.a.a.b;

import com.adjust.sdk.Constants;

public final class d {
    static final String a = "-9223372036854775808";
    static final char[] b = new char[4000];
    static final char[] c = new char[4000];
    static final byte[] d = new byte[4000];
    static final String[] e = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static final String[] f = new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    private static int g = 1000000;
    private static int h = 1000000000;
    private static long i = 10000000000L;
    private static long j = 1000;
    private static long k = -2147483648L;
    private static long l = 2147483647L;

    static {
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            char c;
            char c2 = (char) (i2 + 48);
            if (i2 == 0) {
                c = 0;
            } else {
                c = c2;
            }
            int i3 = 0;
            while (i3 < 10) {
                char c3;
                char c4 = (char) (i3 + 48);
                if (i2 == 0 && i3 == 0) {
                    c3 = 0;
                } else {
                    c3 = c4;
                }
                int i4 = i;
                for (i = 0; i < 10; i++) {
                    char c5 = (char) (i + 48);
                    b[i4] = c;
                    b[i4 + 1] = c3;
                    b[i4 + 2] = c5;
                    c[i4] = c2;
                    c[i4 + 1] = c4;
                    c[i4 + 2] = c5;
                    i4 += 4;
                }
                i3++;
                i = i4;
            }
        }
        for (int i5 = 0; i5 < 4000; i5++) {
            d[i5] = (byte) c[i5];
        }
    }

    public static int a(int i, char[] cArr, int i2) {
        int i3;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return a((long) i, cArr, i2);
            }
            i3 = i2 + 1;
            cArr[i2] = '-';
            i = -i;
            i2 = i3;
        }
        if (i >= g) {
            Object obj = i >= h ? 1 : null;
            if (obj != null) {
                i -= h;
                if (i >= h) {
                    i -= h;
                    i3 = i2 + 1;
                    cArr[i2] = '2';
                    i2 = i3;
                } else {
                    i3 = i2 + 1;
                    cArr[i2] = '1';
                    i2 = i3;
                }
            }
            i3 = i / Constants.ONE_SECOND;
            int i4 = i - (i3 * Constants.ONE_SECOND);
            int i5 = i3 / Constants.ONE_SECOND;
            int i6 = i3 - (i5 * Constants.ONE_SECOND);
            if (obj != null) {
                i3 = c(i5, cArr, i2);
            } else {
                i3 = b(i5, cArr, i2);
            }
            return c(i4, cArr, c(i6, cArr, i3));
        } else if (i >= Constants.ONE_SECOND) {
            i3 = i / Constants.ONE_SECOND;
            return c(i - (i3 * Constants.ONE_SECOND), cArr, b(i3, cArr, i2));
        } else if (i >= 10) {
            return b(i, cArr, i2);
        } else {
            i3 = i2 + 1;
            cArr[i2] = (char) (i + 48);
            return i3;
        }
    }

    public static int a(long j, char[] cArr, int i) {
        int length;
        if (j < 0) {
            if (j > k) {
                return a((int) j, cArr, i);
            }
            if (j == Long.MIN_VALUE) {
                length = a.length();
                a.getChars(0, length, cArr, i);
                return i + length;
            }
            length = i + 1;
            cArr[i] = '-';
            j = -j;
            i = length;
        } else if (j <= l) {
            return a((int) j, cArr, i);
        }
        int i2 = 10;
        for (long j2 = i; j >= j2 && i2 != 19; j2 = (j2 << 1) + (j2 << 3)) {
            i2++;
        }
        int i3 = i + i2;
        length = i3;
        while (j > l) {
            length -= 3;
            long j3 = j / j;
            c((int) (j - (j * j3)), cArr, length);
            j = j3;
        }
        i2 = length;
        length = (int) j;
        while (length >= Constants.ONE_SECOND) {
            int i4 = i2 - 3;
            i2 = length / Constants.ONE_SECOND;
            c(length - (i2 * Constants.ONE_SECOND), cArr, i4);
            length = i2;
            i2 = i4;
        }
        b(length, cArr, i);
        return i3;
    }

    private static int b(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = b[i3];
        if (c != 0) {
            i3 = i2 + 1;
            cArr[i2] = c;
            i2 = i3;
        }
        int i5 = i4 + 1;
        char c2 = b[i4];
        if (c2 != 0) {
            i3 = i2 + 1;
            cArr[i2] = c2;
            i2 = i3;
        }
        i3 = i2 + 1;
        cArr[i2] = b[i5];
        return i3;
    }

    private static int c(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        cArr[i2] = c[i3];
        i3 = i4 + 1;
        int i6 = i5 + 1;
        cArr[i4] = c[i5];
        i4 = i3 + 1;
        cArr[i3] = c[i6];
        return i4;
    }
}
