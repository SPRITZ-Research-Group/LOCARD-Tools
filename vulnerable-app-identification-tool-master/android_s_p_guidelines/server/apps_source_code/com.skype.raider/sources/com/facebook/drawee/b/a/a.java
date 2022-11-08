package com.facebook.drawee.b.a;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import javax.annotation.Nullable;

public final class a extends BaseControllerListener {
    private long a = -1;
    private long b = -1;
    @Nullable
    private b c;

    public a(@Nullable b imageLoadingTimeListener) {
        this.c = imageLoadingTimeListener;
    }

    public final void onSubmit(String id, Object callerContext) {
        this.a = System.currentTimeMillis();
    }

    public final void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
        this.b = System.currentTimeMillis();
        if (this.c != null) {
            this.c.a(this.b - this.a);
        }
    }
}
