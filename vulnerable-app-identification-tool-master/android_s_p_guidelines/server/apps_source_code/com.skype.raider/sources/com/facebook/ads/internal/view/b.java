package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebBackForwardList;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.a.a;
import com.facebook.ads.internal.view.a.f;
import java.util.HashMap;
import java.util.Map;

@TargetApi(19)
public class b implements a {
    private static final String a = b.class.getSimpleName();
    private final AudienceNetworkActivity b;
    private final a c;
    private final f d;
    private final com.facebook.ads.internal.view.a.b e;
    private final c f;
    private final AudienceNetworkActivity.a g = new AudienceNetworkActivity.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public final boolean a() {
            if (!this.a.d.canGoBack()) {
                return false;
            }
            this.a.d.goBack();
            return true;
        }
    };
    private String h;
    private String i;
    private long j;
    private boolean k = true;
    private long l = -1;
    private boolean m = true;

    public b(final AudienceNetworkActivity audienceNetworkActivity, c cVar, a.a aVar) {
        this.b = audienceNetworkActivity;
        this.f = cVar;
        int i = (int) (2.0f * u.b);
        this.c = new a(audienceNetworkActivity);
        this.c.setId(View.generateViewId());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.c.setLayoutParams(layoutParams);
        this.c.setListener(new a.a(this) {
            final /* synthetic */ b b;

            public final void a() {
                audienceNetworkActivity.finish();
            }
        });
        aVar.a(this.c);
        this.d = new f(audienceNetworkActivity);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, this.c.getId());
        layoutParams.addRule(12);
        this.d.setLayoutParams(layoutParams);
        this.d.setListener(new f.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.e.setProgress(100);
                this.a.k = false;
            }

            public final void a(int i) {
                if (this.a.k) {
                    this.a.e.setProgress(i);
                }
            }

            public final void a(String str) {
                this.a.k = true;
                this.a.c.setUrl(str);
            }

            public final void b(String str) {
                this.a.c.setTitle(str);
            }
        });
        aVar.a(this.d);
        this.e = new com.facebook.ads.internal.view.a.b(audienceNetworkActivity, null, 16842872);
        layoutParams = new RelativeLayout.LayoutParams(-1, i);
        layoutParams.addRule(3, this.c.getId());
        this.e.setLayoutParams(layoutParams);
        this.e.setProgress(0);
        aVar.a(this.e);
        audienceNetworkActivity.a(this.g);
    }

    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.l < 0) {
            this.l = System.currentTimeMillis();
        }
        if (bundle == null) {
            this.h = intent.getStringExtra("browserURL");
            this.i = intent.getStringExtra("clientToken");
            this.j = intent.getLongExtra("handlerTime", -1);
        } else {
            this.h = bundle.getString("browserURL");
            this.i = bundle.getString("clientToken");
            this.j = bundle.getLong("handlerTime", -1);
        }
        String str = this.h != null ? this.h : "about:blank";
        this.c.setUrl(str);
        this.d.loadUrl(str);
    }

    public final void a(Bundle bundle) {
        bundle.putString("browserURL", this.h);
    }

    public final void e() {
        this.b.b(this.g);
        com.facebook.ads.internal.q.c.b.a(this.d);
        this.d.destroy();
    }

    public final void h() {
        this.d.onPause();
        if (this.m) {
            this.m = false;
            f fVar = this.d;
            WebBackForwardList copyBackForwardList = fVar.copyBackForwardList();
            com.facebook.ads.internal.view.a.c a = new com.facebook.ads.internal.view.a.c.a(copyBackForwardList.getSize() > 0 ? copyBackForwardList.getItemAtIndex(0).getUrl() : fVar.getUrl()).a(this.j).b(this.l).c(this.d.d()).d(this.d.e()).e(this.d.f()).f(this.d.g()).g(System.currentTimeMillis()).a();
            c cVar = this.f;
            String str = this.i;
            Map hashMap = new HashMap(7);
            hashMap.put("initial_url", a.a);
            hashMap.put("handler_time_ms", String.valueOf(a.b));
            hashMap.put("load_start_ms", String.valueOf(a.c));
            hashMap.put("response_end_ms", String.valueOf(a.d));
            hashMap.put("dom_content_loaded_ms", String.valueOf(a.e));
            hashMap.put("scroll_ready_ms", String.valueOf(a.f));
            hashMap.put("load_finish_ms", String.valueOf(a.g));
            hashMap.put("session_finish_ms", String.valueOf(a.h));
            cVar.f(str, hashMap);
        }
    }

    public final void i() {
        this.d.onResume();
    }

    public void setListener(a.a aVar) {
    }
}
