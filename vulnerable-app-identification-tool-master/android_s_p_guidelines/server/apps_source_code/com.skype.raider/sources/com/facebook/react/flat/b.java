package com.facebook.react.flat;

import android.graphics.Canvas;

abstract class b extends h implements Cloneable {
    protected boolean a_;
    private float c;
    private float d;
    private float e;
    private float f;
    private boolean g;
    private float h;
    private float i;
    private float j;
    private float k;

    protected abstract void c(Canvas canvas);

    b() {
    }

    protected void b(Canvas canvas) {
        canvas.clipRect(this.h, this.i, this.j, this.k);
    }

    public void a(t parent, Canvas canvas) {
        if (this.a_) {
            Object obj;
            if (this.c < this.h || this.d < this.i || this.e > this.j || this.f > this.k) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                canvas.save(2);
                b(canvas);
                c(canvas);
                canvas.restore();
                return;
            }
        }
        c(canvas);
    }

    public final void b(t parent, Canvas canvas) {
        c(parent, canvas);
    }

    protected void c(t parent, Canvas canvas) {
        getClass().getSimpleName().substring(4);
        parent.a(canvas, -16711681, this.c, this.d, this.e, this.f);
    }

    public final b f() {
        try {
            b copy = (b) super.clone();
            copy.g = false;
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean g() {
        return this.g;
    }

    public final float h() {
        return this.c;
    }

    public final float i() {
        return this.d;
    }

    public final float j() {
        return this.e;
    }

    public final float k() {
        return this.f;
    }
}
