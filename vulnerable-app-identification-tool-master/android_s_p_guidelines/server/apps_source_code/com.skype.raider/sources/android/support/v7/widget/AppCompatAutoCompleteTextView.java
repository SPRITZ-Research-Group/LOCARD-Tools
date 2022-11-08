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
import android.support.v7.content.res.b;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class AppCompatAutoCompleteTextView extends AutoCompleteTextView implements o {
    private static final int[] a = new int[]{16843126};
    private final f b;
    private final m c;

    public AppCompatAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attrs) {
        this(context, attrs, a.autoCompleteTextViewStyle);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(an.a(context), attrs, defStyleAttr);
        aq a = aq.a(getContext(), attrs, a, defStyleAttr, 0);
        if (a.h(0)) {
            setDropDownBackgroundDrawable(a.a(0));
        }
        a.a();
        this.b = new f(this);
        this.b.a(attrs, defStyleAttr);
        this.c = m.a((TextView) this);
        this.c.a(attrs, defStyleAttr);
        this.c.a();
    }

    public void setDropDownBackgroundResource(@DrawableRes int resId) {
        setDropDownBackgroundDrawable(b.b(getContext(), resId));
    }

    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (this.b != null) {
            this.b.a(resId);
        }
    }

    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
        if (this.b != null) {
            this.b.a();
        }
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList tint) {
        if (this.b != null) {
            this.b.a(tint);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public final ColorStateList a() {
        return this.b != null ? this.b.b() : null;
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable Mode tintMode) {
        if (this.b != null) {
            this.b.a(tintMode);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public final Mode d() {
        return this.b != null ? this.b.c() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.b != null) {
            this.b.d();
        }
        if (this.c != null) {
            this.c.a();
        }
    }

    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        if (this.c != null) {
            this.c.a(context, resId);
        }
    }
}
