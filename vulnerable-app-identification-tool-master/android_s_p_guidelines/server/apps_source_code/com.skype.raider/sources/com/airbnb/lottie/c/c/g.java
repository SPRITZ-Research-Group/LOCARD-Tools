package com.airbnb.lottie.c.c;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a;
import com.airbnb.lottie.a.b.p;
import com.airbnb.lottie.f.c;

public final class g extends a {
    private final RectF e = new RectF();
    private final Paint f = new Paint();
    private final float[] g = new float[8];
    private final Path h = new Path();
    private final d i;
    @Nullable
    private a<ColorFilter, ColorFilter> j;

    g(LottieDrawable lottieDrawable, d layerModel) {
        super(lottieDrawable, layerModel);
        this.i = layerModel;
        this.f.setAlpha(0);
        this.f.setStyle(Style.FILL);
        this.f.setColor(layerModel.p());
    }

    public final void b(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        int backgroundAlpha = Color.alpha(this.i.p());
        if (backgroundAlpha != 0) {
            int alpha = (int) ((((((float) ((Integer) this.d.a().e()).intValue()) * (((float) backgroundAlpha) / 255.0f)) / 100.0f) * (((float) parentAlpha) / 255.0f)) * 255.0f);
            this.f.setAlpha(alpha);
            if (this.j != null) {
                this.f.setColorFilter((ColorFilter) this.j.e());
            }
            if (alpha > 0) {
                this.g[0] = 0.0f;
                this.g[1] = 0.0f;
                this.g[2] = (float) this.i.r();
                this.g[3] = 0.0f;
                this.g[4] = (float) this.i.r();
                this.g[5] = (float) this.i.q();
                this.g[6] = 0.0f;
                this.g[7] = (float) this.i.q();
                parentMatrix.mapPoints(this.g);
                this.h.reset();
                this.h.moveTo(this.g[0], this.g[1]);
                this.h.lineTo(this.g[2], this.g[3]);
                this.h.lineTo(this.g[4], this.g[5]);
                this.h.lineTo(this.g[6], this.g[7]);
                this.h.lineTo(this.g[0], this.g[1]);
                this.h.close();
                canvas.drawPath(this.h, this.f);
            }
        }
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        super.a(outBounds, parentMatrix);
        this.e.set(0.0f, 0.0f, (float) this.i.r(), (float) this.i.q());
        this.a.mapRect(this.e);
        outBounds.set(this.e);
    }

    public final <T> void a(T property, @Nullable c<T> callback) {
        super.a((Object) property, (c) callback);
        if (property != com.airbnb.lottie.g.x) {
            return;
        }
        if (callback == null) {
            this.j = null;
        } else {
            this.j = new p(callback);
        }
    }
}
