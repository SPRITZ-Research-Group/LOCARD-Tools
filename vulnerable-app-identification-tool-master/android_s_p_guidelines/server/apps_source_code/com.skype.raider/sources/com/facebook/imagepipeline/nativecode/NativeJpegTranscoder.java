package com.facebook.imagepipeline.nativecode;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.imageformat.c;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.producers.p;
import com.facebook.imagepipeline.transcoder.a;
import com.facebook.imagepipeline.transcoder.b;
import com.facebook.imagepipeline.transcoder.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

@DoNotStrip
public class NativeJpegTranscoder implements b {
    public static final String TAG = "NativeJpegTranscoder";
    private int mMaxBitmapSize;
    private boolean mResizingEnabled;
    private boolean mUseDownsamplingRatio;

    @DoNotStrip
    private static native void nativeTranscodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @DoNotStrip
    private static native void nativeTranscodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    static {
        a.a();
    }

    public NativeJpegTranscoder(boolean resizingEnabled, int maxBitmapSize, boolean useDownsamplingRatio) {
        this.mResizingEnabled = resizingEnabled;
        this.mMaxBitmapSize = maxBitmapSize;
        this.mUseDownsamplingRatio = useDownsamplingRatio;
    }

    public boolean canResize(e encodedImage, @Nullable RotationOptions rotationOptions, @Nullable com.facebook.imagepipeline.common.e resizeOptions) {
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.a();
        }
        return d.a(rotationOptions, resizeOptions, encodedImage, this.mResizingEnabled) < 8;
    }

    public boolean canTranscode(c imageFormat) {
        return imageFormat == com.facebook.imageformat.b.a;
    }

    public String getIdentifier() {
        return TAG;
    }

    public a transcode(e encodedImage, OutputStream outputStream, @Nullable RotationOptions rotationOptions, @Nullable com.facebook.imagepipeline.common.e resizeOptions, @Nullable c outputFormat, @Nullable Integer quality) throws IOException {
        if (quality == null) {
            quality = Integer.valueOf(85);
        }
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.a();
        }
        int downsampleRatio = p.a(rotationOptions, resizeOptions, encodedImage, this.mMaxBitmapSize);
        InputStream is = null;
        try {
            int numerator;
            int i;
            int softwareNumerator = d.a(rotationOptions, resizeOptions, encodedImage, this.mResizingEnabled);
            int downsampleNumerator = d.c(downsampleRatio);
            if (this.mUseDownsamplingRatio) {
                numerator = downsampleNumerator;
            } else {
                numerator = softwareNumerator;
            }
            is = encodedImage.c();
            if (d.a.contains(Integer.valueOf(encodedImage.g()))) {
                transcodeJpegWithExifOrientation(is, outputStream, d.b(rotationOptions, encodedImage), numerator, quality.intValue());
            } else {
                transcodeJpeg(is, outputStream, d.a(rotationOptions, encodedImage), numerator, quality.intValue());
            }
            com.facebook.common.internal.b.a(is);
            if (downsampleRatio == 1) {
                i = 1;
            } else {
                i = 0;
            }
            return new a(i);
        } catch (Throwable th) {
            com.facebook.common.internal.b.a(is);
        }
    }

    @VisibleForTesting
    public static void transcodeJpeg(InputStream inputStream, OutputStream outputStream, int rotationAngle, int scaleNumerator, int quality) throws IOException {
        boolean z;
        boolean z2 = false;
        h.a(scaleNumerator > 0);
        if (scaleNumerator <= 16) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (quality >= 0) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (quality <= 100) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        h.a(d.a(rotationAngle));
        if (!(scaleNumerator == 8 && rotationAngle == 0)) {
            z2 = true;
        }
        h.a(z2, (Object) "no transformation requested");
        nativeTranscodeJpeg((InputStream) h.a((Object) inputStream), (OutputStream) h.a((Object) outputStream), rotationAngle, scaleNumerator, quality);
    }

    @VisibleForTesting
    public static void transcodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int exifOrientation, int scaleNumerator, int quality) throws IOException {
        boolean z;
        boolean z2 = false;
        h.a(scaleNumerator > 0);
        if (scaleNumerator <= 16) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (quality >= 0) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (quality <= 100) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        h.a(d.b(exifOrientation));
        if (!(scaleNumerator == 8 && exifOrientation == 1)) {
            z2 = true;
        }
        h.a(z2, (Object) "no transformation requested");
        nativeTranscodeJpegWithExifOrientation((InputStream) h.a((Object) inputStream), (OutputStream) h.a((Object) outputStream), exifOrientation, scaleNumerator, quality);
    }
}
