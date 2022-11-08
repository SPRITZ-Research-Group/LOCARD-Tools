package com.facebook.drawee.backends.pipeline.info.a;

import android.graphics.drawable.Animatable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.time.b;
import com.facebook.drawee.backends.pipeline.info.f;
import com.facebook.drawee.backends.pipeline.info.g;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import javax.annotation.Nullable;

public final class a extends BaseControllerListener<ImageInfo> {
    private final b a;
    private final g b;
    private final f c;

    public final /* synthetic */ void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
        ImageInfo imageInfo = (ImageInfo) obj;
        long now = this.a.now();
        this.b.c(now);
        this.b.g(now);
        this.b.a(str);
        this.b.a(imageInfo);
        this.c.a(this.b, 3);
    }

    public final /* synthetic */ void onIntermediateImageSet(String str, @Nullable Object obj) {
        ImageInfo imageInfo = (ImageInfo) obj;
        this.b.b(this.a.now());
        this.b.a(str);
        this.b.a(imageInfo);
        this.c.a(this.b, 2);
    }

    public a(b clock, g imagePerfState, f imagePerfMonitor) {
        this.a = clock;
        this.b = imagePerfState;
        this.c = imagePerfMonitor;
    }

    public final void onSubmit(String id, Object callerContext) {
        long now = this.a.now();
        this.b.a(now);
        this.b.a(id);
        this.b.a(callerContext);
        this.c.a(this.b, 0);
        this.b.b(true);
        this.b.h(now);
        this.c.a(this.b);
    }

    public final void onFailure(String id, Throwable throwable) {
        long now = this.a.now();
        this.b.d(now);
        this.b.a(id);
        this.c.a(this.b, 5);
        a(now);
    }

    public final void onRelease(String id) {
        super.onRelease(id);
        long now = this.a.now();
        int lastImageLoadStatus = this.b.b();
        if (!(lastImageLoadStatus == 3 || lastImageLoadStatus == 5)) {
            this.b.e(now);
            this.b.a(id);
            this.c.a(this.b, 4);
        }
        a(now);
    }

    @VisibleForTesting
    private void a(long time) {
        this.b.b(false);
        this.b.i(time);
        this.c.a(this.b);
    }
}
