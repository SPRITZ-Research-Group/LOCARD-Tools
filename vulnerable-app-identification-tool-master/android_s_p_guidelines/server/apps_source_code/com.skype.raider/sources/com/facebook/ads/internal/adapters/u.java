package com.facebook.ads.internal.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.a.c;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.t;
import com.facebook.ads.internal.view.f.c.d;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.c.j;
import com.facebook.ads.internal.view.f.c.k;
import com.facebook.ads.internal.view.f.c.l;
import com.facebook.ads.internal.view.f.c.n;
import java.util.HashMap;
import org.json.JSONObject;

public class u extends r implements OnTouchListener, a {
    static final /* synthetic */ boolean i = (!u.class.desiredAssertionStatus());
    private static final String j = u.class.getSimpleName();
    private int A = -12286980;
    private boolean B = false;
    @Nullable
    private com.facebook.ads.internal.view.f.a.a C;
    final int f = 64;
    final int g = 64;
    final int h = 16;
    @Nullable
    private a.a k;
    @Nullable
    private Activity l;
    private AudienceNetworkActivity.a m = new AudienceNetworkActivity.a(this) {
        final /* synthetic */ u a;

        {
            this.a = r1;
        }

        public final boolean a() {
            if (this.a.x == null) {
                return false;
            }
            if (!this.a.x.a()) {
                return true;
            }
            if (!(this.a.x.b() == 0 || this.a.b == null)) {
                this.a.b.c();
            }
            if (this.a.b == null) {
                return false;
            }
            this.a.b.j();
            return false;
        }
    };
    private final OnTouchListener n = new OnTouchListener(this) {
        final /* synthetic */ u a;

        {
            this.a = r1;
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (this.a.x == null) {
                    this.a.l.finish();
                } else if (this.a.x.a()) {
                    if (!(this.a.x.b() == 0 || this.a.b == null)) {
                        this.a.b.c();
                    }
                    if (this.a.b != null) {
                        this.a.b.j();
                    }
                    this.a.l.finish();
                }
            }
            return true;
        }
    };
    private s.a o = s.a.UNSPECIFIED;
    private com.facebook.ads.internal.view.c.a p;
    private TextView q;
    private TextView r;
    private ImageView s;
    @Nullable
    private com.facebook.ads.internal.view.f.c.a.a t;
    private n u;
    private ViewGroup v;
    private d w;
    private j x;
    private int y = -1;
    private int z = -10525069;

    private void a(int i) {
        LayoutParams layoutParams;
        View rootView;
        float f = com.facebook.ads.internal.q.a.u.b;
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) (56.0f * f), (int) (56.0f * f));
        layoutParams2.addRule(10);
        layoutParams2.addRule(11);
        int i2 = (int) (16.0f * f);
        this.x.setPadding(i2, i2, i2, i2);
        this.x.setLayoutParams(layoutParams2);
        d.a aVar = n() ? d.a.FADE_OUT_ON_PLAY : d.a.VISIBLE;
        int id = this.b.getId();
        if (i == 1) {
            if ((((double) (this.b.q() > 0 ? ((float) this.b.p()) / ((float) this.b.q()) : -1.0f)) <= 0.9d ? 1 : null) != null || l()) {
                int i3;
                Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
                gradientDrawable.setCornerRadius(0.0f);
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(10);
                this.b.setLayoutParams(layoutParams);
                a(this.b);
                a(this.x);
                if (this.t != null) {
                    a(this.t);
                }
                LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, (int) (((float) (((((this.p != null ? 64 : 0) + 60) + 16) + 16) + 16)) * f));
                layoutParams3.addRule(12);
                View relativeLayout = new RelativeLayout(this.d);
                com.facebook.ads.internal.q.a.u.a(relativeLayout, gradientDrawable);
                relativeLayout.setLayoutParams(layoutParams3);
                if (this.p != null) {
                    i3 = 64;
                } else {
                    i3 = 0;
                }
                relativeLayout.setPadding(i2, 0, i2, (int) (((float) ((i3 + 16) + 16)) * f));
                this.v = relativeLayout;
                if (!this.B) {
                    this.w.a(relativeLayout, aVar);
                }
                a(relativeLayout);
                if (this.u != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f));
                    layoutParams2.addRule(12);
                    layoutParams2.topMargin = (int) (-6.0f * f);
                    this.u.setLayoutParams(layoutParams2);
                    a(this.u);
                }
                if (this.p != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f));
                    layoutParams2.bottomMargin = i2;
                    layoutParams2.leftMargin = i2;
                    layoutParams2.rightMargin = i2;
                    layoutParams2.addRule(1);
                    layoutParams2.addRule(12);
                    this.p.setLayoutParams(layoutParams2);
                    a(this.p);
                }
                if (this.s != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams((int) (60.0f * f), (int) (60.0f * f));
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    this.s.setLayoutParams(layoutParams2);
                    relativeLayout.addView(this.s);
                }
                if (this.q != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams2.bottomMargin = (int) (36.0f * f);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    this.q.setEllipsize(TruncateAt.END);
                    this.q.setGravity(8388611);
                    this.q.setLayoutParams(layoutParams2);
                    this.q.setMaxLines(1);
                    this.q.setPadding((int) (72.0f * f), 0, 0, 0);
                    this.q.setTextColor(-1);
                    this.q.setTextSize(18.0f);
                    relativeLayout.addView(this.q);
                }
                if (this.r != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    layoutParams2.bottomMargin = (int) (4.0f * f);
                    this.r.setEllipsize(TruncateAt.END);
                    this.r.setGravity(8388611);
                    this.r.setLayoutParams(layoutParams2);
                    this.r.setMaxLines(1);
                    this.r.setPadding((int) (72.0f * f), 0, 0, 0);
                    this.r.setTextColor(-1);
                    relativeLayout.addView(this.r);
                }
                com.facebook.ads.internal.q.a.u.a((View) this.b.getParent(), -16777216);
                rootView = this.b.getRootView();
                if (rootView != null) {
                    rootView.setOnTouchListener(this);
                }
            }
        }
        if (i == 1) {
            layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(10);
            this.b.setLayoutParams(layoutParams2);
            a(this.b);
            a(this.x);
            if (this.t != null) {
                a(this.t);
            }
            rootView = new LinearLayout(this.d);
            this.v = rootView;
            rootView.setGravity(112);
            rootView.setOrientation(1);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.leftMargin = (int) (33.0f * f);
            layoutParams.rightMargin = (int) (33.0f * f);
            layoutParams.topMargin = (int) (8.0f * f);
            if (this.p == null) {
                layoutParams.bottomMargin = i2;
            } else {
                layoutParams.bottomMargin = (int) (80.0f * f);
            }
            layoutParams.addRule(3, id);
            rootView.setLayoutParams(layoutParams);
            a(rootView);
            if (this.p != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f));
                layoutParams.bottomMargin = i2;
                layoutParams.leftMargin = (int) (33.0f * f);
                layoutParams.rightMargin = (int) (33.0f * f);
                layoutParams.addRule(1);
                layoutParams.addRule(12);
                this.p.setLayoutParams(layoutParams);
                a(this.p);
            }
            if (this.q != null) {
                layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.weight = 2.0f;
                layoutParams.gravity = 17;
                this.q.setEllipsize(TruncateAt.END);
                this.q.setGravity(17);
                this.q.setLayoutParams(layoutParams);
                this.q.setMaxLines(2);
                this.q.setPadding(0, 0, 0, 0);
                this.q.setTextColor(this.z);
                this.q.setTextSize(24.0f);
                rootView.addView(this.q);
            }
            if (this.s != null) {
                layoutParams = new LinearLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                layoutParams.weight = 0.0f;
                layoutParams.gravity = 17;
                this.s.setLayoutParams(layoutParams);
                rootView.addView(this.s);
            }
            if (this.r != null) {
                layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.weight = 2.0f;
                layoutParams.gravity = 16;
                this.r.setEllipsize(TruncateAt.END);
                this.r.setGravity(16);
                this.r.setLayoutParams(layoutParams);
                this.r.setMaxLines(2);
                this.r.setPadding(0, 0, 0, 0);
                this.r.setTextColor(this.z);
                rootView.addView(this.r);
            }
            if (this.u != null) {
                layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (f * 6.0f));
                layoutParams2.addRule(3, id);
                this.u.setLayoutParams(layoutParams2);
                a(this.u);
            }
            com.facebook.ads.internal.q.a.u.a((View) this.b.getParent(), this.y);
        } else {
            float p = this.b.q() > 0 ? ((float) this.b.p()) / ((float) this.b.q()) : -1.0f;
            Object obj = (((double) p) <= 0.9d || ((double) p) >= 1.1d) ? null : 1;
            if (obj == null || l()) {
                Drawable gradientDrawable2 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
                gradientDrawable2.setCornerRadius(0.0f);
                this.b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                a(this.b);
                a(this.x);
                if (this.t != null) {
                    a(this.t);
                }
                LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, (int) (124.0f * f));
                layoutParams4.addRule(12);
                View relativeLayout2 = new RelativeLayout(this.d);
                com.facebook.ads.internal.q.a.u.a(relativeLayout2, gradientDrawable2);
                relativeLayout2.setLayoutParams(layoutParams4);
                relativeLayout2.setPadding(i2, 0, i2, i2);
                this.v = relativeLayout2;
                if (!this.B) {
                    this.w.a(relativeLayout2, aVar);
                }
                a(relativeLayout2);
                if (this.p != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams((int) (110.0f * f), (int) (56.0f * f));
                    layoutParams2.rightMargin = i2;
                    layoutParams2.bottomMargin = i2;
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(11);
                    this.p.setLayoutParams(layoutParams2);
                    a(this.p);
                }
                if (this.s != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    layoutParams2.bottomMargin = (int) (8.0f * f);
                    this.s.setLayoutParams(layoutParams2);
                    relativeLayout2.addView(this.s);
                }
                if (this.q != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams2.bottomMargin = (int) (48.0f * f);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    this.q.setEllipsize(TruncateAt.END);
                    this.q.setGravity(8388611);
                    this.q.setLayoutParams(layoutParams2);
                    this.q.setMaxLines(1);
                    this.q.setPadding((int) (80.0f * f), 0, this.p != null ? (int) (126.0f * f) : 0, 0);
                    this.q.setTextColor(-1);
                    this.q.setTextSize(24.0f);
                    relativeLayout2.addView(this.q);
                }
                if (this.r != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    this.r.setEllipsize(TruncateAt.END);
                    this.r.setGravity(8388611);
                    this.r.setLayoutParams(layoutParams2);
                    this.r.setMaxLines(2);
                    this.r.setTextColor(-1);
                    this.r.setPadding((int) (80.0f * f), 0, this.p != null ? (int) (126.0f * f) : 0, 0);
                    relativeLayout2.addView(this.r);
                }
                if (this.u != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (f * 6.0f));
                    layoutParams2.addRule(12);
                    this.u.setLayoutParams(layoutParams2);
                    a(this.u);
                }
                com.facebook.ads.internal.q.a.u.a((View) this.b.getParent(), -16777216);
            } else {
                layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
                layoutParams2.addRule(9);
                this.b.setLayoutParams(layoutParams2);
                a(this.b);
                a(this.x);
                if (this.t != null) {
                    a(this.t);
                }
                rootView = new LinearLayout(this.d);
                this.v = rootView;
                rootView.setGravity(112);
                rootView.setOrientation(1);
                layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.leftMargin = i2;
                layoutParams.rightMargin = i2;
                layoutParams.topMargin = (int) (8.0f * f);
                layoutParams.bottomMargin = (int) (80.0f * f);
                layoutParams.addRule(1, id);
                rootView.setLayoutParams(layoutParams);
                a(rootView);
                if (this.u != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f));
                    layoutParams.addRule(5, id);
                    layoutParams.addRule(7, id);
                    layoutParams.addRule(3, id);
                    layoutParams.topMargin = (int) (-6.0f * f);
                    this.u.setLayoutParams(layoutParams);
                    a(this.u);
                }
                if (this.q != null) {
                    layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.weight = 2.0f;
                    layoutParams.gravity = 17;
                    this.q.setEllipsize(TruncateAt.END);
                    this.q.setGravity(17);
                    this.q.setLayoutParams(layoutParams);
                    this.q.setMaxLines(10);
                    this.q.setPadding(0, 0, 0, 0);
                    this.q.setTextColor(this.z);
                    this.q.setTextSize(24.0f);
                    rootView.addView(this.q);
                }
                if (this.s != null) {
                    layoutParams = new LinearLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                    layoutParams.weight = 0.0f;
                    layoutParams.gravity = 17;
                    this.s.setLayoutParams(layoutParams);
                    rootView.addView(this.s);
                }
                if (this.r != null) {
                    layoutParams = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams.weight = 2.0f;
                    layoutParams.gravity = 16;
                    this.r.setEllipsize(TruncateAt.END);
                    this.r.setGravity(17);
                    this.r.setLayoutParams(layoutParams);
                    this.r.setMaxLines(10);
                    this.r.setPadding(0, 0, 0, 0);
                    this.r.setTextColor(this.z);
                    rootView.addView(this.r);
                }
                if (this.p != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (f * 64.0f));
                    layoutParams2.bottomMargin = i2;
                    layoutParams2.leftMargin = i2;
                    layoutParams2.rightMargin = i2;
                    layoutParams2.addRule(1);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(1, id);
                    this.p.setLayoutParams(layoutParams2);
                    a(this.p);
                }
                com.facebook.ads.internal.q.a.u.a((View) this.b.getParent(), this.y);
            }
        }
        rootView = this.b.getRootView();
        if (rootView != null) {
            rootView.setOnTouchListener(this);
        }
    }

    private void a(View view) {
        if (this.k != null) {
            this.k.a(view);
        }
    }

    private static void b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    private boolean l() {
        if (this.b.q() <= 0) {
            return false;
        }
        Rect rect = new Rect();
        this.l.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (rect.width() > rect.height()) {
            return ((float) (rect.width() - ((rect.height() * this.b.p()) / this.b.q()))) - (192.0f * com.facebook.ads.internal.q.a.u.b) < 0.0f;
        } else {
            return ((((float) (rect.height() - ((rect.width() * this.b.q()) / this.b.p()))) - (com.facebook.ads.internal.q.a.u.b * 64.0f)) - (com.facebook.ads.internal.q.a.u.b * 64.0f)) - (40.0f * com.facebook.ads.internal.q.a.u.b) < 0.0f;
        }
    }

    private void m() {
        b(this.b);
        b(this.p);
        b(this.q);
        b(this.r);
        b(this.s);
        b(this.u);
        b(this.v);
        b(this.x);
        if (this.t != null) {
            b(this.t);
        }
    }

    private boolean n() {
        if (i || this.c != null) {
            try {
                return this.c.getJSONObject("video").getBoolean("autoplay");
            } catch (Exception e) {
                String.valueOf(u.class);
                return true;
            }
        }
        throw new AssertionError();
    }

    protected final void a() {
        if (this.c != null) {
            JSONObject jSONObject;
            b gVar;
            String string = this.c.getString("ct");
            String optString = this.c.getJSONObject("context").optString("orientation");
            if (!optString.isEmpty()) {
                this.o = s.a.a(Integer.parseInt(optString));
            }
            if (this.c.has("layout") && !this.c.isNull("layout")) {
                jSONObject = this.c.getJSONObject("layout");
                this.y = (int) jSONObject.optLong("bgColor", (long) this.y);
                this.z = (int) jSONObject.optLong("textColor", (long) this.z);
                this.A = (int) jSONObject.optLong("accentColor", (long) this.A);
                this.B = jSONObject.optBoolean("persistentAdDetails", this.B);
            }
            JSONObject jSONObject2 = this.c.getJSONObject("text");
            this.b.setId(VERSION.SDK_INT >= 17 ? View.generateViewId() : com.facebook.ads.internal.q.a.u.a());
            int b = b();
            Context context = this.d;
            if (b < 0) {
                b = 0;
            }
            this.x = new j(context, b, this.A);
            this.x.setOnTouchListener(this.n);
            this.b.a(this.x);
            if (this.c.has("cta") && !this.c.isNull("cta")) {
                JSONObject jSONObject3 = this.c.getJSONObject("cta");
                this.p = new com.facebook.ads.internal.view.c.a(this.d, jSONObject3.getString(net.hockeyapp.android.j.FRAGMENT_URL), jSONObject3.getString("text"), this.A, this.b, this.a, string);
                c.a(this.d, this.a, string, Uri.parse(jSONObject3.getString(net.hockeyapp.android.j.FRAGMENT_URL)), new HashMap());
            }
            if (this.c.has("icon") && !this.c.isNull("icon")) {
                jSONObject = this.c.getJSONObject("icon");
                this.s = new ImageView(this.d);
                new com.facebook.ads.internal.view.b.d(this.s).a((int) (com.facebook.ads.internal.q.a.u.b * 64.0f), (int) (com.facebook.ads.internal.q.a.u.b * 64.0f)).a(jSONObject.getString(net.hockeyapp.android.j.FRAGMENT_URL));
            }
            if (this.c.has("image") && !this.c.isNull("image")) {
                jSONObject = this.c.getJSONObject("image");
                gVar = new g(this.d);
                this.b.a(gVar);
                gVar.setImage(jSONObject.getString(net.hockeyapp.android.j.FRAGMENT_URL));
            }
            CharSequence optString2 = jSONObject2.optString("title");
            if (!optString2.isEmpty()) {
                this.q = new TextView(this.d);
                this.q.setText(optString2);
                this.q.setTypeface(Typeface.defaultFromStyle(1));
            }
            optString2 = jSONObject2.optString("subtitle");
            if (!optString2.isEmpty()) {
                this.r = new TextView(this.d);
                this.r.setText(optString2);
                this.r.setTextSize(16.0f);
            }
            this.u = new n(this.d);
            this.b.a(this.u);
            Object c = c();
            if (!TextUtils.isEmpty(c)) {
                this.t = new com.facebook.ads.internal.view.f.c.a.a(this.d, "AdChoices", c, new float[]{0.0f, 0.0f, 8.0f, 0.0f}, string);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(10);
                layoutParams.addRule(9);
                this.t.setLayoutParams(layoutParams);
            }
            this.b.a(new k(this.d));
            gVar = new l(this.d);
            this.b.a(gVar);
            d.a aVar = n() ? d.a.FADE_OUT_ON_PLAY : d.a.VISIBLE;
            this.b.a(new d(gVar, aVar));
            this.w = new d(new RelativeLayout(this.d), aVar);
            this.b.a(this.w);
        }
    }

    @TargetApi(17)
    public final void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        this.l = audienceNetworkActivity;
        if (i || this.k != null) {
            audienceNetworkActivity.a(this.m);
            m();
            a(this.l.getResources().getConfiguration().orientation);
            if (n()) {
                f();
                return;
            } else {
                g();
                return;
            }
        }
        throw new AssertionError();
    }

    public final void a(Configuration configuration) {
        m();
        a(configuration.orientation);
    }

    public final void a(Bundle bundle) {
    }

    public final void e() {
        if (!(this.c == null || this.a == null)) {
            Object optString = this.c.optString("ct");
            if (!TextUtils.isEmpty(optString)) {
                this.a.h(optString, new HashMap());
            }
        }
        if (this.b != null) {
            this.b.j();
        }
        s.a((a) this);
    }

    public final void h() {
        if (this.b != null && this.b.g() == com.facebook.ads.internal.view.f.d.d.STARTED) {
            this.C = this.b.h();
            this.b.a(false);
        }
    }

    public final void i() {
        if (this.b != null && this.C != null) {
            this.b.a(this.C);
        }
    }

    public final s.a j() {
        return this.o;
    }

    public final void k() {
        if (this.l != null) {
            this.l.finish();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.b != null) {
            this.b.a().a(new t(view, motionEvent));
        }
        return true;
    }

    public void setListener(a.a aVar) {
        this.k = aVar;
    }
}
