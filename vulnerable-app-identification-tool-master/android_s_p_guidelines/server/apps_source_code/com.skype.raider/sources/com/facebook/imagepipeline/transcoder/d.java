package com.facebook.imagepipeline.transcoder;

import android.graphics.Matrix;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.common.e;
import javax.annotation.Nullable;

public final class d {
    public static final com.facebook.common.internal.d<Integer> a = com.facebook.common.internal.d.a(Integer.valueOf(2), Integer.valueOf(7), Integer.valueOf(4), Integer.valueOf(5));

    public static boolean a(int degrees) {
        return degrees >= 0 && degrees <= 270 && degrees % 90 == 0;
    }

    public static boolean b(int exifOrientation) {
        switch (exifOrientation) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    public static int a(RotationOptions rotationOptions, @Nullable e resizeOptions, com.facebook.imagepipeline.image.e encodedImage, boolean resizingEnabled) {
        if (!resizingEnabled) {
            return 8;
        }
        if (resizeOptions == null) {
            return 8;
        }
        float f;
        int rotationAngle = a(rotationOptions, encodedImage);
        int exifOrientation = 0;
        if (a.contains(Integer.valueOf(encodedImage.g()))) {
            exifOrientation = b(rotationOptions, encodedImage);
        }
        boolean swapDimensions = rotationAngle == 90 || rotationAngle == 270 || exifOrientation == 5 || exifOrientation == 7;
        int widthAfterRotation = swapDimensions ? encodedImage.i() : encodedImage.h();
        int heightAfterRotation = swapDimensions ? encodedImage.h() : encodedImage.i();
        if (resizeOptions == null) {
            f = 1.0f;
        } else {
            f = Math.max(((float) resizeOptions.a) / ((float) widthAfterRotation), ((float) resizeOptions.b) / ((float) heightAfterRotation));
            if (((float) widthAfterRotation) * f > resizeOptions.c) {
                f = resizeOptions.c / ((float) widthAfterRotation);
            }
            if (((float) heightAfterRotation) * f > resizeOptions.c) {
                f = resizeOptions.c / ((float) heightAfterRotation);
            }
        }
        int numerator = (int) ((f * 8.0f) + resizeOptions.d);
        if (numerator > 8) {
            return 8;
        }
        return numerator <= 0 ? 1 : numerator;
    }

    public static int a(RotationOptions rotationOptions, com.facebook.imagepipeline.image.e encodedImage) {
        int rotationFromMetadata = 0;
        if (!rotationOptions.d()) {
            return 0;
        }
        switch (encodedImage.f()) {
            case 90:
            case 180:
            case 270:
                rotationFromMetadata = encodedImage.f();
                break;
        }
        if (rotationOptions.c()) {
            return rotationFromMetadata;
        }
        return (rotationOptions.e() + rotationFromMetadata) % 360;
    }

    public static int b(RotationOptions rotationOptions, com.facebook.imagepipeline.image.e encodedImage) {
        int index = a.indexOf(Integer.valueOf(encodedImage.g()));
        if (index < 0) {
            throw new IllegalArgumentException("Only accepts inverted exif orientations");
        }
        int forcedAngle = 0;
        if (!rotationOptions.c()) {
            forcedAngle = rotationOptions.e();
        }
        return ((Integer) a.get((index + (forcedAngle / 90)) % a.size())).intValue();
    }

    @VisibleForTesting
    public static int c(int downsampleRatio) {
        return Math.max(1, 8 / downsampleRatio);
    }

    @Nullable
    public static Matrix a(com.facebook.imagepipeline.image.e encodedImage, RotationOptions rotationOptions) {
        Matrix transformationMatrix;
        if (a.contains(Integer.valueOf(encodedImage.g()))) {
            int b = b(rotationOptions, encodedImage);
            transformationMatrix = new Matrix();
            switch (b) {
                case 2:
                    transformationMatrix.setScale(-1.0f, 1.0f);
                    return transformationMatrix;
                case 4:
                    transformationMatrix.setRotate(180.0f);
                    transformationMatrix.postScale(-1.0f, 1.0f);
                    return transformationMatrix;
                case 5:
                    transformationMatrix.setRotate(90.0f);
                    transformationMatrix.postScale(-1.0f, 1.0f);
                    return transformationMatrix;
                case 7:
                    transformationMatrix.setRotate(-90.0f);
                    transformationMatrix.postScale(-1.0f, 1.0f);
                    return transformationMatrix;
                default:
                    return null;
            }
        }
        int rotationAngle = a(rotationOptions, encodedImage);
        if (rotationAngle == 0) {
            return null;
        }
        transformationMatrix = new Matrix();
        transformationMatrix.setRotate((float) rotationAngle);
        return transformationMatrix;
    }
}
