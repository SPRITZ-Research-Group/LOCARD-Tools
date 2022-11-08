package com.airbnb.lottie.c.c;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.util.f;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a;
import com.airbnb.lottie.a.b.p;
import com.airbnb.lottie.c.c.a.AnonymousClass2;
import com.airbnb.lottie.d;
import com.airbnb.lottie.e;
import com.airbnb.lottie.f.c;
import com.airbnb.lottie.g;
import java.util.ArrayList;
import java.util.List;

public final class b extends a {
    @Nullable
    private a<Float, Float> e;
    private final List<a> f = new ArrayList();
    private final RectF g = new RectF();
    private final RectF h = new RectF();

    public b(LottieDrawable lottieDrawable, d layerModel, List<d> layerModels, e composition) {
        int i;
        super(lottieDrawable, layerModel);
        com.airbnb.lottie.c.a.b timeRemapping = layerModel.u();
        if (timeRemapping != null) {
            this.e = timeRemapping.a();
            a(this.e);
            this.e.a((a.a) this);
        } else {
            this.e = null;
        }
        f<a> layerMap = new f(composition.g().size());
        a mattedLayer = null;
        for (i = layerModels.size() - 1; i >= 0; i--) {
            a layer;
            d lm = (d) layerModels.get(i);
            switch (AnonymousClass2.a[lm.k().ordinal()]) {
                case 1:
                    layer = new f(lottieDrawable, lm);
                    break;
                case 2:
                    layer = new b(lottieDrawable, lm, composition.b(lm.g()), composition);
                    break;
                case 3:
                    layer = new g(lottieDrawable, lm);
                    break;
                case 4:
                    layer = new c(lottieDrawable, lm);
                    break;
                case 5:
                    layer = new e(lottieDrawable, lm);
                    break;
                case 6:
                    layer = new h(lottieDrawable, lm);
                    break;
                default:
                    new StringBuilder("Unknown layer type ").append(lm.k());
                    layer = null;
                    break;
            }
            if (layer != null) {
                layerMap.a(layer.c.e(), layer);
                if (mattedLayer == null) {
                    this.f.add(0, layer);
                    switch (lm.l()) {
                        case Add:
                        case Invert:
                            mattedLayer = layer;
                            break;
                        default:
                            break;
                    }
                }
                mattedLayer.a(layer);
                mattedLayer = null;
            }
        }
        for (i = 0; i < layerMap.a(); i++) {
            a layerView = (a) layerMap.a(layerMap.b(i));
            if (layerView != null) {
                a parentLayer = (a) layerMap.a(layerView.c.m());
                if (parentLayer != null) {
                    layerView.b(parentLayer);
                }
            }
        }
    }

    final void b(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        d.a("CompositionLayer#draw");
        canvas.save();
        this.h.set(0.0f, 0.0f, (float) this.c.h(), (float) this.c.i());
        parentMatrix.mapRect(this.h);
        for (int i = this.f.size() - 1; i >= 0; i--) {
            boolean nonEmptyClip = true;
            if (!this.h.isEmpty()) {
                nonEmptyClip = canvas.clipRect(this.h);
            }
            if (nonEmptyClip) {
                ((a) this.f.get(i)).a(canvas, parentMatrix, parentAlpha);
            }
        }
        canvas.restore();
        d.b("CompositionLayer#draw");
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        super.a(outBounds, parentMatrix);
        this.g.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int i = this.f.size() - 1; i >= 0; i--) {
            ((a) this.f.get(i)).a(this.g, this.a);
            if (outBounds.isEmpty()) {
                outBounds.set(this.g);
            } else {
                outBounds.set(Math.min(outBounds.left, this.g.left), Math.min(outBounds.top, this.g.top), Math.max(outBounds.right, this.g.right), Math.max(outBounds.bottom, this.g.bottom));
            }
        }
    }

    public final void a(@FloatRange(from = 0.0d, to = 1.0d) float progress) {
        super.a(progress);
        if (this.e != null) {
            progress = ((float) ((long) (((Float) this.e.e()).floatValue() * 1000.0f))) / this.b.k().c();
        }
        if (this.c.b() != 0.0f) {
            progress /= this.c.b();
        }
        progress -= this.c.c();
        for (int i = this.f.size() - 1; i >= 0; i--) {
            ((a) this.f.get(i)).a(progress);
        }
    }

    protected final void b(com.airbnb.lottie.c.e keyPath, int depth, List<com.airbnb.lottie.c.e> accumulator, com.airbnb.lottie.c.e currentPartialKeyPath) {
        for (int i = 0; i < this.f.size(); i++) {
            ((a) this.f.get(i)).a(keyPath, depth, accumulator, currentPartialKeyPath);
        }
    }

    public final <T> void a(T property, @Nullable c<T> callback) {
        super.a((Object) property, (c) callback);
        if (property != g.w) {
            return;
        }
        if (callback == null) {
            this.e = null;
            return;
        }
        this.e = new p(callback);
        a(this.e);
    }
}
