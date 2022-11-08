package com.facebook.react.uimanager;

import android.util.DisplayMetrics;
import android.util.TypedValue;

public final class p {
    public static float a(float value) {
        return TypedValue.applyDimension(1, value, b.a());
    }

    public static float a(float value, float maxFontScale) {
        DisplayMetrics displayMetrics = b.a();
        float scaledDensity = displayMetrics.scaledDensity;
        float currentFontScale = scaledDensity / displayMetrics.density;
        if (maxFontScale >= 1.0f && maxFontScale < currentFontScale) {
            scaledDensity = displayMetrics.density * maxFontScale;
        }
        return value * scaledDensity;
    }

    public static float b(float value) {
        return value / b.a().density;
    }
}
