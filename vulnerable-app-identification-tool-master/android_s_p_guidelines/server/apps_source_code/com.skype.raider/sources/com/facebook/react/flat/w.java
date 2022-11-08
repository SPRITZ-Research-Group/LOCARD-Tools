package com.facebook.react.flat;

import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.al;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;
import javax.annotation.Nullable;

final class w extends r {
    @Nullable
    private final com.facebook.react.uimanager.w d;
    private boolean f;
    private boolean g;

    public final void setBackgroundColor(int backgroundColor) {
    }

    public final void a(int reactTag) {
        super.a(reactTag);
        if (this.d != null) {
            this.d.a(reactTag);
        }
    }

    public final void a(ae themedContext) {
        super.a(themedContext);
        if (this.d != null) {
            this.d.a(themedContext);
        }
    }

    public final void a(com.facebook.react.uimanager.w child, int i) {
        super.a(child, i);
        if (this.g && (child instanceof r)) {
            ((r) child).c();
        }
    }

    public final void a(int spacingType, float padding) {
        YogaValue current = h(spacingType);
        if (current.unit != YogaUnit.POINT || current.value != padding) {
            super.a(spacingType, padding);
            this.f = true;
            i();
        }
    }

    public final void b(int spacingType, float percent) {
        YogaValue current = h(spacingType);
        if (current.unit != YogaUnit.PERCENT || current.value != percent) {
            super.a(spacingType, percent);
            this.f = true;
            i();
        }
    }

    public final void a(al uiViewOperationQueue) {
        if (this.d != null && this.d.w()) {
            this.d.a(uiViewOperationQueue);
            v();
        }
    }
}
