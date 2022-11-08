package com.projectseptember.RNGL;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.facebook.common.f.a;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.c;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.e;
import com.facebook.imagepipeline.e.b;
import java.util.concurrent.Executor;

public final class g {
    private Uri a;
    private l b;
    private Runnable c;
    private Executor d;
    private Executor e;
    private c<a<com.facebook.imagepipeline.image.c>> f;
    private int g;
    private int h;

    public g(Executor glExecutor, Executor decodeExecutor, Runnable onLoad, int maxWidth, int maxHeight, int causeId) {
        FLog.i("RNGLImage", "init %d (causeId %x)", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(causeId));
        this.c = onLoad;
        this.d = glExecutor;
        this.e = decodeExecutor;
        this.g = maxWidth;
        this.h = maxHeight;
        this.b = new l(glExecutor, maxWidth, maxHeight, causeId);
    }

    public final void a(Uri src, final int causeId) {
        if (this.a == src) {
            return;
        }
        if (this.a == null || !this.a.equals(src)) {
            this.a = src;
            if (!(this.f == null || this.f.b())) {
                this.f.h();
            }
            FLog.i("RNGLImage", "reloadImage %d x %d (causeId %x)", Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(causeId));
            final Uri uri = this.a;
            this.f = Fresco.b().a(com.facebook.imagepipeline.k.c.a(uri).a(new e(this.g, this.h)).q(), null);
            this.f.a(new b(this) {
                final /* synthetic */ g c;

                protected final void a(@Nullable Bitmap bitmap) {
                    this.c.a(bitmap, causeId);
                }

                protected final void f(c<a<com.facebook.imagepipeline.image.c>> dataSource) {
                    FLog.e("RNGLImage", "Failed to load '%s' error:%s (causeId %x)", uri.getPath(), dataSource.f(), Integer.valueOf(causeId));
                }
            }, this.e);
        }
    }

    public final void a(Bitmap source, final int causeId) {
        FLog.i("RNGLImage", "onLoad %d x %d (causeId %x)", Integer.valueOf(source.getWidth()), Integer.valueOf(source.getHeight()), Integer.valueOf(causeId));
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f, -1.0f);
        final Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        bitmap.setHasAlpha(true);
        this.d.execute(new Runnable(this) {
            final /* synthetic */ g c;

            public final void run() {
                FLog.i("RNGLImage", "onLoad texture setPixels");
                this.c.b.a(bitmap, causeId);
                bitmap.recycle();
                this.c.c.run();
            }
        });
    }

    public final l a() {
        return this.b;
    }
}
