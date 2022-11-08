package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.bq;
import defpackage.br;

final class g extends Drawable {
    static h a;
    private static final double b = Math.cos(Math.toRadians(45.0d));
    private final int c;
    private Paint d;
    private Paint e;
    private Paint f;
    private final RectF g;
    private float h;
    private Path i;
    private float j;
    private float k;
    private float l;
    private ColorStateList m;
    private boolean n = true;
    private final int o;
    private final int p;
    private boolean q = true;
    private boolean r = false;

    public final int getOpacity() {
        return -3;
    }

    g(Resources resources, ColorStateList colorStateList, float f, float f2, float f3) {
        this.o = resources.getColor(bq.cardview_shadow_start_color);
        this.p = resources.getColor(bq.cardview_shadow_end_color);
        this.c = resources.getDimensionPixelSize(br.cardview_compat_inset_shadow);
        this.d = new Paint(5);
        b(colorStateList);
        this.e = new Paint(5);
        this.e.setStyle(Style.FILL);
        this.h = (float) ((int) (f + 0.5f));
        this.g = new RectF();
        this.f = new Paint(this.e);
        this.f.setAntiAlias(false);
        a(f2, f3);
    }

    private void b(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.m = colorStateList;
        this.d.setColor(this.m.getColorForState(getState(), this.m.getDefaultColor()));
    }

    private static int d(float f) {
        int i = (int) (f + 0.5f);
        return i % 2 == 1 ? i - 1 : i;
    }

    final void a(boolean z) {
        this.q = z;
        invalidateSelf();
    }

    public final void setAlpha(int i) {
        this.d.setAlpha(i);
        this.e.setAlpha(i);
        this.f.setAlpha(i);
    }

