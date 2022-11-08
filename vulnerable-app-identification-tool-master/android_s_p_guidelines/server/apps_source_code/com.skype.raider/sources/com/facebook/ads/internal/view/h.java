package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.a;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.e;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.c.f;
import com.facebook.ads.internal.view.f.c.o;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class h extends i {
    private final a e = new a(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final boolean a() {
            return !this.a.c.a();
        }
    };
    private final e f = new e(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            com.facebook.ads.internal.view.f.b.d dVar2 = (com.facebook.ads.internal.view.f.b.d) dVar;
            if (this.a.a() != null) {
                this.a.a().a("videoInterstitalEvent", dVar2);
            }
            if (!this.a.z) {
                this.a.k.j();
                this.a.k.s();
                this.a.z = true;
            }
            if (this.a.w != null) {
                this.a.w.finish();
            }
        }
    };
    private final k g = new k(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            j jVar = (j) dVar;
            if (this.a.a() != null) {
                this.a.a().a("videoInterstitalEvent", jVar);
            }
        }
    };
    private final i h = new i(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            com.facebook.ads.internal.view.f.b.h hVar = (com.facebook.ads.internal.view.f.b.h) dVar;
            if (this.a.a() != null) {
                this.a.a().a("videoInterstitalEvent", hVar);
            }
        }
    };
    private final c i = new c(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            b bVar = (b) dVar;
            this.a.t.set(true);
            if (this.a.a() != null) {
                this.a.a().a("videoInterstitalEvent", bVar);
            }
        }
    };
    private final m j = new m(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            l lVar = (l) dVar;
            if (!this.a.z) {
                this.a.u.set(this.a.k.n());
                this.a.b();
            }
            if (this.a.a() != null) {
                this.a.a().a("videoInterstitalEvent", lVar);
            }
            this.a.p.a();
        }
    };
    private final com.facebook.ads.internal.view.f.a k = new com.facebook.ads.internal.view.f.a(getContext());
    private final o l;
    private final f m;
    private final g n;
    private final com.facebook.ads.internal.adapters.a.h o;
    private final com.facebook.ads.internal.s.a p;
    private final com.facebook.ads.internal.s.a.a q;
    private final s r = new s();
    @Nullable
    private final com.facebook.ads.internal.d.b s;
    private final AtomicBoolean t = new AtomicBoolean(false);
    private final AtomicBoolean u = new AtomicBoolean(false);
    private final com.facebook.ads.internal.view.f.c v;
    @Nullable
    private AudienceNetworkActivity w;
    @Nullable
    private com.facebook.ads.internal.view.f.a.a x;
    private long y;
    private boolean z = false;

    public h(Context context, com.facebook.ads.internal.m.c cVar, g gVar, @Nullable com.facebook.ads.internal.d.b bVar) {
        com.facebook.ads.internal.view.f.a.b gVar2;
        super(context, cVar);
        this.k.setVideoProgressReportIntervalMs(gVar.h());
        u.a(this.k);
        u.a(this.k, 0);
        this.n = gVar;
        this.o = (com.facebook.ads.internal.adapters.a.h) this.n.d().get(0);
        this.s = bVar;
        this.l = new o(getContext());
        this.m = new f(context);
        this.k.a().a(this.g, this.h, this.i, this.f, this.j);
        com.facebook.ads.internal.adapters.a.h hVar = this.o;
        this.k.b();
        this.k.a(this.l);
        this.k.a(this.m);
        if (!TextUtils.isEmpty(hVar.c().f())) {
            gVar2 = new com.facebook.ads.internal.view.f.c.g(getContext());
            this.k.a(gVar2);
            gVar2.setImage(hVar.c().f());
        }
        gVar2 = new com.facebook.ads.internal.view.f.c.l(getContext(), true);
        this.k.a(gVar2);
        this.k.a(new com.facebook.ads.internal.view.f.c.d(gVar2, hVar.c().d() ? com.facebook.ads.internal.view.f.c.d.a.FADE_OUT_ON_PLAY : com.facebook.ads.internal.view.f.c.d.a.VISIBLE, true));
        this.k.a(new com.facebook.ads.internal.view.f.c.k(getContext()));
        this.k.a(this.c);
        this.q = new com.facebook.ads.internal.s.a.a(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public final void a() {
                if (!this.a.r.b()) {
                    this.a.r.a();
                    Map hashMap = new HashMap();
                    if (!TextUtils.isEmpty(this.a.n.c())) {
                        this.a.p.a(hashMap);
                        hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(this.a.r.e()));
                        this.a.b.a(this.a.n.c(), hashMap);
                        if (this.a.a() != null) {
                            this.a.a().a("com.facebook.ads.interstitial.impression.logged");
                        }
                    }
                }
            }
        };
        this.p = new com.facebook.ads.internal.s.a(this, 1, this.q);
        this.p.a(gVar.f());
        this.p.b(gVar.g());
        this.v = new com.facebook.ads.internal.view.f.b(getContext(), this.b, this.k, this.n.c());
        String a = this.o.c().a();
        String str = "";
        if (!(this.s == null || a == null)) {
            str = this.s.b(a);
        }
        if (TextUtils.isEmpty(str)) {
            str = a;
        }
        this.k.setVideoURI(str);
    }

    private void a(int i) {
        View a = com.facebook.ads.internal.view.component.a.c.a(new com.facebook.ads.internal.view.component.a.d.a(getContext(), this.b, a(), this.n, this.k, this.p, this.r).a(a).b(i).a(this.l).a(this.m).a());
        b();
        a(a, a.a(), i);
    }

    private void b() {
        this.m.setVisibility(this.u.get() ? 0 : 8);
    }

    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.n);
        this.w = audienceNetworkActivity;
        a(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.w.a(this.e);
        com.facebook.ads.internal.adapters.a.h hVar = (com.facebook.ads.internal.adapters.a.h) this.n.d().get(0);
        if (hVar.c().d()) {
            this.k.setVolume(hVar.c().e() ? 1.0f : 0.0f);
            this.k.a(com.facebook.ads.internal.view.f.a.a.AUTO_STARTED);
        }
        this.y = System.currentTimeMillis();
    }

    public final void a(Bundle bundle) {
    }

    public final void e() {
        if (!this.z) {
            if (!this.t.get()) {
                this.k.c();
            }
            if (this.n != null) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.y, com.facebook.ads.internal.j.a.a.XOUT, this.n.e()));
                if (!TextUtils.isEmpty(this.n.c())) {
                    Map hashMap = new HashMap();
                    this.p.a(hashMap);
                    hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(this.r.e()));
                    this.b.h(this.n.c(), hashMap);
                }
            }
            this.k.j();
            this.k.s();
            this.z = true;
        }
        this.p.c();
        this.w = null;
        super.e();
    }

    public final void h() {
        if (!this.z && this.k.g() == com.facebook.ads.internal.view.f.d.d.STARTED) {
            this.x = this.k.h();
            this.k.a(false);
        }
    }

    public final void i() {
        if (!this.z && this.x != null) {
            this.k.a(this.x);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        u.b(this.k);
        u.b(this.l);
        u.b(this.m);
        a(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.r.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
