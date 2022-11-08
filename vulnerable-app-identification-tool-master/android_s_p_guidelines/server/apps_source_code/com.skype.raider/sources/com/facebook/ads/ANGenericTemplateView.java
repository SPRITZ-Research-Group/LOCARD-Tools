package com.facebook.ads;

import android.widget.RelativeLayout;
import com.facebook.ads.internal.n.a;

public class ANGenericTemplateView extends RelativeLayout {
    private final a a;

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.a();
    }
}
