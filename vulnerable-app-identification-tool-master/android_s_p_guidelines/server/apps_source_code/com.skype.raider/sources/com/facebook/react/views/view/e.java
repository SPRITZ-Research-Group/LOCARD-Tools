package com.facebook.react.views.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.facebook.react.uimanager.ad;
import com.facebook.react.uimanager.c;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nullable;

public final class e extends Drawable {
    @Nullable
    private ad a;
    @Nullable
    private ad b;
    @Nullable
    private ad c;
    @Nullable
    private a d;
    @Nullable
    private PathEffect e;
    @Nullable
    private Path f;
    @Nullable
    private Path g;
    @Nullable
    private Path h;
    @Nullable
    private RectF i;
    @Nullable
    private RectF j;
    private boolean k = false;
    private float l = Float.NaN;
    private final Paint m = new Paint(1);
    private int n = 0;
    private int o = 255;
    @Nullable
    private float[] p;

    private enum a {
        SOLID,
        DASHED,
        DOTTED
    }

    public e(byte b) {
    }

    protected final void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.k = true;
    }

    public final void setAlpha(int alpha) {
        if (alpha != this.o) {
            this.o = alpha;
            invalidateSelf();
        }
    }

    public final int getAlpha() {
        return this.o;
    }

    public final void setColorFilter(ColorFilter cf) {
    }

    public final int getOpacity() {
        int a = a.a(this.n, this.o) >>> 24;
        if (a == 255) {
            return -1;
        }
        if (a == 0) {
            return -2;
        }
        return -3;
    }

    public final void getOutline(Outline outline) {
        if (VERSION.SDK_INT < 21) {
            super.getOutline(outline);
        } else if ((com.facebook.yoga.a.a(this.l) || this.l <= 0.0f) && this.p == null) {
            outline.setRect(getBounds());
        } else {
            a();
            outline.setConvexPath(this.g);
        }
    }

    public final void a(int position, float width) {
        if (this.a == null) {
            this.a = new ad();
        }
        if (!c.a(this.a.b(position), width)) {
            this.a.a(position, width);
            if (position == 8) {
                this.k = true;
            }
            invalidateSelf();
        }
    }

    public final void a(@Nullable String style) {
        a borderStyle;
        if (style == null) {
            borderStyle = null;
        } else {
            borderStyle = a.valueOf(style.toUpperCase(Locale.US));
        }
        if (this.d != borderStyle) {
            this.d = borderStyle;
            this.k = true;
            invalidateSelf();
        }
    }

    public final void a(float radius) {
        if (!c.a(this.l, radius)) {
            this.l = radius;
            this.k = true;
            invalidateSelf();
        }
    }

    public final void a(float radius, int position) {
        if (this.p == null) {
            this.p = new float[4];
            Arrays.fill(this.p, Float.NaN);
        }
        if (!c.a(this.p[position], radius)) {
            this.p[position] = radius;
            this.k = true;
            invalidateSelf();
        }
    }

    public final void a(int color) {
        this.n = color;
        invalidateSelf();
    }

    private void a() {
        if (this.k) {
            float topLeftRadius;
            float topRightRadius;
            float bottomRightRadius;
            float bottomLeftRadius;
            this.k = false;
            if (this.f == null) {
                this.f = new Path();
                this.i = new RectF();
                this.g = new Path();
                this.j = new RectF();
            }
            this.f.reset();
            this.g.reset();
            this.i.set(getBounds());
            this.j.set(getBounds());
            float fullBorderWidth = b();
            if (fullBorderWidth > 0.0f) {
                this.i.inset(0.5f * fullBorderWidth, 0.5f * fullBorderWidth);
            }
            float defaultBorderRadius = !com.facebook.yoga.a.a(this.l) ? this.l : 0.0f;
            if (this.p == null || com.facebook.yoga.a.a(this.p[0])) {
                topLeftRadius = defaultBorderRadius;
            } else {
                topLeftRadius = this.p[0];
            }
            if (this.p == null || com.facebook.yoga.a.a(this.p[1])) {
                topRightRadius = defaultBorderRadius;
            } else {
                topRightRadius = this.p[1];
            }
            if (this.p == null || com.facebook.yoga.a.a(this.p[2])) {
                bottomRightRadius = defaultBorderRadius;
            } else {
                bottomRightRadius = this.p[2];
            }
            if (this.p == null || com.facebook.yoga.a.a(this.p[3])) {
                bottomLeftRadius = defaultBorderRadius;
            } else {
                bottomLeftRadius = this.p[3];
            }
            this.f.addRoundRect(this.i, new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius}, Direction.CW);
            float extraRadiusForOutline = 0.0f;
            if (this.a != null) {
                extraRadiusForOutline = this.a.a(8) / 2.0f;
            }
            this.g.addRoundRect(this.j, new float[]{topLeftRadius + extraRadiusForOutline, topLeftRadius + extraRadiusForOutline, topRightRadius + extraRadiusForOutline, topRightRadius + extraRadiusForOutline, bottomRightRadius + extraRadiusForOutline, bottomRightRadius + extraRadiusForOutline, bottomLeftRadius + extraRadiusForOutline, bottomLeftRadius + extraRadiusForOutline}, Direction.CW);
        }
    }

    private float b() {
        return (this.a == null || com.facebook.yoga.a.a(this.a.b(8))) ? 0.0f : this.a.b(8);
    }

    private int b(int position) {
        return this.a != null ? Math.round(this.a.a(position)) : 0;
    }

    private static int a(float alpha, float rgb) {
        return (16777215 & ((int) rgb)) | (-16777216 & (((int) alpha) << 24));
    }

    private int c(int position) {
        return a(this.c != null ? this.c.a(position) : 255.0f, this.b != null ? this.b.a(position) : 0.0f);
    }

    public final void draw(Canvas canvas) {
        float b;
        PathEffect pathEffect;
        if (this.d != null) {
            a aVar = this.d;
            b = b();
            switch (aVar) {
                case SOLID:
                    pathEffect = null;
                    break;
                case DASHED:
                    pathEffect = new DashPathEffect(new float[]{3.0f * b, 3.0f * b, 3.0f * b, b * 3.0f}, 0.0f);
                    break;
                case DOTTED:
                    pathEffect = new DashPathEffect(new float[]{b, b, b, b}, 0.0f);
                    break;
                default:
                    pathEffect = null;
                    break;
            }
        }
        pathEffect = null;
        this.e = pathEffect;
        this.m.setPathEffect(this.e);
        Object obj = (this.p != null || (!com.facebook.yoga.a.a(this.l) && this.l > 0.0f)) ? 1 : null;
        int a;
        if (obj == null) {
            a = a.a(this.n, this.o);
            if (Color.alpha(a) != 0) {
                this.m.setColor(a);
                this.m.setStyle(Style.FILL);
                canvas.drawRect(getBounds(), this.m);
            }
            if (b(0) > 0 || b(1) > 0 || b(2) > 0 || b(3) > 0) {
                Rect bounds = getBounds();
                int b2 = b(0);
                int b3 = b(1);
                int b4 = b(2);
                int b5 = b(3);
                int c = c(0);
                int c2 = c(1);
                int c3 = c(2);
                int c4 = c(3);
                int i = bounds.left;
                int i2 = bounds.top;
                a = (b5 > 0 ? c4 : -1) & (((b2 > 0 ? c : -1) & (b3 > 0 ? c2 : -1)) & (b4 > 0 ? c3 : -1));
                if (a != ((b5 > 0 ? c4 : 0) | (((b2 > 0 ? c : 0) | (b3 > 0 ? c2 : 0)) | (b4 > 0 ? c3 : 0)))) {
                    a = 0;
                }
                if (a == 0) {
                    if (this.h == null) {
                        this.h = new Path();
                    }
                    this.m.setAntiAlias(false);
                    a = bounds.width();
                    int height = bounds.height();
                    if (b2 > 0 && c != 0) {
                        this.m.setColor(c);
                        this.h.reset();
                        this.h.moveTo((float) i, (float) i2);
                        this.h.lineTo((float) (i + b2), (float) (i2 + b3));
                        this.h.lineTo((float) (i + b2), (float) ((i2 + height) - b5));
                        this.h.lineTo((float) i, (float) (i2 + height));
                        this.h.lineTo((float) i, (float) i2);
                        canvas.drawPath(this.h, this.m);
                    }
                    if (b3 > 0 && c2 != 0) {
                        this.m.setColor(c2);
                        this.h.reset();
                        this.h.moveTo((float) i, (float) i2);
                        this.h.lineTo((float) (i + b2), (float) (i2 + b3));
                        this.h.lineTo((float) ((i + a) - b4), (float) (i2 + b3));
                        this.h.lineTo((float) (i + a), (float) i2);
                        this.h.lineTo((float) i, (float) i2);
                        canvas.drawPath(this.h, this.m);
                    }
                    if (b4 > 0 && c3 != 0) {
                        this.m.setColor(c3);
                        this.h.reset();
                        this.h.moveTo((float) (i + a), (float) i2);
                        this.h.lineTo((float) (i + a), (float) (i2 + height));
                        this.h.lineTo((float) ((i + a) - b4), (float) ((i2 + height) - b5));
                        this.h.lineTo((float) ((i + a) - b4), (float) (i2 + b3));
                        this.h.lineTo((float) (i + a), (float) i2);
                        canvas.drawPath(this.h, this.m);
                    }
                    if (b5 > 0 && c4 != 0) {
                        this.m.setColor(c4);
                        this.h.reset();
                        this.h.moveTo((float) i, (float) (i2 + height));
                        this.h.lineTo((float) (i + a), (float) (i2 + height));
                        this.h.lineTo((float) ((a + i) - b4), (float) ((i2 + height) - b5));
                        this.h.lineTo((float) (i + b2), (float) ((i2 + height) - b5));
                        this.h.lineTo((float) i, (float) (height + i2));
                        canvas.drawPath(this.h, this.m);
                    }
                    this.m.setAntiAlias(true);
                    return;
                } else if (Color.alpha(a) != 0) {
                    c = bounds.right;
                    int i3 = bounds.bottom;
                    this.m.setColor(a);
                    if (b2 > 0) {
                        canvas.drawRect((float) i, (float) i2, (float) (i + b2), (float) (i3 - b5), this.m);
                    }
                    if (b3 > 0) {
                        canvas.drawRect((float) (i + b2), (float) i2, (float) c, (float) (i2 + b3), this.m);
                    }
                    if (b4 > 0) {
                        canvas.drawRect((float) (c - b4), (float) (i2 + b3), (float) c, (float) i3, this.m);
                    }
                    if (b5 > 0) {
                        canvas.drawRect((float) i, (float) (i3 - b5), (float) (c - b4), (float) i3, this.m);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        a();
        a = a.a(this.n, this.o);
        if (Color.alpha(a) != 0) {
            this.m.setColor(a);
            this.m.setStyle(Style.FILL);
            canvas.drawPath(this.f, this.m);
        }
        float b6 = b();
        if (b6 > 0.0f) {
            float b7 = (this.b == null || com.facebook.yoga.a.a(this.b.b(8))) ? 0.0f : this.b.b(8);
            b = (this.c == null || com.facebook.yoga.a.a(this.c.b(8))) ? 255.0f : this.c.b(8);
            this.m.setColor(a.a(a(b, b7), this.o));
            this.m.setStyle(Style.STROKE);
            this.m.setStrokeWidth(b6);
            canvas.drawPath(this.f, this.m);
        }
    }

    public final void a(int position, float rgb, float alpha) {
        if (this.b == null) {
            this.b = new ad(0.0f);
        }
        if (!c.a(this.b.b(position), rgb)) {
            this.b.a(position, rgb);
            invalidateSelf();
        }
        if (this.c == null) {
            this.c = new ad(255.0f);
        }
        if (!c.a(this.c.b(position), alpha)) {
            this.c.a(position, alpha);
            invalidateSelf();
        }
    }
}
