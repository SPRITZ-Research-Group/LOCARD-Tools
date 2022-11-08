package com.facebook.react.flat;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

final class ag extends CharacterStyle {
    static final ag a = new ag(0.0f, 0.0f, 0.0f, 0, true);
    private float b;
    private float c;
    private float d;
    private int e;
    private boolean f;

    private ag(float dx, float dy, float radius, int color, boolean frozen) {
        this.b = dx;
        this.c = dy;
        this.d = radius;
        this.e = color;
        this.f = frozen;
    }

    public final boolean a(float dx, float dy) {
        return this.b == dx && this.c == dy;
    }

    public final void b(float dx, float dy) {
        this.b = dx;
        this.c = dy;
    }

    public final float a() {
        return this.d;
    }

    public final void a(float radius) {
        this.d = radius;
    }

    public final int b() {
        return this.e;
    }

    public final void a(int color) {
        this.e = color;
    }

    final ag c() {
        return new ag(this.b, this.c, this.d, this.e, false);
    }

    final boolean d() {
        return this.f;
    }

    final void e() {
        this.f = true;
    }

    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setShadowLayer(this.d, this.b, this.c, this.e);
    }
}
