package android.support.v7.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.h;
import android.view.View;

public abstract class ad {
    protected final g a;
    final Rect b;
    private int c;

    public abstract int a(View view);

    public abstract void a(int i);

    public abstract int b(View view);

    public abstract int c();

    public abstract int c(View view);

    public abstract int d();

    public abstract int d(View view);

    public abstract int e();

    public abstract int e(View view);

    public abstract int f();

    public abstract int f(View view);

    public abstract int g();

    public abstract int h();

    public abstract int i();

    /* synthetic */ ad(g x0, byte b) {
        this(x0);
    }

    private ad(g layoutManager) {
        this.c = Integer.MIN_VALUE;
        this.b = new Rect();
        this.a = layoutManager;
    }

    public final void a() {
        this.c = f();
    }

    public final int b() {
        return Integer.MIN_VALUE == this.c ? 0 : f() - this.c;
    }

    public static ad a(g layoutManager, int orientation) {
        switch (orientation) {
            case 0:
                return a(layoutManager);
            case 1:
                return b(layoutManager);
            default:
                throw new IllegalArgumentException("invalid orientation");
        }
    }

    public static ad a(g layoutManager) {
        return new ad(layoutManager) {
            public final int d() {
                return this.a.v() - this.a.z();
            }

            public final int e() {
                return this.a.v();
            }

            public final void a(int amount) {
                this.a.h(amount);
            }

            public final int c() {
                return this.a.x();
            }

            public final int e(View view) {
                h params = (h) view.getLayoutParams();
                return (g.g(view) + params.leftMargin) + params.rightMargin;
            }

            public final int f(View view) {
                h params = (h) view.getLayoutParams();
                return (g.h(view) + params.topMargin) + params.bottomMargin;
            }

            public final int b(View view) {
                return g.k(view) + ((h) view.getLayoutParams()).rightMargin;
            }

            public final int a(View view) {
                return g.i(view) - ((h) view.getLayoutParams()).leftMargin;
            }

            public final int c(View view) {
                this.a.a(view, this.b);
                return this.b.right;
            }

            public final int d(View view) {
                this.a.a(view, this.b);
                return this.b.left;
            }

            public final int f() {
                return (this.a.v() - this.a.x()) - this.a.z();
            }

            public final int g() {
                return this.a.z();
            }

            public final int h() {
                return this.a.t();
            }

            public final int i() {
                return this.a.u();
            }
        };
    }

    public static ad b(g layoutManager) {
        return new ad(layoutManager) {
            public final int d() {
                return this.a.w() - this.a.A();
            }

            public final int e() {
                return this.a.w();
            }

            public final void a(int amount) {
                this.a.i(amount);
            }

            public final int c() {
                return this.a.y();
            }

            public final int e(View view) {
                h params = (h) view.getLayoutParams();
                return (g.h(view) + params.topMargin) + params.bottomMargin;
            }

            public final int f(View view) {
                h params = (h) view.getLayoutParams();
                return (g.g(view) + params.leftMargin) + params.rightMargin;
            }

            public final int b(View view) {
                return g.l(view) + ((h) view.getLayoutParams()).bottomMargin;
            }

            public final int a(View view) {
                return g.j(view) - ((h) view.getLayoutParams()).topMargin;
            }

            public final int c(View view) {
                this.a.a(view, this.b);
                return this.b.bottom;
            }

            public final int d(View view) {
                this.a.a(view, this.b);
                return this.b.top;
            }

            public final int f() {
                return (this.a.w() - this.a.y()) - this.a.A();
            }

            public final int g() {
                return this.a.A();
            }

            public final int h() {
                return this.a.u();
            }

            public final int i() {
                return this.a.t();
            }
        };
    }
}
