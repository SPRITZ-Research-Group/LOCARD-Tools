package com.microsoft.skypemessagetextinput.e;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.net.Uri;
import android.text.style.ReplacementSpan;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.ae;
import com.facebook.react.uimanager.p;
import com.microsoft.skypemessagetextinput.d.d.a;

final class c extends ReplacementSpan implements a {
    private static final int a = ((int) p.a(2.0f));
    private f b;
    private com.microsoft.skypemessagetextinput.view.a c;
    private com.microsoft.skypemessagetextinput.d.c d;
    private String e;
    private String f;
    private Bitmap g;
    private int h = 0;
    private int i = 0;
    private boolean j = false;
    private long k = 0;

    public c(f parentSpan, ae context, com.microsoft.skypemessagetextinput.view.a view, String uri, String emoticonText) {
        this.b = parentSpan;
        this.c = view;
        this.d = new com.microsoft.skypemessagetextinput.d.c(context, this);
        this.e = uri;
        this.f = emoticonText;
    }

    public final void a() {
        this.d.a();
    }

    public final int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        FontMetricsInt currentMetrics = paint.getFontMetricsInt();
        if (fm != null) {
            fm.top = currentMetrics.top;
            fm.ascent = currentMetrics.ascent;
            fm.descent = currentMetrics.descent;
            fm.bottom = currentMetrics.bottom;
            fm.leading = currentMetrics.leading;
        }
        this.i = Math.max(0, (currentMetrics.bottom - currentMetrics.top) - (a * 2));
        if (this.g != null && this.h == this.i) {
            return this.g.getWidth();
        }
        if (this.e == null || this.d.b() > 0) {
            return (int) paint.measureText(this.f);
        }
        if (!this.j) {
            try {
                this.j = true;
                this.k = System.currentTimeMillis();
                this.d.b(Uri.parse(this.e));
            } catch (Throwable e) {
                FLog.e("SkypeMsgTextInput/EmoticonRenderer", "Exception in EmoticonRenderer._getBitmapOrNull()", e);
            }
        }
        return this.i;
    }

    public final void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        if (this.g != null) {
            canvas.drawBitmap(this.g, x, (float) (a + top), paint);
        } else if (this.e == null || this.d.b() > 0) {
            canvas.drawText(this.f, 0, this.f.length(), x, (float) y, paint);
        }
    }

    public final void a(Bitmap originalBitmap) {
        this.j = false;
        float scale = ((float) this.i) / ((float) originalBitmap.getHeight());
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        this.h = this.i;
        this.g = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);
        this.c.a(this.b);
        ar event = new WritableNativeMap();
        event.putInt("loadTime", (int) (System.currentTimeMillis() - this.k));
        this.c.a(com.microsoft.skypemessagetextinput.view.a.a.onEmoticonLoadCompleted, event);
    }

    public final void a(int warningCount) {
        if (warningCount < 3) {
            this.c.a(this.b);
        }
    }
}
