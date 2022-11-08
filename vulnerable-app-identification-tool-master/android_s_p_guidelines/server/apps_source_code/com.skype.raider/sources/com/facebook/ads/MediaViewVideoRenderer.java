package com.facebook.ads;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.e;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.q;
import com.facebook.ads.internal.view.f.b.w;
import com.facebook.ads.internal.view.j;

public abstract class MediaViewVideoRenderer extends FrameLayout {
    private static final String d = MediaViewVideoRenderer.class.getSimpleName();
    @Nullable
    protected g a;
    protected l b;
    final j c;
    private final m e = new m(this) {
        final /* synthetic */ MediaViewVideoRenderer a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            this.a.b();
        }
    };
    private final k f = new k(this) {
        final /* synthetic */ MediaViewVideoRenderer a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.a != null) {
                this.a.a.c().a(true, true);
            }
            MediaViewVideoRenderer.c();
        }
    };
    private final i g = new i(this) {
        final /* synthetic */ MediaViewVideoRenderer a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            MediaViewVideoRenderer.d();
        }
    };
    private final q h = new q(this) {
        final /* synthetic */ MediaViewVideoRenderer a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            MediaViewVideoRenderer.e();
        }
    };
    private final c i = new c(this) {
        final /* synthetic */ MediaViewVideoRenderer a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            MediaViewVideoRenderer.f();
        }
    };
    private final w j = new w(this) {
        final /* synthetic */ MediaViewVideoRenderer a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            MediaViewVideoRenderer.g();
        }
    };
    private final e k = new e(this) {
        final /* synthetic */ MediaViewVideoRenderer a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.a != null) {
                this.a.a.c().a(false, true);
            }
            MediaViewVideoRenderer.h();
        }
    };

    public MediaViewVideoRenderer(Context context) {
        super(context);
        this.c = new j(context);
        i();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new j(context, attributeSet);
        i();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new j(context, attributeSet, i);
        i();
    }

    public static void c() {
    }

    public static void d() {
    }

    public static void e() {
    }

    public static void f() {
    }

    public static void g() {
    }

    public static void h() {
    }

    private void i() {
        this.c.setEnableBackgroundVideo(false);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.c.setLayoutParams(layoutParams);
        super.addView(this.c, -1, layoutParams);
        com.facebook.ads.internal.q.a.j.a(this.c, com.facebook.ads.internal.q.a.j.INTERNAL_AD_MEDIA);
        this.c.a().a(this.e, this.f, this.g, this.h, this.i, this.j, this.k);
    }

    protected void a() {
        this.c.a(false);
        this.c.a(null, null);
        this.c.setVideoMPD(null);
        this.c.setVideoURI(null);
        this.c.setVideoCTA(null);
        this.c.setNativeAd(null);
        this.b = l.DEFAULT;
        if (this.a != null) {
            this.a.c().a(false, false);
        }
        this.a = null;
    }

    protected void a(g gVar) {
        this.a = gVar;
        this.c.a(gVar.c().p(), gVar.p());
        this.c.setVideoMPD(gVar.c().o());
        this.c.setVideoURI(gVar.c().n());
        this.c.setVideoProgressReportIntervalMs(gVar.d().y());
        this.c.setVideoCTA(gVar.j());
        this.c.setNativeAd(gVar);
        this.b = l.a(gVar.c().q());
    }

    final void a(com.facebook.ads.internal.view.k kVar) {
        this.c.setListener(kVar);
    }

    public void addView(View view) {
    }

    public void addView(View view, int i) {
    }

    public void addView(View view, int i, int i2) {
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
    }

    public void addView(View view, LayoutParams layoutParams) {
    }

    public void b() {
    }

    public final void setVolume(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.c.setVolume(f);
    }
}
