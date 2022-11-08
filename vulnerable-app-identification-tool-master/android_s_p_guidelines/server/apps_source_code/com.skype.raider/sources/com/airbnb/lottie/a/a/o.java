package com.airbnb.lottie.a.a;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.c.b.k;
import com.airbnb.lottie.c.e;
import com.airbnb.lottie.f.c;
import com.airbnb.lottie.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public final class o implements d, i, j, l, a {
    private final Matrix a = new Matrix();
    private final Path b = new Path();
    private final LottieDrawable c;
    private final com.airbnb.lottie.c.c.a d;
    private final String e;
    private final com.airbnb.lottie.a.b.a<Float, Float> f;
    private final com.airbnb.lottie.a.b.a<Float, Float> g;
    private final com.airbnb.lottie.a.b.o h;
    private c i;

    public o(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, k repeater) {
        this.c = lottieDrawable;
        this.d = layer;
        this.e = repeater.a();
        this.f = repeater.b().a();
        layer.a(this.f);
        this.f.a((a) this);
        this.g = repeater.c().a();
        layer.a(this.g);
        this.g.a((a) this);
        this.h = repeater.d().h();
        this.h.a(layer);
        this.h.a((a) this);
    }

    public final void a(ListIterator<b> contentsIter) {
        if (this.i == null) {
            while (contentsIter.hasPrevious()) {
                if (contentsIter.previous() == this) {
                    break;
                }
            }
            List<b> contents = new ArrayList();
            while (contentsIter.hasPrevious()) {
                contents.add(contentsIter.previous());
                contentsIter.remove();
            }
            Collections.reverse(contents);
            this.i = new c(this.c, this.d, "Repeater", contents, null);
        }
    }

    public final String b() {
        return this.e;
    }

    public final void a(List<b> contentsBefore, List<b> contentsAfter) {
        this.i.a((List) contentsBefore, (List) contentsAfter);
    }

    public final Path e() {
        Path contentPath = this.i.e();
        this.b.reset();
        float copies = ((Float) this.f.e()).floatValue();
        float offset = ((Float) this.g.e()).floatValue();
        for (int i = ((int) copies) - 1; i >= 0; i--) {
            this.a.set(this.h.b(((float) i) + offset));
            this.b.addPath(contentPath, this.a);
        }
        return this.b;
    }

    public final void a(Canvas canvas, Matrix parentMatrix, int alpha) {
        float copies = ((Float) this.f.e()).floatValue();
        float offset = ((Float) this.g.e()).floatValue();
        float startOpacity = ((Float) this.h.b().e()).floatValue() / 100.0f;
        float endOpacity = ((Float) this.h.c().e()).floatValue() / 100.0f;
        for (int i = ((int) copies) - 1; i >= 0; i--) {
            this.a.set(parentMatrix);
            this.a.preConcat(this.h.b(((float) i) + offset));
            this.i.a(canvas, this.a, (int) (((float) alpha) * (((((float) i) / copies) * (endOpacity - startOpacity)) + startOpacity)));
        }
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        this.i.a(outBounds, parentMatrix);
    }

    public final void a() {
        this.c.invalidateSelf();
    }

    public final void a(e keyPath, int depth, List<e> accumulator, e currentPartialKeyPath) {
        com.airbnb.lottie.e.e.a(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public final <T> void a(T property, @Nullable c<T> callback) {
        if (!this.h.a(property, callback)) {
            if (property == g.m) {
                this.f.a((c) callback);
            } else if (property == g.n) {
                this.g.a((c) callback);
            }
        }
    }
}
