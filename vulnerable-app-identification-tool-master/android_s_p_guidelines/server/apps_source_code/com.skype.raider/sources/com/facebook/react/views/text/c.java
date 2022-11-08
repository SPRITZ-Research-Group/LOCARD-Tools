package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;

public final class c {
    public static ColorStateList a(Context context) {
        return a(context, 16842906);
    }

    public static ColorStateList b(Context context) {
        return a(context, 16842904);
    }

    public static int c(Context context) {
        return a(context, 16842905).getDefaultColor();
    }

    private static ColorStateList a(Context context, int attribute) {
        TypedArray textAppearances = null;
        try {
            textAppearances = context.getTheme().obtainStyledAttributes(new int[]{attribute});
            ColorStateList colorStateList = textAppearances.getColorStateList(0);
            return colorStateList;
        } finally {
            if (textAppearances != null) {
                textAppearances.recycle();
            }
        }
    }
}
