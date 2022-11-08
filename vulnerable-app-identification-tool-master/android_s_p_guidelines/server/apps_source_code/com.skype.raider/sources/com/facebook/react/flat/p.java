package com.facebook.react.flat;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.react.uimanager.w;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;

class p extends r {
    private final Point d = new Point();
    private final Point f = new Point();
    private boolean g;

    p() {
        j();
        c();
    }

    @TargetApi(16)
    public final void a(w child, int i) {
        int width;
        int height;
        super.a(child, i);
        Display display = ((WindowManager) E().getSystemService("window")).getDefaultDisplay();
        display.getCurrentSizeRange(this.d, this.f);
        int rotation = display.getRotation();
        if (rotation == 0 || rotation == 2) {
            width = this.d.x;
            height = this.f.y;
        } else {
            width = this.f.x;
            height = this.d.y;
        }
        child.a((float) width);
        child.b((float) height);
    }

    public final void a(int spacingType, float padding) {
        YogaValue current = h(spacingType);
        if (current.unit != YogaUnit.POINT || current.value != padding) {
            super.a(spacingType, padding);
            this.g = true;
            i();
        }
    }

    public final void b(int spacingType, float percent) {
        YogaValue current = h(spacingType);
        if (current.unit != YogaUnit.PERCENT || current.value != percent) {
            super.a(spacingType, percent);
            this.g = true;
            i();
        }
    }
}
