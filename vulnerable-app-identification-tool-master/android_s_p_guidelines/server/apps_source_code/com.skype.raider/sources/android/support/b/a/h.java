package android.support.b.a;

import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.a;
import android.support.v4.graphics.drawable.f;

abstract class h extends Drawable implements f {
    Drawable b;

    h() {
    }

    public void setColorFilter(int color, Mode mode) {
        if (this.b != null) {
            this.b.setColorFilter(color, mode);
        } else {
            super.setColorFilter(color, mode);
        }
    }

    public ColorFilter getColorFilter() {
        if (this.b != null) {
            return a.e(this.b);
        }
        return null;
    }

    protected boolean onLevelChange(int level) {
        if (this.b != null) {
            return this.b.setLevel(level);
        }
        return super.onLevelChange(level);
    }

    protected void onBoundsChange(Rect bounds) {
        if (this.b != null) {
            this.b.setBounds(bounds);
        } else {
            super.onBoundsChange(bounds);
        }
    }

    public void setHotspot(float x, float y) {
        if (this.b != null) {
            a.a(this.b, x, y);
        }
    }

    public void setHotspotBounds(int left, int top, int right, int bottom) {
        if (this.b != null) {
            a.a(this.b, left, top, right, bottom);
        }
    }

    public void setFilterBitmap(boolean filter) {
        if (this.b != null) {
            this.b.setFilterBitmap(filter);
        }
    }

    public void jumpToCurrentState() {
        if (this.b != null) {
            a.a(this.b);
        }
    }

    public void applyTheme(Theme t) {
        if (this.b != null) {
            a.a(this.b, t);
        }
    }

    public void clearColorFilter() {
        if (this.b != null) {
            this.b.clearColorFilter();
        } else {
            super.clearColorFilter();
        }
    }

    public Drawable getCurrent() {
        if (this.b != null) {
            return this.b.getCurrent();
        }
        return super.getCurrent();
    }

    public int getMinimumWidth() {
        if (this.b != null) {
            return this.b.getMinimumWidth();
        }
        return super.getMinimumWidth();
    }

    public int getMinimumHeight() {
        if (this.b != null) {
            return this.b.getMinimumHeight();
        }
        return super.getMinimumHeight();
    }

    public boolean getPadding(Rect padding) {
        if (this.b != null) {
            return this.b.getPadding(padding);
        }
        return super.getPadding(padding);
    }

    public int[] getState() {
        if (this.b != null) {
            return this.b.getState();
        }
        return super.getState();
    }

    public Region getTransparentRegion() {
        if (this.b != null) {
            return this.b.getTransparentRegion();
        }
        return super.getTransparentRegion();
    }

    public void setChangingConfigurations(int configs) {
        if (this.b != null) {
            this.b.setChangingConfigurations(configs);
        } else {
            super.setChangingConfigurations(configs);
        }
    }

    public boolean setState(int[] stateSet) {
        if (this.b != null) {
            return this.b.setState(stateSet);
        }
        return super.setState(stateSet);
    }
}
