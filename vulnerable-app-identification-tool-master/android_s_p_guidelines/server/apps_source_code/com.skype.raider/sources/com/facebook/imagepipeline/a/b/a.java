package com.facebook.imagepipeline.a.b;

import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.imagepipeline.f.c;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public interface a {
    @Nullable
    com.facebook.imagepipeline.g.a getAnimatedDrawableFactory(Context context);

    @Nullable
    c getGifDecoder(Config config);

    @Nullable
    c getWebPDecoder(Config config);
}
