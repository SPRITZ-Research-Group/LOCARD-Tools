package com.microsoft.dl.video.capture.api;

import com.microsoft.dl.video.utils.Resolution;

public enum ImageFormat {
    YV12("YV12", 12),
    NV21("NV21", 12),
    NV16("NV16", 16),
    YUY2("YUY2", 16);
    
    private final String a;
    private final int b;
    private final int c;

    private ImageFormat(String s, int bitsPerPixel) {
        this.a = s;
        this.b = a(s);
        this.c = bitsPerPixel;
    }

    private static int a(String str) {
        int n = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            n = (n << 8) | str.charAt(i);
        }
        return n;
    }

    public final int getFourCC() {
        return this.b;
    }

    public final int getBitsPerPixel() {
        return this.c;
    }

    public final int getSampleSize(Resolution resolution) {
        switch (this) {
            case YV12:
                int width;
                if (resolution.getWidth() % 16 > 0) {
                    width = ((resolution.getWidth() >> 4) + 1) << 4;
                } else {
                    width = resolution.getWidth();
                }
                return ((width * resolution.getHeight()) * this.c) / 8;
            default:
                return (resolution.getNumPixels() * this.c) / 8;
        }
    }

    public final String toString() {
        return this.a;
    }
}
