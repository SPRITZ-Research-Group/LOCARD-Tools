package com.facebook.react.views.toolbar;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import com.facebook.drawee.c.g;
import com.facebook.imagepipeline.image.ImageInfo;

public final class a extends g implements Callback {
    private final ImageInfo a;

    public a(Drawable drawable, ImageInfo imageInfo) {
        super(drawable);
        this.a = imageInfo;
    }

    public final int getIntrinsicWidth() {
        return this.a.a();
    }

    public final int getIntrinsicHeight() {
        return this.a.b();
    }
}
