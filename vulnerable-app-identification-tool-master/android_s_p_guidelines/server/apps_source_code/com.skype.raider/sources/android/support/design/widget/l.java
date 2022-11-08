package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.List;

abstract class l extends x<View> {
    abstract View a(List<View> list);

    public l(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean a(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int childLpHeight = child.getLayoutParams().height;
        if (childLpHeight == -1 || childLpHeight == -2) {
            List<View> dependencies = parent.c(child);
            if (dependencies.isEmpty()) {
                return false;
            }
            View header = a(dependencies);
            if (header != null && ViewCompat.B(header)) {
                if (ViewCompat.u(header)) {
                    ViewCompat.a(child, true);
                }
                int availableHeight = MeasureSpec.getSize(parentHeightMeasureSpec);
                if (availableHeight == 0) {
                    availableHeight = parent.getHeight();
                }
                parent.a(child, parentWidthMeasureSpec, widthUsed, MeasureSpec.makeMeasureSpec(b(header) + (availableHeight - header.getMeasuredHeight()), childLpHeight == -1 ? ErrorDialogData.SUPPRESSED : Integer.MIN_VALUE), heightUsed);
                return true;
            }
        }
        return false;
    }

    int b(View v) {
        return v.getMeasuredHeight();
    }
}
