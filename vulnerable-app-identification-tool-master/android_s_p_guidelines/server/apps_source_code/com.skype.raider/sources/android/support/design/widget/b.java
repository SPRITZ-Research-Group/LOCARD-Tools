package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.a;

class b extends Drawable {
    final Paint a = new Paint(1);
    final Rect b = new Rect();
    final RectF c = new RectF();
    float d;
    private int e;
    private int f;
    private int g;
    private int h;
    private ColorStateList i;
    private int j;
    private boolean k = true;
    private float l;

    public b() {
        this.a.setStyle(Style.STROKE);
    }

    final void a(int topOuterStrokeColor, int topInnerStrokeColor, int bottomOuterStrokeColor, int bottomInnerStrokeColor) {
        this.e = topOuterStrokeColor;
        this.f = topInnerStrokeColor;
        this.g = bottomOuterStrokeColor;
        this.h = bottomInnerStrokeColor;
    }

    final void a(float width) {
        if (this.d != width) {
            this.d = width;
            this.a.setStrokeWidth(1.3333f * width);
            this.k = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        if (this.k) {
            Paint paint = this.a;
            Rect rect = this.b;
            copyBounds(rect);
            float height = this.d / ((float) rect.height());
            float f = 0.0f;
            paint.setShader(new LinearGradient(0.0f, (float) rect.top, f, (float) rect.bottom, new int[]{a.a(this.e, this.j), a.a(this.f, this.j), a.a(a.b(this.f, 0), this.j), a.a(a.b(this.h, 0), this.j), a.a(this.h, this.j), a.a(this.g, this.j)}, new float[]{0.0f, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, TileMode.CLAMP));
            this.k = false;
        }
        float halfBorderWidth = this.a.getStrokeWidth() / 2.0f;
        RectF rectF = this.c;
        copyBounds(this.b);
        rectF.set(this.b);
        rectF.left += halfBorderWidth;
        rectF.top += halfBorderWidth;
        rectF.right -= halfBorderWidth;
        rectF.bottom -= halfBorderWidth;
        canvas.save();
        canvas.rotate(this.l, rectF.centerX(), rectF.centerY());
        canvas.drawOval(rectF, this.a);
        canvas.restore();
    }

    public boolean getPadding(Rect padding) {
        int borderWidth = Math.round(this.d);
        padding.set(borderWidth, borderWidth, borderWidth, borderWidth);
        return true;
    }

    public void setAlpha(int alpha) {
        this.a.setAlpha(alpha);
        invalidateSelf();
    }

    final void a(ColorStateList tint) {
        if (tint != null) {
            this.j = tint.getColorForState(getState(), this.j);
        }
        this.i = tint;
        this.k = true;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        return this.d > 0.0f ? -3 : -2;
    }

    final void b(float rotation) {
        if (rotation != this.l) {
            this.l = rotation;
            invalidateSelf();
        }
    }

    protected void onBoundsChange(Rect bounds) {
        this.k = true;
    }

    public boolean isStateful() {
        return (this.i != null && this.i.isStateful()) || super.isStateful();
    }

    protected boolean onStateChange(int[] state) {
        if (this.i != null) {
            int newColor = this.i.getColorForState(state, this.j);
            if (newColor != this.j) {
                this.k = true;
                this.j = newColor;
            }
        }
        if (this.k) {
            invalidateSelf();
        }
        return this.k;
    }
}
