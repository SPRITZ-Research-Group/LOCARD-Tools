package com.facebook.drawee.c;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;

public final class b extends g implements Runnable {
    @VisibleForTesting
    float a;
    private int c;
    private boolean d;
    private boolean e;

    public b(Drawable drawable, int interval) {
        this(drawable, interval, (byte) 0);
    }

    private b(Drawable drawable, int interval, byte b) {
        super((Drawable) h.a((Object) drawable));
        this.a = 0.0f;
        this.e = false;
        this.c = interval;
        this.d = true;
    }

    public final void draw(Canvas canvas) {
        int saveCount = canvas.save();
        Rect bounds = getBounds();
        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;
        float angle = this.a;
        if (!this.d) {
            angle = 360.0f - this.a;
        }
        canvas.rotate(angle, (float) (bounds.left + (width / 2)), (float) (bounds.top + (height / 2)));
        super.draw(canvas);
        canvas.restoreToCount(saveCount);
        if (!this.e) {
            this.e = true;
            scheduleSelf(this, SystemClock.uptimeMillis() + 20);
        }
    }

    public final void run() {
        this.e = false;
        this.a += (float) ((int) ((20.0f / ((float) this.c)) * 360.0f));
        invalidateSelf();
    }
}
