package com.airbnb.lottie.a.a;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.p;
import com.airbnb.lottie.c.a.b;
import com.airbnb.lottie.c.a.d;
import com.airbnb.lottie.e.e;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.f.c;
import com.airbnb.lottie.g;
import java.util.ArrayList;
import java.util.List;

public abstract class a implements d, j, com.airbnb.lottie.a.b.a.a {
    final Paint a = new Paint(1);
    private final PathMeasure b = new PathMeasure();
    private final Path c = new Path();
    private final Path d = new Path();
    private final RectF e = new RectF();
    private final LottieDrawable f;
    private final List<a> g = new ArrayList();
    private final float[] h;
    private final com.airbnb.lottie.a.b.a<?, Float> i;
    private final com.airbnb.lottie.a.b.a<?, Integer> j;
    private final List<com.airbnb.lottie.a.b.a<?, Float>> k;
    @Nullable
    private final com.airbnb.lottie.a.b.a<?, Float> l;
    @Nullable
    private com.airbnb.lottie.a.b.a<ColorFilter, ColorFilter> m;

    private static final class a {
        private final List<l> a;
        @Nullable
        private final r b;

        /* synthetic */ a(r x0, byte b) {
            this(x0);
        }

        private a(@Nullable r trimPath) {
            this.a = new ArrayList();
            this.b = trimPath;
        }
    }

    a(LottieDrawable lottieDrawable, com.airbnb.lottie.c.c.a layer, Cap cap, Join join, d opacity, b width, List<b> dashPattern, b offset) {
        int i;
        this.f = lottieDrawable;
        this.a.setStyle(Style.STROKE);
        this.a.setStrokeCap(cap);
        this.a.setStrokeJoin(join);
        this.j = opacity.a();
        this.i = width.a();
        if (offset == null) {
            this.l = null;
        } else {
            this.l = offset.a();
        }
        this.k = new ArrayList(dashPattern.size());
        this.h = new float[dashPattern.size()];
        for (i = 0; i < dashPattern.size(); i++) {
            this.k.add(((b) dashPattern.get(i)).a());
        }
        layer.a(this.j);
        layer.a(this.i);
        for (i = 0; i < this.k.size(); i++) {
            layer.a((com.airbnb.lottie.a.b.a) this.k.get(i));
        }
        if (this.l != null) {
            layer.a(this.l);
        }
        this.j.a((com.airbnb.lottie.a.b.a.a) this);
        this.i.a((com.airbnb.lottie.a.b.a.a) this);
        for (i = 0; i < dashPattern.size(); i++) {
            ((com.airbnb.lottie.a.b.a) this.k.get(i)).a((com.airbnb.lottie.a.b.a.a) this);
        }
        if (this.l != null) {
            this.l.a((com.airbnb.lottie.a.b.a.a) this);
        }
    }

    public final void a() {
        this.f.invalidateSelf();
    }

    public final void a(List<b> contentsBefore, List<b> contentsAfter) {
        int i;
        b content;
        r trimPathContentBefore = null;
        for (i = contentsBefore.size() - 1; i >= 0; i--) {
            content = (b) contentsBefore.get(i);
            if ((content instanceof r) && ((r) content).c() == com.airbnb.lottie.c.b.q.a.Individually) {
                trimPathContentBefore = (r) content;
            }
        }
        if (trimPathContentBefore != null) {
            trimPathContentBefore.a(this);
        }
        a currentPathGroup = null;
        for (i = contentsAfter.size() - 1; i >= 0; i--) {
            content = (b) contentsAfter.get(i);
            if ((content instanceof r) && ((r) content).c() == com.airbnb.lottie.c.b.q.a.Individually) {
                if (currentPathGroup != null) {
                    this.g.add(currentPathGroup);
                }
                currentPathGroup = new a((r) content, (byte) 0);
                ((r) content).a(this);
            } else if (content instanceof l) {
                if (currentPathGroup == null) {
                    currentPathGroup = new a(trimPathContentBefore, (byte) 0);
                }
                currentPathGroup.a.add((l) content);
            }
        }
        if (currentPathGroup != null) {
            this.g.add(currentPathGroup);
        }
    }

