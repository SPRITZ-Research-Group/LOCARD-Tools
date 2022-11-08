package com.google.android.gms.internal.clearcut;

import com.adjust.sdk.Constants;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class aj {
    static final Charset a = Charset.forName(Constants.ENCODING);
    public static final byte[] b;
    private static final Charset c = Charset.forName("ISO-8859-1");
    private static final ByteBuffer d;
    private static final q e;

    static {
        byte[] bArr = new byte[0];
        b = bArr;
        d = ByteBuffer.wrap(bArr);
        bArr = b;
        e = q.a(bArr, bArr.length);
    }

    static int a(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static int a(long j) {
        return (int) ((j >>> 32) ^ j);
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static Object a(Object obj, Object obj2) {
        return ((bk) obj).e().a((bk) obj2).b();
    }

    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static boolean a() {
        return false;
    }

    public static boolean a(byte[] bArr) {
        return cz.a(bArr);
    }

    public static String b(byte[] bArr) {
        return new String(bArr, a);
    }

    public static int c(byte[] bArr) {
        int length = bArr.length;
        length = a(length, bArr, 0, length);
        return length == 0 ? 1 : length;
    }
}
