package com.facebook.react.views.slider;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import javax.annotation.Nullable;

public class ReactSlider extends SeekBar {
    private static int a = 128;
    private double b = 0.0d;
    private double c = 0.0d;
    private double d = 0.0d;
    private double e = 0.0d;
    private double f = 0.0d;

    public ReactSlider(Context context, @Nullable AttributeSet attrs, int style) {
        super(context, attrs, style);
    }

    final void a(double max) {
        this.c = max;
        a();
    }

    final void b(double min) {
        this.b = min;
        a();
    }

    final void c(double value) {
        this.d = value;
        b();
    }

    final void d(double step) {
        this.e = step;
        a();
    }

    public final double a(int seekBarProgress) {
        if (seekBarProgress == getMax()) {
            return this.c;
        }
        return (((double) seekBarProgress) * d()) + this.b;
    }

    private void a() {
        if (this.e == 0.0d) {
            this.f = (this.c - this.b) / ((double) a);
        }
        setMax(c());
        b();
    }

    private void b() {
        setProgress((int) Math.round(((this.d - this.b) / (this.c - this.b)) * ((double) c())));
    }

    private int c() {
        return (int) Math.ceil((this.c - this.b) / d());
    }

    private double d() {
        return this.e > 0.0d ? this.e : this.f;
    }
}
