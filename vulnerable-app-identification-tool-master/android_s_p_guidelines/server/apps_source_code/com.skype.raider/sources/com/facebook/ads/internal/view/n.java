package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.j;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.adapters.g;
import com.facebook.ads.internal.q.a.f;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.c.e;
import com.facebook.ads.internal.view.b.a.d;
import com.facebook.ads.internal.view.f.b.z;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(16)
public class n extends RelativeLayout implements com.facebook.ads.internal.q.a.f.a, a, d {
    private static final LayoutParams a = new LayoutParams(-1, -1);
    private final RelativeLayout b;
    private final com.facebook.ads.internal.m.c c;
    private final k d;
    private final j e;
    private final com.facebook.ads.internal.adapters.a.a f;
    private final f g;
    private final f h;
    private final f i;
    private int j;
    private WeakReference<com.facebook.ads.internal.view.b.a> k;
    private boolean l = false;
    @Nullable
    private Context m;
    @Nullable
    private AudienceNetworkActivity n;
    @Nullable
    private com.facebook.ads.internal.view.a.a o;
    private com.facebook.ads.internal.view.b.a.b p;
    private final AtomicBoolean q = new AtomicBoolean();
    private Executor r = AsyncTask.THREAD_POOL_EXECUTOR;
    private final com.facebook.ads.AudienceNetworkActivity.a s = new com.facebook.ads.AudienceNetworkActivity.a(this) {
        final /* synthetic */ n a;

        {
            this.a = r1;
        }

        public final boolean a() {
            return !this.a.h.d();
        }
    };
    private g t;

    private static class a implements OnTouchListener {
        final WeakReference<com.facebook.ads.internal.view.b.a> a;
        final com.facebook.ads.internal.m.c b;
        final k c;

        private a(com.facebook.ads.internal.view.b.a aVar, com.facebook.ads.internal.m.c cVar, k kVar) {
            this.a = new WeakReference(aVar);
            this.b = cVar;
            this.c = kVar;
        }

