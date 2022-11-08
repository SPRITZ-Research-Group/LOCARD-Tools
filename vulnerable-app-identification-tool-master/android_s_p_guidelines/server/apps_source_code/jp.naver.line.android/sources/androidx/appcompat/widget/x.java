package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.core.graphics.drawable.a;
import androidx.core.widget.e;
import androidx.core.widget.n;
import defpackage.v;
import defpackage.w;

final class x {
    private final CompoundButton a;
    private ColorStateList b = null;
    private Mode c = null;
    private boolean d = false;
    private boolean e = false;
    private boolean f;

    x(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    final void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, v.CompoundButton, i, 0);
        try {
            CompoundButton compoundButton;
            if (obtainStyledAttributes.hasValue(v.CompoundButton_android_button)) {
                i = obtainStyledAttributes.getResourceId(v.CompoundButton_android_button, 0);
                if (i != 0) {
                    this.a.setButtonDrawable(w.b(this.a.getContext(), i));
                }
            }
            if (obtainStyledAttributes.hasValue(v.CompoundButton_buttonTint)) {
                compoundButton = this.a;
                ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(v.CompoundButton_buttonTint);
                if (VERSION.SDK_INT >= 21) {
                    compoundButton.setButtonTintList(colorStateList);
                } else if (compoundButton instanceof n) {
                    ((n) compoundButton).setSupportButtonTintList(colorStateList);
                }
            }
            if (obtainStyledAttributes.hasValue(v.CompoundButton_buttonTintMode)) {
                compoundButton = this.a;
                Mode a = ap.a(obtainStyledAttributes.getInt(v.CompoundButton_buttonTintMode, -1), null);
                if (VERSION.SDK_INT >= 21) {
                    compoundButton.setButtonTintMode(a);
                } else if (compoundButton instanceof n) {
                    ((n) compoundButton).setSupportButtonTintMode(a);
                }
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    final void a(ColorStateList colorStateList) {
        this.b = colorStateList;
        this.d = true;
        d();
    }

    final ColorStateList a() {
        return this.b;
    }

    final void a(Mode mode) {
        this.c = mode;
        this.e = true;
        d();
    }

    final Mode b() {
        return this.c;
    }

    final void c() {
        if (this.f) {
            this.f = false;
            return;
        }
        this.f = true;
        d();
    }

    private void d() {
        Drawable a = e.a(this.a);
        if (a == null) {
            return;
        }
        if (this.d || this.e) {
            a = a.e(a).mutate();
            if (this.d) {
                a.a(a, this.b);
            }
            if (this.e) {
                a.a(a, this.c);
            }
            if (a.isStateful()) {
                a.setState(this.a.getDrawableState());
            }
            this.a.setButtonDrawable(a);
        }
    }

    final int a(int i) {
        if (VERSION.SDK_INT >= 17) {
            return i;
        }
        Drawable a = e.a(this.a);
        return a != null ? i + a.getIntrinsicWidth() : i;
    }
}
