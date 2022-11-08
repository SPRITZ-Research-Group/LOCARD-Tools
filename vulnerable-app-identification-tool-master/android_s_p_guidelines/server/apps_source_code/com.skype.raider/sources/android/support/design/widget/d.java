package android.support.design.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.design.a.j;
import android.support.v4.text.e;
import android.support.v4.text.f;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.Interpolator;

final class d {
    private static final boolean a = (VERSION.SDK_INT < 18);
    private static final Paint b = null;
    private boolean A;
    private Bitmap B;
    private Paint C;
    private float D;
    private float E;
    private float F;
    private float G;
    private boolean H;
    private final TextPaint I;
    private Interpolator J;
    private Interpolator K;
    private float L;
    private float M;
    private float N;
    private int O;
    private float P;
    private float Q;
    private float R;
    private int S;
    private final View c;
    private boolean d;
    private float e;
    private final Rect f;
    private final Rect g;
    private final RectF h;
    private int i = 16;
    private int j = 16;
    private float k = 15.0f;
    private float l = 15.0f;
    private int m;
    private int n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private Typeface u;
    private Typeface v;
    private Typeface w;
    private CharSequence x;
    private CharSequence y;
    private boolean z;

    public d(View view) {
        this.c = view;
        this.I = new TextPaint();
        this.I.setAntiAlias(true);
        this.g = new Rect();
        this.f = new Rect();
        this.h = new RectF();
    }

    final void a(Interpolator interpolator) {
        this.K = interpolator;
        d();
    }

    final void b(Interpolator interpolator) {
        this.J = interpolator;
        d();
    }

    final void a(float textSize) {
        if (this.k != textSize) {
            this.k = textSize;
            d();
        }
    }

    final void a(int textColor) {
        if (this.n != textColor) {
            this.n = textColor;
            d();
        }
    }

    final void b(int textColor) {
        if (this.m != textColor) {
            this.m = textColor;
            d();
        }
    }

    final void a(int left, int top, int right, int bottom) {
        if (!a(this.f, left, top, right, bottom)) {
            this.f.set(left, top, right, bottom);
            this.H = true;
            g();
        }
    }

    final void b(int left, int top, int right, int bottom) {
        if (!a(this.g, left, top, right, bottom)) {
            this.g.set(left, top, right, bottom);
            this.H = true;
            g();
        }
    }

    private void g() {
        boolean z = this.g.width() > 0 && this.g.height() > 0 && this.f.width() > 0 && this.f.height() > 0;
        this.d = z;
    }

    final void c(int gravity) {
        if (this.i != gravity) {
            this.i = gravity;
            d();
        }
    }

    final void d(int gravity) {
        if (this.j != gravity) {
            this.j = gravity;
            d();
        }
    }

