package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class ap extends AnimatorListenerAdapter {
    private final View a;
    private final View b;
    private final int c;
    private final int d;
    private int[] e = ((int[]) this.a.getTag(t.transition_position));
    private float f;
    private float g;
    private final float h;
    private final float i;

    ap(View view, View view2, int i, int i2, float f, float f2) {
        this.b = view;
        this.a = view2;
        this.c = i - Math.round(this.b.getTranslationX());
        this.d = i2 - Math.round(this.b.getTranslationY());
        this.h = f;
        this.i = f2;
        if (this.e != null) {
            this.a.setTag(t.transition_position, null);
        }
    }

    public final void onAnimationCancel(Animator animator) {
        if (this.e == null) {
            this.e = new int[2];
        }
        this.e[0] = Math.round(((float) this.c) + this.b.getTranslationX());
        this.e[1] = Math.round(((float) this.d) + this.b.getTranslationY());
        this.a.setTag(t.transition_position, this.e);
    }

    public final void onAnimationEnd(Animator animator) {
        this.b.setTranslationX(this.h);
        this.b.setTranslationY(this.i);
    }

    public final void onAnimationPause(Animator animator) {
        this.f = this.b.getTranslationX();
        this.g = this.b.getTranslationY();
        this.b.setTranslationX(this.h);
        this.b.setTranslationY(this.i);
    }

    public final void onAnimationResume(Animator animator) {
        this.b.setTranslationX(this.f);
        this.b.setTranslationY(this.g);
    }
}
