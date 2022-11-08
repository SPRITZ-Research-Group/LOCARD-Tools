package com.facebook.imageutils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.ColorSpace;
import android.os.Build.VERSION;
import android.support.v4.util.j.c;
import android.util.Pair;
import com.facebook.common.internal.h;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class a {
    private static final c<ByteBuffer> a = new c(12);

    /* renamed from: com.facebook.imageutils.a$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Config.values().length];

        static {
            try {
                a[Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Config.ALPHA_8.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Config.RGB_565.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[Config.RGBA_F16.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static int a(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (VERSION.SDK_INT > 19) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException e) {
            }
        }
        if (VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @Nullable
    public static Pair<Integer, Integer> a(InputStream is) {
        Pair<Integer, Integer> pair = null;
        h.a((Object) is);
        ByteBuffer byteBuffer = (ByteBuffer) a.a();
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(16384);
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            options.inTempStorage = byteBuffer.array();
            BitmapFactory.decodeStream(is, null, options);
            if (!(options.outWidth == -1 || options.outHeight == -1)) {
                pair = new Pair(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
            }
            a.a(byteBuffer);
            return pair;
        } catch (Throwable th) {
            a.a(byteBuffer);
        }
    }

    public static b b(InputStream is) {
        h.a((Object) is);
        ByteBuffer byteBuffer = (ByteBuffer) a.a();
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(16384);
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            options.inTempStorage = byteBuffer.array();
            BitmapFactory.decodeStream(is, null, options);
            ColorSpace colorSpace = null;
            if (VERSION.SDK_INT >= 26) {
                colorSpace = options.outColorSpace;
            }
            b bVar = new b(options.outWidth, options.outHeight, colorSpace);
            return bVar;
        } finally {
            a.a(byteBuffer);
        }
    }

    public static int a(Config bitmapConfig) {
        switch (AnonymousClass1.a[bitmapConfig.ordinal()]) {
            case 1:
                return 4;
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
                return 8;
            default:
                throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
        }
    }

    public static int a(int width, int height, Config bitmapConfig) {
        return (width * height) * a(bitmapConfig);
    }
}
