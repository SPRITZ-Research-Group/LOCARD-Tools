package com.facebook.react.uimanager.a;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;

final class l extends Animation {
    private final View a;
    private final float b;
    private final float c;

    static class a implements AnimationListener {
        private final View a;
        private boolean b = false;

        public a(View view) {
            this.a = view;
        }

        public final void onAnimationStart(Animation animation) {
            if (this.a.hasOverlappingRendering() && this.a.getLayerType() == 0) {
                this.b = true;
                this.a.setLayerType(2, null);
            }
        }

        public final void onAnimationEnd(Animation animation) {
            if (this.b) {
                this.a.setLayerType(0, null);
            }
        }

        public final void onAnimationRepeat(Animation animation) {
        }
    }

    public l(View view, float startOpacity, float endOpacity) {
        this.a = view;
        this.b = startOpacity;
        this.c = endOpacity - startOpacity;
        setAnimationListener(new a(view));
    }

    protected final void applyTransformation(float interpolatedTime, Transformation t) {
        this.a.setAlpha(this.b + (this.c * interpolatedTime));
    }

    public final boolean willChangeBounds() {
        return false;
    }
}
