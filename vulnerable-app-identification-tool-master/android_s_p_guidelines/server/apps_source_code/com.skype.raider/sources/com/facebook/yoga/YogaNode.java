package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@DoNotStrip
public class YogaNode {
    private static final int BORDER = 4;
    private static final int MARGIN = 1;
    private static final int PADDING = 2;
    private YogaBaselineFunction mBaselineFunction;
    @DoNotStrip
    private float mBorderBottom;
    @DoNotStrip
    private float mBorderLeft;
    @DoNotStrip
    private float mBorderRight;
    @DoNotStrip
    private float mBorderTop;
    private List<YogaNode> mChildren;
    private Object mData;
    @DoNotStrip
    private int mEdgeSetFlag;
    @DoNotStrip
    private boolean mHasNewLayout;
    private boolean mHasSetPosition;
    @DoNotStrip
    private float mHeight;
    @DoNotStrip
    private int mLayoutDirection;
    @DoNotStrip
    private float mLeft;
    @DoNotStrip
    private float mMarginBottom;
    @DoNotStrip
    private float mMarginLeft;
    @DoNotStrip
    private float mMarginRight;
    @DoNotStrip
    private float mMarginTop;
    private YogaMeasureFunction mMeasureFunction;
    private long mNativePointer;
    @DoNotStrip
    private float mPaddingBottom;
    @DoNotStrip
    private float mPaddingLeft;
    @DoNotStrip
    private float mPaddingRight;
    @DoNotStrip
    private float mPaddingTop;
    private YogaNode mParent;
    @DoNotStrip
    private float mTop;
    @DoNotStrip
    private float mWidth;

    private native void jni_YGNodeCalculateLayout(long j, float f, float f2);

    private native void jni_YGNodeCopyStyle(long j, long j2);

    private native void jni_YGNodeFree(long j);

    static native int jni_YGNodeGetInstanceCount();

    private native void jni_YGNodeInsertChild(long j, long j2, int i);

    private native boolean jni_YGNodeIsDirty(long j);

    private native void jni_YGNodeMarkDirty(long j);

    private native long jni_YGNodeNew();

    private native long jni_YGNodeNewWithConfig(long j);

    private native void jni_YGNodePrint(long j);

    private native void jni_YGNodeRemoveChild(long j, long j2);

    private native void jni_YGNodeReset(long j);

    private native void jni_YGNodeSetHasBaselineFunc(long j, boolean z);

    private native void jni_YGNodeSetHasMeasureFunc(long j, boolean z);

    private native int jni_YGNodeStyleGetAlignContent(long j);

    private native int jni_YGNodeStyleGetAlignItems(long j);

    private native int jni_YGNodeStyleGetAlignSelf(long j);

    private native float jni_YGNodeStyleGetAspectRatio(long j);

    private native float jni_YGNodeStyleGetBorder(long j, int i);

    private native int jni_YGNodeStyleGetDirection(long j);

    private native int jni_YGNodeStyleGetDisplay(long j);

    private native Object jni_YGNodeStyleGetFlexBasis(long j);

    private native int jni_YGNodeStyleGetFlexDirection(long j);

    private native float jni_YGNodeStyleGetFlexGrow(long j);

    private native float jni_YGNodeStyleGetFlexShrink(long j);

    private native Object jni_YGNodeStyleGetHeight(long j);

    private native int jni_YGNodeStyleGetJustifyContent(long j);

    private native Object jni_YGNodeStyleGetMargin(long j, int i);

    private native Object jni_YGNodeStyleGetMaxHeight(long j);

    private native Object jni_YGNodeStyleGetMaxWidth(long j);

    private native Object jni_YGNodeStyleGetMinHeight(long j);

    private native Object jni_YGNodeStyleGetMinWidth(long j);

    private native int jni_YGNodeStyleGetOverflow(long j);

    private native Object jni_YGNodeStyleGetPadding(long j, int i);

    private native Object jni_YGNodeStyleGetPosition(long j, int i);

    private native int jni_YGNodeStyleGetPositionType(long j);

    private native Object jni_YGNodeStyleGetWidth(long j);

    private native void jni_YGNodeStyleSetAlignContent(long j, int i);

    private native void jni_YGNodeStyleSetAlignItems(long j, int i);

