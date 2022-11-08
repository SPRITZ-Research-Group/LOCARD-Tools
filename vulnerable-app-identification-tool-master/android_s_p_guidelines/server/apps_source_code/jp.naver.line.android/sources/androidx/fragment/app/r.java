package androidx.fragment.app;

import android.animation.Animator;
import android.view.animation.Animation;

final class r {
    public final Animation a;
    public final Animator b;

    r(Animation animation) {
        this.a = animation;
        this.b = null;
        if (animation == null) {
            throw new IllegalStateException("Animation cannot be null");
        }
    }

    r(Animator animator) {
        this.a = null;
        this.b = animator;
        if (animator == null) {
            throw new IllegalStateException("Animator cannot be null");
        }
    }
}
