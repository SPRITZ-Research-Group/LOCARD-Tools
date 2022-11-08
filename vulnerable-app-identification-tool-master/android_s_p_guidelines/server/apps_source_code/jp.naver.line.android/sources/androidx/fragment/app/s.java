package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class s extends AnimatorListenerAdapter {
    View a;

    s(View view) {
        this.a = view;
    }

    public final void onAnimationStart(Animator animator) {
        this.a.setLayerType(2, null);
    }

    public final void onAnimationEnd(Animator animator) {
        this.a.setLayerType(0, null);
        animator.removeListener(this);
    }
}
