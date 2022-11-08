package com.skype4life.a;

import android.graphics.Bitmap.Config;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.d;
import com.facebook.imagepipeline.common.b;
import com.facebook.imagepipeline.core.h;
import com.facebook.imagepipeline.core.j;
import com.facebook.imagepipeline.f.c;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.image.g;

public final class a implements c {
    com.facebook.imagepipeline.core.h.a a;
    private final int b;
    private final int c;
    private volatile c d;

    public a(com.facebook.imagepipeline.core.h.a frescoConfigBuilder, int maxImageSize, int maxAnimatedImageSize) {
        this.a = frescoConfigBuilder;
        this.c = maxAnimatedImageSize;
        this.b = maxImageSize;
    }

    public final com.facebook.imagepipeline.image.c a(e encodedImage, int length, g qualityInfo, b options) {
        com.facebook.imageformat.c imageFormat = encodedImage.e();
        if (imageFormat == null || imageFormat == com.facebook.imageformat.c.a) {
            imageFormat = d.a(encodedImage.c());
            encodedImage.a(imageFormat);
        }
        int resultImageSize = encodedImage.h() * encodedImage.i();
        if (imageFormat == com.facebook.imageformat.b.j || imageFormat == com.facebook.imageformat.b.c) {
            if (resultImageSize > this.c) {
                a(encodedImage);
            }
        } else if (imageFormat != com.facebook.imageformat.b.a && resultImageSize > this.b) {
            a(encodedImage);
        }
        if (this.d == null) {
            j a = j.a();
            h a2 = this.a.a();
            Config a3 = a2.a();
            com.facebook.imagepipeline.a.b.a a4 = com.facebook.imagepipeline.a.b.b.a(a.d(), a2.k(), a.b());
            this.d = new com.facebook.imagepipeline.f.b(a4.getGifDecoder(a3), a4.getWebPDecoder(a3), a.e());
        }
        return this.d.a(encodedImage, length, qualityInfo, options);
    }

    private void a(e encodedImage) {
        String message = "Image too big to be decoded " + encodedImage.h() + 'x' + encodedImage.i() + " " + encodedImage.e().a() + " static image limit: " + this.b + " animated image limit: " + this.c;
        FLog.i("LimitedSizeImageDecoder", message);
        throw new IllegalArgumentException(message);
    }
}
