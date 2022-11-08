package com.facebook.imagepipeline.e;

import android.graphics.Bitmap;
import com.facebook.common.f.a;
import com.facebook.imagepipeline.image.c;
import javax.annotation.Nullable;

public abstract class b extends com.facebook.datasource.b<a<c>> {
    protected abstract void a(@Nullable Bitmap bitmap);

    public final void e(com.facebook.datasource.c<a<c>> dataSource) {
        if (dataSource.b()) {
            a<c> closeableImageRef = (a) dataSource.d();
            Bitmap bitmap = null;
            if (closeableImageRef != null && (closeableImageRef.a() instanceof com.facebook.imagepipeline.image.b)) {
                bitmap = ((com.facebook.imagepipeline.image.b) closeableImageRef.a()).f();
            }
            try {
                a(bitmap);
            } finally {
                a.c(closeableImageRef);
            }
        }
    }
}
