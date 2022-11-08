package com.facebook.ads.internal.view.c.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.b;
import com.facebook.ads.internal.view.f.c.d;
import com.facebook.ads.internal.view.f.c.d.a;
import com.facebook.ads.internal.view.f.c.f;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.c.l;
import com.facebook.ads.internal.view.t;
import java.util.Map;

public class e extends FrameLayout {
    private static final int a = ((int) (16.0f * u.b));
    private final c b;
    private t c;
    private f d;
    private l e;
    private g f;
    @Nullable
    private b g;

    public e(Context context, c cVar) {
        super(context);
        this.b = cVar;
        this.c = new t(context);
        this.c.setLayoutParams(new LayoutParams(-1, -2));
        u.a(this.c);
        addView(this.c);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.e.performClick();
            }
        });
        this.c.b();
        this.f = new g(context);
        this.c.a(this.f);
        this.d = new f(context);
        this.c.a(new com.facebook.ads.internal.view.f.c.b(context));
        this.c.a(this.d);
        this.e = new l(context, true);
        this.c.a(this.e);
        this.c.a(new d(this.e, a.FADE_OUT_ON_PLAY, true, true));
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(a, a, a, a);
        this.d.setLayoutParams(layoutParams);
        this.c.addView(this.d);
    }

    public final void a() {
        this.c.a(true);
    }

    public final void a(com.facebook.ads.internal.j.f fVar) {
        this.c.a().a(fVar);
    }

    public final void a(c cVar, String str, Map<String, String> map) {
        d();
        this.g = new b(getContext(), cVar, this.c, str, (Map) map);
    }

    public final void a(com.facebook.ads.internal.view.f.a.a aVar) {
        this.c.a(aVar);
    }

    public final float b() {
        return this.c.l();
    }

    public final boolean c() {
        return this.c.g() == com.facebook.ads.internal.view.f.d.d.STARTED;
    }

    public final void d() {
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
    }

    public void setPlaceholderUrl(String str) {
        this.f.setImage(str);
    }

    public void setVideoURI(String str) {
        this.c.setVideoURI(str);
    }

    public void setVolume(float f) {
        this.c.setVolume(f);
        this.d.a();
    }
}
