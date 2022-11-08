package com.facebook.react.views.text;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

public final class j extends CharacterStyle {
    private final float a;
    private final float b;
    private final float c;
    private final int d;

    public j(float dx, float dy, float radius, int color) {
        this.a = dx;
        this.b = dy;
        this.c = radius;
        this.d = color;
    }

    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setShadowLayer(this.c, this.a, this.b, this.d);
    }
}
