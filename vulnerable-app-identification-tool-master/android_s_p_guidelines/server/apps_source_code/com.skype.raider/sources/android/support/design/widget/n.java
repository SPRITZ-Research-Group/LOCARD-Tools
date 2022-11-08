package android.support.design.widget;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.design.a.c;
import android.support.v7.graphics.drawable.a;

final class n extends a {
    static final double a = Math.cos(Math.toRadians(45.0d));
    final Paint b;
    final Paint c;
    final RectF d;
    float e;
    Path f;
    float g;
    float h;
    float i;
    float j;
    private boolean k = true;
    private final int l;
    private final int m;
    private final int n;
    private boolean o = true;
    private float p;
    private boolean q = false;

    public n(Resources resources, Drawable content, float radius, float shadowSize, float maxShadowSize) {
        super(content);
        this.l = resources.getColor(c.design_fab_shadow_start_color);
        this.m = resources.getColor(c.design_fab_shadow_mid_color);
        this.n = resources.getColor(c.design_fab_shadow_end_color);
        this.b = new Paint(5);
        this.b.setStyle(Style.FILL);
        this.e = (float) Math.round(radius);
        this.d = new RectF();
        this.c = new Paint(this.b);
        this.c.setAntiAlias(false);
        a(shadowSize, maxShadowSize);
    }

    private static int b(float value) {
        int i = Math.round(value);
        return i % 2 == 1 ? i - 1 : i;
    }

    public final void a() {
        this.o = false;
        invalidateSelf();
    }

    public final void setAlpha(int alpha) {
        super.setAlpha(alpha);
        this.b.setAlpha(alpha);
        this.c.setAlpha(alpha);
    }

    protected final void onBoundsChange(Rect bounds) {
        this.k = true;
    }

    final void a(float shadowSize, float maxShadowSize) {
        if (shadowSize < 0.0f || maxShadowSize < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        shadowSize = (float) b(shadowSize);
        maxShadowSize = (float) b(maxShadowSize);
        if (shadowSize > maxShadowSize) {
            shadowSize = maxShadowSize;
            if (!this.q) {
                this.q = true;
            }
        }
        if (this.j != shadowSize || this.h != maxShadowSize) {
            this.j = shadowSize;
            this.h = maxShadowSize;
            this.i = (float) Math.round(1.5f * shadowSize);
            this.g = maxShadowSize;
            this.k = true;
            invalidateSelf();
        }
    }

    public final boolean getPadding(Rect padding) {
        float f = this.h;
        float f2 = this.e;
        if (this.o) {
            f = (float) ((((double) f2) * (1.0d - a)) + ((double) (f * 1.5f)));
        } else {
            f *= 1.5f;
        }
        int vOffset = (int) Math.ceil((double) f);
        f = this.h;
        f2 = this.e;
        if (this.o) {
            f = (float) ((((double) f2) * (1.0d - a)) + ((double) f));
        }
        int hOffset = (int) Math.ceil((double) f);
        padding.set(hOffset, vOffset, hOffset, vOffset);
        return true;
    }

    public final int getOpacity() {
        return -3;
    }

    public final void draw(Canvas canvas) {
        float f;
        Object obj;
        if (this.k) {
            Rect bounds = getBounds();
            float f2 = this.h * 1.5f;
            this.d.set(((float) bounds.left) + this.h, ((float) bounds.top) + f2, ((float) bounds.right) - this.h, ((float) bounds.bottom) - f2);
            b().setBounds((int) this.d.left, (int) this.d.top, (int) this.d.right, (int) this.d.bottom);
            RectF rectF = new RectF(-this.e, -this.e, this.e, this.e);
            RectF rectF2 = new RectF(rectF);
            rectF2.inset(-this.i, -this.i);
            if (this.f == null) {
                this.f = new Path();
            } else {
                this.f.reset();
            }
            this.f.setFillType(FillType.EVEN_ODD);
            this.f.moveTo(-this.e, 0.0f);
            this.f.rLineTo(-this.i, 0.0f);
            this.f.arcTo(rectF2, 180.0f, 90.0f, false);
            this.f.arcTo(rectF, 270.0f, -90.0f, false);
            this.f.close();
            float f3 = -rectF2.top;
            if (f3 > 0.0f) {
                float f4 = this.e / f3;
                f = f4 + ((1.0f - f4) / 2.0f);
                this.b.setShader(new RadialGradient(0.0f, 0.0f, f3, new int[]{0, this.l, this.m, this.n}, new float[]{0.0f, f4, f, 1.0f}, TileMode.CLAMP));
            }
            this.c.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.l, this.m, this.n}, new float[]{0.0f, 0.5f, 1.0f}, TileMode.CLAMP));
            this.c.setAntiAlias(false);
            this.k = false;
        }
        int save = canvas.save();
        canvas.rotate(this.p, this.d.centerX(), this.d.centerY());
        float f5 = (-this.e) - this.i;
        f = this.e;
        Object obj2 = this.d.width() - (2.0f * f) > 0.0f ? 1 : null;
        if (this.d.height() - (2.0f * f) > 0.0f) {
            obj = 1;
        } else {
            obj = null;
        }
        float f6 = f / ((this.j - (this.j * 0.5f)) + f);
        float f7 = f / ((this.j - (this.j * 0.25f)) + f);
        float f8 = f / (f + (this.j - (this.j * 1.0f)));
        int save2 = canvas.save();
        canvas.translate(this.d.left + f, this.d.top + f);
        canvas.scale(f6, f7);
        canvas.drawPath(this.f, this.b);
        if (obj2 != null) {
            canvas.scale(1.0f / f6, 1.0f);
            canvas.drawRect(0.0f, f5, this.d.width() - (2.0f * f), -this.e, this.c);
        }
        canvas.restoreToCount(save2);
        save2 = canvas.save();
        canvas.translate(this.d.right - f, this.d.bottom - f);
        canvas.scale(f6, f8);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f, this.b);
        if (obj2 != null) {
            canvas.scale(1.0f / f6, 1.0f);
            canvas.drawRect(0.0f, f5, this.d.width() - (2.0f * f), this.i + (-this.e), this.c);
        }
        canvas.restoreToCount(save2);
        int save3 = canvas.save();
        canvas.translate(this.d.left + f, this.d.bottom - f);
        canvas.scale(f6, f8);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f, this.b);
        if (obj != null) {
            canvas.scale(1.0f / f8, 1.0f);
            canvas.drawRect(0.0f, f5, this.d.height() - (2.0f * f), -this.e, this.c);
        }
        canvas.restoreToCount(save3);
        save3 = canvas.save();
        canvas.translate(this.d.right - f, this.d.top + f);
        canvas.scale(f6, f7);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f, this.b);
        if (obj != null) {
            canvas.scale(1.0f / f7, 1.0f);
            canvas.drawRect(0.0f, f5, this.d.height() - (2.0f * f), -this.e, this.c);
        }
        canvas.restoreToCount(save3);
        canvas.restoreToCount(save);
        super.draw(canvas);
    }

    final void a(float rotation) {
        if (this.p != rotation) {
            this.p = rotation;
            invalidateSelf();
        }
    }
}
