package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.b;
import com.facebook.react.uimanager.w;
import javax.annotation.Nullable;

public abstract class e extends w {
    private static final float[] a = new float[9];
    private static final float[] d = new float[9];
    protected float b = 1.0f;
    protected final float c = b.a().density;
    @Nullable
    private Matrix f = new Matrix();

    public abstract void a(Canvas canvas, Paint paint, float f);

    public boolean a() {
        return true;
    }

    protected final void a(Canvas canvas) {
        canvas.save();
        if (this.f != null) {
            canvas.concat(this.f);
        }
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(float opacity) {
        this.b = opacity;
        i();
    }

    @ReactProp(name = "transform")
    public void setTransform(@Nullable al transformArray) {
        if (transformArray != null) {
            int matrixSize = f.a(transformArray, a);
            if (matrixSize == 6) {
                d[0] = a[0];
                d[1] = a[2];
                d[2] = a[4] * this.c;
                d[3] = a[1];
                d[4] = a[3];
                d[5] = a[5] * this.c;
                d[6] = 0.0f;
                d[7] = 0.0f;
                d[8] = 1.0f;
                if (this.f == null) {
                    this.f = new Matrix();
                }
                this.f.setValues(d);
            } else if (matrixSize != -1) {
                throw new n("Transform matrices must be of size 6");
            }
        }
        this.f = null;
        i();
    }
}
