package com.facebook.imagepipeline.f;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import com.facebook.common.f.a;
import com.facebook.imageformat.c;
import com.facebook.imageformat.d;
import com.facebook.imagepipeline.i.f;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.image.g;
import java.util.Map;
import javax.annotation.Nullable;

public final class b implements c {
    private final c a;
    private final c b;
    private final f c;
    private final c d;
    @Nullable
    private final Map<c, c> e;

    public b(c animatedGifDecoder, c animatedWebPDecoder, f platformDecoder) {
        this(animatedGifDecoder, animatedWebPDecoder, platformDecoder, null);
    }

    public b(c animatedGifDecoder, c animatedWebPDecoder, f platformDecoder, @Nullable Map<c, c> customDecoders) {
        this.d = new c(this) {
            final /* synthetic */ b a;

            {
                this.a = this$0;
            }

            public final com.facebook.imagepipeline.image.c a(e encodedImage, int length, g qualityInfo, com.facebook.imagepipeline.common.b options) {
                c imageFormat = encodedImage.e();
                if (imageFormat == com.facebook.imageformat.b.a) {
                    return this.a.c(encodedImage, length, qualityInfo, options);
                }
                if (imageFormat == com.facebook.imageformat.b.c) {
                    return this.a.b(encodedImage, length, qualityInfo, options);
                }
                if (imageFormat == com.facebook.imageformat.b.j) {
                    return this.a.d(encodedImage, length, qualityInfo, options);
                }
                if (imageFormat != c.a) {
                    return this.a.a(encodedImage, options);
                }
                throw new a("unknown image format", encodedImage);
            }
        };
        this.a = animatedGifDecoder;
        this.b = animatedWebPDecoder;
        this.c = platformDecoder;
        this.e = customDecoders;
    }

    public final com.facebook.imagepipeline.image.c a(e encodedImage, int length, g qualityInfo, com.facebook.imagepipeline.common.b options) {
        if (options.h != null) {
            return options.h.a(encodedImage, length, qualityInfo, options);
        }
        c imageFormat = encodedImage.e();
        if (imageFormat == null || imageFormat == c.a) {
            imageFormat = d.a(encodedImage.c());
            encodedImage.a(imageFormat);
        }
        if (this.e != null) {
            c decoder = (c) this.e.get(imageFormat);
            if (decoder != null) {
                return decoder.a(encodedImage, length, qualityInfo, options);
            }
        }
        return this.d.a(encodedImage, length, qualityInfo, options);
    }

    public final com.facebook.imagepipeline.image.c b(e encodedImage, int length, g qualityInfo, com.facebook.imagepipeline.common.b options) {
        if (options.e || this.a == null) {
            return a(encodedImage, options);
        }
        return this.a.a(encodedImage, length, qualityInfo, options);
    }

    public final com.facebook.imagepipeline.image.d a(e encodedImage, com.facebook.imagepipeline.common.b options) {
        a bitmapReference = this.c.decodeFromEncodedImageWithColorSpace(encodedImage, options.g, null, options.f);
        try {
            a(options.i, bitmapReference);
            com.facebook.imagepipeline.image.d dVar = new com.facebook.imagepipeline.image.d(bitmapReference, com.facebook.imagepipeline.image.f.a, encodedImage.f(), encodedImage.g());
            return dVar;
        } finally {
            bitmapReference.close();
        }
    }

    public final com.facebook.imagepipeline.image.d c(e encodedImage, int length, g qualityInfo, com.facebook.imagepipeline.common.b options) {
        a bitmapReference = this.c.decodeJPEGFromEncodedImageWithColorSpace(encodedImage, options.g, null, length, options.f);
        try {
            a(options.i, bitmapReference);
            com.facebook.imagepipeline.image.d dVar = new com.facebook.imagepipeline.image.d(bitmapReference, qualityInfo, encodedImage.f(), encodedImage.g());
            return dVar;
        } finally {
            bitmapReference.close();
        }
    }

    public final com.facebook.imagepipeline.image.c d(e encodedImage, int length, g qualityInfo, com.facebook.imagepipeline.common.b options) {
        return this.b.a(encodedImage, length, qualityInfo, options);
    }

    private static void a(@Nullable com.facebook.imagepipeline.m.a transformation, a<Bitmap> bitmapReference) {
        if (transformation != null) {
            Bitmap bitmap = (Bitmap) bitmapReference.a();
            if (VERSION.SDK_INT >= 12 && transformation.a()) {
                bitmap.setHasAlpha(true);
            }
        }
    }
}
