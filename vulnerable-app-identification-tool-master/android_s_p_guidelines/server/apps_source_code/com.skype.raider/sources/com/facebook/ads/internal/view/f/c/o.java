package com.facebook.ads.internal.view.f.c;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import com.microsoft.react.videofxp.VideoFXPModule;
import java.util.concurrent.atomic.AtomicInteger;

public class o extends RelativeLayout implements b {
    private static final int a = ((int) (6.0f * u.b));
    private ObjectAnimator b;
    private AtomicInteger c;
    private ProgressBar d;
    @Nullable
    private a e;
    private f f;
    private f g;
    private f h;
    private f i;

    public o(Context context) {
        this(context, a);
    }

    private o(Context context, int i) {
        super(context);
        this.f = new com.facebook.ads.internal.view.f.b.o(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                if (this.a.e != null) {
                    o.a(this.a, this.a.e.f(), this.a.e.d());
                }
            }
        };
        this.g = new i(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.b();
            }
        };
        this.h = new k(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                if (this.a.e != null) {
                    o.a(this.a, this.a.e.f(), this.a.e.d());
                }
            }
        };
        this.i = new c(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                if (this.a.e != null) {
                    o.c(this.a);
                }
            }
        };
        this.c = new AtomicInteger(-1);
        this.d = new ProgressBar(context, null, 16842872);
        this.d.setLayoutParams(new LayoutParams(-1, i));
        setProgressBarColor(-12549889);
        this.d.setMax(10000);
        addView(this.d);
    }

    private void b() {
        if (this.b != null) {
            this.b.cancel();
            this.b.setTarget(null);
            this.b = null;
            this.d.clearAnimation();
        }
    }

    public final void a() {
        b();
        this.d = null;
        this.e = null;
    }

    public final void a(a aVar) {
        this.e = aVar;
        aVar.a().a(this.g, this.h, this.f, this.i);
    }

    public final void b(a aVar) {
        aVar.a().b(this.f, this.h, this.g, this.i);
        this.e = null;
    }

    public void setProgressBarColor(int i) {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        ColorDrawable colorDrawable2 = new ColorDrawable(0);
        ScaleDrawable scaleDrawable = new ScaleDrawable(new ColorDrawable(i), 8388611, 1.0f, -1.0f);
        Drawable layerDrawable = new LayerDrawable(new Drawable[]{colorDrawable, colorDrawable2, scaleDrawable});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        this.d.setProgressDrawable(layerDrawable);
    }

    static /* synthetic */ void a(o oVar, int i, int i2) {
        oVar.b();
        if (oVar.c.get() < i2 && i > i2) {
            int i3 = (i2 * 10000) / i;
            int min = (Math.min(i2 + 250, i) * 10000) / i;
            oVar.b = ObjectAnimator.ofInt(oVar.d, VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, new int[]{i3, min});
            oVar.b.setDuration((long) Math.min(250, i - i2));
            oVar.b.setInterpolator(new LinearInterpolator());
            oVar.b.start();
            oVar.c.set(i2);
        }
    }

    static /* synthetic */ void c(o oVar) {
        oVar.b();
        oVar.b = ObjectAnimator.ofInt(oVar.d, VideoFXPModule.REENCODING_EVENT_PROGRESS_KEY, new int[]{0, 0});
        oVar.b.setDuration(0);
        oVar.b.setInterpolator(new LinearInterpolator());
        oVar.b.start();
        oVar.c.set(0);
    }
}
