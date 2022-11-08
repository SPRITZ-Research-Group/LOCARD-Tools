package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.d;
import android.support.v7.appcompat.a.f;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@RestrictTo({a.LIBRARY_GROUP})
public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(@Nullable Context context) {
        super(context);
    }

    public AlertDialogLayout(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childTop;
        int dividerHeight;
        int paddingLeft = getPaddingLeft();
        int width = right - left;
        int childRight = width - getPaddingRight();
        int childSpace = (width - paddingLeft) - getPaddingRight();
        int totalLength = getMeasuredHeight();
        int count = getChildCount();
        int gravity = m();
        int minorGravity = gravity & 8388615;
        switch (gravity & 112) {
            case 16:
                childTop = getPaddingTop() + (((bottom - top) - totalLength) / 2);
                break;
            case 80:
                childTop = ((getPaddingTop() + bottom) - top) - totalLength;
                break;
            default:
                childTop = getPaddingTop();
                break;
        }
        Drawable dividerDrawable = k();
        if (dividerDrawable == null) {
            dividerHeight = 0;
        } else {
            dividerHeight = dividerDrawable.getIntrinsicHeight();
        }
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (!(child == null || child.getVisibility() == 8)) {
                int childLeft;
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                LinearLayoutCompat.a lp = (LinearLayoutCompat.a) child.getLayoutParams();
                int layoutGravity = lp.h;
                if (layoutGravity < 0) {
                    layoutGravity = minorGravity;
                }
                switch (d.a(layoutGravity, ViewCompat.f(this)) & 7) {
                    case 1:
                        childLeft = ((((childSpace - childWidth) / 2) + paddingLeft) + lp.leftMargin) - lp.rightMargin;
                        break;
                    case 5:
                        childLeft = (childRight - childWidth) - lp.rightMargin;
                        break;
                    default:
                        childLeft = paddingLeft + lp.leftMargin;
                        break;
                }
                if (a(i)) {
                    childTop += dividerHeight;
                }
                childTop += lp.topMargin;
                child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
                childTop += lp.bottomMargin + childHeight;
            }
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View childAt;
        int id;
        Object obj;
        int i;
        int i2;
        int i3;
        int combineMeasuredStates;
        int i4;
        View view = null;
        View view2 = null;
        View view3 = null;
        int childCount = getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                id = childAt.getId();
                View view4;
                if (id != f.topPanel) {
                    if (id != f.buttonPanel) {
                        if (id != f.contentPanel && id != f.customPanel) {
                            obj = null;
                            break;
                        } else if (view3 != null) {
                            obj = null;
                            break;
                        } else {
                            view3 = view2;
                            view2 = view;
                        }
                    } else {
                        view2 = view;
                        view4 = childAt;
                        childAt = view3;
                        view3 = view4;
                    }
                } else {
                    view4 = view3;
                    view3 = view2;
                    view2 = childAt;
                    childAt = view4;
                }
            } else {
                childAt = view3;
                view3 = view2;
                view2 = view;
            }
            i5++;
            view = view2;
            view2 = view3;
            view3 = childAt;
        }
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode2 = MeasureSpec.getMode(widthMeasureSpec);
        id = 0;
        i5 = getPaddingBottom() + getPaddingTop();
        if (view != null) {
            view.measure(widthMeasureSpec, 0);
            i5 += view.getMeasuredHeight();
            id = View.combineMeasuredStates(0, view.getMeasuredState());
        }
        int i6 = 0;
        if (view2 != null) {
            view2.measure(widthMeasureSpec, 0);
            childAt = view2;
            while (true) {
                i6 = ViewCompat.o(childAt);
                if (i6 <= 0) {
                    if (!(childAt instanceof ViewGroup)) {
                        break;
                    }
                    ViewGroup viewGroup = (ViewGroup) childAt;
                    if (viewGroup.getChildCount() != 1) {
                        break;
                    }
                    childAt = viewGroup.getChildAt(0);
                } else {
                    i = i6;
                    break;
                }
            }
            i = 0;
            i6 = view2.getMeasuredHeight() - i;
            i5 += i;
            id = View.combineMeasuredStates(id, view2.getMeasuredState());
            i2 = i6;
            i6 = i;
        } else {
            i2 = 0;
        }
        if (view3 != null) {
            if (mode == 0) {
                i = 0;
            } else {
                i = MeasureSpec.makeMeasureSpec(Math.max(0, size - i5), mode);
            }
            view3.measure(widthMeasureSpec, i);
            i = view3.getMeasuredHeight();
            i5 += i;
            id = View.combineMeasuredStates(id, view3.getMeasuredState());
            i3 = i;
        } else {
            i3 = 0;
        }
        i = size - i5;
        if (view2 != null) {
            i5 -= i6;
            i2 = Math.min(i, i2);
            if (i2 > 0) {
                i -= i2;
                i6 += i2;
            }
            view2.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(i6, ErrorDialogData.SUPPRESSED));
            i6 = view2.getMeasuredHeight() + i5;
            combineMeasuredStates = View.combineMeasuredStates(id, view2.getMeasuredState());
            int i7 = i;
            i = i6;
            i6 = i7;
        } else {
            i6 = i;
            combineMeasuredStates = id;
            i = i5;
        }
        if (view3 == null || i6 <= 0) {
            i4 = combineMeasuredStates;
        } else {
            i -= i3;
            view3.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(i6 + i3, mode));
            i += view3.getMeasuredHeight();
            i4 = View.combineMeasuredStates(combineMeasuredStates, view3.getMeasuredState());
        }
        combineMeasuredStates = 0;
        for (i6 = 0; i6 < childCount; i6++) {
            View childAt2 = getChildAt(i6);
            if (childAt2.getVisibility() != 8) {
                combineMeasuredStates = Math.max(combineMeasuredStates, childAt2.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(combineMeasuredStates + (getPaddingLeft() + getPaddingRight()), widthMeasureSpec, i4), View.resolveSizeAndState(i, heightMeasureSpec, 0));
        if (mode2 != ErrorDialogData.SUPPRESSED) {
            combineMeasuredStates = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), ErrorDialogData.SUPPRESSED);
            for (i2 = 0; i2 < childCount; i2++) {
                view3 = getChildAt(i2);
                if (view3.getVisibility() != 8) {
                    LinearLayoutCompat.a aVar = (LinearLayoutCompat.a) view3.getLayoutParams();
                    if (aVar.width == -1) {
                        mode = aVar.height;
                        aVar.height = view3.getMeasuredHeight();
                        measureChildWithMargins(view3, combineMeasuredStates, 0, heightMeasureSpec, 0);
                        aVar.height = mode;
                    }
                }
            }
        }
        obj = 1;
        if (obj == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
