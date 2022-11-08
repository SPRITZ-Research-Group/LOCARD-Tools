package com.facebook.react.uimanager.a;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.facebook.react.uimanager.f;

abstract class c extends a {
    abstract boolean c();

    c() {
    }

    final boolean a() {
        return this.b > 0 && this.a != null;
    }

    final Animation a(View view, int x, int y, int width, int height) {
        if (this.a != null) {
            float fromValue;
            switch (this.a) {
                case OPACITY:
                    if (c()) {
                        fromValue = view.getAlpha();
                    } else {
                        fromValue = 0.0f;
                    }
                    return new l(view, fromValue, c() ? 0.0f : view.getAlpha());
                case SCALE_XY:
                    float toValue;
                    if (c()) {
                        fromValue = 1.0f;
                    } else {
                        fromValue = 0.0f;
                    }
                    if (c()) {
                        toValue = 0.0f;
                    } else {
                        toValue = 1.0f;
                    }
                    return new ScaleAnimation(fromValue, toValue, fromValue, toValue, 1, 0.5f, 1, 0.5f);
                default:
                    throw new f("Missing animation for property : " + this.a);
            }
        }
        throw new f("Missing animated property from animation config");
    }
}
