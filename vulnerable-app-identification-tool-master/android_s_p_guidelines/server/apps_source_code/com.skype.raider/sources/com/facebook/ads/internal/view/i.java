package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.a;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.p;
import com.facebook.ads.internal.q.a.u;

public abstract class i extends RelativeLayout implements a {
    protected static final int a = ((int) (56.0f * u.b));
    protected final c b;
    protected final f c = new f(getContext());
    protected a d;
    @Nullable
    private a.a e;
    private final p f = new p(this);

    protected i(Context context, c cVar) {
        super(context.getApplicationContext());
        this.b = cVar;
    }

    private void b() {
        removeAllViews();
        u.b(this);
    }

    protected final a.a a() {
        return this.e;
    }

    protected final void a(View view, boolean z, int i) {
        int d;
        this.f.a(p.a.DEFAULT);
        b();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, z ? 0 : a, 0, 0);
        addView(view, layoutParams);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, a);
        layoutParams2.addRule(10);
        if (i == 1) {
            d = this.d.a().d(z);
            this.c.a(this.d.a(), z);
        } else {
            d = this.d.b().d(z);
            this.c.a(this.d.b(), z);
        }
        addView(this.c, layoutParams2);
        u.a((View) this, d);
        if (this.e != null) {
            this.e.a((View) this);
            if (z && VERSION.SDK_INT >= 19) {
                this.f.a(p.a.FULL_SCREEN);
            }
        }
    }

    public final void a(final AudienceNetworkActivity audienceNetworkActivity, g gVar) {
        this.f.a(audienceNetworkActivity.getWindow());
        this.d = gVar.b();
        this.c.a(gVar.a(), gVar.c(), ((h) gVar.d().get(0)).c().c());
        this.c.setToolbarListener(new f.a(this) {
            final /* synthetic */ i b;

            public final void a() {
                audienceNetworkActivity.finish();
            }
        });
    }

    public void e() {
        this.f.a();
        this.c.setToolbarListener(null);
        b();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        this.c.d();
        super.onConfigurationChanged(configuration);
        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ i b;

            @RequiresApi(api = 16)
            public final void onGlobalLayout() {
                this.b.c.e();
                if (VERSION.SDK_INT >= 14) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public void setListener(a.a aVar) {
        this.e = aVar;
    }
}
