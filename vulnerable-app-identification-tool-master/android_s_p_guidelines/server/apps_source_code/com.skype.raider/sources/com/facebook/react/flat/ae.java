package com.facebook.react.flat;

import android.graphics.Rect;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.p;
import javax.annotation.Nullable;

final class ae extends r {
    private static final int[] d = new int[]{8, 0, 2, 1, 3};
    @Nullable
    private g f;
    @Nullable
    private Rect g;

    ae() {
    }

    public final void setBackgroundColor(int backgroundColor) {
        l().f(backgroundColor);
    }

    public final void setBorderWidths(int index, float borderWidth) {
        super.setBorderWidths(index, borderWidth);
        l().a(d[index], p.a(borderWidth));
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public final void setHotspot(@Nullable am bg) {
        if (bg != null) {
            j();
        }
    }

    @ReactPropGroup(customType = "Color", defaultDouble = Double.NaN, names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public final void setBorderColor(int index, double color) {
        int type = d[index];
        if (Double.isNaN(color)) {
            l().e(type);
        } else {
            l().a(type, (int) color);
        }
    }

    @ReactProp(name = "borderRadius")
    public final void setBorderRadius(float borderRadius) {
        this.b = borderRadius;
        if (this.c && borderRadius > 0.5f) {
            j();
        }
        l().b(p.a(borderRadius));
    }

    @ReactProp(name = "borderStyle")
    public final void setBorderStyle(@Nullable String borderStyle) {
        l().a(borderStyle);
    }

    @ReactProp(name = "hitSlop")
    public final void setHitSlop(@Nullable am hitSlop) {
        if (hitSlop == null) {
            this.g = null;
        } else {
            this.g = new Rect((int) p.a((float) hitSlop.getDouble("left")), (int) p.a((float) hitSlop.getDouble("top")), (int) p.a((float) hitSlop.getDouble("right")), (int) p.a((float) hitSlop.getDouble("bottom")));
        }
    }

    @ReactProp(name = "pointerEvents")
    public final void setPointerEvents(@Nullable String pointerEventsStr) {
        j();
    }

    private g l() {
        if (this.f == null) {
            this.f = new g();
        } else if (this.f.g()) {
            this.f = (g) this.f.f();
        }
        h();
        return this.f;
    }
}
