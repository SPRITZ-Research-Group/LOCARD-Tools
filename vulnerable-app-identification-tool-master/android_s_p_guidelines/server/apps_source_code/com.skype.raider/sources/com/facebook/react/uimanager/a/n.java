package com.facebook.react.uimanager.a;

import android.view.animation.Interpolator;

final class n implements Interpolator {
    n() {
    }

    public final float getInterpolation(float input) {
        return (float) (1.0d + (Math.pow(2.0d, (double) (-10.0f * input)) * Math.sin(((((double) (input - 0.125f)) * 3.141592653589793d) * 2.0d) / 0.5d)));
    }
}
