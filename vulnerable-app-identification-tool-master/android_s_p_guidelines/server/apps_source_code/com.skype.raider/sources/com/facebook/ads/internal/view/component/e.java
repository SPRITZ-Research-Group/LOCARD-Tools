package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.widget.ImageView;
import com.facebook.ads.internal.q.a.u;

public class e extends ImageView {
    private static final int a = ((int) (8.0f * u.b));
    private final Path b = new Path();
    private final RectF c = new RectF();
    private int d = a;
    private boolean e = false;

    public e(Context context) {
        super(context);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    protected void onDraw(Canvas canvas) {
        this.c.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.b.reset();
        float min = this.e ? (float) (Math.min(getWidth(), getHeight()) / 2) : (float) this.d;
        this.b.addRoundRect(this.c, min, min, Direction.CW);
        canvas.clipPath(this.b);
        super.onDraw(canvas);
    }

    public void setFullCircleCorners(boolean z) {
        this.e = z;
    }

    public void setRadius(int i) {
        this.d = (int) (((float) i) * u.b);
    }
}
