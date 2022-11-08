package com.facebook.ads.internal.view;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.facebook.ads.internal.q.a.u;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class q extends TextView {
    private float a;
    private float b = 8.0f;

    public q(Context context) {
        super(context);
        super.setSingleLine();
        super.setMaxLines(1);
        this.a = getTextSize() / u.b;
        setEllipsize(TruncateAt.END);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        for (float f = this.a; f >= this.b; f -= 0.5f) {
            super.setTextSize(f);
            measure(0, 0);
            if (getMeasuredWidth() <= i5) {
                break;
            }
        }
        if (getMeasuredWidth() > i5) {
            measure(MeasureSpec.makeMeasureSpec(measuredWidth, ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(measuredHeight, ErrorDialogData.SUPPRESSED));
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setMaxLines(int i) {
    }

    public void setMinTextSize(float f) {
        if (f <= this.a) {
            this.b = f;
        }
    }

    public void setSingleLine(boolean z) {
    }

    public void setTextSize(float f) {
        this.a = f;
        super.setTextSize(f);
    }
}
