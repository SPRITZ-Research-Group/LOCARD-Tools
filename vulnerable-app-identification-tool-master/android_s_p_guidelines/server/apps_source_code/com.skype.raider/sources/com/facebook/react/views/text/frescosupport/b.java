package com.facebook.react.views.text.frescosupport;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.TextView;
import com.facebook.drawee.d.a;
import com.facebook.imagepipeline.k.c;
import com.facebook.react.bridge.am;
import com.facebook.react.views.text.k;
import javax.annotation.Nullable;

public final class b extends k {
    @Nullable
    private Drawable a;
    private final com.facebook.drawee.controller.b b;
    private final com.facebook.drawee.view.b<a> c;
    @Nullable
    private final Object d;
    private int e;
    private Uri f;
    private int g;
    private am h;
    @Nullable
    private TextView i;

    public b(Resources resources, int height, int width, @Nullable Uri uri, am headers, com.facebook.drawee.controller.b draweeControllerBuilder, @Nullable Object callerContext) {
        this.c = new com.facebook.drawee.view.b(com.facebook.drawee.d.b.a(resources).s());
        this.b = draweeControllerBuilder;
        this.d = callerContext;
        this.e = height;
        this.g = width;
        if (uri == null) {
            uri = Uri.EMPTY;
        }
        this.f = uri;
        this.h = headers;
    }

    public final void b() {
        this.c.c();
    }

    public final void c() {
        this.c.c();
    }

    public final void d() {
        this.c.b();
    }

    public final void e() {
        this.c.b();
    }

    @Nullable
    public final Drawable a() {
        return this.a;
    }

    public final int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        if (fm != null) {
            fm.ascent = -this.e;
            fm.descent = 0;
            fm.top = fm.ascent;
            fm.bottom = 0;
        }
        return this.g;
    }

    public final void a(TextView textView) {
        this.i = textView;
    }

    public final void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        if (this.a == null) {
            this.c.a(this.b.b().a(this.c.d()).a(this.d).b(com.facebook.react.modules.fresco.a.a(c.a(this.f), this.h)).h());
            this.b.b();
            this.a = this.c.f();
            this.a.setBounds(0, 0, this.g, this.e);
            this.a.setCallback(this.i);
        }
        canvas.save();
        canvas.translate(x, (float) (y - this.a.getBounds().bottom));
        this.a.draw(canvas);
        canvas.restore();
    }
}
