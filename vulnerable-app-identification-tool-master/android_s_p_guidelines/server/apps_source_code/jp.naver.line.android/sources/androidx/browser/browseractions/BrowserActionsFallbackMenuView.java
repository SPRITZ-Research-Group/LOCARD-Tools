package androidx.browser.browseractions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import defpackage.bn;

public class BrowserActionsFallbackMenuView extends LinearLayout {
    private final int a = getResources().getDimensionPixelOffset(bn.browser_actions_context_menu_min_padding);
    private final int b = getResources().getDimensionPixelOffset(bn.browser_actions_context_menu_max_width);

    public BrowserActionsFallbackMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(Math.min(getResources().getDisplayMetrics().widthPixels - (this.a * 2), this.b), 1073741824), i2);
    }
}
