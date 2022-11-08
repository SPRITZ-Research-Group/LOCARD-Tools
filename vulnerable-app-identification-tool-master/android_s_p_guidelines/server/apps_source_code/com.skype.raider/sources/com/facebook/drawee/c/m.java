package com.facebook.drawee.c;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import java.util.Arrays;
import javax.annotation.Nullable;

public final class m extends g implements j {
    @VisibleForTesting
    a a = a.OVERLAY_COLOR;
    @VisibleForTesting
    final float[] c = new float[8];
    @VisibleForTesting
    final Paint d = new Paint(1);
    private final RectF e = new RectF();
    @Nullable
    private RectF f;
    @Nullable
    private Matrix g;
    private final float[] h = new float[8];
    private boolean i = false;
    private float j = 0.0f;
    private int k = 0;
    private int l = 0;
    private float m = 0.0f;
    private boolean n = false;
    private final Path o = new Path();
    private final Path p = new Path();
    private final RectF q = new RectF();

    public enum a {
        OVERLAY_COLOR,
        CLIPPING
    }

    public m(Drawable drawable) {
        super((Drawable) h.a((Object) drawable));
    }

    public final void a(boolean isCircle) {
        this.i = isCircle;
        b();
        invalidateSelf();
    }

    public final void h_() {
        Arrays.fill(this.h, 0.0f);
        b();
        invalidateSelf();
    }

    public final void a(float[] radii) {
        if (radii == null) {
            Arrays.fill(this.h, 0.0f);
        } else {
            boolean z;
            if (radii.length == 8) {
                z = true;
            } else {
                z = false;
            }
            h.a(z, (Object) "radii should have exactly 8 values");
            System.arraycopy(radii, 0, this.h, 0, 8);
        }
        b();
        invalidateSelf();
    }

    public final void a(int overlayColor) {
        this.l = overlayColor;
        invalidateSelf();
    }

    public final void a(int color, float width) {
        this.k = color;
        this.j = width;
        b();
        invalidateSelf();
    }

    public final void a(float padding) {
        this.m = padding;
        b();
        invalidateSelf();
    }

    public final void b(boolean scaleDownInsideBorders) {
        this.n = scaleDownInsideBorders;
        b();
        invalidateSelf();
    }

    protected final void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        b();
    }

    private void b() {
        this.o.reset();
        this.p.reset();
        this.q.set(getBounds());
        this.q.inset(this.m, this.m);
        this.o.addRect(this.q, Direction.CW);
        if (this.i) {
            this.o.addCircle(this.q.centerX(), this.q.centerY(), Math.min(this.q.width(), this.q.height()) / 2.0f, Direction.CW);
        } else {
            this.o.addRoundRect(this.q, this.h, Direction.CW);
        }
        this.q.inset(-this.m, -this.m);
        this.q.inset(this.j / 2.0f, this.j / 2.0f);
        if (this.i) {
            this.p.addCircle(this.q.centerX(), this.q.centerY(), Math.min(this.q.width(), this.q.height()) / 2.0f, Direction.CW);
        } else {
            for (int i = 0; i < this.c.length; i++) {
                this.c[i] = (this.h[i] + this.m) - (this.j / 2.0f);
            }
            this.p.addRoundRect(this.q, this.c, Direction.CW);
        }
        this.q.inset((-this.j) / 2.0f, (-this.j) / 2.0f);
    }

    public final void draw(Canvas canvas) {
        this.e.set(getBounds());
        int saveCount;
        switch (this.a) {
            case CLIPPING:
                saveCount = canvas.save();
                this.o.setFillType(FillType.EVEN_ODD);
                canvas.clipPath(this.o);
                super.draw(canvas);
                canvas.restoreToCount(saveCount);
                break;
            case OVERLAY_COLOR:
                if (this.n) {
                    if (this.f == null) {
                        this.f = new RectF(this.e);
                        this.g = new Matrix();
                    } else {
                        this.f.set(this.e);
                    }
                    this.f.inset(this.j, this.j);
                    this.g.setRectToRect(this.e, this.f, ScaleToFit.FILL);
                    saveCount = canvas.save();
                    canvas.clipRect(this.e);
                    canvas.concat(this.g);
                    super.draw(canvas);
                    canvas.restoreToCount(saveCount);
                } else {
                    super.draw(canvas);
                }
                this.d.setStyle(Style.FILL);
                this.d.setColor(this.l);
                this.d.setStrokeWidth(0.0f);
                this.o.setFillType(FillType.EVEN_ODD);
                canvas.drawPath(this.o, this.d);
                if (this.i) {
                    float paddingH = ((this.e.width() - this.e.height()) + this.j) / 2.0f;
                    float paddingV = ((this.e.height() - this.e.width()) + this.j) / 2.0f;
                    if (paddingH > 0.0f) {
                        canvas.drawRect(this.e.left, this.e.top, this.e.left + paddingH, this.e.bottom, this.d);
                        canvas.drawRect(this.e.right - paddingH, this.e.top, this.e.right, this.e.bottom, this.d);
                    }
                    if (paddingV > 0.0f) {
                        canvas.drawRect(this.e.left, this.e.top, this.e.right, this.e.top + paddingV, this.d);
                        canvas.drawRect(this.e.left, this.e.bottom - paddingV, this.e.right, this.e.bottom, this.d);
                        break;
                    }
                }
                break;
        }
        if (this.k != 0) {
            this.d.setStyle(Style.STROKE);
            this.d.setColor(this.k);
            this.d.setStrokeWidth(this.j);
            this.o.setFillType(FillType.EVEN_ODD);
            canvas.drawPath(this.p, this.d);
        }
    }
}
