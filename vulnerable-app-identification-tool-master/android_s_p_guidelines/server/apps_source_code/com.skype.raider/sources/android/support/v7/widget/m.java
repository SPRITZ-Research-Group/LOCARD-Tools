package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.appcompat.a.j;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

@RequiresApi(9)
class m {
    final TextView a;
    private ao b;
    private ao c;
    private ao d;
    private ao e;
    @NonNull
    private final o f;
    private int g = 0;
    private Typeface h;

    static m a(TextView textView) {
        if (VERSION.SDK_INT >= 17) {
            return new n(textView);
        }
        return new m(textView);
    }

    m(TextView view) {
        this.a = view;
        this.f = new o(this.a);
    }

    void a(AttributeSet attrs, int defStyleAttr) {
        Context context = this.a.getContext();
        h drawableManager = h.a();
        aq a = aq.a(context, attrs, j.AppCompatTextHelper, defStyleAttr, 0);
        int ap = a.g(j.AppCompatTextHelper_android_textAppearance, -1);
        if (a.h(j.AppCompatTextHelper_android_drawableLeft)) {
            this.b = a(context, drawableManager, a.g(j.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (a.h(j.AppCompatTextHelper_android_drawableTop)) {
            this.c = a(context, drawableManager, a.g(j.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (a.h(j.AppCompatTextHelper_android_drawableRight)) {
            this.d = a(context, drawableManager, a.g(j.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (a.h(j.AppCompatTextHelper_android_drawableBottom)) {
            this.e = a(context, drawableManager, a.g(j.AppCompatTextHelper_android_drawableBottom, 0));
        }
        a.a();
        boolean hasPwdTm = this.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean allCaps = false;
        boolean allCapsSet = false;
        ColorStateList textColor = null;
        ColorStateList textColorHint = null;
        ColorStateList textColorLink = null;
        if (ap != -1) {
            a = aq.a(context, ap, j.TextAppearance);
            if (!hasPwdTm && a.h(j.TextAppearance_textAllCaps)) {
                allCapsSet = true;
                allCaps = a.a(j.TextAppearance_textAllCaps, false);
            }
            a(context, a);
            if (VERSION.SDK_INT < 23) {
                if (a.h(j.TextAppearance_android_textColor)) {
                    textColor = a.f(j.TextAppearance_android_textColor);
                }
                if (a.h(j.TextAppearance_android_textColorHint)) {
                    textColorHint = a.f(j.TextAppearance_android_textColorHint);
                }
                if (a.h(j.TextAppearance_android_textColorLink)) {
                    textColorLink = a.f(j.TextAppearance_android_textColorLink);
                }
            }
            a.a();
        }
        a = aq.a(context, attrs, j.TextAppearance, defStyleAttr, 0);
        if (!hasPwdTm && a.h(j.TextAppearance_textAllCaps)) {
            allCapsSet = true;
            allCaps = a.a(j.TextAppearance_textAllCaps, false);
        }
        if (VERSION.SDK_INT < 23) {
            if (a.h(j.TextAppearance_android_textColor)) {
                textColor = a.f(j.TextAppearance_android_textColor);
            }
            if (a.h(j.TextAppearance_android_textColorHint)) {
                textColorHint = a.f(j.TextAppearance_android_textColorHint);
            }
            if (a.h(j.TextAppearance_android_textColorLink)) {
                textColorLink = a.f(j.TextAppearance_android_textColorLink);
            }
        }
        a(context, a);
        a.a();
        if (textColor != null) {
            this.a.setTextColor(textColor);
        }
        if (textColorHint != null) {
            this.a.setHintTextColor(textColorHint);
        }
        if (textColorLink != null) {
            this.a.setLinkTextColor(textColorLink);
        }
        if (!hasPwdTm && allCapsSet) {
            a(allCaps);
        }
        if (this.h != null) {
            this.a.setTypeface(this.h, this.g);
        }
        this.f.a(attrs, defStyleAttr);
        if (VERSION.SDK_INT >= 26 && this.f.a() != 0) {
            int[] autoSizeTextSizesInPx = this.f.e();
            if (autoSizeTextSizesInPx.length <= 0) {
                return;
            }
            if (((float) this.a.getAutoSizeStepGranularity()) != -1.0f) {
                this.a.setAutoSizeTextTypeUniformWithConfiguration(this.f.c(), this.f.d(), this.f.b(), 0);
            } else {
                this.a.setAutoSizeTextTypeUniformWithPresetSizes(autoSizeTextSizesInPx, 0);
            }
        }
    }

    private void a(Context context, aq a) {
        this.g = a.a(j.TextAppearance_android_textStyle, this.g);
        if (a.h(j.TextAppearance_android_fontFamily) || a.h(j.TextAppearance_fontFamily)) {
            this.h = null;
            int fontFamilyId = a.h(j.TextAppearance_android_fontFamily) ? j.TextAppearance_android_fontFamily : j.TextAppearance_fontFamily;
            if (!context.isRestricted()) {
                try {
                    this.h = a.a(fontFamilyId, this.g, this.a);
                } catch (UnsupportedOperationException e) {
                } catch (NotFoundException e2) {
                }
            }
            if (this.h == null) {
                this.h = Typeface.create(a.d(fontFamilyId), this.g);
            }
        }
    }

    final void a(Context context, int resId) {
        aq a = aq.a(context, resId, j.TextAppearance);
        if (a.h(j.TextAppearance_textAllCaps)) {
            a(a.a(j.TextAppearance_textAllCaps, false));
        }
        if (VERSION.SDK_INT < 23 && a.h(j.TextAppearance_android_textColor)) {
            ColorStateList textColor = a.f(j.TextAppearance_android_textColor);
            if (textColor != null) {
                this.a.setTextColor(textColor);
            }
        }
        a(context, a);
        a.a();
        if (this.h != null) {
            this.a.setTypeface(this.h, this.g);
        }
    }

    final void a(boolean allCaps) {
        this.a.setAllCaps(allCaps);
    }

    void a() {
        if (this.b != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
    }

    final void a(Drawable drawable, ao info) {
        if (drawable != null && info != null) {
            h.a(drawable, info, this.a.getDrawableState());
        }
    }

    protected static ao a(Context context, h drawableManager, int drawableId) {
        ColorStateList tintList = drawableManager.b(context, drawableId);
        if (tintList == null) {
            return null;
        }
        ao tintInfo = new ao();
        tintInfo.d = true;
        tintInfo.a = tintList;
        return tintInfo;
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final void b() {
        if (VERSION.SDK_INT < 26) {
            this.f.f();
        }
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final void a(int unit, float size) {
        if (VERSION.SDK_INT < 26 && !this.f.g()) {
            this.f.a(unit, size);
        }
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final void c() {
        this.f.f();
    }

    @RestrictTo({a.LIBRARY_GROUP})
    final boolean d() {
        return this.f.g();
    }

    final void a(int autoSizeTextType) {
        this.f.a(autoSizeTextType);
    }

    final void a(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
        this.f.a(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
    }

    final void a(@NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
        this.f.a(presetSizes, unit);
    }

    final int e() {
        return this.f.a();
    }

    final int f() {
        return this.f.b();
    }

    final int g() {
        return this.f.c();
    }

    final int h() {
        return this.f.d();
    }

    final int[] i() {
        return this.f.e();
    }
}
