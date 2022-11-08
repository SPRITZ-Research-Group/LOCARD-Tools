package com.facebook.drawee.c;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.l.b;
import java.util.Arrays;

public abstract class n extends Drawable implements j, r {
    private boolean A = true;
    @Nullable
    private s B;
    protected boolean a = false;
    protected boolean b = false;
    protected float c = 0.0f;
    protected final Path d = new Path();
    protected boolean e = true;
    protected int f = 0;
    protected final Path g = new Path();
    @VisibleForTesting
    final float[] h = new float[8];
    @Nullable
    @VisibleForTesting
    float[] i;
    @VisibleForTesting
    final RectF j = new RectF();
    @VisibleForTesting
    final RectF k = new RectF();
    @VisibleForTesting
    final RectF l = new RectF();
    @VisibleForTesting
    final RectF m = new RectF();
    @Nullable
    @VisibleForTesting
    RectF n;
    @VisibleForTesting
    final Matrix o = new Matrix();
    @VisibleForTesting
    final Matrix p = new Matrix();
    @VisibleForTesting
    final Matrix q = new Matrix();
    @VisibleForTesting
    final Matrix r = new Matrix();
    @VisibleForTesting
    final Matrix s = new Matrix();
    @Nullable
    @VisibleForTesting
    Matrix t;
    @Nullable
    @VisibleForTesting
    Matrix u;
    @VisibleForTesting
    final Matrix v = new Matrix();
    private final Drawable w;
    private final float[] x = new float[8];
    private float y = 0.0f;
    private boolean z = false;

    n(Drawable drawable) {
        this.w = drawable;
    }

    public final void a(boolean isCircle) {
        this.a = isCircle;
        this.A = true;
        invalidateSelf();
    }

    public final void h_() {
        h.b(true);
        Arrays.fill(this.x, 0.0f);
        this.b = false;
        this.A = true;
        invalidateSelf();
    }

    public final void a(float[] radii) {
        if (radii == null) {
            Arrays.fill(this.x, 0.0f);
            this.b = false;
        } else {
            boolean z;
            if (radii.length == 8) {
                z = true;
            } else {
                z = false;
            }
            h.a(z, (Object) "radii should have exactly 8 values");
            System.arraycopy(radii, 0, this.x, 0, 8);
            this.b = false;
            for (int i = 0; i < 8; i++) {
                int i2;
                boolean z2 = this.b;
                if (radii[i] > 0.0f) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                this.b = i2 | z2;
            }
        }
        this.A = true;
        invalidateSelf();
    }

    public final void a(int color, float width) {
        if (this.f != color || this.c != width) {
            this.f = color;
            this.c = width;
            this.A = true;
            invalidateSelf();
        }
    }

    public final void a(float padding) {
        if (this.y != padding) {
            this.y = padding;
            this.A = true;
            invalidateSelf();
        }
    }

    public final void b(boolean scaleDownInsideBorders) {
        if (this.z != scaleDownInsideBorders) {
            this.z = scaleDownInsideBorders;
            this.A = true;
            invalidateSelf();
        }
    }

    public final void a(@Nullable s transformCallback) {
        this.B = transformCallback;
    }

    protected final void c() {
        if (this.B != null) {
            this.B.a(this.q);
            this.B.a(this.j);
        } else {
            this.q.reset();
            this.j.set(getBounds());
        }
        this.l.set(0.0f, 0.0f, (float) getIntrinsicWidth(), (float) getIntrinsicHeight());
        this.m.set(this.w.getBounds());
        this.o.setRectToRect(this.l, this.m, ScaleToFit.FILL);
        if (this.z) {
            if (this.n == null) {
                this.n = new RectF(this.j);
            } else {
                this.n.set(this.j);
            }
            this.n.inset(this.c, this.c);
            if (this.t == null) {
                this.t = new Matrix();
            }
            this.t.setRectToRect(this.j, this.n, ScaleToFit.FILL);
        } else if (this.t != null) {
            this.t.reset();
        }
        if (!(this.q.equals(this.r) && this.o.equals(this.p) && (this.t == null || this.t.equals(this.u)))) {
            this.e = true;
            this.q.invert(this.s);
            this.v.set(this.q);
            if (this.z) {
                this.v.postConcat(this.t);
            }
            this.v.preConcat(this.o);
            this.r.set(this.q);
            this.p.set(this.o);
            if (this.z) {
                if (this.u == null) {
                    this.u = new Matrix(this.t);
                } else {
                    this.u.set(this.t);
                }
            } else if (this.u != null) {
                this.u.reset();
            }
        }
        if (!this.j.equals(this.k)) {
            this.A = true;
            this.k.set(this.j);
        }
    }

    protected final void d() {
        if (this.A) {
            int i;
            this.g.reset();
            this.j.inset(this.c / 2.0f, this.c / 2.0f);
            if (this.a) {
                this.g.addCircle(this.j.centerX(), this.j.centerY(), Math.min(this.j.width(), this.j.height()) / 2.0f, Direction.CW);
            } else {
                for (i = 0; i < this.h.length; i++) {
                    this.h[i] = (this.x[i] + this.y) - (this.c / 2.0f);
                }
                this.g.addRoundRect(this.j, this.h, Direction.CW);
            }
            this.j.inset((-this.c) / 2.0f, (-this.c) / 2.0f);
            this.d.reset();
            float totalPadding = this.y + (this.z ? this.c : 0.0f);
            this.j.inset(totalPadding, totalPadding);
            if (this.a) {
                this.d.addCircle(this.j.centerX(), this.j.centerY(), Math.min(this.j.width(), this.j.height()) / 2.0f, Direction.CW);
            } else if (this.z) {
                if (this.i == null) {
                    this.i = new float[8];
                }
                for (i = 0; i < this.h.length; i++) {
                    this.i[i] = this.x[i] - this.c;
                }
                this.d.addRoundRect(this.j, this.i, Direction.CW);
            } else {
                this.d.addRoundRect(this.j, this.x, Direction.CW);
            }
            this.j.inset(-totalPadding, -totalPadding);
            this.d.setFillType(FillType.WINDING);
            this.A = false;
        }
    }

    @VisibleForTesting
    boolean b() {
        return this.a || this.b || this.c > 0.0f;
    }

    protected void onBoundsChange(Rect bounds) {
        this.w.setBounds(bounds);
    }

    public int getIntrinsicWidth() {
        return this.w.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.w.getIntrinsicHeight();
    }

    public int getOpacity() {
        return this.w.getOpacity();
    }

    public void setColorFilter(int color, @NonNull Mode mode) {
        this.w.setColorFilter(color, mode);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.w.setColorFilter(colorFilter);
    }

    @Nullable
    @RequiresApi(api = 21)
    public ColorFilter getColorFilter() {
        return this.w.getColorFilter();
    }

    public void clearColorFilter() {
        this.w.clearColorFilter();
    }

    @RequiresApi(api = 19)
    public int getAlpha() {
        return this.w.getAlpha();
    }

    public void setAlpha(int alpha) {
        this.w.setAlpha(alpha);
    }

    public void draw(@NonNull Canvas canvas) {
        b.a();
        this.w.draw(canvas);
        b.a();
    }
}
