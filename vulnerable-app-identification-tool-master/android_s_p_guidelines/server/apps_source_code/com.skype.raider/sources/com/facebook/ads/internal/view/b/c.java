package com.facebook.ads.internal.view.b;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.facebook.ads.internal.s.a;
import java.lang.ref.WeakReference;

public final class c extends Drawable {
    private final Paint a = new Paint();
    private final Paint b = new Paint();
    private final Path c = new Path();
    private final TextPaint d = new TextPaint();
    private final Paint e = new Paint();
    private int f;
    private int g;
    private String h;
    private int i;
    private boolean j;
    @Nullable
    private String k;
    private String l;
    private long m;
    private final Handler n = new Handler();
    @Nullable
    private WeakReference<a> o;
    private final Runnable p = new Runnable(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.c();
            if (this.a.j) {
                this.a.n.postDelayed(this.a.p, 250);
            }
        }
    };

    public c() {
        this.a.setColor(Color.argb(127, 36, 36, 36));
        this.a.setStyle(Style.FILL_AND_STROKE);
        this.b.setAntiAlias(true);
        this.b.setColor(Color.argb(191, 0, 255, 0));
        this.b.setStrokeWidth(20.0f);
        this.b.setStyle(Style.STROKE);
        this.d.setAntiAlias(true);
        this.d.setColor(-1);
        this.d.setStyle(Style.FILL_AND_STROKE);
        this.d.setTextSize(30.0f);
        this.e.setColor(Color.argb(212, 0, 0, 0));
        this.e.setStyle(Style.FILL_AND_STROKE);
        b();
    }

    private void c() {
        int i;
        int i2;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f <= 0) {
            if (!TextUtils.isEmpty(this.k)) {
                stringBuilder.append(this.k);
                stringBuilder.append("\n");
            }
            if (!TextUtils.isEmpty(this.l)) {
                stringBuilder.append(this.l);
                stringBuilder.append("\n");
            }
            stringBuilder.append("Sdk ");
            stringBuilder.append("4.99.1");
            stringBuilder.append(", Loaded ");
            if (this.m > 0) {
                long max = Math.max(0, System.currentTimeMillis() - this.m);
                i = (int) (max / 3600000);
                max %= 3600000;
                int i3 = (int) (max / 60000);
                i2 = (int) ((max % 60000) / 1000);
                if (i > 0) {
                    stringBuilder.append(i);
                    stringBuilder.append("h ");
                }
                if (i > 0 || i3 > 0) {
                    stringBuilder.append(i3);
                    stringBuilder.append("m ");
                }
                stringBuilder.append(i2);
                stringBuilder.append("s ago");
            } else {
                stringBuilder.append("Unknown");
            }
        } else {
            stringBuilder.append("Card ");
            stringBuilder.append(this.g + 1);
            stringBuilder.append(" of ");
            stringBuilder.append(this.f);
        }
        stringBuilder.append("\nView: ");
        if (this.o == null || this.o.get() == null) {
            stringBuilder.append("Viewability Checker not set");
        } else {
            stringBuilder.append(((a) this.o.get()).d());
        }
        this.h = stringBuilder.toString();
        float f = -2.14748365E9f;
        for (String str : this.h.split("\n")) {
            f = Math.max(f, this.d.measureText(str, 0, str.length()));
        }
        this.i = (int) (0.5f + f);
        invalidateSelf();
    }

    public final void a(int i, int i2) {
        this.f = i;
        this.g = i2;
        c();
    }

    public final void a(long j) {
        this.m = j;
        c();
    }

    public final void a(a aVar) {
        this.o = new WeakReference(aVar);
        c();
    }

    public final void a(String str) {
        this.k = str;
        c();
    }

    public final void a(boolean z) {
        this.j = z;
        if (this.j) {
            this.n.post(this.p);
        } else {
            this.n.removeCallbacks(this.p);
        }
        invalidateSelf();
    }

    public final boolean a() {
        return this.j;
    }

    public final void b() {
        this.f = 0;
        this.g = -1;
        this.h = "Initializing...";
        this.i = 100;
        this.k = null;
        this.m = -1;
        this.o = null;
        a(false);
    }

    public final void b(String str) {
        this.l = str;
        c();
    }

    public final void draw(Canvas canvas) {
        if (this.j) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, this.a);
            StaticLayout staticLayout = new StaticLayout(this.h, this.d, this.i, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            float f = ((float) width) / 2.0f;
            float f2 = ((float) height) / 2.0f;
            float width2 = ((float) staticLayout.getWidth()) / 2.0f;
            float height2 = ((float) staticLayout.getHeight()) / 2.0f;
            canvas.drawRect((f - width2) - 40.0f, (f2 - height2) - 40.0f, 40.0f + (f + width2), 40.0f + (f2 + height2), this.e);
            canvas.save();
            canvas.translate(f - width2, f2 - height2);
            staticLayout.draw(canvas);
            canvas.restore();
            this.c.reset();
            this.c.moveTo(0.0f, 0.0f);
            this.c.lineTo((float) width, 0.0f);
            this.c.lineTo((float) width, (float) height);
            this.c.lineTo(0.0f, (float) height);
            this.c.lineTo(0.0f, 0.0f);
            canvas.drawPath(this.c, this.b);
        }
    }

    public final int getOpacity() {
        return -3;
    }

    public final void setAlpha(@IntRange(from = 0, to = 255) int i) {
    }

    public final void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
