package com.facebook.imagepipeline.a.b;

import com.facebook.cache.a.c;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.core.e;
import com.facebook.imagepipeline.d.h;

public final class b {
    private static boolean a;
    private static a b = null;

    public static a a(f platformBitmapFactory, e executorSupplier, h<c, com.facebook.imagepipeline.image.c> backingCache) {
        if (!a) {
            try {
                b = (a) Class.forName("com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl").getConstructor(new Class[]{f.class, e.class, h.class}).newInstance(new Object[]{platformBitmapFactory, executorSupplier, backingCache});
            } catch (Throwable th) {
            }
            if (b != null) {
                a = true;
            }
        }
        return b;
    }
}
