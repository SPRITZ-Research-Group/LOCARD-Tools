package com.airbnb.lottie.c.c;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.b.a;
import com.airbnb.lottie.a.b.p;
import com.airbnb.lottie.e.f;
import com.airbnb.lottie.g;

public final class c extends a {
    private final Paint e = new Paint(3);
    private final Rect f = new Rect();
    private final Rect g = new Rect();
    @Nullable
    private a<ColorFilter, ColorFilter> h;

    c(LottieDrawable lottieDrawable, d layerModel) {
        super(lottieDrawable, layerModel);
    }

    public final void b(@NonNull Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Bitmap bitmap = c();
        if (bitmap != null) {
            float density = f.a();
            this.e.setAlpha(parentAlpha);
            if (this.h != null) {
                this.e.setColorFilter((ColorFilter) this.h.e());
            }
            canvas.save();
            canvas.concat(parentMatrix);
            this.f.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            this.g.set(0, 0, (int) (((float) bitmap.getWidth()) * density), (int) (((float) bitmap.getHeight()) * density));
            canvas.drawBitmap(bitmap, this.f, this.g, this.e);
            canvas.restore();
        }
    }

    public final void a(RectF outBounds, Matrix parentMatrix) {
        super.a(outBounds, parentMatrix);
        Bitmap bitmap = c();
        if (bitmap != null) {
            outBounds.set(outBounds.left, outBounds.top, Math.min(outBounds.right, (float) bitmap.getWidth()), Math.min(outBounds.bottom, (float) bitmap.getHeight()));
            this.a.mapRect(outBounds);
        }
    }

    @Nullable
    private Bitmap c() {
        return this.b.b(this.c.g());
    }

    public final <T> void a(T property, @Nullable com.airbnb.lottie.f.c<T> callback) {
        super.a((Object) property, (com.airbnb.lottie.f.c) callback);
        if (property != g.x) {
            return;
        }
        if (callback == null) {
            this.h = null;
        } else {
            this.h = new p(callback);
        }
    }
}
