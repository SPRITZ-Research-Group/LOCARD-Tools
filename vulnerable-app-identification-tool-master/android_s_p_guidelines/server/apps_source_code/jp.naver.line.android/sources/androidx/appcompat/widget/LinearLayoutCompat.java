package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.he;
import defpackage.hs;
import defpackage.v;

public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public class LayoutParams extends MarginLayoutParams {
        public float g;
        public int h;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, v.LinearLayoutCompat_Layout);
            this.g = obtainStyledAttributes.getFloat(v.LinearLayoutCompat_Layout_android_layout_weight, BitmapDescriptorFactory.HUE_RED);
            this.h = obtainStyledAttributes.getInt(v.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.h = -1;
            this.g = BitmapDescriptorFactory.HUE_RED;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.h = -1;
        }
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        bu a = bu.a(context, attributeSet, v.LinearLayoutCompat, i, 0);
        int a2 = a.a(v.LinearLayoutCompat_android_orientation, -1);
        if (a2 >= 0) {
            setOrientation(a2);
        }
        a2 = a.a(v.LinearLayoutCompat_android_gravity, -1);
        if (a2 >= 0) {
            setGravity(a2);
        }
        boolean a3 = a.a(v.LinearLayoutCompat_android_baselineAligned, true);
        if (!a3) {
            setBaselineAligned(a3);
        }
        this.mWeightSum = a.a(v.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = a.a(v.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = a.a(v.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a.a(v.LinearLayoutCompat_divider));
        this.mShowDividers = a.a(v.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = a.e(v.LinearLayoutCompat_dividerPadding, 0);
        a.a();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        int i = 0;
        while (i < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                virtualChildCount = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                virtualChildCount = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, virtualChildCount);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        boolean a = cd.a(this);
        int i = 0;
        while (i < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                int right;
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (a) {
                    right = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    right = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, right);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (a) {
                    virtualChildCount = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    virtualChildCount = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (a) {
                virtualChildCount = getPaddingLeft();
            } else {
                virtualChildCount = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, virtualChildCount);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (getChildCount() > this.mBaselineAlignedChildIndex) {
            View childAt = getChildAt(this.mBaselineAlignedChildIndex);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i = this.mBaselineChildTop;
                if (this.mOrientation == 1) {
                    int i2 = this.mGravity & 112;
                    if (i2 != 48) {
                        if (i2 == 16) {
                            i += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                        } else if (i2 == 80) {
                            i = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                        }
                    }
                }
                return (i + ((LayoutParams) childAt.getLayoutParams()).topMargin) + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            StringBuilder stringBuilder = new StringBuilder("base aligned child index out of range (0, ");
            stringBuilder.append(getChildCount());
            stringBuilder.append(")");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(BitmapDescriptorFactory.HUE_RED, f);
    }

    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    protected boolean hasDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        } else {
            if (i == getChildCount()) {
                return (this.mShowDividers & 4) != 0;
            } else {
                if ((this.mShowDividers & 2) == 0) {
                    return false;
                }
                for (i--; i >= 0; i--) {
                    if (getChildAt(i).getVisibility() != 8) {
                        z = true;
                        break;
                    }
                }
                return z;
            }
        }
    }

    void measureVertical(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int i5 = this.mBaselineAlignedChildIndex;
        boolean z = this.mUseLargestChild;
        float f = BitmapDescriptorFactory.HUE_RED;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        Object obj = null;
        Object obj2 = 1;
        Object obj3 = null;
        while (true) {
            int i12 = 8;
            int i13 = i9;
            int i14;
            int i15;
            int i16;
            int i17;
            View view;
            Object obj4;
            if (i11 < virtualChildCount) {
                View virtualChildAt = getVirtualChildAt(i11);
                if (virtualChildAt == null) {
                    r7.mTotalLength += measureNullChild(i11);
                    i14 = virtualChildCount;
                    i15 = mode2;
                    i9 = i13;
                    i16 = i11;
                    i11 = i6;
                    i6 = i7;
                } else {
                    float f2;
                    int i18 = i6;
                    if (virtualChildAt.getVisibility() != 8) {
                        int i19;
                        int i20;
                        if (hasDividerBeforeChildAt(i11)) {
                            r7.mTotalLength += r7.mDividerHeight;
                        }
                        LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                        f2 = f + layoutParams.g;
                        int i21;
                        if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.g > BitmapDescriptorFactory.HUE_RED) {
                            i17 = r7.mTotalLength;
                            i21 = i7;
                            r7.mTotalLength = Math.max(i17, (layoutParams.topMargin + i17) + layoutParams.bottomMargin);
                            i17 = i8;
                            view = virtualChildAt;
                            i19 = i10;
                            i14 = virtualChildCount;
                            i15 = mode2;
                            mode2 = i13;
                            i3 = i18;
                            i20 = i21;
                            obj = 1;
                            virtualChildCount = i11;
                        } else {
                            i21 = i7;
                            if (layoutParams.height != 0 || layoutParams.g <= BitmapDescriptorFactory.HUE_RED) {
                                i7 = Integer.MIN_VALUE;
                            } else {
                                layoutParams.height = -2;
                                i7 = 0;
                            }
                            i3 = i18;
                            int i22 = i7;
                            i20 = i21;
                            i4 = i8;
                            View view2 = virtualChildAt;
                            i14 = virtualChildCount;
                            i15 = mode2;
                            mode2 = i13;
                            i19 = i10;
                            virtualChildCount = i11;
                            measureChildBeforeLayout(virtualChildAt, i11, i, 0, i2, f2 == BitmapDescriptorFactory.HUE_RED ? r7.mTotalLength : 0);
                            i17 = i22;
                            if (i17 != Integer.MIN_VALUE) {
                                layoutParams.height = i17;
                            }
                            i17 = view2.getMeasuredHeight();
                            i6 = r7.mTotalLength;
                            view = view2;
                            r7.mTotalLength = Math.max(i6, (((i6 + i17) + layoutParams.topMargin) + layoutParams.bottomMargin) + getNextLocationOffset(view));
                            i17 = z ? Math.max(i17, i4) : i4;
                        }
                        if (i5 >= 0 && i5 == virtualChildCount + 1) {
                            r7.mBaselineChildTop = r7.mTotalLength;
                        }
                        if (virtualChildCount >= i5 || layoutParams.g <= BitmapDescriptorFactory.HUE_RED) {
                            if (mode == 1073741824 || layoutParams.width != -1) {
                                obj4 = null;
                            } else {
                                obj4 = 1;
                                obj3 = 1;
                            }
                            i7 = layoutParams.leftMargin + layoutParams.rightMargin;
                            i9 = view.getMeasuredWidth() + i7;
                            i10 = Math.max(i20, i9);
                            i11 = View.combineMeasuredStates(i3, view.getMeasuredState());
                            obj2 = (obj2 == null || layoutParams.width != -1) ? null : 1;
                            if (layoutParams.g > BitmapDescriptorFactory.HUE_RED) {
                                if (obj4 == null) {
                                    i7 = i9;
                                }
                                mode2 = Math.max(mode2, i7);
                                i6 = i19;
                            } else {
                                if (obj4 == null) {
                                    i7 = i9;
                                }
                                i6 = Math.max(i19, i7);
                            }
                        } else {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                    }
                    i4 = i8;
                    view = virtualChildAt;
                    i6 = i10;
                    i14 = virtualChildCount;
                    i15 = mode2;
                    mode2 = i13;
                    i10 = i7;
                    virtualChildCount = i11;
                    f2 = f;
                    i11 = i18;
                    i17 = i4;
                    i7 = getChildrenSkipCount(view, virtualChildCount) + virtualChildCount;
                    i8 = i17;
                    i9 = mode2;
                    f = f2;
                    i16 = i10;
                    i10 = i6;
                }
                i6 = i16;
                mode2 = i15;
                virtualChildCount = i14;
                i3 = i;
                i4 = i2;
                i16 = i7 + 1;
                i7 = i6;
                i6 = i11;
                i11 = i16;
            } else {
                i3 = i6;
                i4 = i8;
                i6 = i10;
                i14 = virtualChildCount;
                i15 = mode2;
                mode2 = i13;
                i10 = i7;
                if (r7.mTotalLength > 0) {
                    i7 = i14;
                    if (hasDividerBeforeChildAt(i7)) {
                        r7.mTotalLength += r7.mDividerHeight;
                    }
                } else {
                    i7 = i14;
                }
                if (z) {
                    i8 = i15;
                    if (i8 == Integer.MIN_VALUE || i8 == 0) {
                        r7.mTotalLength = 0;
                        i9 = 0;
                        while (i9 < i7) {
                            View virtualChildAt2 = getVirtualChildAt(i9);
                            if (virtualChildAt2 == null) {
                                r7.mTotalLength += measureNullChild(i9);
                            } else if (virtualChildAt2.getVisibility() == i12) {
                                i9 += getChildrenSkipCount(virtualChildAt2, i9);
                            } else {
                                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                                i5 = r7.mTotalLength;
                                r7.mTotalLength = Math.max(i5, (((i5 + i4) + layoutParams2.topMargin) + layoutParams2.bottomMargin) + getNextLocationOffset(virtualChildAt2));
                            }
                            i9++;
                            i12 = 8;
                        }
                    }
                } else {
                    i8 = i15;
                }
                r7.mTotalLength += getPaddingTop() + getPaddingBottom();
                i12 = i4;
                i11 = i2;
                i9 = View.resolveSizeAndState(Math.max(r7.mTotalLength, getSuggestedMinimumHeight()), i11, 0);
                i4 = (16777215 & i9) - r7.mTotalLength;
                if (obj != null || (i4 != 0 && f > BitmapDescriptorFactory.HUE_RED)) {
                    if (r7.mWeightSum > BitmapDescriptorFactory.HUE_RED) {
                        f = r7.mWeightSum;
                    }
                    r7.mTotalLength = 0;
                    float f3 = f;
                    i17 = 0;
                    i16 = i3;
                    i3 = i6;
                    i6 = i16;
                    while (i17 < i7) {
                        float f4;
                        int i23;
                        View virtualChildAt3 = getVirtualChildAt(i17);
                        if (virtualChildAt3.getVisibility() != 8) {
                            int i24;
                            Object obj5;
                            LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                            f4 = layoutParams3.g;
                            if (f4 > BitmapDescriptorFactory.HUE_RED) {
                                i24 = (int) ((((float) i4) * f4) / f3);
                                int i25 = i4 - i24;
                                float f5 = f3 - f4;
                                i4 = getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + layoutParams3.leftMargin) + layoutParams3.rightMargin, layoutParams3.width);
                                if (layoutParams3.height != 0 || i8 != 1073741824) {
                                    i12 = virtualChildAt3.getMeasuredHeight() + i24;
                                    if (i12 < 0) {
                                        i12 = 0;
                                    }
                                    i24 = i12;
                                } else if (i24 <= 0) {
                                    i12 = 1073741824;
                                    i24 = 0;
                                    virtualChildAt3.measure(i4, MeasureSpec.makeMeasureSpec(i24, i12));
                                    i6 = View.combineMeasuredStates(i6, virtualChildAt3.getMeasuredState() & -256);
                                    i4 = i25;
                                    f4 = f5;
                                }
                                i12 = 1073741824;
                                virtualChildAt3.measure(i4, MeasureSpec.makeMeasureSpec(i24, i12));
                                i6 = View.combineMeasuredStates(i6, virtualChildAt3.getMeasuredState() & -256);
                                i4 = i25;
                                f4 = f5;
                            } else {
                                f4 = f3;
                                virtualChildCount = i;
                            }
                            int i26 = i6;
                            i24 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                            i6 = virtualChildAt3.getMeasuredWidth() + i24;
                            i10 = Math.max(i10, i6);
                            int i27 = i6;
                            if (mode != 1073741824) {
                                i23 = i8;
                                i8 = -1;
                                if (layoutParams3.width == -1) {
                                    obj4 = 1;
                                    if (obj4 == null) {
                                        i24 = i27;
                                    }
                                    i6 = Math.max(i3, i24);
                                    obj5 = (obj2 == null && layoutParams3.width == i8) ? 1 : null;
                                    i24 = r7.mTotalLength;
                                    r7.mTotalLength = Math.max(i24, (((i24 + virtualChildAt3.getMeasuredHeight()) + layoutParams3.topMargin) + layoutParams3.bottomMargin) + getNextLocationOffset(virtualChildAt3));
                                    obj2 = obj5;
                                    i3 = i6;
                                    i6 = i26;
                                }
                            } else {
                                i23 = i8;
                                i8 = -1;
                            }
                            obj4 = null;
                            if (obj4 == null) {
                                i24 = i27;
                            }
                            i6 = Math.max(i3, i24);
                            if (obj2 == null) {
                            }
                            i24 = r7.mTotalLength;
                            r7.mTotalLength = Math.max(i24, (((i24 + virtualChildAt3.getMeasuredHeight()) + layoutParams3.topMargin) + layoutParams3.bottomMargin) + getNextLocationOffset(virtualChildAt3));
                            obj2 = obj5;
                            i3 = i6;
                            i6 = i26;
                        } else {
                            i23 = i8;
                            f4 = f3;
                            virtualChildCount = i;
                        }
                        i17++;
                        f3 = f4;
                        i8 = i23;
                    }
                    virtualChildCount = i;
                    r7.mTotalLength += getPaddingTop() + getPaddingBottom();
                    i17 = i3;
                } else {
                    i17 = Math.max(i6, mode2);
                    if (z && r3 != 1073741824) {
                        for (i6 = 0; i6 < i7; i6++) {
                            view = getVirtualChildAt(i6);
                            if (!(view == null || view.getVisibility() == 8 || ((LayoutParams) view.getLayoutParams()).g <= BitmapDescriptorFactory.HUE_RED)) {
                                view.measure(MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(i12, 1073741824));
                            }
                        }
                    }
                    i6 = i3;
                    virtualChildCount = i;
                }
                if (obj2 != null || mode == 1073741824) {
                    i17 = i10;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(i17 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), virtualChildCount, i6), i9);
                if (obj3 != null) {
                    forceUniformWidth(i7, i11);
                    return;
                }
                return;
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void measureHorizontal(int i, int i2) {
        int[] iArr;
        int i3;
        boolean z;
        boolean z2;
        int measuredHeight;
        int baseline;
        int i4;
        int i5;
        int i6;
        View virtualChildAt;
        int i7;
        int i8 = i;
        int i9 = i2;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || r7.mMaxDescent == null) {
            r7.mMaxAscent = new int[4];
            r7.mMaxDescent = new int[4];
        }
        int[] iArr2 = r7.mMaxAscent;
        int[] iArr3 = r7.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z3 = r7.mBaselineAligned;
        boolean z4 = r7.mUseLargestChild;
        int i10 = 1073741824;
        Object obj = mode == 1073741824 ? 1 : null;
        float f = BitmapDescriptorFactory.HUE_RED;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        Object obj2 = null;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        Object obj3 = 1;
        while (true) {
            iArr = iArr3;
            if (i11 >= virtualChildCount) {
                break;
            }
            float f2;
            View virtualChildAt2 = getVirtualChildAt(i11);
            if (virtualChildAt2 == null) {
                r7.mTotalLength += measureNullChild(i11);
                f2 = f;
                i3 = i11;
                z = z4;
                z2 = z3;
            } else {
                int i17;
                View view;
                if (virtualChildAt2.getVisibility() != 8) {
                    Object obj4;
                    if (hasDividerBeforeChildAt(i11)) {
                        r7.mTotalLength += r7.mDividerWidth;
                    }
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt2.getLayoutParams();
                    f2 = f + layoutParams.g;
                    if (mode == i10 && layoutParams.width == 0 && layoutParams.g > BitmapDescriptorFactory.HUE_RED) {
                        if (obj != null) {
                            r7.mTotalLength += layoutParams.leftMargin + layoutParams.rightMargin;
                        } else {
                            i3 = r7.mTotalLength;
                            r7.mTotalLength = Math.max(i3, (layoutParams.leftMargin + i3) + layoutParams.rightMargin);
                        }
                        if (z3) {
                            i10 = MeasureSpec.makeMeasureSpec(0, 0);
                            virtualChildAt2.measure(i10, i10);
                            i17 = i11;
                            z = z4;
                            z2 = z3;
                            view = virtualChildAt2;
                        } else {
                            i17 = i11;
                            z = z4;
                            z2 = z3;
                            view = virtualChildAt2;
                            i11 = 1073741824;
                            obj2 = 1;
                            obj4 = (mode2 == i11 && layoutParams.height == -1) ? 1 : null;
                            i10 = layoutParams.topMargin + layoutParams.bottomMargin;
                            measuredHeight = view.getMeasuredHeight() + i10;
                            i16 = View.combineMeasuredStates(i16, view.getMeasuredState());
                            if (z2) {
                                baseline = view.getBaseline();
                                if (baseline != -1) {
                                    i4 = ((((layoutParams.h >= 0 ? r7.mGravity : layoutParams.h) & 112) >> 4) & -2) >> 1;
                                    iArr2[i4] = Math.max(iArr2[i4], baseline);
                                    iArr[i4] = Math.max(iArr[i4], measuredHeight - baseline);
                                }
                            }
                            i13 = Math.max(i13, measuredHeight);
                            obj3 = (obj3 == null && layoutParams.height == -1) ? 1 : null;
                            if (layoutParams.g <= BitmapDescriptorFactory.HUE_RED) {
                                if (obj4 == null) {
                                    i10 = measuredHeight;
                                }
                                i15 = Math.max(i15, i10);
                            } else {
                                i5 = i15;
                                if (obj4 != null) {
                                    measuredHeight = i10;
                                }
                                i14 = Math.max(i14, measuredHeight);
                                i15 = i5;
                            }
                        }
                    } else {
                        if (layoutParams.width != 0 || layoutParams.g <= BitmapDescriptorFactory.HUE_RED) {
                            i10 = Integer.MIN_VALUE;
                        } else {
                            layoutParams.width = -2;
                            i10 = 0;
                        }
                        i17 = i11;
                        int i18 = i10;
                        z = z4;
                        z2 = z3;
                        View view2 = virtualChildAt2;
                        measureChildBeforeLayout(virtualChildAt2, i17, i, f2 == BitmapDescriptorFactory.HUE_RED ? r7.mTotalLength : 0, i2, 0);
                        i3 = i18;
                        if (i3 != Integer.MIN_VALUE) {
                            layoutParams.width = i3;
                        }
                        i3 = view2.getMeasuredWidth();
                        if (obj != null) {
                            view = view2;
                            r7.mTotalLength += ((layoutParams.leftMargin + i3) + layoutParams.rightMargin) + getNextLocationOffset(view);
                        } else {
                            view = view2;
                            i11 = r7.mTotalLength;
                            r7.mTotalLength = Math.max(i11, (((i11 + i3) + layoutParams.leftMargin) + layoutParams.rightMargin) + getNextLocationOffset(view));
                        }
                        if (z) {
                            i12 = Math.max(i3, i12);
                        }
                    }
                    i11 = 1073741824;
                    if (mode2 == i11) {
                    }
                    i10 = layoutParams.topMargin + layoutParams.bottomMargin;
                    measuredHeight = view.getMeasuredHeight() + i10;
                    i16 = View.combineMeasuredStates(i16, view.getMeasuredState());
                    if (z2) {
                        baseline = view.getBaseline();
                        if (baseline != -1) {
                            if (layoutParams.h >= 0) {
                            }
                            i4 = ((((layoutParams.h >= 0 ? r7.mGravity : layoutParams.h) & 112) >> 4) & -2) >> 1;
                            iArr2[i4] = Math.max(iArr2[i4], baseline);
                            iArr[i4] = Math.max(iArr[i4], measuredHeight - baseline);
                        }
                    }
                    i13 = Math.max(i13, measuredHeight);
                    if (obj3 == null) {
                    }
                    if (layoutParams.g <= BitmapDescriptorFactory.HUE_RED) {
                        i5 = i15;
                        if (obj4 != null) {
                            measuredHeight = i10;
                        }
                        i14 = Math.max(i14, measuredHeight);
                        i15 = i5;
                    } else {
                        if (obj4 == null) {
                            i10 = measuredHeight;
                        }
                        i15 = Math.max(i15, i10);
                    }
                } else {
                    i17 = i11;
                    z = z4;
                    z2 = z3;
                    view = virtualChildAt2;
                    baseline = i13;
                    i10 = i14;
                    i5 = i15;
                    i4 = i16;
                    f2 = f;
                }
                i5 = i17;
                i3 = getChildrenSkipCount(view, i5) + i5;
            }
            i11 = i3 + 1;
            iArr3 = iArr;
            f = f2;
            z4 = z;
            z3 = z2;
            i10 = 1073741824;
            i9 = i2;
        }
        z = z4;
        z2 = z3;
        int i19 = i13;
        i10 = i14;
        i5 = i15;
        i4 = i16;
        if (r7.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            r7.mTotalLength += r7.mDividerWidth;
        }
        if (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) {
            i6 = i4;
        } else {
            i6 = i4;
            i19 = Math.max(i19, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        }
        if (z && (mode == Integer.MIN_VALUE || mode == 0)) {
            r7.mTotalLength = 0;
            i11 = 0;
            while (i11 < virtualChildCount) {
                int i20;
                virtualChildAt = getVirtualChildAt(i11);
                if (virtualChildAt == null) {
                    r7.mTotalLength += measureNullChild(i11);
                } else if (virtualChildAt.getVisibility() == 8) {
                    i11 += getChildrenSkipCount(virtualChildAt, i11);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt.getLayoutParams();
                    if (obj != null) {
                        r7.mTotalLength += ((layoutParams2.leftMargin + i12) + layoutParams2.rightMargin) + getNextLocationOffset(virtualChildAt);
                    } else {
                        i4 = r7.mTotalLength;
                        i20 = i11;
                        r7.mTotalLength = Math.max(i4, (((i4 + i12) + layoutParams2.leftMargin) + layoutParams2.rightMargin) + getNextLocationOffset(virtualChildAt));
                        i11 = i20 + 1;
                    }
                }
                i20 = i11;
                i11 = i20 + 1;
            }
        }
        r7.mTotalLength += getPaddingLeft() + getPaddingRight();
        i11 = View.resolveSizeAndState(Math.max(r7.mTotalLength, getSuggestedMinimumWidth()), i8, 0);
        measuredHeight = (16777215 & i11) - r7.mTotalLength;
        if (obj2 != null || (measuredHeight != 0 && f > BitmapDescriptorFactory.HUE_RED)) {
            if (r7.mWeightSum > BitmapDescriptorFactory.HUE_RED) {
                f = r7.mWeightSum;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            r7.mTotalLength = 0;
            i5 = i10;
            i9 = i6;
            i4 = -1;
            float f3 = f;
            i3 = 0;
            while (i3 < virtualChildCount) {
                View virtualChildAt3 = getVirtualChildAt(i3);
                if (virtualChildAt3 == null || virtualChildAt3.getVisibility() == 8) {
                    baseline = measuredHeight;
                    i7 = virtualChildCount;
                    measuredHeight = i2;
                } else {
                    float f4;
                    Object obj5;
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f5 = layoutParams3.g;
                    if (f5 > BitmapDescriptorFactory.HUE_RED) {
                        i8 = (int) ((((float) measuredHeight) * f5) / f3);
                        float f6 = f3 - f5;
                        int i21 = measuredHeight - i8;
                        i7 = virtualChildCount;
                        i10 = getChildMeasureSpec(i2, ((getPaddingTop() + getPaddingBottom()) + layoutParams3.topMargin) + layoutParams3.bottomMargin, layoutParams3.height);
                        if (layoutParams3.width != 0 || mode != 1073741824) {
                            baseline = virtualChildAt3.getMeasuredWidth() + i8;
                        } else if (i8 > 0) {
                            baseline = i8;
                            virtualChildAt3.measure(MeasureSpec.makeMeasureSpec(baseline, 1073741824), i10);
                            i9 = View.combineMeasuredStates(i9, virtualChildAt3.getMeasuredState() & -16777216);
                            f3 = f6;
                            baseline = i21;
                        }
                        baseline = 0;
                        virtualChildAt3.measure(MeasureSpec.makeMeasureSpec(baseline, 1073741824), i10);
                        i9 = View.combineMeasuredStates(i9, virtualChildAt3.getMeasuredState() & -16777216);
                        f3 = f6;
                        baseline = i21;
                    } else {
                        baseline = measuredHeight;
                        i7 = virtualChildCount;
                        measuredHeight = i2;
                    }
                    if (obj != null) {
                        r7.mTotalLength += ((virtualChildAt3.getMeasuredWidth() + layoutParams3.leftMargin) + layoutParams3.rightMargin) + getNextLocationOffset(virtualChildAt3);
                        f4 = f3;
                    } else {
                        i8 = r7.mTotalLength;
                        f4 = f3;
                        r7.mTotalLength = Math.max(i8, (((virtualChildAt3.getMeasuredWidth() + i8) + layoutParams3.leftMargin) + layoutParams3.rightMargin) + getNextLocationOffset(virtualChildAt3));
                    }
                    Object obj6 = (mode2 == 1073741824 || layoutParams3.height != -1) ? null : 1;
                    i8 = layoutParams3.topMargin + layoutParams3.bottomMargin;
                    virtualChildCount = virtualChildAt3.getMeasuredHeight() + i8;
                    i4 = Math.max(i4, virtualChildCount);
                    if (obj6 == null) {
                        i8 = virtualChildCount;
                    }
                    i10 = Math.max(i5, i8);
                    if (obj3 != null) {
                        i5 = -1;
                        if (layoutParams3.height == -1) {
                            obj5 = 1;
                            if (z2) {
                                i12 = virtualChildAt3.getBaseline();
                                if (i12 != i5) {
                                    i19 = ((((layoutParams3.h >= 0 ? r7.mGravity : layoutParams3.h) & 112) >> 4) & -2) >> 1;
                                    iArr2[i19] = Math.max(iArr2[i19], i12);
                                    iArr[i19] = Math.max(iArr[i19], virtualChildCount - i12);
                                    i5 = i10;
                                    obj3 = obj5;
                                    f3 = f4;
                                }
                            }
                            i5 = i10;
                            obj3 = obj5;
                            f3 = f4;
                        }
                    } else {
                        i5 = -1;
                    }
                    obj5 = null;
                    if (z2) {
                        i12 = virtualChildAt3.getBaseline();
                        if (i12 != i5) {
                            if (layoutParams3.h >= 0) {
                            }
                            i19 = ((((layoutParams3.h >= 0 ? r7.mGravity : layoutParams3.h) & 112) >> 4) & -2) >> 1;
                            iArr2[i19] = Math.max(iArr2[i19], i12);
                            iArr[i19] = Math.max(iArr[i19], virtualChildCount - i12);
                            i5 = i10;
                            obj3 = obj5;
                            f3 = f4;
                        }
                    }
                    i5 = i10;
                    obj3 = obj5;
                    f3 = f4;
                }
                i3++;
                measuredHeight = baseline;
                virtualChildCount = i7;
                i8 = i;
            }
            i7 = virtualChildCount;
            measuredHeight = i2;
            r7.mTotalLength += getPaddingLeft() + getPaddingRight();
            if (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) {
                i19 = i4;
            } else {
                i19 = Math.max(i4, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            }
            i6 = i9;
            i3 = i5;
        } else {
            i3 = Math.max(i10, i5);
            if (z && mode != 1073741824) {
                for (i10 = 0; i10 < virtualChildCount; i10++) {
                    virtualChildAt = getVirtualChildAt(i10);
                    if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || ((LayoutParams) virtualChildAt.getLayoutParams()).g <= BitmapDescriptorFactory.HUE_RED)) {
                        virtualChildAt.measure(MeasureSpec.makeMeasureSpec(i12, 1073741824), MeasureSpec.makeMeasureSpec(virtualChildAt.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i7 = virtualChildCount;
            measuredHeight = i2;
        }
        if (obj3 != null || mode2 == 1073741824) {
            i3 = i19;
        }
        setMeasuredDimension(i11 | (i6 & -16777216), View.resolveSizeAndState(Math.max(i3 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), measuredHeight, i6 << 16));
        if (null != null) {
            forceUniformHeight(i7, i);
        }
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int paddingLeft = getPaddingLeft();
        int i5 = i3 - i;
        int paddingRight = i5 - getPaddingRight();
        int paddingRight2 = (i5 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        i5 = this.mGravity & 112;
        int i6 = this.mGravity & 8388615;
        if (i5 == 16) {
            paddingTop = (((i4 - i2) - r6.mTotalLength) / 2) + getPaddingTop();
        } else if (i5 != 80) {
            paddingTop = getPaddingTop();
        } else {
            paddingTop = ((getPaddingTop() + i4) - i2) - r6.mTotalLength;
        }
        int i7 = 0;
        while (i7 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i7);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(i7);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i8 = layoutParams.h;
                if (i8 < 0) {
                    i8 = i6;
                }
                i8 = he.a(i8, hs.g(this)) & 7;
                if (i8 == 1) {
                    i8 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                } else if (i8 != 5) {
                    i8 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i8 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                }
                i5 = i8;
                if (hasDividerBeforeChildAt(i7)) {
                    paddingTop += r6.mDividerHeight;
                }
                int i9 = paddingTop + layoutParams.topMargin;
                LayoutParams layoutParams2 = layoutParams;
                setChildFrame(virtualChildAt, i5, i9 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i7 += getChildrenSkipCount(virtualChildAt, i7);
                paddingTop = i9 + ((measuredHeight + layoutParams2.bottomMargin) + getNextLocationOffset(virtualChildAt));
            }
            i7++;
        }
    }

    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        boolean a = cd.a(this);
        int paddingTop = getPaddingTop();
        int i7 = i4 - i2;
        int paddingBottom = i7 - getPaddingBottom();
        int paddingBottom2 = (i7 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        i7 = this.mGravity & 8388615;
        int i8 = this.mGravity & 112;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        i7 = he.a(i7, hs.g(this));
        if (i7 == 1) {
            paddingLeft = (((i3 - i) - r6.mTotalLength) / 2) + getPaddingLeft();
        } else if (i7 != 5) {
            paddingLeft = getPaddingLeft();
        } else {
            paddingLeft = ((getPaddingLeft() + i3) - i) - r6.mTotalLength;
        }
        if (a) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        i7 = 0;
        while (i7 < virtualChildCount) {
            int i9;
            int i10;
            int i11;
            int i12 = i5 + (i6 * i7);
            View virtualChildAt = getVirtualChildAt(i12);
            int i13;
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i12);
            } else if (virtualChildAt.getVisibility() != 8) {
                int i14;
                View view;
                LayoutParams layoutParams;
                View view2;
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt.getLayoutParams();
                if (z) {
                    i14 = i7;
                    i9 = virtualChildCount;
                    if (layoutParams2.height != -1) {
                        i7 = virtualChildAt.getBaseline();
                        virtualChildCount = layoutParams2.h;
                        if (virtualChildCount < 0) {
                            virtualChildCount = i8;
                        }
                        virtualChildCount &= 112;
                        i10 = i8;
                        if (virtualChildCount != 16) {
                            i7 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams2.topMargin) - layoutParams2.bottomMargin;
                        } else if (virtualChildCount != 48) {
                            virtualChildCount = layoutParams2.topMargin + paddingTop;
                            if (i7 != -1) {
                                virtualChildCount += iArr[1] - i7;
                            }
                            i7 = virtualChildCount;
                        } else if (virtualChildCount == 80) {
                            i7 = paddingTop;
                        } else {
                            virtualChildCount = (paddingBottom - measuredHeight) - layoutParams2.bottomMargin;
                            if (i7 != -1) {
                                virtualChildCount -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - i7);
                            }
                            i7 = virtualChildCount;
                        }
                        if (hasDividerBeforeChildAt(i12)) {
                            paddingLeft += r6.mDividerWidth;
                        }
                        virtualChildCount = layoutParams2.leftMargin + paddingLeft;
                        view = virtualChildAt;
                        i8 = i12;
                        i12 = virtualChildCount + getLocationOffset(virtualChildAt);
                        i13 = i14;
                        i11 = paddingTop;
                        layoutParams = layoutParams2;
                        setChildFrame(virtualChildAt, i12, i7, measuredWidth, measuredHeight);
                        view2 = view;
                        i7 = i13 + getChildrenSkipCount(view2, i8);
                        paddingLeft = virtualChildCount + ((measuredWidth + layoutParams.rightMargin) + getNextLocationOffset(view2));
                        i7++;
                        virtualChildCount = i9;
                        i8 = i10;
                        paddingTop = i11;
                    }
                } else {
                    i14 = i7;
                    i9 = virtualChildCount;
                }
                i7 = -1;
                virtualChildCount = layoutParams2.h;
                if (virtualChildCount < 0) {
                    virtualChildCount = i8;
                }
                virtualChildCount &= 112;
                i10 = i8;
                if (virtualChildCount != 16) {
                    i7 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams2.topMargin) - layoutParams2.bottomMargin;
                } else if (virtualChildCount != 48) {
                    virtualChildCount = layoutParams2.topMargin + paddingTop;
                    if (i7 != -1) {
                        virtualChildCount += iArr[1] - i7;
                    }
                    i7 = virtualChildCount;
                } else if (virtualChildCount == 80) {
                    virtualChildCount = (paddingBottom - measuredHeight) - layoutParams2.bottomMargin;
                    if (i7 != -1) {
                        virtualChildCount -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - i7);
                    }
                    i7 = virtualChildCount;
                } else {
                    i7 = paddingTop;
                }
                if (hasDividerBeforeChildAt(i12)) {
                    paddingLeft += r6.mDividerWidth;
                }
                virtualChildCount = layoutParams2.leftMargin + paddingLeft;
                view = virtualChildAt;
                i8 = i12;
                i12 = virtualChildCount + getLocationOffset(virtualChildAt);
                i13 = i14;
                i11 = paddingTop;
                layoutParams = layoutParams2;
                setChildFrame(virtualChildAt, i12, i7, measuredWidth, measuredHeight);
                view2 = view;
                i7 = i13 + getChildrenSkipCount(view2, i8);
                paddingLeft = virtualChildCount + ((measuredWidth + layoutParams.rightMargin) + getNextLocationOffset(view2));
                i7++;
                virtualChildCount = i9;
                i8 = i10;
                paddingTop = i11;
            } else {
                i13 = i7;
            }
            i11 = paddingTop;
            i9 = virtualChildCount;
            i10 = i8;
            i7++;
            virtualChildCount = i9;
            i8 = i10;
            paddingTop = i11;
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        i &= 8388615;
        if ((8388615 & this.mGravity) != i) {
            this.mGravity = i | (this.mGravity & -8388616);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        i &= 112;
        if ((this.mGravity & 112) != i) {
            this.mGravity = i | (this.mGravity & -113);
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        return this.mOrientation == 1 ? new LayoutParams(-1, -2) : null;
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }
}
