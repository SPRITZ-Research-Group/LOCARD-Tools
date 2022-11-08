package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View.MeasureSpec;
import com.facebook.ads.internal.view.f.a;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class t extends a {
    public t(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        if (MeasureSpec.getMode(i) == ErrorDialogData.SUPPRESSED) {
            i2 = i;
        } else if (MeasureSpec.getMode(i2) == ErrorDialogData.SUPPRESSED) {
            i = i2;
        }
        super.onMeasure(i, i2);
    }
}
