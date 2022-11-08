package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import org.bouncycastle.crypto.tls.CipherSuite;

public class ai extends View {
    private final int A;
    protected Paint a;
    protected Paint b;
    private Paint c;
    private Paint d;
    private RectF e;
    private float f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private float l;
    private int m;
    private String n;
    private String o;
    private float p;
    private String q;
    private float r;
    private final float s;
    private final int t;
    private final int u;
    private final int v;
    private final int w;
    private final int x;
    private final float y;
    private final float z;

    public ai(Context context) {
        this(context, null);
    }

    public ai(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ai(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new RectF();
        this.i = 0;
        this.n = "";
        this.o = "";
        this.q = "";
        this.t = Color.rgb(66, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 241);
        this.u = Color.rgb(66, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 241);
        this.v = Color.rgb(66, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 241);
        this.w = 0;
        this.x = 100;
        this.y = aj.b(getResources(), 14.0f);
        this.A = (int) aj.a(getResources(), 100.0f);
        this.s = aj.a(getResources(), 4.0f);
        this.z = aj.b(getResources(), 18.0f);
        b();
        a();
    }

    private int e(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return i;
        }
        int i2 = this.A;
        return mode == Integer.MIN_VALUE ? Math.min(i2, i) : i2;
    }

    private float o() {
        return (((float) d()) / ((float) this.j)) * 360.0f;
    }

    protected void a() {
        this.a = new TextPaint();
        this.a.setColor(this.g);
        this.a.setTextSize(this.f);
        this.a.setAntiAlias(true);
        this.b = new TextPaint();
        this.b.setColor(this.h);
        this.b.setTextSize(this.p);
        this.b.setAntiAlias(true);
        this.c = new Paint();
        this.c.setColor(this.k);
        this.c.setStyle(Style.STROKE);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(this.l);
        this.d = new Paint();
        this.d.setColor(this.m);
        this.d.setAntiAlias(true);
    }

    public void a(float f) {
        this.l = f;
        invalidate();
    }

    public void a(int i) {
        this.i = i;
        if (this.i > e()) {
            this.i %= e();
        }
        invalidate();
    }

    protected void b() {
        this.k = this.t;
        this.g = this.u;
        this.f = this.y;
        b(100);
        a(0);
        this.l = this.s;
        this.m = 0;
        this.p = this.z;
        this.h = this.v;
    }

    public void b(float f) {
        this.f = f;
        invalidate();
    }

    public void b(int i) {
        if (i > 0) {
            this.j = i;
            invalidate();
        }
    }

    public float c() {
        return this.l;
    }

    public void c(int i) {
        this.g = i;
        invalidate();
    }

    public int d() {
        return this.i;
    }

    public void d(int i) {
        this.k = i;
        invalidate();
    }

    public int e() {
        return this.j;
    }

    public float f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.k;
    }

    public String i() {
        return this.o;
    }

    public void invalidate() {
        a();
        super.invalidate();
    }

    public String j() {
        return this.n;
    }

    public int k() {
        return this.m;
    }

    public String l() {
        return this.q;
    }

    public float m() {
        return this.p;
    }

    public int n() {
        return this.h;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.l;
        this.e.set(f, f, ((float) getWidth()) - f, ((float) getHeight()) - f);
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, ((((float) getWidth()) - this.l) + this.l) / 2.0f, this.d);
        canvas.drawArc(this.e, 270.0f, -o(), false, this.c);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.n);
        stringBuilder.append(this.i);
        stringBuilder.append(this.o);
        String stringBuilder2 = stringBuilder.toString();
        if (!TextUtils.isEmpty(stringBuilder2)) {
            canvas.drawText(stringBuilder2, (((float) getWidth()) - this.a.measureText(stringBuilder2)) / 2.0f, (((float) getWidth()) - (this.a.descent() + this.a.ascent())) / 2.0f, this.a);
        }
        if (!TextUtils.isEmpty(l())) {
            this.b.setTextSize(this.p);
            canvas.drawText(l(), (((float) getWidth()) - this.b.measureText(l())) / 2.0f, (((float) getHeight()) - this.r) - ((this.a.descent() + this.a.ascent()) / 2.0f), this.b);
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(e(i), e(i2));
        this.r = (float) (getHeight() - ((getHeight() * 3) / 4));
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.g = bundle.getInt("text_color");
            this.f = bundle.getFloat("text_size");
            this.p = bundle.getFloat("inner_bottom_text_size");
            this.q = bundle.getString("inner_bottom_text");
            this.h = bundle.getInt("inner_bottom_text_color");
            this.k = bundle.getInt("finished_stroke_color");
            this.l = bundle.getFloat("finished_stroke_width");
            this.m = bundle.getInt("inner_background_color");
            a();
            b(bundle.getInt("max"));
            a(bundle.getInt("progress"));
            this.n = bundle.getString("prefix");
            this.o = bundle.getString("suffix");
            super.onRestoreInstanceState(bundle.getParcelable("saved_instance"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("saved_instance", super.onSaveInstanceState());
        bundle.putInt("text_color", g());
        bundle.putFloat("text_size", f());
        bundle.putFloat("inner_bottom_text_size", m());
        bundle.putFloat("inner_bottom_text_color", (float) n());
        bundle.putString("inner_bottom_text", l());
        bundle.putInt("inner_bottom_text_color", n());
        bundle.putInt("finished_stroke_color", h());
        bundle.putInt("max", e());
        bundle.putInt("progress", d());
        bundle.putString("suffix", i());
        bundle.putString("prefix", j());
        bundle.putFloat("finished_stroke_width", c());
        bundle.putInt("inner_background_color", k());
        return bundle;
    }
}
