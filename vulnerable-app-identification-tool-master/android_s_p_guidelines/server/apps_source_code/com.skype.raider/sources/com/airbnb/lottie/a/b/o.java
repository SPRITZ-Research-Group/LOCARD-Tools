package com.airbnb.lottie.a.b;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.c.a.l;
import com.airbnb.lottie.c.c.a;
import com.airbnb.lottie.f.c;
import com.airbnb.lottie.f.d;
import com.airbnb.lottie.g;

public final class o {
    private final Matrix a = new Matrix();
    private final a<PointF, PointF> b;
    private final a<?, PointF> c;
    private final a<d, d> d;
    private final a<Float, Float> e;
    private final a<Integer, Integer> f;
    @Nullable
    private final a<?, Float> g;
    @Nullable
    private final a<?, Float> h;

    public o(l animatableTransform) {
        this.b = animatableTransform.a().a();
        this.c = animatableTransform.b().a();
        this.d = animatableTransform.c().a();
        this.e = animatableTransform.d().a();
        this.f = animatableTransform.e().a();
        if (animatableTransform.f() != null) {
            this.g = animatableTransform.f().a();
        } else {
            this.g = null;
        }
        if (animatableTransform.g() != null) {
            this.h = animatableTransform.g().a();
        } else {
            this.h = null;
        }
    }

    public final void a(a layer) {
        layer.a(this.b);
        layer.a(this.c);
        layer.a(this.d);
        layer.a(this.e);
        layer.a(this.f);
        if (this.g != null) {
            layer.a(this.g);
        }
        if (this.h != null) {
            layer.a(this.h);
        }
    }

    public final void a(a.a listener) {
        this.b.a(listener);
        this.c.a(listener);
        this.d.a(listener);
        this.e.a(listener);
        this.f.a(listener);
        if (this.g != null) {
            this.g.a(listener);
        }
        if (this.h != null) {
            this.h.a(listener);
        }
    }

    public final void a(float progress) {
        this.b.a(progress);
        this.c.a(progress);
        this.d.a(progress);
        this.e.a(progress);
        this.f.a(progress);
        if (this.g != null) {
            this.g.a(progress);
        }
        if (this.h != null) {
            this.h.a(progress);
        }
    }

    public final a<?, Integer> a() {
        return this.f;
    }

    @Nullable
    public final a<?, Float> b() {
        return this.g;
    }

    @Nullable
    public final a<?, Float> c() {
        return this.h;
    }

    public final Matrix d() {
        this.a.reset();
        PointF position = (PointF) this.c.e();
        if (!(position.x == 0.0f && position.y == 0.0f)) {
            this.a.preTranslate(position.x, position.y);
        }
        float rotation = ((Float) this.e.e()).floatValue();
        if (rotation != 0.0f) {
            this.a.preRotate(rotation);
        }
        d scaleTransform = (d) this.d.e();
        if (!(scaleTransform.a() == 1.0f && scaleTransform.b() == 1.0f)) {
            this.a.preScale(scaleTransform.a(), scaleTransform.b());
        }
        PointF anchorPoint = (PointF) this.b.e();
        if (!(anchorPoint.x == 0.0f && anchorPoint.y == 0.0f)) {
            this.a.preTranslate(-anchorPoint.x, -anchorPoint.y);
        }
        return this.a;
    }

    public final Matrix b(float amount) {
        PointF position = (PointF) this.c.e();
        PointF anchorPoint = (PointF) this.b.e();
        d scale = (d) this.d.e();
        float rotation = ((Float) this.e.e()).floatValue();
        this.a.reset();
        this.a.preTranslate(position.x * amount, position.y * amount);
        this.a.preScale((float) Math.pow((double) scale.a(), (double) amount), (float) Math.pow((double) scale.b(), (double) amount));
        this.a.preRotate(rotation * amount, anchorPoint.x, anchorPoint.y);
        return this.a;
    }

    public final <T> boolean a(T property, @Nullable c<T> callback) {
        if (property == g.e) {
            this.b.a((c) callback);
        } else if (property == g.f) {
            this.c.a((c) callback);
        } else if (property == g.i) {
            this.d.a((c) callback);
        } else if (property == g.j) {
            this.e.a((c) callback);
        } else if (property == g.c) {
            this.f.a((c) callback);
        } else if (property == g.u && this.g != null) {
            this.g.a((c) callback);
        } else if (property != g.v || this.h == null) {
            return false;
        } else {
            this.h.a((c) callback);
        }
        return true;
    }
}
