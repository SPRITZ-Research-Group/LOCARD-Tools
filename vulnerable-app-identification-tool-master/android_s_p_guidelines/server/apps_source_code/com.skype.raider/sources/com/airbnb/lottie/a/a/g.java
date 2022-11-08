package com.airbnb.lottie.a.a;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.support.annotation.Nullable;
import android.support.v4.util.f;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.a.b.p;
import com.airbnb.lottie.c.b.c;
import com.airbnb.lottie.c.b.d;
import com.airbnb.lottie.e.e;
import java.util.ArrayList;
import java.util.List;

public final class g implements d, j, a {
    private final String a;
    private final f<LinearGradient> b = new f();
    private final f<RadialGradient> c = new f();
    private final Matrix d = new Matrix();
    private final Path e = new Path();
    private final Paint f = new Paint(1);
    private final RectF g = new RectF();
    private final List<l> h = new ArrayList();
    private final com.airbnb.lottie.c.b.f i;
    private final com.airbnb.lottie.a.b.a<c, c> j;
    private final com.airbnb.lottie.a.b.a<Integer, Integer> k;
    private final com.airbnb.lottie.a.b.a<PointF, PointF> l;
    private final com.airbnb.lottie.a.b.a<PointF, PointF> m;
    @Nullable
    private com.airbnb.lottie.a.b.a<ColorFilter, ColorFilter> n;
    private final LottieDrawable o;
    private final int p;

    public g(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, d fill) {
        this.a = fill.a();
        this.o = lottieDrawable;
        this.i = fill.b();
        this.e.setFillType(fill.c());
        this.p = (int) (lottieDrawable.k().c() / 32.0f);
        this.j = fill.d().a();
        this.j.a((a) this);
        layer.a(this.j);
        this.k = fill.e().a();
        this.k.a((a) this);
        layer.a(this.k);
        this.l = fill.f().a();
        this.l.a((a) this);
        layer.a(this.l);
        this.m = fill.g().a();
        this.m.a((a) this);
        layer.a(this.m);
    }

    public final void a() {
        this.o.invalidateSelf();
    }

    public final void a(List<b> list, List<b> contentsAfter) {
        for (int i = 0; i < contentsAfter.size(); i++) {
            b content = (b) contentsAfter.get(i);
            if (content instanceof l) {
                this.h.add((l) content);
            }
        }
    }

    public final void a(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Shader shader;
        com.airbnb.lottie.d.a("GradientFillContent#draw");
        this.e.reset();
        for (int i = 0; i < this.h.size(); i++) {
            this.e.addPath(((l) this.h.get(i)).e(), parentMatrix);
        }
        this.e.computeBounds(this.g, false);
        Shader shader2;
        if (this.i == com.airbnb.lottie.c.b.f.Linear) {
            int c = c();
            shader2 = (LinearGradient) this.b.a((long) c);
            if (shader2 != null) {
                shader = shader2;
            } else {
                PointF pointF = (PointF) this.l.e();
                PointF pointF2 = (PointF) this.m.e();
                c cVar = (c) this.j.e();
                shader2 = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, cVar.b(), cVar.a(), TileMode.CLAMP);
                this.b.a((long) c, shader2);
                shader = shader2;
            }
        } else {
            int c2 = c();
            shader2 = (RadialGradient) this.c.a((long) c2);
            if (shader2 != null) {
                shader = shader2;
            } else {
                PointF pointF3 = (PointF) this.l.e();
                PointF pointF4 = (PointF) this.m.e();
                c cVar2 = (c) this.j.e();
                int[] b = cVar2.b();
                float[] a = cVar2.a();
                float f = pointF3.x;
                float f2 = pointF3.y;
                shader2 = new RadialGradient(f, f2, (float) Math.hypot((double) (pointF4.x - f), (double) (pointF4.y - f2)), b, a, TileMode.CLAMP);
                this.c.a((long) c2, shader2);
                shader = shader2;
            }
        }
        this.d.set(parentMatrix);
        shader.setLocalMatrix(this.d);
        this.f.setShader(shader);
        if (this.n != null) {
            this.f.setColorFilter((ColorFilter) this.n.e());
        }
        this.f.setAlpha(e.a((int) (((((float) ((Integer) this.k.e()).intValue()) * (((float) parentAlpha) / 255.0f)) / 100.0f) * 255.0f)));
        canvas.drawPath(this.e, this.f);
        com.airbnb.lottie.d.b("GradientFillContent#draw");
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        this.e.reset();
        for (int i = 0; i < this.h.size(); i++) {
            this.e.addPath(((l) this.h.get(i)).e(), parentMatrix);
        }
        this.e.computeBounds(outBounds, false);
        outBounds.set(outBounds.left - 1.0f, outBounds.top - 1.0f, outBounds.right + 1.0f, outBounds.bottom + 1.0f);
    }

    public final String b() {
        return this.a;
    }

    private int c() {
        int startPointProgress = Math.round(this.l.f() * ((float) this.p));
        int endPointProgress = Math.round(this.m.f() * ((float) this.p));
        int colorProgress = Math.round(this.j.f() * ((float) this.p));
        int hash = 17;
        if (startPointProgress != 0) {
            hash = startPointProgress * 527;
        }
        if (endPointProgress != 0) {
            hash = (hash * 31) * endPointProgress;
        }
        if (colorProgress != 0) {
            return (hash * 31) * colorProgress;
        }
        return hash;
    }

    public final void a(com.airbnb.lottie.c.e keyPath, int depth, List<com.airbnb.lottie.c.e> accumulator, com.airbnb.lottie.c.e currentPartialKeyPath) {
        e.a(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public final <T> void a(T property, @Nullable com.airbnb.lottie.f.c<T> callback) {
        if (property != com.airbnb.lottie.g.x) {
            return;
        }
        if (callback == null) {
            this.n = null;
        } else {
            this.n = new p(callback);
        }
    }
}
