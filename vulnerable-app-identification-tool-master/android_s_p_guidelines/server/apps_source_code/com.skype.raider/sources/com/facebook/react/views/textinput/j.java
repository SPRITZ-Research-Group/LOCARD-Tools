package com.facebook.react.views.textinput;

import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.n;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.m;
import com.facebook.react.views.text.g;
import com.facebook.react.views.text.h;
import com.facebook.react.views.view.c;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.b;
import javax.annotation.Nullable;

@VisibleForTesting
public class j extends g implements YogaMeasureFunction {
    @Nullable
    private EditText j;
    private int k = -1;

    public j() {
        int i = VERSION.SDK_INT;
        this.g = 0;
        a((YogaMeasureFunction) this);
    }

    public final boolean c() {
        return false;
    }

    public final void a(ae themedContext) {
        super.a(themedContext);
        this.j = new EditText(E());
        this.j.setLayoutParams(new LayoutParams(-2, -2));
        f(4, (float) this.j.getPaddingStart());
        f(1, (float) this.j.getPaddingTop());
        f(5, (float) this.j.getPaddingEnd());
        f(3, (float) this.j.getPaddingBottom());
        this.j.setPadding(0, 0, 0, 0);
    }

    public long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        EditText editText = (EditText) a.a(this.j);
        editText.setTextSize(0, (float) c(Float.NaN));
        if (this.b != -1) {
            editText.setLines(this.b);
        }
        if (VERSION.SDK_INT >= 23 && editText.getBreakStrategy() != this.g) {
            editText.setBreakStrategy(this.g);
        }
        editText.measure(c.a(width, widthMode), c.a(height, heightMode));
        return b.a((float) editText.getMeasuredWidth(), (float) editText.getMeasuredHeight());
    }

    public final void a(m nativeViewHierarchyOptimizer) {
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int mostRecentEventCount) {
        this.k = mostRecentEventCount;
    }

    public void setTextBreakStrategy(@Nullable String textBreakStrategy) {
        if (VERSION.SDK_INT >= 23) {
            if (textBreakStrategy == null || "simple".equals(textBreakStrategy)) {
                this.g = 0;
            } else if ("highQuality".equals(textBreakStrategy)) {
                this.g = 1;
            } else if ("balanced".equals(textBreakStrategy)) {
                this.g = 2;
            } else {
                throw new n("Invalid textBreakStrategy: " + textBreakStrategy);
            }
        }
    }

    public final void a(al uiViewOperationQueue) {
        super.a(uiViewOperationQueue);
        if (this.k != -1) {
            uiViewOperationQueue.a(A(), new h(g.a((g) this, null), this.k, this.h, g(0), g(1), g(2), g(3), this.f, this.g));
        }
    }

    public final void a(int spacingType, float padding) {
        super.a(spacingType, padding);
        i();
    }
}
