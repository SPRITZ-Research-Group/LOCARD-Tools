package com.facebook.share.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.common.b;
import com.facebook.common.c;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@Deprecated
public class LikeBoxCountView extends FrameLayout {
    private TextView a;
    private u b = u.LEFT;
    private float c;
    private float d;
    private float e;
    private Paint f;
    private int g;
    private int h;

    @Deprecated
    public LikeBoxCountView(Context context) {
        super(context);
        setWillNotDraw(false);
        this.c = getResources().getDimension(c.com_facebook_likeboxcountview_caret_height);
        this.d = getResources().getDimension(c.com_facebook_likeboxcountview_caret_width);
        this.e = getResources().getDimension(c.com_facebook_likeboxcountview_border_radius);
        this.f = new Paint();
        this.f.setColor(getResources().getColor(b.com_facebook_likeboxcountview_border_color));
        this.f.setStrokeWidth(getResources().getDimension(c.com_facebook_likeboxcountview_border_width));
        this.f.setStyle(Style.STROKE);
        this.a = new TextView(context);
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        this.a.setGravity(17);
        this.a.setTextSize(0, getResources().getDimension(c.com_facebook_likeboxcountview_text_size));
        this.a.setTextColor(getResources().getColor(b.com_facebook_likeboxcountview_text_color));
        this.g = getResources().getDimensionPixelSize(c.com_facebook_likeboxcountview_text_padding);
        this.h = getResources().getDimensionPixelSize(c.com_facebook_likeboxcountview_caret_height);
        addView(this.a);
        setCaretPosition(this.b);
    }

    @Deprecated
    public void setText(String str) {
        this.a.setText(str);
    }

    @Deprecated
    public void setCaretPosition(u uVar) {
        this.b = uVar;
        switch (uVar) {
            case LEFT:
                a(this.h, 0, 0, 0);
                return;
            case TOP:
                a(0, this.h, 0, 0);
                return;
            case RIGHT:
                a(0, 0, this.h, 0);
                return;
            case BOTTOM:
                a(0, 0, 0, this.h);
                break;
        }
    }

    protected void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        switch (this.b) {
            case LEFT:
                paddingLeft = (int) (((float) paddingLeft) + this.c);
                break;
            case TOP:
                paddingTop = (int) (((float) paddingTop) + this.c);
                break;
            case RIGHT:
                width = (int) (((float) width) - this.c);
                break;
            case BOTTOM:
                height = (int) (((float) height) - this.c);
                break;
        }
        float f2 = (float) paddingLeft;
        float f3 = (float) paddingTop;
        float f4 = (float) width;
        float f5 = (float) height;
        Path path = new Path();
        float f6 = this.e * 2.0f;
        float f7 = f2 + f6;
        float f8 = f3 + f6;
        path.addArc(new RectF(f2, f3, f7, f8), -180.0f, 90.0f);
        if (this.b == u.TOP) {
            f = f4 - f2;
            path.lineTo(((f - this.d) / 2.0f) + f2, f3);
            path.lineTo((f / 2.0f) + f2, f3 - this.c);
            path.lineTo(((f + this.d) / 2.0f) + f2, f3);
        }
        path.lineTo(f4 - this.e, f3);
        float f9 = f4 - f6;
        path.addArc(new RectF(f9, f3, f4, f8), -90.0f, 90.0f);
        if (this.b == u.RIGHT) {
            f = f5 - f3;
            path.lineTo(f4, ((f - this.d) / 2.0f) + f3);
            path.lineTo(this.c + f4, (f / 2.0f) + f3);
            path.lineTo(f4, ((f + this.d) / 2.0f) + f3);
        }
        path.lineTo(f4, f5 - this.e);
        f6 = f5 - f6;
        path.addArc(new RectF(f9, f6, f4, f5), BitmapDescriptorFactory.HUE_RED, 90.0f);
        if (this.b == u.BOTTOM) {
            f4 -= f2;
            path.lineTo(((this.d + f4) / 2.0f) + f2, f5);
            path.lineTo((f4 / 2.0f) + f2, this.c + f5);
            path.lineTo(((f4 - this.d) / 2.0f) + f2, f5);
        }
        path.lineTo(this.e + f2, f5);
        path.addArc(new RectF(f2, f6, f7, f5), 90.0f, 90.0f);
        if (this.b == u.LEFT) {
            f5 -= f3;
            path.lineTo(f2, ((this.d + f5) / 2.0f) + f3);
            path.lineTo(f2 - this.c, (f5 / 2.0f) + f3);
            path.lineTo(f2, ((f5 - this.d) / 2.0f) + f3);
        }
        path.lineTo(f2, f3 + this.e);
        canvas.drawPath(path, this.f);
    }

    private void a(int i, int i2, int i3, int i4) {
        this.a.setPadding(this.g + i, this.g + i2, this.g + i3, this.g + i4);
    }
}
