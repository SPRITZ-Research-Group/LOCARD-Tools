package com.airbnb.lottie.a.a;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.support.v4.util.f;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a;
import com.airbnb.lottie.c.b.c;
import com.airbnb.lottie.c.b.e;

public final class h extends a {
    private final String b;
    private final f<LinearGradient> c = new f();
    private final f<RadialGradient> d = new f();
    private final RectF e = new RectF();
    private final com.airbnb.lottie.c.b.f f;
    private final int g;
    private final a<c, c> h;
    private final a<PointF, PointF> i;
    private final a<PointF, PointF> j;

    public h(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, e stroke) {
        super(lottieDrawable, layer, stroke.h().a(), stroke.i().a(), stroke.d(), stroke.g(), stroke.j(), stroke.k());
        this.b = stroke.a();
        this.f = stroke.b();
        this.g = (int) (lottieDrawable.k().c() / 32.0f);
        this.h = stroke.c().a();
        this.h.a((a.a) this);
        layer.a(this.h);
        this.i = stroke.e().a();
        this.i.a((a.a) this);
        layer.a(this.i);
        this.j = stroke.f().a();
        this.j.a((a.a) this);
        layer.a(this.j);
    }

    public final void a(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        a(this.e, parentMatrix);
        Shader shader;
        PointF pointF;
        PointF pointF2;
        c cVar;
        if (this.f == com.airbnb.lottie.c.b.f.Linear) {
            Paint paint = this.a;
            int c = c();
            shader = (LinearGradient) this.c.a((long) c);
            if (shader == null) {
                pointF = (PointF) this.i.e();
                pointF2 = (PointF) this.j.e();
                cVar = (c) this.h.e();
                shader = new LinearGradient((float) ((int) ((this.e.left + (this.e.width() / 2.0f)) + pointF.x)), (float) ((int) (pointF.y + (this.e.top + (this.e.height() / 2.0f)))), (float) ((int) ((this.e.left + (this.e.width() / 2.0f)) + pointF2.x)), (float) ((int) ((this.e.top + (this.e.height() / 2.0f)) + pointF2.y)), cVar.b(), cVar.a(), TileMode.CLAMP);
                this.c.a((long) c, shader);
            }
            paint.setShader(shader);
        } else {
            Paint paint2 = this.a;
            int c2 = c();
            shader = (RadialGradient) this.d.a((long) c2);
            if (shader == null) {
                pointF = (PointF) this.i.e();
                pointF2 = (PointF) this.j.e();
                cVar = (c) this.h.e();
                int[] b = cVar.b();
                float[] a = cVar.a();
                int width = (int) ((this.e.left + (this.e.width() / 2.0f)) + pointF.x);
                int height = (int) (pointF.y + (this.e.top + (this.e.height() / 2.0f)));
                float f = (float) width;
                float f2 = (float) height;
                shader = new RadialGradient(f, f2, (float) Math.hypot((double) (((int) ((this.e.left + (this.e.width() / 2.0f)) + pointF2.x)) - width), (double) (((int) (pointF2.y + (this.e.top + (this.e.height() / 2.0f)))) - height)), b, a, TileMode.CLAMP);
                this.d.a((long) c2, shader);
            }
            paint2.setShader(shader);
        }
        super.a(canvas, parentMatrix, parentAlpha);
    }

    public final String b() {
        return this.b;
    }

    private int c() {
        int startPointProgress = Math.round(this.i.f() * ((float) this.g));
        int endPointProgress = Math.round(this.j.f() * ((float) this.g));
        int colorProgress = Math.round(this.h.f() * ((float) this.g));
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
}
