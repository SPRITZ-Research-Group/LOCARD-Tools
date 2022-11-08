package com.facebook.react.views.modal;

import android.graphics.Point;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.w;

class b extends h {
    b() {
    }

    public final void a(w child, int i) {
        super.a(child, i);
        Point modalSize = a.a(E());
        child.a((float) modalSize.x);
        child.b((float) modalSize.y);
    }
}
