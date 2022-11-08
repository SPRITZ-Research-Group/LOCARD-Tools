package android.support.v7.view;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.s;
import android.support.v4.view.t;
import android.support.v4.view.u;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

@RestrictTo({a.LIBRARY_GROUP})
public final class h {
    final ArrayList<s> a = new ArrayList();
    t b;
    private long c = -1;
    private Interpolator d;
    private boolean e;
    private final u f = new u(this) {
        final /* synthetic */ h a;
        private boolean b = false;
        private int c = 0;

        {
            this.a = this$0;
        }

        public final void a(View view) {
            if (!this.b) {
                this.b = true;
                if (this.a.b != null) {
                    this.a.b.a(null);
                }
            }
        }

        public final void b(View view) {
            int i = this.c + 1;
            this.c = i;
            if (i == this.a.a.size()) {
                if (this.a.b != null) {
                    this.a.b.b(null);
                }
                this.c = 0;
                this.b = false;
                this.a.b();
            }
        }
    };

    public final h a(s animator) {
        if (!this.e) {
            this.a.add(animator);
        }
        return this;
    }

    public final h a(s anim1, s anim2) {
        this.a.add(anim1);
        anim2.b(anim1.a());
        this.a.add(anim2);
        return this;
    }

    public final void a() {
        if (!this.e) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                s animator = (s) it.next();
                if (this.c >= 0) {
                    animator.a(this.c);
                }
                if (this.d != null) {
                    animator.a(this.d);
                }
                if (this.b != null) {
                    animator.a(this.f);
                }
                animator.c();
            }
            this.e = true;
        }
    }

    final void b() {
        this.e = false;
    }

    public final void c() {
        if (this.e) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((s) it.next()).b();
            }
            this.e = false;
        }
    }

    public final h d() {
        if (!this.e) {
            this.c = 250;
        }
        return this;
    }

    public final h a(Interpolator interpolator) {
        if (!this.e) {
            this.d = interpolator;
        }
        return this;
    }

    public final h a(t listener) {
        if (!this.e) {
            this.b = listener;
        }
        return this;
    }
}
