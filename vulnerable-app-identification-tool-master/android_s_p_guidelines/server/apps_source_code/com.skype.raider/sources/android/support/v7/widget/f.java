package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.view.View;

final class f {
    private final View a;
    private final h b;
    private int c = -1;
    private ao d;
    private ao e;
    private ao f;

    f(View view) {
        this.a = view;
        this.b = h.a();
    }

    final void a(AttributeSet attrs, int defStyleAttr) {
        aq a = aq.a(this.a.getContext(), attrs, j.ViewBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.h(j.ViewBackgroundHelper_android_background)) {
                this.c = a.g(j.ViewBackgroundHelper_android_background, -1);
                ColorStateList tint = this.b.b(this.a.getContext(), this.c);
                if (tint != null) {
                    b(tint);
                }
            }
            if (a.h(j.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.a(this.a, a.f(j.ViewBackgroundHelper_backgroundTint));
            }
            if (a.h(j.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.a(this.a, t.a(a.a(j.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            a.a();
        } catch (Throwable th) {
            a.a();
        }
    }

    final void a(int resId) {
        this.c = resId;
        b(this.b != null ? this.b.b(this.a.getContext(), resId) : null);
        d();
    }

    final void a() {
        this.c = -1;
        b(null);
        d();
    }

    final void a(ColorStateList tint) {
        if (this.e == null) {
            this.e = new ao();
        }
        this.e.a = tint;
        this.e.d = true;
        d();
    }

    final ColorStateList b() {
        return this.e != null ? this.e.a : null;
    }

    final void a(Mode tintMode) {
        if (this.e == null) {
            this.e = new ao();
        }
        this.e.b = tintMode;
        this.e.c = true;
        d();
    }

    final Mode c() {
        return this.e != null ? this.e.b : null;
    }

    final void d() {
        boolean z = false;
        Drawable background = this.a.getBackground();
        if (background != null) {
            boolean z2;
            int i = VERSION.SDK_INT;
            if (i > 21) {
                if (this.d != null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else if (i == 21) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (this.f == null) {
                    this.f = new ao();
                }
                ao aoVar = this.f;
                aoVar.a();
                ColorStateList x = ViewCompat.x(this.a);
                if (x != null) {
                    aoVar.d = true;
                    aoVar.a = x;
                }
                Mode y = ViewCompat.y(this.a);
                if (y != null) {
                    aoVar.c = true;
                    aoVar.b = y;
                }
                if (aoVar.d || aoVar.c) {
                    h.a(background, aoVar, this.a.getDrawableState());
                    z = true;
                }
                if (z) {
                    return;
                }
            }
            if (this.e != null) {
                h.a(background, this.e, this.a.getDrawableState());
            } else if (this.d != null) {
                h.a(background, this.d, this.a.getDrawableState());
            }
        }
    }

    private void b(ColorStateList tint) {
        if (tint != null) {
            if (this.d == null) {
                this.d = new ao();
            }
            this.d.a = tint;
            this.d.d = true;
        } else {
            this.d = null;
        }
        d();
    }
}