    private native void jni_YGNodeStyleSetAlignSelf(long j, int i);

    private native void jni_YGNodeStyleSetAspectRatio(long j, float f);

    private native void jni_YGNodeStyleSetBorder(long j, int i, float f);

    private native void jni_YGNodeStyleSetDirection(long j, int i);

    private native void jni_YGNodeStyleSetDisplay(long j, int i);

    private native void jni_YGNodeStyleSetFlex(long j, float f);

    private native void jni_YGNodeStyleSetFlexBasis(long j, float f);

    private native void jni_YGNodeStyleSetFlexBasisAuto(long j);

    private native void jni_YGNodeStyleSetFlexBasisPercent(long j, float f);

    private native void jni_YGNodeStyleSetFlexDirection(long j, int i);

    private native void jni_YGNodeStyleSetFlexGrow(long j, float f);

    private native void jni_YGNodeStyleSetFlexShrink(long j, float f);

    private native void jni_YGNodeStyleSetFlexWrap(long j, int i);

    private native void jni_YGNodeStyleSetHeight(long j, float f);

    private native void jni_YGNodeStyleSetHeightAuto(long j);

    private native void jni_YGNodeStyleSetHeightPercent(long j, float f);

    private native void jni_YGNodeStyleSetJustifyContent(long j, int i);

    private native void jni_YGNodeStyleSetMargin(long j, int i, float f);

    private native void jni_YGNodeStyleSetMarginAuto(long j, int i);

    private native void jni_YGNodeStyleSetMarginPercent(long j, int i, float f);

    private native void jni_YGNodeStyleSetMaxHeight(long j, float f);

    private native void jni_YGNodeStyleSetMaxHeightPercent(long j, float f);

    private native void jni_YGNodeStyleSetMaxWidth(long j, float f);

    private native void jni_YGNodeStyleSetMaxWidthPercent(long j, float f);

    private native void jni_YGNodeStyleSetMinHeight(long j, float f);

    private native void jni_YGNodeStyleSetMinHeightPercent(long j, float f);

    private native void jni_YGNodeStyleSetMinWidth(long j, float f);

    private native void jni_YGNodeStyleSetMinWidthPercent(long j, float f);

    private native void jni_YGNodeStyleSetOverflow(long j, int i);

    private native void jni_YGNodeStyleSetPadding(long j, int i, float f);

    private native void jni_YGNodeStyleSetPaddingPercent(long j, int i, float f);

    private native void jni_YGNodeStyleSetPosition(long j, int i, float f);

    private native void jni_YGNodeStyleSetPositionPercent(long j, int i, float f);

    private native void jni_YGNodeStyleSetPositionType(long j, int i);

    private native void jni_YGNodeStyleSetWidth(long j, float f);

    private native void jni_YGNodeStyleSetWidthAuto(long j);

    private native void jni_YGNodeStyleSetWidthPercent(long j, float f);

    static {
        SoLoader.a("yoga");
    }

