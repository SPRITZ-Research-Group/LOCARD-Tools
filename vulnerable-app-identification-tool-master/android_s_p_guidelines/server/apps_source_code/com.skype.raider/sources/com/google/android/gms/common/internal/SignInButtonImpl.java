package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.a;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.a.a.b;
import com.google.android.gms.a.a.c;
import com.google.android.gms.common.util.g;

public final class SignInButtonImpl extends Button {
    public SignInButtonImpl(Context context) {
        this(context, null);
    }

    public SignInButtonImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842824);
    }

    private static int a(int i, int i2, int i3, int i4) {
        switch (i) {
            case 0:
                return i2;
            case 1:
                return i3;
            case 2:
                return i4;
            default:
                throw new IllegalStateException("Unknown color scheme: " + i);
        }
    }

    public final void a(Resources resources, int i, int i2) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        float f = resources.getDisplayMetrics().density;
        setMinHeight((int) ((f * 48.0f) + 0.5f));
        setMinWidth((int) ((f * 48.0f) + 0.5f));
        int a = a(i2, b.common_google_signin_btn_icon_dark, b.common_google_signin_btn_icon_light, b.common_google_signin_btn_icon_light);
        int a2 = a(i2, b.common_google_signin_btn_text_dark, b.common_google_signin_btn_text_light, b.common_google_signin_btn_text_light);
        switch (i) {
            case 0:
            case 1:
                break;
            case 2:
                a2 = a;
                break;
            default:
                throw new IllegalStateException("Unknown button size: " + i);
        }
        Drawable f2 = a.f(resources.getDrawable(a2));
        a.a(f2, resources.getColorStateList(com.google.android.gms.a.a.a.common_google_signin_btn_tint));
        a.a(f2, Mode.SRC_ATOP);
        setBackgroundDrawable(f2);
        setTextColor((ColorStateList) ab.a(resources.getColorStateList(a(i2, com.google.android.gms.a.a.a.common_google_signin_btn_text_dark, com.google.android.gms.a.a.a.common_google_signin_btn_text_light, com.google.android.gms.a.a.a.common_google_signin_btn_text_light))));
        switch (i) {
            case 0:
                setText(resources.getString(c.common_signin_button_text));
                break;
            case 1:
                setText(resources.getString(c.common_signin_button_text_long));
                break;
            case 2:
                setText(null);
                break;
            default:
                throw new IllegalStateException("Unknown button size: " + i);
        }
        setTransformationMethod(null);
        if (g.a(getContext())) {
            setGravity(19);
        }
    }
}
