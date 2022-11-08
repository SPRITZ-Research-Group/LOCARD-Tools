package com.facebook.react.views.view;

import android.view.View.MeasureSpec;
import com.facebook.yoga.YogaMeasureMode;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public final class c {
    public static int a(float size, YogaMeasureMode mode) {
        if (mode == YogaMeasureMode.EXACTLY) {
            return MeasureSpec.makeMeasureSpec((int) size, ErrorDialogData.SUPPRESSED);
        }
        if (mode == YogaMeasureMode.AT_MOST) {
            return MeasureSpec.makeMeasureSpec((int) size, Integer.MIN_VALUE);
        }
        return MeasureSpec.makeMeasureSpec(0, 0);
    }
}
