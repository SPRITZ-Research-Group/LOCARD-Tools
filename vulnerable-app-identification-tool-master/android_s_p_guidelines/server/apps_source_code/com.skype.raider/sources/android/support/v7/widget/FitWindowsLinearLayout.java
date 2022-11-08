package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.util.AttributeSet;
import android.widget.LinearLayout;

@RestrictTo({a.LIBRARY_GROUP})
public class FitWindowsLinearLayout extends LinearLayout implements v {
    private v.a a;

    public FitWindowsLinearLayout(Context context) {
        super(context);
    }

    public FitWindowsLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnFitSystemWindowsListener(v.a listener) {
        this.a = listener;
    }

    protected boolean fitSystemWindows(Rect insets) {
        if (this.a != null) {
            this.a.a(insets);
        }
        return super.fitSystemWindows(insets);
    }
}
