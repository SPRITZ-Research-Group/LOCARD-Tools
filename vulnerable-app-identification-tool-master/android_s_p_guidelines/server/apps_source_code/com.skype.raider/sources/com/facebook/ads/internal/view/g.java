package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.j.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.s.a;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.b.e;
import java.util.HashMap;
import java.util.Map;

public class g extends i {
    private final com.facebook.ads.internal.adapters.a.g e;
    private final a f;
    private final s g = new s();
    private final a.a h;
    private long i;

    public g(Context context, com.facebook.ads.internal.adapters.a.g gVar, c cVar) {
        super(context, cVar);
        this.e = gVar;
        this.h = new a.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public final void a() {
                if (!this.a.g.b()) {
                    this.a.g.a();
                    Map hashMap = new HashMap();
                    this.a.f.a(hashMap);
                    hashMap.put("touch", k.a(this.a.g.e()));
                    this.a.b.a(this.a.e.c(), hashMap);
                    if (this.a.a() != null) {
                        this.a.a().a("com.facebook.ads.interstitial.impression.logged");
                    }
                }
            }
        };
        this.f = new a(this, 100, this.h);
        this.f.a(gVar.f());
    }

    private void a(int i) {
        h hVar = (h) this.e.d().get(0);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        d a = new d(imageView).a(hVar.c().h(), hVar.c().g());
        a.a(new e(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public final void a(boolean z) {
                if (z) {
                    this.a.f.a();
                }
            }
        });
        a.a(hVar.c().f());
        View a2 = com.facebook.ads.internal.view.component.a.c.a(new com.facebook.ads.internal.view.component.a.d.a(getContext(), this.b, a(), this.e, imageView, this.f, this.g).a(a).b(i).a());
        a(a2, a2.a(), i);
    }

    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.e);
        a(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.i = System.currentTimeMillis();
    }

    public final void a(Bundle bundle) {
    }

    public final void e() {
        if (this.e != null) {
            b.a(com.facebook.ads.internal.j.a.a(this.i, com.facebook.ads.internal.j.a.a.XOUT, this.e.e()));
            if (!TextUtils.isEmpty(this.e.c())) {
                Map hashMap = new HashMap();
                this.f.a(hashMap);
                hashMap.put("touch", k.a(this.g.e()));
                this.b.h(this.e.c(), hashMap);
            }
        }
        this.f.c();
        super.e();
    }

    public final void h() {
    }

    public final void i() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        a(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.g.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
