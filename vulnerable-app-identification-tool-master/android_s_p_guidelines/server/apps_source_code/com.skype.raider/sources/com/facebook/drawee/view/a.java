package com.facebook.drawee.view;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import javax.annotation.Nullable;

public final class a {

    public static class a {
        public int a;
        public int b;
    }

    public static void a(a spec, float aspectRatio, @Nullable LayoutParams layoutParams, int widthPadding, int heightPadding) {
        if (aspectRatio > 0.0f && layoutParams != null) {
            if (a(layoutParams.height)) {
                spec.b = MeasureSpec.makeMeasureSpec(View.resolveSize((int) ((((float) (MeasureSpec.getSize(spec.a) - widthPadding)) / aspectRatio) + ((float) heightPadding)), spec.b), ErrorDialogData.SUPPRESSED);
            } else if (a(layoutParams.width)) {
                spec.a = MeasureSpec.makeMeasureSpec(View.resolveSize((int) ((((float) (MeasureSpec.getSize(spec.b) - heightPadding)) * aspectRatio) + ((float) widthPadding)), spec.a), ErrorDialogData.SUPPRESSED);
            }
        }
    }

    private static boolean a(int layoutDimension) {
        return layoutDimension == 0 || layoutDimension == -2;
    }
}
