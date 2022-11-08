package com.facebook.ads.internal.view.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

@TargetApi(19)
public class b extends ProgressBar {
    private static final int a = Color.argb(26, 0, 0, 0);
    private static final int b = Color.rgb(88, 144, 255);
    private Rect c = new Rect();
    private Paint d = new Paint();

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setIndeterminate(false);
        setMax(100);
        ColorDrawable colorDrawable = new ColorDrawable(0);
        ClipDrawable clipDrawable = new ClipDrawable(new ColorDrawable(b), 3, 1);
        setProgressDrawable(new LayerDrawable(new Drawable[]{colorDrawable, clipDrawable}));
        this.d.setStyle(Style.FILL);
        this.d.setColor(a);
    }

    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawRect(this.c, this.d);
        super.onDraw(canvas);
    }

    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.c.set(0, 0, getMeasuredWidth(), 2);
    }

    public synchronized void setProgress(int i) {
        super.setProgress(i == 100 ? 0 : Math.max(i, 5));
    }
}
