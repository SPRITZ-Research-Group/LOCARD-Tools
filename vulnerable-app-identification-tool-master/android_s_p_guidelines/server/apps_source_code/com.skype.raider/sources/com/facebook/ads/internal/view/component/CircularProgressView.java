package com.facebook.ads.internal.view.component;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.microsoft.react.videofxp.VideoFXPModule;

public class CircularProgressView extends View {
    private final float a = (3.0f * Resources.getSystem().getDisplayMetrics().density);
    private final RectF b = new RectF();
    private final Paint c = new Paint(1);
    private final Paint d;
    private float e = 0.0f;

    public CircularProgressView(Context context) {
        super(context);
        this.c.setStyle(Style.STROKE);
        this.c.setStrokeWidth(this.a);
        this.d = new Paint(1);
        this.d.setStyle(Style.STROKE);
        this.d.setStrokeWidth(this.a);
    }

    public final void a(int i, int i2) {
        this.c.setColor(i);
        this.d.setColor(i2);
    }

    @Keep
    public float getProgress() {
        return this.e;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.b, 0.0f, 360.0f, false, this.c);
        canvas.drawArc(this.b, -90.0f, (this.e * 360.0f) / 100.0f, false, this.d);
    }

    protected void onMeasure(int i, int i2) {
        int min = Math.min(getDefaultSize(getSuggestedMinimumHeight(), i2), getDefaultSize(getSuggestedMinimumWidth(), i));
        setMeasuredDimension(min, min);
        this.b.set(((this.a / 2.0f) + 0.0f) + ((float) getPaddingLeft()), ((this.a / 2.0f) + 0.0f) + ((float) getPaddingTop()), (((float) min) - (this.a / 2.0f)) - ((float) getPaddingRight()), (((float) min) - (this.a / 2.0f)) - ((float) getPaddingBottom()));
    }

    @Keep
    public void setProgress(float f) {
        this.e = Math.min(f, 100.0f);
        postInvalidate();
    }

    public void setProgressWithAnimation(float f) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, new float[]{f});
        ofFloat.setDuration(400);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
    }
}