        /* synthetic */ a(com.facebook.ads.internal.view.b.a aVar, com.facebook.ads.internal.m.c cVar, k kVar, byte b) {
            this(aVar, cVar, kVar);
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.a.get() != null && motionEvent.getAction() == 1) {
                Map hashMap = new HashMap();
                ((com.facebook.ads.internal.view.b.a) this.a.get()).d().a(hashMap);
                hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(((com.facebook.ads.internal.view.b.a) this.a.get()).e().e()));
                this.b.c(this.c.g(), hashMap);
            }
            return false;
        }
    }

    private class b {
        final /* synthetic */ n a;

        private b(n nVar) {
            this.a = nVar;
        }

        /* synthetic */ b(n nVar, byte b) {
            this(nVar);
        }

        @JavascriptInterface
        public final void onCTAClick() {
            if (this.a.k.get() != null) {
                com.facebook.ads.internal.view.b.a aVar = (com.facebook.ads.internal.view.b.a) this.a.k.get();
                com.facebook.ads.internal.view.component.a aVar2 = new com.facebook.ads.internal.view.component.a(this.a.m, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), this.a.f.a(), this.a.c, this.a.o, aVar.d(), aVar.e());
                aVar2.a(this.a.d.c(), this.a.d.g(), new HashMap());
                aVar2.performClick();
            }
        }
    }

    private static class c implements com.facebook.ads.internal.q.c.e.a {
        final WeakReference<com.facebook.ads.internal.view.a.a> a;

        private c(WeakReference<com.facebook.ads.internal.view.a.a> weakReference) {
            this.a = weakReference;
        }

        /* synthetic */ c(WeakReference weakReference, byte b) {
            this(weakReference);
        }

        public final void a() {
            if (this.a.get() != null) {
                ((com.facebook.ads.internal.view.a.a) this.a.get()).a(z.REWARD_SERVER_FAILED.a());
            }
        }

        public final void a(com.facebook.ads.internal.q.c.f fVar) {
            if (this.a.get() != null) {
                if (fVar == null || !fVar.a()) {
                    ((com.facebook.ads.internal.view.a.a) this.a.get()).a(z.REWARD_SERVER_FAILED.a());
                } else {
                    ((com.facebook.ads.internal.view.a.a) this.a.get()).a(z.REWARD_SERVER_SUCCESS.a());
                }
            }
        }
    }

    public n(Context context, com.facebook.ads.internal.m.c cVar, com.facebook.ads.internal.view.a.a aVar, k kVar) {
        super(context);
        this.m = context;
        this.o = aVar;
        this.c = cVar;
        this.d = kVar;
        this.e = kVar.e().i();
        this.f = kVar.d();
        this.b = new RelativeLayout(context);
        this.g = new f(context);
        this.h = new f(this.e.b(), this);
        this.i = new f(3, new com.facebook.ads.internal.q.a.f.a(this) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public final void a() {
                n.b(this.a);
            }

            public final void a(int i) {
            }
        });
    }

    static /* synthetic */ void b(n nVar) {
        nVar.p = new com.facebook.ads.internal.view.b.a.c(nVar) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public final void b() {
                if (this.a.q.compareAndSet(false, true)) {
                    this.a.h.a();
                    this.a.t.a();
                }
            }

            public final void c() {
                this.a.l = true;
                if (this.a.k.get() != null) {
                    ((com.facebook.ads.internal.view.b.a) this.a.k.get()).setVisibility(4);
                }
                if (this.a.o != null) {
                    this.a.o.a(z.REWARDED_VIDEO_ERROR.a());
                }
            }
        };
        View aVar = new com.facebook.ads.internal.view.b.a(nVar.m, new WeakReference(nVar.p), 10);
        aVar.setLogMultipleImpressions(false);
        aVar.setWaitForAssetsToLoad(true);
        aVar.setCheckAssetsByJavascriptBridge(false);
        WebSettings settings = aVar.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        if (nVar.m != null) {
            nVar.t = new g(nVar.m, nVar.c, aVar.d(), aVar.e(), new com.facebook.ads.internal.adapters.k(nVar) {
                final /* synthetic */ n a;

                {
                    this.a = r1;
                }

                public final void a() {
                    if (this.a.o != null) {
                        this.a.o.a(z.REWARDED_VIDEO_IMPRESSION.a());
                    }
                }
            });
            nVar.t.a(nVar.d);
        }
        nVar.k = new WeakReference(aVar);
        aVar.loadUrl(nVar.e.a());
        aVar.setOnTouchListener(new a(aVar, nVar.c, nVar.d, (byte) 0));
        aVar.addJavascriptInterface(new b(nVar, (byte) 0), "FbPlayableAd");
        u.a(nVar.b, nVar.f.a().d(true));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(3, nVar.g.getId());
        aVar.setLayoutParams(layoutParams);
        aVar.setVisibility(4);
        aVar.setOnAssetsLoadedListener(nVar);
        nVar.b.addView(nVar.g);
        nVar.b.addView(aVar);
    }

    public final void a() {
        this.g.a(true);
        CharSequence a = this.d.f().a();
        if (!(this.m == null && TextUtils.isEmpty(a))) {
            e eVar = new e(this.m, new HashMap());
            eVar.a(new c(new WeakReference(this.o), (byte) 0));
            eVar.executeOnExecutor(this.r, new String[]{a});
        }
        if (this.o != null) {
            this.o.a(z.REWARDED_VIDEO_COMPLETE.a(), new com.facebook.ads.internal.view.f.b.b(0, 0));
        }
    }

    public final void a(int i) {
        this.g.setProgress((1.0f - (((float) i) / ((float) this.e.b()))) * 100.0f);
    }

    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.o != null && this.m != null) {
            this.n = audienceNetworkActivity;
            this.n.a(this.s);
            this.j = audienceNetworkActivity.getRequestedOrientation();
            audienceNetworkActivity.setRequestedOrientation(1);
            this.g.a(this.f.a(), true);
            this.g.setShowPageDetails(false);
            this.g.a(this.d.a(), this.d.g(), this.e.b());
            this.g.setToolbarListener(new com.facebook.ads.internal.view.f.a(this) {
                final /* synthetic */ n a;

                {
                    this.a = r1;
                }

                public final void a() {
                    if (this.a.o != null) {
                        this.a.o.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
                    }
                }
            });
            u.a(this.g);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.addRule(10);
            this.g.setLayoutParams(layoutParams);
            View aVar = new com.facebook.ads.internal.view.d.a(this.m, this.d);
            this.b.setLayoutParams(a);
            u.a(this.b, this.f.a().d(true));
            this.b.addView(aVar, a);
            addView(this.b);
            setLayoutParams(a);
            this.o.a((View) this);
            this.i.a();
        }
    }

    public final void a(Bundle bundle) {
    }

    public final void b() {
        if (!this.l && this.k.get() != null) {
            ((com.facebook.ads.internal.view.b.a) this.k.get()).setVisibility(0);
        }
    }

    public final void e() {
        this.i.b();
        this.h.b();
        this.g.setToolbarListener(null);
        if (this.n != null) {
            this.n.b(this.s);
            this.n.setRequestedOrientation(this.j);
        }
        com.facebook.ads.internal.view.b.a aVar = (com.facebook.ads.internal.view.b.a) this.k.get();
        if (aVar != null) {
            aVar.removeJavascriptInterface("FbPlayableAd");
        }
        if (!(aVar == null || TextUtils.isEmpty(this.d.g()))) {
            Map hashMap = new HashMap();
            aVar.d().a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(aVar.e().e()));
            this.c.h(this.d.g(), hashMap);
        }
        this.o = null;
        this.p = null;
        this.n = null;
        this.m = null;
    }

    public final void h() {
        this.i.b();
        this.h.b();
    }

    public final void i() {
        if (!this.i.d()) {
            this.i.a();
        } else if (!this.h.c()) {
            this.h.a();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.k != null && this.k.get() != null) {
            if (z) {
                i();
            } else {
                h();
            }
        }
    }

    public void setListener(com.facebook.ads.internal.view.a.a aVar) {
        this.o = aVar;
    }
}
