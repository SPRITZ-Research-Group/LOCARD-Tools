package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@RestrictTo({a.LIBRARY_GROUP})
public class ButtonBarLayout extends LinearLayout {
    private boolean a;
    private int b = -1;
    private int c = 0;

    public ButtonBarLayout(Context context, AttributeSet attrs) {
        boolean allowStackingDefault = false;
        super(context, attrs);
        if (getResources().getConfiguration().screenHeightDp >= 320) {
            allowStackingDefault = true;
        }
        TypedArray ta = context.obtainStyledAttributes(attrs, j.ButtonBarLayout);
        this.a = ta.getBoolean(j.ButtonBarLayout_allowStacking, allowStackingDefault);
        ta.recycle();
    }

    public void setAllowStacking(boolean allowStacking) {
        if (this.a != allowStacking) {
            this.a = allowStacking;
            if (!this.a && getOrientation() == 1) {
                a(false);
            }
            requestLayout();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int initialWidthMeasureSpec;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (this.a) {
            if (widthSize > this.b && a()) {
                a(false);
            }
            this.b = widthSize;
        }
        boolean needsRemeasure = false;
        if (a() || MeasureSpec.getMode(widthMeasureSpec) != ErrorDialogData.SUPPRESSED) {
            initialWidthMeasureSpec = widthMeasureSpec;
        } else {
            initialWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, Integer.MIN_VALUE);
            needsRemeasure = true;
        }
        super.onMeasure(initialWidthMeasureSpec, heightMeasureSpec);
        if (this.a && !a()) {
            boolean z;
            if ((getMeasuredWidthAndState() & -16777216) == 16777216) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a(true);
                needsRemeasure = true;
            }
        }
        if (needsRemeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int minHeight = 0;
        int firstVisible = a(0);
        if (firstVisible >= 0) {
            View firstButton = getChildAt(firstVisible);
            LayoutParams firstParams = (LayoutParams) firstButton.getLayoutParams();
            minHeight = (((getPaddingTop() + firstButton.getMeasuredHeight()) + firstParams.topMargin) + firstParams.bottomMargin) + 0;
            if (a()) {
                int secondVisible = a(firstVisible + 1);
                if (secondVisible >= 0) {
                    minHeight += getChildAt(secondVisible).getPaddingTop() + ((int) (16.0f * getResources().getDisplayMetrics().density));
                }
            } else {
                minHeight += getPaddingBottom();
            }
        }
        if (ViewCompat.o(this) != minHeight) {
            setMinimumHeight(minHeight);
        }
    }

    private int a(int index) {
        int count = getChildCount();
        for (int i = index; i < count; i++) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
        }
        return -1;
    }

    public int getMinimumHeight() {
        return Math.max(this.c, super.getMinimumHeight());
    }

    private void a(boolean stacked) {
        setOrientation(stacked ? 1 : 0);
        setGravity(stacked ? 5 : 80);
        View spacer = findViewById(f.spacer);
        if (spacer != null) {
            spacer.setVisibility(stacked ? 8 : 4);
        }
        for (int i = getChildCount() - 2; i >= 0; i--) {
            bringChildToFront(getChildAt(i));
        }
    }

    private boolean a() {
        return getOrientation() == 1;
    }
}
