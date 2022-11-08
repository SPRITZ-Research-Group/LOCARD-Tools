package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import defpackage.hs;
import defpackage.v;

final class w {
    private final View a;
    private final y b;
    private int c = -1;
    private bs d;
    private bs e;
    private bs f;

    w(View view) {
        this.a = view;
        this.b = y.a();
    }

    final void a(AttributeSet attributeSet, int i) {
        bu a = bu.a(this.a.getContext(), attributeSet, v.ViewBackgroundHelper, i, 0);
        try {
            if (a.h(v.ViewBackgroundHelper_android_background)) {
                this.c = a.g(v.ViewBackgroundHelper_android_background, -1);
                ColorStateList b = this.b.b(this.a.getContext(), this.c);
                if (b != null) {
                    b(b);
                }
            }
            if (a.h(v.ViewBackgroundHelper_backgroundTint)) {
                hs.a(this.a, a.e(v.ViewBackgroundHelper_backgroundTint));
            }
            if (a.h(v.ViewBackgroundHelper_backgroundTintMode)) {
                hs.a(this.a, ap.a(a.a(v.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            a.a();
        } catch (Throwable th) {
            a.a();
        }
    }

    final void a(int i) {
        this.c = i;
        b(this.b != null ? this.b.b(this.a.getContext(), i) : null);
        d();
    }

    final void a() {
        this.c = -1;
        b(null);
        d();
    }

    final void a(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new bs();
        }
        this.e.a = colorStateList;
        this.e.d = true;
        d();
    }

    final ColorStateList b() {
        return this.e != null ? this.e.a : null;
    }

    final void a(Mode mode) {
        if (this.e == null) {
            this.e = new bs();
        }
        this.e.b = mode;
        this.e.c = true;
        d();
    }

    final Mode c() {
        return this.e != null ? this.e.b : null;
    }

    final void d() {
        Drawable background = this.a.getBackground();
        if (background != null && (!e() || !a(background))) {
            if (this.e != null) {
                y.a(background, this.e, this.a.getDrawableState());
                return;
            }
            if (this.d != null) {
                y.a(background, this.d, this.a.getDrawableState());
            }
        }
    }

    private void b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new bs();
            }
            this.d.a = colorStateList;
            this.d.d = true;
        } else {
            this.d = null;
        }
        d();
    }

    private boolean e() {
        int i = VERSION.SDK_INT;
        return i > 21 ? this.d != null : i == 21;
    }

    private boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new bs();
        }
        bs bsVar = this.f;
        bsVar.a();
        ColorStateList x = hs.x(this.a);
        if (x != null) {
            bsVar.d = true;
            bsVar.a = x;
        }
        Mode y = hs.y(this.a);
        if (y != null) {
            bsVar.c = true;
            bsVar.b = y;
        }
        if (!bsVar.d && !bsVar.c) {
            return false;
        }
        y.a(drawable, bsVar, this.a.getDrawableState());
        return true;
    }
}