    public YogaNode() {
        this.mEdgeSetFlag = 0;
        this.mHasSetPosition = false;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = 0.0f;
        this.mMarginTop = 0.0f;
        this.mMarginRight = 0.0f;
        this.mMarginBottom = 0.0f;
        this.mPaddingLeft = 0.0f;
        this.mPaddingTop = 0.0f;
        this.mPaddingRight = 0.0f;
        this.mPaddingBottom = 0.0f;
        this.mBorderLeft = 0.0f;
        this.mBorderTop = 0.0f;
        this.mBorderRight = 0.0f;
        this.mBorderBottom = 0.0f;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        this.mNativePointer = jni_YGNodeNew();
        if (this.mNativePointer == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
        setOverflow(YogaOverflow.HIDDEN);
    }

    public YogaNode(YogaConfig config) {
        this.mEdgeSetFlag = 0;
        this.mHasSetPosition = false;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = 0.0f;
        this.mMarginTop = 0.0f;
        this.mMarginRight = 0.0f;
        this.mMarginBottom = 0.0f;
        this.mPaddingLeft = 0.0f;
        this.mPaddingTop = 0.0f;
        this.mPaddingRight = 0.0f;
        this.mPaddingBottom = 0.0f;
        this.mBorderLeft = 0.0f;
        this.mBorderTop = 0.0f;
        this.mBorderRight = 0.0f;
        this.mBorderBottom = 0.0f;
        this.mLayoutDirection = 0;
        this.mHasNewLayout = true;
        this.mNativePointer = jni_YGNodeNewWithConfig(config.mNativePointer);
        if (this.mNativePointer == 0) {
            throw new IllegalStateException("Failed to allocate native memory");
        }
    }

    protected void finalize() throws Throwable {
        try {
            jni_YGNodeFree(this.mNativePointer);
        } finally {
            super.finalize();
        }
    }

    public void reset() {
        this.mEdgeSetFlag = 0;
        this.mHasSetPosition = false;
        this.mHasNewLayout = true;
        this.mWidth = Float.NaN;
        this.mHeight = Float.NaN;
        this.mTop = Float.NaN;
        this.mLeft = Float.NaN;
        this.mMarginLeft = 0.0f;
        this.mMarginTop = 0.0f;
        this.mMarginRight = 0.0f;
        this.mMarginBottom = 0.0f;
        this.mPaddingLeft = 0.0f;
        this.mPaddingTop = 0.0f;
        this.mPaddingRight = 0.0f;
        this.mPaddingBottom = 0.0f;
        this.mBorderLeft = 0.0f;
        this.mBorderTop = 0.0f;
        this.mBorderRight = 0.0f;
        this.mBorderBottom = 0.0f;
        this.mLayoutDirection = 0;
        this.mMeasureFunction = null;
        this.mBaselineFunction = null;
        this.mData = null;
        jni_YGNodeReset(this.mNativePointer);
        setOverflow(YogaOverflow.HIDDEN);
    }

    public int getChildCount() {
        return this.mChildren == null ? 0 : this.mChildren.size();
    }

    public YogaNode getChildAt(int i) {
        return (YogaNode) this.mChildren.get(i);
    }

    public void addChildAt(YogaNode child, int i) {
        if (child.mParent != null) {
            throw new IllegalStateException("Child already has a parent, it must be removed first.");
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList(4);
        }
        this.mChildren.add(i, child);
        child.mParent = this;
        jni_YGNodeInsertChild(this.mNativePointer, child.mNativePointer, i);
    }

    public YogaNode removeChildAt(int i) {
        YogaNode child = (YogaNode) this.mChildren.remove(i);
        child.mParent = null;
        jni_YGNodeRemoveChild(this.mNativePointer, child.mNativePointer);
        return child;
    }

    @Nullable
    public YogaNode getParent() {
        return this.mParent;
    }

    public int indexOf(YogaNode child) {
        return this.mChildren == null ? -1 : this.mChildren.indexOf(child);
    }

    public void calculateLayout(float width, float height) {
        jni_YGNodeCalculateLayout(this.mNativePointer, width, height);
    }

    public boolean hasNewLayout() {
        return this.mHasNewLayout;
    }

    public void dirty() {
        jni_YGNodeMarkDirty(this.mNativePointer);
    }

    public boolean isDirty() {
        return jni_YGNodeIsDirty(this.mNativePointer);
    }

    public void copyStyle(YogaNode srcNode) {
        jni_YGNodeCopyStyle(this.mNativePointer, srcNode.mNativePointer);
    }

    public void markLayoutSeen() {
        this.mHasNewLayout = false;
    }

    public YogaDirection getStyleDirection() {
        return YogaDirection.fromInt(jni_YGNodeStyleGetDirection(this.mNativePointer));
    }

    public void setDirection(YogaDirection direction) {
        jni_YGNodeStyleSetDirection(this.mNativePointer, direction.intValue());
    }

    public YogaFlexDirection getFlexDirection() {
        return YogaFlexDirection.fromInt(jni_YGNodeStyleGetFlexDirection(this.mNativePointer));
    }

    public void setFlexDirection(YogaFlexDirection flexDirection) {
        jni_YGNodeStyleSetFlexDirection(this.mNativePointer, flexDirection.intValue());
    }

    public YogaJustify getJustifyContent() {
        return YogaJustify.fromInt(jni_YGNodeStyleGetJustifyContent(this.mNativePointer));
    }

    public void setJustifyContent(YogaJustify justifyContent) {
        jni_YGNodeStyleSetJustifyContent(this.mNativePointer, justifyContent.intValue());
    }

    public YogaAlign getAlignItems() {
        return YogaAlign.fromInt(jni_YGNodeStyleGetAlignItems(this.mNativePointer));
    }

    public void setAlignItems(YogaAlign alignItems) {
        jni_YGNodeStyleSetAlignItems(this.mNativePointer, alignItems.intValue());
    }

    public YogaAlign getAlignSelf() {
        return YogaAlign.fromInt(jni_YGNodeStyleGetAlignSelf(this.mNativePointer));
    }

    public void setAlignSelf(YogaAlign alignSelf) {
        jni_YGNodeStyleSetAlignSelf(this.mNativePointer, alignSelf.intValue());
    }

    public YogaAlign getAlignContent() {
        return YogaAlign.fromInt(jni_YGNodeStyleGetAlignContent(this.mNativePointer));
    }

    public void setAlignContent(YogaAlign alignContent) {
        jni_YGNodeStyleSetAlignContent(this.mNativePointer, alignContent.intValue());
    }

    public YogaPositionType getPositionType() {
        return YogaPositionType.fromInt(jni_YGNodeStyleGetPositionType(this.mNativePointer));
    }

    public void setPositionType(YogaPositionType positionType) {
        jni_YGNodeStyleSetPositionType(this.mNativePointer, positionType.intValue());
    }

    public void setWrap(YogaWrap flexWrap) {
        jni_YGNodeStyleSetFlexWrap(this.mNativePointer, flexWrap.intValue());
    }

    public YogaOverflow getOverflow() {
        return YogaOverflow.fromInt(jni_YGNodeStyleGetOverflow(this.mNativePointer));
    }

    public void setOverflow(YogaOverflow overflow) {
        jni_YGNodeStyleSetOverflow(this.mNativePointer, overflow.intValue());
    }

    public YogaDisplay getDisplay() {
        return YogaDisplay.fromInt(jni_YGNodeStyleGetDisplay(this.mNativePointer));
    }

    public void setDisplay(YogaDisplay display) {
        jni_YGNodeStyleSetDisplay(this.mNativePointer, display.intValue());
    }

    public void setFlex(float flex) {
        jni_YGNodeStyleSetFlex(this.mNativePointer, flex);
    }

    public float getFlexGrow() {
        return jni_YGNodeStyleGetFlexGrow(this.mNativePointer);
    }

    public void setFlexGrow(float flexGrow) {
        jni_YGNodeStyleSetFlexGrow(this.mNativePointer, flexGrow);
    }

    public float getFlexShrink() {
        return jni_YGNodeStyleGetFlexShrink(this.mNativePointer);
    }

    public void setFlexShrink(float flexShrink) {
        jni_YGNodeStyleSetFlexShrink(this.mNativePointer, flexShrink);
    }

    public YogaValue getFlexBasis() {
        return (YogaValue) jni_YGNodeStyleGetFlexBasis(this.mNativePointer);
    }

    public void setFlexBasis(float flexBasis) {
        jni_YGNodeStyleSetFlexBasis(this.mNativePointer, flexBasis);
    }

    public void setFlexBasisPercent(float percent) {
        jni_YGNodeStyleSetFlexBasisPercent(this.mNativePointer, percent);
    }

    public void setFlexBasisAuto() {
        jni_YGNodeStyleSetFlexBasisAuto(this.mNativePointer);
    }

    public YogaValue getMargin(YogaEdge edge) {
        if ((this.mEdgeSetFlag & 1) != 1) {
            return YogaValue.UNDEFINED;
        }
        return (YogaValue) jni_YGNodeStyleGetMargin(this.mNativePointer, edge.intValue());
    }

    public void setMargin(YogaEdge edge, float margin) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMargin(this.mNativePointer, edge.intValue(), margin);
    }

