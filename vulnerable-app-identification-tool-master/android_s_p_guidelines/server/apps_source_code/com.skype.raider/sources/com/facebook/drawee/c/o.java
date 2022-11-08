package com.facebook.drawee.c;

import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;
import com.facebook.imagepipeline.l.b;

public final class o extends n {
    public o(NinePatchDrawable ninePatchDrawable) {
        super(ninePatchDrawable);
    }

    public final void draw(Canvas canvas) {
        b.a();
        if (b()) {
            c();
            d();
            canvas.clipPath(this.d);
            super.draw(canvas);
            b.a();
            return;
        }
        super.draw(canvas);
        b.a();
    }
}
