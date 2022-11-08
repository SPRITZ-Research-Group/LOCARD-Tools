package com.facebook.drawee.c;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import com.facebook.common.internal.h;
import javax.annotation.Nullable;

public class a extends Drawable implements Callback, r, s {
    private s a;
    private final d b = new d();
    private final Drawable[] c;
    private final c[] d;
    private final Rect e = new Rect();
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;

    public a(Drawable[] layers) {
        h.a((Object) layers);
        this.c = layers;
        for (Drawable a : this.c) {
            e.a(a, this, this);
        }
        this.d = new c[this.c.length];
    }

    public final int a() {
        return this.c.length;
    }

    @Nullable
    public final Drawable a(int index) {
        boolean z;
        boolean z2 = true;
        if (index >= 0) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (index >= this.c.length) {
            z2 = false;
        }
        h.a(z2);
        return this.c[index];
    }

    @Nullable
    public final Drawable a(int index, @Nullable Drawable drawable) {
        boolean z;
        boolean z2 = true;
        if (index >= 0) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        if (index >= this.c.length) {
            z2 = false;
        }
        h.a(z2);
        Drawable oldDrawable = this.c[index];
        if (drawable != oldDrawable) {
            if (drawable != null && this.h) {
                drawable.mutate();
            }
            e.a(this.c[index], null, null);
            e.a(drawable, null, null);
            e.a(drawable, this.b);
            e.a(drawable, (Drawable) this);
            e.a(drawable, this, this);
            this.g = false;
            this.c[index] = drawable;
            invalidateSelf();
        }
        return oldDrawable;
    }

    public int getIntrinsicWidth() {
        int width = -1;
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                width = Math.max(width, drawable.getIntrinsicWidth());
            }
        }
        return width > 0 ? width : -1;
    }

    public int getIntrinsicHeight() {
        int height = -1;
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                height = Math.max(height, drawable.getIntrinsicHeight());
            }
        }
        return height > 0 ? height : -1;
    }

    protected void onBoundsChange(Rect bounds) {
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setBounds(bounds);
            }
        }
    }

    public boolean isStateful() {
        if (!this.g) {
            this.f = false;
            for (Drawable drawable : this.c) {
                int i;
                boolean z = this.f;
                if (drawable == null || !drawable.isStateful()) {
                    i = 0;
                } else {
                    i = 1;
                }
                this.f = i | z;
            }
            this.g = true;
        }
        return this.f;
    }

    protected boolean onStateChange(int[] state) {
        boolean stateChanged = false;
        for (Drawable drawable : this.c) {
            if (drawable != null && drawable.setState(state)) {
                stateChanged = true;
            }
        }
        return stateChanged;
    }

    protected boolean onLevelChange(int level) {
        boolean levelChanged = false;
        for (Drawable drawable : this.c) {
            if (drawable != null && drawable.setLevel(level)) {
                levelChanged = true;
            }
        }
        return levelChanged;
    }

    public void draw(Canvas canvas) {
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    public boolean getPadding(Rect padding) {
        padding.left = 0;
        padding.top = 0;
        padding.right = 0;
        padding.bottom = 0;
        Rect rect = this.e;
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.getPadding(rect);
                padding.left = Math.max(padding.left, rect.left);
                padding.top = Math.max(padding.top, rect.top);
                padding.right = Math.max(padding.right, rect.right);
                padding.bottom = Math.max(padding.bottom, rect.bottom);
            }
        }
        return true;
    }

    public Drawable mutate() {
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.mutate();
            }
        }
        this.h = true;
        return this;
    }

    public int getOpacity() {
        if (this.c.length == 0) {
            return -2;
        }
        int opacity = -1;
        for (int i = 1; i < this.c.length; i++) {
            Drawable drawable = this.c[i];
            if (drawable != null) {
                opacity = Drawable.resolveOpacity(opacity, drawable.getOpacity());
            }
        }
        return opacity;
    }

    public void setAlpha(int alpha) {
        this.b.a(alpha);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setAlpha(alpha);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.b.a(colorFilter);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setDither(boolean dither) {
        this.b.a(dither);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setDither(dither);
            }
        }
    }

    public void setFilterBitmap(boolean filterBitmap) {
        this.b.b(filterBitmap);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setFilterBitmap(filterBitmap);
            }
        }
    }

    public boolean setVisible(boolean visible, boolean restart) {
        boolean changed = super.setVisible(visible, restart);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setVisible(visible, restart);
            }
        }
        return changed;
    }

    public final c b(final int index) {
        boolean z = true;
        h.a(index >= 0);
        if (index >= this.d.length) {
            z = false;
        }
        h.a(z);
        if (this.d[index] == null) {
            this.d[index] = new c(this) {
                final /* synthetic */ a b;

                public final Drawable a(Drawable newDrawable) {
                    return this.b.a(index, newDrawable);
                }

                public final Drawable a() {
                    return this.b.a(index);
                }
            };
        }
        return this.d[index];
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
        this.a = transformCallback;
    }

    public final void a(Matrix transform) {
        if (this.a != null) {
            this.a.a(transform);
        } else {
            transform.reset();
        }
    }

    public final void a(RectF bounds) {
        if (this.a != null) {
            this.a.a(bounds);
        } else {
            bounds.set(getBounds());
        }
    }

    @TargetApi(21)
    public void setHotspot(float x, float y) {
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setHotspot(x, y);
            }
        }
    }
}
