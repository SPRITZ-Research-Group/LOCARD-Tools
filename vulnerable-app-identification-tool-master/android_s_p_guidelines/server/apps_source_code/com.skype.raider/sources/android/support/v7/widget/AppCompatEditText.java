package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.o;
import android.support.v7.appcompat.a.a;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

public class AppCompatEditText extends EditText implements o {
    private final f a;
    private final m b;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    public AppCompatEditText(Context context, AttributeSet attrs) {
        this(context, attrs, a.editTextStyle);
    }

    public AppCompatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(an.a(context), attrs, defStyleAttr);
        this.a = new f(this);
        this.a.a(attrs, defStyleAttr);
        this.b = m.a((TextView) this);
        this.b.a(attrs, defStyleAttr);
        this.b.a();
    }

    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (this.a != null) {
            this.a.a(resId);
        }
    }

    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
        if (this.a != null) {
            this.a.a();
        }
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList tint) {
        if (this.a != null) {
            this.a.a(tint);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public final ColorStateList a() {
        return this.a != null ? this.a.b() : null;
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable Mode tintMode) {
        if (this.a != null) {
            this.a.a(tintMode);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public final Mode d() {
        return this.a != null ? this.a.c() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.a != null) {
            this.a.d();
        }
        if (this.b != null) {
            this.b.a();
        }
    }

    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (this.b != null) {
            this.b.a(context, resId);
        }
    }
}
