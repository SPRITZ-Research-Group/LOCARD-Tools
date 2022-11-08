package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.d;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    private boolean a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private float g;
    private boolean h;
    private int[] i;
    private int[] j;
    private Drawable k;
    private int l;
    private int m;
    private int n;
    private int o;

    public static class a extends MarginLayoutParams {
        public float g;
        public int h;

        public a(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.h = -1;
            TypedArray a = c.obtainStyledAttributes(attrs, j.LinearLayoutCompat_Layout);
            this.g = a.getFloat(j.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.h = a.getInt(j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            a.recycle();
        }

        public a(int width, int height) {
            super(width, height);
            this.h = -1;
            this.g = 0.0f;
        }

        public a(LayoutParams p) {
            super(p);
            this.h = -1;
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return j();
    }

    public /* synthetic */ LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    protected /* synthetic */ LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return b(layoutParams);
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.a = true;
        this.b = -1;
        this.c = 0;
        this.e = 8388659;
        aq a = aq.a(context, attrs, j.LinearLayoutCompat, defStyleAttr, 0);
        int index = a.a(j.LinearLayoutCompat_android_orientation, -1);
        if (index >= 0) {
            setOrientation(index);
        }
        index = a.a(j.LinearLayoutCompat_android_gravity, -1);
        if (index >= 0) {
            setGravity(index);
        }
        boolean baselineAligned = a.a(j.LinearLayoutCompat_android_baselineAligned, true);
        if (!baselineAligned) {
            setBaselineAligned(baselineAligned);
        }
        this.g = a.e(j.LinearLayoutCompat_android_weightSum);
        this.b = a.a(j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.h = a.a(j.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a.a(j.LinearLayoutCompat_divider));
        this.n = a.a(j.LinearLayoutCompat_showDividers, 0);
        this.o = a.e(j.LinearLayoutCompat_dividerPadding, 0);
        a.a();
    }

    public void setShowDividers(int showDividers) {
        if (showDividers != this.n) {
            requestLayout();
        }
        this.n = showDividers;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public final Drawable k() {
        return this.k;
    }

    public void setDividerDrawable(Drawable divider) {
        boolean z = false;
        if (divider != this.k) {
            this.k = divider;
            if (divider != null) {
                this.l = divider.getIntrinsicWidth();
                this.m = divider.getIntrinsicHeight();
            } else {
                this.l = 0;
                this.m = 0;
            }
            if (divider == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int padding) {
        this.o = padding;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final int l() {
        return this.l;
    }

    protected void onDraw(Canvas canvas) {
        if (this.k != null) {
            int childCount;
            int i;
            View childAt;
            int height;
            a aVar;
            if (this.d == 1) {
                childCount = getChildCount();
                i = 0;
                while (i < childCount) {
                    View childAt2 = getChildAt(i);
                    if (!(childAt2 == null || childAt2.getVisibility() == 8 || !a(i))) {
                        a(canvas, (childAt2.getTop() - ((a) childAt2.getLayoutParams()).topMargin) - this.m);
                    }
                    i++;
                }
                if (a(childCount)) {
                    childAt = getChildAt(childCount - 1);
                    if (childAt == null) {
                        height = (getHeight() - getPaddingBottom()) - this.m;
                    } else {
                        aVar = (a) childAt.getLayoutParams();
                        height = aVar.bottomMargin + childAt.getBottom();
                    }
                    a(canvas, height);
                    return;
                }
                return;
            }
            childCount = getChildCount();
            boolean a = ax.a(this);
            i = 0;
            while (i < childCount) {
                View childAt3 = getChildAt(i);
                if (!(childAt3 == null || childAt3.getVisibility() == 8 || !a(i))) {
                    aVar = (a) childAt3.getLayoutParams();
                    if (a) {
                        height = aVar.rightMargin + childAt3.getRight();
                    } else {
                        height = (childAt3.getLeft() - aVar.leftMargin) - this.l;
                    }
                    b(canvas, height);
                }
                i++;
            }
            if (a(childCount)) {
                childAt = getChildAt(childCount - 1);
                if (childAt != null) {
                    aVar = (a) childAt.getLayoutParams();
                    if (a) {
                        height = (childAt.getLeft() - aVar.leftMargin) - this.l;
                    } else {
                        height = aVar.rightMargin + childAt.getRight();
                    }
                } else if (a) {
                    height = getPaddingLeft();
                } else {
                    height = (getWidth() - getPaddingRight()) - this.l;
                }
                b(canvas, height);
            }
        }
    }

    private void a(Canvas canvas, int top) {
        this.k.setBounds(getPaddingLeft() + this.o, top, (getWidth() - getPaddingRight()) - this.o, this.m + top);
        this.k.draw(canvas);
    }

    private void b(Canvas canvas, int left) {
        this.k.setBounds(left, getPaddingTop() + this.o, this.l + left, (getHeight() - getPaddingBottom()) - this.o);
        this.k.draw(canvas);
    }

    public void setBaselineAligned(boolean baselineAligned) {
        this.a = baselineAligned;
    }

    public void setMeasureWithLargestChildEnabled(boolean enabled) {
        this.h = enabled;
    }

    public int getBaseline() {
        if (this.b < 0) {
            return super.getBaseline();
        }
        if (getChildCount() <= this.b) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View child = getChildAt(this.b);
        int childBaseline = child.getBaseline();
        if (childBaseline != -1) {
            int childTop = this.c;
            if (this.d == 1) {
                int majorGravity = this.e & 112;
                if (majorGravity != 48) {
                    switch (majorGravity) {
                        case 16:
                            childTop += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f) / 2;
                            break;
                        case 80:
                            childTop = ((getBottom() - getTop()) - getPaddingBottom()) - this.f;
                            break;
                    }
                }
            }
            return (((a) child.getLayoutParams()).topMargin + childTop) + childBaseline;
        } else if (this.b == 0) {
            return -1;
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.b = i;
    }

    public void setWeightSum(float weightSum) {
        this.g = Math.max(0.0f, weightSum);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.d == 1) {
            int i;
            int i2;
            int i3;
            int i4;
            int measuredWidth;
            int max;
            int i5;
            int i6;
            a aVar;
            this.f = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            Object obj = 1;
            float f = 0.0f;
            int childCount = getChildCount();
            int mode = MeasureSpec.getMode(widthMeasureSpec);
            int mode2 = MeasureSpec.getMode(heightMeasureSpec);
            Object obj2 = null;
            Object obj3 = null;
            int i11 = this.b;
            boolean z = this.h;
            int i12 = Integer.MIN_VALUE;
            int i13 = 0;
            while (i13 < childCount) {
                View childAt = getChildAt(i13);
                if (childAt == null) {
                    this.f += 0;
                    i = i13;
                } else {
                    Object obj4;
                    int combineMeasuredStates;
                    Object obj5;
                    float f2;
                    Object obj6;
                    if (childAt.getVisibility() != 8) {
                        Object obj7;
                        if (a(i13)) {
                            this.f += this.m;
                        }
                        a aVar2 = (a) childAt.getLayoutParams();
                        float f3 = f + aVar2.g;
                        if (mode2 == 1073741824 && aVar2.height == 0 && aVar2.g > 0.0f) {
                            i = this.f;
                            this.f = Math.max(i, (aVar2.topMargin + i) + aVar2.bottomMargin);
                            i2 = i12;
                            obj7 = 1;
                        } else {
                            i = Integer.MIN_VALUE;
                            if (aVar2.height == 0 && aVar2.g > 0.0f) {
                                i = 0;
                                aVar2.height = -2;
                            }
                            int i14 = i;
                            a(childAt, widthMeasureSpec, 0, heightMeasureSpec, f3 == 0.0f ? this.f : 0);
                            if (i14 != Integer.MIN_VALUE) {
                                aVar2.height = i14;
                            }
                            i = childAt.getMeasuredHeight();
                            i3 = this.f;
                            this.f = Math.max(i3, (((i3 + i) + aVar2.topMargin) + aVar2.bottomMargin) + 0);
                            if (z) {
                                i2 = Math.max(i, i12);
                                obj7 = obj3;
                            } else {
                                i2 = i12;
                                obj7 = obj3;
                            }
                        }
                        if (i11 >= 0 && i11 == i13 + 1) {
                            this.c = this.f;
                        }
                        if (i13 >= i11 || aVar2.g <= 0.0f) {
                            Object obj8 = null;
                            if (mode == 1073741824 || aVar2.width != -1) {
                                obj4 = obj2;
                            } else {
                                obj4 = 1;
                                obj8 = 1;
                            }
                            i4 = aVar2.leftMargin + aVar2.rightMargin;
                            measuredWidth = childAt.getMeasuredWidth() + i4;
                            max = Math.max(i7, measuredWidth);
                            combineMeasuredStates = View.combineMeasuredStates(i8, childAt.getMeasuredState());
                            obj5 = (obj == null || aVar2.width != -1) ? null : 1;
                            if (aVar2.g > 0.0f) {
                                if (obj8 != null) {
                                    i = i4;
                                } else {
                                    i = measuredWidth;
                                }
                                f2 = f3;
                                obj6 = obj5;
                                i5 = i9;
                                obj5 = obj7;
                                i12 = max;
                                int i15 = i2;
                                i2 = Math.max(i10, i);
                                i = i15;
                            } else {
                                if (obj8 == null) {
                                    i4 = measuredWidth;
                                }
                                i = Math.max(i9, i4);
                                f2 = f3;
                                obj6 = obj5;
                                i5 = i;
                                obj5 = obj7;
                                i = i2;
                                i2 = i10;
                                i12 = max;
                            }
                        } else {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                    }
                    i = i12;
                    obj5 = obj3;
                    f2 = f;
                    obj6 = obj;
                    i2 = i10;
                    i5 = i9;
                    obj4 = obj2;
                    i12 = i7;
                    combineMeasuredStates = i8;
                    obj = obj6;
                    i10 = i2;
                    i9 = i5;
                    i8 = combineMeasuredStates;
                    i7 = i12;
                    i12 = i;
                    obj2 = obj4;
                    i = i13 + 0;
                    f = f2;
                    obj3 = obj5;
                }
                i13 = i + 1;
            }
            if (this.f > 0 && a(childCount)) {
                this.f += this.m;
            }
            if (z && (mode2 == Integer.MIN_VALUE || mode2 == 0)) {
                this.f = 0;
                i6 = 0;
                while (i6 < childCount) {
                    View childAt2 = getChildAt(i6);
                    if (childAt2 == null) {
                        this.f += 0;
                        i = i6;
                    } else if (childAt2.getVisibility() == 8) {
                        i = i6 + 0;
                    } else {
                        aVar = (a) childAt2.getLayoutParams();
                        i4 = this.f;
                        this.f = Math.max(i4, (aVar.bottomMargin + ((i4 + i12) + aVar.topMargin)) + 0);
                        i = i6;
                    }
                    i6 = i + 1;
                }
            }
            this.f += getPaddingTop() + getPaddingBottom();
            i13 = View.resolveSizeAndState(Math.max(this.f, getSuggestedMinimumHeight()), heightMeasureSpec, 0);
            i4 = (16777215 & i13) - this.f;
            int i16;
            if (obj3 != null || (i4 != 0 && f > 0.0f)) {
                if (this.g > 0.0f) {
                    f = this.g;
                }
                this.f = 0;
                i10 = 0;
                obj3 = obj;
                i16 = i9;
                i12 = i8;
                int i17 = i7;
                while (i10 < childCount) {
                    float f4;
                    Object obj9;
                    View childAt3 = getChildAt(i10);
                    if (childAt3.getVisibility() != 8) {
                        aVar = (a) childAt3.getLayoutParams();
                        float f5 = aVar.g;
                        if (f5 > 0.0f) {
                            View view;
                            i6 = (int) ((((float) i4) * f5) / f);
                            f5 = f - f5;
                            i5 = i4 - i6;
                            i3 = getChildMeasureSpec(widthMeasureSpec, ((getPaddingLeft() + getPaddingRight()) + aVar.leftMargin) + aVar.rightMargin, aVar.width);
                            if (aVar.height != 0 || mode2 != 1073741824) {
                                i6 += childAt3.getMeasuredHeight();
                                if (i6 < 0) {
                                    i6 = 0;
                                }
                                view = childAt3;
                            } else if (i6 > 0) {
                                view = childAt3;
                            } else {
                                i6 = 0;
                                view = childAt3;
                            }
                            view.measure(i3, MeasureSpec.makeMeasureSpec(i6, ErrorDialogData.SUPPRESSED));
                            i3 = i5;
                            i4 = View.combineMeasuredStates(i12, childAt3.getMeasuredState() & -256);
                            f4 = f5;
                        } else {
                            f4 = f;
                            i3 = i4;
                            i4 = i12;
                        }
                        i2 = aVar.leftMargin + aVar.rightMargin;
                        i5 = childAt3.getMeasuredWidth() + i2;
                        i12 = Math.max(i17, i5);
                        obj = (mode == 1073741824 || aVar.width != -1) ? null : 1;
                        if (obj == null) {
                            i2 = i5;
                        }
                        i5 = Math.max(i16, i2);
                        obj9 = (obj3 == null || aVar.width != -1) ? null : 1;
                        max = this.f;
                        this.f = Math.max(max, (aVar.bottomMargin + ((childAt3.getMeasuredHeight() + max) + aVar.topMargin)) + 0);
                        i = i5;
                    } else {
                        f4 = f;
                        obj9 = obj3;
                        i = i16;
                        measuredWidth = i17;
                        i3 = i4;
                    }
                    measuredWidth = i12;
                    i10++;
                    obj3 = obj9;
                    i16 = i;
                    i12 = i4;
                    i17 = measuredWidth;
                    i4 = i3;
                    f = f4;
                }
                this.f += getPaddingTop() + getPaddingBottom();
                i = i16;
                i8 = i12;
                i6 = i17;
                obj = obj3;
            } else {
                i16 = Math.max(i9, i10);
                if (z && mode2 != 1073741824) {
                    i = 0;
                    while (true) {
                        i6 = i;
                        if (i6 >= childCount) {
                            break;
                        }
                        View childAt4 = getChildAt(i6);
                        if (!(childAt4 == null || childAt4.getVisibility() == 8 || ((a) childAt4.getLayoutParams()).g <= 0.0f)) {
                            childAt4.measure(MeasureSpec.makeMeasureSpec(childAt4.getMeasuredWidth(), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(i12, ErrorDialogData.SUPPRESSED));
                        }
                        i = i6 + 1;
                    }
                }
                i = i16;
                i6 = i7;
            }
            if (obj != null || mode == 1073741824) {
                i = i6;
            }
            setMeasuredDimension(View.resolveSizeAndState(Math.max(i + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), widthMeasureSpec, i8), i13);
            if (obj2 != null) {
                a(childCount, heightMeasureSpec);
                return;
            }
            return;
        }
        b(widthMeasureSpec, heightMeasureSpec);
    }

    protected final boolean a(int childIndex) {
        if (childIndex == 0) {
            if ((this.n & 1) != 0) {
                return true;
            }
            return false;
        } else if (childIndex == getChildCount()) {
            if ((this.n & 4) == 0) {
                return false;
            }
            return true;
        } else if ((this.n & 2) == 0) {
            return false;
        } else {
            for (int i = childIndex - 1; i >= 0; i--) {
                if (getChildAt(i).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    private void a(int count, int heightMeasureSpec) {
        int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), ErrorDialogData.SUPPRESSED);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                a lp = (a) child.getLayoutParams();
                if (lp.width == -1) {
                    int oldHeight = lp.height;
                    lp.height = child.getMeasuredHeight();
                    measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
                    lp.height = oldHeight;
                }
            }
        }
    }

    private void b(int widthMeasureSpec, int heightMeasureSpec) {
        View child;
        a lp;
        int totalLength;
        int childWidth;
        boolean matchHeightLocally;
        int margin;
        int childHeight;
        int childBaseline;
        int i;
        int index;
        this.f = 0;
        int maxHeight = 0;
        int childState = 0;
        int alternativeMaxHeight = 0;
        int weightedMaxHeight = 0;
        boolean allFillParent = true;
        float totalWeight = 0.0f;
        int count = getChildCount();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        boolean matchHeight = false;
        boolean skippedMeasure = false;
        if (this.i == null || this.j == null) {
            this.i = new int[4];
            this.j = new int[4];
        }
        int[] maxAscent = this.i;
        int[] maxDescent = this.j;
        maxAscent[3] = -1;
        maxAscent[2] = -1;
        maxAscent[1] = -1;
        maxAscent[0] = -1;
        maxDescent[3] = -1;
        maxDescent[2] = -1;
        maxDescent[1] = -1;
        maxDescent[0] = -1;
        boolean baselineAligned = this.a;
        boolean useLargestChild = this.h;
        boolean isExactly = widthMode == 1073741824;
        int largestChildWidth = Integer.MIN_VALUE;
        int i2 = 0;
        while (i2 < count) {
            child = getChildAt(i2);
            if (child == null) {
                this.f += 0;
            } else {
                if (child.getVisibility() != 8) {
                    if (a(i2)) {
                        this.f += this.l;
                    }
                    lp = (a) child.getLayoutParams();
                    totalWeight += lp.g;
                    if (widthMode == 1073741824 && lp.width == 0 && lp.g > 0.0f) {
                        if (isExactly) {
                            this.f += lp.leftMargin + lp.rightMargin;
                        } else {
                            totalLength = this.f;
                            this.f = Math.max(totalLength, (lp.leftMargin + totalLength) + lp.rightMargin);
                        }
                        if (baselineAligned) {
                            int freeSpec = MeasureSpec.makeMeasureSpec(0, 0);
                            child.measure(freeSpec, freeSpec);
                        } else {
                            skippedMeasure = true;
                        }
                    } else {
                        int oldWidth = Integer.MIN_VALUE;
                        if (lp.width == 0 && lp.g > 0.0f) {
                            oldWidth = 0;
                            lp.width = -2;
                        }
                        a(child, widthMeasureSpec, totalWeight == 0.0f ? this.f : 0, heightMeasureSpec, 0);
                        if (oldWidth != Integer.MIN_VALUE) {
                            lp.width = oldWidth;
                        }
                        childWidth = child.getMeasuredWidth();
                        if (isExactly) {
                            this.f += ((lp.leftMargin + childWidth) + lp.rightMargin) + 0;
                        } else {
                            totalLength = this.f;
                            this.f = Math.max(totalLength, (((totalLength + childWidth) + lp.leftMargin) + lp.rightMargin) + 0);
                        }
                        if (useLargestChild) {
                            largestChildWidth = Math.max(childWidth, largestChildWidth);
                        }
                    }
                    matchHeightLocally = false;
                    if (heightMode != 1073741824 && lp.height == -1) {
                        matchHeight = true;
                        matchHeightLocally = true;
                    }
                    margin = lp.topMargin + lp.bottomMargin;
                    childHeight = child.getMeasuredHeight() + margin;
                    childState = View.combineMeasuredStates(childState, child.getMeasuredState());
                    if (baselineAligned) {
                        childBaseline = child.getBaseline();
                        if (childBaseline != -1) {
                            if (lp.h < 0) {
                                i = this.e;
                            } else {
                                i = lp.h;
                            }
                            index = (((i & 112) >> 4) & -2) >> 1;
                            maxAscent[index] = Math.max(maxAscent[index], childBaseline);
                            maxDescent[index] = Math.max(maxDescent[index], childHeight - childBaseline);
                        }
                    }
                    maxHeight = Math.max(maxHeight, childHeight);
                    allFillParent = allFillParent && lp.height == -1;
                    if (lp.g > 0.0f) {
                        if (!matchHeightLocally) {
                            margin = childHeight;
                        }
                        weightedMaxHeight = Math.max(weightedMaxHeight, margin);
                    } else {
                        if (!matchHeightLocally) {
                            margin = childHeight;
                        }
                        alternativeMaxHeight = Math.max(alternativeMaxHeight, margin);
                    }
                }
                i2 += 0;
            }
            i2++;
        }
        if (this.f > 0 && a(count)) {
            this.f += this.l;
        }
        if (!(maxAscent[1] == -1 && maxAscent[0] == -1 && maxAscent[2] == -1 && maxAscent[3] == -1)) {
            maxHeight = Math.max(maxHeight, Math.max(maxAscent[3], Math.max(maxAscent[0], Math.max(maxAscent[1], maxAscent[2]))) + Math.max(maxDescent[3], Math.max(maxDescent[0], Math.max(maxDescent[1], maxDescent[2]))));
        }
        if (useLargestChild && (widthMode == Integer.MIN_VALUE || widthMode == 0)) {
            this.f = 0;
            i2 = 0;
            while (i2 < count) {
                child = getChildAt(i2);
                if (child == null) {
                    this.f += 0;
                } else if (child.getVisibility() == 8) {
                    i2 += 0;
                } else {
                    lp = (a) child.getLayoutParams();
                    if (isExactly) {
                        this.f += ((lp.leftMargin + largestChildWidth) + lp.rightMargin) + 0;
                    } else {
                        totalLength = this.f;
                        this.f = Math.max(totalLength, (((totalLength + largestChildWidth) + lp.leftMargin) + lp.rightMargin) + 0);
                    }
                }
                i2++;
            }
        }
        this.f += getPaddingLeft() + getPaddingRight();
        int widthSizeAndState = View.resolveSizeAndState(Math.max(this.f, getSuggestedMinimumWidth()), widthMeasureSpec, 0);
        int delta = (16777215 & widthSizeAndState) - this.f;
        if (skippedMeasure || (delta != 0 && totalWeight > 0.0f)) {
            float weightSum;
            if (this.g > 0.0f) {
                weightSum = this.g;
            } else {
                weightSum = totalWeight;
            }
            maxAscent[3] = -1;
            maxAscent[2] = -1;
            maxAscent[1] = -1;
            maxAscent[0] = -1;
            maxDescent[3] = -1;
            maxDescent[2] = -1;
            maxDescent[1] = -1;
            maxDescent[0] = -1;
            maxHeight = -1;
            this.f = 0;
            for (i2 = 0; i2 < count; i2++) {
                child = getChildAt(i2);
                if (!(child == null || child.getVisibility() == 8)) {
                    lp = (a) child.getLayoutParams();
                    float childExtra = lp.g;
                    if (childExtra > 0.0f) {
                        View child2;
                        int share = (int) ((((float) delta) * childExtra) / weightSum);
                        weightSum -= childExtra;
                        delta -= share;
                        int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, ((getPaddingTop() + getPaddingBottom()) + lp.topMargin) + lp.bottomMargin, lp.height);
                        if (lp.width != 0 || widthMode != 1073741824) {
                            childWidth = child.getMeasuredWidth() + share;
                            if (childWidth < 0) {
                                childWidth = 0;
                            }
                            share = childWidth;
                            child2 = child;
                        } else if (share > 0) {
                            child2 = child;
                        } else {
                            share = 0;
                            child2 = child;
                        }
                        child2.measure(MeasureSpec.makeMeasureSpec(share, ErrorDialogData.SUPPRESSED), childHeightMeasureSpec);
                        childState = View.combineMeasuredStates(childState, child.getMeasuredState() & -16777216);
                    }
                    if (isExactly) {
                        this.f += ((child.getMeasuredWidth() + lp.leftMargin) + lp.rightMargin) + 0;
                    } else {
                        totalLength = this.f;
                        this.f = Math.max(totalLength, (((child.getMeasuredWidth() + totalLength) + lp.leftMargin) + lp.rightMargin) + 0);
                    }
                    matchHeightLocally = heightMode != 1073741824 && lp.height == -1;
                    margin = lp.topMargin + lp.bottomMargin;
                    childHeight = child.getMeasuredHeight() + margin;
                    maxHeight = Math.max(maxHeight, childHeight);
                    if (!matchHeightLocally) {
                        margin = childHeight;
                    }
                    alternativeMaxHeight = Math.max(alternativeMaxHeight, margin);
                    allFillParent = allFillParent && lp.height == -1;
                    if (baselineAligned) {
                        childBaseline = child.getBaseline();
                        if (childBaseline != -1) {
                            if (lp.h < 0) {
                                i = this.e;
                            } else {
                                i = lp.h;
                            }
                            index = (((i & 112) >> 4) & -2) >> 1;
                            maxAscent[index] = Math.max(maxAscent[index], childBaseline);
                            maxDescent[index] = Math.max(maxDescent[index], childHeight - childBaseline);
                        }
                    }
                }
            }
            this.f += getPaddingLeft() + getPaddingRight();
            if (!(maxAscent[1] == -1 && maxAscent[0] == -1 && maxAscent[2] == -1 && maxAscent[3] == -1)) {
                maxHeight = Math.max(maxHeight, Math.max(maxAscent[3], Math.max(maxAscent[0], Math.max(maxAscent[1], maxAscent[2]))) + Math.max(maxDescent[3], Math.max(maxDescent[0], Math.max(maxDescent[1], maxDescent[2]))));
            }
        } else {
            alternativeMaxHeight = Math.max(alternativeMaxHeight, weightedMaxHeight);
            if (useLargestChild && widthMode != 1073741824) {
                for (i2 = 0; i2 < count; i2++) {
                    child = getChildAt(i2);
                    if (!(child == null || child.getVisibility() == 8 || ((a) child.getLayoutParams()).g <= 0.0f)) {
                        child.measure(MeasureSpec.makeMeasureSpec(largestChildWidth, ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), ErrorDialogData.SUPPRESSED));
                    }
                }
            }
        }
        if (!(allFillParent || heightMode == 1073741824)) {
            maxHeight = alternativeMaxHeight;
        }
        setMeasuredDimension((-16777216 & childState) | widthSizeAndState, View.resolveSizeAndState(Math.max((getPaddingTop() + getPaddingBottom()) + maxHeight, getSuggestedMinimumHeight()), heightMeasureSpec, childState << 16));
        if (matchHeight) {
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), ErrorDialogData.SUPPRESSED);
            i = 0;
            while (true) {
                int i3 = i;
                if (i3 < count) {
                    View childAt = getChildAt(i3);
                    if (childAt.getVisibility() != 8) {
                        a aVar = (a) childAt.getLayoutParams();
                        if (aVar.height == -1) {
                            int i4 = aVar.width;
                            aVar.width = childAt.getMeasuredWidth();
                            measureChildWithMargins(childAt, widthMeasureSpec, 0, makeMeasureSpec, 0);
                            aVar.width = i4;
                        }
                    }
                    i = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    private void a(View child, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
        measureChildWithMargins(child, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft;
        int i;
        int paddingRight;
        int paddingRight2;
        int childCount;
        int i2;
        int i3;
        int i4;
        int measuredWidth;
        int measuredHeight;
        a aVar;
        int i5;
        if (this.d == 1) {
            paddingLeft = getPaddingLeft();
            i = r - l;
            paddingRight = i - getPaddingRight();
            paddingRight2 = (i - paddingLeft) - getPaddingRight();
            childCount = getChildCount();
            i2 = 8388615 & this.e;
            switch (this.e & 112) {
                case 16:
                    i = getPaddingTop() + (((b - t) - this.f) / 2);
                    break;
                case 80:
                    i = ((getPaddingTop() + b) - t) - this.f;
                    break;
                default:
                    i = getPaddingTop();
                    break;
            }
            i3 = 0;
            i4 = i;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                if (childAt == null) {
                    i4 += 0;
                    i = i3;
                } else if (childAt.getVisibility() != 8) {
                    measuredWidth = childAt.getMeasuredWidth();
                    measuredHeight = childAt.getMeasuredHeight();
                    aVar = (a) childAt.getLayoutParams();
                    i5 = aVar.h;
                    if (i5 < 0) {
                        i5 = i2;
                    }
                    switch (d.a(i5, ViewCompat.f(this)) & 7) {
                        case 1:
                            i5 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + aVar.leftMargin) - aVar.rightMargin;
                            break;
                        case 5:
                            i5 = (paddingRight - measuredWidth) - aVar.rightMargin;
                            break;
                        default:
                            i5 = aVar.leftMargin + paddingLeft;
                            break;
                    }
                    if (a(i3)) {
                        i4 += this.m;
                    }
                    i4 += aVar.topMargin;
                    b(childAt, i5, i4 + 0, measuredWidth, measuredHeight);
                    i4 += (aVar.bottomMargin + measuredHeight) + 0;
                    i = i3 + 0;
                } else {
                    i = i3;
                }
                i3 = i + 1;
            }
            return;
        }
        boolean a = ax.a(this);
        paddingLeft = getPaddingTop();
        i = b - t;
        int paddingBottom = i - getPaddingBottom();
        measuredWidth = (i - paddingLeft) - getPaddingBottom();
        measuredHeight = getChildCount();
        i = this.e & 8388615;
        childCount = this.e & 112;
        boolean z = this.a;
        int[] iArr = this.i;
        int[] iArr2 = this.j;
        switch (d.a(i, ViewCompat.f(this))) {
            case 1:
                paddingRight = getPaddingLeft() + (((r - l) - this.f) / 2);
                break;
            case 5:
                paddingRight = ((getPaddingLeft() + r) - l) - this.f;
                break;
            default:
                paddingRight = getPaddingLeft();
                break;
        }
        if (a) {
            i2 = measuredHeight - 1;
            i5 = -1;
        } else {
            i2 = 0;
            i5 = 1;
        }
        paddingRight2 = 0;
        while (paddingRight2 < measuredHeight) {
            int i6 = i2 + (i5 * paddingRight2);
            View childAt2 = getChildAt(i6);
            if (childAt2 == null) {
                paddingRight += 0;
                i = paddingRight2;
            } else if (childAt2.getVisibility() != 8) {
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                i4 = -1;
                aVar = (a) childAt2.getLayoutParams();
                if (z && aVar.height != -1) {
                    i4 = childAt2.getBaseline();
                }
                i3 = aVar.h;
                if (i3 < 0) {
                    i3 = childCount;
                }
                switch (i3 & 112) {
                    case 16:
                        i4 = ((((measuredWidth - measuredHeight2) / 2) + paddingLeft) + aVar.topMargin) - aVar.bottomMargin;
                        break;
                    case 48:
                        i3 = aVar.topMargin + paddingLeft;
                        if (i4 != -1) {
                            i4 = (iArr[1] - i4) + i3;
                            break;
                        }
                    case 80:
                        i3 = (paddingBottom - measuredHeight2) - aVar.bottomMargin;
                        if (i4 != -1) {
                            i4 = i3 - (iArr2[2] - (childAt2.getMeasuredHeight() - i4));
                            break;
                        }
                    default:
                        i4 = paddingLeft;
                        break;
                }
                if (a(i6)) {
                    i3 = this.l + paddingRight;
                } else {
                    i3 = paddingRight;
                }
                i3 += aVar.leftMargin;
                b(childAt2, i3 + 0, i4, measuredWidth2, measuredHeight2);
                paddingRight = i3 + ((aVar.rightMargin + measuredWidth2) + 0);
                i = paddingRight2 + 0;
            } else {
                i = paddingRight2;
            }
            paddingRight2 = i + 1;
        }
    }

    private static void b(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }

    public void setOrientation(int orientation) {
        if (this.d != orientation) {
            this.d = orientation;
            requestLayout();
        }
    }

    public void setGravity(int gravity) {
        if (this.e != gravity) {
            if ((8388615 & gravity) == 0) {
                gravity |= 8388611;
            }
            if ((gravity & 112) == 0) {
                gravity |= 48;
            }
            this.e = gravity;
            requestLayout();
        }
    }

    public final int m() {
        return this.e;
    }

    public void setHorizontalGravity(int horizontalGravity) {
        int gravity = horizontalGravity & 8388615;
        if ((this.e & 8388615) != gravity) {
            this.e = (this.e & -8388616) | gravity;
            requestLayout();
        }
    }

    public void setVerticalGravity(int verticalGravity) {
        int gravity = verticalGravity & 112;
        if ((this.e & 112) != gravity) {
            this.e = (this.e & -113) | gravity;
            requestLayout();
        }
    }

    public a a(AttributeSet attrs) {
        return new a(getContext(), attrs);
    }

    protected a j() {
        if (this.d == 0) {
            return new a(-2, -2);
        }
        if (this.d == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    protected a b(LayoutParams p) {
        return new a(p);
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof a;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(event);
            event.setClassName(LinearLayoutCompat.class.getName());
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        if (VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(info);
            info.setClassName(LinearLayoutCompat.class.getName());
        }
    }
}
