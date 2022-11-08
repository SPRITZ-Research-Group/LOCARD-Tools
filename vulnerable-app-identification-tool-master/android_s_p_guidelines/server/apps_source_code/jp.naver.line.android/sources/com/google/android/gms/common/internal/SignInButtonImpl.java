package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.core.graphics.drawable.a;
import com.google.android.gms.base.R;
import com.google.android.gms.common.util.DeviceProperties;

public final class SignInButtonImpl extends Button {
    public SignInButtonImpl(Context context) {
        this(context, null);
    }

    public SignInButtonImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842824);
    }

    public final void configure(Resources resources, SignInButtonConfig signInButtonConfig) {
        configure(resources, signInButtonConfig.getButtonSize(), signInButtonConfig.getColorScheme());
    }

    public final void configure(Resources resources, int i, int i2) {
        StringBuilder stringBuilder;
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i3 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i3);
        setMinWidth(i3);
        i3 = R.drawable.common_google_signin_btn_icon_dark;
        int i4 = R.drawable.common_google_signin_btn_icon_light;
        i3 = zaa(i2, i3, i4, i4);
        i4 = R.drawable.common_google_signin_btn_text_dark;
        int i5 = R.drawable.common_google_signin_btn_text_light;
        i4 = zaa(i2, i4, i5, i5);
        switch (i) {
            case 0:
            case 1:
                i3 = i4;
                break;
            case 2:
                break;
            default:
                stringBuilder = new StringBuilder(32);
                stringBuilder.append("Unknown button size: ");
                stringBuilder.append(i);
                throw new IllegalStateException(stringBuilder.toString());
        }
        Drawable e = a.e(resources.getDrawable(i3));
        a.a(e, resources.getColorStateList(R.color.common_google_signin_btn_tint));
        a.a(e, Mode.SRC_ATOP);
        setBackgroundDrawable(e);
        i3 = R.color.common_google_signin_btn_text_dark;
        i4 = R.color.common_google_signin_btn_text_light;
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources.getColorStateList(zaa(i2, i3, i4, i4))));
        switch (i) {
            case 0:
                setText(resources.getString(R.string.common_signin_button_text));
                break;
            case 1:
                setText(resources.getString(R.string.common_signin_button_text_long));
                break;
            case 2:
                setText(null);
                break;
            default:
                stringBuilder = new StringBuilder(32);
                stringBuilder.append("Unknown button size: ");
                stringBuilder.append(i);
                throw new IllegalStateException(stringBuilder.toString());
        }
        setTransformationMethod(null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }

    private static int zaa(int i, int i2, int i3, int i4) {
        switch (i) {
            case 0:
                return i2;
            case 1:
                return i3;
            case 2:
                return i4;
            default:
                StringBuilder stringBuilder = new StringBuilder(33);
                stringBuilder.append("Unknown color scheme: ");
                stringBuilder.append(i);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }
}
