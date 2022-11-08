package com.facebook.drawee.d;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.c.c;
import com.facebook.drawee.c.f;
import com.facebook.drawee.c.g;
import com.facebook.drawee.c.h;
import com.facebook.drawee.c.p;
import com.facebook.drawee.c.q;
import com.facebook.drawee.interfaces.b;
import javax.annotation.Nullable;

public final class a implements b {
    private final Drawable a = new ColorDrawable(0);
    private final Resources b;
    @Nullable
    private e c;
    private final d d;
    private final f e;
    private final g f;

    a(b builder) {
        int size;
        int i;
        com.facebook.imagepipeline.l.b.a();
        this.b = builder.a();
        this.c = builder.r();
        this.f = new g(this.a);
        if (builder.p() != null) {
            size = builder.p().size();
        } else {
            size = 1;
        }
        if (builder.q() != null) {
            i = 1;
        } else {
            i = 0;
        }
        int numOverlays = size + i;
        Drawable[] layers = new Drawable[(numOverlays + 6)];
        layers[0] = b(builder.o(), null);
        layers[1] = b(builder.d(), builder.e());
        Drawable drawable = this.f;
        q.b l = builder.l();
        PointF m = builder.m();
        drawable.setColorFilter(builder.n());
        layers[2] = f.a(drawable, l, m);
        layers[3] = b(builder.j(), builder.k());
        layers[4] = b(builder.f(), builder.g());
        layers[5] = b(builder.h(), builder.i());
        if (numOverlays > 0) {
            int index = 0;
            if (builder.p() != null) {
                for (Drawable overlay : builder.p()) {
                    int index2 = index + 1;
                    layers[index + 6] = b(overlay, null);
                    index = index2;
                }
            } else {
                index = 1;
            }
            if (builder.q() != null) {
                layers[index + 6] = b(builder.q(), null);
            }
        }
        this.e = new f(layers);
        this.e.c(builder.b());
        this.d = new d(f.a(this.e, this.c));
        this.d.mutate();
        f();
        com.facebook.imagepipeline.l.b.a();
    }

    @Nullable
    private Drawable b(@Nullable Drawable drawable, @Nullable q.b scaleType) {
        return f.a(f.a(drawable, this.c, this.b), scaleType);
    }

    private void f() {
        if (this.e != null) {
            this.e.b();
            this.e.d();
            g();
            b(1);
            this.e.e();
            this.e.c();
        }
    }

    private void g() {
        c(1);
        c(2);
        c(3);
        c(4);
        c(5);
    }

    private void b(int index) {
        if (index >= 0) {
            this.e.d(index);
        }
    }

    private void c(int index) {
        if (index >= 0) {
            this.e.e(index);
        }
    }

    private void a(float progress) {
        Drawable progressBarDrawable = this.e.a(3);
        if (progressBarDrawable != null) {
            if (progress >= 0.999f) {
                if (progressBarDrawable instanceof Animatable) {
                    ((Animatable) progressBarDrawable).stop();
                }
                c(3);
            } else {
                if (progressBarDrawable instanceof Animatable) {
                    ((Animatable) progressBarDrawable).start();
                }
                b(3);
            }
            progressBarDrawable.setLevel(Math.round(10000.0f * progress));
        }
    }

    public final Drawable a() {
        return this.d;
    }

    public final void a(Drawable drawable, float progress, boolean immediate) {
        drawable = f.a(drawable, this.c, this.b);
        drawable.mutate();
        this.f.a(drawable);
        this.e.b();
        g();
        b(2);
        a(progress);
        if (immediate) {
            this.e.e();
        }
        this.e.c();
    }

    public final void a(float progress, boolean immediate) {
        if (this.e.a(3) != null) {
            this.e.b();
            a(progress);
            if (immediate) {
                this.e.e();
            }
            this.e.c();
        }
    }

    public final void c() {
        this.e.b();
        g();
        if (this.e.a(5) != null) {
            b(5);
        } else {
            b(1);
        }
        this.e.c();
    }

    public final void d() {
        this.e.b();
        g();
        if (this.e.a(4) != null) {
            b(4);
        } else {
            b(1);
        }
        this.e.c();
    }

    public final void a(@Nullable Drawable drawable) {
        d dVar = this.d;
        dVar.a = drawable;
        dVar.invalidateSelf();
    }

    private c d(int index) {
        c parent = this.e.b(index);
        if (parent.a() instanceof h) {
            parent = (h) parent.a();
        }
        if (parent.a() instanceof p) {
            return (p) parent.a();
        }
        return parent;
    }

    private p e(int index) {
        c parent = d(index);
        if (parent instanceof p) {
            return (p) parent;
        }
        return f.a(parent, q.b.a);
    }

    public final void a(int durationMs) {
        this.e.c(durationMs);
    }

    public final void a(q.b scaleType) {
        com.facebook.common.internal.h.a((Object) scaleType);
        e(2).a(scaleType);
    }

    public final void a(ColorFilter colorfilter) {
        this.f.setColorFilter(colorfilter);
    }

    public final void a(Drawable drawable, q.b scaleType) {
        if (drawable == null) {
            this.e.a(1, null);
        } else {
            d(1).a(f.a(drawable, this.c, this.b));
        }
        e(1).a(scaleType);
    }

    public final void a(@Nullable e roundingParams) {
        this.c = roundingParams;
        f.a(this.d, this.c);
        for (int i = 0; i < this.e.a(); i++) {
            f.a(d(i), this.c, this.b);
        }
    }

    @Nullable
    public final e e() {
        return this.c;
    }

    public final void b() {
        this.f.a(this.a);
        f();
    }
}
