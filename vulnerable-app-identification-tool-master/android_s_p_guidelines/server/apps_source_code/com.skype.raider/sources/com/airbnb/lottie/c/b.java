package com.airbnb.lottie.c;

import android.support.annotation.ColorInt;

public final class b {
    public final String a;
    public final String b;
    public final double c;
    final int d;
    public final int e;
    final double f;
    public final double g;
    @ColorInt
    public final int h;
    @ColorInt
    public final int i;
    public final int j;
    public final boolean k;

    public b(String text, String fontName, double size, int justification, int tracking, double lineHeight, double baselineShift, @ColorInt int color, @ColorInt int strokeColor, int strokeWidth, boolean strokeOverFill) {
        this.a = text;
        this.b = fontName;
        this.c = size;
        this.d = justification;
        this.e = tracking;
        this.f = lineHeight;
        this.g = baselineShift;
        this.h = color;
        this.i = strokeColor;
        this.j = strokeWidth;
        this.k = strokeOverFill;
    }

    public final int hashCode() {
        int result = (((((int) (((double) (((this.a.hashCode() * 31) + this.b.hashCode()) * 31)) + this.c)) * 31) + this.d) * 31) + this.e;
        long temp = Double.doubleToLongBits(this.f);
        return (((result * 31) + ((int) ((temp >>> 32) ^ temp))) * 31) + this.h;
    }
}
