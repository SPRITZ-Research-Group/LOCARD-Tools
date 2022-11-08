package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.a;
import android.support.v4.widget.c;
import android.support.v7.appcompat.a.j;
import android.support.v7.content.res.b;
import android.util.AttributeSet;
import android.widget.CompoundButton;

final class g {
    private final CompoundButton a;
    private ColorStateList b = null;
    private Mode c = null;
    private boolean d = false;
    private boolean e = false;
    private boolean f;

    g(CompoundButton view) {
        this.a = view;
    }

    final void a(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.a.getContext().obtainStyledAttributes(attrs, j.CompoundButton, defStyleAttr, 0);
        try {
            if (a.hasValue(j.CompoundButton_android_button)) {
                int resourceId = a.getResourceId(j.CompoundButton_android_button, 0);
                if (resourceId != 0) {
                    this.a.setButtonDrawable(b.b(this.a.getContext(), resourceId));
                }
            }
            if (a.hasValue(j.CompoundButton_buttonTint)) {
                c.a(this.a, a.getColorStateList(j.CompoundButton_buttonTint));
            }
            if (a.hasValue(j.CompoundButton_buttonTintMode)) {
                c.a(this.a, t.a(a.getInt(j.CompoundButton_buttonTintMode, -1), null));
            }
            a.recycle();
        } catch (Throwable th) {
            a.recycle();
        }
    }

    final void a(ColorStateList tint) {
        this.b = tint;
        this.d = true;
        b();
    }

    final void a(@Nullable Mode tintMode) {
        this.c = tintMode;
        this.e = true;
        b();
    }

    final void a() {
        if (this.f) {
            this.f = false;
            return;
        }
        this.f = true;
        b();
    }

    private void b() {
        Drawable buttonDrawable = c.a(this.a);
        if (buttonDrawable == null) {
            return;
        }
        if (this.d || this.e) {
            buttonDrawable = a.f(buttonDrawable).mutate();
            if (this.d) {
                a.a(buttonDrawable, this.b);
            }
            if (this.e) {
                a.a(buttonDrawable, this.c);
            }
            if (buttonDrawable.isStateful()) {
                buttonDrawable.setState(this.a.getDrawableState());
            }
            this.a.setButtonDrawable(buttonDrawable);
        }
    }

    final int a(int superValue) {
        if (VERSION.SDK_INT >= 17) {
            return superValue;
        }
        Drawable buttonDrawable = c.a(this.a);
        if (buttonDrawable != null) {
            return superValue + buttonDrawable.getIntrinsicWidth();
        }
        return superValue;
    }
}