    final void e(int resId) {
        TypedArray a = this.c.getContext().obtainStyledAttributes(resId, j.TextAppearance);
        if (a.hasValue(j.TextAppearance_android_textColor)) {
            this.n = a.getColor(j.TextAppearance_android_textColor, this.n);
        }
        if (a.hasValue(j.TextAppearance_android_textSize)) {
            this.l = (float) a.getDimensionPixelSize(j.TextAppearance_android_textSize, (int) this.l);
        }
        this.O = a.getInt(j.TextAppearance_android_shadowColor, 0);
        this.M = a.getFloat(j.TextAppearance_android_shadowDx, 0.0f);
        this.N = a.getFloat(j.TextAppearance_android_shadowDy, 0.0f);
        this.L = a.getFloat(j.TextAppearance_android_shadowRadius, 0.0f);
        a.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.u = g(resId);
        }
        d();
    }

    final void f(int resId) {
        TypedArray a = this.c.getContext().obtainStyledAttributes(resId, j.TextAppearance);
        if (a.hasValue(j.TextAppearance_android_textColor)) {
            this.m = a.getColor(j.TextAppearance_android_textColor, this.m);
        }
        if (a.hasValue(j.TextAppearance_android_textSize)) {
            this.k = (float) a.getDimensionPixelSize(j.TextAppearance_android_textSize, (int) this.k);
        }
        this.S = a.getInt(j.TextAppearance_android_shadowColor, 0);
        this.Q = a.getFloat(j.TextAppearance_android_shadowDx, 0.0f);
        this.R = a.getFloat(j.TextAppearance_android_shadowDy, 0.0f);
        this.P = a.getFloat(j.TextAppearance_android_shadowRadius, 0.0f);
        a.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.v = g(resId);
        }
        d();
    }

    private Typeface g(int resId) {
        TypedArray a = this.c.getContext().obtainStyledAttributes(resId, new int[]{16843692});
        try {
            String family = a.getString(0);
            if (family != null) {
                Typeface create = Typeface.create(family, 0);
                return create;
            }
            a.recycle();
            return null;
        } finally {
            a.recycle();
        }
    }

    final void a(Typeface typeface) {
        if (this.u != typeface) {
            this.u = typeface;
            d();
        }
    }

    final void b(Typeface typeface) {
        if (this.v != typeface) {
            this.v = typeface;
            d();
        }
    }

    final void c(Typeface typeface) {
        this.v = typeface;
        this.u = typeface;
        d();
    }

    final Typeface a() {
        return this.u != null ? this.u : Typeface.DEFAULT;
    }

    final void b(float fraction) {
        if (fraction < 0.0f) {
            fraction = 0.0f;
        } else if (fraction > 1.0f) {
            fraction = 1.0f;
        }
        if (fraction != this.e) {
            this.e = fraction;
            h();
        }
    }

    final float b() {
        return this.e;
    }

    final float c() {
        return this.l;
    }

    private void h() {
        c(this.e);
    }

    private void c(float fraction) {
        this.h.left = a((float) this.f.left, (float) this.g.left, fraction, this.J);
        this.h.top = a(this.o, this.p, fraction, this.J);
        this.h.right = a((float) this.f.right, (float) this.g.right, fraction, this.J);
        this.h.bottom = a((float) this.f.bottom, (float) this.g.bottom, fraction, this.J);
        this.s = a(this.q, this.r, fraction, this.J);
        this.t = a(this.o, this.p, fraction, this.J);
        d(a(this.k, this.l, fraction, this.K));
        if (this.n != this.m) {
            this.I.setColor(a(this.m, this.n, fraction));
        } else {
            this.I.setColor(this.n);
        }
        this.I.setShadowLayer(a(this.P, this.L, fraction, null), a(this.Q, this.M, fraction, null), a(this.R, this.N, fraction, null), a(this.S, this.O, fraction));
        ViewCompat.d(this.c);
    }

    public final void a(Canvas canvas) {
        int saveCount = canvas.save();
        if (this.y != null && this.d) {
            boolean drawTexture;
            float ascent;
            float x = this.s;
            float y = this.t;
            if (!this.A || this.B == null) {
                drawTexture = false;
            } else {
                drawTexture = true;
            }
            this.I.setTextSize(this.G);
            if (drawTexture) {
                ascent = this.D * this.F;
            } else {
                this.I.ascent();
                ascent = 0.0f;
                this.I.descent();
            }
            if (drawTexture) {
                y += ascent;
            }
            if (this.F != 1.0f) {
                canvas.scale(this.F, this.F, x, y);
            }
            if (drawTexture) {
                canvas.drawBitmap(this.B, x, y, this.C);
            } else {
                canvas.drawText(this.y, 0, this.y.length(), x, y, this.I);
            }
        }
        canvas.restoreToCount(saveCount);
    }

    private void d(float textSize) {
        e(textSize);
        boolean z = a && this.F != 1.0f;
        this.A = z;
        if (this.A && this.B == null && !this.f.isEmpty() && !TextUtils.isEmpty(this.y)) {
            c(0.0f);
            this.D = this.I.ascent();
            this.E = this.I.descent();
            int round = Math.round(this.I.measureText(this.y, 0, this.y.length()));
            int round2 = Math.round(this.E - this.D);
            if (round > 0 && round2 > 0) {
                this.B = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
                new Canvas(this.B).drawText(this.y, 0, this.y.length(), 0.0f, ((float) round2) - this.I.descent(), this.I);
                if (this.C == null) {
                    this.C = new Paint(3);
                }
            }
        }
        ViewCompat.d(this.c);
    }

    private void e(float textSize) {
        boolean z = true;
        if (this.x != null) {
            float availableWidth;
            float newTextSize;
            boolean updateDrawText = false;
            if (a(textSize, this.l)) {
                availableWidth = (float) this.g.width();
                newTextSize = this.l;
                this.F = 1.0f;
                if (this.w != this.u) {
                    this.w = this.u;
                    updateDrawText = true;
                }
            } else {
                availableWidth = (float) this.f.width();
                newTextSize = this.k;
                if (this.w != this.v) {
                    this.w = this.v;
                    updateDrawText = true;
                }
                if (a(textSize, this.k)) {
                    this.F = 1.0f;
                } else {
                    this.F = textSize / this.k;
                }
            }
            if (availableWidth > 0.0f) {
                if (this.G != newTextSize || this.H || updateDrawText) {
                    updateDrawText = true;
                } else {
                    updateDrawText = false;
                }
                this.G = newTextSize;
                this.H = false;
            }
            if (this.y == null || updateDrawText) {
                this.I.setTextSize(this.G);
                this.I.setTypeface(this.w);
                CharSequence title = TextUtils.ellipsize(this.x, this.I, availableWidth, TruncateAt.END);
                if (!TextUtils.equals(title, this.y)) {
                    e eVar;
                    this.y = title;
                    CharSequence charSequence = this.y;
                    if (ViewCompat.f(this.c) != 1) {
                        z = false;
                    }
                    if (z) {
                        eVar = f.d;
                    } else {
                        eVar = f.c;
                    }
                    this.z = eVar.a(charSequence, charSequence.length());
                }
            }
        }
    }

    public final void d() {
        int i = 1;
        float f = 0.0f;
        if (this.c.getHeight() > 0 && this.c.getWidth() > 0) {
            int i2;
            float f2 = this.G;
            e(this.l);
            float measureText = this.y != null ? this.I.measureText(this.y, 0, this.y.length()) : 0.0f;
            int i3 = this.j;
            if (this.z) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            i2 = android.support.v4.view.d.a(i3, i2);
            switch (i2 & 112) {
                case 48:
                    this.p = ((float) this.g.top) - this.I.ascent();
                    break;
                case 80:
                    this.p = (float) this.g.bottom;
                    break;
                default:
                    this.p = (((this.I.descent() - this.I.ascent()) / 2.0f) - this.I.descent()) + ((float) this.g.centerY());
                    break;
            }
            switch (i2 & 7) {
                case 1:
                    this.r = ((float) this.g.centerX()) - (measureText / 2.0f);
                    break;
                case 5:
                    this.r = ((float) this.g.right) - measureText;
                    break;
                default:
                    this.r = (float) this.g.left;
                    break;
            }
            e(this.k);
            if (this.y != null) {
                f = this.I.measureText(this.y, 0, this.y.length());
            }
            int i4 = this.i;
            if (!this.z) {
                i = 0;
            }
            i4 = android.support.v4.view.d.a(i4, i);
            switch (i4 & 112) {
                case 48:
                    this.o = ((float) this.f.top) - this.I.ascent();
                    break;
                case 80:
                    this.o = (float) this.f.bottom;
                    break;
                default:
                    this.o = (((this.I.descent() - this.I.ascent()) / 2.0f) - this.I.descent()) + ((float) this.f.centerY());
                    break;
            }
            switch (i4 & 7) {
                case 1:
                    this.q = ((float) this.f.centerX()) - (f / 2.0f);
                    break;
                case 5:
                    this.q = ((float) this.f.right) - f;
                    break;
                default:
                    this.q = (float) this.f.left;
                    break;
            }
            i();
            d(f2);
            h();
        }
    }

    final void a(CharSequence text) {
        if (text == null || !text.equals(this.x)) {
            this.x = text;
            this.y = null;
            i();
            d();
        }
    }

    final CharSequence e() {
        return this.x;
    }

    private void i() {
        if (this.B != null) {
            this.B.recycle();
            this.B = null;
        }
    }

    private static boolean a(float value, float targetValue) {
        return Math.abs(value - targetValue) < 0.001f;
    }

    final int f() {
        return this.n;
    }

    private static int a(int color1, int color2, float ratio) {
        float inverseRatio = 1.0f - ratio;
        return Color.argb((int) ((((float) Color.alpha(color1)) * inverseRatio) + (((float) Color.alpha(color2)) * ratio)), (int) ((((float) Color.red(color1)) * inverseRatio) + (((float) Color.red(color2)) * ratio)), (int) ((((float) Color.green(color1)) * inverseRatio) + (((float) Color.green(color2)) * ratio)), (int) ((((float) Color.blue(color1)) * inverseRatio) + (((float) Color.blue(color2)) * ratio)));
    }

    private static float a(float startValue, float endValue, float fraction, Interpolator interpolator) {
        if (interpolator != null) {
            fraction = interpolator.getInterpolation(fraction);
        }
        return a.a(startValue, endValue, fraction);
    }

    private static boolean a(Rect r, int left, int top, int right, int bottom) {
        return r.left == left && r.top == top && r.right == right && r.bottom == bottom;
    }
}
