package com.facebook.imagepipeline.c;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.e.h;
import com.facebook.common.f.a;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.b;
import com.facebook.imagepipeline.i.f;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(11)
@ThreadSafe
public class e extends f {
    private static final String a = e.class.getSimpleName();
    private final b b;
    private final f c;
    private boolean d;

    public e(b jpegGenerator, f purgeableDecoder) {
        this.b = jpegGenerator;
        this.c = purgeableDecoder;
    }

    @TargetApi(12)
    public final a<Bitmap> a(int width, int height, Config bitmapConfig) {
        if (this.d) {
            return b(width, height, bitmapConfig);
        }
        a jpgRef = this.b.a((short) width, (short) height);
        com.facebook.imagepipeline.image.e encodedImage;
        try {
            encodedImage = new com.facebook.imagepipeline.image.e(jpgRef);
            encodedImage.a(b.a);
            a<Bitmap> bitmapRef = this.c.decodeJPEGFromEncodedImage(encodedImage, bitmapConfig, null, ((h) jpgRef.a()).a());
            if (((Bitmap) bitmapRef.a()).isMutable()) {
                ((Bitmap) bitmapRef.a()).setHasAlpha(true);
                ((Bitmap) bitmapRef.a()).eraseColor(0);
                com.facebook.imagepipeline.image.e.d(encodedImage);
                jpgRef.close();
                return bitmapRef;
            }
            a.c(bitmapRef);
            this.d = true;
            FLog.wtf(a, "Immutable bitmap returned by decoder");
            bitmapRef = b(width, height, bitmapConfig);
            com.facebook.imagepipeline.image.e.d(encodedImage);
            jpgRef.close();
            return bitmapRef;
        } catch (Throwable th) {
            jpgRef.close();
        }
    }

    private static a<Bitmap> b(int width, int height, Config bitmapConfig) {
        return a.a(Bitmap.createBitmap(width, height, bitmapConfig), g.a());
    }
}
