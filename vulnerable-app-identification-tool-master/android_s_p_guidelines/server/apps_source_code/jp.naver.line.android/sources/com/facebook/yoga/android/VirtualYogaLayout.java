package com.facebook.yoga.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.android.YogaLayout.LayoutParams;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VirtualYogaLayout extends ViewGroup {
    private final List<View> mChildren;
    private final YogaNode mYogaNode;
    private final Map<View, YogaNode> mYogaNodes;

    public VirtualYogaLayout(Context context) {
        super(context);
        this.mChildren = new LinkedList();
        this.mYogaNodes = new HashMap();
        this.mYogaNode = new YogaNode();
    }

    public VirtualYogaLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VirtualYogaLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChildren = new LinkedList();
        this.mYogaNodes = new HashMap();
        this.mYogaNode = new YogaNode();
        YogaLayout.applyLayoutParams(new LayoutParams(context, attributeSet), this.mYogaNode, this);
    }

    public YogaNode getYogaNode() {
        return this.mYogaNode;
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof VirtualYogaLayout) {
            VirtualYogaLayout virtualYogaLayout = (VirtualYogaLayout) view;
            virtualYogaLayout.transferChildren(this);
            this.mYogaNode.addChildAt(virtualYogaLayout.getYogaNode(), this.mYogaNode.getChildCount());
            return;
        }
        YogaNode yogaNode = new YogaNode();
        YogaLayout.applyLayoutParams(new LayoutParams(layoutParams), yogaNode, view);
        yogaNode.setData(view);
        yogaNode.setMeasureFunction(new a());
        this.mYogaNode.addChildAt(yogaNode, this.mYogaNode.getChildCount());
        addView(view, yogaNode);
    }

    public void addView(View view, YogaNode yogaNode) {
        this.mChildren.add(view);
        this.mYogaNodes.put(view, yogaNode);
    }

    protected void transferChildren(ViewGroup viewGroup) {
        if (viewGroup instanceof VirtualYogaLayout) {
            for (View view : this.mChildren) {
                ((VirtualYogaLayout) viewGroup).addView(view, (YogaNode) this.mYogaNodes.get(view));
            }
        } else if (viewGroup instanceof YogaLayout) {
            for (View view2 : this.mChildren) {
                ((YogaLayout) viewGroup).addView(view2, (YogaNode) this.mYogaNodes.get(view2));
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder("VirtualYogaLayout cannot transfer children to ViewGroup of type ");
            stringBuilder.append(viewGroup.getClass().getCanonicalName());
            stringBuilder.append(".  Must either be a VirtualYogaLayout or a YogaLayout.");
            throw new RuntimeException(stringBuilder.toString());
        }
        this.mChildren.clear();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        throw new RuntimeException("Attempting to layout a VirtualYogaLayout");
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }
}
