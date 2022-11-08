package com.facebook.widget.text.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class BetterImageSpan extends ReplacementSpan {
    private int a;
    private int b;
    private Rect c;
    private final int d;
    private final FontMetricsInt e;
    private final Drawable f;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BetterImageSpanAlignment {
    }

    public final void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        paint.getFontMetricsInt(this.e);
        int iconTop = y + a(this.e);
        canvas.translate(x, (float) iconTop);
        this.f.draw(canvas);
        canvas.translate(-x, (float) (-iconTop));
    }

    private int a(FontMetricsInt fm) {
        switch (this.d) {
            case 0:
                return fm.descent - this.b;
            case 2:
                return fm.ascent + (((fm.descent - fm.ascent) - this.b) / 2);
            default:
                return -this.b;
        }
    }

    public final int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fontMetrics) {
        this.c = this.f.getBounds();
        this.a = this.c.width();
        this.b = this.c.height();
        if (fontMetrics == null) {
            return this.a;
        }
        int offsetAbove = a(fontMetrics);
        int offsetBelow = this.b + offsetAbove;
        if (offsetAbove < fontMetrics.ascent) {
            fontMetrics.ascent = offsetAbove;
        }
        if (offsetAbove < fontMetrics.top) {
            fontMetrics.top = offsetAbove;
        }
        if (offsetBelow > fontMetrics.descent) {
            fontMetrics.descent = offsetBelow;
        }
        if (offsetBelow > fontMetrics.bottom) {
            fontMetrics.bottom = offsetBelow;
        }
        return this.a;
    }
}
