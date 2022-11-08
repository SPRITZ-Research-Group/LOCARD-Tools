package com.facebook.react.flat;

import android.support.v4.text.f;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import com.facebook.fbui.textlayoutbuilder.TextLayoutBuilder;
import com.facebook.fbui.textlayoutbuilder.a.a;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.b;
import javax.annotation.Nullable;

final class ab extends af implements YogaMeasureFunction {
    private static final TextLayoutBuilder d = new TextLayoutBuilder().b().c().a(new a());
    @Nullable
    private CharSequence f;
    @Nullable
    private l g;
    private float h = 1.0f;
    private float i = 0.0f;
    private int j = Integer.MAX_VALUE;
    private int k = 0;

    public ab() {
        a((YogaMeasureFunction) this);
        q().b(af.c(14.0f));
    }

    public final boolean a() {
        return false;
    }

    public final boolean b() {
        return true;
    }

    public final long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        CharSequence text = r();
        if (TextUtils.isEmpty(text)) {
            this.f = null;
            return b.a(0.0f, 0.0f);
        }
        Alignment alignment;
        int i;
        this.f = text;
        int ceil = (int) Math.ceil((double) width);
        TruncateAt truncateAt = TruncateAt.END;
        int i2 = this.j;
        boolean z = this.j == 1;
        int o = o();
        float f = this.i;
        float f2 = this.h;
        int p = p();
        Object obj = R() == YogaDirection.RTL ? 1 : null;
        switch (this.k) {
            case 3:
                alignment = Alignment.values()[obj != null ? 4 : 3];
                break;
            case 5:
                alignment = Alignment.values()[obj != null ? 3 : 4];
                break;
            case 17:
                alignment = Alignment.ALIGN_CENTER;
                break;
            default:
                alignment = Alignment.ALIGN_NORMAL;
                break;
        }
        switch (widthMode) {
            case UNDEFINED:
                i = 0;
                break;
            case EXACTLY:
                i = 1;
                break;
            case AT_MOST:
                i = 2;
                break;
            default:
                throw new IllegalStateException("Unexpected size mode: " + widthMode);
        }
        d.a(truncateAt).c(i2).a(z).a(text).a(o).a(ceil, i);
        d.b(p);
        d.a(f.c);
        d.a();
        d.a(f);
        d.b(f2);
        d.a(alignment);
        Layout layout = d.d();
        d.a(null);
        if (this.g == null || this.g.g()) {
            this.g = new l(layout);
        } else {
            this.g.a(layout);
        }
        return b.a(this.g.a(), this.g.b());
    }

    @ReactProp(defaultDouble = Double.NaN, name = "lineHeight")
    public final void setLineHeight(double lineHeight) {
        if (Double.isNaN(lineHeight)) {
            this.h = 1.0f;
            this.i = 0.0f;
        } else {
            this.h = 0.0f;
            this.i = p.a((float) lineHeight, Float.NaN);
        }
        x();
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public final void setNumberOfLines(int numberOfLines) {
        this.j = numberOfLines;
        x();
    }

    protected final int n() {
        return af.c(14.0f);
    }

    protected final void a(boolean shouldRemeasure) {
        x();
    }

    @ReactProp(name = "textAlign")
    public final void setTextAlign(@Nullable String textAlign) {
        if (textAlign == null || "auto".equals(textAlign)) {
            this.k = 0;
        } else if ("left".equals(textAlign)) {
            this.k = 3;
        } else if ("right".equals(textAlign)) {
            this.k = 5;
        } else if ("center".equals(textAlign)) {
            this.k = 17;
        } else {
            throw new n("Invalid textAlign: " + textAlign);
        }
        x();
    }
}
