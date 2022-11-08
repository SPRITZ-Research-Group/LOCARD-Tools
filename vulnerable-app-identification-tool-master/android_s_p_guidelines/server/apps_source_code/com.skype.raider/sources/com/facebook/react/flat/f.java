package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Paint;

final class f extends b {
    private static final Paint c = new Paint();
    private final int d;

    f(int backgroundColor) {
        this.d = backgroundColor;
    }

    public final void c(Canvas canvas) {
        c.setColor(this.d);
        canvas.drawRect(h(), i(), j(), k(), c);
    }
}
