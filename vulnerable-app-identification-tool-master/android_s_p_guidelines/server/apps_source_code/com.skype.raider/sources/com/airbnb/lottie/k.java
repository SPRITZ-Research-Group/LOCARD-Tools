package com.airbnb.lottie;

import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.ColorInt;

public final class k extends PorterDuffColorFilter {
    public k(@ColorInt int color) {
        super(color, Mode.SRC_ATOP);
    }
}
