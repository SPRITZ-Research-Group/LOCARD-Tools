package com.facebook.drawee.c;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.Nullable;

public class g extends Drawable implements Callback, c, r, s {
    private static final Matrix d = new Matrix();
    @Nullable
    private Drawable a;
    protected s b;
    private final d c = new d();

    public g(@Nullable Drawable drawable) {
        this.a = drawable;
        e.a(this.a, this, this);
    }

    @Nullable
    public Drawable b(@Nullable Drawable newDelegate) {
        Drawable previousDelegate = this.a;
        e.a(previousDelegate, null, null);
        e.a(newDelegate, null, null);
        e.a(newDelegate, this.c);
        e.a(newDelegate, (Drawable) this);
        e.a(newDelegate, this, this);
        this.a = newDelegate;
        invalidateSelf();
        return previousDelegate;
    }

    public int getOpacity() {
        if (this.a == null) {
            return 0;
        }
        return this.a.getOpacity();
    }

    public void setAlpha(int alpha) {
        this.c.a(alpha);
        if (this.a != null) {
            this.a.setAlpha(alpha);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.c.a(colorFilter);
        if (this.a != null) {
            this.a.setColorFilter(colorFilter);
        }
    }

    public void setDither(boolean dither) {
        this.c.a(dither);
        if (this.a != null) {
            this.a.setDither(dither);
        }
    }

    public void setFilterBitmap(boolean filterBitmap) {
        this.c.b(filterBitmap);
        if (this.a != null) {
            this.a.setFilterBitmap(filterBitmap);
        }
    }

    public boolean setVisible(boolean visible, boolean restart) {
        return this.a == null ? super.setVisible(visible, restart) : this.a.setVisible(visible, restart);
    }

    protected void onBoundsChange(Rect bounds) {
        if (this.a != null) {
            this.a.setBounds(bounds);
        }
    }

    @Nullable
    public ConstantState getConstantState() {
        if (this.a == null) {
            return super.getConstantState();
        }
        return this.a.getConstantState();
    }

    public boolean isStateful() {
        if (this.a == null) {
            return false;
        }
        return this.a.isStateful();
    }

    protected boolean onStateChange(int[] state) {
        if (this.a == null) {
            return super.onStateChange(state);
        }
        return this.a.setState(state);
    }

    protected boolean onLevelChange(int level) {
        if (this.a == null) {
            return super.onLevelChange(level);
        }
        return this.a.setLevel(level);
    }

    public void draw(Canvas canvas) {
        if (this.a != null) {
            this.a.draw(canvas);
        }
    }

    public int getIntrinsicWidth() {
        if (this.a == null) {
            return super.getIntrinsicWidth();
        }
        return this.a.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        if (this.a == null) {
            return super.getIntrinsicHeight();
        }
        return this.a.getIntrinsicHeight();
    }

    public boolean getPadding(Rect padding) {
        if (this.a == null) {
            return super.getPadding(padding);
        }
        return this.a.getPadding(padding);
    }

    public Drawable mutate() {
        if (this.a != null) {
            this.a.mutate();
        }
        return this;
    }

    @Nullable
    public Drawable getCurrent() {
        return this.a;
    }

    public final Drawable a(@Nullable Drawable newDrawable) {
        return b(newDrawable);
    }

    @Nullable
    public final Drawable a() {
        return getCurrent();
    }

    public void invalidateDrawable(Drawable who) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        scheduleSelf(what, when);
    }

    public void unscheduleDrawable(Drawable who, Runnable what) {
        unscheduleSelf(what);
    }

    public final void a(s transformCallback) {
        this.b = transformCallback;
    }

    protected final void b(Matrix transform) {
        if (this.b != null) {
            this.b.a(transform);
        } else {
            transform.reset();
        }
    }

    public void a(Matrix transform) {
        b(transform);
    }

    public final void a(RectF bounds) {
        if (this.b != null) {
            this.b.a(bounds);
        } else {
            bounds.set(getBounds());
        }
    }

    @TargetApi(21)
    public void setHotspot(float x, float y) {
        if (this.a != null) {
            this.a.setHotspot(x, y);
        }
    }
}
