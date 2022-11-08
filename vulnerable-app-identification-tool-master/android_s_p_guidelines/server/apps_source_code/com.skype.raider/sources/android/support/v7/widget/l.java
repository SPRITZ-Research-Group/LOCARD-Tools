package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.a;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.widget.SeekBar;

final class l extends k {
    private final SeekBar a;
    private Drawable b;
    private ColorStateList c = null;
    private Mode d = null;
    private boolean e = false;
    private boolean f = false;

    l(SeekBar view) {
        super(view);
        this.a = view;
    }

    final void a(AttributeSet attrs, int defStyleAttr) {
        super.a(attrs, defStyleAttr);
        aq a = aq.a(this.a.getContext(), attrs, j.AppCompatSeekBar, defStyleAttr, 0);
        Drawable drawable = a.b(j.AppCompatSeekBar_android_thumb);
        if (drawable != null) {
            this.a.setThumb(drawable);
        }
        Drawable tickMark = a.a(j.AppCompatSeekBar_tickMark);
        if (this.b != null) {
            this.b.setCallback(null);
        }
        this.b = tickMark;
        if (tickMark != null) {
            tickMark.setCallback(this.a);
            a.b(tickMark, ViewCompat.f(this.a));
            if (tickMark.isStateful()) {
                tickMark.setState(this.a.getDrawableState());
            }
            d();
        }
        this.a.invalidate();
        if (a.h(j.AppCompatSeekBar_tickMarkTintMode)) {
            this.d = t.a(a.a(j.AppCompatSeekBar_tickMarkTintMode, -1), this.d);
            this.f = true;
        }
        if (a.h(j.AppCompatSeekBar_tickMarkTint)) {
            this.c = a.f(j.AppCompatSeekBar_tickMarkTint);
            this.e = true;
        }
        a.a();
        d();
    }

    private void d() {
        if (this.b == null) {
            return;
        }
        if (this.e || this.f) {
            this.b = a.f(this.b.mutate());
            if (this.e) {
                a.a(this.b, this.c);
            }
            if (this.f) {
                a.a(this.b, this.d);
            }
            if (this.b.isStateful()) {
                this.b.setState(this.a.getDrawableState());
            }
        }
    }

    @RequiresApi(11)
    final void b() {
        if (this.b != null) {
            this.b.jumpToCurrentState();
        }
    }

    final void c() {
        Drawable tickMark = this.b;
        if (tickMark != null && tickMark.isStateful() && tickMark.setState(this.a.getDrawableState())) {
            this.a.invalidateDrawable(tickMark);
        }
    }

    final void a(Canvas canvas) {
        int halfH = 1;
        if (this.b != null) {
            int count = this.a.getMax();
            if (count > 1) {
                int halfW;
                int w = this.b.getIntrinsicWidth();
                int h = this.b.getIntrinsicHeight();
                if (w >= 0) {
                    halfW = w / 2;
                } else {
                    halfW = 1;
                }
                if (h >= 0) {
                    halfH = h / 2;
                }
                this.b.setBounds(-halfW, -halfH, halfW, halfH);
                float spacing = ((float) ((this.a.getWidth() - this.a.getPaddingLeft()) - this.a.getPaddingRight())) / ((float) count);
                int saveCount = canvas.save();
                canvas.translate((float) this.a.getPaddingLeft(), (float) (this.a.getHeight() / 2));
                for (int i = 0; i <= count; i++) {
                    this.b.draw(canvas);
                    canvas.translate(spacing, 0.0f);
                }
                canvas.restoreToCount(saveCount);
            }
        }
    }
}
