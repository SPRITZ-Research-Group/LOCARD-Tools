package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.u;

public class d extends View {
    private Paint a;
    private Paint b;
    private Paint c;
    private int d;
    private boolean e;

    public d(Context context) {
        this(context, (byte) 0);
    }

    private d(Context context, byte b) {
        super(context);
        this.d = 60;
        this.e = true;
        this.a = new Paint();
        this.a.setColor(-3355444);
        this.a.setStyle(Style.STROKE);
        this.a.setStrokeWidth(3.0f);
        this.a.setAntiAlias(true);
        this.b = new Paint();
        this.b.setColor(-1287371708);
        this.b.setStyle(Style.FILL);
        this.b.setAntiAlias(true);
        this.c = new Paint();
        this.c.setColor(-1);
        this.c.setStyle(Style.STROKE);
        this.c.setStrokeWidth(6.0f);
        this.c.setAntiAlias(true);
        float f = u.b;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((float) this.d) * f), (int) (f * ((float) this.d)));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        setLayoutParams(layoutParams);
    }

    protected void onDraw(Canvas canvas) {
        if (this.e) {
            if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
                setLayerType(1, null);
            }
            int min = Math.min(canvas.getWidth(), canvas.getHeight());
            int i = min / 2;
            int i2 = (i * 2) / 3;
            canvas.drawCircle((float) i, (float) i, (float) i2, this.a);
            canvas.drawCircle((float) i, (float) i, (float) (i2 - 2), this.b);
            int i3 = min / 3;
            canvas.drawLine((float) i3, (float) i3, (float) (i3 * 2), (float) (i3 * 2), this.c);
            canvas.drawLine((float) (i3 * 2), (float) i3, (float) i3, (float) (i3 * 2), this.c);
        }
        super.onDraw(canvas);
    }
}
