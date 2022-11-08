package com.appboy.ui.inappmessage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.appboy.b.a.b;
import com.appboy.f.c;
import com.facebook.drawee.c.q;
import com.facebook.drawee.d.a;
import com.facebook.drawee.view.SimpleDraweeView;

public class AppboyInAppMessageSimpleDraweeView extends SimpleDraweeView implements IInAppMessageImageView {
    private static final String TAG = c.a(AppboyInAppMessageSimpleDraweeView.class);
    private Path mClipPath = new Path();
    private float[] mInAppRadii;
    private RectF mRect = new RectF();

    public AppboyInAppMessageSimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCornersRadiiPx(float topLeft, float topRight, float bottomLeft, float bottomRight) {
        this.mInAppRadii = new float[]{topLeft, topLeft, topRight, topRight, bottomLeft, bottomLeft, bottomRight, bottomRight};
    }

    public void setCornersRadiusPx(float cornersRadius) {
        setCornersRadiiPx(cornersRadius, cornersRadius, cornersRadius, cornersRadius);
    }

    public void setInAppMessageImageCropType(b cropType) {
        if (cropType.equals(b.FIT_CENTER)) {
            ((a) getHierarchy()).a(q.b.c);
        } else if (cropType.equals(b.CENTER_CROP)) {
            ((a) getHierarchy()).a(q.b.g);
        }
    }

    protected void onDraw(Canvas canvas) {
        clipCanvasToPath(canvas, getWidth(), getHeight());
        super.onDraw(canvas);
    }

    boolean clipCanvasToPath(Canvas canvas, int widthPx, int heightPx) {
        if (this.mInAppRadii == null) {
            return false;
        }
        try {
            this.mClipPath.reset();
            this.mRect.set(0.0f, 0.0f, (float) widthPx, (float) heightPx);
            this.mClipPath.addRoundRect(this.mRect, this.mInAppRadii, Direction.CW);
            canvas.clipPath(this.mClipPath);
            return true;
        } catch (Exception e) {
            c.d(TAG, "Encountered exception while trying to clip in-app message image", e);
            return false;
        }
    }
}
