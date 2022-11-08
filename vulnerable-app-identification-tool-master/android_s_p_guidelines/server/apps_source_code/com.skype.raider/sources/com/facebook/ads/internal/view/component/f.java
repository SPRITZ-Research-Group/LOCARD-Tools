package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.u;

public class f extends RelativeLayout {
    private static final int a = ((int) (4.0f * u.b));
    private final Path b = new Path();
    private final RectF c = new RectF();

    public f(Context context) {
        super(context);
        u.a((View) this, 0);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    protected void onDraw(Canvas canvas) {
        this.c.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.b.reset();
        this.b.addRoundRect(this.c, (float) a, (float) a, Direction.CW);
        canvas.clipPath(this.b);
        super.onDraw(canvas);
    }
}
