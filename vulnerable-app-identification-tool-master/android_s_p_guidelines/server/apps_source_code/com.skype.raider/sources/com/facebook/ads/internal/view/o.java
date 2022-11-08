package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.e.b;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.e;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.z;
import com.facebook.ads.internal.view.f.c.d;
import com.facebook.ads.internal.view.f.c.f;
import com.facebook.ads.internal.view.f.c.j;
import com.facebook.ads.internal.view.f.c.l;
import com.microsoft.applications.telemetry.LogConfiguration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class o extends RelativeLayout implements a {
    static final /* synthetic */ boolean a = (!o.class.desiredAssertionStatus());
    private static final int b = ((int) (12.0f * u.b));
    private static final int c = ((int) (18.0f * u.b));
    private static final int d = ((int) (16.0f * u.b));
    private static final int e = ((int) (72.0f * u.b));
    private static final int f = ((int) (u.b * 56.0f));
    private static final int g = ((int) (u.b * 56.0f));
    private static final int h = ((int) (28.0f * u.b));
    private static final int i = ((int) (20.0f * u.b));
    private static final LayoutParams j = new LayoutParams(-1, -1);
    @Nullable
    private Context A;
    @Nullable
    private a B;
    @Nullable
    private a.a C;
    @Nullable
    private com.facebook.ads.internal.view.e.a D;
    @Nullable
    private d E;
    @Nullable
    private l F;
    @Nullable
    private j G;
    @Nullable
    private f H;
    private b I;
    private boolean J = false;
    private final AudienceNetworkActivity.a k = new AudienceNetworkActivity.a(this) {
        final /* synthetic */ o a;

        {
            this.a = r1;
        }

        public final boolean a() {
            return !this.a.J;
        }
    };
    private final c l = new c(this) {
        final /* synthetic */ o a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            com.facebook.ads.internal.view.f.b.b bVar = (com.facebook.ads.internal.view.f.b.b) dVar;
            if (this.a.C != null) {
                this.a.I.d();
                o.d(this.a);
                this.a.C.a(z.REWARDED_VIDEO_COMPLETE.a(), bVar);
            }
        }
    };
    private final e m = new e(this) {
        final /* synthetic */ o a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            if (this.a.C != null) {
                this.a.C.a(z.REWARDED_VIDEO_ERROR.a());
            }
            this.a.a();
        }
    };
    private final m n = new m(this) {
        final /* synthetic */ o a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            if (this.a.B != null) {
                this.a.B.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
                this.a.r.a();
                this.a.z.set(this.a.B.n());
                this.a.b();
            }
        }
    };
    private final com.facebook.ads.internal.view.f.b.o o = new com.facebook.ads.internal.view.f.b.o(this) {
        final /* synthetic */ o a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
            if (this.a.B != null && this.a.E != null && this.a.B.f() - this.a.B.d() <= LogConfiguration.BASE_BACKOFF_FOR_SENDING_RETRIES_MILLIS && this.a.E.a()) {
                this.a.E.b();
            }
        }
    };
    private final k p;
    private final com.facebook.ads.internal.m.c q;
    private final com.facebook.ads.internal.s.a r;
    private final com.facebook.ads.internal.s.a.a s;
    private final s t = new s();
    private final com.facebook.ads.internal.view.f.c.o u;
    private final com.facebook.ads.internal.view.f.b v;
    private final RelativeLayout w;
    private final f x;
    private final com.facebook.ads.internal.adapters.a.d y;
    private final AtomicBoolean z = new AtomicBoolean(false);

    public o(Context context, com.facebook.ads.internal.m.c cVar, a aVar, a.a aVar2, k kVar) {
        super(context);
        this.A = context;
        this.C = aVar2;
        this.B = aVar;
        this.q = cVar;
        this.p = kVar;
        this.y = this.p.d().a();
        this.w = new RelativeLayout(context);
        this.u = new com.facebook.ads.internal.view.f.c.o(this.A);
        this.x = new f(this.A);
        com.facebook.ads.internal.view.b.d a = new com.facebook.ads.internal.view.b.d(this.w, i).a();
        a.a = com.facebook.ads.internal.l.a.e(this.A);
        a.a(this.p.e().f());
        this.s = new com.facebook.ads.internal.s.a.a(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public final void a() {
                if (!this.a.t.b()) {
                    this.a.t.a();
                    Map hashMap = new HashMap();
                    if (!TextUtils.isEmpty(this.a.p.g())) {
                        this.a.r.a(hashMap);
                        hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(this.a.t.e()));
                        this.a.q.a(this.a.p.g(), hashMap);
                    }
                    if (this.a.C != null) {
                        this.a.C.a(z.REWARDED_VIDEO_IMPRESSION.a());
                    }
                }
            }
        };
        this.r = new com.facebook.ads.internal.s.a(this, 1, this.s);
        this.r.a(250);
        this.v = new com.facebook.ads.internal.view.f.b(this.A, this.q, this.B, this.p.g());
        this.I = new b(this.A, this.q, this.p, this.C, this.r, this.t);
        if (a || this.B != null) {
            this.B.setVideoProgressReportIntervalMs(kVar.h());
            u.a(this.B, -16777216);
            this.B.a().a(this.l, this.m, this.n, this.o);
            return;
        }
        throw new AssertionError();
    }

    private void b() {
        this.x.setVisibility(this.z.get() ? 0 : 8);
    }

    static /* synthetic */ void d(o oVar) {
        oVar.J = true;
        if (oVar.A != null) {
            View frameLayout = new FrameLayout(oVar.A);
            frameLayout.setLayoutParams(j);
            u.a(frameLayout, -1509949440);
            oVar.w.addView(frameLayout, 0);
        }
        if (VERSION.SDK_INT > 19) {
            Transition autoTransition = new AutoTransition();
            autoTransition.setDuration(200);
            autoTransition.setInterpolator(new AccelerateDecelerateInterpolator());
            TransitionManager.beginDelayedTransition(oVar.w, autoTransition);
        }
        if (oVar.B != null) {
            oVar.B.b();
            oVar.B.setVisibility(4);
        }
        if (oVar.H != null) {
            oVar.H.a(true);
            oVar.H.c();
        }
        u.a(oVar.B, oVar.G, oVar.x, oVar.u);
        Pair c = oVar.I.c();
        ViewGroup.LayoutParams layoutParams;
        switch ((b.a) c.first) {
            case MARKUP:
                u.a(oVar.D);
                oVar.w.addView((View) c.second, j);
                return;
            case SCREENSHOTS:
                if (oVar.D != null) {
                    oVar.D.setVisibility(0);
                    oVar.D.a();
                }
                layoutParams = new LayoutParams(-1, -1);
                layoutParams.setMargins(0, g, 0, 0);
                layoutParams.addRule(2, oVar.D.getId());
                oVar.w.addView((View) c.second, layoutParams);
                oVar.t.a();
                return;
            case INFO:
                u.a(oVar.D);
                layoutParams = new LayoutParams(-1, -2);
                layoutParams.addRule(15);
                layoutParams.setMargins(d, d, d, d);
                oVar.w.addView((View) c.second, layoutParams);
                oVar.t.a();
                return;
            default:
                return;
        }
    }

    public final void a() {
        if (this.B != null) {
            this.B.j();
            this.B.s();
        }
        if (this.r != null) {
            this.r.c();
        }
    }

    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.B != null && this.C != null) {
            ViewGroup.LayoutParams layoutParams;
            if (this.B != null) {
                this.B.b();
                this.B.a(new com.facebook.ads.internal.view.f.c.k(this.A));
                this.B.a(this.x);
                this.B.a(this.u);
                this.F = new l(this.A, true);
                com.facebook.ads.internal.view.f.a.b dVar = new d(this.F, d.a.FADE_OUT_ON_PLAY, true);
                this.B.a(this.F);
                this.B.a(dVar);
                this.D = new com.facebook.ads.internal.view.e.a(this.A, e, this.y, this.q, this.C, this.I.b() == b.a.INFO, this.I.b() == b.a.INFO, this.r, this.t);
                this.D.setInfo(this.p);
                this.E = new d(this.D, d.a.FADE_OUT_ON_PLAY, true);
                this.B.a(this.E);
                if (this.I.a() && this.p.e().c() > 0) {
                    this.G = new j(this.A, this.p.e().c(), -12286980);
                    this.G.setButtonMode(j.a.SKIP_BUTTON_MODE);
                    this.G.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ o a;

                        {
                            this.a = r1;
                        }

                        public final void onClick(View view) {
                            if (this.a.G != null && this.a.G.a() && this.a.G.b() != 0 && this.a.B != null) {
                                this.a.B.c();
                            }
                        }
                    });
                    this.B.a(this.G);
                } else if (!this.I.a()) {
                    this.H = new f(this.A);
                    this.H.a(this.p.a(), this.p.g(), this.p.e().c());
                    if (this.p.e().c() <= 0) {
                        this.H.b();
                    }
                    if (this.I.b() != b.a.INFO) {
                        this.H.c();
                    }
                    this.H.setToolbarListener(new f.a(this) {
                        final /* synthetic */ o a;

                        {
                            this.a = r1;
                        }

                        public final void a() {
                            if (!this.a.J && this.a.B != null) {
                                this.a.J = true;
                                this.a.B.c();
                            } else if (this.a.J && this.a.C != null) {
                                this.a.C.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
                            }
                        }
                    });
                    this.B.a(this.H);
                }
            }
            audienceNetworkActivity.a(this.k);
            this.B.setVideoURI(!TextUtils.isEmpty(this.p.e().b()) ? this.p.e().b() : this.p.e().a());
            int i = audienceNetworkActivity.getResources().getConfiguration().orientation;
            this.w.removeAllViews();
            this.w.addView(this.B, j);
            if (this.D != null) {
                u.a(this.D);
                this.D.a(i);
                layoutParams = new LayoutParams(-1, -2);
                layoutParams.addRule(12);
                this.D.setPadding(d, d, d, d);
                this.w.addView(this.D, layoutParams);
            }
            if (this.G != null) {
                layoutParams = new LayoutParams(f, f);
                layoutParams.addRule(10);
                layoutParams.addRule(11);
                this.G.setPadding(d, d, d, d);
                this.w.addView(this.G, layoutParams);
            }
            layoutParams = new LayoutParams(h, h);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            layoutParams.setMargins(b, b + g, b, c);
            this.w.addView(this.x, layoutParams);
            b();
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.addRule(12);
            this.w.addView(this.u, layoutParams);
            addView(this.w, j);
            if (this.H != null) {
                u.a(this.H);
                this.H.a(this.y, true);
                addView(this.H, new LayoutParams(-1, g));
            }
            setLayoutParams(j);
            this.C.a((View) this);
        }
    }

    public final void a(Bundle bundle) {
    }

    public final void e() {
        a();
        if (this.B != null) {
            this.B.a().b(this.l, this.m, this.n, this.o);
        }
        if (!TextUtils.isEmpty(this.p.g())) {
            Map hashMap = new HashMap();
            this.r.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(this.t.e()));
            this.q.h(this.p.g(), hashMap);
        }
        if (this.H != null) {
            this.H.setToolbarListener(null);
        }
        this.v.a();
        this.B = null;
        this.I.e();
        this.G = null;
        this.D = null;
        this.E = null;
        this.C = null;
        this.A = null;
        this.u.a();
    }

    public final void h() {
        if (this.B != null) {
            this.B.a(false);
        }
    }

    public final void i() {
        if (this.B != null && this.C != null && this.B.t() && !this.B.u()) {
            this.B.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.D != null) {
            this.D.a(configuration.orientation);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.t.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setListener(a.a aVar) {
    }
}
