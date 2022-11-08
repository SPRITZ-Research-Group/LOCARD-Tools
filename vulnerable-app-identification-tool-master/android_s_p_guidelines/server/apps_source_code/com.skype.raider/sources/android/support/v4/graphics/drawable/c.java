package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
class c extends Drawable implements Callback, b, f {
    static final Mode a = Mode.SRC_IN;
    a b;
    Drawable c;
    private int d;
    private Mode e;
    private boolean f;
    private boolean g;

    protected static abstract class a extends ConstantState {
        int a;
        ConstantState b;
        ColorStateList c = null;
        Mode d = c.a;

        public abstract Drawable newDrawable(@Nullable Resources resources);

        a(@Nullable a orig) {
            if (orig != null) {
                this.a = orig.a;
                this.b = orig.b;
                this.c = orig.c;
                this.d = orig.d;
            }
        }

        public Drawable newDrawable() {
            return newDrawable(null);
        }

        public int getChangingConfigurations() {
            return (this.b != null ? this.b.getChangingConfigurations() : 0) | this.a;
        }
    }

    private static class b extends a {
        b(@Nullable a orig) {
            super(orig);
        }

        public final Drawable newDrawable(@Nullable Resources res) {
            return new c(this, res);
        }
    }

    c(@NonNull a state, @Nullable Resources res) {
        this.b = state;
        if (this.b != null && this.b.b != null) {
            a(this.b.b.newDrawable(res));
        }
    }

    c(@Nullable Drawable dr) {
        this.b = b();
        a(dr);
    }

    public void jumpToCurrentState() {
        this.c.jumpToCurrentState();
    }

    public void draw(Canvas canvas) {
        this.c.draw(canvas);
    }

    protected void onBoundsChange(Rect bounds) {
        if (this.c != null) {
            this.c.setBounds(bounds);
        }
    }

    public void setChangingConfigurations(int configs) {
        this.c.setChangingConfigurations(configs);
    }

    public int getChangingConfigurations() {
        return ((this.b != null ? this.b.getChangingConfigurations() : 0) | super.getChangingConfigurations()) | this.c.getChangingConfigurations();
    }

    public void setDither(boolean dither) {
        this.c.setDither(dither);
    }

    public void setFilterBitmap(boolean filter) {
        this.c.setFilterBitmap(filter);
    }

    public void setAlpha(int alpha) {
        this.c.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.c.setColorFilter(cf);
    }

    public boolean isStateful() {
        ColorStateList tintList = (!c() || this.b == null) ? null : this.b.c;
        return (tintList != null && tintList.isStateful()) || this.c.isStateful();
    }

    public boolean setState(int[] stateSet) {
        return a(stateSet) || this.c.setState(stateSet);
    }

    public int[] getState() {
        return this.c.getState();
    }

    public Drawable getCurrent() {
        return this.c.getCurrent();
    }

    public boolean setVisible(boolean visible, boolean restart) {
        return super.setVisible(visible, restart) || this.c.setVisible(visible, restart);
    }

    public int getOpacity() {
        return this.c.getOpacity();
    }

    public Region getTransparentRegion() {
        return this.c.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        return this.c.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.c.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        return this.c.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.c.getMinimumHeight();
    }

    public boolean getPadding(Rect padding) {
        return this.c.getPadding(padding);
    }

    @Nullable
    public ConstantState getConstantState() {
        if (this.b != null) {
            Object obj;
            if (this.b.b != null) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                this.b.a = getChangingConfigurations();
                return this.b;
            }
        }
        return null;
    }

    public Drawable mutate() {
        if (!this.g && super.mutate() == this) {
            this.b = b();
            if (this.c != null) {
                this.c.mutate();
            }
            if (this.b != null) {
                this.b.b = this.c != null ? this.c.getConstantState() : null;
            }
            this.g = true;
        }
        return this;
    }

    @NonNull
    a b() {
        return new b(this.b);
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

    protected boolean onLevelChange(int level) {
        return this.c.setLevel(level);
    }

    public void setTint(int tint) {
        setTintList(ColorStateList.valueOf(tint));
    }

    public void setTintList(ColorStateList tint) {
        this.b.c = tint;
        a(getState());
    }

    public void setTintMode(Mode tintMode) {
        this.b.d = tintMode;
        a(getState());
    }

    private boolean a(int[] state) {
        if (!c()) {
            return false;
        }
        ColorStateList tintList = this.b.c;
        Mode tintMode = this.b.d;
        if (tintList == null || tintMode == null) {
            this.f = false;
            clearColorFilter();
            return false;
        }
        int color = tintList.getColorForState(state, tintList.getDefaultColor());
        if (this.f && color == this.d && tintMode == this.e) {
            return false;
        }
        setColorFilter(color, tintMode);
        this.d = color;
        this.e = tintMode;
        this.f = true;
        return true;
    }

    public final Drawable a() {
        return this.c;
    }

    public final void a(Drawable dr) {
        if (this.c != null) {
            this.c.setCallback(null);
        }
        this.c = dr;
        if (dr != null) {
            dr.setCallback(this);
            setVisible(dr.isVisible(), true);
            setState(dr.getState());
            setLevel(dr.getLevel());
            setBounds(dr.getBounds());
            if (this.b != null) {
                this.b.b = dr.getConstantState();
            }
        }
        invalidateSelf();
    }

    protected boolean c() {
        return true;
    }
}
