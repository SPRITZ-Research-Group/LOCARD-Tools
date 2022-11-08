package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.o;
import android.support.v4.widget.m;
import android.support.v7.appcompat.a.a;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class AppCompatImageButton extends ImageButton implements o, m {
    private final f a;
    private final i b;

    public AppCompatImageButton(Context context) {
        this(context, null);
    }

    public AppCompatImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, a.imageButtonStyle);
    }

    public AppCompatImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(an.a(context), attrs, defStyleAttr);
        this.a = new f(this);
        this.a.a(attrs, defStyleAttr);
        this.b = new i(this);
        this.b.a(attrs, defStyleAttr);
    }

    public void setImageResource(@DrawableRes int resId) {
        this.b.a(resId);
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.b != null) {
            this.b.d();
        }
    }

    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        if (this.b != null) {
            this.b.d();
        }
    }

    public void setImageIcon(@Nullable Icon icon) {
        super.setImageIcon(icon);
        if (this.b != null) {
            this.b.d();
        }
    }

    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        if (this.b != null) {
            this.b.d();
        }
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

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setSupportImageTintList(@Nullable ColorStateList tint) {
        if (this.b != null) {
            this.b.a(tint);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public final ColorStateList b() {
        return this.b != null ? this.b.b() : null;
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setSupportImageTintMode(@Nullable Mode tintMode) {
        if (this.b != null) {
            this.b.a(tintMode);
        }
    }

    @Nullable
    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public final Mode c() {
        return this.b != null ? this.b.c() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.a != null) {
            this.a.d();
        }
        if (this.b != null) {
            this.b.d();
        }
    }

    public boolean hasOverlappingRendering() {
        return this.b.a() && super.hasOverlappingRendering();
    }
}
