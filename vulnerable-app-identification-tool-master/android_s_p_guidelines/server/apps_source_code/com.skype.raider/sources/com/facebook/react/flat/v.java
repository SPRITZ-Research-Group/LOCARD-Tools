package com.facebook.react.flat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import com.facebook.imagepipeline.k.b;
import javax.annotation.Nullable;

final class v extends ReplacementSpan implements c, d {
    private static final RectF b = new RectF();
    @Nullable
    private y c;
    @Nullable
    private a d;
    private float e;
    private float f;
    private boolean g;

    v() {
        this(null, Float.NaN, Float.NaN);
    }

    private v(@Nullable y requestHelper, float width, float height) {
        this.c = requestHelper;
        this.e = width;
        this.f = height;
    }

    final v d() {
        return new v(this.c, this.e, this.f);
    }

    final void a(@Nullable b imageRequest) {
        if (imageRequest == null) {
            this.c = null;
        } else {
            this.c = new y(imageRequest);
        }
    }

    final float e() {
        return this.e;
    }

    final void a(float width) {
        this.e = width;
    }

    final float f() {
        return this.f;
    }

    final void b(float height) {
        this.f = height;
    }

    final void g() {
        this.g = true;
    }

    final boolean h() {
        return this.g;
    }

    public final void b() {
        this.d.a();
    }

    public final void c() {
        this.d.a();
    }

    public final void a(a callback) {
        this.d = callback;
        if (this.c != null) {
            this.c.a((d) this);
        }
    }

    public final void a() {
        if (this.c != null) {
            this.c.a();
            if (this.c.c()) {
                this.d = null;
            }
        }
    }

    public final int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        if (fm != null) {
            fm.ascent = -Math.round(this.f);
            fm.descent = 0;
            fm.top = fm.ascent;
            fm.bottom = 0;
        }
        return Math.round(this.e);
    }

    public final void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        if (this.c != null) {
            Bitmap bitmap = this.c.b();
            if (bitmap != null) {
                float bottomFloat = ((float) bottom) - ((float) paint.getFontMetricsInt().descent);
                b.set(x, bottomFloat - this.f, this.e + x, bottomFloat);
                canvas.drawBitmap(bitmap, null, b, paint);
            }
        }
    }
}
