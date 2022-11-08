package com.facebook.imagepipeline.producers;

import android.support.annotation.Nullable;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.b;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.common.e;

public final class p {
    public static int a(RotationOptions rotationOptions, @Nullable e resizeOptions, com.facebook.imagepipeline.image.e encodedImage, int maxBitmapSize) {
        if (!com.facebook.imagepipeline.image.e.c(encodedImage)) {
            return 1;
        }
        float ratio;
        int i;
        int sampleSize;
        h.a(com.facebook.imagepipeline.image.e.c(encodedImage));
        if (resizeOptions == null || resizeOptions.b <= 0 || resizeOptions.a <= 0 || encodedImage.h() == 0 || encodedImage.i() == 0) {
            ratio = 1.0f;
        } else {
            int f;
            if (rotationOptions.c()) {
                f = encodedImage.f();
                boolean z = f == 0 || f == 90 || f == 180 || f == 270;
                h.a(z);
                i = f;
            } else {
                i = 0;
            }
            Object obj = (i == 90 || i == 270) ? 1 : null;
            ratio = Math.max(((float) resizeOptions.a) / ((float) (obj != null ? encodedImage.i() : encodedImage.h())), ((float) resizeOptions.b) / ((float) (obj != null ? encodedImage.h() : encodedImage.i())));
            FLog.v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f", Integer.valueOf(resizeOptions.a), Integer.valueOf(resizeOptions.b), Integer.valueOf(f), Integer.valueOf(i), Float.valueOf(r8), Float.valueOf(r9), Float.valueOf(ratio));
        }
        if (encodedImage.e() == b.a) {
            if (ratio <= 0.6666667f) {
                sampleSize = 2;
                while (true) {
                    if (((1.0d / ((double) (sampleSize * 2))) * 0.3333333432674408d) + (1.0d / ((double) (sampleSize * 2))) <= ((double) ratio)) {
                        break;
                    }
                    sampleSize *= 2;
                }
            } else {
                sampleSize = 1;
            }
        } else if (ratio > 0.6666667f) {
            sampleSize = 1;
        } else {
            i = 2;
            while (true) {
                if (((1.0d / (Math.pow((double) i, 2.0d) - ((double) i))) * 0.3333333432674408d) + (1.0d / ((double) i)) <= ((double) ratio)) {
                    break;
                }
                i++;
            }
            sampleSize = i - 1;
        }
        int maxDimension = Math.max(encodedImage.i(), encodedImage.h());
        float computedMaxBitmapSize = resizeOptions != null ? resizeOptions.c : (float) maxBitmapSize;
        while (((float) (maxDimension / sampleSize)) > computedMaxBitmapSize) {
            if (encodedImage.e() == b.a) {
                sampleSize *= 2;
            } else {
                sampleSize++;
            }
        }
        return sampleSize;
    }
}
