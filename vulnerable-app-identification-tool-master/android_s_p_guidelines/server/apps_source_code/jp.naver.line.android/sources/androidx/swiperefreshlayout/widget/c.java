package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.gs;
import defpackage.kq;

public final class c extends Drawable implements Animatable {
    private static final Interpolator c = new LinearInterpolator();
    private static final Interpolator d = new kq();
    private static final int[] e = new int[]{-16777216};
    float a;
    boolean b;
    private final d f = new d();
    private float g;
    private Resources h;
    private Animator i;

    public final int getOpacity() {
        return -3;
    }

    public c(Context context) {
        this.h = ((Context) gs.a((Object) context)).getResources();
        this.f.a(e);
        this.f.a(2.5f);
        invalidateSelf();
        final d dVar = this.f;
        Animator ofFloat = ValueAnimator.ofFloat(new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f});
        ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ c b;

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                c.a(floatValue, dVar);
                this.b.a(floatValue, dVar, false);
                this.b.invalidateSelf();
            }
        });
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(c);
        ofFloat.addListener(new AnimatorListener(this) {
            final /* synthetic */ c b;

            public final void onAnimationCancel(Animator animator) {
            }

            public final void onAnimationEnd(Animator animator) {
            }

            public final void onAnimationStart(Animator animator) {
                this.b.a = BitmapDescriptorFactory.HUE_RED;
            }

            public final void onAnimationRepeat(Animator animator) {
                this.b.a(1.0f, dVar, true);
                dVar.c();
                d dVar = dVar;
                dVar.a(dVar.a());
                if (this.b.b) {
                    this.b.b = false;
                    animator.cancel();
                    animator.setDuration(1332);
                    animator.start();
                    dVar.a(false);
                    return;
                }
                this.b.a += 1.0f;
            }
        });
        this.i = ofFloat;
    }

    private void a(float f, float f2, float f3, float f4) {
        d dVar = this.f;
        float f5 = this.h.getDisplayMetrics().density;
        dVar.a(f2 * f5);
        dVar.q = f * f5;
        dVar.a(0);
        f4 *= f5;
        dVar.r = (int) (f3 * f5);
        dVar.s = (int) f4;
    }

    public final void a(int i) {
        if (i == 0) {
            a(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            a(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public final void a(boolean z) {
        this.f.a(z);
        invalidateSelf();
    }

    public final void a(float f) {
        d dVar = this.f;
        if (f != dVar.p) {
            dVar.p = f;
        }
        invalidateSelf();
    }

    public final void b(float f) {
        this.f.e = BitmapDescriptorFactory.HUE_RED;
        this.f.f = f;
        invalidateSelf();
    }

    public final void c(float f) {
        this.f.g = f;
        invalidateSelf();
    }

    public final void a(int... iArr) {
        this.f.a(iArr);
        this.f.a(0);
        invalidateSelf();
    }

    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.g, bounds.exactCenterX(), bounds.exactCenterY());
        d dVar = this.f;
        RectF rectF = dVar.a;
        float f = dVar.q + (dVar.h / 2.0f);
        if (dVar.q <= BitmapDescriptorFactory.HUE_RED) {
            f = (((float) Math.min(bounds.width(), bounds.height())) / 2.0f) - Math.max((((float) dVar.r) * dVar.p) / 2.0f, dVar.h / 2.0f);
        }
        rectF.set(((float) bounds.centerX()) - f, ((float) bounds.centerY()) - f, ((float) bounds.centerX()) + f, ((float) bounds.centerY()) + f);
        float f2 = (dVar.e + dVar.g) * 360.0f;
        float f3 = ((dVar.f + dVar.g) * 360.0f) - f2;
        dVar.b.setColor(dVar.u);
        dVar.b.setAlpha(dVar.t);
        f = dVar.h / 2.0f;
        rectF.inset(f, f);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, dVar.d);
        f = -f;
        rectF.inset(f, f);
        canvas.drawArc(rectF, f2, f3, false, dVar.b);
        if (dVar.n) {
            if (dVar.o == null) {
                dVar.o = new Path();
                dVar.o.setFillType(FillType.EVEN_ODD);
            } else {
                dVar.o.reset();
            }
            f = Math.min(rectF.width(), rectF.height()) / 2.0f;
            float f4 = (((float) dVar.r) * dVar.p) / 2.0f;
            dVar.o.moveTo(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            dVar.o.lineTo(((float) dVar.r) * dVar.p, BitmapDescriptorFactory.HUE_RED);
            dVar.o.lineTo((((float) dVar.r) * dVar.p) / 2.0f, ((float) dVar.s) * dVar.p);
            dVar.o.offset((f + rectF.centerX()) - f4, rectF.centerY() + (dVar.h / 2.0f));
            dVar.o.close();
            dVar.c.setColor(dVar.u);
            dVar.c.setAlpha(dVar.t);
            canvas.save();
            canvas.rotate(f2 + f3, rectF.centerX(), rectF.centerY());
            canvas.drawPath(dVar.o, dVar.c);
            canvas.restore();
        }
        canvas.restore();
    }

    public final void setAlpha(int i) {
        this.f.t = i;
        invalidateSelf();
    }

    public final int getAlpha() {
        return this.f.t;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f.b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final boolean isRunning() {
        return this.i.isRunning();
    }

    public final void start() {
        this.i.cancel();
        this.f.c();
        if (this.f.f != this.f.e) {
            this.b = true;
            this.i.setDuration(666);
            this.i.start();
            return;
        }
        this.f.a(0);
        this.f.d();
        this.i.setDuration(1332);
        this.i.start();
    }

    public final void stop() {
        this.i.cancel();
        this.g = BitmapDescriptorFactory.HUE_RED;
        this.f.a(false);
        this.f.a(0);
        this.f.d();
        invalidateSelf();
    }

    static void a(float f, d dVar) {
        if (f > 0.75f) {
            f = (f - 0.75f) / 0.25f;
            int b = dVar.b();
            int i = dVar.i[dVar.a()];
            int i2 = (b >> 24) & 255;
            int i3 = (b >> 16) & 255;
            int i4 = (b >> 8) & 255;
            b &= 255;
            dVar.u = ((((i2 + ((int) (((float) (((i >> 24) & 255) - i2)) * f))) << 24) | ((i3 + ((int) (((float) (((i >> 16) & 255) - i3)) * f))) << 16)) | ((i4 + ((int) (((float) (((i >> 8) & 255) - i4)) * f))) << 8)) | (b + ((int) (f * ((float) ((i & 255) - b)))));
            return;
        }
        dVar.u = dVar.b();
    }

    final void a(float f, d dVar, boolean z) {
        float floor;
        if (this.b) {
            a(f, dVar);
            floor = (float) (Math.floor((double) (dVar.m / 0.8f)) + 1.0d);
            dVar.e = dVar.k + (((dVar.l - 0.01f) - dVar.k) * f);
            dVar.f = dVar.l;
            dVar.g = dVar.m + ((floor - dVar.m) * f);
            return;
        }
        if (f != 1.0f || z) {
            float f2;
            float f3;
            floor = dVar.m;
            if (f < 0.5f) {
                f2 = f / 0.5f;
                f3 = dVar.k;
                float f4 = f3;
                f3 = ((d.getInterpolation(f2) * 0.79f) + 0.01f) + f3;
                f2 = f4;
            } else {
                f3 = dVar.k + 0.79f;
                f2 = f3 - (((1.0f - d.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
            }
            floor += 0.20999998f * f;
            f = (f + this.a) * 216.0f;
            dVar.e = f2;
            dVar.f = f3;
            dVar.g = floor;
            this.g = f;
        }
    }
}