    public void a(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        com.airbnb.lottie.d.a("StrokeContent#draw");
        this.a.setAlpha(e.a((int) (((((float) ((Integer) this.j.e()).intValue()) * (((float) parentAlpha) / 255.0f)) / 100.0f) * 255.0f)));
        this.a.setStrokeWidth(((Float) this.i.e()).floatValue() * f.a(parentMatrix));
        if (this.a.getStrokeWidth() <= 0.0f) {
            com.airbnb.lottie.d.b("StrokeContent#draw");
            return;
        }
        com.airbnb.lottie.d.a("StrokeContent#applyDashPattern");
        if (this.k.isEmpty()) {
            com.airbnb.lottie.d.b("StrokeContent#applyDashPattern");
        } else {
            float f;
            float a = f.a(parentMatrix);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.k.size()) {
                    break;
                }
                this.h[i2] = ((Float) ((com.airbnb.lottie.a.b.a) this.k.get(i2)).e()).floatValue();
                if (i2 % 2 == 0) {
                    if (this.h[i2] < 1.0f) {
                        this.h[i2] = 1.0f;
                    }
                } else if (this.h[i2] < 0.1f) {
                    this.h[i2] = 0.1f;
                }
                float[] fArr = this.h;
                fArr[i2] = fArr[i2] * a;
                i = i2 + 1;
            }
            if (this.l == null) {
                f = 0.0f;
            } else {
                f = ((Float) this.l.e()).floatValue();
            }
            this.a.setPathEffect(new DashPathEffect(this.h, f));
            com.airbnb.lottie.d.b("StrokeContent#applyDashPattern");
        }
        if (this.m != null) {
            this.a.setColorFilter((ColorFilter) this.m.e());
        }
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            a pathGroup = (a) this.g.get(i3);
            if (pathGroup.b != null) {
                a(canvas, pathGroup, parentMatrix);
            } else {
                com.airbnb.lottie.d.a("StrokeContent#buildPath");
                this.c.reset();
                for (int j = pathGroup.a.size() - 1; j >= 0; j--) {
                    this.c.addPath(((l) pathGroup.a.get(j)).e(), parentMatrix);
                }
                com.airbnb.lottie.d.b("StrokeContent#buildPath");
                com.airbnb.lottie.d.a("StrokeContent#drawPath");
                canvas.drawPath(this.c, this.a);
                com.airbnb.lottie.d.b("StrokeContent#drawPath");
            }
        }
        com.airbnb.lottie.d.b("StrokeContent#draw");
    }

    private void a(Canvas canvas, a pathGroup, Matrix parentMatrix) {
        com.airbnb.lottie.d.a("StrokeContent#applyTrimPath");
        if (pathGroup.b == null) {
            com.airbnb.lottie.d.b("StrokeContent#applyTrimPath");
            return;
        }
        int j;
        this.c.reset();
        for (j = pathGroup.a.size() - 1; j >= 0; j--) {
            this.c.addPath(((l) pathGroup.a.get(j)).e(), parentMatrix);
        }
        this.b.setPath(this.c, false);
        float totalLength = this.b.getLength();
        while (this.b.nextContour()) {
            totalLength += this.b.getLength();
        }
        float offsetLength = (((Float) pathGroup.b.f().e()).floatValue() * totalLength) / 360.0f;
        float startLength = ((((Float) pathGroup.b.d().e()).floatValue() * totalLength) / 100.0f) + offsetLength;
        float endLength = ((((Float) pathGroup.b.e().e()).floatValue() * totalLength) / 100.0f) + offsetLength;
        float currentLength = 0.0f;
        for (j = pathGroup.a.size() - 1; j >= 0; j--) {
            float startValue;
            float endValue;
            this.d.set(((l) pathGroup.a.get(j)).e());
            this.d.transform(parentMatrix);
            this.b.setPath(this.d, false);
            float length = this.b.getLength();
            if (endLength <= totalLength || endLength - totalLength >= currentLength + length || currentLength >= endLength - totalLength) {
                if (currentLength + length >= startLength && currentLength <= endLength) {
                    if (currentLength + length > endLength || startLength >= currentLength) {
                        if (startLength < currentLength) {
                            startValue = 0.0f;
                        } else {
                            startValue = (startLength - currentLength) / length;
                        }
                        if (endLength > currentLength + length) {
                            endValue = 1.0f;
                        } else {
                            endValue = (endLength - currentLength) / length;
                        }
                    } else {
                        canvas.drawPath(this.d, this.a);
                    }
                }
                currentLength += length;
            } else {
                if (startLength > totalLength) {
                    startValue = (startLength - totalLength) / length;
                } else {
                    startValue = 0.0f;
                }
                endValue = Math.min((endLength - totalLength) / length, 1.0f);
            }
            f.a(this.d, startValue, endValue, 0.0f);
            canvas.drawPath(this.d, this.a);
            currentLength += length;
        }
        com.airbnb.lottie.d.b("StrokeContent#applyTrimPath");
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        com.airbnb.lottie.d.a("StrokeContent#getBounds");
        this.c.reset();
        for (int i = 0; i < this.g.size(); i++) {
            a pathGroup = (a) this.g.get(i);
            for (int j = 0; j < pathGroup.a.size(); j++) {
                this.c.addPath(((l) pathGroup.a.get(j)).e(), parentMatrix);
            }
        }
        this.c.computeBounds(this.e, false);
        float width = ((Float) this.i.e()).floatValue();
        this.e.set(this.e.left - (width / 2.0f), this.e.top - (width / 2.0f), this.e.right + (width / 2.0f), this.e.bottom + (width / 2.0f));
        outBounds.set(this.e);
        outBounds.set(outBounds.left - 1.0f, outBounds.top - 1.0f, outBounds.right + 1.0f, outBounds.bottom + 1.0f);
        com.airbnb.lottie.d.b("StrokeContent#getBounds");
    }

    public final void a(com.airbnb.lottie.c.e keyPath, int depth, List<com.airbnb.lottie.c.e> accumulator, com.airbnb.lottie.c.e currentPartialKeyPath) {
        e.a(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    @CallSuper
    public <T> void a(T property, @Nullable c<T> callback) {
        if (property == g.d) {
            this.j.a((c) callback);
        } else if (property == g.k) {
            this.i.a((c) callback);
        } else if (property != g.x) {
        } else {
            if (callback == null) {
                this.m = null;
            } else {
                this.m = new p(callback);
            }
        }
    }
}
