package com.facebook.react.flat;

import android.graphics.Canvas;
import android.text.Layout;
import com.facebook.fbui.textlayoutbuilder.b.a;

final class l extends b {
    private Layout c;
    private float d;
    private float e;

    l(Layout layout) {
        a(layout);
    }

    public final void a(Layout layout) {
        this.c = layout;
        this.d = (float) layout.getWidth();
        this.e = (float) a.a(layout);
    }

    public final float a() {
        return this.d;
    }

    public final float b() {
        return this.e;
    }

    protected final void c(Canvas canvas) {
        float left = h();
        float top = i();
        canvas.translate(left, top);
        this.c.draw(canvas);
        canvas.translate(-left, -top);
    }
}
