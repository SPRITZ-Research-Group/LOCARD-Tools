package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.view.View;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.o;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class j extends View implements b {
    private final Paint a;
    private final Paint b;
    private final Paint c;
    private a d = a.CLOSE_BUTTON_MODE;
    private final Paint e;
    private final RectF f;
    @Nullable
    private com.facebook.ads.internal.view.f.a g;
    private int h;
    private final AtomicInteger i = new AtomicInteger(0);
    private final AtomicBoolean j = new AtomicBoolean(false);
    private final m k = new m(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            this.a.j.set(true);
        }
    };
    private final o l = new o(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.g != null) {
                int c = this.a.h;
                int f = this.a.g.f();
                if (c <= 0) {
                    this.a.i.set(0);
                } else {
                    c = Math.min(f, c * Constants.ONE_SECOND);
                    if (c != 0) {
                        this.a.i.set(((c - this.a.g.d()) * 100) / c);
                    } else {
                        return;
                    }
                }
                this.a.postInvalidate();
            }
        }
    };
    private final c m = new c(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            this.a.h = 0;
            this.a.i.set(0);
            this.a.postInvalidate();
        }
    };

    public enum a {
        CLOSE_BUTTON_MODE,
        SKIP_BUTTON_MODE
    }

    public j(Context context, int i, int i2) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        this.h = i;
        this.b = new Paint();
        this.b.setStyle(Style.FILL);
        this.b.setColor(i2);
        this.c = new Paint();
        this.c.setColor(-1);
        this.c.setAlpha(230);
        this.c.setStyle(Style.FILL);
        this.c.setStrokeWidth(1.0f * f);
        this.c.setAntiAlias(true);
        this.a = new Paint();
        this.a.setColor(-16777216);
        this.a.setStyle(Style.STROKE);
        this.a.setAlpha(102);
        this.a.setStrokeWidth(1.5f * f);
        this.a.setAntiAlias(true);
        setLayerType(1, null);
        this.a.setMaskFilter(new BlurMaskFilter(6.0f, Blur.NORMAL));
        this.e = new Paint();
        this.e.setColor(-10066330);
        this.e.setStyle(Style.STROKE);
        this.e.setStrokeWidth(f * 2.0f);
        this.e.setAntiAlias(true);
        this.f = new RectF();
    }

    public final void a(com.facebook.ads.internal.view.f.a aVar) {
        this.g = aVar;
        this.g.a().a(this.k, this.l, this.m);
    }

    public final boolean a() {
        return this.g != null && (this.h <= 0 || this.i.get() < 0);
    }

    public final int b() {
        return this.h;
    }

    public final void b(com.facebook.ads.internal.view.f.a aVar) {
        this.g.a().b(this.m, this.l, this.k);
        this.g = null;
    }

    protected void onDraw(Canvas canvas) {
        if (this.j.get()) {
            int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
            int i = min / 2;
            canvas.drawCircle((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i), (float) i, this.a);
            canvas.drawCircle((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i), (float) i, this.c);
            if (this.i.get() > 0) {
                this.f.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
                canvas.drawArc(this.f, -90.0f, ((float) (-(this.i.get() * 360))) / 100.0f, true, this.b);
            } else if (this.d == a.SKIP_BUTTON_MODE) {
                int i2 = min / 4;
                min /= 3;
                Path path = new Path();
                path.moveTo((float) (getPaddingLeft() + i2), (float) (getPaddingTop() + min));
                path.lineTo((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i));
                path.lineTo((float) (getPaddingLeft() + i2), (float) ((min * 2) + getPaddingTop()));
                canvas.drawPath(path, this.e);
                path = new Path();
                path.moveTo((float) (getPaddingLeft() + i), (float) (getPaddingTop() + min));
                path.lineTo((float) ((i2 * 3) + getPaddingLeft()), (float) (getPaddingTop() + i));
                path.lineTo((float) (i + getPaddingLeft()), (float) ((min * 2) + getPaddingTop()));
                canvas.drawPath(path, this.e);
            } else {
                int i3 = min / 3;
                canvas.drawLine((float) (getPaddingLeft() + i3), (float) (getPaddingTop() + i3), (float) ((i3 * 2) + getPaddingLeft()), (float) ((i3 * 2) + getPaddingTop()), this.e);
                canvas.drawLine((float) ((i3 * 2) + getPaddingLeft()), (float) (getPaddingTop() + i3), (float) (getPaddingLeft() + i3), (float) ((i3 * 2) + getPaddingTop()), this.e);
            }
            super.onDraw(canvas);
            return;
        }
        super.onDraw(canvas);
    }

    public void setButtonMode(a aVar) {
        this.d = aVar;
    }
}
