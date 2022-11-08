package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import defpackage.hs;

final class h extends AnimatorListenerAdapter {
    private final View a;
    private boolean b = false;

    h(View view) {
        this.a = view;
    }

    public final void onAnimationStart(Animator animator) {
        if (hs.v(this.a) && this.a.getLayerType() == 0) {
            this.b = true;
            this.a.setLayerType(2, null);
        }
    }

    public final void onAnimationEnd(Animator animator) {
        ba.a(this.a, 1.0f);
        if (this.b) {
            this.a.setLayerType(0, null);
        }
    }
}
