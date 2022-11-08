package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.widget.g;
import defpackage.v;
import defpackage.w;

public final class af {
    private final ImageView a;
    private bs b;
    private bs c;
    private bs d;

    public af(ImageView imageView) {
        this.a = imageView;
    }

    public final void a(AttributeSet attributeSet, int i) {
        bu a = bu.a(this.a.getContext(), attributeSet, v.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null) {
                int g = a.g(v.AppCompatImageView_srcCompat, -1);
                if (g != -1) {
                    drawable = w.b(this.a.getContext(), g);
                    if (drawable != null) {
                        this.a.setImageDrawable(drawable);
                    }
                }
            }
            if (drawable != null) {
                ap.b(drawable);
            }
            if (a.h(v.AppCompatImageView_tint)) {
                g.a(this.a, a.e(v.AppCompatImageView_tint));
            }
            if (a.h(v.AppCompatImageView_tintMode)) {
                g.a(this.a, ap.a(a.a(v.AppCompatImageView_tintMode, -1), null));
            }
            a.a();
        } catch (Throwable th) {
            a.a();
        }
    }

    public final void a(int i) {
        if (i != 0) {
            Drawable b = w.b(this.a.getContext(), i);
            if (b != null) {
                ap.b(b);
            }
            this.a.setImageDrawable(b);
        } else {
            this.a.setImageDrawable(null);
        }
        d();
    }

    final boolean a() {
        return VERSION.SDK_INT < 21 || !(this.a.getBackground() instanceof RippleDrawable);
    }

    final void a(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new bs();
        }
        this.c.a = colorStateList;
        this.c.d = true;
        d();
    }

    final ColorStateList b() {
        return this.c != null ? this.c.a : null;
    }

    final void a(Mode mode) {
        if (this.c == null) {
            this.c = new bs();
        }
        this.c.b = mode;
        this.c.c = true;
        d();
    }

    final Mode c() {
        return this.c != null ? this.c.b : null;
    }

    final void d() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            ap.b(drawable);
        }
        if (drawable != null && (!e() || !a(drawable))) {
            if (this.c != null) {
                y.a(drawable, this.c, this.a.getDrawableState());
                return;
            }
            if (this.b != null) {
                y.a(drawable, this.b, this.a.getDrawableState());
            }
        }
    }

    private boolean e() {
        int i = VERSION.SDK_INT;
        return i > 21 ? this.b != null : i == 21;
    }

    private boolean a(Drawable drawable) {
        if (this.d == null) {
            this.d = new bs();
        }
        bs bsVar = this.d;
        bsVar.a();
        ColorStateList a = g.a(this.a);
        if (a != null) {
            bsVar.d = true;
            bsVar.a = a;
        }
        Mode b = g.b(this.a);
        if (b != null) {
            bsVar.c = true;
            bsVar.b = b;
        }
        if (!bsVar.d && !bsVar.c) {
            return false;
        }
        y.a(drawable, bsVar, this.a.getDrawableState());
        return true;
    }
}
