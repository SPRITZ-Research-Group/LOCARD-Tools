package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.lang.reflect.Method;

@RequiresApi(21)
final class e extends d {
    private static Method d;

    private static class a extends a {
        a(@Nullable a orig) {
            super(orig);
        }

        public final Drawable newDrawable(@Nullable Resources res) {
            return new e(this, res);
        }
    }

    e(Drawable drawable) {
        super(drawable);
        d();
    }

    e(a state, Resources resources) {
        super(state, resources);
        d();
    }

    public final void setHotspot(float x, float y) {
        this.c.setHotspot(x, y);
    }

    public final void setHotspotBounds(int left, int top, int right, int bottom) {
        this.c.setHotspotBounds(left, top, right, bottom);
    }

    public final void getOutline(Outline outline) {
        this.c.getOutline(outline);
    }

    public final Rect getDirtyBounds() {
        return this.c.getDirtyBounds();
    }

    public final void setTintList(ColorStateList tint) {
        if (c()) {
            super.setTintList(tint);
        } else {
            this.c.setTintList(tint);
        }
    }

    public final void setTint(int tintColor) {
        if (c()) {
            super.setTint(tintColor);
        } else {
            this.c.setTint(tintColor);
        }
    }

    public final void setTintMode(Mode tintMode) {
        if (c()) {
            super.setTintMode(tintMode);
        } else {
            this.c.setTintMode(tintMode);
        }
    }

    public final boolean setState(int[] stateSet) {
        if (!super.setState(stateSet)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    protected final boolean c() {
        if (VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable drawable = this.c;
        if ((drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }

    @NonNull
    final a b() {
        return new a(this.b);
    }

    private static void d() {
        if (d == null) {
            try {
                d = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception e) {
            }
        }
    }
}
