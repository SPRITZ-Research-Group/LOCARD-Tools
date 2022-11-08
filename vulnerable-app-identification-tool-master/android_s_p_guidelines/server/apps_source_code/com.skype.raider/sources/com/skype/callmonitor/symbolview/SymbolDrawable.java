package com.skype.callmonitor.symbolview;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class SymbolDrawable extends Drawable {
    public static final Options a = new Options(1.0f);
    public static final Options b = new Options(1.05f);
    private Paint c;
    private char[] d;
    private int e;
    private int f;
    private float g;
    private float h;

    public static class Options {
        float a = 1.0f;
        float b = 1.0f;

        public Options(float alignMultiplier) {
            this.a = alignMultiplier;
            this.b = 1.0f;
        }
    }

    public int getIntrinsicHeight() {
        return this.e;
    }

    public int getIntrinsicWidth() {
        return this.f;
    }

    public void draw(Canvas canvas) {
        if (isVisible()) {
            int count = canvas.save();
            Rect bounds = getBounds();
            canvas.translate((float) bounds.left, (float) bounds.top);
            canvas.drawText(this.d, 0, this.d.length, this.g, this.h, this.c);
            canvas.restoreToCount(count);
        }
    }

    public void setAlpha(int alpha) {
        this.c.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.c.setColorFilter(cf);
    }

    public int getOpacity() {
        return -3;
    }
}
