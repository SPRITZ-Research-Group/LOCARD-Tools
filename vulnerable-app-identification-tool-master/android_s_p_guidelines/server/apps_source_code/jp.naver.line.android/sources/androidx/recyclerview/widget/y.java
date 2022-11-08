package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class y extends AnimatorListenerAdapter {
    final /* synthetic */ x a;
    private boolean b = false;

    y(x xVar) {
        this.a = xVar;
    }

    public final void onAnimationEnd(Animator animator) {
        if (this.b) {
            this.b = false;
        } else if (((Float) this.a.i.getAnimatedValue()).floatValue() == BitmapDescriptorFactory.HUE_RED) {
            this.a.j = 0;
            this.a.a(0);
        } else {
            this.a.j = 2;
            this.a.a();
        }
    }

    public final void onAnimationCancel(Animator animator) {
        this.b = true;
    }
}
