package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import javax.annotation.Nullable;

public class b extends MetricAffectingSpan {
    private final AssetManager a;
    private final int b;
    private final int c;
    @Nullable
    private final String d;

    public b(int fontStyle, int fontWeight, @Nullable String fontFamily, AssetManager assetManager) {
        this.b = fontStyle;
        this.c = fontWeight;
        this.d = fontFamily;
        this.a = assetManager;
    }

    public void updateDrawState(TextPaint ds) {
        a(ds, this.b, this.c, this.d, this.a);
    }

    public void updateMeasureState(TextPaint paint) {
        a(paint, this.b, this.c, this.d, this.a);
    }

    private static void a(Paint paint, int style, int weight, @Nullable String family, AssetManager assetManager) {
        Typeface typeface = paint.getTypeface();
        int oldStyle;
        if (typeface == null) {
            oldStyle = 0;
        } else {
            oldStyle = typeface.getStyle();
        }
        int want = 0;
        if (weight == 1 || ((oldStyle & 1) != 0 && weight == -1)) {
            want = 1;
        }
        if (style == 2 || ((oldStyle & 2) != 0 && style == -1)) {
            want |= 2;
        }
        if (family != null) {
            typeface = d.a().a(family, want, assetManager);
        } else if (typeface != null) {
            typeface = Typeface.create(typeface, want);
        }
        if (typeface != null) {
            paint.setTypeface(typeface);
        } else {
            paint.setTypeface(Typeface.defaultFromStyle(want));
        }
    }
}
