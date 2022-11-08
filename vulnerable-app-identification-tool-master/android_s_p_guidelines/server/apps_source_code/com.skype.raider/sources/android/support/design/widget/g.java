package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;

class g extends i {
    Drawable a;
    Drawable b;
    b c;
    n d;
    private float j;
    private float k;
    private int l;
    private q m = new q();
    private boolean n;

    private abstract class a extends Animation {
        final /* synthetic */ g a;
        private float b;
        private float c;

        protected abstract float a();

        private a(g gVar) {
            this.a = gVar;
        }

        /* synthetic */ a(g x0, byte b) {
            this(x0);
        }

        public void reset() {
            super.reset();
            this.b = this.a.d.j;
            this.c = a() - this.b;
        }

        protected void applyTransformation(float interpolatedTime, Transformation t) {
            n nVar = this.a.d;
            nVar.a(this.b + (this.c * interpolatedTime), nVar.h);
        }
    }

    private class b extends a {
        final /* synthetic */ g b;

        private b(g gVar) {
            this.b = gVar;
            super(gVar, (byte) 0);
        }

        /* synthetic */ b(g x0, byte b) {
            this(x0);
        }

        protected final float a() {
            return this.b.j + this.b.k;
        }
    }

    private class c extends a {
        final /* synthetic */ g b;

        private c(g gVar) {
            this.b = gVar;
            super(gVar, (byte) 0);
        }

        /* synthetic */ c(g x0, byte b) {
            this(x0);
        }

        protected final float a() {
            return this.b.j;
        }
    }

    g(View view, o shadowViewDelegate) {
        super(view, shadowViewDelegate);
        this.l = view.getResources().getInteger(17694720);
        this.m.a(view);
        this.m.a(e, a(new b()));
        this.m.a(f, a(new b()));
        this.m.a(g, a(new c()));
    }

    void a(ColorStateList backgroundTint, Mode backgroundTintMode, int rippleColor, int borderWidth) {
        Drawable[] layers;
        this.a = android.support.v4.graphics.drawable.a.f(i.i());
        android.support.v4.graphics.drawable.a.a(this.a, backgroundTint);
        if (backgroundTintMode != null) {
            android.support.v4.graphics.drawable.a.a(this.a, backgroundTintMode);
        }
        this.b = android.support.v4.graphics.drawable.a.f(i.i());
        android.support.v4.graphics.drawable.a.a(this.b, b(rippleColor));
        android.support.v4.graphics.drawable.a.a(this.b, Mode.MULTIPLY);
        if (borderWidth > 0) {
            this.c = a(borderWidth, backgroundTint);
            layers = new Drawable[]{this.c, this.a, this.b};
        } else {
            this.c = null;
            layers = new Drawable[]{this.a, this.b};
        }
        this.d = new n(this.h.getResources(), new LayerDrawable(layers), this.i.a(), this.j, this.j + this.k);
        this.d.a();
        this.i.a(this.d);
        j();
    }

    final void a(ColorStateList tint) {
        android.support.v4.graphics.drawable.a.a(this.a, tint);
        if (this.c != null) {
            this.c.a(tint);
        }
    }

    final void a(Mode tintMode) {
        android.support.v4.graphics.drawable.a.a(this.a, tintMode);
    }

    void a(int rippleColor) {
        android.support.v4.graphics.drawable.a.a(this.b, b(rippleColor));
    }

    void a(float elevation) {
        if (this.j != elevation && this.d != null) {
            this.d.a(elevation, this.k + elevation);
            this.j = elevation;
            j();
        }
    }

    void b(float translationZ) {
        if (this.k != translationZ && this.d != null) {
            this.k = translationZ;
            n nVar = this.d;
            nVar.a(nVar.j, this.j + translationZ);
            j();
        }
    }

    void a(int[] state) {
        this.m.a(state);
    }

    void a() {
        this.m.a();
    }

    void b() {
        if (!this.n && this.h.getVisibility() == 0) {
            Animation anim = AnimationUtils.loadAnimation(this.h.getContext(), android.support.design.a.a.design_fab_out);
            anim.setInterpolator(a.b);
            anim.setDuration(200);
            anim.setAnimationListener(new a(this) {
                final /* synthetic */ a a = null;
                final /* synthetic */ g b;

                {
                    this.b = r2;
                }

                public final void onAnimationStart(Animation animation) {
                    this.b.n = true;
                }

                public final void onAnimationEnd(Animation animation) {
                    this.b.n = false;
                    this.b.h.setVisibility(8);
                }
            });
            this.h.startAnimation(anim);
        }
    }

    void c() {
        if (this.h.getVisibility() != 0 || this.n) {
            this.h.clearAnimation();
            this.h.setVisibility(0);
            Animation anim = AnimationUtils.loadAnimation(this.h.getContext(), android.support.design.a.a.design_fab_in);
            anim.setDuration(200);
            anim.setInterpolator(a.b);
            anim.setAnimationListener(new a(this) {
                final /* synthetic */ a a = null;
                final /* synthetic */ g b;

                {
                    this.b = r2;
                }

                public final void onAnimationEnd(Animation animation) {
                }
            });
            this.h.startAnimation(anim);
        }
    }

    private void j() {
        Rect rect = new Rect();
        this.d.getPadding(rect);
        this.i.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    private Animation a(Animation animation) {
        animation.setInterpolator(a.b);
        animation.setDuration((long) this.l);
        return animation;
    }

    private static ColorStateList b(int selectedColor) {
        states = new int[3][];
        int[] colors = new int[]{f, selectedColor, e};
        colors[1] = selectedColor;
        states[2] = new int[0];
        colors[2] = 0;
        return new ColorStateList(states, colors);
    }
}
