package com.facebook.react.flat;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import com.facebook.react.uimanager.w;
import javax.annotation.Nullable;

class af extends s {
    private u d = u.a;
    private ag f = ag.a;

    af() {
    }

    public void a(w child, int i) {
        super.a(child, i);
        a(true);
    }

    protected void b(SpannableStringBuilder builder) {
        int childCount = y();
        for (int i = 0; i < childCount; i++) {
            ((s) c(i)).a(builder);
        }
    }

    protected final void a(SpannableStringBuilder builder, int begin, int end, boolean isEditable) {
        this.d.c();
        int flag = isEditable ? 33 : begin == 0 ? 18 : 34;
        builder.setSpan(this.d, begin, end, flag);
        if (!(this.f.b() == 0 || this.f.a() == 0.0f)) {
            this.f.e();
            builder.setSpan(this.f, begin, end, flag);
        }
        int childCount = y();
        for (int i = 0; i < childCount; i++) {
            ((s) c(i)).a(builder, isEditable);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "fontSize")
    public void setFontSize(float fontSizeSp) {
        int fontSize;
        if (Float.isNaN(fontSizeSp)) {
            fontSize = n();
        } else {
            fontSize = c(fontSizeSp);
        }
        if (this.d.f() != fontSize) {
            q().b(fontSize);
            a(true);
        }
    }

    @ReactProp(defaultDouble = Double.NaN, name = "color")
    public void setColor(double textColor) {
        if (this.d.d() != textColor) {
            q().a(textColor);
            a(false);
        }
    }

    public void setBackgroundColor(int backgroundColor) {
        if (!a()) {
            super.setBackgroundColor(backgroundColor);
        } else if (this.d.e() != backgroundColor) {
            q().a(backgroundColor);
            a(false);
        }
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(@Nullable String fontFamily) {
        if (!TextUtils.equals(this.d.i(), fontFamily)) {
            q().a(fontFamily);
            a(true);
        }
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(@Nullable String fontWeightString) {
        int fontWeight = -1;
        if (fontWeightString != null) {
            if ("bold".equals(fontWeightString)) {
                fontWeight = 1;
            } else {
                if (!Constants.NORMAL.equals(fontWeightString)) {
                    int fontWeightNumeric;
                    if (fontWeightString.length() != 3 || !fontWeightString.endsWith("00") || fontWeightString.charAt(0) > '9' || fontWeightString.charAt(0) < '1') {
                        fontWeightNumeric = -1;
                    } else {
                        fontWeightNumeric = (fontWeightString.charAt(0) - 48) * 100;
                    }
                    if (fontWeightNumeric == -1) {
                        throw new RuntimeException("invalid font weight " + fontWeightString);
                    } else if (fontWeightNumeric >= 500) {
                        fontWeight = 1;
                    }
                }
                fontWeight = 0;
            }
        }
        if (this.d.h() != fontWeight) {
            q().d(fontWeight);
            a(true);
        }
    }

    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(@Nullable String textDecorationLineString) {
        boolean isUnderlineTextDecorationSet = false;
        boolean isLineThroughTextDecorationSet = false;
        if (textDecorationLineString != null) {
            for (String textDecorationLineSubString : textDecorationLineString.split(" ")) {
                if ("underline".equals(textDecorationLineSubString)) {
                    isUnderlineTextDecorationSet = true;
                } else if ("line-through".equals(textDecorationLineSubString)) {
                    isLineThroughTextDecorationSet = true;
                }
            }
        }
        if (isUnderlineTextDecorationSet != this.d.j() || isLineThroughTextDecorationSet != this.d.k()) {
            u span = q();
            span.a(isUnderlineTextDecorationSet);
            span.b(isLineThroughTextDecorationSet);
            a(true);
        }
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(@Nullable String fontStyleString) {
        int fontStyle;
        if (fontStyleString == null) {
            fontStyle = -1;
        } else if ("italic".equals(fontStyleString)) {
            fontStyle = 2;
        } else if (Constants.NORMAL.equals(fontStyleString)) {
            fontStyle = 0;
        } else {
            throw new RuntimeException("invalid font style " + fontStyleString);
        }
        if (this.d.g() != fontStyle) {
            q().c(fontStyle);
            a(true);
        }
    }

    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(@Nullable am offsetMap) {
        float dx = 0.0f;
        float dy = 0.0f;
        if (offsetMap != null) {
            if (offsetMap.hasKey("width")) {
                dx = p.a((float) offsetMap.getDouble("width"));
            }
            if (offsetMap.hasKey("height")) {
                dy = p.a((float) offsetMap.getDouble("height"));
            }
        }
        if (!this.f.a(dx, dy)) {
            Z().b(dx, dy);
            a(false);
        }
    }

    @ReactProp(name = "textShadowRadius")
    public void setTextShadowRadius(float textShadowRadius) {
        textShadowRadius = p.a(textShadowRadius);
        if (this.f.a() != textShadowRadius) {
            Z().a(textShadowRadius);
            a(false);
        }
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(int textShadowColor) {
        if (this.f.b() != textShadowColor) {
            Z().a(textShadowColor);
            a(false);
        }
    }

    protected final int o() {
        return this.d.f();
    }

    protected final int p() {
        int style = this.d.g();
        return style >= 0 ? style : 0;
    }

    protected int n() {
        return -1;
    }

    protected final u q() {
        if (this.d.b()) {
            this.d = this.d.a();
        }
        return this.d;
    }

    final SpannableStringBuilder r() {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        a(sb);
        a(sb, m());
        return sb;
    }

    private final ag Z() {
        if (this.f.d()) {
            this.f = this.f.c();
        }
        return this.f;
    }

    static int c(float sp) {
        return (int) Math.ceil((double) p.a(sp, Float.NaN));
    }
}
