package com.airbnb.lottie.e;

import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class a extends ValueAnimator {
    private final Set<AnimatorUpdateListener> a = new CopyOnWriteArraySet();
    private final Set<AnimatorListener> b = new CopyOnWriteArraySet();

    public long getStartDelay() {
        throw new UnsupportedOperationException("LottieAnimator does not support getStartDelay.");
    }

    public void setStartDelay(long startDelay) {
        throw new UnsupportedOperationException("LottieAnimator does not support setStartDelay.");
    }

    public ValueAnimator setDuration(long duration) {
        throw new UnsupportedOperationException("LottieAnimator does not support setDuration.");
    }

    public void setInterpolator(TimeInterpolator value) {
        throw new UnsupportedOperationException("LottieAnimator does not support setInterpolator.");
    }

    public void addUpdateListener(AnimatorUpdateListener listener) {
        this.a.add(listener);
    }

    public void removeUpdateListener(AnimatorUpdateListener listener) {
        this.a.remove(listener);
    }

    public void removeAllUpdateListeners() {
        this.a.clear();
    }

    public void addListener(AnimatorListener listener) {
        this.b.add(listener);
    }

    public void removeListener(AnimatorListener listener) {
        this.b.remove(listener);
    }

    public void removeAllListeners() {
        this.b.clear();
    }

    final void a() {
        for (AnimatorListener onAnimationStart : this.b) {
            onAnimationStart.onAnimationStart(this);
        }
    }

    final void b() {
        for (AnimatorListener onAnimationRepeat : this.b) {
            onAnimationRepeat.onAnimationRepeat(this);
        }
    }

    final void c() {
        for (AnimatorListener onAnimationEnd : this.b) {
            onAnimationEnd.onAnimationEnd(this);
        }
    }

    final void d() {
        for (AnimatorListener onAnimationCancel : this.b) {
            onAnimationCancel.onAnimationCancel(this);
        }
    }

    final void e() {
        for (AnimatorUpdateListener onAnimationUpdate : this.a) {
            onAnimationUpdate.onAnimationUpdate(this);
        }
    }
}
