package android.support.design.widget;

import android.util.StateSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

final class q {
    private final ArrayList<a> a = new ArrayList();
    private a b = null;
    private Animation c = null;
    private WeakReference<View> d;
    private AnimationListener e = new AnimationListener(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public final void onAnimationEnd(Animation animation) {
            if (this.a.c == animation) {
                this.a.c = null;
            }
        }

        public final void onAnimationStart(Animation animation) {
        }

        public final void onAnimationRepeat(Animation animation) {
        }
    };

    static class a {
        final int[] a;
        final Animation b;

        /* synthetic */ a(int[] x0, Animation x1, byte b) {
            this(x0, x1);
        }

        private a(int[] specs, Animation Animation) {
            this.a = specs;
            this.b = Animation;
        }
    }

    q() {
    }

    public final void a(int[] specs, Animation animation) {
        a tuple = new a(specs, animation, (byte) 0);
        animation.setAnimationListener(this.e);
        this.a.add(tuple);
    }

    private View b() {
        return this.d == null ? null : (View) this.d.get();
    }

    final void a(View view) {
        View current = b();
        if (current != view) {
            if (current != null) {
                View b = b();
                int size = this.a.size();
                for (int i = 0; i < size; i++) {
                    if (b.getAnimation() == ((a) this.a.get(i)).b) {
                        b.clearAnimation();
                    }
                }
                this.d = null;
                this.b = null;
                this.c = null;
            }
            if (view != null) {
                this.d = new WeakReference(view);
            }
        }
    }

    final void a(int[] state) {
        a match = null;
        int count = this.a.size();
        for (int i = 0; i < count; i++) {
            a tuple = (a) this.a.get(i);
            if (StateSet.stateSetMatches(tuple.a, state)) {
                match = tuple;
                break;
            }
        }
        if (match != this.b) {
            View b;
            if (!(this.b == null || this.c == null)) {
                b = b();
                if (b != null && b.getAnimation() == this.c) {
                    b.clearAnimation();
                }
                this.c = null;
            }
            this.b = match;
            View view = (View) this.d.get();
            if (match != null && view != null && view.getVisibility() == 0) {
                this.c = match.b;
                b = b();
                if (b != null) {
                    b.startAnimation(this.c);
                }
            }
        }
    }

    public final void a() {
        if (this.c != null) {
            View view = b();
            if (view != null && view.getAnimation() == this.c) {
                view.clearAnimation();
            }
        }
    }
}
