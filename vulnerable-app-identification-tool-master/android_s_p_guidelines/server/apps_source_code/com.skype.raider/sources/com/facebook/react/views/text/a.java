package com.facebook.react.views.text;

import android.graphics.Paint.FontMetricsInt;
import android.text.style.LineHeightSpan;

public final class a implements LineHeightSpan {
    private final float a;

    public a(float lineHeight) {
        this.a = lineHeight;
    }

    public final void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, FontMetricsInt fm) {
        int height = fm.bottom - fm.top;
        if (((float) height) < this.a) {
            float delta = this.a - ((float) height);
            fm.top = (int) (((double) fm.top) - Math.floor((double) (delta / 2.0f)));
            fm.bottom = (int) (((double) fm.bottom) + (((double) delta) - Math.floor((double) (delta / 2.0f))));
        }
    }
}
