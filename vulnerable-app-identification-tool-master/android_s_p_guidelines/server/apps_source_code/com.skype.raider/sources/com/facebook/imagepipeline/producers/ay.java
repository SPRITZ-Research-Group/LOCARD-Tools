package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.e;

public final class ay {
    public static boolean a(int width, int height, e resizeOptions) {
        if (resizeOptions == null) {
            if (((float) ((int) (((float) width) * 1.3333334f))) < 2048.0f || ((int) (((float) height) * 1.3333334f)) < 2048) {
                return false;
            }
            return true;
        } else if (((int) (((float) width) * 1.3333334f)) < resizeOptions.a || ((int) (((float) height) * 1.3333334f)) < resizeOptions.b) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean a(com.facebook.imagepipeline.image.e encodedImage, e resizeOptions) {
        if (encodedImage == null) {
            return false;
        }
        switch (encodedImage.f()) {
            case 90:
            case 270:
                return a(encodedImage.i(), encodedImage.h(), resizeOptions);
            default:
                return a(encodedImage.h(), encodedImage.i(), resizeOptions);
        }
    }
}
