package com.google.android.gms.common.util;

public final class i {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[(bArr.length << 1)];
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = bArr[i] & 255;
            int i4 = i2 + 1;
            cArr[i2] = b[i3 >>> 4];
            i2 = i4 + 1;
            cArr[i4] = b[i3 & 15];
            i++;
        }
        return new String(cArr);
    }
}
