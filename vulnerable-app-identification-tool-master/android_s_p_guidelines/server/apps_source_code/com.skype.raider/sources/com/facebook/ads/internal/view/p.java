package com.facebook.ads.internal.view;

import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class p extends TextView {
    private int a;
    private float b;
    private float c;

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.setMaxLines(this.a + 1);
        super.setTextSize(this.b);
        int i5 = i3 - i;
        int i6 = i4 - i2;
        measure(MeasureSpec.makeMeasureSpec(i5, ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(i6, 0));
        if (getMeasuredHeight() > i6) {
            float f = this.b;
            while (f > this.c) {
                f -= 0.5f;
                super.setTextSize(f);
                measure(MeasureSpec.makeMeasureSpec(i5, ErrorDialogData.SUPPRESSED), 0);
                if (getMeasuredHeight() <= i6 && getLineCount() <= this.a) {
                    break;
                }
            }
        }
        super.setMaxLines(this.a);
        setMeasuredDimension(i5, i6);
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setMaxLines(int i) {
        this.a = i;
        super.setMaxLines(i);
    }

    public void setMinTextSize(float f) {
        this.c = f;
    }

    public void setTextSize(float f) {
        this.b = f;
        super.setTextSize(f);
    }
}
