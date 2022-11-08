package com.facebook.ads.internal.view.c.a;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ae;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.s.a;
import com.facebook.ads.internal.view.component.d;
import com.facebook.ads.internal.view.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class f extends i {
    private static final int e = ((int) (48.0f * u.b));
    private static final int f = ((int) (u.b * 8.0f));
    private static final int g = ((int) (u.b * 8.0f));
    private static final int h = ((int) (56.0f * u.b));
    private static final int i = ((int) (12.0f * u.b));
    private final s j = new s();
    @Nullable
    private b k;
    @Nullable
    private LinearLayout l;
    private String m;
    private long n;
    private String o;
    private List<b> p;
    @Nullable
    private d q;
    @Nullable
    private c r;
    private a s;
    private a.a t;
    private int u;
    private int v;

    public f(Context context, c cVar, @Nullable b bVar) {
        super(context, cVar);
        this.k = bVar;
    }

    private void b() {
        if (this.l != null) {
            this.l.removeAllViews();
            this.l = null;
        }
        if (this.r != null) {
            this.r.removeAllViews();
            this.r = null;
        }
        if (this.q != null) {
            this.q.removeAllViews();
            this.q = null;
        }
    }

    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        g gVar = (g) intent.getSerializableExtra("ad_data_bundle");
        super.a(audienceNetworkActivity, gVar);
        this.m = gVar.c();
        this.o = gVar.e();
        this.u = gVar.f();
        this.v = gVar.g();
        List d = gVar.d();
        this.p = new ArrayList(d.size());
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < d.size()) {
                this.p.add(new b(i2, d.size(), (h) d.get(i2)));
                i = i2 + 1;
            } else {
                setUpLayout(audienceNetworkActivity.getResources().getConfiguration().orientation);
                this.n = System.currentTimeMillis();
                return;
            }
        }
    }

    public final void a(Bundle bundle) {
    }

    public final void e() {
        super.e();
        com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.n, com.facebook.ads.internal.j.a.a.XOUT, this.o));
        if (!TextUtils.isEmpty(this.m)) {
            Map hashMap = new HashMap();
            this.s.a(hashMap);
            hashMap.put("touch", k.a(this.j.e()));
            this.b.h(this.m, hashMap);
        }
        b();
        this.s.c();
        this.s = null;
        this.t = null;
        this.p = null;
    }

    public final void h() {
    }

    public final void i() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        b();
        setUpLayout(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.j.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setUpLayout(int i) {
        int min;
        int i2;
        int i3;
        this.l = new LinearLayout(getContext());
        if (i == 1) {
            this.l.setGravity(17);
        } else {
            this.l.setGravity(48);
        }
        this.l.setLayoutParams(new LayoutParams(-1, -1));
        this.l.setOrientation(1);
        int i4 = u.a.widthPixels;
        int i5 = u.a.heightPixels;
        if (i == 1) {
            min = Math.min(i4 - (f * 4), i5 / 2);
            i2 = (i4 - min) / 8;
            i3 = i2 * 4;
        } else {
            min = i5 - ((h + e) + (f * 2));
            i2 = f;
            i3 = i2 * 2;
        }
        this.t = new a.a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public final void a() {
                Map hashMap = new HashMap();
                if (!this.a.j.b()) {
                    this.a.j.a();
                    if (this.a.a() != null) {
                        this.a.a().a("com.facebook.ads.interstitial.impression.logged");
                    }
                    if (!TextUtils.isEmpty(this.a.m)) {
                        this.a.s.a(hashMap);
                        hashMap.put("touch", k.a(this.a.j.e()));
                        this.a.b.a(this.a.m, hashMap);
                    }
                }
            }
        };
        this.s = new a(this, 1, this.t);
        this.s.a(this.u);
        this.s.b(this.v);
        this.r = new c(getContext());
        this.r.setLayoutParams(new LayoutParams(-1, -2));
        a aVar = new a(this.r, i, this.p, this.s);
        this.r.setAdapter(new d(this.p, this.b, this.k, this.s, this.j, a(), i == 1 ? this.d.a() : this.d.b(), this.m, min, i2, i3, i, aVar));
        if (i == 1) {
            new ae().a(this.r);
            aVar.a(new d.a(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public final void a(int i) {
                    if (this.a.q != null) {
                        this.a.q.a(i);
                    }
                }
            });
            this.q = new d(getContext(), this.d.a(), this.p.size());
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, g);
            layoutParams.setMargins(0, i, 0, 0);
            this.q.setLayoutParams(layoutParams);
        }
        this.l.addView(this.r);
        if (this.q != null) {
            this.l.addView(this.q);
        }
        a(this.l, false, i);
    }
}
