package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

public final class bi extends AnimatorListenerAdapter {
    final /* synthetic */ ScrollingTabContainerView a;
    private boolean b = false;
    private int c;

    protected bi(ScrollingTabContainerView scrollingTabContainerView) {
        this.a = scrollingTabContainerView;
    }

    public final void onAnimationStart(Animator animator) {
        this.a.setVisibility(0);
        this.b = false;
    }

    public final void onAnimationEnd(Animator animator) {
        if (!this.b) {
            this.a.e = null;
            this.a.setVisibility(this.c);
        }
    }

    public final void onAnimationCancel(Animator animator) {
        this.b = true;
    }
}
