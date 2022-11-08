package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.animation.Interpolator;

final class u extends e {
    final ValueAnimator a = new ValueAnimator();

    u() {
    }

    public final void a() {
        this.a.start();
    }

    public final boolean b() {
        return this.a.isRunning();
    }

    public final void a(Interpolator interpolator) {
        this.a.setInterpolator(interpolator);
    }

    public final void a(final b updateListener) {
        this.a.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ u b;

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateListener.a();
            }
        });
    }

    public final void a(final a listener) {
        this.a.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ u b;

            public final void onAnimationStart(Animator animator) {
            }

            public final void onAnimationEnd(Animator animator) {
                listener.a();
            }

            public final void onAnimationCancel(Animator animator) {
                listener.b();
            }
        });
    }

    public final void a(int from, int to) {
        this.a.setIntValues(new int[]{from, to});
    }

    public final int c() {
        return ((Integer) this.a.getAnimatedValue()).intValue();
    }

    public final void a(float from, float to) {
        this.a.setFloatValues(new float[]{from, to});
    }

    public final float d() {
        return ((Float) this.a.getAnimatedValue()).floatValue();
    }

    public final void a(int duration) {
        this.a.setDuration((long) duration);
    }

    public final void e() {
        this.a.cancel();
    }

    public final float f() {
        return this.a.getAnimatedFraction();
    }

    public final long g() {
        return this.a.getDuration();
    }
}
