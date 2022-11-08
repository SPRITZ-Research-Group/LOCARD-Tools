package com.facebook.ads.internal.view.component;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdIconView;

public class c extends LinearLayout {
    private AdIconView a;
    private b b;
    private LinearLayout c;

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        TextView a = this.b.a();
        if (a.getLayout().getLineEnd(a.getLineCount() - 1) < this.b.b()) {
            this.c.removeView(this.a);
            super.onMeasure(i, i2);
        }
    }
}
