package android.support.v7.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class a extends Drawable implements Callback {
    private Drawable a;

    public a(Drawable drawable) {
        if (this.a != null) {
            this.a.setCallback(null);
        }
        this.a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    public void draw(Canvas canvas) {
        this.a.draw(canvas);
    }

    protected void onBoundsChange(Rect bounds) {
        this.a.setBounds(bounds);
    }

    public void setChangingConfigurations(int configs) {
        this.a.setChangingConfigurations(configs);
    }

    public int getChangingConfigurations() {
        return this.a.getChangingConfigurations();
    }

    public void setDither(boolean dither) {
        this.a.setDither(dither);
    }

    public void setFilterBitmap(boolean filter) {
        this.a.setFilterBitmap(filter);
    }

    public void setAlpha(int alpha) {
        this.a.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.a.setColorFilter(cf);
    }

    public boolean isStateful() {
        return this.a.isStateful();
    }

    public boolean setState(int[] stateSet) {
        return this.a.setState(stateSet);
    }

    public int[] getState() {
        return this.a.getState();
    }

    public void jumpToCurrentState() {
        android.support.v4.graphics.drawable.a.a(this.a);
    }

    public Drawable getCurrent() {
        return this.a.getCurrent();
    }

    public boolean setVisible(boolean visible, boolean restart) {
        return super.setVisible(visible, restart) || this.a.setVisible(visible, restart);
    }

    public int getOpacity() {
        return this.a.getOpacity();
    }

    public Region getTransparentRegion() {
        return this.a.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        return this.a.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.a.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        return this.a.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.a.getMinimumHeight();
    }

    public boolean getPadding(Rect padding) {
        return this.a.getPadding(padding);
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
        return this.a.setLevel(level);
    }

    public void setAutoMirrored(boolean mirrored) {
        android.support.v4.graphics.drawable.a.a(this.a, mirrored);
    }

    public boolean isAutoMirrored() {
        return android.support.v4.graphics.drawable.a.b(this.a);
    }

    public void setTint(int tint) {
        android.support.v4.graphics.drawable.a.a(this.a, tint);
    }

    public void setTintList(ColorStateList tint) {
        android.support.v4.graphics.drawable.a.a(this.a, tint);
    }

    public void setTintMode(Mode tintMode) {
        android.support.v4.graphics.drawable.a.a(this.a, tintMode);
    }

    public void setHotspot(float x, float y) {
        android.support.v4.graphics.drawable.a.a(this.a, x, y);
    }

    public void setHotspotBounds(int left, int top, int right, int bottom) {
        android.support.v4.graphics.drawable.a.a(this.a, left, top, right, bottom);
    }

    public final Drawable b() {
        return this.a;
    }
}
