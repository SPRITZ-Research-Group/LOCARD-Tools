package com.airbnb.lottie.c.c;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.CallSuper;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.d;
import com.airbnb.lottie.a.b.c;
import com.airbnb.lottie.a.b.g;
import com.airbnb.lottie.a.b.o;
import com.airbnb.lottie.c.c.d.b;
import com.airbnb.lottie.c.e;
import com.airbnb.lottie.c.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class a implements d, com.airbnb.lottie.a.b.a.a, f {
    final Matrix a = new Matrix();
    final LottieDrawable b;
    final d c;
    final o d;
    private final Path e = new Path();
    private final Matrix f = new Matrix();
    private final Paint g = new Paint(1);
    private final Paint h = new Paint(1);
    private final Paint i = new Paint(1);
    private final Paint j = new Paint(1);
    private final Paint k = new Paint();
    private final RectF l = new RectF();
    private final RectF m = new RectF();
    private final RectF n = new RectF();
    private final RectF o = new RectF();
    private final String p;
    @Nullable
    private g q;
    @Nullable
    private a r;
    @Nullable
    private a s;
    private List<a> t;
    private final List<com.airbnb.lottie.a.b.a<?, ?>> u = new ArrayList();
    private boolean v = true;

    /* renamed from: com.airbnb.lottie.c.c.a$2 */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[com.airbnb.lottie.c.c.d.a.values().length];

        static {
            b = new int[com.airbnb.lottie.c.b.g.a.values().length];
            try {
                b[com.airbnb.lottie.c.b.g.a.MaskModeSubtract.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[com.airbnb.lottie.c.b.g.a.MaskModeIntersect.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[com.airbnb.lottie.c.b.g.a.MaskModeAdd.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[com.airbnb.lottie.c.c.d.a.Shape.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[com.airbnb.lottie.c.c.d.a.PreComp.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[com.airbnb.lottie.c.c.d.a.Solid.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[com.airbnb.lottie.c.c.d.a.Image.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[com.airbnb.lottie.c.c.d.a.Null.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[com.airbnb.lottie.c.c.d.a.Text.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[com.airbnb.lottie.c.c.d.a.Unknown.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    abstract void b(Canvas canvas, Matrix matrix, int i);

    a(LottieDrawable lottieDrawable, d layerModel) {
        this.b = lottieDrawable;
        this.c = layerModel;
        this.p = layerModel.f() + "#draw";
        this.k.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.h.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        this.i.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        if (layerModel.l() == b.Invert) {
            this.j.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
        } else {
            this.j.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        }
        this.d = layerModel.o().h();
        this.d.a((com.airbnb.lottie.a.b.a.a) this);
        if (!(layerModel.j() == null || layerModel.j().isEmpty())) {
            this.q = new g(layerModel.j());
            for (com.airbnb.lottie.a.b.a animation : this.q.b()) {
                a(animation);
                animation.a((com.airbnb.lottie.a.b.a.a) this);
            }
            for (com.airbnb.lottie.a.b.a animation2 : this.q.c()) {
                a(animation2);
                animation2.a((com.airbnb.lottie.a.b.a.a) this);
            }
        }
        if (this.c.d().isEmpty()) {
            a(true);
            return;
        }
        final com.airbnb.lottie.a.b.a cVar = new c(this.c.d());
        cVar.a();
        cVar.a(new com.airbnb.lottie.a.b.a.a(this) {
            final /* synthetic */ a b;

            public final void a() {
                this.b.a(((Float) cVar.e()).floatValue() == 1.0f);
            }
        });
        a(((Float) cVar.e()).floatValue() == 1.0f);
        a(cVar);
    }

    final void a(@Nullable a matteLayer) {
        this.r = matteLayer;
    }

    private boolean c() {
        return this.r != null;
    }

    final void b(@Nullable a parentLayer) {
        this.s = parentLayer;
    }

    public final void a(com.airbnb.lottie.a.b.a<?, ?> newAnimation) {
        this.u.add(newAnimation);
    }

    @CallSuper
    public void a(RectF outBounds, Matrix parentMatrix) {
        this.a.set(parentMatrix);
        this.a.preConcat(this.d.d());
    }

    @SuppressLint({"WrongConstant"})
    public final void a(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        com.airbnb.lottie.d.a(this.p);
        if (this.v) {
            if (this.t == null) {
                if (this.s == null) {
                    this.t = Collections.emptyList();
                } else {
                    this.t = new ArrayList();
                    for (a aVar = this.s; aVar != null; aVar = aVar.s) {
                        this.t.add(aVar);
                    }
                }
            }
            com.airbnb.lottie.d.a("Layer#parentMatrix");
            this.f.reset();
            this.f.set(parentMatrix);
            for (int i = this.t.size() - 1; i >= 0; i--) {
                this.f.preConcat(((a) this.t.get(i)).d.d());
            }
            com.airbnb.lottie.d.b("Layer#parentMatrix");
            int alpha = (int) (((((float) ((Integer) this.d.a().e()).intValue()) * (((float) parentAlpha) / 255.0f)) / 100.0f) * 255.0f);
            if (c() || d()) {
                com.airbnb.lottie.d.a("Layer#computeBounds");
                this.l.set(0.0f, 0.0f, 0.0f, 0.0f);
                a(this.l, this.f);
                RectF rectF = this.l;
                Matrix matrix = this.f;
                if (c() && this.c.l() != b.Invert) {
                    this.r.a(this.n, matrix);
                    rectF.set(Math.max(rectF.left, this.n.left), Math.max(rectF.top, this.n.top), Math.min(rectF.right, this.n.right), Math.min(rectF.bottom, this.n.bottom));
                }
                this.f.preConcat(this.d.d());
                b(this.l, this.f);
                this.l.set(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
                com.airbnb.lottie.d.b("Layer#computeBounds");
                com.airbnb.lottie.d.a("Layer#saveLayer");
                canvas.saveLayer(this.l, this.g, 31);
                com.airbnb.lottie.d.b("Layer#saveLayer");
                a(canvas);
                com.airbnb.lottie.d.a("Layer#drawLayer");
                b(canvas, this.f, alpha);
                com.airbnb.lottie.d.b("Layer#drawLayer");
                if (d()) {
                    Matrix matrix2 = this.f;
                    a(canvas, matrix2, com.airbnb.lottie.c.b.g.a.MaskModeAdd);
                    a(canvas, matrix2, com.airbnb.lottie.c.b.g.a.MaskModeIntersect);
                    a(canvas, matrix2, com.airbnb.lottie.c.b.g.a.MaskModeSubtract);
                }
                if (c()) {
                    com.airbnb.lottie.d.a("Layer#drawMatte");
                    com.airbnb.lottie.d.a("Layer#saveLayer");
                    canvas.saveLayer(this.l, this.j, 19);
                    com.airbnb.lottie.d.b("Layer#saveLayer");
                    a(canvas);
                    this.r.a(canvas, parentMatrix, alpha);
                    com.airbnb.lottie.d.a("Layer#restoreLayer");
                    canvas.restore();
                    com.airbnb.lottie.d.b("Layer#restoreLayer");
                    com.airbnb.lottie.d.b("Layer#drawMatte");
                }
                com.airbnb.lottie.d.a("Layer#restoreLayer");
                canvas.restore();
                com.airbnb.lottie.d.b("Layer#restoreLayer");
                b(com.airbnb.lottie.d.b(this.p));
                return;
            }
            this.f.preConcat(this.d.d());
            com.airbnb.lottie.d.a("Layer#drawLayer");
            b(canvas, this.f, alpha);
            com.airbnb.lottie.d.b("Layer#drawLayer");
            b(com.airbnb.lottie.d.b(this.p));
            return;
        }
        com.airbnb.lottie.d.b(this.p);
    }

    private void b(float ms) {
        this.b.k().a().a(this.c.f(), ms);
    }

    private void a(Canvas canvas) {
        com.airbnb.lottie.d.a("Layer#clearLayer");
        canvas.drawRect(this.l.left - 1.0f, this.l.top - 1.0f, this.l.right + 1.0f, 1.0f + this.l.bottom, this.k);
        com.airbnb.lottie.d.b("Layer#clearLayer");
    }

    private void b(RectF rect, Matrix matrix) {
        this.m.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (d()) {
            int size = this.q.a().size();
            int i = 0;
            while (i < size) {
                com.airbnb.lottie.c.b.g mask = (com.airbnb.lottie.c.b.g) this.q.a().get(i);
                this.e.set((Path) ((com.airbnb.lottie.a.b.a) this.q.b().get(i)).e());
                this.e.transform(matrix);
                switch (mask.a()) {
                    case MaskModeSubtract:
                    case MaskModeIntersect:
                        return;
                    default:
                        this.e.computeBounds(this.o, false);
                        if (i == 0) {
                            this.m.set(this.o);
                        } else {
                            this.m.set(Math.min(this.m.left, this.o.left), Math.min(this.m.top, this.o.top), Math.max(this.m.right, this.o.right), Math.max(this.m.bottom, this.o.bottom));
                        }
                        i++;
                }
            }
            rect.set(Math.max(rect.left, this.m.left), Math.max(rect.top, this.m.top), Math.min(rect.right, this.m.right), Math.min(rect.bottom, this.m.bottom));
        }
    }

    @SuppressLint({"WrongConstant"})
    private void a(Canvas canvas, Matrix matrix, com.airbnb.lottie.c.b.g.a maskMode) {
        Paint paint;
        int i;
        com.airbnb.lottie.a.b.a<Integer, Integer> opacityAnimation;
        int alpha;
        switch (maskMode) {
            case MaskModeSubtract:
                paint = this.i;
                break;
            default:
                paint = this.h;
                break;
        }
        int size = this.q.a().size();
        boolean hasMask = false;
        for (i = 0; i < size; i++) {
            if (((com.airbnb.lottie.c.b.g) this.q.a().get(i)).a() == maskMode) {
                hasMask = true;
                if (!hasMask) {
                    com.airbnb.lottie.d.a("Layer#drawMask");
                    com.airbnb.lottie.d.a("Layer#saveLayer");
                    canvas.saveLayer(this.l, paint, 19);
                    com.airbnb.lottie.d.b("Layer#saveLayer");
                    a(canvas);
                    for (i = 0; i < size; i++) {
                        if (((com.airbnb.lottie.c.b.g) this.q.a().get(i)).a() == maskMode) {
                            this.e.set((Path) ((com.airbnb.lottie.a.b.a) this.q.b().get(i)).e());
                            this.e.transform(matrix);
                            opacityAnimation = (com.airbnb.lottie.a.b.a) this.q.c().get(i);
                            alpha = this.g.getAlpha();
                            this.g.setAlpha((int) (((float) ((Integer) opacityAnimation.e()).intValue()) * 2.55f));
                            canvas.drawPath(this.e, this.g);
                            this.g.setAlpha(alpha);
                        }
                    }
                    com.airbnb.lottie.d.a("Layer#restoreLayer");
                    canvas.restore();
                    com.airbnb.lottie.d.b("Layer#restoreLayer");
                    com.airbnb.lottie.d.b("Layer#drawMask");
                }
            }
        }
        if (!hasMask) {
            com.airbnb.lottie.d.a("Layer#drawMask");
            com.airbnb.lottie.d.a("Layer#saveLayer");
            canvas.saveLayer(this.l, paint, 19);
            com.airbnb.lottie.d.b("Layer#saveLayer");
            a(canvas);
            for (i = 0; i < size; i++) {
                if (((com.airbnb.lottie.c.b.g) this.q.a().get(i)).a() == maskMode) {
                    this.e.set((Path) ((com.airbnb.lottie.a.b.a) this.q.b().get(i)).e());
                    this.e.transform(matrix);
                    opacityAnimation = (com.airbnb.lottie.a.b.a) this.q.c().get(i);
                    alpha = this.g.getAlpha();
                    this.g.setAlpha((int) (((float) ((Integer) opacityAnimation.e()).intValue()) * 2.55f));
                    canvas.drawPath(this.e, this.g);
                    this.g.setAlpha(alpha);
                }
            }
            com.airbnb.lottie.d.a("Layer#restoreLayer");
            canvas.restore();
            com.airbnb.lottie.d.b("Layer#restoreLayer");
            com.airbnb.lottie.d.b("Layer#drawMask");
        }
    }

    private boolean d() {
        return (this.q == null || this.q.b().isEmpty()) ? false : true;
    }

    private void a(boolean visible) {
        if (visible != this.v) {
            this.v = visible;
            this.b.invalidateSelf();
        }
    }

    void a(@FloatRange(from = 0.0d, to = 1.0d) float progress) {
        this.d.a(progress);
        if (this.c.b() != 0.0f) {
            progress /= this.c.b();
        }
        if (this.r != null) {
            this.r.a(progress * this.r.c.b());
        }
        for (int i = 0; i < this.u.size(); i++) {
            ((com.airbnb.lottie.a.b.a) this.u.get(i)).a(progress);
        }
    }

    public final String b() {
        return this.c.f();
    }

    public final void a(List<com.airbnb.lottie.a.a.b> list, List<com.airbnb.lottie.a.a.b> list2) {
    }

    void b(e keyPath, int depth, List<e> list, e currentPartialKeyPath) {
    }

    @CallSuper
    public <T> void a(T property, @Nullable com.airbnb.lottie.f.c<T> callback) {
        this.d.a(property, callback);
    }

    public final void a() {
        this.b.invalidateSelf();
    }

    public final void a(e keyPath, int depth, List<e> accumulator, e currentPartialKeyPath) {
        if (keyPath.a(this.c.f(), depth)) {
            if (!"__container".equals(this.c.f())) {
                currentPartialKeyPath = currentPartialKeyPath.a(this.c.f());
                if (keyPath.c(this.c.f(), depth)) {
                    accumulator.add(currentPartialKeyPath.a((f) this));
                }
            }
            if (keyPath.d(this.c.f(), depth)) {
                b(keyPath, depth + keyPath.b(this.c.f(), depth), accumulator, currentPartialKeyPath);
            }
        }
    }
}
