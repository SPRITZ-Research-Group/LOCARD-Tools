package com.skypecam.obscura.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.skypecam.obscura.e.g;

public class CameraView extends ViewGroup {
    private CameraViewFinder a;

    public CameraView(Context context) {
        this(context, null);
    }

    public CameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public final CameraViewFinder a() {
        return this.a;
    }

    public final void a(CameraView otherView) {
        if (!(otherView == null || otherView.a == null)) {
            g.a().b("CameraView", "stealViewFinderFrom: steal");
            CameraViewFinder cameraViewFinder = otherView.a;
            otherView.a = null;
            this.a = cameraViewFinder;
            otherView.removeAllViews();
        }
        if (this.a == null) {
            g.a().b("CameraView", "CameraStateMachine stealViewFinderFrom: new");
            this.a = new CameraViewFinder(getContext());
        }
        if (this.a.getParent() != null) {
            g.a().c("CameraView", "CameraStateMachine stealViewFinderFrom: still has parent; steal may have failed (equals: " + (this.a.getParent() == this) + ")");
        } else {
            addView(this.a);
        }
        a(getLeft(), getTop(), getRight(), getBottom());
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        g.a().b("CameraView", "onLayout");
        if (this.a != null) {
            a(left, top, right, bottom);
        }
    }

    private void a(int left, int top, int right, int bottom) {
        g.a().b("CameraView", "layoutViewFinder " + left + "-" + top + "-" + right + "-" + bottom);
        this.a.layout(left, top, right, bottom);
        postInvalidate(getLeft(), getTop(), getRight(), getBottom());
    }
}
