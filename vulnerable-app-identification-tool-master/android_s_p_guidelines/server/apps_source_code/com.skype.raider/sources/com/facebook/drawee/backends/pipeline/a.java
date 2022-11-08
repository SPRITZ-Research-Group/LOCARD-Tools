package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.c.i;
import com.facebook.imagepipeline.image.c;
import com.facebook.imagepipeline.image.d;
import com.facebook.imagepipeline.l.b;
import javax.annotation.Nullable;

public final class a implements com.facebook.imagepipeline.g.a {
    private final Resources a;
    @Nullable
    private final com.facebook.imagepipeline.g.a b;

    public a(Resources resources, @Nullable com.facebook.imagepipeline.g.a animatedDrawableFactory) {
        this.a = resources;
        this.b = animatedDrawableFactory;
    }

    public final boolean a(c image) {
        return true;
    }

    @Nullable
    public final Drawable b(c closeableImage) {
        Object obj = 1;
        try {
            b.a();
            Drawable bitmapDrawable;
            if (closeableImage instanceof d) {
                Object obj2;
                d closeableStaticBitmap = (d) closeableImage;
                bitmapDrawable = new BitmapDrawable(this.a, closeableStaticBitmap.f());
                if (closeableStaticBitmap.i() == 0 || closeableStaticBitmap.i() == -1) {
                    obj2 = null;
                } else {
                    obj2 = 1;
                }
                if (obj2 == null) {
                    if (closeableStaticBitmap.j() == 1 || closeableStaticBitmap.j() == 0) {
                        obj = null;
                    }
                    if (obj == null) {
                        return bitmapDrawable;
                    }
                }
                Drawable iVar = new i(bitmapDrawable, closeableStaticBitmap.i(), closeableStaticBitmap.j());
                b.a();
                return iVar;
            } else if (this.b == null || !this.b.a(closeableImage)) {
                b.a();
                return null;
            } else {
                bitmapDrawable = this.b.b(closeableImage);
                b.a();
                return bitmapDrawable;
            }
        } finally {
            b.a();
        }
    }
}
