package com.airbnb.lottie.a.a;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.a.b.p;
import com.airbnb.lottie.c.b.m;
import com.airbnb.lottie.d;
import com.airbnb.lottie.e.e;
import com.airbnb.lottie.f.c;
import com.airbnb.lottie.g;
import java.util.ArrayList;
import java.util.List;

public final class f implements d, j, a {
    private final Path a = new Path();
    private final Paint b = new Paint(1);
    private final String c;
    private final List<l> d = new ArrayList();
    private final com.airbnb.lottie.a.b.a<Integer, Integer> e;
    private final com.airbnb.lottie.a.b.a<Integer, Integer> f;
    @Nullable
    private com.airbnb.lottie.a.b.a<ColorFilter, ColorFilter> g;
    private final LottieDrawable h;

    public f(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, m fill) {
        this.c = fill.a();
        this.h = lottieDrawable;
        if (fill.b() == null || fill.c() == null) {
            this.e = null;
            this.f = null;
            return;
        }
        this.a.setFillType(fill.d());
        this.e = fill.b().a();
        this.e.a((a) this);
        layer.a(this.e);
        this.f = fill.c().a();
        this.f.a((a) this);
        layer.a(this.f);
    }

    public final void a() {
        this.h.invalidateSelf();
    }

    public final void a(List<b> list, List<b> contentsAfter) {
        for (int i = 0; i < contentsAfter.size(); i++) {
            b content = (b) contentsAfter.get(i);
            if (content instanceof l) {
                this.d.add((l) content);
            }
        }
    }

    public final String b() {
        return this.c;
    }

    public final void a(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        d.a("FillContent#draw");
        this.b.setColor(((Integer) this.e.e()).intValue());
        this.b.setAlpha(e.a((int) (((((float) ((Integer) this.f.e()).intValue()) * (((float) parentAlpha) / 255.0f)) / 100.0f) * 255.0f)));
        if (this.g != null) {
            this.b.setColorFilter((ColorFilter) this.g.e());
        }
        this.a.reset();
        for (int i = 0; i < this.d.size(); i++) {
            this.a.addPath(((l) this.d.get(i)).e(), parentMatrix);
        }
        canvas.drawPath(this.a, this.b);
        d.b("FillContent#draw");
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        this.a.reset();
        for (int i = 0; i < this.d.size(); i++) {
            this.a.addPath(((l) this.d.get(i)).e(), parentMatrix);
        }
        this.a.computeBounds(outBounds, false);
        outBounds.set(outBounds.left - 1.0f, outBounds.top - 1.0f, outBounds.right + 1.0f, outBounds.bottom + 1.0f);
    }

    public final void a(com.airbnb.lottie.c.e keyPath, int depth, List<com.airbnb.lottie.c.e> accumulator, com.airbnb.lottie.c.e currentPartialKeyPath) {
        e.a(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public final <T> void a(T property, @Nullable c<T> callback) {
        if (property == g.a) {
            this.e.a((c) callback);
        } else if (property == g.d) {
            this.f.a((c) callback);
        } else if (property != g.x) {
        } else {
            if (callback == null) {
                this.g = null;
            } else {
                this.g = new p(callback);
            }
        }
    }
}
