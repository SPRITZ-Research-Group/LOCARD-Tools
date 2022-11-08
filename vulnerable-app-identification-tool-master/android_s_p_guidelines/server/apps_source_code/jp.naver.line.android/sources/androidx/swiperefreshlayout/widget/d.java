package androidx.swiperefreshlayout.widget;

import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

final class d {
    final RectF a = new RectF();
    final Paint b = new Paint();
    final Paint c = new Paint();
    final Paint d = new Paint();
    float e = BitmapDescriptorFactory.HUE_RED;
    float f = BitmapDescriptorFactory.HUE_RED;
    float g = BitmapDescriptorFactory.HUE_RED;
    float h = 5.0f;
    int[] i;
    int j;
    float k;
    float l;
    float m;
    boolean n;
    Path o;
    float p = 1.0f;
    float q;
    int r;
    int s;
    int t = 255;
    int u;

    d() {
        this.b.setStrokeCap(Cap.SQUARE);
        this.b.setAntiAlias(true);
        this.b.setStyle(Style.STROKE);
        this.c.setStyle(Style.FILL);
        this.c.setAntiAlias(true);
        this.d.setColor(0);
    }

    final void a(int[] iArr) {
        this.i = iArr;
        a(0);
    }

    final void a(int i) {
        this.j = i;
        this.u = this.i[this.j];
    }

    final int a() {
        return (this.j + 1) % this.i.length;
    }

    final void a(float f) {
        this.h = f;
        this.b.setStrokeWidth(f);
    }

    final int b() {
        return this.i[this.j];
    }

    final void a(boolean z) {
        if (this.n != z) {
            this.n = z;
        }
    }

    final void c() {
        this.k = this.e;
        this.l = this.f;
        this.m = this.g;
    }

    final void d() {
        this.k = BitmapDescriptorFactory.HUE_RED;
        this.l = BitmapDescriptorFactory.HUE_RED;
        this.m = BitmapDescriptorFactory.HUE_RED;
        this.e = BitmapDescriptorFactory.HUE_RED;
        this.f = BitmapDescriptorFactory.HUE_RED;
        this.g = BitmapDescriptorFactory.HUE_RED;
    }
}
