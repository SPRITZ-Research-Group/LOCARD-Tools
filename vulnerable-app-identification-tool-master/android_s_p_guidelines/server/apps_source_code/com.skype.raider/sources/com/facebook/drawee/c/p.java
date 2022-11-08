package com.facebook.drawee.c;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.g;
import com.facebook.common.internal.h;
import com.facebook.drawee.c.q.b;
import com.facebook.drawee.c.q.l;

public final class p extends g {
    @VisibleForTesting
    b a;
    @VisibleForTesting
    Object c;
    @VisibleForTesting
    PointF d = null;
    @VisibleForTesting
    int e = 0;
    @VisibleForTesting
    int f = 0;
    @VisibleForTesting
    Matrix g;
    private Matrix h = new Matrix();

    public p(Drawable drawable, b scaleType) {
        super((Drawable) h.a((Object) drawable));
        this.a = scaleType;
    }

    public final Drawable b(Drawable newDelegate) {
        Drawable previousDelegate = super.b(newDelegate);
        d();
        return previousDelegate;
    }

    public final b b() {
        return this.a;
    }

    public final void a(b scaleType) {
        if (!g.a(this.a, scaleType)) {
            this.a = scaleType;
            this.c = null;
            d();
            invalidateSelf();
        }
    }

    public final void a(PointF focusPoint) {
        if (!g.a(this.d, focusPoint)) {
            if (this.d == null) {
                this.d = new PointF();
            }
            this.d.set(focusPoint);
            d();
            invalidateSelf();
        }
    }

    public final void draw(Canvas canvas) {
        c();
        if (this.g != null) {
            int saveCount = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.g);
            super.draw(canvas);
            canvas.restoreToCount(saveCount);
            return;
        }
        super.draw(canvas);
    }

    protected final void onBoundsChange(Rect bounds) {
        d();
    }

    private void c() {
        Object obj = null;
        boolean scaleTypeChanged = false;
        if (this.a instanceof l) {
            Object state = ((l) this.a).a();
            if (state == null || !state.equals(this.c)) {
                scaleTypeChanged = true;
            } else {
                scaleTypeChanged = false;
            }
            this.c = state;
        }
        if (!(this.e == getCurrent().getIntrinsicWidth() && this.f == getCurrent().getIntrinsicHeight())) {
            obj = 1;
        }
        if (obj != null || scaleTypeChanged) {
            d();
        }
    }

    @VisibleForTesting
    private void d() {
        float f = 0.5f;
        Drawable underlyingDrawable = getCurrent();
        Rect bounds = getBounds();
        int viewWidth = bounds.width();
        int viewHeight = bounds.height();
        int underlyingWidth = underlyingDrawable.getIntrinsicWidth();
        this.e = underlyingWidth;
        int underlyingHeight = underlyingDrawable.getIntrinsicHeight();
        this.f = underlyingHeight;
        if (underlyingWidth <= 0 || underlyingHeight <= 0) {
            underlyingDrawable.setBounds(bounds);
            this.g = null;
        } else if (underlyingWidth == viewWidth && underlyingHeight == viewHeight) {
            underlyingDrawable.setBounds(bounds);
            this.g = null;
        } else if (this.a == b.a) {
            underlyingDrawable.setBounds(bounds);
            this.g = null;
        } else {
            underlyingDrawable.setBounds(0, 0, underlyingWidth, underlyingHeight);
            b bVar = this.a;
            Matrix matrix = this.h;
            float f2 = this.d != null ? this.d.x : 0.5f;
            if (this.d != null) {
                f = this.d.y;
            }
            bVar.a(matrix, bounds, underlyingWidth, underlyingHeight, f2, f);
            this.g = this.h;
        }
    }

    public final void a(Matrix transform) {
        b(transform);
        c();
        if (this.g != null) {
            transform.preConcat(this.g);
        }
    }
}
