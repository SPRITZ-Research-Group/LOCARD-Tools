package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

final class t extends AnimationSet implements Runnable {
    private final ViewGroup a;
    private final View b;
    private boolean c;
    private boolean d;
    private boolean e = true;

    t(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.a = viewGroup;
        this.b = view;
        addAnimation(animation);
        this.a.post(this);
    }

    public final boolean getTransformation(long j, Transformation transformation) {
        this.e = true;
        if (!this.c) {
            if (!super.getTransformation(j, transformation)) {
                this.c = true;
                ai.a(this.a, this);
            }
            return true;
        } else if (this.d) {
            return false;
        } else {
            return true;
        }
    }

    public final boolean getTransformation(long j, Transformation transformation, float f) {
        this.e = true;
        if (!this.c) {
            if (!super.getTransformation(j, transformation, f)) {
                this.c = true;
                ai.a(this.a, this);
            }
            return true;
        } else if (this.d) {
            return false;
        } else {
            return true;
        }
    }

    public final void run() {
        if (this.c || !this.e) {
            this.a.endViewTransition(this.b);
            this.d = true;
            return;
        }
        this.e = false;
        this.a.post(this);
    }
}
