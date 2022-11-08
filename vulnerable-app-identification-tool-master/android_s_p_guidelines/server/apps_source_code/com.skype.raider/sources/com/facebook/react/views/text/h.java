package com.facebook.react.views.text;

import android.text.Spannable;

public final class h {
    private final Spannable a;
    private final int b;
    private final boolean c;
    private final float d;
    private final float e;
    private final float f;
    private final float g;
    private final int h;
    private final int i;

    @Deprecated
    public h(Spannable text, int jsEventCounter, float paddingStart, float paddingTop, float paddingEnd, float paddingBottom) {
        this(text, jsEventCounter, false, paddingStart, paddingTop, paddingEnd, paddingBottom, -1, 1);
    }

    public h(Spannable text, int jsEventCounter, boolean containsImages, float paddingStart, float paddingTop, float paddingEnd, float paddingBottom, int textAlign, int textBreakStrategy) {
        this.a = text;
        this.b = jsEventCounter;
        this.c = containsImages;
        this.d = paddingStart;
        this.e = paddingTop;
        this.f = paddingEnd;
        this.g = paddingBottom;
        this.h = textAlign;
        this.i = textBreakStrategy;
    }

    public final Spannable a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final boolean c() {
        return this.c;
    }

    public final float d() {
        return this.d;
    }

    public final float e() {
        return this.e;
    }

    public final float f() {
        return this.f;
    }

    public final float g() {
        return this.g;
    }

    public final int h() {
        return this.h;
    }

    public final int i() {
        return this.i;
    }
}
