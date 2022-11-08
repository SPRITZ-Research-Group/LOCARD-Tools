package com.google.android.exoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class AspectRatioFrameLayout extends FrameLayout {
    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;
    private float videoAspectRatio;

    public AspectRatioFrameLayout(Context context) {
        super(context);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void setAspectRatio(float f) {
        if (this.videoAspectRatio != f) {
            this.videoAspectRatio = f;
            requestLayout();
        }
    }

    protected final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.videoAspectRatio != BitmapDescriptorFactory.HUE_RED) {
            i = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f = (float) i;
            float f2 = (float) measuredHeight;
            float f3 = (this.videoAspectRatio / (f / f2)) - 1.0f;
            if (Math.abs(f3) > MAX_ASPECT_RATIO_DEFORMATION_FRACTION) {
                if (f3 > BitmapDescriptorFactory.HUE_RED) {
                    measuredHeight = (int) (f / this.videoAspectRatio);
                } else {
                    i = (int) (f2 * this.videoAspectRatio);
                }
                super.onMeasure(MeasureSpec.makeMeasureSpec(i, 1073741824), MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            }
        }
    }
}
