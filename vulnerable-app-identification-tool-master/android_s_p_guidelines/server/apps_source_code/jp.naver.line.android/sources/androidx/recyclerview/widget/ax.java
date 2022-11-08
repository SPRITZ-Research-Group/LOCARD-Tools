package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;

public abstract class ax {
    protected final bj a;
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

    /* synthetic */ ax(bj bjVar, byte b) {
        this(bjVar);
    }

    private ax(bj bjVar) {
        this.c = Integer.MIN_VALUE;
        this.b = new Rect();
        this.a = bjVar;
    }

    public final void a() {
        this.c = f();
    }

    public final int b() {
        return Integer.MIN_VALUE == this.c ? 0 : f() - this.c;
    }

    public static ax a(bj bjVar, int i) {
        switch (i) {
            case 0:
                return a(bjVar);
            case 1:
                return b(bjVar);
            default:
                throw new IllegalArgumentException("invalid orientation");
        }
    }

    public static ax a(bj bjVar) {
        return new ax(bjVar) {
            public final int d() {
                return this.a.C() - this.a.G();
            }

            public final int e() {
                return this.a.C();
            }

            public final void a(int i) {
                this.a.h(i);
            }

            public final int c() {
                return this.a.E();
            }

            public final int e(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return (this.a.g(view) + layoutParams.leftMargin) + layoutParams.rightMargin;
            }

            public final int f(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return (this.a.h(view) + layoutParams.topMargin) + layoutParams.bottomMargin;
            }

            public final int b(View view) {
                return this.a.k(view) + ((LayoutParams) view.getLayoutParams()).rightMargin;
            }

            public final int a(View view) {
                return this.a.i(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
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
                return (this.a.C() - this.a.E()) - this.a.G();
            }

            public final int g() {
                return this.a.G();
            }

            public final int h() {
                return this.a.A();
            }

            public final int i() {
                return this.a.B();
            }
        };
    }

    public static ax b(bj bjVar) {
        return new ax(bjVar) {
            public final int d() {
                return this.a.D() - this.a.H();
            }

            public final int e() {
                return this.a.D();
            }

            public final void a(int i) {
                this.a.i(i);
            }

            public final int c() {
                return this.a.F();
            }

            public final int e(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return (this.a.h(view) + layoutParams.topMargin) + layoutParams.bottomMargin;
            }

            public final int f(View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                return (this.a.g(view) + layoutParams.leftMargin) + layoutParams.rightMargin;
            }

            public final int b(View view) {
                return this.a.l(view) + ((LayoutParams) view.getLayoutParams()).bottomMargin;
            }

            public final int a(View view) {
                return this.a.j(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
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
                return (this.a.D() - this.a.F()) - this.a.H();
            }

            public final int g() {
                return this.a.H();
            }

            public final int h() {
                return this.a.B();
            }

            public final int i() {
                return this.a.A();
            }
        };
    }
}
