package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

class ai implements AnimatorListener {
    private final ValueAnimator a;
    private float b;
    final float d;
    final float e;
    final float f;
    final float g;
    final cb h;
    final int i;
    final int j;
    boolean k;
    float l;
    float m;
    boolean n = false;
    boolean o = false;

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    ai(cb cbVar, int i, int i2, float f, float f2, float f3, float f4) {
        this.i = i2;
        this.j = i;
        this.h = cbVar;
        this.d = f;
        this.e = f2;
        this.f = f3;
        this.g = f4;
        this.a = ValueAnimator.ofFloat(new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f});
        this.a.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ ai a;

            {
                this.a = r1;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.a(valueAnimator.getAnimatedFraction());
            }
        });
        this.a.setTarget(cbVar.itemView);
        this.a.addListener(this);
        this.b = BitmapDescriptorFactory.HUE_RED;
    }

    public final void a(long j) {
        this.a.setDuration(j);
    }

    public final void a() {
        this.h.setIsRecyclable(false);
        this.a.start();
    }

    public final void b() {
        this.a.cancel();
    }

    public final void a(float f) {
        this.b = f;
    }

    public final void c() {
        if (this.d == this.f) {
            this.l = this.h.itemView.getTranslationX();
        } else {
            this.l = this.d + (this.b * (this.f - this.d));
        }
        if (this.e == this.g) {
            this.m = this.h.itemView.getTranslationY();
        } else {
            this.m = this.e + (this.b * (this.g - this.e));
        }
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.o) {
            this.h.setIsRecyclable(true);
        }
        this.o = true;
    }

    public void onAnimationCancel(Animator animator) {
        this.b = 1.0f;
    }
}
