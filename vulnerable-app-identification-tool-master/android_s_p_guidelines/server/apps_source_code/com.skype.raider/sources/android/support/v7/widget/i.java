package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.widget.g;
import android.support.v7.appcompat.a.j;
import android.support.v7.content.res.b;
import android.util.AttributeSet;
import android.widget.ImageView;

@RestrictTo({a.LIBRARY_GROUP})
public final class i {
    private final ImageView a;
    private ao b;
    private ao c;
    private ao d;

    public i(ImageView view) {
        this.a = view;
    }

    public final void a(AttributeSet attrs, int defStyleAttr) {
        aq a = aq.a(this.a.getContext(), attrs, j.AppCompatImageView, defStyleAttr, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null) {
                int id = a.g(j.AppCompatImageView_srcCompat, -1);
                if (id != -1) {
                    drawable = b.b(this.a.getContext(), id);
                    if (drawable != null) {
                        this.a.setImageDrawable(drawable);
                    }
                }
            }
            if (drawable != null) {
                t.b(drawable);
            }
            if (a.h(j.AppCompatImageView_tint)) {
                g.a(this.a, a.f(j.AppCompatImageView_tint));
            }
            if (a.h(j.AppCompatImageView_tintMode)) {
                g.a(this.a, t.a(a.a(j.AppCompatImageView_tintMode, -1), null));
            }
            a.a();
        } catch (Throwable th) {
            a.a();
        }
    }

    public final void a(int resId) {
        if (resId != 0) {
            Drawable d = b.b(this.a.getContext(), resId);
            if (d != null) {
                t.b(d);
            }
            this.a.setImageDrawable(d);
        } else {
            this.a.setImageDrawable(null);
        }
        d();
    }

    final boolean a() {
        Drawable background = this.a.getBackground();
        if (VERSION.SDK_INT < 21 || !(background instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }

    final void a(ColorStateList tint) {
        if (this.c == null) {
            this.c = new ao();
        }
        this.c.a = tint;
        this.c.d = true;
        d();
    }

    final ColorStateList b() {
        return this.c != null ? this.c.a : null;
    }

    final void a(Mode tintMode) {
        if (this.c == null) {
            this.c = new ao();
        }
        this.c.b = tintMode;
        this.c.c = true;
        d();
    }

    final Mode c() {
        return this.c != null ? this.c.b : null;
    }

    final void d() {
        boolean z = false;
        Drawable imageViewDrawable = this.a.getDrawable();
        if (imageViewDrawable != null) {
            t.b(imageViewDrawable);
        }
        if (imageViewDrawable != null) {
            boolean z2;
            int i = VERSION.SDK_INT;
            if (i > 21) {
                if (this.b != null) {
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
                if (this.d == null) {
                    this.d = new ao();
                }
                ao aoVar = this.d;
                aoVar.a();
                ColorStateList a = g.a(this.a);
                if (a != null) {
                    aoVar.d = true;
                    aoVar.a = a;
                }
                Mode b = g.b(this.a);
                if (b != null) {
                    aoVar.c = true;
                    aoVar.b = b;
                }
                if (aoVar.d || aoVar.c) {
                    h.a(imageViewDrawable, aoVar, this.a.getDrawableState());
                    z = true;
                }
                if (z) {
                    return;
                }
            }
            if (this.c != null) {
                h.a(imageViewDrawable, this.c, this.a.getDrawableState());
            } else if (this.b != null) {
                h.a(imageViewDrawable, this.b, this.a.getDrawableState());
            }
        }
    }
}
