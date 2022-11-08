package com.airbnb.lottie.a.a;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a.a;
import com.airbnb.lottie.a.b.o;
import com.airbnb.lottie.c.a.l;
import com.airbnb.lottie.c.b.b;
import com.airbnb.lottie.c.b.n;
import com.airbnb.lottie.c.e;
import com.airbnb.lottie.c.f;
import java.util.ArrayList;
import java.util.List;

public final class c implements d, l, a, f {
    private final Matrix a;
    private final Path b;
    private final RectF c;
    private final String d;
    private final List<b> e;
    private final LottieDrawable f;
    @Nullable
    private List<l> g;
    @Nullable
    private o h;

    private static List<b> a(LottieDrawable drawable, com.airbnb.lottie.c.c.a layer, List<b> contentModels) {
        List<b> contents = new ArrayList(contentModels.size());
        for (int i = 0; i < contentModels.size(); i++) {
            b content = ((b) contentModels.get(i)).a(drawable, layer);
            if (content != null) {
                contents.add(content);
            }
        }
        return contents;
    }

    @Nullable
    private static l a(List<b> contentModels) {
        for (int i = 0; i < contentModels.size(); i++) {
            b contentModel = (b) contentModels.get(i);
            if (contentModel instanceof l) {
                return (l) contentModel;
            }
        }
        return null;
    }

    public c(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, n shapeGroup) {
        this(lottieDrawable, layer, shapeGroup.a(), a(lottieDrawable, layer, shapeGroup.b()), a(shapeGroup.b()));
    }

    c(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, String name, List<b> contents, @Nullable l transform) {
        int i;
        this.a = new Matrix();
        this.b = new Path();
        this.c = new RectF();
        this.d = name;
        this.f = lottieDrawable;
        this.e = contents;
        if (transform != null) {
            this.h = transform.h();
            this.h.a(layer);
            this.h.a((a) this);
        }
        List<i> greedyContents = new ArrayList();
        for (i = contents.size() - 1; i >= 0; i--) {
            b content = (b) contents.get(i);
            if (content instanceof i) {
                greedyContents.add((i) content);
            }
        }
        for (i = greedyContents.size() - 1; i >= 0; i--) {
            ((i) greedyContents.get(i)).a(contents.listIterator(contents.size()));
        }
    }

    public final void a() {
        this.f.invalidateSelf();
    }

    public final String b() {
        return this.d;
    }

    public final void a(List<b> contentsBefore, List<b> list) {
        List<b> myContentsBefore = new ArrayList(contentsBefore.size() + this.e.size());
        myContentsBefore.addAll(contentsBefore);
        for (int i = this.e.size() - 1; i >= 0; i--) {
            b content = (b) this.e.get(i);
            content.a(myContentsBefore, this.e.subList(0, i));
            myContentsBefore.add(content);
        }
    }

    final List<l> c() {
        if (this.g == null) {
            this.g = new ArrayList();
            for (int i = 0; i < this.e.size(); i++) {
                b content = (b) this.e.get(i);
                if (content instanceof l) {
                    this.g.add((l) content);
                }
            }
        }
        return this.g;
    }

    final Matrix d() {
        if (this.h != null) {
            return this.h.d();
        }
        this.a.reset();
        return this.a;
    }

    public final Path e() {
        this.a.reset();
        if (this.h != null) {
            this.a.set(this.h.d());
        }
        this.b.reset();
        for (int i = this.e.size() - 1; i >= 0; i--) {
            b content = (b) this.e.get(i);
            if (content instanceof l) {
                this.b.addPath(((l) content).e(), this.a);
            }
        }
        return this.b;
    }

    public final void a(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        int alpha;
        this.a.set(parentMatrix);
        if (this.h != null) {
            this.a.preConcat(this.h.d());
            alpha = (int) ((((((float) ((Integer) this.h.a().e()).intValue()) / 100.0f) * ((float) parentAlpha)) / 255.0f) * 255.0f);
        } else {
            alpha = parentAlpha;
        }
        for (int i = this.e.size() - 1; i >= 0; i--) {
            Object content = this.e.get(i);
            if (content instanceof d) {
                ((d) content).a(canvas, this.a, alpha);
            }
        }
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        this.a.set(parentMatrix);
        if (this.h != null) {
            this.a.preConcat(this.h.d());
        }
        this.c.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int i = this.e.size() - 1; i >= 0; i--) {
            b content = (b) this.e.get(i);
            if (content instanceof d) {
                ((d) content).a(this.c, this.a);
                if (outBounds.isEmpty()) {
                    outBounds.set(this.c);
                } else {
                    outBounds.set(Math.min(outBounds.left, this.c.left), Math.min(outBounds.top, this.c.top), Math.max(outBounds.right, this.c.right), Math.max(outBounds.bottom, this.c.bottom));
                }
            }
        }
    }

    public final <T> void a(T property, @Nullable com.airbnb.lottie.f.c<T> callback) {
        if (this.h != null) {
            this.h.a(property, callback);
        }
    }

    public final void a(e keyPath, int depth, List<e> accumulator, e currentPartialKeyPath) {
        if (keyPath.a(this.d, depth)) {
            if (!"__container".equals(this.d)) {
                currentPartialKeyPath = currentPartialKeyPath.a(this.d);
                if (keyPath.c(this.d, depth)) {
                    accumulator.add(currentPartialKeyPath.a((f) this));
                }
            }
            if (keyPath.d(this.d, depth)) {
                int newDepth = depth + keyPath.b(this.d, depth);
                for (int i = 0; i < this.e.size(); i++) {
                    b content = (b) this.e.get(i);
                    if (content instanceof f) {
                        ((f) content).a(keyPath, newDepth, accumulator, currentPartialKeyPath);
                    }
                }
            }
        }
    }
}
