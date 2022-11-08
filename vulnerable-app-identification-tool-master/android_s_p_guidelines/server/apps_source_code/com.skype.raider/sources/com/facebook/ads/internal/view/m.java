package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.e;
import com.facebook.ads.internal.view.f.b.f;
import com.facebook.ads.internal.view.f.b.g;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.p;

public final class m implements a {
    private final k a = new k(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            this.a.h.a("videoInterstitalEvent", (j) dVar);
        }
    };
    private final i b = new i(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            this.a.h.a("videoInterstitalEvent", (h) dVar);
        }
    };
    private final c c = new c(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            this.a.h.a("videoInterstitalEvent", (b) dVar);
        }
    };
    private final e d = new e(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            this.a.e.finish();
        }
    };
    private final AudienceNetworkActivity e;
    private final com.facebook.ads.internal.m.c f;
    private final a g;
    private final a.a h;
    private com.facebook.ads.internal.view.f.b i;
    private int j;

    public m(final AudienceNetworkActivity audienceNetworkActivity, com.facebook.ads.internal.m.c cVar, a.a aVar) {
        this.e = audienceNetworkActivity;
        this.f = cVar;
        this.g = new a(audienceNetworkActivity);
        this.g.a(new com.facebook.ads.internal.view.f.c.b(audienceNetworkActivity));
        this.g.a().a(this.a, this.b, this.c, this.d);
        this.h = aVar;
        this.g.setIsFullScreen(true);
        this.g.setVolume(1.0f);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        this.g.setLayoutParams(layoutParams);
        aVar.a(this.g);
        View dVar = new d(audienceNetworkActivity);
        dVar.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ m b;

            public final void onClick(View view) {
                audienceNetworkActivity.finish();
            }
        });
        aVar.a(dVar);
    }

    public final void a(int i) {
        this.g.setVideoProgressReportIntervalMs(i);
    }

    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        String stringExtra = intent.getStringExtra("useNativeCtaButton");
        if (!(stringExtra == null || stringExtra.isEmpty())) {
            View bVar = new com.facebook.ads.internal.view.c.b(audienceNetworkActivity, stringExtra);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            int i = (int) (u.b * 16.0f);
            layoutParams.setMargins(i, i, i, i);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            bVar.setLayoutParams(layoutParams);
            bVar.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }

                public final void onClick(View view) {
                    this.a.h.a("performCtaClick");
                }
            });
            this.h.a(bVar);
        }
        this.j = intent.getIntExtra("videoSeekTime", 0);
        this.i = new com.facebook.ads.internal.view.f.b((Context) audienceNetworkActivity, this.f, this.g, intent.getStringExtra("clientToken"), intent.getBundleExtra("videoLogger"));
        this.g.setVideoMPD(intent.getStringExtra("videoMPD"));
        this.g.setVideoURI(intent.getStringExtra("videoURL"));
        if (this.j > 0) {
            this.g.a(this.j);
        }
        if (intent.getBooleanExtra("autoplay", false)) {
            this.g.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
        }
    }

    public final void a(Bundle bundle) {
    }

    public final void a(View view) {
        this.g.setControlsAnchorView(view);
    }

    public final void e() {
        this.h.a("videoInterstitalEvent", new p(this.j, this.g.d()));
        this.i.b(this.g.d());
        this.g.j();
        this.g.s();
    }

    public final void h() {
        this.h.a("videoInterstitalEvent", new f());
        this.g.a(false);
    }

    public final void i() {
        this.h.a("videoInterstitalEvent", new g());
        this.g.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
    }

    public final void setListener(a.a aVar) {
    }
}
