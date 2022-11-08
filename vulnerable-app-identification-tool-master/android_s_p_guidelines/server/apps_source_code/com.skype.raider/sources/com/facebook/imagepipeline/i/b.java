package com.facebook.imagepipeline.i;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.ColorSpace;
import android.graphics.ColorSpace.Named;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.util.j.c;
import com.facebook.common.f.a;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.c.g;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.memory.d;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public abstract class b implements f {
    private static final Class<?> b = b.class;
    private static final byte[] d = new byte[]{(byte) -1, (byte) -39};
    @VisibleForTesting
    final c<ByteBuffer> a;
    private final d c;

    public abstract int a(int i, int i2, Options options);

    public b(d bitmapPool, int maxNumThreads, c decodeBuffers) {
        this.c = bitmapPool;
        this.a = decodeBuffers;
        for (int i = 0; i < maxNumThreads; i++) {
            this.a.a(ByteBuffer.allocate(16384));
        }
    }

    public a<Bitmap> decodeJPEGFromEncodedImage(e encodedImage, Config bitmapConfig, @Nullable Rect regionToDecode, int length) {
        return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, bitmapConfig, regionToDecode, length, false);
    }

    public a<Bitmap> decodeFromEncodedImageWithColorSpace(e encodedImage, Config bitmapConfig, @Nullable Rect regionToDecode, boolean transformToSRGB) {
        Options options = a(encodedImage, bitmapConfig);
        boolean retryOnFail = options.inPreferredConfig != Config.ARGB_8888;
        try {
            return a(encodedImage.c(), options, regionToDecode, transformToSRGB);
        } catch (RuntimeException re) {
            if (retryOnFail) {
                return decodeFromEncodedImageWithColorSpace(encodedImage, Config.ARGB_8888, regionToDecode, transformToSRGB);
            }
            throw re;
        }
    }

    public a<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(e encodedImage, Config bitmapConfig, @Nullable Rect regionToDecode, int length, boolean transformToSRGB) {
        InputStream jpegDataStream;
        boolean isJpegComplete = encodedImage.e(length);
        Options options = a(encodedImage, bitmapConfig);
        InputStream jpegDataStream2 = encodedImage.c();
        h.a((Object) jpegDataStream2);
        if (encodedImage.l() > length) {
            jpegDataStream = new com.facebook.common.h.a(jpegDataStream2, length);
        } else {
            jpegDataStream = jpegDataStream2;
        }
        if (isJpegComplete) {
            jpegDataStream2 = jpegDataStream;
        } else {
            jpegDataStream2 = new com.facebook.common.h.b(jpegDataStream, d);
        }
        boolean retryOnFail = options.inPreferredConfig != Config.ARGB_8888;
        try {
            return a(jpegDataStream2, options, regionToDecode, transformToSRGB);
        } catch (RuntimeException re) {
            if (retryOnFail) {
                return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, Config.ARGB_8888, regionToDecode, length, transformToSRGB);
            }
            throw re;
        }
    }

    private a<Bitmap> a(InputStream inputStream, Options options, @Nullable Rect regionToDecode, boolean transformToSRGB) {
        h.a((Object) inputStream);
        int targetWidth = options.outWidth;
        int targetHeight = options.outHeight;
        if (regionToDecode != null) {
            targetWidth = regionToDecode.width() / options.inSampleSize;
            targetHeight = regionToDecode.height() / options.inSampleSize;
        }
        Bitmap bitmapToReuse = (Bitmap) this.c.a(a(targetWidth, targetHeight, options));
        if (bitmapToReuse == null) {
            throw new NullPointerException("BitmapPool.get returned null");
        }
        options.inBitmap = bitmapToReuse;
        if (VERSION.SDK_INT >= 26 && transformToSRGB) {
            options.inPreferredColorSpace = ColorSpace.get(Named.SRGB);
        }
        Bitmap decodedBitmap = null;
        ByteBuffer byteBuffer = (ByteBuffer) this.a.a();
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(16384);
        }
        try {
            options.inTempStorage = byteBuffer.array();
            if (regionToDecode != null) {
                BitmapRegionDecoder bitmapRegionDecoder = null;
                try {
                    bitmapToReuse.reconfigure(targetWidth, targetHeight, options.inPreferredConfig);
                    bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, true);
                    decodedBitmap = bitmapRegionDecoder.decodeRegion(regionToDecode, options);
                    if (bitmapRegionDecoder != null) {
                        bitmapRegionDecoder.recycle();
                    }
                } catch (IOException e) {
                    FLog.e(b, "Could not decode region %s, decoding full bitmap instead.", regionToDecode);
                    if (bitmapRegionDecoder != null) {
                        bitmapRegionDecoder.recycle();
                    }
                } catch (Throwable th) {
                    if (bitmapRegionDecoder != null) {
                        bitmapRegionDecoder.recycle();
                    }
                }
            }
            if (decodedBitmap == null) {
                decodedBitmap = BitmapFactory.decodeStream(inputStream, null, options);
            }
            this.a.a(byteBuffer);
            if (bitmapToReuse == decodedBitmap) {
                return a.a(decodedBitmap, this.c);
            }
            this.c.a((Object) bitmapToReuse);
            decodedBitmap.recycle();
            throw new IllegalStateException();
        } catch (IllegalArgumentException e2) {
            this.c.a((Object) bitmapToReuse);
            inputStream.reset();
            Bitmap naiveDecodedBitmap = BitmapFactory.decodeStream(inputStream);
            if (naiveDecodedBitmap == null) {
                throw e2;
            }
            a<Bitmap> a = a.a(naiveDecodedBitmap, g.a());
            this.a.a(byteBuffer);
            return a;
        } catch (RuntimeException re) {
            this.c.a((Object) bitmapToReuse);
            throw re;
        } catch (IOException e3) {
            throw e2;
        } catch (Throwable th2) {
            this.a.a(byteBuffer);
        }
    }

    private static Options a(e encodedImage, Config bitmapConfig) {
        Options options = new Options();
        options.inSampleSize = encodedImage.j();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(encodedImage.c(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = bitmapConfig;
        options.inMutable = true;
        return options;
    }
}
