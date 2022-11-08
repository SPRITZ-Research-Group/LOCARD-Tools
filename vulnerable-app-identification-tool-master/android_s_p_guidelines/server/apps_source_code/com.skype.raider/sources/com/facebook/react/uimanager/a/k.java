package com.facebook.react.uimanager.a;

import android.view.View;
import android.view.animation.Animation;
import javax.annotation.Nullable;

final class k extends a {
    k() {
    }

    final boolean a() {
        return this.b > 0;
    }

    @Nullable
    final Animation a(View view, int x, int y, int width, int height) {
        boolean animateLocation;
        if (view.getX() == ((float) x) && view.getY() == ((float) y)) {
            animateLocation = false;
        } else {
            animateLocation = true;
        }
        boolean animateSize;
        if (view.getWidth() == width && view.getHeight() == height) {
            animateSize = false;
        } else {
            animateSize = true;
        }
        if (animateLocation || animateSize) {
            return new m(view, x, y, width, height);
        }
        return null;
    }
}