    public void setMarginPercent(YogaEdge edge, float percent) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMarginPercent(this.mNativePointer, edge.intValue(), percent);
    }

    public void setMarginAuto(YogaEdge edge) {
        this.mEdgeSetFlag |= 1;
        jni_YGNodeStyleSetMarginAuto(this.mNativePointer, edge.intValue());
    }

    public YogaValue getPadding(YogaEdge edge) {
        if ((this.mEdgeSetFlag & 2) != 2) {
            return YogaValue.UNDEFINED;
        }
        return (YogaValue) jni_YGNodeStyleGetPadding(this.mNativePointer, edge.intValue());
    }

    public void setPadding(YogaEdge edge, float padding) {
        this.mEdgeSetFlag |= 2;
        jni_YGNodeStyleSetPadding(this.mNativePointer, edge.intValue(), padding);
    }

    public void setPaddingPercent(YogaEdge edge, float percent) {
        this.mEdgeSetFlag |= 2;
        jni_YGNodeStyleSetPaddingPercent(this.mNativePointer, edge.intValue(), percent);
    }

    public float getBorder(YogaEdge edge) {
        if ((this.mEdgeSetFlag & 4) != 4) {
            return Float.NaN;
        }
        return jni_YGNodeStyleGetBorder(this.mNativePointer, edge.intValue());
    }

    public void setBorder(YogaEdge edge, float border) {
        this.mEdgeSetFlag |= 4;
        jni_YGNodeStyleSetBorder(this.mNativePointer, edge.intValue(), border);
    }

    public YogaValue getPosition(YogaEdge edge) {
        if (this.mHasSetPosition) {
            return (YogaValue) jni_YGNodeStyleGetPosition(this.mNativePointer, edge.intValue());
        }
        return YogaValue.UNDEFINED;
    }

    public void setPosition(YogaEdge edge, float position) {
        this.mHasSetPosition = true;
        jni_YGNodeStyleSetPosition(this.mNativePointer, edge.intValue(), position);
    }

    public void setPositionPercent(YogaEdge edge, float percent) {
        this.mHasSetPosition = true;
        jni_YGNodeStyleSetPositionPercent(this.mNativePointer, edge.intValue(), percent);
    }

    public YogaValue getWidth() {
        return (YogaValue) jni_YGNodeStyleGetWidth(this.mNativePointer);
    }

    public void setWidth(float width) {
        jni_YGNodeStyleSetWidth(this.mNativePointer, width);
    }

    public void setWidthPercent(float percent) {
        jni_YGNodeStyleSetWidthPercent(this.mNativePointer, percent);
    }

    public void setWidthAuto() {
        jni_YGNodeStyleSetWidthAuto(this.mNativePointer);
    }

    public YogaValue getHeight() {
        return (YogaValue) jni_YGNodeStyleGetHeight(this.mNativePointer);
    }

    public void setHeight(float height) {
        jni_YGNodeStyleSetHeight(this.mNativePointer, height);
    }

    public void setHeightPercent(float percent) {
        jni_YGNodeStyleSetHeightPercent(this.mNativePointer, percent);
    }

    public void setHeightAuto() {
        jni_YGNodeStyleSetHeightAuto(this.mNativePointer);
    }

    public YogaValue getMinWidth() {
        return (YogaValue) jni_YGNodeStyleGetMinWidth(this.mNativePointer);
    }

    public void setMinWidth(float minWidth) {
        jni_YGNodeStyleSetMinWidth(this.mNativePointer, minWidth);
    }

    public void setMinWidthPercent(float percent) {
        jni_YGNodeStyleSetMinWidthPercent(this.mNativePointer, percent);
    }

    public YogaValue getMinHeight() {
        return (YogaValue) jni_YGNodeStyleGetMinHeight(this.mNativePointer);
    }

    public void setMinHeight(float minHeight) {
        jni_YGNodeStyleSetMinHeight(this.mNativePointer, minHeight);
    }

    public void setMinHeightPercent(float percent) {
        jni_YGNodeStyleSetMinHeightPercent(this.mNativePointer, percent);
    }

    public YogaValue getMaxWidth() {
        return (YogaValue) jni_YGNodeStyleGetMaxWidth(this.mNativePointer);
    }

    public void setMaxWidth(float maxWidth) {
        jni_YGNodeStyleSetMaxWidth(this.mNativePointer, maxWidth);
    }

    public void setMaxWidthPercent(float percent) {
        jni_YGNodeStyleSetMaxWidthPercent(this.mNativePointer, percent);
    }

    public YogaValue getMaxHeight() {
        return (YogaValue) jni_YGNodeStyleGetMaxHeight(this.mNativePointer);
    }

    public void setMaxHeight(float maxheight) {
        jni_YGNodeStyleSetMaxHeight(this.mNativePointer, maxheight);
    }

    public void setMaxHeightPercent(float percent) {
        jni_YGNodeStyleSetMaxHeightPercent(this.mNativePointer, percent);
    }

    public float getAspectRatio() {
        return jni_YGNodeStyleGetAspectRatio(this.mNativePointer);
    }

    public void setAspectRatio(float aspectRatio) {
        jni_YGNodeStyleSetAspectRatio(this.mNativePointer, aspectRatio);
    }

    public float getLayoutX() {
        return this.mLeft;
    }

    public float getLayoutY() {
        return this.mTop;
    }

    public float getLayoutWidth() {
        return this.mWidth;
    }

    public float getLayoutHeight() {
        return this.mHeight;
    }

    public float getLayoutMargin(YogaEdge edge) {
        switch (edge) {
            case LEFT:
                return this.mMarginLeft;
            case TOP:
                return this.mMarginTop;
            case RIGHT:
                return this.mMarginRight;
            case BOTTOM:
                return this.mMarginBottom;
            case START:
                return getLayoutDirection() == YogaDirection.RTL ? this.mMarginRight : this.mMarginLeft;
            case END:
                return getLayoutDirection() == YogaDirection.RTL ? this.mMarginLeft : this.mMarginRight;
            default:
                throw new IllegalArgumentException("Cannot get layout margins of multi-edge shorthands");
        }
    }

    public float getLayoutPadding(YogaEdge edge) {
        switch (edge) {
            case LEFT:
                return this.mPaddingLeft;
            case TOP:
                return this.mPaddingTop;
            case RIGHT:
                return this.mPaddingRight;
            case BOTTOM:
                return this.mPaddingBottom;
            case START:
                return getLayoutDirection() == YogaDirection.RTL ? this.mPaddingRight : this.mPaddingLeft;
            case END:
                return getLayoutDirection() == YogaDirection.RTL ? this.mPaddingLeft : this.mPaddingRight;
            default:
                throw new IllegalArgumentException("Cannot get layout paddings of multi-edge shorthands");
        }
    }

    public float getLayoutBorder(YogaEdge edge) {
        switch (edge) {
            case LEFT:
                return this.mBorderLeft;
            case TOP:
                return this.mBorderTop;
            case RIGHT:
                return this.mBorderRight;
            case BOTTOM:
                return this.mBorderBottom;
            case START:
                return getLayoutDirection() == YogaDirection.RTL ? this.mBorderRight : this.mBorderLeft;
            case END:
                return getLayoutDirection() == YogaDirection.RTL ? this.mBorderLeft : this.mBorderRight;
            default:
                throw new IllegalArgumentException("Cannot get layout border of multi-edge shorthands");
        }
    }

    public YogaDirection getLayoutDirection() {
        return YogaDirection.fromInt(this.mLayoutDirection);
    }

    public void setMeasureFunction(YogaMeasureFunction measureFunction) {
        this.mMeasureFunction = measureFunction;
        jni_YGNodeSetHasMeasureFunc(this.mNativePointer, measureFunction != null);
    }

    @DoNotStrip
    public final long measure(float width, int widthMode, float height, int heightMode) {
        if (isMeasureDefined()) {
            return this.mMeasureFunction.measure(this, width, YogaMeasureMode.fromInt(widthMode), height, YogaMeasureMode.fromInt(heightMode));
        }
        throw new RuntimeException("Measure function isn't defined!");
    }

    public void setBaselineFunction(YogaBaselineFunction baselineFunction) {
        this.mBaselineFunction = baselineFunction;
        jni_YGNodeSetHasBaselineFunc(this.mNativePointer, baselineFunction != null);
    }

    @DoNotStrip
    public final float baseline(float width, float height) {
        return this.mBaselineFunction.baseline(this, width, height);
    }

    public boolean isMeasureDefined() {
        return this.mMeasureFunction != null;
    }

    public void setData(Object data) {
        this.mData = data;
    }

    public Object getData() {
        return this.mData;
    }

    public void print() {
        jni_YGNodePrint(this.mNativePointer);
    }
}
