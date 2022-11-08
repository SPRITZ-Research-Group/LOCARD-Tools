package com.airbnb.lottie.c.c;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.c;
import com.airbnb.lottie.c.b.n;
import com.airbnb.lottie.c.e;
import java.util.Collections;
import java.util.List;

public final class f extends a {
    private final c e;

    f(LottieDrawable lottieDrawable, d layerModel) {
        super(lottieDrawable, layerModel);
        this.e = new c(lottieDrawable, this, new n("__container", layerModel.n()));
        this.e.a(Collections.emptyList(), Collections.emptyList());
    }

    final void b(@NonNull Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.e.a(canvas, parentMatrix, parentAlpha);
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        super.a(outBounds, parentMatrix);
        this.e.a(outBounds, this.a);
    }

    protected final void b(e keyPath, int depth, List<e> accumulator, e currentPartialKeyPath) {
        this.e.a(keyPath, depth, accumulator, currentPartialKeyPath);
    }
}
