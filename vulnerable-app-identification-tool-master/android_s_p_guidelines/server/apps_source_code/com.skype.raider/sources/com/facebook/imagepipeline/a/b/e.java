package com.facebook.imagepipeline.a.b;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import com.facebook.common.f.a;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.a.c.b;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.image.c;
import com.facebook.imagepipeline.image.d;
import java.util.ArrayList;
import java.util.List;

public final class e implements d {
    static c a;
    static c b;
    private final b c;
    private final f d;

    static {
        a = null;
        b = null;
        a = a("com.facebook.animated.gif.GifImage");
        b = a("com.facebook.animated.webp.WebPImage");
    }

    private static c a(String className) {
        try {
            return (c) Class.forName(className).newInstance();
        } catch (Throwable th) {
            return null;
        }
    }

    public e(b animatedDrawableBackendProvider, f bitmapFactory) {
        this.c = animatedDrawableBackendProvider;
        this.d = bitmapFactory;
    }

    public final c a(com.facebook.imagepipeline.image.e encodedImage, com.facebook.imagepipeline.common.b options, Config bitmapConfig) {
        if (a == null) {
            throw new UnsupportedOperationException("To encode animated gif please add the dependency to the animated-gif module");
        }
        Object bytesRef = encodedImage.b();
        h.a(bytesRef);
        try {
            com.facebook.imagepipeline.a.a.c gifImage;
            com.facebook.common.e.h input = (com.facebook.common.e.h) bytesRef.a();
            if (input.c() != null) {
                gifImage = a.decode(input.c());
            } else {
                gifImage = a.decode(input.b(), input.a());
            }
            c a = a(options, gifImage, bitmapConfig);
            return a;
        } finally {
            a.c(bytesRef);
        }
    }

    public final c b(com.facebook.imagepipeline.image.e encodedImage, com.facebook.imagepipeline.common.b options, Config bitmapConfig) {
        if (b == null) {
            throw new UnsupportedOperationException("To encode animated webp please add the dependency to the animated-webp module");
        }
        Object bytesRef = encodedImage.b();
        h.a(bytesRef);
        try {
            com.facebook.imagepipeline.a.a.c webPImage;
            com.facebook.common.e.h input = (com.facebook.common.e.h) bytesRef.a();
            if (input.c() != null) {
                webPImage = b.decode(input.c());
            } else {
                webPImage = b.decode(input.b(), input.a());
            }
            c a = a(options, webPImage, bitmapConfig);
            return a;
        } finally {
            a.c(bytesRef);
        }
    }

    private c a(com.facebook.imagepipeline.common.b options, com.facebook.imagepipeline.a.a.c image, Config bitmapConfig) {
        Throwable th;
        int i = 0;
        Iterable decodedFrames = null;
        a<Bitmap> previewBitmap = null;
        try {
            int frameForPreview;
            c dVar;
            if (options.c) {
                frameForPreview = image.getFrameCount() - 1;
            } else {
                frameForPreview = 0;
            }
            if (options.e) {
                dVar = new d(a(image, bitmapConfig, frameForPreview), com.facebook.imagepipeline.image.f.a);
                a.c(null);
                a.a(null);
            } else {
                a previewBitmap2;
                if (options.d) {
                    com.facebook.imagepipeline.a.a.a a = this.c.a(com.facebook.imagepipeline.a.a.e.a(image), null);
                    final List<a<Bitmap>> decodedFrames2 = new ArrayList(a.a());
                    com.facebook.imagepipeline.a.c.d dVar2 = new com.facebook.imagepipeline.a.c.d(a, new com.facebook.imagepipeline.a.c.d.a(this) {
                        final /* synthetic */ e b;

                        public final a<Bitmap> a(int frameNumber) {
                            return a.b((a) decodedFrames2.get(frameNumber));
                        }
                    });
                    while (true) {
                        int i2 = i;
                        if (i2 < a.a()) {
                            a a2 = a(a.c(), a.d(), bitmapConfig);
                            dVar2.a(i2, (Bitmap) a2.a());
                            decodedFrames2.add(a2);
                            i = i2 + 1;
                        } else {
                            try {
                                break;
                            } catch (Throwable th2) {
                                th = th2;
                                Object decodedFrames3 = decodedFrames2;
                                a.c(previewBitmap);
                                a.a(decodedFrames);
                                throw th;
                            }
                        }
                    }
                    previewBitmap = a.b((a) decodedFrames2.get(frameForPreview));
                    decodedFrames = decodedFrames2;
                }
                if (options.b && previewBitmap == null) {
                    previewBitmap2 = a(image, bitmapConfig, frameForPreview);
                }
                dVar = new com.facebook.imagepipeline.image.a(com.facebook.imagepipeline.a.a.e.b(image).a(previewBitmap2).a(frameForPreview).a((List) decodedFrames).e());
                a.c(previewBitmap2);
                a.a(decodedFrames);
            }
            return dVar;
        } catch (Throwable th3) {
            th = th3;
            a.c(previewBitmap);
            a.a(decodedFrames);
            throw th;
        }
    }

    private a<Bitmap> a(com.facebook.imagepipeline.a.a.c image, Config bitmapConfig, int frameForPreview) {
        a<Bitmap> bitmap = a(image.getWidth(), image.getHeight(), bitmapConfig);
        new com.facebook.imagepipeline.a.c.d(this.c.a(com.facebook.imagepipeline.a.a.e.a(image), null), new com.facebook.imagepipeline.a.c.d.a(this) {
            final /* synthetic */ e a;

            {
                this.a = this$0;
            }

            public final a<Bitmap> a(int frameNumber) {
                return null;
            }
        }).a(frameForPreview, (Bitmap) bitmap.a());
        return bitmap;
    }

    @SuppressLint({"NewApi"})
    private a<Bitmap> a(int width, int height, Config bitmapConfig) {
        a<Bitmap> bitmap = this.d.a(width, height, bitmapConfig);
        ((Bitmap) bitmap.a()).eraseColor(0);
        if (VERSION.SDK_INT >= 12) {
            ((Bitmap) bitmap.a()).setHasAlpha(true);
        }
        return bitmap;
    }
}
