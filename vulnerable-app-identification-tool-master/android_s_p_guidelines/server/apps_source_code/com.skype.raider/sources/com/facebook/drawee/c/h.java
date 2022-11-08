package com.facebook.drawee.c;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public final class h extends g {
    private Matrix a;
    private Matrix c;
    private int d;
    private int e;

    public final Drawable b(Drawable newDelegate) {
        Drawable previousDelegate = super.b(newDelegate);
        b();
        return previousDelegate;
    }

    protected final void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        b();
    }

    private void b() {
        Drawable underlyingDrawable = getCurrent();
        Rect bounds = getBounds();
        int underlyingWidth = underlyingDrawable.getIntrinsicWidth();
        this.d = underlyingWidth;
        int underlyingHeight = underlyingDrawable.getIntrinsicHeight();
        this.e = underlyingHeight;
        if (underlyingWidth <= 0 || underlyingHeight <= 0) {
            underlyingDrawable.setBounds(bounds);
            this.c = null;
            return;
        }
        underlyingDrawable.setBounds(0, 0, underlyingWidth, underlyingHeight);
        this.c = this.a;
    }

    public final void a(Matrix transform) {
        super.a(transform);
        if (this.c != null) {
            transform.preConcat(this.c);
        }
    }

    public final void draw(Canvas canvas) {
        if (!(this.d == getCurrent().getIntrinsicWidth() && this.e == getCurrent().getIntrinsicHeight())) {
            b();
        }
        if (this.c != null) {
            int saveCount = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.c);
            super.draw(canvas);
            canvas.restoreToCount(saveCount);
            return;
        }
        super.draw(canvas);
    }
}
