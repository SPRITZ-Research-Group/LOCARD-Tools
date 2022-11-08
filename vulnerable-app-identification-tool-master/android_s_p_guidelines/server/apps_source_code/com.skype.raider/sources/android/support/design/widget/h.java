package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.View;

class h extends g {
    private boolean j;

    h(View view, o shadowViewDelegate) {
        super(view, shadowViewDelegate);
    }

    boolean d() {
        return true;
    }

    final void e() {
        float rotation = this.h.getRotation();
        if (this.d != null) {
            this.d.a(-rotation);
        }
        if (this.c != null) {
            this.c.b(-rotation);
        }
    }

    final void b() {
        if (!this.j && this.h.getVisibility() == 0) {
            if (!ViewCompat.B(this.h) || this.h.isInEditMode()) {
                this.h.setVisibility(8);
            } else {
                this.h.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200).setInterpolator(a.b).setListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ a a = null;
                    final /* synthetic */ h b;

                    {
                        this.b = r2;
                    }

                    public final void onAnimationStart(Animator animation) {
                        this.b.j = true;
                        this.b.h.setVisibility(0);
                    }

                    public final void onAnimationCancel(Animator animation) {
                        this.b.j = false;
                    }

                    public final void onAnimationEnd(Animator animation) {
                        this.b.j = false;
                        this.b.h.setVisibility(8);
                    }
                });
            }
        }
    }

    final void c() {
        if (this.h.getVisibility() == 0) {
            return;
        }
        if (!ViewCompat.B(this.h) || this.h.isInEditMode()) {
            this.h.setVisibility(0);
            this.h.setAlpha(1.0f);
            this.h.setScaleY(1.0f);
            this.h.setScaleX(1.0f);
            return;
        }
        this.h.setAlpha(0.0f);
        this.h.setScaleY(0.0f);
        this.h.setScaleX(0.0f);
        this.h.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200).setInterpolator(a.b).setListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ a a = null;
            final /* synthetic */ h b;

            {
                this.b = r2;
            }

            public final void onAnimationStart(Animator animation) {
                this.b.h.setVisibility(0);
            }

            public final void onAnimationEnd(Animator animation) {
            }
        });
    }
}
