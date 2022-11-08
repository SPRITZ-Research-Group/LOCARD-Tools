package com.facebook.react.flat;

import com.facebook.drawee.c.q.b;
import com.facebook.react.bridge.al;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.image.c;
import javax.annotation.Nullable;

class z<T extends b & j> extends r {
    static Object d = z.class;
    private T f;

    static Object l() {
        return d;
    }

    z(T drawImage) {
        this.f = drawImage;
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setShouldNotifyLoadEvents(boolean shouldNotifyLoadEvents) {
        ((j) m()).b(shouldNotifyLoadEvents ? A() : 0);
    }

    @ReactProp(name = "src")
    public void setSource(@Nullable al sources) {
        ((j) m()).a(E(), sources);
    }

    @ReactProp(name = "tintColor")
    public void setTintColor(int tintColor) {
        ((j) m()).a(tintColor);
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(@Nullable String resizeMode) {
        b scaleType = c.a(resizeMode);
        if (((j) this.f).b() != scaleType) {
            ((j) m()).a(scaleType);
        }
    }

    @ReactProp(customType = "Color", name = "borderColor")
    public void setBorderColor(int borderColor) {
        if (((j) this.f).e() != borderColor) {
            ((j) m()).c(borderColor);
        }
    }

    public final void c(int spacingType, float borderWidth) {
        super.c(spacingType, borderWidth);
        if (spacingType == 8 && ((j) this.f).c() != borderWidth) {
            ((j) m()).a(borderWidth);
        }
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(float borderRadius) {
        if (((j) this.f).d() != borderRadius) {
            ((j) m()).b(p.a(borderRadius));
        }
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(int durationMs) {
        ((j) m()).d(durationMs);
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(boolean enabled) {
        ((j) m()).a(enabled);
    }

    private T m() {
        if (this.f.g()) {
            this.f = this.f.f();
            h();
        }
        return this.f;
    }
}
