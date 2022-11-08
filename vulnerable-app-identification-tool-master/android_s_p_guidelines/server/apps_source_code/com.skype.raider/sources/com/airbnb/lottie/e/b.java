package com.airbnb.lottie.e;

public final class b {
    private static float a(float linear) {
        if (linear <= 0.0031308f) {
            return 12.92f * linear;
        }
        return (float) ((Math.pow((double) linear, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    private static float b(float srgb) {
        return srgb <= 0.04045f ? srgb / 12.92f : (float) Math.pow((double) ((0.055f + srgb) / 1.055f), 2.4000000953674316d);
    }

    public static int a(float fraction, int startInt, int endInt) {
        float startA = ((float) ((startInt >> 24) & 255)) / 255.0f;
        float startG = ((float) ((startInt >> 8) & 255)) / 255.0f;
        float startB = ((float) (startInt & 255)) / 255.0f;
        float endA = ((float) ((endInt >> 24) & 255)) / 255.0f;
        float endR = ((float) ((endInt >> 16) & 255)) / 255.0f;
        float endG = ((float) ((endInt >> 8) & 255)) / 255.0f;
        float endB = ((float) (endInt & 255)) / 255.0f;
        float startR = b(((float) ((startInt >> 16) & 255)) / 255.0f);
        startG = b(startG);
        startB = b(startB);
        endR = b(endR);
        return (((Math.round((startA + ((endA - startA) * fraction)) * 255.0f) << 24) | (Math.round(a(startR + ((endR - startR) * fraction)) * 255.0f) << 16)) | (Math.round(a(startG + ((b(endG) - startG) * fraction)) * 255.0f) << 8)) | Math.round(a(startB + ((b(endB) - startB) * fraction)) * 255.0f);
    }
}
