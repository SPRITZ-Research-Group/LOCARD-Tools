package com.airbnb.lottie;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Build.VERSION;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.airbnb.lottie.b.b;
import com.airbnb.lottie.c.a.l;
import com.airbnb.lottie.c.c.d;
import com.airbnb.lottie.c.e;
import com.airbnb.lottie.e.c;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LottieDrawable extends Drawable implements Animatable, Callback {
    private static final String c = LottieDrawable.class.getSimpleName();
    @Nullable
    b a;
    @Nullable
    l b;
    private final Matrix d = new Matrix();
    private e e;
    private final c f = new c();
    private float g = 1.0f;
    private final Set<Object> h = new HashSet();
    private final ArrayList<a> i = new ArrayList();
    @Nullable
    private b j;
    @Nullable
    private String k;
    @Nullable
    private c l;
    @Nullable
    private com.airbnb.lottie.b.a m;
    private boolean n;
    @Nullable
    private com.airbnb.lottie.c.c.b o;
    private int p = 255;
    private boolean q;

    private interface a {
        void a();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    public LottieDrawable() {
        this.f.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ LottieDrawable a;

            {
                this.a = this$0;
            }

            public final void onAnimationUpdate(ValueAnimator animation) {
                if (this.a.o != null) {
                    this.a.o.a(this.a.f.f());
                }
            }
        });
    }

    public final boolean a() {
        return this.n;
    }

    public final void a(boolean enable) {
        if (VERSION.SDK_INT >= 19) {
            this.n = enable;
            if (this.e != null) {
                n();
            }
        }
    }

    public final void a(@Nullable String imageAssetsFolder) {
        this.k = imageAssetsFolder;
    }

    @Nullable
    public final String b() {
        return this.k;
    }

    public final void c() {
        if (this.j != null) {
            this.j.a();
        }
    }

    public final boolean a(e composition) {
        if (this.e == composition) {
            return false;
        }
        d();
        this.e = composition;
        n();
        this.f.a(composition);
        d(this.f.getAnimatedFraction());
        e(this.g);
        o();
        Iterator<a> it = new ArrayList(this.i).iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
            it.remove();
        }
        this.i.clear();
        composition.a(this.q);
        return true;
    }

    public final void b(boolean enabled) {
        this.q = enabled;
        if (this.e != null) {
            this.e.a(enabled);
        }
    }

    private void n() {
        e eVar = this.e;
        Rect b = eVar.b();
        this.o = new com.airbnb.lottie.c.c.b(this, new d(Collections.emptyList(), eVar, "__container", -1, com.airbnb.lottie.c.c.d.a.PreComp, -1, null, Collections.emptyList(), new l(), 0, 0, 0, 0.0f, 0.0f, b.width(), b.height(), null, null, Collections.emptyList(), d.b.None, null), this.e.g(), this.e);
    }

    public final void d() {
        c();
        if (this.f.isRunning()) {
            this.f.cancel();
        }
        this.e = null;
        this.o = null;
        this.j = null;
        invalidateSelf();
    }

    public void invalidateSelf() {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        this.p = alpha;
    }

    public int getAlpha() {
        return this.p;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return -3;
    }

    public void draw(@NonNull Canvas canvas) {
        d.a("Drawable#draw");
        if (this.o != null) {
            float scale = this.g;
            float extraScale = 1.0f;
            float maxScale = Math.min(((float) canvas.getWidth()) / ((float) this.e.b().width()), ((float) canvas.getHeight()) / ((float) this.e.b().height()));
            if (scale > maxScale) {
                scale = maxScale;
                extraScale = this.g / scale;
            }
            if (extraScale > 1.0f) {
                canvas.save();
                float halfWidth = ((float) this.e.b().width()) / 2.0f;
                float halfHeight = ((float) this.e.b().height()) / 2.0f;
                float scaledHalfWidth = halfWidth * scale;
                float scaledHalfHeight = halfHeight * scale;
                canvas.translate((this.g * halfWidth) - scaledHalfWidth, (this.g * halfHeight) - scaledHalfHeight);
                canvas.scale(extraScale, extraScale, scaledHalfWidth, scaledHalfHeight);
            }
            this.d.reset();
            this.d.preScale(scale, scale);
            this.o.a(canvas, this.d, this.p);
            d.b("Drawable#draw");
            if (extraScale > 1.0f) {
                canvas.restore();
            }
        }
    }

    public void start() {
        e();
    }

    public final void e() {
        if (this.o == null) {
            this.i.add(new a(this) {
                final /* synthetic */ LottieDrawable a;

                {
                    this.a = this$0;
                }

                public final void a() {
                    this.a.e();
                }
            });
        } else {
            this.f.g();
        }
    }

    public final void a(int minFrame) {
        this.f.b(minFrame);
    }

    public final void a(final float minProgress) {
        if (this.e == null) {
            this.i.add(new a(this) {
                final /* synthetic */ LottieDrawable b;

                public final void a() {
                    this.b.a(minProgress);
                }
            });
        } else {
            a((int) (this.e.k() * minProgress));
        }
    }

    public final void b(int maxFrame) {
        this.f.c(maxFrame);
    }

    public final void b(@FloatRange(from = 0.0d, to = 1.0d) final float maxProgress) {
        if (this.e == null) {
            this.i.add(new a(this) {
                final /* synthetic */ LottieDrawable b;

                public final void a() {
                    this.b.b(maxProgress);
                }
            });
        } else {
            b((int) (this.e.k() * maxProgress));
        }
    }

    public final void a(int minFrame, int maxFrame) {
        this.f.a(minFrame, maxFrame);
    }

    public final void a(@FloatRange(from = 0.0d, to = 1.0d) final float minProgress, @FloatRange(from = 0.0d, to = 1.0d) final float maxProgress) {
        if (this.e == null) {
            this.i.add(new a(this) {
                final /* synthetic */ LottieDrawable c;

                public final void a() {
                    this.c.a(minProgress, maxProgress);
                }
            });
        } else {
            a((int) (this.e.k() * minProgress), (int) (this.e.k() * maxProgress));
        }
    }

    public final void c(float speed) {
        this.f.a(speed);
    }

    public final void c(final int frame) {
        if (this.e == null) {
            this.i.add(new a(this) {
                final /* synthetic */ LottieDrawable b;

                public final void a() {
                    this.b.c(frame);
                }
            });
        } else {
            this.f.a(frame);
        }
    }

    public final void d(@FloatRange(from = 0.0d, to = 1.0d) final float progress) {
        if (this.e == null) {
            this.i.add(new a(this) {
                final /* synthetic */ LottieDrawable b;

                public final void a() {
                    this.b.d(progress);
                }
            });
            return;
        }
        float d = this.e.d();
        c((int) (d + ((this.e.e() - d) * progress)));
    }

    public final void d(int mode) {
        this.f.setRepeatMode(mode);
    }

    public final int f() {
        return this.f.getRepeatMode();
    }

    public final void e(int count) {
        this.f.setRepeatCount(count);
    }

    public final int g() {
        return this.f.getRepeatCount();
    }

    public final boolean h() {
        return this.f.isRunning();
    }

    public final void e(float scale) {
        this.g = scale;
        o();
    }

    public final void a(c assetDelegate) {
        this.l = assetDelegate;
        if (this.j != null) {
            this.j.a(assetDelegate);
        }
    }

    public final void a(b assetDelegate) {
        this.a = assetDelegate;
        if (this.m != null) {
            this.m.a(assetDelegate);
        }
    }

    @Nullable
    public final l i() {
        return this.b;
    }

    public final boolean j() {
        return this.b == null && this.e.h().a() > 0;
    }

    public final e k() {
        return this.e;
    }

    private void o() {
        if (this.e != null) {
            float scale = this.g;
            setBounds(0, 0, (int) (((float) this.e.b().width()) * scale), (int) (((float) this.e.b().height()) * scale));
        }
    }

    public final void l() {
        this.i.clear();
        this.f.cancel();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public final float m() {
        return this.f.f();
    }

    public int getIntrinsicWidth() {
        if (this.e == null) {
            return -1;
        }
        return (int) (((float) this.e.b().width()) * this.g);
    }

    public int getIntrinsicHeight() {
        if (this.e == null) {
            return -1;
        }
        return (int) (((float) this.e.b().height()) * this.g);
    }

    public final <T> void a(final e keyPath, final T property, final com.airbnb.lottie.f.c<T> callback) {
        if (this.o == null) {
            this.i.add(new a(this) {
                final /* synthetic */ LottieDrawable d;

                public final void a() {
                    this.d.a(keyPath, property, callback);
                }
            });
            return;
        }
        int i;
        if (keyPath.a() != null) {
            keyPath.a().a(property, callback);
            i = 1;
        } else {
            List<e> elements;
            if (this.o == null) {
                elements = Collections.emptyList();
            } else {
                elements = new ArrayList();
                this.o.a(keyPath, 0, elements, new e(new String[0]));
            }
            for (int i2 = 0; i2 < elements.size(); i2++) {
                ((e) elements.get(i2)).a().a(property, callback);
            }
            i = !elements.isEmpty() ? 1 : 0;
        }
        if (i != 0) {
            invalidateSelf();
            if (property == g.w) {
                d(this.f.f());
            }
        }
    }

    @Nullable
    public final Bitmap b(String id) {
        b bm;
        if (getCallback() == null) {
            bm = null;
        } else {
            if (this.j != null) {
                Context context;
                b bVar = this.j;
                Callback callback = getCallback();
                if (callback == null || !(callback instanceof View)) {
                    context = null;
                } else {
                    context = ((View) callback).getContext();
                }
                if (!bVar.a(context)) {
                    this.j.a();
                    this.j = null;
                }
            }
            if (this.j == null) {
                this.j = new b(getCallback(), this.k, this.l, this.e.j());
            }
            bm = this.j;
        }
        if (bm != null) {
            return bm.a(id);
        }
        return null;
    }

    @Nullable
    public final Typeface a(String fontFamily, String style) {
        com.airbnb.lottie.b.a assetManager;
        if (getCallback() == null) {
            assetManager = null;
        } else {
            if (this.m == null) {
                this.m = new com.airbnb.lottie.b.a(getCallback(), this.a);
            }
            assetManager = this.m;
        }
        if (assetManager != null) {
            return assetManager.a(fontFamily, style);
        }
        return null;
    }

    public void invalidateDrawable(@NonNull Drawable who) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    public void stop() {
        this.i.clear();
        this.f.h();
    }

    public boolean isRunning() {
        return this.f.isRunning();
    }
}
