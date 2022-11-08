package androidx.core.widget;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hs;

public abstract class a implements OnTouchListener {
    private static final int r = ViewConfiguration.getTapTimeout();
    final b a = new b();
    final View b;
    boolean c;
    boolean d;
    boolean e;
    private final Interpolator f = new AccelerateInterpolator();
    private Runnable g;
    private float[] h = new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    private float[] i = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
    private int j;
    private int k;
    private float[] l = new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    private float[] m = new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    private float[] n = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
    private boolean o;
    private boolean p;
    private boolean q;

    static float a(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    static int a(int i, int i2) {
        return i > i2 ? i2 : i < 0 ? 0 : i;
    }

    public abstract void a(int i);

    public abstract boolean b(int i);

    public a(View view) {
        this.b = view;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int i = (int) ((displayMetrics.density * 1575.0f) + 0.5f);
        int i2 = (int) ((displayMetrics.density * 315.0f) + 0.5f);
        float f = ((float) i) / 1000.0f;
        this.n[0] = f;
        this.n[1] = f;
        float f2 = ((float) i2) / 1000.0f;
        this.m[0] = f2;
        this.m[1] = f2;
        this.j = 1;
        this.i[0] = Float.MAX_VALUE;
        this.i[1] = Float.MAX_VALUE;
        this.h[0] = 0.2f;
        this.h[1] = 0.2f;
        this.l[0] = 0.001f;
        this.l[1] = 0.001f;
        this.k = r;
        this.a.a();
        this.a.b();
    }

    public final a a(boolean z) {
        if (this.p && !z) {
            b();
        }
        this.p = z;
        return this;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.p) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.d = true;
                this.o = false;
                break;
            case 1:
            case 3:
                b();
                break;
            case 2:
                break;
        }
        this.a.a(a(0, motionEvent.getX(), (float) view.getWidth(), (float) this.b.getWidth()), a(1, motionEvent.getY(), (float) view.getHeight(), (float) this.b.getHeight()));
        if (!this.e && a()) {
            if (this.g == null) {
                this.g = new c(this);
            }
            this.e = true;
            this.c = true;
            if (this.o || this.k <= 0) {
                this.g.run();
            } else {
                hs.a(this.b, this.g, (long) this.k);
            }
            this.o = true;
        }
        if (this.q && this.e) {
            return true;
        }
        return false;
    }

    final boolean a() {
        b bVar = this.a;
        int h = bVar.h();
        int g = bVar.g();
        return h != 0 && b(h);
    }

    private void b() {
        if (this.c) {
            this.e = false;
        } else {
            this.a.d();
        }
    }

    private float a(int i, float f, float f2, float f3) {
        f = a(this.h[i], f2, this.i[i], f);
        if (f == BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float f4 = this.l[i];
        float f5 = this.m[i];
        float f6 = this.n[i];
        f4 *= f3;
        if (f > BitmapDescriptorFactory.HUE_RED) {
            return a(f * f4, f5, f6);
        }
        return -a((-f) * f4, f5, f6);
    }

    private float a(float f, float f2, float f3, float f4) {
        f = a(f * f2, BitmapDescriptorFactory.HUE_RED, f3);
        f = a(f2 - f4, f) - a(f4, f);
        if (f < BitmapDescriptorFactory.HUE_RED) {
            f = -this.f.getInterpolation(-f);
        } else if (f <= BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        } else {
            f = this.f.getInterpolation(f);
        }
        return a(f, -1.0f, 1.0f);
    }

    private float a(float f, float f2) {
        if (f2 == BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        switch (this.j) {
            case 0:
            case 1:
                if (f < f2) {
                    if (f >= BitmapDescriptorFactory.HUE_RED) {
                        return 1.0f - (f / f2);
                    }
                    return (this.e && this.j == 1) ? 1.0f : BitmapDescriptorFactory.HUE_RED;
                }
                break;
            case 2:
                if (f < BitmapDescriptorFactory.HUE_RED) {
                    return f / (-f2);
                }
                break;
        }
    }
}
