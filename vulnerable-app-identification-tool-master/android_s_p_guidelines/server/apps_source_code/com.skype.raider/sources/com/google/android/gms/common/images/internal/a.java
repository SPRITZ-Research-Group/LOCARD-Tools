package com.google.android.gms.common.images.internal;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

public final class a {
    public static final ColorFilter a = new ColorMatrixColorFilter(b);
    private static final ColorMatrix b;

    static {
        ColorMatrix colorMatrix = new ColorMatrix();
        b = colorMatrix;
        colorMatrix.setSaturation(0.0f);
    }
}
