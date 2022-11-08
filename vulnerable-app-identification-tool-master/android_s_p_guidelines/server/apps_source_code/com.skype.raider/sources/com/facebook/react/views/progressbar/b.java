package com.facebook.react.views.progressbar;

import android.util.SparseIntArray;
import android.view.View.MeasureSpec;
import android.widget.ProgressBar;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.h;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class b extends h implements YogaMeasureFunction {
    private String a = "Normal";
    private final SparseIntArray b = new SparseIntArray();
    private final SparseIntArray c = new SparseIntArray();
    private final Set<Integer> d = new HashSet();

    public b() {
        a((YogaMeasureFunction) this);
    }

    @ReactProp(name = "styleAttr")
    public void setStyle(@Nullable String style) {
        if (style == null) {
            style = "Normal";
        }
        this.a = style;
    }

    public long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        int style = ReactProgressBarViewManager.getStyleFromString(this.a);
        if (!this.d.contains(Integer.valueOf(style))) {
            ProgressBar progressBar = ReactProgressBarViewManager.createProgressBar(E(), style);
            int spec = MeasureSpec.makeMeasureSpec(-2, 0);
            progressBar.measure(spec, spec);
            this.b.put(style, progressBar.getMeasuredHeight());
            this.c.put(style, progressBar.getMeasuredWidth());
            this.d.add(Integer.valueOf(style));
        }
        return com.facebook.yoga.b.a((float) this.c.get(style), (float) this.b.get(style));
    }
}
