package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.view.o;
import android.support.v7.appcompat.a.a;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;

public class AppCompatButton extends Button implements o {
    private final f a;
    private final m b;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    public AppCompatButton(Context context, AttributeSet attrs) {
        this(context, attrs, a.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attrs, int defStyleAttr) {
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

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(Button.class.getName());
    }

    @RequiresApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(Button.class.getName());
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.b != null) {
            this.b.b();
        }
    }

    public void setTextSize(int unit, float size) {
        if (VERSION.SDK_INT >= 26) {
            super.setTextSize(unit, size);
        } else if (this.b != null) {
            this.b.a(unit, size);
        }
    }

    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (this.b != null && VERSION.SDK_INT < 26 && this.b.d()) {
            this.b.c();
        }
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setAutoSizeTextTypeWithDefaults(int autoSizeTextType) {
        if (VERSION.SDK_INT >= 26) {
            super.setAutoSizeTextTypeWithDefaults(autoSizeTextType);
        } else if (this.b != null) {
            this.b.a(autoSizeTextType);
        }
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithConfiguration(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
        if (VERSION.SDK_INT >= 26) {
            super.setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
        } else if (this.b != null) {
            this.b.a(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
        }
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
        if (VERSION.SDK_INT >= 26) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(presetSizes, unit);
        } else if (this.b != null) {
            this.b.a(presetSizes, unit);
        }
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public int getAutoSizeTextType() {
        if (VERSION.SDK_INT < 26) {
            return this.b != null ? this.b.e() : 0;
        } else {
            if (super.getAutoSizeTextType() == 1) {
                return 1;
            }
            return 0;
        }
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public int getAutoSizeStepGranularity() {
        if (VERSION.SDK_INT >= 26) {
            return super.getAutoSizeStepGranularity();
        }
        if (this.b != null) {
            return this.b.f();
        }
        return -1;
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public int getAutoSizeMinTextSize() {
        if (VERSION.SDK_INT >= 26) {
            return super.getAutoSizeMinTextSize();
        }
        if (this.b != null) {
            return this.b.g();
        }
        return -1;
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public int getAutoSizeMaxTextSize() {
        if (VERSION.SDK_INT >= 26) {
            return super.getAutoSizeMaxTextSize();
        }
        if (this.b != null) {
            return this.b.h();
        }
        return -1;
    }

    @RestrictTo({RestrictTo.a.LIBRARY_GROUP})
    public int[] getAutoSizeTextAvailableSizes() {
        if (VERSION.SDK_INT >= 26) {
            return super.getAutoSizeTextAvailableSizes();
        }
        if (this.b != null) {
            return this.b.i();
        }
        return new int[0];
    }

    public void setSupportAllCaps(boolean allCaps) {
        if (this.b != null) {
            this.b.a(allCaps);
        }
    }
}
