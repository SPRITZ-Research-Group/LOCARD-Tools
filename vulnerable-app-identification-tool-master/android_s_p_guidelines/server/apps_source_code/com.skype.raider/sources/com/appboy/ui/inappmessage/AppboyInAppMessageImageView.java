package com.appboy.ui.inappmessage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.appboy.b.a.b;
import com.appboy.f.c;

@SuppressLint({"AppCompatCustomView"})
public class AppboyInAppMessageImageView extends ImageView implements IInAppMessageImageView {
    private static final String TAG = c.a(AppboyInAppMessageImageView.class);
    private Path mClipPath = new Path();
    private float[] mInAppRadii;
    private RectF mRect = new RectF();

    public AppboyInAppMessageImageView(Context context, AttributeSet attrs) {
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
            setScaleType(ScaleType.FIT_CENTER);
        } else if (cropType.equals(b.CENTER_CROP)) {
            setScaleType(ScaleType.CENTER_CROP);
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