    protected final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.n = true;
    }

    private void a(float f, float f2) {
        StringBuilder stringBuilder;
        if (f < BitmapDescriptorFactory.HUE_RED) {
            stringBuilder = new StringBuilder("Invalid shadow size ");
            stringBuilder.append(f);
            stringBuilder.append(". Must be >= 0");
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (f2 >= BitmapDescriptorFactory.HUE_RED) {
            f = (float) d(f);
            f2 = (float) d(f2);
            if (f > f2) {
                if (!this.r) {
                    this.r = true;
                }
                f = f2;
            }
            if (this.l != f || this.j != f2) {
                this.l = f;
                this.j = f2;
                this.k = (float) ((int) (((f * 1.5f) + ((float) this.c)) + 0.5f));
                this.n = true;
                invalidateSelf();
            }
        } else {
            stringBuilder = new StringBuilder("Invalid max shadow size ");
            stringBuilder.append(f2);
            stringBuilder.append(". Must be >= 0");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public final boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil((double) a(this.j, this.h, this.q));
        int ceil2 = (int) Math.ceil((double) b(this.j, this.h, this.q));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    static float a(float f, float f2, boolean z) {
        if (!z) {
            return f * 1.5f;
        }
        double d = (double) (f * 1.5f);
        double d2 = 1.0d - b;
        double d3 = (double) f2;
        Double.isNaN(d3);
        d2 *= d3;
        Double.isNaN(d);
        return (float) (d + d2);
    }

    static float b(float f, float f2, boolean z) {
        if (!z) {
            return f;
        }
        double d = (double) f;
        double d2 = 1.0d - b;
        double d3 = (double) f2;
        Double.isNaN(d3);
        d2 *= d3;
        Double.isNaN(d);
        return (float) (d + d2);
    }

    protected final boolean onStateChange(int[] iArr) {
        int colorForState = this.m.getColorForState(iArr, this.m.getDefaultColor());
        if (this.d.getColor() == colorForState) {
            return false;
        }
        this.d.setColor(colorForState);
        this.n = true;
        invalidateSelf();
        return true;
    }

    public final boolean isStateful() {
        return (this.m != null && this.m.isStateful()) || super.isStateful();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
    }

    final void a(float f) {
        if (f >= BitmapDescriptorFactory.HUE_RED) {
            f = (float) ((int) (f + 0.5f));
            if (this.h != f) {
                this.h = f;
                this.n = true;
                invalidateSelf();
                return;
            }
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Invalid radius ");
        stringBuilder.append(f);
        stringBuilder.append(". Must be >= 0");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public final void draw(Canvas canvas) {
        int i;
        Canvas canvas2 = canvas;
        if (this.n) {
            Rect bounds = getBounds();
            float f = r0.j * 1.5f;
            r0.g.set(((float) bounds.left) + r0.j, ((float) bounds.top) + f, ((float) bounds.right) - r0.j, ((float) bounds.bottom) - f);
            RectF rectF = new RectF(-r0.h, -r0.h, r0.h, r0.h);
            RectF rectF2 = new RectF(rectF);
            rectF2.inset(-r0.k, -r0.k);
            if (r0.i == null) {
                r0.i = new Path();
            } else {
                r0.i.reset();
            }
            r0.i.setFillType(FillType.EVEN_ODD);
            r0.i.moveTo(-r0.h, BitmapDescriptorFactory.HUE_RED);
            r0.i.rLineTo(-r0.k, BitmapDescriptorFactory.HUE_RED);
            r0.i.arcTo(rectF2, 180.0f, 90.0f, false);
            r0.i.arcTo(rectF, 270.0f, -90.0f, false);
            r0.i.close();
            float f2 = r0.h / (r0.h + r0.k);
            r0.e.setShader(new RadialGradient(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, r0.h + r0.k, new int[]{r0.o, r0.o, r0.p}, new float[]{BitmapDescriptorFactory.HUE_RED, f2, 1.0f}, TileMode.CLAMP));
            r0.f.setShader(new LinearGradient(BitmapDescriptorFactory.HUE_RED, (-r0.h) + r0.k, BitmapDescriptorFactory.HUE_RED, (-r0.h) - r0.k, new int[]{r0.o, r0.o, r0.p}, new float[]{BitmapDescriptorFactory.HUE_RED, 0.5f, 1.0f}, TileMode.CLAMP));
            r0.f.setAntiAlias(false);
            r0.n = false;
        }
        canvas2.translate(BitmapDescriptorFactory.HUE_RED, r0.l / 2.0f);
        float f3 = (-r0.h) - r0.k;
        float f4 = (r0.h + ((float) r0.c)) + (r0.l / 2.0f);
        float f5 = f4 * 2.0f;
        Object obj = r0.g.width() - f5 > BitmapDescriptorFactory.HUE_RED ? 1 : null;
        Object obj2 = r0.g.height() - f5 > BitmapDescriptorFactory.HUE_RED ? 1 : null;
        int save = canvas.save();
        canvas2.translate(r0.g.left + f4, r0.g.top + f4);
        canvas2.drawPath(r0.i, r0.e);
        if (obj != null) {
            i = save;
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, f3, r0.g.width() - f5, -r0.h, r0.f);
        } else {
            i = save;
        }
        canvas2.restoreToCount(i);
        i = canvas.save();
        canvas2.translate(r0.g.right - f4, r0.g.bottom - f4);
        canvas2.rotate(180.0f);
        canvas2.drawPath(r0.i, r0.e);
        if (obj != null) {
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, f3, r0.g.width() - f5, (-r0.h) + r0.k, r0.f);
        }
        canvas2.restoreToCount(i);
        int save2 = canvas.save();
        canvas2.translate(r0.g.left + f4, r0.g.bottom - f4);
        canvas2.rotate(270.0f);
        canvas2.drawPath(r0.i, r0.e);
        if (obj2 != null) {
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, f3, r0.g.height() - f5, -r0.h, r0.f);
        }
        canvas2.restoreToCount(save2);
        int save3 = canvas.save();
        canvas2.translate(r0.g.right - f4, r0.g.top + f4);
        canvas2.rotate(90.0f);
        canvas2.drawPath(r0.i, r0.e);
        if (obj2 != null) {
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, f3, r0.g.height() - f5, -r0.h, r0.f);
        }
        canvas2.restoreToCount(save3);
        canvas2.translate(BitmapDescriptorFactory.HUE_RED, (-r0.l) / 2.0f);
        a.a(canvas2, r0.g, r0.h, r0.d);
    }

    final float a() {
        return this.h;
    }

    final void b(float f) {
        a(f, this.j);
    }

    final void c(float f) {
        a(this.l, f);
    }

    final float b() {
        return this.l;
    }

    final float c() {
        return this.j;
    }

    final float d() {
        return (Math.max(this.j, (this.h + ((float) this.c)) + (this.j / 2.0f)) * 2.0f) + ((this.j + ((float) this.c)) * 2.0f);
    }

    final float e() {
        return (Math.max(this.j, (this.h + ((float) this.c)) + ((this.j * 1.5f) / 2.0f)) * 2.0f) + (((this.j * 1.5f) + ((float) this.c)) * 2.0f);
    }

    final void a(ColorStateList colorStateList) {
        b(colorStateList);
        invalidateSelf();
    }

    final ColorStateList f() {
        return this.m;
    }
}
