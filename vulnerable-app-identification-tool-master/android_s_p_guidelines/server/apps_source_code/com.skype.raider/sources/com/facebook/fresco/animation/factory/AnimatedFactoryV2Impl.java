package com.facebook.fresco.animation.factory;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import com.facebook.cache.a.c;
import com.facebook.common.b.i;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.j;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.a.b.a;
import com.facebook.imagepipeline.a.b.d;
import com.facebook.imagepipeline.a.c.b;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.core.e;
import com.facebook.imagepipeline.d.h;
import com.facebook.imagepipeline.image.g;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@DoNotStrip
@NotThreadSafe
public class AnimatedFactoryV2Impl implements a {
    private static final int NUMBER_OF_FRAMES_TO_PREPARE = 3;
    @Nullable
    private b mAnimatedDrawableBackendProvider;
    @Nullable
    private com.facebook.imagepipeline.g.a mAnimatedDrawableFactory;
    @Nullable
    private com.facebook.imagepipeline.a.d.a mAnimatedDrawableUtil;
    @Nullable
    private d mAnimatedImageFactory;
    private final h<c, com.facebook.imagepipeline.image.c> mBackingCache;
    private final e mExecutorSupplier;
    private final f mPlatformBitmapFactory;

    @DoNotStrip
    public AnimatedFactoryV2Impl(f platformBitmapFactory, e executorSupplier, h<c, com.facebook.imagepipeline.image.c> backingCache) {
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mExecutorSupplier = executorSupplier;
        this.mBackingCache = backingCache;
    }

    @Nullable
    public com.facebook.imagepipeline.g.a getAnimatedDrawableFactory(Context context) {
        if (this.mAnimatedDrawableFactory == null) {
            this.mAnimatedDrawableFactory = createDrawableFactory();
        }
        return this.mAnimatedDrawableFactory;
    }

    public com.facebook.imagepipeline.f.c getGifDecoder(final Config bitmapConfig) {
        return new com.facebook.imagepipeline.f.c(this) {
            final /* synthetic */ AnimatedFactoryV2Impl b;

            public final com.facebook.imagepipeline.image.c a(com.facebook.imagepipeline.image.e encodedImage, int length, g qualityInfo, com.facebook.imagepipeline.common.b options) {
                return this.b.getAnimatedImageFactory().a(encodedImage, options, bitmapConfig);
            }
        };
    }

    public com.facebook.imagepipeline.f.c getWebPDecoder(final Config bitmapConfig) {
        return new com.facebook.imagepipeline.f.c(this) {
            final /* synthetic */ AnimatedFactoryV2Impl b;

            public final com.facebook.imagepipeline.image.c a(com.facebook.imagepipeline.image.e encodedImage, int length, g qualityInfo, com.facebook.imagepipeline.common.b options) {
                return this.b.getAnimatedImageFactory().b(encodedImage, options, bitmapConfig);
            }
        };
    }

    private a createDrawableFactory() {
        j<Integer> cachingStrategySupplier = new j<Integer>(this) {
            final /* synthetic */ AnimatedFactoryV2Impl a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ Object a() {
                return Integer.valueOf(2);
            }
        };
        return new a(getAnimatedDrawableBackendProvider(), i.b(), new com.facebook.common.b.c(this.mExecutorSupplier.c()), RealtimeSinceBootClock.get(), this.mPlatformBitmapFactory, this.mBackingCache, cachingStrategySupplier, new j<Integer>(this) {
            final /* synthetic */ AnimatedFactoryV2Impl a;

            {
                this.a = this$0;
            }

            public final /* synthetic */ Object a() {
                return Integer.valueOf(3);
            }
        });
    }

    private com.facebook.imagepipeline.a.d.a getAnimatedDrawableUtil() {
        if (this.mAnimatedDrawableUtil == null) {
            this.mAnimatedDrawableUtil = new com.facebook.imagepipeline.a.d.a();
        }
        return this.mAnimatedDrawableUtil;
    }

    private d getAnimatedImageFactory() {
        if (this.mAnimatedImageFactory == null) {
            this.mAnimatedImageFactory = buildAnimatedImageFactory();
        }
        return this.mAnimatedImageFactory;
    }

    private b getAnimatedDrawableBackendProvider() {
        if (this.mAnimatedDrawableBackendProvider == null) {
            this.mAnimatedDrawableBackendProvider = new b(this) {
                final /* synthetic */ AnimatedFactoryV2Impl a;

                {
                    this.a = this$0;
                }

                public final com.facebook.imagepipeline.a.a.a a(com.facebook.imagepipeline.a.a.e animatedImageResult, Rect bounds) {
                    return new com.facebook.imagepipeline.a.c.a(this.a.getAnimatedDrawableUtil(), animatedImageResult, bounds);
                }
            };
        }
        return this.mAnimatedDrawableBackendProvider;
    }

    private d buildAnimatedImageFactory() {
        return new com.facebook.imagepipeline.a.b.e(new b(this) {
            final /* synthetic */ AnimatedFactoryV2Impl a;

            {
                this.a = this$0;
            }

            public final com.facebook.imagepipeline.a.a.a a(com.facebook.imagepipeline.a.a.e imageResult, Rect bounds) {
                return new com.facebook.imagepipeline.a.c.a(this.a.getAnimatedDrawableUtil(), imageResult, bounds);
            }
        }, this.mPlatformBitmapFactory);
    }
}
