package com.facebook.ads;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import com.facebook.ads.internal.q.a.u;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class NativeAdScrollView extends LinearLayout {
    private final a a;

    private class a extends ViewPager {
        protected final void onMeasure(int i, int i2) {
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i3) {
                    i3 = measuredHeight;
                }
            }
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(i3, ErrorDialogData.SUPPRESSED));
        }
    }

    public void setInset(int i) {
        if (i > 0) {
            float f = u.b;
            int round = Math.round(((float) i) * f);
            this.a.setPadding(round, 0, round, 0);
            this.a.setPageMargin(Math.round(f * ((float) (i / 2))));
            this.a.setClipToPadding(false);
        }
    }
}
