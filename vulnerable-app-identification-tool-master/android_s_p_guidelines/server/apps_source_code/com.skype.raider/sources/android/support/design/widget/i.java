package android.support.design.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.GradientDrawable;
import android.support.design.a.c;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

abstract class i {
    static final int[] e = new int[]{16842919, 16842910};
    static final int[] f = new int[]{16842908, 16842910};
    static final int[] g = new int[0];
    private OnPreDrawListener a;
    final View h;
    final o i;

    interface a {
    }

    abstract void a();

    abstract void a(float f);

    abstract void a(int i);

    abstract void a(ColorStateList colorStateList);

    abstract void a(ColorStateList colorStateList, Mode mode, int i, int i2);

    abstract void a(Mode mode);

    abstract void a(int[] iArr);

    abstract void b();

    abstract void b(float f);

    abstract void c();

    i(View view, o shadowViewDelegate) {
        this.h = view;
        this.i = shadowViewDelegate;
    }

    final void f() {
        if (d()) {
            if (this.a == null) {
                this.a = new OnPreDrawListener(this) {
                    final /* synthetic */ i a;

                    {
                        this.a = r1;
                    }

                    public final boolean onPreDraw() {
                        this.a.e();
                        return true;
                    }
                };
            }
            this.h.getViewTreeObserver().addOnPreDrawListener(this.a);
        }
    }

    final void g() {
        if (this.a != null) {
            this.h.getViewTreeObserver().removeOnPreDrawListener(this.a);
            this.a = null;
        }
    }

    boolean d() {
        return false;
    }

    final b a(int borderWidth, ColorStateList backgroundTint) {
        Resources resources = this.h.getResources();
        b borderDrawable = h();
        borderDrawable.a(resources.getColor(c.design_fab_stroke_top_outer_color), resources.getColor(c.design_fab_stroke_top_inner_color), resources.getColor(c.design_fab_stroke_end_inner_color), resources.getColor(c.design_fab_stroke_end_outer_color));
        borderDrawable.a((float) borderWidth);
        borderDrawable.a(backgroundTint);
        return borderDrawable;
    }

    b h() {
        return new b();
    }

    static GradientDrawable i() {
        GradientDrawable d = new GradientDrawable();
        d.setShape(1);
        d.setColor(-1);
        return d;
    }

    void e() {
    }
}
