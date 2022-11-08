package com.airbnb.lottie;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import defpackage.tg;
import defpackage.th;
import defpackage.tm;
import defpackage.vc;
import defpackage.we;
import defpackage.ww;
import defpackage.xc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class f extends Drawable implements Animatable, Callback {
    private static final String c = "f";
    a a;
    q b;
    private final Matrix d = new Matrix();
    private d e;
    private final ww f = new ww();
    private float g = 1.0f;
    private final Set<Object> h = new HashSet();
    private final ArrayList<g> i = new ArrayList();
    private th j;
    private String k;
    private b l;
    private tg m;
    private boolean n;
    private vc o;
    private int p = 255;
    private boolean q;

    public int getOpacity() {
        return -3;
    }

    public f() {
        this.f.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (this.a.o != null) {
                    this.a.o.a(this.a.f.d());
                }
            }
        });
    }

    public final boolean a() {
        return this.n;
    }

    public final void a(boolean z) {
        if (this.n != z) {
            if (VERSION.SDK_INT < 19) {
                Log.w(c, "Merge paths are not supported pre-Kit Kat.");
                return;
            }
            this.n = z;
            if (this.e != null) {
                p();
            }
        }
    }

    public final void a(String str) {
        this.k = str;
    }

    public final String b() {
        return this.k;
    }

    public final void c() {
        if (this.j != null) {
            this.j.a();
        }
    }

    public final boolean a(d dVar) {
        if (this.e == dVar) {
            return false;
        }
        d();
        this.e = dVar;
        p();
        this.f.a(dVar);
        d(this.f.getAnimatedFraction());
        e(this.g);
        q();
        Iterator it = new ArrayList(this.i).iterator();
        while (it.hasNext()) {
            ((g) it.next()).a();
            it.remove();
        }
        this.i.clear();
        dVar.a(this.q);
        return true;
    }

    public final void b(boolean z) {
        this.q = z;
        if (this.e != null) {
            this.e.a(z);
        }
    }

    private void p() {
        this.o = new vc(this, we.a(this.e), this.e.g(), this.e);
    }

    public final void d() {
        c();
        if (this.f.isRunning()) {
            this.f.cancel();
        }
        this.e = null;
        this.o = null;
        this.j = null;
        this.f.e();
        invalidateSelf();
    }

    public void invalidateSelf() {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void setAlpha(int i) {
        this.p = i;
    }

    public int getAlpha() {
        return this.p;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Log.w("LOTTIE", "Use addColorFilter instead.");
    }

    public void draw(Canvas canvas) {
        c.b("Drawable#draw");
        if (this.o != null) {
            float f = this.g;
            float min = Math.min(((float) canvas.getWidth()) / ((float) this.e.b().width()), ((float) canvas.getHeight()) / ((float) this.e.b().height()));
            if (f > min) {
                f = this.g / min;
            } else {
                min = f;
                f = 1.0f;
            }
            if (f > 1.0f) {
                canvas.save();
                float width = ((float) this.e.b().width()) / 2.0f;
                float height = ((float) this.e.b().height()) / 2.0f;
                float f2 = width * min;
                float f3 = height * min;
                canvas.translate((this.g * width) - f2, (this.g * height) - f3);
                canvas.scale(f, f, f2, f3);
            }
            this.d.reset();
            this.d.preScale(min, min);
            this.o.a(canvas, this.d, this.p);
            c.c("Drawable#draw");
            if (f > 1.0f) {
                canvas.restore();
            }
        }
    }

    public void start() {
        e();
    }

    public final void e() {
        if (this.o == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public final void a() {
                    this.a.e();
                }
            });
        } else {
            this.f.f();
        }
    }

    public final void a(final int i) {
        if (this.e == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f b;

                public final void a() {
                    this.b.a(i);
                }
            });
        } else {
            this.f.b(i);
        }
    }

    public final float f() {
        return this.f.h();
    }

    public final void a(final float f) {
        if (this.e == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f b;

                public final void a() {
                    this.b.a(f);
                }
            });
            return;
        }
        float d = this.e.d();
        a((int) (d + (f * (this.e.e() - d))));
    }

    public final void b(final int i) {
        if (this.e == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f b;

                public final void a() {
                    this.b.b(i);
                }
            });
        } else {
            this.f.c(i);
        }
    }

    public final void b(final float f) {
        if (this.e == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f b;

                public final void a() {
                    this.b.b(f);
                }
            });
            return;
        }
        float d = this.e.d();
        b((int) (d + (f * (this.e.e() - d))));
    }

    public final void a(final int i, final int i2) {
        if (this.e == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f c;

                public final void a() {
                    this.c.a(i, i2);
                }
            });
        } else {
            this.f.a(i, i2);
        }
    }

    public final void a(final float f, final float f2) {
        if (this.e == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f c;

                public final void a() {
                    this.c.a(f, f2);
                }
            });
            return;
        }
        float d = this.e.d();
        int e = (int) (d + (f * (this.e.e() - d)));
        d = this.e.d();
        a(e, (int) (d + (f2 * (this.e.e() - d))));
    }

    public final void c(float f) {
        this.f.a(f);
    }

    public final void a(AnimatorListener animatorListener) {
        this.f.addListener(animatorListener);
    }

    public final void g() {
        this.f.removeAllListeners();
    }

    public final void c(final int i) {
        if (this.e == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f b;

                public final void a() {
                    this.b.c(i);
                }
            });
        } else {
            this.f.a(i);
        }
    }

    public final void d(final float f) {
        if (this.e == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f b;

                public final void a() {
                    this.b.d(f);
                }
            });
            return;
        }
        float d = this.e.d();
        c((int) (d + (f * (this.e.e() - d))));
    }

    public final void d(int i) {
        this.f.setRepeatMode(i);
    }

    public final int h() {
        return this.f.getRepeatMode();
    }

    public final void e(int i) {
        this.f.setRepeatCount(i);
    }

    public final int i() {
        return this.f.getRepeatCount();
    }

    public final boolean j() {
        return this.f.isRunning();
    }

    public final void e(float f) {
        this.g = f;
        q();
    }

    public final void a(b bVar) {
        this.l = bVar;
        if (this.j != null) {
            this.j.a(bVar);
        }
    }

    public final void a(a aVar) {
        this.a = aVar;
        if (this.m != null) {
            this.m.a(aVar);
        }
    }

    public final q k() {
        return this.b;
    }

    public final boolean l() {
        return this.b == null && this.e.h().b() > 0;
    }

    public final d m() {
        return this.e;
    }

    private void q() {
        if (this.e != null) {
            float f = this.g;
            setBounds(0, 0, (int) (((float) this.e.b().width()) * f), (int) (((float) this.e.b().height()) * f));
        }
    }

    public final void n() {
        this.i.clear();
        this.f.cancel();
    }

    public final float o() {
        return this.f.d();
    }

    public int getIntrinsicWidth() {
        return this.e == null ? -1 : (int) (((float) this.e.b().width()) * this.g);
    }

    public int getIntrinsicHeight() {
        return this.e == null ? -1 : (int) (((float) this.e.b().height()) * this.g);
    }

    public final <T> void a(final tm tmVar, final T t, final xc<T> xcVar) {
        if (this.o == null) {
            this.i.add(new g(this) {
                final /* synthetic */ f d;

                public final void a() {
                    this.d.a(tmVar, t, xcVar);
                }
            });
            return;
        }
        Object obj = 1;
        if (tmVar.a() != null) {
            tmVar.a().a(t, xcVar);
        } else {
            List emptyList;
            if (this.o == null) {
                Log.w("LOTTIE", "Cannot resolve KeyPath. Composition is not set yet.");
                emptyList = Collections.emptyList();
            } else {
                List arrayList = new ArrayList();
                this.o.a(tmVar, 0, arrayList, new tm(new String[0]));
                emptyList = arrayList;
            }
            for (int i = 0; i < emptyList.size(); i++) {
                ((tm) emptyList.get(i)).a().a(t, xcVar);
            }
            if (emptyList.isEmpty()) {
                obj = null;
            }
        }
        if (obj != null) {
            invalidateSelf();
            if (t == j.w) {
                d(this.f.d());
            }
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public void stop() {
        this.i.clear();
        this.f.g();
    }

    public boolean isRunning() {
        return this.f.isRunning();
    }

    public final Bitmap b(String str) {
        th thVar;
        if (getCallback() == null) {
            thVar = null;
        } else {
            if (this.j != null) {
                thVar = this.j;
                Callback callback = getCallback();
                Context context = (callback == null || !(callback instanceof View)) ? null : ((View) callback).getContext();
                if (!thVar.a(context)) {
                    this.j.a();
                    this.j = null;
                }
            }
            if (this.j == null) {
                this.j = new th(getCallback(), this.k, this.l, this.e.j());
            }
            thVar = this.j;
        }
        if (thVar != null) {
            return thVar.a(str);
        }
        return null;
    }

    public final Typeface a(String str, String str2) {
        tg tgVar;
        if (getCallback() == null) {
            tgVar = null;
        } else {
            if (this.m == null) {
                this.m = new tg(getCallback(), this.a);
            }
            tgVar = this.m;
        }
        if (tgVar != null) {
            return tgVar.a(str, str2);
        }
        return null;
    }
}
