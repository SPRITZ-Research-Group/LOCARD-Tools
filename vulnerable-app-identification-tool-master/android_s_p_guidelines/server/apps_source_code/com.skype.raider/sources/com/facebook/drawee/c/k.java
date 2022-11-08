package com.facebook.drawee.c;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.l.b;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

public final class k extends n {
    private final Paint w = new Paint();
    private final Paint x = new Paint(1);
    @Nullable
    private final Bitmap y;
    private WeakReference<Bitmap> z;

    public k(Resources res, @Nullable Bitmap bitmap, @Nullable Paint paint) {
        super(new BitmapDrawable(res, bitmap));
        this.y = bitmap;
        if (paint != null) {
            this.w.set(paint);
        }
        this.w.setFlags(1);
        this.x.setStyle(Style.STROKE);
    }

    public final void draw(Canvas canvas) {
        b.a();
        if (b()) {
            c();
            d();
            if (this.z == null || this.z.get() != this.y) {
                this.z = new WeakReference(this.y);
                this.w.setShader(new BitmapShader(this.y, TileMode.CLAMP, TileMode.CLAMP));
                this.e = true;
            }
            if (this.e) {
                this.w.getShader().setLocalMatrix(this.v);
                this.e = false;
            }
            int saveCount = canvas.save();
            canvas.concat(this.s);
            canvas.drawPath(this.d, this.w);
            if (this.c > 0.0f) {
                this.x.setStrokeWidth(this.c);
                this.x.setColor(e.a(this.f, this.w.getAlpha()));
                canvas.drawPath(this.g, this.x);
            }
            canvas.restoreToCount(saveCount);
            b.a();
            return;
        }
        super.draw(canvas);
        b.a();
    }

    @VisibleForTesting
    final boolean b() {
        return super.b() && this.y != null;
    }

    public final void setAlpha(int alpha) {
        super.setAlpha(alpha);
        if (alpha != this.w.getAlpha()) {
            this.w.setAlpha(alpha);
            super.setAlpha(alpha);
            invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        this.w.setColorFilter(colorFilter);
    }
}
