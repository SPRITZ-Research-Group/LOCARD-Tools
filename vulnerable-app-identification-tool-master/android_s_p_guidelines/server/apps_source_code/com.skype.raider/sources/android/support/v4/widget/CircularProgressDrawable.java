package android.support.v4.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.util.k;
import android.support.v4.view.animation.b;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CircularProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator a = new LinearInterpolator();
    private static final Interpolator b = new b();
    private static final int[] c = new int[]{-16777216};
    private final a d = new a();
    private float e;
    private Resources f;
    private Animator g;
    private float h;
    private boolean i;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProgressDrawableSize {
    }

    private static class a {
        final RectF a = new RectF();
        final Paint b = new Paint();
        final Paint c = new Paint();
        final Paint d = new Paint();
        float e = 0.0f;
        float f = 0.0f;
        float g = 0.0f;
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

        a() {
            this.b.setStrokeCap(Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Style.STROKE);
            this.c.setStyle(Style.FILL);
            this.c.setAntiAlias(true);
            this.d.setColor(0);
        }

        final void a(@NonNull int[] colors) {
            this.i = colors;
            a(0);
        }

        final void a(int index) {
            this.j = index;
            this.u = this.i[this.j];
        }

        final int a() {
            return (this.j + 1) % this.i.length;
        }

        final void a(float strokeWidth) {
            this.h = strokeWidth;
            this.b.setStrokeWidth(strokeWidth);
        }

        final int b() {
            return this.i[this.j];
        }

        final void a(boolean show) {
            if (this.n != show) {
                this.n = show;
            }
        }

        final void c() {
            this.k = this.e;
            this.l = this.f;
            this.m = this.g;
        }

        final void d() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
        }
    }

    static /* synthetic */ void a(CircularProgressDrawable x0, float x1, a x2, boolean x3) {
        float floor;
        if (x0.i) {
            b(x1, x2);
            floor = (float) (Math.floor((double) (x2.m / 0.8f)) + 1.0d);
            x2.e = x2.k + (((x2.l - 0.01f) - x2.k) * x1);
            x2.f = x2.l;
            x2.g = ((floor - x2.m) * x1) + x2.m;
        } else if (x1 != 1.0f || x3) {
            float f;
            float f2 = x2.m;
            if (x1 < 0.5f) {
                floor = x1 / 0.5f;
                f = x2.k;
                floor = ((b.getInterpolation(floor) * 0.79f) + 0.01f) + f;
            } else {
                floor = x2.k + 0.79f;
                f = floor - (((1.0f - b.getInterpolation((x1 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
            }
            f2 += 0.20999998f * x1;
            float f3 = 216.0f * (x0.h + x1);
            x2.e = f;
            x2.f = floor;
            x2.g = f2;
            x0.e = f3;
        }
    }

    public CircularProgressDrawable(Context context) {
        this.f = ((Context) k.a(context)).getResources();
        this.d.a(c);
        this.d.a(2.5f);
        invalidateSelf();
        final a aVar = this.d;
        Animator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ CircularProgressDrawable b;

            public final void onAnimationUpdate(ValueAnimator animation) {
                float interpolatedTime = ((Float) animation.getAnimatedValue()).floatValue();
                CircularProgressDrawable.b(interpolatedTime, aVar);
                CircularProgressDrawable.a(this.b, interpolatedTime, aVar, false);
                this.b.invalidateSelf();
            }
        });
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(a);
        ofFloat.addListener(new AnimatorListener(this) {
            final /* synthetic */ CircularProgressDrawable b;

            public final void onAnimationStart(Animator animator) {
                this.b.h = 0.0f;
            }

            public final void onAnimationEnd(Animator animator) {
            }

            public final void onAnimationCancel(Animator animation) {
            }

            public final void onAnimationRepeat(Animator animator) {
                CircularProgressDrawable.a(this.b, 1.0f, aVar, true);
                aVar.c();
                a aVar = aVar;
                aVar.a(aVar.a());
                if (this.b.i) {
                    this.b.i = false;
                    animator.cancel();
                    animator.setDuration(1332);
                    animator.start();
                    aVar.a(false);
                    return;
                }
                this.b.h = this.b.h + 1.0f;
            }
        });
        this.g = ofFloat;
    }

    private void a(float centerRadius, float strokeWidth, float arrowWidth, float arrowHeight) {
        a ring = this.d;
        float screenDensity = this.f.getDisplayMetrics().density;
        ring.a(strokeWidth * screenDensity);
        ring.q = centerRadius * screenDensity;
        ring.a(0);
        float f = arrowHeight * screenDensity;
        ring.r = (int) (arrowWidth * screenDensity);
        ring.s = (int) f;
    }

    public final void a(int size) {
        if (size == 0) {
            a(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            a(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public final void a(boolean show) {
        this.d.a(show);
        invalidateSelf();
    }

    public final void a(float scale) {
        a aVar = this.d;
        if (scale != aVar.p) {
            aVar.p = scale;
        }
        invalidateSelf();
    }

    public final void b(float end) {
        this.d.e = 0.0f;
        this.d.f = end;
        invalidateSelf();
    }

    public final void c(float rotation) {
        this.d.g = rotation;
        invalidateSelf();
    }

    public final void a(int... colors) {
        this.d.a(colors);
        this.d.a(0);
        invalidateSelf();
    }

    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.e, bounds.exactCenterX(), bounds.exactCenterY());
        a aVar = this.d;
        RectF rectF = aVar.a;
        float f = aVar.q + (aVar.h / 2.0f);
        if (aVar.q <= 0.0f) {
            f = (((float) Math.min(bounds.width(), bounds.height())) / 2.0f) - Math.max((((float) aVar.r) * aVar.p) / 2.0f, aVar.h / 2.0f);
        }
        rectF.set(((float) bounds.centerX()) - f, ((float) bounds.centerY()) - f, ((float) bounds.centerX()) + f, f + ((float) bounds.centerY()));
        float f2 = (aVar.e + aVar.g) * 360.0f;
        float f3 = ((aVar.f + aVar.g) * 360.0f) - f2;
        aVar.b.setColor(aVar.u);
        aVar.b.setAlpha(aVar.t);
        f = aVar.h / 2.0f;
        rectF.inset(f, f);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, aVar.d);
        rectF.inset(-f, -f);
        canvas.drawArc(rectF, f2, f3, false, aVar.b);
        if (aVar.n) {
            if (aVar.o == null) {
                aVar.o = new Path();
                aVar.o.setFillType(FillType.EVEN_ODD);
            } else {
                aVar.o.reset();
            }
            f = Math.min(rectF.width(), rectF.height()) / 2.0f;
            float f4 = (((float) aVar.r) * aVar.p) / 2.0f;
            aVar.o.moveTo(0.0f, 0.0f);
            aVar.o.lineTo(((float) aVar.r) * aVar.p, 0.0f);
            aVar.o.lineTo((((float) aVar.r) * aVar.p) / 2.0f, ((float) aVar.s) * aVar.p);
            aVar.o.offset((f + rectF.centerX()) - f4, rectF.centerY() + (aVar.h / 2.0f));
            aVar.o.close();
            aVar.c.setColor(aVar.u);
            aVar.c.setAlpha(aVar.t);
            canvas.save();
            canvas.rotate(f2 + f3, rectF.centerX(), rectF.centerY());
            canvas.drawPath(aVar.o, aVar.c);
            canvas.restore();
        }
        canvas.restore();
    }

    public final void setAlpha(int alpha) {
        this.d.t = alpha;
        invalidateSelf();
    }

    public final int getAlpha() {
        return this.d.t;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.d.b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final int getOpacity() {
        return -3;
    }

    public final boolean isRunning() {
        return this.g.isRunning();
    }

    public final void start() {
        this.g.cancel();
        this.d.c();
        if (this.d.f != this.d.e) {
            this.i = true;
            this.g.setDuration(666);
            this.g.start();
            return;
        }
        this.d.a(0);
        this.d.d();
        this.g.setDuration(1332);
        this.g.start();
    }

    public final void stop() {
        this.g.cancel();
        this.e = 0.0f;
        this.d.a(false);
        this.d.a(0);
        this.d.d();
        invalidateSelf();
    }

    private static void b(float interpolatedTime, a ring) {
        if (interpolatedTime > 0.75f) {
            float f = (interpolatedTime - 0.75f) / 0.25f;
            int b = ring.b();
            int i = ring.i[ring.a()];
            int i2 = (b >> 24) & 255;
            int i3 = (b >> 16) & 255;
            int i4 = (b >> 8) & 255;
            b &= 255;
            ring.u = (((int) (f * ((float) ((i & 255) - b)))) + b) | ((((i2 + ((int) (((float) (((i >> 24) & 255) - i2)) * f))) << 24) | ((i3 + ((int) (((float) (((i >> 16) & 255) - i3)) * f))) << 16)) | ((((int) (((float) (((i >> 8) & 255) - i4)) * f)) + i4) << 8));
            return;
        }
        ring.u = ring.b();
    }
}
