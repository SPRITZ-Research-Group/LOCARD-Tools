package com.microsoft.a.a;

import java.nio.charset.Charset;

public final class d {
    public static final Charset a = Charset.forName("utf-8");
    public static final Charset b = Charset.forName("utf-16le");

    public static String a(byte[] buffer, int length) {
        return new String(buffer, 0, length, a);
    }

    public static byte[] a(String src) {
        return src.getBytes(a);
    }

    public static String b(byte[] buffer, int length) {
        return new String(buffer, 0, length, b);
    }

    public static byte[] b(String src) {
        return src.getBytes(b);
    }
}
