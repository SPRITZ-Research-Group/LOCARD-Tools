package com.facebook.common.j;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import javax.annotation.Nullable;

public final class b {
    public static final boolean a = (VERSION.SDK_INT <= 17);
    public static final boolean b;
    public static final boolean c;
    @Nullable
    public static a d = null;
    private static boolean e = false;
    private static final byte[] f = a("RIFF");
    private static final byte[] g = a("WEBP");
    private static final byte[] h = a("VP8 ");
    private static final byte[] i = a("VP8L");
    private static final byte[] j = a("VP8X");

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        } else {
            z = false;
        }
        b = z;
        if (VERSION.SDK_INT < 17) {
            z2 = false;
        } else if (VERSION.SDK_INT == 17) {
            byte[] decode = Base64.decode("UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==", 0);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
            if (!(options.outHeight == 1 && options.outWidth == 1)) {
                z2 = false;
            }
        }
        c = z2;
    }

    @Nullable
    public static a a() {
        if (e) {
            return d;
        }
        a loadedWebpBitmapFactory = null;
        try {
            loadedWebpBitmapFactory = (a) Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
        } catch (Throwable th) {
        }
        e = true;
        return loadedWebpBitmapFactory;
    }

    private static byte[] a(String value) {
        try {
            return value.getBytes("ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("ASCII not found!", uee);
        }
    }

    public static boolean a(byte[] imageHeaderBytes) {
        boolean isVp8x = a(imageHeaderBytes, 12, j);
        boolean hasAnimationBit;
        if ((imageHeaderBytes[20] & 2) == 2) {
            hasAnimationBit = true;
        } else {
            hasAnimationBit = false;
        }
        if (isVp8x && hasAnimationBit) {
            return true;
        }
        return false;
    }

    public static boolean b(byte[] imageHeaderBytes) {
        return a(imageHeaderBytes, 12, h);
    }

    public static boolean c(byte[] imageHeaderBytes) {
        return a(imageHeaderBytes, 12, i);
    }

    public static boolean a(byte[] imageHeaderBytes, int headerSize) {
        return headerSize >= 21 && a(imageHeaderBytes, 12, j);
    }

    public static boolean d(byte[] imageHeaderBytes) {
        boolean isVp8x = a(imageHeaderBytes, 12, j);
        boolean hasAlphaBit;
        if ((imageHeaderBytes[20] & 16) == 16) {
            hasAlphaBit = true;
        } else {
            hasAlphaBit = false;
        }
        if (isVp8x && hasAlphaBit) {
            return true;
        }
        return false;
    }

    public static boolean b(byte[] imageHeaderBytes, int headerSize) {
        if (headerSize >= 20 && a(imageHeaderBytes, 0, f) && a(imageHeaderBytes, 8, g)) {
            return true;
        }
        return false;
    }

    private static boolean a(byte[] byteArray, int offset, byte[] pattern) {
        if (pattern == null || byteArray == null || pattern.length + offset > byteArray.length) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (byteArray[i + offset] != pattern[i]) {
                return false;
            }
        }
        return true;
    }
}
