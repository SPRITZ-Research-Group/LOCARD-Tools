package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import javax.annotation.Nullable;

final class m extends b {
    public static final m[] c = new m[0];
    final int d = 0;
    private final RectF e = new RectF();
    private float f;
    @Nullable
    private Path g;

    public final void a(t parent, Canvas canvas) {
        if (this.a_ || this.f > 0.5f) {
            canvas.save(2);
            b(canvas);
            parent.b(canvas);
            canvas.restore();
            return;
        }
        parent.b(canvas);
    }

    protected final void b(Canvas canvas) {
        if (this.f > 0.5f) {
            canvas.clipPath(this.g);
        } else {
            super.b(canvas);
        }
    }

    protected final void c(Canvas canvas) {
    }

    protected final void c(t parent, Canvas canvas) {
        parent.a(canvas);
    }
}
