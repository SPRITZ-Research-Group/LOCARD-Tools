package com.facebook.yoga.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaWrap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashMap;
import java.util.Map;

public class YogaLayout extends ViewGroup {
    private final YogaNode mYogaNode;
    private final Map<View, YogaNode> mYogaNodes;

    public class LayoutParams extends android.view.ViewGroup.LayoutParams {
        SparseArray<Float> numericAttributes;
        SparseArray<String> stringAttributes;

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                this.numericAttributes = layoutParams2.numericAttributes.clone();
                this.stringAttributes = layoutParams2.stringAttributes.clone();
                return;
            }
            this.numericAttributes = new SparseArray();
            this.stringAttributes = new SparseArray();
            if (layoutParams.width >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf((float) this.width));
            }
            if (layoutParams.height >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf((float) this.height));
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.numericAttributes = new SparseArray();
            this.stringAttributes = new SparseArray();
            if (i >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf((float) i));
            }
            if (i2 >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf((float) i2));
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.numericAttributes = new SparseArray();
            this.stringAttributes = new SparseArray();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.yoga);
            if (this.width >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_width, Float.valueOf((float) this.width));
            }
            if (this.height >= 0) {
                this.numericAttributes.put(R.styleable.yoga_yg_height, Float.valueOf((float) this.height));
            }
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                TypedValue typedValue = new TypedValue();
                obtainStyledAttributes.getValue(index, typedValue);
                if (typedValue.type == 5) {
                    this.numericAttributes.put(index, Float.valueOf((float) obtainStyledAttributes.getDimensionPixelSize(index, 0)));
                } else if (typedValue.type == 3) {
                    this.stringAttributes.put(index, obtainStyledAttributes.getString(index));
                } else {
                    this.numericAttributes.put(index, Float.valueOf(obtainStyledAttributes.getFloat(index, BitmapDescriptorFactory.HUE_RED)));
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public YogaLayout(Context context) {
        this(context, null, 0);
    }

    public YogaLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YogaLayout(Context context, AttributeSet attributeSet, int i) {
        LayoutParams layoutParams;
        super(context, attributeSet, i);
        this.mYogaNode = new YogaNode();
        this.mYogaNodes = new HashMap();
        this.mYogaNode.setData(this);
        this.mYogaNode.setMeasureFunction(new a());
        if (attributeSet != null) {
            layoutParams = new LayoutParams(context, attributeSet);
        } else {
            layoutParams = (LayoutParams) generateDefaultLayoutParams();
        }
        applyLayoutParams(layoutParams, this.mYogaNode, this);
    }

    public YogaNode getYogaNode() {
        return this.mYogaNode;
    }

    public YogaNode getYogaNodeForView(View view) {
        return (YogaNode) this.mYogaNodes.get(view);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        this.mYogaNode.setMeasureFunction(null);
        if (view instanceof VirtualYogaLayout) {
            VirtualYogaLayout virtualYogaLayout = (VirtualYogaLayout) view;
            virtualYogaLayout.transferChildren(this);
            this.mYogaNode.addChildAt(virtualYogaLayout.getYogaNode(), this.mYogaNode.getChildCount());
            return;
        }
        super.addView(view, i, layoutParams);
        if (!this.mYogaNodes.containsKey(view)) {
            YogaNode yogaNode;
            if (view instanceof YogaLayout) {
                yogaNode = ((YogaLayout) view).getYogaNode();
            } else {
                if (this.mYogaNodes.containsKey(view)) {
                    yogaNode = (YogaNode) this.mYogaNodes.get(view);
                } else {
                    yogaNode = new YogaNode();
                }
                yogaNode.setData(view);
                yogaNode.setMeasureFunction(new a());
            }
            applyLayoutParams((LayoutParams) view.getLayoutParams(), yogaNode, view);
            this.mYogaNodes.put(view, yogaNode);
            this.mYogaNode.addChildAt(yogaNode, this.mYogaNode.getChildCount());
        }
    }

    public void addView(View view, YogaNode yogaNode) {
        this.mYogaNodes.put(view, yogaNode);
        addView(view);
    }

    public void removeView(View view) {
        removeViewFromYogaTree(view, false);
        super.removeView(view);
    }

    public void removeViewAt(int i) {
        removeViewFromYogaTree(getChildAt(i), false);
        super.removeViewAt(i);
    }

    public void removeViewInLayout(View view) {
        removeViewFromYogaTree(view, true);
        super.removeViewInLayout(view);
    }

    public void removeViews(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            removeViewFromYogaTree(getChildAt(i3), false);
        }
        super.removeViews(i, i2);
    }

    public void removeViewsInLayout(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            removeViewFromYogaTree(getChildAt(i3), true);
        }
        super.removeViewsInLayout(i, i2);
    }

    public void removeAllViews() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            removeViewFromYogaTree(getChildAt(i), false);
        }
        super.removeAllViews();
    }

    public void removeAllViewsInLayout() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            removeViewFromYogaTree(getChildAt(i), true);
        }
        super.removeAllViewsInLayout();
    }

    public void invalidate(View view) {
        if (this.mYogaNodes.containsKey(view)) {
            ((YogaNode) this.mYogaNodes.get(view)).dirty();
            return;
        }
        int childCount = this.mYogaNode.getChildCount();
        for (int i = 0; i < childCount; i++) {
            YogaNode childAt = this.mYogaNode.getChildAt(i);
            if (childAt.getData() instanceof YogaLayout) {
                ((YogaLayout) childAt.getData()).invalidate(view);
            }
        }
        invalidate();
    }

    private void removeViewFromYogaTree(View view, boolean z) {
        YogaNode yogaNode = (YogaNode) this.mYogaNodes.get(view);
        if (yogaNode != null) {
            YogaNode owner = yogaNode.getOwner();
            for (int i = 0; i < owner.getChildCount(); i++) {
                if (owner.getChildAt(i).equals(yogaNode)) {
                    owner.removeChildAt(i);
                    break;
                }
            }
            yogaNode.setData(null);
            this.mYogaNodes.remove(view);
            if (z) {
                this.mYogaNode.calculateLayout(1.0E21f, 1.0E21f);
            }
        }
    }

    private void applyLayoutRecursive(YogaNode yogaNode, float f, float f2) {
        int round;
        int round2;
        View view = (View) yogaNode.getData();
        if (!(view == null || view == this)) {
            if (view.getVisibility() != 8) {
                round = Math.round(yogaNode.getLayoutX() + f);
                round2 = Math.round(yogaNode.getLayoutY() + f2);
                view.measure(MeasureSpec.makeMeasureSpec(Math.round(yogaNode.getLayoutWidth()), 1073741824), MeasureSpec.makeMeasureSpec(Math.round(yogaNode.getLayoutHeight()), 1073741824));
                view.layout(round, round2, view.getMeasuredWidth() + round, view.getMeasuredHeight() + round2);
            } else {
                return;
            }
        }
        round = yogaNode.getChildCount();
        for (round2 = 0; round2 < round; round2++) {
            if (equals(view)) {
                applyLayoutRecursive(yogaNode.getChildAt(round2), f, f2);
            } else if (!(view instanceof YogaLayout)) {
                applyLayoutRecursive(yogaNode.getChildAt(round2), yogaNode.getLayoutX() + f, yogaNode.getLayoutY() + f2);
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!(getParent() instanceof YogaLayout)) {
            createLayout(MeasureSpec.makeMeasureSpec(i3 - i, 1073741824), MeasureSpec.makeMeasureSpec(i4 - i2, 1073741824));
        }
        applyLayoutRecursive(this.mYogaNode, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
    }

    protected void onMeasure(int i, int i2) {
        if (!(getParent() instanceof YogaLayout)) {
            createLayout(i, i2);
        }
        setMeasuredDimension(Math.round(this.mYogaNode.getLayoutWidth()), Math.round(this.mYogaNode.getLayoutHeight()));
    }

    private void createLayout(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        i = MeasureSpec.getMode(i);
        i2 = MeasureSpec.getMode(i2);
        if (i2 == 1073741824) {
            this.mYogaNode.setHeight((float) size2);
        }
        if (i == 1073741824) {
            this.mYogaNode.setWidth((float) size);
        }
        if (i2 == Integer.MIN_VALUE) {
            this.mYogaNode.setMaxHeight((float) size2);
        }
        if (i == Integer.MIN_VALUE) {
            this.mYogaNode.setMaxWidth((float) size);
        }
        this.mYogaNode.calculateLayout(1.0E21f, 1.0E21f);
    }

    protected static void applyLayoutParams(LayoutParams layoutParams, YogaNode yogaNode, View view) {
        int i;
        int keyAt;
        float floatValue;
        if (VERSION.SDK_INT >= 17 && view.getResources().getConfiguration().getLayoutDirection() == 1) {
            yogaNode.setDirection(YogaDirection.RTL);
        }
        Drawable background = view.getBackground();
        if (background != null) {
            Rect rect = new Rect();
            if (background.getPadding(rect)) {
                yogaNode.setPadding(YogaEdge.LEFT, (float) rect.left);
                yogaNode.setPadding(YogaEdge.TOP, (float) rect.top);
                yogaNode.setPadding(YogaEdge.RIGHT, (float) rect.right);
                yogaNode.setPadding(YogaEdge.BOTTOM, (float) rect.bottom);
            }
        }
        for (i = 0; i < layoutParams.numericAttributes.size(); i++) {
            keyAt = layoutParams.numericAttributes.keyAt(i);
            floatValue = ((Float) layoutParams.numericAttributes.valueAt(i)).floatValue();
            if (keyAt == R.styleable.yoga_yg_alignContent) {
                yogaNode.setAlignContent(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_alignItems) {
                yogaNode.setAlignItems(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_alignSelf) {
                yogaNode.setAlignSelf(YogaAlign.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_aspectRatio) {
                yogaNode.setAspectRatio(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderLeft) {
                yogaNode.setBorder(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderTop) {
                yogaNode.setBorder(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderRight) {
                yogaNode.setBorder(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderBottom) {
                yogaNode.setBorder(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderStart) {
                yogaNode.setBorder(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderEnd) {
                yogaNode.setBorder(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderHorizontal) {
                yogaNode.setBorder(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderVertical) {
                yogaNode.setBorder(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_borderAll) {
                yogaNode.setBorder(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_direction) {
                yogaNode.setDirection(YogaDirection.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_display) {
                yogaNode.setDisplay(YogaDisplay.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_flex) {
                yogaNode.setFlex(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_flexBasis) {
                yogaNode.setFlexBasis(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_flexDirection) {
                yogaNode.setFlexDirection(YogaFlexDirection.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_flexGrow) {
                yogaNode.setFlexGrow(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_flexShrink) {
                yogaNode.setFlexShrink(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_height) {
                yogaNode.setHeight(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginLeft) {
                yogaNode.setMargin(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_justifyContent) {
                yogaNode.setJustifyContent(YogaJustify.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_marginTop) {
                yogaNode.setMargin(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginRight) {
                yogaNode.setMargin(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginBottom) {
                yogaNode.setMargin(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginStart) {
                yogaNode.setMargin(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginEnd) {
                yogaNode.setMargin(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginHorizontal) {
                yogaNode.setMargin(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginVertical) {
                yogaNode.setMargin(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_marginAll) {
                yogaNode.setMargin(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_maxHeight) {
                yogaNode.setMaxHeight(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_maxWidth) {
                yogaNode.setMaxWidth(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_minHeight) {
                yogaNode.setMinHeight(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_minWidth) {
                yogaNode.setMinWidth(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_overflow) {
                yogaNode.setOverflow(YogaOverflow.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_paddingLeft) {
                yogaNode.setPadding(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingTop) {
                yogaNode.setPadding(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingRight) {
                yogaNode.setPadding(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingBottom) {
                yogaNode.setPadding(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingStart) {
                yogaNode.setPadding(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingEnd) {
                yogaNode.setPadding(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingHorizontal) {
                yogaNode.setPadding(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingVertical) {
                yogaNode.setPadding(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_paddingAll) {
                yogaNode.setPadding(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionLeft) {
                yogaNode.setPosition(YogaEdge.LEFT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionTop) {
                yogaNode.setPosition(YogaEdge.TOP, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionRight) {
                yogaNode.setPosition(YogaEdge.RIGHT, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionBottom) {
                yogaNode.setPosition(YogaEdge.BOTTOM, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionStart) {
                yogaNode.setPosition(YogaEdge.START, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionEnd) {
                yogaNode.setPosition(YogaEdge.END, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionHorizontal) {
                yogaNode.setPosition(YogaEdge.HORIZONTAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionVertical) {
                yogaNode.setPosition(YogaEdge.VERTICAL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionAll) {
                yogaNode.setPosition(YogaEdge.ALL, floatValue);
            } else if (keyAt == R.styleable.yoga_yg_positionType) {
                yogaNode.setPositionType(YogaPositionType.fromInt(Math.round(floatValue)));
            } else if (keyAt == R.styleable.yoga_yg_width) {
                yogaNode.setWidth(floatValue);
            } else if (keyAt == R.styleable.yoga_yg_wrap) {
                yogaNode.setWrap(YogaWrap.fromInt(Math.round(floatValue)));
            }
        }
        for (i = 0; i < layoutParams.stringAttributes.size(); i++) {
            keyAt = layoutParams.stringAttributes.keyAt(i);
            String str = (String) layoutParams.stringAttributes.valueAt(i);
            if (str.equals("auto")) {
                if (keyAt == R.styleable.yoga_yg_marginLeft) {
                    yogaNode.setMarginAuto(YogaEdge.LEFT);
                } else if (keyAt == R.styleable.yoga_yg_marginTop) {
                    yogaNode.setMarginAuto(YogaEdge.TOP);
                } else if (keyAt == R.styleable.yoga_yg_marginRight) {
                    yogaNode.setMarginAuto(YogaEdge.RIGHT);
                } else if (keyAt == R.styleable.yoga_yg_marginBottom) {
                    yogaNode.setMarginAuto(YogaEdge.BOTTOM);
                } else if (keyAt == R.styleable.yoga_yg_marginStart) {
                    yogaNode.setMarginAuto(YogaEdge.START);
                } else if (keyAt == R.styleable.yoga_yg_marginEnd) {
                    yogaNode.setMarginAuto(YogaEdge.END);
                } else if (keyAt == R.styleable.yoga_yg_marginHorizontal) {
                    yogaNode.setMarginAuto(YogaEdge.HORIZONTAL);
                } else if (keyAt == R.styleable.yoga_yg_marginVertical) {
                    yogaNode.setMarginAuto(YogaEdge.VERTICAL);
                } else if (keyAt == R.styleable.yoga_yg_marginAll) {
                    yogaNode.setMarginAuto(YogaEdge.ALL);
                }
            }
            if (str.endsWith("%")) {
                floatValue = Float.parseFloat(str.substring(0, str.length() - 1));
                if (keyAt == R.styleable.yoga_yg_flexBasis) {
                    yogaNode.setFlexBasisPercent(floatValue);
                } else if (keyAt == R.styleable.yoga_yg_height) {
                    yogaNode.setHeightPercent(floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginLeft) {
                    yogaNode.setMarginPercent(YogaEdge.LEFT, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginTop) {
                    yogaNode.setMarginPercent(YogaEdge.TOP, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginRight) {
                    yogaNode.setMarginPercent(YogaEdge.RIGHT, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginBottom) {
                    yogaNode.setMarginPercent(YogaEdge.BOTTOM, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginStart) {
                    yogaNode.setMarginPercent(YogaEdge.START, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginEnd) {
                    yogaNode.setMarginPercent(YogaEdge.END, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginHorizontal) {
                    yogaNode.setMarginPercent(YogaEdge.HORIZONTAL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginVertical) {
                    yogaNode.setMarginPercent(YogaEdge.VERTICAL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_marginAll) {
                    yogaNode.setMarginPercent(YogaEdge.ALL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_maxHeight) {
                    yogaNode.setMaxHeightPercent(floatValue);
                } else if (keyAt == R.styleable.yoga_yg_maxWidth) {
                    yogaNode.setMaxWidthPercent(floatValue);
                } else if (keyAt == R.styleable.yoga_yg_minHeight) {
                    yogaNode.setMinHeightPercent(floatValue);
                } else if (keyAt == R.styleable.yoga_yg_minWidth) {
                    yogaNode.setMinWidthPercent(floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingLeft) {
                    yogaNode.setPaddingPercent(YogaEdge.LEFT, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingTop) {
                    yogaNode.setPaddingPercent(YogaEdge.TOP, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingRight) {
                    yogaNode.setPaddingPercent(YogaEdge.RIGHT, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingBottom) {
                    yogaNode.setPaddingPercent(YogaEdge.BOTTOM, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingStart) {
                    yogaNode.setPaddingPercent(YogaEdge.START, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingEnd) {
                    yogaNode.setPaddingPercent(YogaEdge.END, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingHorizontal) {
                    yogaNode.setPaddingPercent(YogaEdge.HORIZONTAL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingVertical) {
                    yogaNode.setPaddingPercent(YogaEdge.VERTICAL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_paddingAll) {
                    yogaNode.setPaddingPercent(YogaEdge.ALL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionLeft) {
                    yogaNode.setPositionPercent(YogaEdge.LEFT, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionTop) {
                    yogaNode.setPositionPercent(YogaEdge.TOP, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionRight) {
                    yogaNode.setPositionPercent(YogaEdge.RIGHT, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionBottom) {
                    yogaNode.setPositionPercent(YogaEdge.BOTTOM, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionStart) {
                    yogaNode.setPositionPercent(YogaEdge.START, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionEnd) {
                    yogaNode.setPositionPercent(YogaEdge.END, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionHorizontal) {
                    yogaNode.setPositionPercent(YogaEdge.HORIZONTAL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionVertical) {
                    yogaNode.setPositionPercent(YogaEdge.VERTICAL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_positionAll) {
                    yogaNode.setPositionPercent(YogaEdge.ALL, floatValue);
                } else if (keyAt == R.styleable.yoga_yg_width) {
                    yogaNode.setWidthPercent(floatValue);
                }
            }
        }
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }
}
