package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.os.Build.VERSION;
import com.facebook.common.e.h;
import com.facebook.common.f.a;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.l;
import com.facebook.imagepipeline.i.f;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.memory.b;
import com.facebook.imagepipeline.memory.c;
import java.util.Locale;
import javax.annotation.Nullable;

@DoNotStrip
public abstract class DalvikPurgeableDecoder implements f {
    protected static final byte[] EOI = new byte[]{(byte) -1, (byte) -39};
    private final b mUnpooledBitmapsCounter = c.a();

    @DoNotStrip
    private static native void nativePinBitmap(Bitmap bitmap);

    protected abstract Bitmap decodeByteArrayAsPurgeable(a<h> aVar, Options options);

    protected abstract Bitmap decodeJPEGByteArrayAsPurgeable(a<h> aVar, int i, Options options);

    static {
        a.a();
    }

    protected DalvikPurgeableDecoder() {
    }

    public a<Bitmap> decodeFromEncodedImage(e encodedImage, Config bitmapConfig, @Nullable Rect regionToDecode) {
        return decodeFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, false);
    }

    public a<Bitmap> decodeJPEGFromEncodedImage(e encodedImage, Config bitmapConfig, @Nullable Rect regionToDecode, int length) {
        return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, length, false);
    }

    public a<Bitmap> decodeFromEncodedImageWithColorSpace(e encodedImage, Config bitmapConfig, @Nullable Rect regionToDecode, boolean transformToSRGB) {
        Options options = getBitmapFactoryOptions(encodedImage.j(), bitmapConfig);
        Object bytesRef = encodedImage.b();
        com.facebook.common.internal.h.a(bytesRef);
        try {
            a<Bitmap> pinBitmap = pinBitmap(decodeByteArrayAsPurgeable(bytesRef, options));
            return pinBitmap;
        } finally {
            a.c(bytesRef);
        }
    }

    public a<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(e encodedImage, Config bitmapConfig, @Nullable Rect regionToDecode, int length, boolean transformToSRGB) {
        Options options = getBitmapFactoryOptions(encodedImage.j(), bitmapConfig);
        Object bytesRef = encodedImage.b();
        com.facebook.common.internal.h.a(bytesRef);
        try {
            a<Bitmap> pinBitmap = pinBitmap(decodeJPEGByteArrayAsPurgeable(bytesRef, length, options));
            return pinBitmap;
        } finally {
            a.c(bytesRef);
        }
    }

    @VisibleForTesting
    public static Options getBitmapFactoryOptions(int sampleSize, Config bitmapConfig) {
        Options options = new Options();
        options.inDither = true;
        options.inPreferredConfig = bitmapConfig;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = sampleSize;
        if (VERSION.SDK_INT >= 11) {
            options.inMutable = true;
        }
        return options;
    }

    @VisibleForTesting
    public static boolean endsWithEOI(a<h> bytesRef, int length) {
        h buffer = (h) bytesRef.a();
        return length >= 2 && buffer.a(length - 2) == (byte) -1 && buffer.a(length - 1) == (byte) -39;
    }

    public a<Bitmap> pinBitmap(Bitmap bitmap) {
        com.facebook.common.internal.h.a((Object) bitmap);
        try {
            nativePinBitmap(bitmap);
            if (this.mUnpooledBitmapsCounter.a(bitmap)) {
                return a.a(bitmap, this.mUnpooledBitmapsCounter.e());
            }
            int bitmapSize = com.facebook.imageutils.a.a(bitmap);
            bitmap.recycle();
            throw new com.facebook.imagepipeline.common.f(String.format(Locale.US, "Attempted to pin a bitmap of size %d bytes. The current pool count is %d, the current pool size is %d bytes. The current pool max count is %d, the current pool max size is %d bytes.", new Object[]{Integer.valueOf(bitmapSize), Integer.valueOf(this.mUnpooledBitmapsCounter.a()), Long.valueOf(this.mUnpooledBitmapsCounter.b()), Integer.valueOf(this.mUnpooledBitmapsCounter.c()), Integer.valueOf(this.mUnpooledBitmapsCounter.d())}));
        } catch (Exception e) {
            bitmap.recycle();
            throw l.b(e);
        }
    }
}
