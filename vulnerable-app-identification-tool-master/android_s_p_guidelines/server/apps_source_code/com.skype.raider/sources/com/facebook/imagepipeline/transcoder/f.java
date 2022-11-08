package com.facebook.imagepipeline.transcoder;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.b;
import com.facebook.imageformat.c;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.producers.p;
import java.io.OutputStream;
import javax.annotation.Nullable;

public final class f implements b {
    private final boolean a;
    private final int b;

    public f(boolean resizingEnabled, int maxBitmapSize) {
        this.a = resizingEnabled;
        this.b = maxBitmapSize;
    }

    public final a transcode(e encodedImage, OutputStream outputStream, @Nullable RotationOptions rotationOptions, @Nullable com.facebook.imagepipeline.common.e resizeOptions, @Nullable c outputFormat, @Nullable Integer quality) {
        int sampleSize;
        a aVar;
        if (quality == null) {
            quality = Integer.valueOf(85);
        }
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.a();
        }
        if (this.a) {
            sampleSize = p.a(rotationOptions, resizeOptions, encodedImage, this.b);
        } else {
            sampleSize = 1;
        }
        Options options = new Options();
        options.inSampleSize = sampleSize;
        try {
            Bitmap resizedBitmap = BitmapFactory.decodeStream(encodedImage.c(), null, options);
            if (resizedBitmap == null) {
                FLog.e("SimpleImageTranscoder", "Couldn't decode the EncodedImage InputStream ! ");
                return new a(2);
            }
            CompressFormat compressFormat;
            Matrix transformationMatrix = d.a(encodedImage, rotationOptions);
            Bitmap srcBitmap = resizedBitmap;
            if (transformationMatrix != null) {
                try {
                    srcBitmap = Bitmap.createBitmap(resizedBitmap, 0, 0, resizedBitmap.getWidth(), resizedBitmap.getHeight(), transformationMatrix, false);
                } catch (Throwable oom) {
                    FLog.e("SimpleImageTranscoder", "Out-Of-Memory during transcode", oom);
                    aVar = new a(2);
                    return aVar;
                } finally {
                    srcBitmap.recycle();
                    resizedBitmap.recycle();
                }
            }
            if (!(outputFormat == null || outputFormat == b.a)) {
                if (outputFormat == b.b) {
                    compressFormat = CompressFormat.PNG;
                } else if (VERSION.SDK_INT >= 14 && b.b(outputFormat)) {
                    compressFormat = CompressFormat.WEBP;
                }
                srcBitmap.compress(compressFormat, quality.intValue(), outputStream);
                aVar = new a(sampleSize <= 1 ? 0 : 1);
                srcBitmap.recycle();
                resizedBitmap.recycle();
                return aVar;
            }
            compressFormat = CompressFormat.JPEG;
            srcBitmap.compress(compressFormat, quality.intValue(), outputStream);
            if (sampleSize <= 1) {
            }
            aVar = new a(sampleSize <= 1 ? 0 : 1);
            srcBitmap.recycle();
            resizedBitmap.recycle();
            return aVar;
        } catch (Throwable oom2) {
            FLog.e("SimpleImageTranscoder", "Out-Of-Memory during transcode", oom2);
            return new a(2);
        }
    }

    public final boolean canResize(e encodedImage, @Nullable RotationOptions rotationOptions, @Nullable com.facebook.imagepipeline.common.e resizeOptions) {
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.a();
        }
        if (!this.a || p.a(rotationOptions, resizeOptions, encodedImage, this.b) <= 1) {
            return false;
        }
        return true;
    }

    public final boolean canTranscode(c imageFormat) {
        return imageFormat == b.k || imageFormat == b.a;
    }

    public final String getIdentifier() {
        return "SimpleImageTranscoder";
    }
}
