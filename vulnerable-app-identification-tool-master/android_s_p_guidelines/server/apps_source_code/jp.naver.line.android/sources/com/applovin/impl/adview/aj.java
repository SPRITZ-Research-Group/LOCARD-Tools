package com.applovin.impl.adview;

import android.content.res.Resources;

class aj {
    public static float a(Resources resources, float f) {
        return (f * resources.getDisplayMetrics().density) + 0.5f;
    }

    public static float b(Resources resources, float f) {
        return f * resources.getDisplayMetrics().scaledDensity;
    }
}
