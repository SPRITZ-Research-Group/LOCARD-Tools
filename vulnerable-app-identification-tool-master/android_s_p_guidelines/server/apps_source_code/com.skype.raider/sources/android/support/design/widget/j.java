package android.support.design.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v4.graphics.drawable.a;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

@TargetApi(21)
final class j extends h {
    private Interpolator j;

    j(View view, o shadowViewDelegate) {
        super(view, shadowViewDelegate);
        if (!view.isInEditMode()) {
            this.j = AnimationUtils.loadInterpolator(this.h.getContext(), 17563661);
        }
    }

    final void a(ColorStateList backgroundTint, Mode backgroundTintMode, int rippleColor, int borderWidth) {
        Drawable rippleContent;
        this.a = a.f(i.i());
        a.a(this.a, backgroundTint);
        if (backgroundTintMode != null) {
            a.a(this.a, backgroundTintMode);
        }
        if (borderWidth > 0) {
            this.c = a(borderWidth, backgroundTint);
            rippleContent = new LayerDrawable(new Drawable[]{this.c, this.a});
        } else {
            this.c = null;
            rippleContent = this.a;
        }
        this.b = new RippleDrawable(ColorStateList.valueOf(rippleColor), rippleContent, null);
        this.i.a(this.b);
        this.i.a(0, 0, 0, 0);
    }

    final void a(int rippleColor) {
        if (this.b instanceof RippleDrawable) {
            ((RippleDrawable) this.b).setColor(ColorStateList.valueOf(rippleColor));
        } else {
            super.a(rippleColor);
        }
    }

    public final void a(float elevation) {
        ViewCompat.c(this.h, elevation);
    }

    final void b(float translationZ) {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(e, a(ObjectAnimator.ofFloat(this.h, "translationZ", new float[]{translationZ})));
        stateListAnimator.addState(f, a(ObjectAnimator.ofFloat(this.h, "translationZ", new float[]{translationZ})));
        stateListAnimator.addState(g, a(ObjectAnimator.ofFloat(this.h, "translationZ", new float[]{0.0f})));
        this.h.setStateListAnimator(stateListAnimator);
    }

    final void a(int[] state) {
    }

    final void a() {
    }

    final boolean d() {
        return false;
    }

    private Animator a(Animator animator) {
        animator.setInterpolator(this.j);
        return animator;
    }

    final b h() {
        return new c();
    }
}
