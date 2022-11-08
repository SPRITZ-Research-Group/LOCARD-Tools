package com.facebook.ads.internal.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.k;
import com.facebook.ads.internal.adapters.y;
import com.facebook.ads.internal.adapters.z;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.view.a.a;
import com.facebook.ads.internal.view.b.a.b;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class e implements a {
    private static final String a = e.class.getSimpleName();
    private final a b;
    private final com.facebook.ads.internal.view.b.a c;
    private final b d;
    private final z e;
    private final c f;
    private y g;
    private long h = System.currentTimeMillis();
    private long i;
    private com.facebook.ads.internal.j.a.a j;

    public e(final AudienceNetworkActivity audienceNetworkActivity, final c cVar, a aVar) {
        this.b = aVar;
        this.f = cVar;
        this.d = new com.facebook.ads.internal.view.b.a.c(this) {
            final /* synthetic */ e c;
            private long d = 0;

            public final void a() {
                this.c.e.b();
            }

            public final void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && "close".equals(parse.getAuthority())) {
                    audienceNetworkActivity.finish();
                    return;
                }
                long j = this.d;
                this.d = System.currentTimeMillis();
                if (this.d - j >= 1000) {
                    if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority())) {
                        this.c.b.a("com.facebook.ads.interstitial.clicked");
                    }
                    com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(audienceNetworkActivity, cVar, this.c.g.c(), parse, map);
                    if (a != null) {
                        try {
                            this.c.j = a.a();
                            this.c.i = System.currentTimeMillis();
                            a.b();
                        } catch (Exception e) {
                            e.a;
                        }
                    }
                }
            }

            public final void b() {
                this.c.e.a();
            }
        };
        this.c = new com.facebook.ads.internal.view.b.a(audienceNetworkActivity, new WeakReference(this.d), 1);
        this.c.setLayoutParams(new LayoutParams(-1, -1));
        k anonymousClass2 = new k(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.b.a("com.facebook.ads.interstitial.impression.logged");
            }
        };
        this.e = new z(audienceNetworkActivity, cVar, this.c, this.c.d(), anonymousClass2);
        aVar.a(this.c);
    }

    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (bundle == null || !bundle.containsKey("dataModel")) {
            this.g = y.b(intent);
            if (this.g != null) {
                this.e.a(this.g);
                this.c.loadDataWithBaseURL(com.facebook.ads.internal.q.c.b.a(), this.g.d(), "text/html", "utf-8", null);
                this.c.a(this.g.h(), this.g.i());
                return;
            }
            return;
        }
        this.g = y.a(bundle.getBundle("dataModel"));
        if (this.g != null) {
            this.c.loadDataWithBaseURL(com.facebook.ads.internal.q.c.b.a(), this.g.d(), "text/html", "utf-8", null);
            this.c.a(this.g.h(), this.g.i());
        }
    }

    public final void a(Bundle bundle) {
        if (this.g != null) {
            bundle.putBundle("dataModel", this.g.j());
        }
    }

    public final void e() {
        if (this.g != null) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.h, com.facebook.ads.internal.j.a.a.XOUT, this.g.g()));
            if (!TextUtils.isEmpty(this.g.c())) {
                Map hashMap = new HashMap();
                this.c.d().a(hashMap);
                hashMap.put("touch", com.facebook.ads.internal.q.a.k.a(this.c.f()));
                this.f.h(this.g.c(), hashMap);
            }
        }
        com.facebook.ads.internal.q.c.b.a(this.c);
        this.c.destroy();
    }

    public final void h() {
        this.c.onPause();
    }

    public final void i() {
        if (!(this.i <= 0 || this.j == null || this.g == null)) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.i, this.j, this.g.g()));
        }
        this.c.onResume();
    }

    public void setListener(a aVar) {
    }
}
