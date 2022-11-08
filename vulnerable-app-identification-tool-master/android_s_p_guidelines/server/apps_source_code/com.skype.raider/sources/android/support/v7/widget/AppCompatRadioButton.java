package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.widget.l;
import android.support.v7.appcompat.a.a;
import android.support.v7.content.res.b;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class AppCompatRadioButton extends RadioButton implements l {
    private final g a;

    public AppCompatRadioButton(Context context) {
        this(context, null);
    }

    public AppCompatRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, a.radioButtonStyle);
    }

    public AppCompatRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(an.a(context), attrs, defStyleAttr);
        this.a = new g(this);
        this.a.a(attrs, defStyleAttr);
    }

    public void setButtonDrawable(Drawable buttonDrawable) {
        super.setButtonDrawable(buttonDrawable);
        if (this.a != null) {
            this.a.a();
        }
    }

    public void setButtonDrawable(@DrawableRes int resId) {
        setButtonDrawable(b.b(getContext(), resId));
    }

    public int getCompoundPaddingLeft() {
        int value = super.getCompoundPaddingLeft();
        return this.a != null ? this.a.a(value) : value;
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setSupportButtonTintList(@Nullable ColorStateList tint) {
        if (this.a != null) {
            this.a.a(tint);
        }
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setSupportButtonTintMode(@Nullable Mode tintMode) {
        if (this.a != null) {
            this.a.a(tintMode);
        }
    }
}
