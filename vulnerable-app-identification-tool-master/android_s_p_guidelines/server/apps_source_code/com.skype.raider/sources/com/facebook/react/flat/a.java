package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathEffect;
import android.graphics.RectF;
import javax.annotation.Nullable;

abstract class a extends b {
    private static final Paint c = new Paint(1);
    private static final RectF d = new RectF();
    private int e;
    private int f = -16777216;
    private float g;
    private float h;
    @Nullable
    private Path i;

    a() {
    }

    static {
        c.setStyle(Style.STROKE);
    }

    public final void a(float borderWidth) {
        this.g = borderWidth;
        c(1);
    }

    public final float a() {
        return this.g;
    }

    public final void b(float borderRadius) {
        this.h = borderRadius;
        c(1);
    }

    public final float b() {
        return this.h;
    }

    public final void a(int borderColor) {
        this.f = borderColor;
    }

    public final int c() {
        return this.f;
    }

    protected final void a(Canvas canvas) {
        if (this.g >= 0.5f && this.f != 0) {
            c.setColor(this.f);
            c.setStrokeWidth(this.g);
            c.setPathEffect(d());
            canvas.drawPath(e(), c);
        }
    }

    @Nullable
    protected PathEffect d() {
        return null;
    }

    protected final boolean b(int mask) {
        return (this.e & mask) == mask;
    }

    protected final void c(int mask) {
        this.e |= mask;
    }

    protected final void d(int mask) {
        this.e &= mask ^ -1;
    }

    protected final Path e() {
        if (b(1)) {
            if (this.i == null) {
                this.i = new Path();
            }
            Path path = this.i;
            float f = this.g * 0.5f;
            path.reset();
            d.set(h() + f, i() + f, j() - f, k() - f);
            path.addRoundRect(d, this.h, this.h, Direction.CW);
            d(1);
        }
        return this.i;
    }
}
