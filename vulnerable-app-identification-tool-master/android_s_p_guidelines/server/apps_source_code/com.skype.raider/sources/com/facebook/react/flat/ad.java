package com.facebook.react.flat;

import android.annotation.TargetApi;
import android.text.SpannableStringBuilder;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.text.h;
import com.facebook.react.views.view.c;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.b;
import javax.annotation.Nullable;

public class ad extends af implements YogaMeasureFunction {
    @Nullable
    private String d;
    private int f = -1;
    private boolean g = false;
    private int h = -1;
    @Nullable
    private EditText i;

    public final /* bridge */ /* synthetic */ void i() {
        super.i();
    }

    @ReactProp(defaultDouble = Double.NaN, name = "color")
    public /* bridge */ /* synthetic */ void setColor(double d) {
        super.setColor(d);
    }

    @ReactProp(name = "fontFamily")
    public /* bridge */ /* synthetic */ void setFontFamily(@Nullable String str) {
        super.setFontFamily(str);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "fontSize")
    public /* bridge */ /* synthetic */ void setFontSize(float f) {
        super.setFontSize(f);
    }

    @ReactProp(name = "fontStyle")
    public /* bridge */ /* synthetic */ void setFontStyle(@Nullable String str) {
        super.setFontStyle(str);
    }

    @ReactProp(name = "fontWeight")
    public /* bridge */ /* synthetic */ void setFontWeight(@Nullable String str) {
        super.setFontWeight(str);
    }

    public /* bridge */ /* synthetic */ void setOverflow(String str) {
        super.setOverflow(str);
    }

    @ReactProp(name = "textDecorationLine")
    public /* bridge */ /* synthetic */ void setTextDecorationLine(@Nullable String str) {
        super.setTextDecorationLine(str);
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public /* bridge */ /* synthetic */ void setTextShadowColor(int i) {
        super.setTextShadowColor(i);
    }

    @ReactProp(name = "textShadowOffset")
    public /* bridge */ /* synthetic */ void setTextShadowOffset(@Nullable am amVar) {
        super.setTextShadowOffset(amVar);
    }

    @ReactProp(name = "textShadowRadius")
    public /* bridge */ /* synthetic */ void setTextShadowRadius(float f) {
        super.setTextShadowRadius(f);
    }

    public ad() {
        j();
        a((YogaMeasureFunction) this);
    }

    protected final void a(boolean shouldRemeasure) {
        super.a(shouldRemeasure);
        super.i();
    }

    @TargetApi(17)
    public final void a(ae themedContext) {
        super.a(themedContext);
        this.i = new EditText(themedContext);
        this.i.setLayoutParams(new LayoutParams(-2, -2));
        f(4, (float) this.i.getPaddingStart());
        f(1, (float) this.i.getPaddingTop());
        f(5, (float) this.i.getPaddingEnd());
        f(3, (float) this.i.getPaddingBottom());
        this.i.setPadding(0, 0, 0, 0);
    }

    public long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        EditText editText = (EditText) a.a(this.i);
        int fontSize = o();
        editText.setTextSize(0, fontSize == -1 ? (float) ((int) Math.ceil((double) p.a(14.0f, Float.NaN))) : (float) fontSize);
        if (this.h != -1) {
            editText.setLines(this.h);
        }
        editText.measure(c.a(width, widthMode), c.a(height, heightMode));
        return b.a((float) editText.getMeasuredWidth(), (float) editText.getMeasuredHeight());
    }

    public final boolean a() {
        return false;
    }

    public final boolean b() {
        return true;
    }

    public void setBackgroundColor(int backgroundColor) {
    }

    public final void a(al uiViewOperationQueue) {
        super.a(uiViewOperationQueue);
        if (this.f != -1) {
            uiViewOperationQueue.a(A(), new h(r(), this.f, g(4), g(1), g(5), g(3)));
        }
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int mostRecentEventCount) {
        this.f = mostRecentEventCount;
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(int numberOfLines) {
        this.h = numberOfLines;
        a(true);
    }

    @ReactProp(name = "text")
    public void setText(@Nullable String text) {
        this.d = text;
        a(true);
    }

    public final void a(int spacingType, float padding) {
        super.a(spacingType, padding);
        this.g = true;
        x();
    }

    final boolean l() {
        return true;
    }

    final boolean m() {
        return true;
    }

    protected final void b(SpannableStringBuilder builder) {
        if (this.d != null) {
            builder.append(this.d);
        }
        super.b(builder);
    }
}
