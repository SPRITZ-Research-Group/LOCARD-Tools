package android.support.design.widget;

import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewParent;

final class y {
    private final View a;
    private int b;
    private int c;
    private int d;
    private int e;

    public y(View view) {
        this.a = view;
    }

    public final void a() {
        this.b = this.a.getTop();
        this.c = this.a.getLeft();
        c();
    }

    private void c() {
        ViewCompat.b(this.a, this.d - (this.a.getTop() - this.b));
        ViewCompat.c(this.a, this.e - (this.a.getLeft() - this.c));
        if (VERSION.SDK_INT < 23) {
            a(this.a);
            ViewParent vp = this.a.getParent();
            if (vp instanceof View) {
                a((View) vp);
            }
        }
    }

    private static void a(View view) {
        float x = ViewCompat.l(view);
        ViewCompat.a(view, 1.0f + x);
        ViewCompat.a(view, x);
    }

    public final boolean a(int offset) {
        if (this.d == offset) {
            return false;
        }
        this.d = offset;
        c();
        return true;
    }

    public final boolean b(int offset) {
        if (this.e == offset) {
            return false;
        }
        this.e = offset;
        c();
        return true;
    }

    public final int b() {
        return this.d;
    }
}
