package com.facebook.react.views.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.text.style.ReplacementSpan;

public class l extends ReplacementSpan {
    private int a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;

    public l(int reactTag, float width, float height, float marginStart, float marginTop, float marginEnd, float marginBottom) {
        this.a = reactTag;
        this.b = width;
        this.c = height;
        this.d = marginStart;
        this.e = marginTop;
        this.f = marginEnd;
        this.g = marginBottom;
    }

    public final int a() {
        return this.a;
    }

    public final int b() {
        return (int) this.b;
    }

    public final int c() {
        return (int) this.c;
    }

    public final int d() {
        return (int) ((this.b + this.d) + this.f);
    }

    public final int e() {
        return (int) this.d;
    }

    public final int f() {
        return (int) this.f;
    }

    public final int g() {
        return (int) this.g;
    }

    public int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        int width = d();
        if (fm != null) {
            int height = (int) ((this.c + this.e) + this.g);
            int bottomMargin = (int) this.g;
            if (bottomMargin < 0) {
                fm.ascent = -height;
                fm.descent = -bottomMargin;
            } else {
                fm.ascent = -height;
                fm.descent = 0;
            }
            if (fm.top > fm.ascent) {
                fm.top = fm.ascent;
            }
            if (fm.bottom < fm.descent) {
                fm.bottom = fm.descent;
            }
        }
        return width;
    }

    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
    }
}
