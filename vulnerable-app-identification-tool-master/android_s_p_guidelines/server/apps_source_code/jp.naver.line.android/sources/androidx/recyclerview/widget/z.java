package androidx.recyclerview.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final class z implements AnimatorUpdateListener {
    final /* synthetic */ x a;

    z(x xVar) {
        this.a = xVar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        this.a.a.setAlpha(floatValue);
        this.a.b.setAlpha(floatValue);
        this.a.a();
    }
}
