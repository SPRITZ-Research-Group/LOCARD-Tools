package com.facebook.react.flat;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import javax.annotation.Nullable;

final class u extends MetricAffectingSpan {
    static final u a = new u(-1.6777216E7d, 0, -1, -1, -1, false, false, null, true);
    private double b;
    private int c;
    private boolean d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    @Nullable
    private String i;
    private boolean j;

    u() {
    }

    private u(double textColor, int backgroundColor, int fontSize, int fontStyle, int fontWeight, boolean hasUnderline, boolean hasStrikeThrough, @Nullable String fontFamily, boolean frozen) {
        this.b = textColor;
        this.c = backgroundColor;
        this.f = fontSize;
        this.g = fontStyle;
        this.h = fontWeight;
        this.d = hasUnderline;
        this.e = hasStrikeThrough;
        this.i = fontFamily;
        this.j = frozen;
    }

    final u a() {
        return new u(this.b, this.c, this.f, this.g, this.h, this.d, this.e, this.i, false);
    }

    final boolean b() {
        return this.j;
    }

    final void c() {
        this.j = true;
    }

    final double d() {
        return this.b;
    }

    final void a(double textColor) {
        this.b = textColor;
    }

    final int e() {
        return this.c;
    }

    final void a(int backgroundColor) {
        this.c = backgroundColor;
    }

    final int f() {
        return this.f;
    }

    final void b(int fontSize) {
        this.f = fontSize;
    }

    final int g() {
        return this.g;
    }

    final void c(int fontStyle) {
        this.g = fontStyle;
    }

    final int h() {
        return this.h;
    }

    final void d(int fontWeight) {
        this.h = fontWeight;
    }

    @Nullable
    final String i() {
        return this.i;
    }

    final void a(@Nullable String fontFamily) {
        this.i = fontFamily;
    }

    final boolean j() {
        return this.d;
    }

    final void a(boolean hasUnderline) {
        this.d = hasUnderline;
    }

    final boolean k() {
        return this.e;
    }

    final void b(boolean hasStrikeThrough) {
        this.e = hasStrikeThrough;
    }

    public final void updateDrawState(TextPaint ds) {
        if (!Double.isNaN(this.b)) {
            ds.setColor((int) this.b);
        }
        ds.bgColor = this.c;
        ds.setUnderlineText(this.d);
        ds.setStrikeThruText(this.e);
        updateMeasureState(ds);
    }

    public final void updateMeasureState(TextPaint ds) {
        int i;
        int i2;
        if (this.f != -1) {
            ds.setTextSize((float) this.f);
        }
        Typeface typeface = ds.getTypeface();
        if (typeface == null) {
            i = 0;
        } else {
            i = typeface.getStyle();
        }
        if (this.g != -1) {
            i2 = (i & -3) | this.g;
        } else {
            i2 = i;
        }
        if (this.h != -1) {
            i2 = (i2 & -2) | this.h;
        }
        if (i != i2 || this.i != null) {
            Typeface a;
            if (this.i != null) {
                a = ai.a(this.i, i2);
            } else {
                a = ai.a(typeface, i2);
            }
            ds.setTypeface(a);
        }
    }
}
