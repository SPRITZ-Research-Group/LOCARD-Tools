package com.facebook.react.flat;

import android.graphics.Bitmap;
import com.facebook.common.b.i;
import com.facebook.common.f.a;
import com.facebook.datasource.e;
import com.facebook.imagepipeline.core.j;
import com.facebook.imagepipeline.image.c;
import com.facebook.imagepipeline.k.b;
import javax.annotation.Nullable;

final class y implements e<a<c>> {
    private final b a;
    @Nullable
    private d b;
    @Nullable
    private com.facebook.datasource.c<a<c>> c;
    @Nullable
    private a<c> d;
    private int e;

    y(b imageRequest) {
        this.a = imageRequest;
    }

    final void a(d listener) {
        boolean z = true;
        this.b = listener;
        this.e++;
        if (this.e == 1) {
            boolean z2;
            if (this.c == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            com.facebook.infer.annotation.a.a(z2);
            if (this.d != null) {
                z = false;
            }
            com.facebook.infer.annotation.a.a(z);
            this.c = j.a().c().a(this.a, z.l());
            this.c.a(this, i.b());
        } else if (b() != null) {
            listener.b();
        }
    }

    final void a() {
        this.e--;
        if (this.e == 0) {
            if (this.c != null) {
                this.c.h();
                this.c = null;
            }
            if (this.d != null) {
                this.d.close();
                this.d = null;
            }
            this.b = null;
        }
    }

    @Nullable
    final Bitmap b() {
        if (this.d == null) {
            return null;
        }
        c closeableImage = (c) this.d.a();
        if (closeableImage instanceof com.facebook.imagepipeline.image.b) {
            return ((com.facebook.imagepipeline.image.b) closeableImage).f();
        }
        this.d.close();
        this.d = null;
        return null;
    }

    final boolean c() {
        return this.e == 0;
    }

    public final void a(com.facebook.datasource.c<a<c>> dataSource) {
        if (dataSource.b()) {
            try {
                if (this.c == dataSource) {
                    this.c = null;
                    a<c> imageReference = (a) dataSource.d();
                    if (imageReference == null) {
                        dataSource.h();
                    } else if (((c) imageReference.a()) instanceof com.facebook.imagepipeline.image.b) {
                        this.d = imageReference;
                        if (b() == null) {
                            dataSource.h();
                            return;
                        }
                        this.b.c();
                        dataSource.h();
                    } else {
                        imageReference.close();
                        dataSource.h();
                    }
                }
            } finally {
                dataSource.h();
            }
        }
    }

    public final void b(com.facebook.datasource.c<a<c>> dataSource) {
        if (this.c == dataSource) {
            this.c = null;
        }
        dataSource.h();
    }

    public final void c(com.facebook.datasource.c<a<c>> dataSource) {
        if (this.c == dataSource) {
            this.c = null;
        }
        dataSource.h();
    }

    public final void d(com.facebook.datasource.c<a<c>> cVar) {
    }
}
