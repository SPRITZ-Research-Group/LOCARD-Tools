package com.facebook.drawee.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.b.a.b;
import com.facebook.drawee.c.q;
import javax.annotation.Nullable;

public final class a extends Drawable implements b {
    private String a;
    private String b;
    private int c;
    private int d;
    private int e;
    private String f;
    private q.b g;
    private int h;
    private int i;
    private int j = 80;
    private final Paint k = new Paint(1);
    private final Matrix l = new Matrix();
    private final Rect m = new Rect();
    private final RectF n = new RectF();
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private long t;
    private String u;

    public a() {
        a();
    }

    public final void a() {
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.h = -1;
        this.i = -1;
        this.f = null;
        a(null);
        this.t = -1;
        this.u = null;
        invalidateSelf();
    }

    public final void a(@Nullable String controllerId) {
        if (controllerId == null) {
            controllerId = "none";
        }
        this.a = controllerId;
        invalidateSelf();
    }

    public final void a(int widthPx, int heightPx) {
        this.c = widthPx;
        this.d = heightPx;
        invalidateSelf();
    }

    public final void b(String s) {
        this.u = s;
        invalidateSelf();
    }

    public final void a(int imageSizeBytes) {
        this.e = imageSizeBytes;
    }

    public final void a(q.b scaleType) {
        this.g = scaleType;
    }

    protected final void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        int min = Math.min(40, Math.max(10, Math.min(bounds.width() / 8, bounds.height() / 9)));
        this.k.setTextSize((float) min);
        this.q = min + 8;
        if (this.j == 80) {
            this.q *= -1;
        }
        this.o = bounds.left + 10;
        if (this.j == 80) {
            min = bounds.bottom - 10;
        } else {
            min = (bounds.top + 10) + 10;
        }
        this.p = min;
    }

    public final void draw(Canvas canvas) {
        int i;
        Rect bounds = getBounds();
        this.k.setStyle(Style.STROKE);
        this.k.setStrokeWidth(2.0f);
        this.k.setColor(-26624);
        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.k);
        this.k.setStyle(Style.FILL);
        Paint paint = this.k;
        int i2 = this.c;
        int i3 = this.d;
        q.b bVar = this.g;
        int width = getBounds().width();
        int height = getBounds().height();
        if (width <= 0 || height <= 0 || i2 <= 0 || i3 <= 0) {
            i = 1727284022;
        } else {
            int min;
            if (bVar != null) {
                Rect rect = this.m;
                this.m.top = 0;
                rect.left = 0;
                this.m.right = width;
                this.m.bottom = height;
                this.l.reset();
                bVar.a(this.l, this.m, i2, i3, 0.0f, 0.0f);
                RectF rectF = this.n;
                this.n.top = 0.0f;
                rectF.left = 0.0f;
                this.n.right = (float) i2;
                this.n.bottom = (float) i3;
                this.l.mapRect(this.n);
                int height2 = (int) this.n.height();
                min = Math.min(width, (int) this.n.width());
                i = Math.min(height, height2);
            } else {
                i = height;
                min = width;
            }
            float f = ((float) min) * 0.1f;
            float f2 = ((float) min) * 0.5f;
            float f3 = ((float) i) * 0.1f;
            float f4 = ((float) i) * 0.5f;
            min = Math.abs(i2 - min);
            i = Math.abs(i3 - i);
            if (((float) min) < f && ((float) i) < f3) {
                i = 1716301648;
            } else if (((float) min) >= f2 || ((float) i) >= f4) {
                i = 1727284022;
            } else {
                i = 1728026624;
            }
        }
        paint.setColor(i);
        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.k);
        this.k.setStyle(Style.FILL);
        this.k.setStrokeWidth(0.0f);
        this.k.setColor(-1);
        this.r = this.o;
        this.s = this.p;
        if (this.b != null) {
            a(canvas, "IDs: %s, %s", this.a, this.b);
        } else {
            a(canvas, "ID: %s", this.a);
        }
        a(canvas, "D: %dx%d", Integer.valueOf(bounds.width()), Integer.valueOf(bounds.height()));
        a(canvas, "I: %dx%d", Integer.valueOf(this.c), Integer.valueOf(this.d));
        a(canvas, "I: %d KiB", Integer.valueOf(this.e / 1024));
        if (this.f != null) {
            a(canvas, "i format: %s", this.f);
        }
        if (this.h > 0) {
            a(canvas, "anim: f %d, l %d", Integer.valueOf(this.h), Integer.valueOf(this.i));
        }
        if (this.g != null) {
            a(canvas, "scale: %s", this.g);
        }
        if (this.t >= 0) {
            a(canvas, "t: %d ms", Long.valueOf(this.t));
        }
        if (this.u != null) {
            a(canvas, "origin: %s", this.u);
        }
    }

    public final void setAlpha(int alpha) {
    }

    public final void setColorFilter(ColorFilter cf) {
    }

    public final int getOpacity() {
        return -3;
    }

    private void a(Canvas canvas, String text, @Nullable Object... args) {
        canvas.drawText(String.format(text, args), (float) this.r, (float) this.s, this.k);
        this.s += this.q;
    }

    public final void a(long finalImageTimeMs) {
        this.t = finalImageTimeMs;
        invalidateSelf();
    }
}
