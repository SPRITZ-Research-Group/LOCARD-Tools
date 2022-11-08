package com.facebook.drawee.backends.pipeline.info;

import android.graphics.Rect;
import com.facebook.drawee.backends.pipeline.b;
import com.facebook.drawee.backends.pipeline.info.a.c;
import com.facebook.imagepipeline.h.a;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

public final class f extends a {
    private final b a;
    private final com.facebook.common.time.b b;
    private final g c = new g();
    @Nullable
    private c d;
    @Nullable
    private b e;
    @Nullable
    private c f;
    @Nullable
    private com.facebook.drawee.backends.pipeline.info.a.a g;
    @Nullable
    private com.facebook.imagepipeline.h.b h;
    @Nullable
    private List<e> i;
    private boolean j;

    public f(com.facebook.common.time.b monotonicClock, b pipelineDraweeController) {
        this.b = monotonicClock;
        this.a = pipelineDraweeController;
    }

    public final void a(boolean enabled) {
        this.j = enabled;
        if (enabled) {
            if (this.g == null) {
                this.g = new com.facebook.drawee.backends.pipeline.info.a.a(this.b, this.c, this);
            }
            if (this.f == null) {
                this.f = new c(this.b, this.c);
            }
            if (this.e == null) {
                this.e = new com.facebook.drawee.backends.pipeline.info.a.b(this.c, this);
            }
            if (this.d == null) {
                this.d = new c(this.a.e(), this.e);
            } else {
                this.d.a(this.a.e());
            }
            if (this.h == null) {
                this.h = new com.facebook.imagepipeline.h.b(this.f, this.d);
            }
            if (this.e != null) {
                this.a.a(this.e);
            }
            if (this.g != null) {
                this.a.a(this.g);
            }
            if (this.h != null) {
                this.a.a(this.h);
                return;
            }
            return;
        }
        if (this.e != null) {
            this.a.b(this.e);
        }
        if (this.g != null) {
            this.a.b(this.g);
        }
        if (this.h != null) {
            this.a.b(this.h);
        }
    }

    public final void a(@Nullable e imagePerfDataListener) {
        if (imagePerfDataListener != null) {
            if (this.i == null) {
                this.i = new LinkedList();
            }
            this.i.add(imagePerfDataListener);
        }
    }

    public final void a(g state, int imageLoadStatus) {
        state.a(imageLoadStatus);
        if (this.j && this.i != null && !this.i.isEmpty()) {
            if (imageLoadStatus == 3) {
                com.facebook.drawee.interfaces.a h = this.a.h();
                if (!(h == null || h.a() == null)) {
                    Rect bounds = h.a().getBounds();
                    this.c.c(bounds.width());
                    this.c.d(bounds.height());
                }
            }
            state.c();
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final void a(g state) {
        if (this.j && this.i != null && !this.i.isEmpty()) {
            state.c();
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public final void a() {
        if (this.i != null) {
            this.i.clear();
        }
        a(false);
        this.c.a();
    }
}
