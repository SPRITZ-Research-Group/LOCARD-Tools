package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import javax.annotation.Nullable;

final class g extends a {
    private static final Paint c = new Paint(1);
    private static final float[] d = new float[4];
    private float e;
    private float f;
    private float g;
    private float h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m = 0;
    private int n;
    @Nullable
    private DashPathEffect o;
    @Nullable
    private Path p;

    g() {
    }

    @Nullable
    protected final /* synthetic */ PathEffect d() {
        return l();
    }

    public final void a(int position, float borderWidth) {
        switch (position) {
            case 0:
                this.e = borderWidth;
                return;
            case 1:
                this.f = borderWidth;
                return;
            case 2:
                this.g = borderWidth;
                return;
            case 3:
                this.h = borderWidth;
                return;
            case 8:
                a(borderWidth);
                return;
            default:
                return;
        }
    }

    public final void a(@Nullable String style) {
        if ("dotted".equals(style)) {
            this.m = 1;
        } else if ("dashed".equals(style)) {
            this.m = 2;
        } else {
            this.m = 0;
        }
        c(32);
    }

    public final void e(int position) {
        switch (position) {
            case 0:
                d(2);
                return;
            case 1:
                d(4);
                return;
            case 2:
                d(8);
                return;
            case 3:
                d(16);
                return;
            case 8:
                a(-16777216);
                return;
            default:
                return;
        }
    }

    public final void a(int position, int borderColor) {
        switch (position) {
            case 0:
                this.i = borderColor;
                c(2);
                return;
            case 1:
                this.j = borderColor;
                c(4);
                return;
            case 2:
                this.k = borderColor;
                c(8);
                return;
            case 3:
                this.l = borderColor;
                c(16);
                return;
            case 8:
                a(borderColor);
                return;
            default:
                return;
        }
    }

    public final void f(int backgroundColor) {
        this.n = backgroundColor;
    }

    protected final void c(Canvas canvas) {
        if (b() >= 0.5f || l() != null) {
            if (this.n != 0) {
                c.setColor(this.n);
                canvas.drawPath(e(), c);
            }
            a(canvas);
            return;
        }
        int i;
        int c = c();
        float a = a();
        float i2 = i();
        float a2 = a(this.f, a);
        float f = i2 + a2;
        int a3 = a(4, this.j, c);
        float k = k();
        float a4 = a(this.h, a);
        float f2 = k - a4;
        int a5 = a(16, this.l, c);
        float h = h();
        float a6 = a(this.e, a);
        float f3 = h + a6;
        int a7 = a(2, this.i, c);
        float j = j();
        float a8 = a(this.g, a);
        a = j - a8;
        int a9 = a(8, this.k, c);
        c = (a4 > 0.0f ? a5 : -1) & (((a6 > 0.0f ? a7 : -1) & (a2 > 0.0f ? a3 : -1)) & (a8 > 0.0f ? a9 : -1));
        if (c == ((a4 > 0.0f ? a5 : 0) | (((a6 > 0.0f ? a7 : 0) | (a2 > 0.0f ? a3 : 0)) | (a8 > 0.0f ? a9 : 0)))) {
            i = c;
        } else {
            i = 0;
        }
        if (i == 0) {
            Path path;
            if (this.p == null) {
                this.p = new Path();
            }
            if (Color.alpha(this.n) != 0) {
                c.setColor(this.n);
                canvas.drawRect(h, i2, j, k, c);
            }
            if (!(a2 == 0.0f || Color.alpha(a3) == 0)) {
                c.setColor(a3);
                path = this.p;
                path.reset();
                path.moveTo(h, i2);
                path.lineTo(f3, f);
                path.lineTo(a, f);
                path.lineTo(j, i2);
                path.lineTo(h, i2);
                canvas.drawPath(this.p, c);
            }
            if (!(a4 == 0.0f || Color.alpha(a5) == 0)) {
                c.setColor(a5);
                path = this.p;
                path.reset();
                path.moveTo(h, k);
                path.lineTo(j, k);
                path.lineTo(a, f2);
                path.lineTo(f3, f2);
                path.lineTo(h, k);
                canvas.drawPath(this.p, c);
            }
            if (!(a6 == 0.0f || Color.alpha(a7) == 0)) {
                c.setColor(a7);
                path = this.p;
                path.reset();
                path.moveTo(h, i2);
                path.lineTo(f3, f);
                path.lineTo(f3, f2);
                path.lineTo(h, k);
                path.lineTo(h, i2);
                canvas.drawPath(this.p, c);
            }
            if (a8 != 0.0f && Color.alpha(a9) != 0) {
                c.setColor(a9);
                path = this.p;
                path.reset();
                path.moveTo(j, i2);
                path.lineTo(j, k);
                path.lineTo(a, f2);
                path.lineTo(a, f);
                path.lineTo(j, i2);
                canvas.drawPath(this.p, c);
            }
        } else if (Color.alpha(i) != 0) {
            if (Color.alpha(this.n) != 0) {
                c.setColor(this.n);
                if (Color.alpha(i) == 255) {
                    canvas.drawRect(f3, f, a, f2, c);
                } else {
                    canvas.drawRect(h, i2, j, k, c);
                }
            }
            c.setColor(i);
            if (a6 > 0.0f) {
                canvas.drawRect(h, i2, f3, k - a4, c);
            }
            if (a2 > 0.0f) {
                canvas.drawRect(h + a6, i2, j, f, c);
            }
            if (a8 > 0.0f) {
                canvas.drawRect(a, i2 + a2, j, k, c);
            }
            if (a4 > 0.0f) {
                canvas.drawRect(h, f2, j - a8, k, c);
            }
        }
    }

    @Nullable
    private DashPathEffect l() {
        if (b(32)) {
            switch (this.m) {
                case 1:
                    this.o = c(a());
                    break;
                case 2:
                    this.o = c(a() * 3.0f);
                    break;
                default:
                    this.o = null;
                    break;
            }
            d(32);
        }
        return this.o;
    }

    private int a(int flag, int color, int defaultColor) {
        return b(flag) ? color : defaultColor;
    }

    private static float a(float width, float defaultWidth) {
        return (width == 0.0f || width != width) ? defaultWidth : width;
    }

    private static DashPathEffect c(float borderWidth) {
        for (int i = 0; i < 4; i++) {
            d[i] = borderWidth;
        }
        return new DashPathEffect(d, 0.0f);
    }
}
