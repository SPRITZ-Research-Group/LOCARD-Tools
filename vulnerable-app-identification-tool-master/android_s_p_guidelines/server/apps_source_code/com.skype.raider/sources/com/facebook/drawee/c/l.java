package com.facebook.drawee.c;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import java.util.Arrays;
import javax.annotation.Nullable;

public final class l extends Drawable implements j {
    @VisibleForTesting
    final float[] a = new float[8];
    @VisibleForTesting
    @Nullable
    float[] b;
    @VisibleForTesting
    final Paint c = new Paint(1);
    @VisibleForTesting
    final Path d = new Path();
    @VisibleForTesting
    final Path e = new Path();
    private final float[] f = new float[8];
    private boolean g = false;
    private float h = 0.0f;
    private float i = 0.0f;
    private int j = 0;
    private boolean k = false;
    private int l = 0;
    private final RectF m = new RectF();
    private int n = 255;

    private l(int color) {
        if (this.l != color) {
            this.l = color;
            invalidateSelf();
        }
    }

    @TargetApi(11)
    public static l a(ColorDrawable colorDrawable) {
        return new l(colorDrawable.getColor());
    }

    protected final void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        b();
    }

    public final void draw(Canvas canvas) {
        this.c.setColor(e.a(this.l, this.n));
        this.c.setStyle(Style.FILL);
        canvas.drawPath(this.d, this.c);
        if (this.h != 0.0f) {
            this.c.setColor(e.a(this.j, this.n));
            this.c.setStyle(Style.STROKE);
            this.c.setStrokeWidth(this.h);
            canvas.drawPath(this.e, this.c);
        }
    }

    public final void a(boolean isCircle) {
        this.g = isCircle;
        b();
        invalidateSelf();
    }

    public final void a(float[] radii) {
        if (radii == null) {
            Arrays.fill(this.f, 0.0f);
        } else {
            boolean z;
            if (radii.length == 8) {
                z = true;
            } else {
                z = false;
            }
            h.a(z, (Object) "radii should have exactly 8 values");
            System.arraycopy(radii, 0, this.f, 0, 8);
        }
        b();
        invalidateSelf();
    }

    public final void h_() {
        h.a(true, (Object) "radius should be non negative");
        Arrays.fill(this.f, 0.0f);
        b();
        invalidateSelf();
    }

    public final void a(int color, float width) {
        if (this.j != color) {
            this.j = color;
            invalidateSelf();
        }
        if (this.h != width) {
            this.h = width;
            b();
            invalidateSelf();
        }
    }

    public final void a(float padding) {
        if (this.i != padding) {
            this.i = padding;
            b();
            invalidateSelf();
        }
    }

    public final void b(boolean scaleDownInsideBorders) {
        if (this.k != scaleDownInsideBorders) {
            this.k = scaleDownInsideBorders;
            b();
            invalidateSelf();
        }
    }

    public final void setAlpha(int alpha) {
        if (alpha != this.n) {
            this.n = alpha;
            invalidateSelf();
        }
    }

    public final int getAlpha() {
        return this.n;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
    }

    public final int getOpacity() {
        int a = e.a(this.l, this.n) >>> 24;
        if (a == 255) {
            return -1;
        }
        if (a == 0) {
            return -2;
        }
        return -3;
    }

    private void b() {
        int i;
        this.d.reset();
        this.e.reset();
        this.m.set(getBounds());
        this.m.inset(this.h / 2.0f, this.h / 2.0f);
        if (this.g) {
            this.e.addCircle(this.m.centerX(), this.m.centerY(), Math.min(this.m.width(), this.m.height()) / 2.0f, Direction.CW);
        } else {
            for (i = 0; i < this.a.length; i++) {
                this.a[i] = (this.f[i] + this.i) - (this.h / 2.0f);
            }
            this.e.addRoundRect(this.m, this.a, Direction.CW);
        }
        this.m.inset((-this.h) / 2.0f, (-this.h) / 2.0f);
        float totalPadding = this.i + (this.k ? this.h : 0.0f);
        this.m.inset(totalPadding, totalPadding);
        if (this.g) {
            this.d.addCircle(this.m.centerX(), this.m.centerY(), Math.min(this.m.width(), this.m.height()) / 2.0f, Direction.CW);
        } else if (this.k) {
            if (this.b == null) {
                this.b = new float[8];
            }
            for (i = 0; i < this.b.length; i++) {
                this.b[i] = this.f[i] - this.h;
            }
            this.d.addRoundRect(this.m, this.b, Direction.CW);
        } else {
            this.d.addRoundRect(this.m, this.f, Direction.CW);
        }
        this.m.inset(-totalPadding, -totalPadding);
    }
}
