package com.airbnb.lottie.c.c;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;

public final class e extends a {
    e(LottieDrawable lottieDrawable, d layerModel) {
        super(lottieDrawable, layerModel);
    }

    final void b(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        super.a(outBounds, parentMatrix);
        outBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
    }
}
