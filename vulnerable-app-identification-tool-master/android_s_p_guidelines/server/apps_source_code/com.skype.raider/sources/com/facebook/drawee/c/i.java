package com.facebook.drawee.c;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;

public final class i extends g {
    @VisibleForTesting
    final Matrix a;
    private int c;
    private int d;
    private final Matrix e = new Matrix();
    private final RectF f = new RectF();

    public i(Drawable drawable, int rotationAngle, int exifOrientation) {
        boolean z = true;
        super(drawable);
        h.a(rotationAngle % 90 == 0);
        if (exifOrientation < 0 || exifOrientation > 8) {
            z = false;
        }
        h.a(z);
        this.a = new Matrix();
        this.c = rotationAngle;
        this.d = exifOrientation;
    }

    public final void draw(Canvas canvas) {
        if (this.c > 0 || !(this.d == 0 || this.d == 1)) {
            int saveCount = canvas.save();
            canvas.concat(this.a);
            super.draw(canvas);
            canvas.restoreToCount(saveCount);
            return;
        }
        super.draw(canvas);
    }

    public final int getIntrinsicWidth() {
        if (this.d == 5 || this.d == 7 || this.c % 180 != 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    public final int getIntrinsicHeight() {
        if (this.d == 5 || this.d == 7 || this.c % 180 != 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    protected final void onBoundsChange(Rect bounds) {
        Drawable underlyingDrawable = getCurrent();
        if (this.c > 0 || !(this.d == 0 || this.d == 1)) {
            switch (this.d) {
                case 2:
                    this.a.setScale(-1.0f, 1.0f);
                    break;
                case 4:
                    this.a.setScale(1.0f, -1.0f);
                    break;
                case 5:
                    this.a.setRotate(270.0f, (float) bounds.centerX(), (float) bounds.centerY());
                    this.a.postScale(1.0f, -1.0f);
                    break;
                case 7:
                    this.a.setRotate(270.0f, (float) bounds.centerX(), (float) bounds.centerY());
                    this.a.postScale(-1.0f, 1.0f);
                    break;
                default:
                    this.a.setRotate((float) this.c, (float) bounds.centerX(), (float) bounds.centerY());
                    break;
            }
            this.e.reset();
            this.a.invert(this.e);
            this.f.set(bounds);
            this.e.mapRect(this.f);
            underlyingDrawable.setBounds((int) this.f.left, (int) this.f.top, (int) this.f.right, (int) this.f.bottom);
            return;
        }
        underlyingDrawable.setBounds(bounds);
    }

    public final void a(Matrix transform) {
        b(transform);
        if (!this.a.isIdentity()) {
            transform.preConcat(this.a);
        }
    }
}
