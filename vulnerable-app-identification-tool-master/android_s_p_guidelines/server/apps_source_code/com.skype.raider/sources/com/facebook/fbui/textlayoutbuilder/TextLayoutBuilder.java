package com.facebook.fbui.textlayoutbuilder;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Px;
import android.support.annotation.VisibleForTesting;
import android.support.v4.text.e;
import android.support.v4.text.f;
import android.support.v4.util.g;
import android.text.BoringLayout;
import android.text.BoringLayout.Metrics;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.ClickableSpan;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class TextLayoutBuilder {
    @VisibleForTesting
    static final g<Integer, Layout> a = new g(100);
    @VisibleForTesting
    final b b = new b();
    private Layout c = null;
    private a d;
    private boolean e = true;
    private boolean f = false;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MeasureMode {
    }

    private static class a extends TextPaint {
        private float a;
        private float b;
        private float c;
        private int d;

        public a(byte b) {
            super(1);
        }

        public a(Paint p) {
            super(p);
        }

        public final void setShadowLayer(float radius, float dx, float dy, int color) {
            this.c = radius;
            this.a = dx;
            this.b = dy;
            this.d = color;
            super.setShadowLayer(radius, dx, dy, color);
        }

        public final int hashCode() {
            Typeface tf = getTypeface();
            int hashCode = ((((((((((((((getColor() + 31) * 31) + Float.floatToIntBits(getTextSize())) * 31) + (tf != null ? tf.hashCode() : 0)) * 31) + Float.floatToIntBits(this.a)) * 31) + Float.floatToIntBits(this.b)) * 31) + Float.floatToIntBits(this.c)) * 31) + this.d) * 31) + this.linkColor;
            if (this.drawableState == null) {
                return (hashCode * 31) + 0;
            }
            for (int i : this.drawableState) {
                hashCode = (hashCode * 31) + i;
            }
            return hashCode;
        }
    }

    @VisibleForTesting
    static class b {
        TextPaint a = new a((byte) 0);
        int b;
        int c;
        CharSequence d;
        float e = 1.0f;
        float f = 0.0f;
        boolean g = true;
        TruncateAt h = null;
        boolean i = false;
        int j = Integer.MAX_VALUE;
        Alignment k = Alignment.ALIGN_NORMAL;
        e l = f.c;
        boolean m = false;

        b() {
        }

        final void a() {
            if (this.m) {
                this.a = new a(this.a);
                this.m = false;
            }
        }

        public final int hashCode() {
            int hashCode;
            int i = 1;
            int i2 = 0;
            if (this.a != null) {
                hashCode = this.a.hashCode();
            } else {
                hashCode = 0;
            }
            int floatToIntBits = (((((((((hashCode + 31) * 31) + this.b) * 31) + this.c) * 31) + Float.floatToIntBits(this.e)) * 31) + Float.floatToIntBits(this.f)) * 31;
            if (this.g) {
                hashCode = 1;
            } else {
                hashCode = 0;
            }
            floatToIntBits = (floatToIntBits + hashCode) * 31;
            if (this.h != null) {
                hashCode = this.h.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode = (floatToIntBits + hashCode) * 31;
            if (!this.i) {
                i = 0;
            }
            i = (((hashCode + i) * 31) + this.j) * 31;
            if (this.k != null) {
                hashCode = this.k.hashCode();
            } else {
                hashCode = 0;
            }
            i = (i + hashCode) * 31;
            if (this.l != null) {
                hashCode = this.l.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode = (i + hashCode) * 31;
            if (this.d != null) {
                i2 = this.d.hashCode();
            }
            return hashCode + i2;
        }
    }

    public final TextLayoutBuilder a(@Px int width, int measureMode) {
        if (!(this.b.b == width && this.b.c == measureMode)) {
            this.b.b = width;
            this.b.c = measureMode;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder a(CharSequence text) {
        if (text != this.b.d && (text == null || this.b.d == null || !text.equals(this.b.d))) {
            this.b.d = text;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder a(int size) {
        if (this.b.a.getTextSize() != ((float) size)) {
            this.b.a();
            this.b.a.setTextSize((float) size);
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder a(float spacingExtra) {
        if (this.b.f != spacingExtra) {
            this.b.f = spacingExtra;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder b(float spacingMultiplier) {
        if (this.b.e != spacingMultiplier) {
            this.b.e = spacingMultiplier;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder a() {
        if (!this.b.g) {
            this.b.g = true;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder a(Alignment alignment) {
        if (this.b.k != alignment) {
            this.b.k = alignment;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder a(e textDirection) {
        if (this.b.l != textDirection) {
            this.b.l = textDirection;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder b(int style) {
        Typeface defaultFromStyle = Typeface.defaultFromStyle(style);
        if (this.b.a.getTypeface() != defaultFromStyle) {
            this.b.a();
            this.b.a.setTypeface(defaultFromStyle);
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder a(TruncateAt ellipsize) {
        if (this.b.h != ellipsize) {
            this.b.h = ellipsize;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder a(boolean singleLine) {
        if (this.b.i != singleLine) {
            this.b.i = singleLine;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder c(int maxLines) {
        if (this.b.j != maxLines) {
            this.b.j = maxLines;
            this.c = null;
        }
        return this;
    }

    public final TextLayoutBuilder b() {
        this.e = false;
        return this;
    }

    public final TextLayoutBuilder c() {
        this.f = true;
        return this;
    }

    public final TextLayoutBuilder a(a glyphWarmer) {
        this.d = glyphWarmer;
        return this;
    }

    public final Layout d() {
        if (this.e && this.c != null) {
            return this.c;
        }
        if (TextUtils.isEmpty(this.b.d)) {
            return null;
        }
        int numLines;
        int width;
        Layout layout;
        boolean hasClickableSpans = false;
        int hashCode = -1;
        if (this.e && (this.b.d instanceof Spannable)) {
            hasClickableSpans = ((ClickableSpan[]) ((Spannable) this.b.d).getSpans(0, this.b.d.length() + -1, ClickableSpan.class)).length > 0;
        }
        if (this.e && !hasClickableSpans) {
            hashCode = this.b.hashCode();
            Layout cachedLayout = (Layout) a.a(Integer.valueOf(hashCode));
            if (cachedLayout != null) {
                return cachedLayout;
            }
        }
        Metrics metrics = null;
        if (this.b.i) {
            numLines = 1;
        } else {
            numLines = this.b.j;
        }
        if (numLines == 1) {
            metrics = BoringLayout.isBoring(this.b.d, this.b.a);
        }
        switch (this.b.c) {
            case 0:
                width = (int) Math.ceil((double) Layout.getDesiredWidth(this.b.d, this.b.a));
                break;
            case 1:
                width = this.b.b;
                break;
            case 2:
                width = Math.min((int) Math.ceil((double) Layout.getDesiredWidth(this.b.d, this.b.a)), this.b.b);
                break;
            default:
                throw new IllegalStateException("Unexpected measure mode " + this.b.c);
        }
        if (metrics != null) {
            layout = BoringLayout.make(this.b.d, this.b.a, width, this.b.k, this.b.e, this.b.f, metrics, this.b.g, this.b.h, width);
        } else {
            while (true) {
                try {
                    layout = b.a(this.b.d, this.b.d.length(), this.b.a, width, this.b.k, this.b.e, this.b.f, this.b.g, this.b.h, width, numLines, this.b.l);
                } catch (IndexOutOfBoundsException e) {
                    if (this.b.d instanceof String) {
                        throw e;
                    }
                    this.b.d = this.b.d.toString();
                }
            }
        }
        if (this.e && !hasClickableSpans) {
            this.c = layout;
            a.a(Integer.valueOf(hashCode), layout);
        }
        this.b.m = true;
        if (this.f && this.d != null) {
            this.d.a(layout);
        }
        return layout;
    }
}
