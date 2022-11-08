package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region.Op;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public class a extends e {
    @Nullable
    protected RectF a;

    @ReactProp(name = "clipping")
    public void setClipping(@Nullable al clippingDims) {
        float[] clippingData = f.a(clippingDims);
        if (clippingData == null) {
            return;
        }
        if (clippingData.length != 4) {
            throw new n("Clipping should be array of length 4 (e.g. [x, y, width, height])");
        }
        this.a = new RectF(clippingData[0], clippingData[1], clippingData[0] + clippingData[2], clippingData[1] + clippingData[3]);
        i();
    }

    public final boolean a() {
        return true;
    }

    public final void a(Canvas canvas, Paint paint, float opacity) {
        opacity *= this.b;
        if (opacity > 0.01f) {
            a(canvas);
            if (this.a != null) {
                Canvas canvas2 = canvas;
                canvas2.clipRect(this.c * this.a.left, this.c * this.a.top, this.c * this.a.right, this.c * this.a.bottom, Op.REPLACE);
            }
            for (int i = 0; i < y(); i++) {
                e child = (e) c(i);
                child.a(canvas, paint, opacity);
                child.v();
            }
            canvas.restore();
        }
    }
}
