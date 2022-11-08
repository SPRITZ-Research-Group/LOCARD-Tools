package androidx.fragment.app;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class q implements AnimationListener {
    private final AnimationListener a;

    q(AnimationListener animationListener) {
        this.a = animationListener;
    }

    public void onAnimationStart(Animation animation) {
        if (this.a != null) {
            this.a.onAnimationStart(animation);
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (this.a != null) {
            this.a.onAnimationEnd(animation);
        }
    }

    public void onAnimationRepeat(Animation animation) {
        if (this.a != null) {
            this.a.onAnimationRepeat(animation);
        }
    }
}
