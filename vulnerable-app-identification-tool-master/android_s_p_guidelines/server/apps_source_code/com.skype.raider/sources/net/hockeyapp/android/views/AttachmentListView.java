package net.hockeyapp.android.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import net.hockeyapp.android.f.e;

public class AttachmentListView extends ViewGroup {
    private int a;

    public AttachmentListView(Context context) {
        super(context);
    }

    public AttachmentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public final ArrayList<Uri> a() {
        ArrayList<Uri> attachments = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            attachments.add(((AttachmentView) getChildAt(i)).a());
        }
        return attachments;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == 0) {
            e.b("AttachmentListView");
        }
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int count = getChildCount();
        int height = 0;
        int line_height = 0;
        int xPos = getPaddingLeft();
        int yPos = getPaddingTop();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            AttachmentView attachmentView = (AttachmentView) child;
            height = attachmentView.g() + attachmentView.getPaddingTop();
            if (child.getVisibility() != 8) {
                LayoutParams lp = child.getLayoutParams();
                child.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE));
                int childWidth = child.getMeasuredWidth();
                line_height = Math.max(line_height, child.getMeasuredHeight() + lp.height);
                if (xPos + childWidth > width) {
                    xPos = getPaddingLeft();
                    yPos += line_height;
                }
                xPos += lp.width + childWidth;
            }
        }
        this.a = line_height;
        if (MeasureSpec.getMode(heightMeasureSpec) == 0) {
            height = (yPos + line_height) + getPaddingBottom();
        } else if (MeasureSpec.getMode(heightMeasureSpec) == Integer.MIN_VALUE && (yPos + line_height) + getPaddingBottom() < height) {
            height = (yPos + line_height) + getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(1, 1);
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return p != null;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int width = r - l;
        int xPos = getPaddingLeft();
        int yPos = getPaddingTop();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                child.invalidate();
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                LayoutParams lp = child.getLayoutParams();
                if (xPos + childWidth > width) {
                    xPos = getPaddingLeft();
                    yPos += this.a;
                }
                child.layout(xPos, yPos, xPos + childWidth, yPos + childHeight);
                xPos += (lp.width + childWidth) + ((AttachmentView) child).f();
            }
        }
    }
}
