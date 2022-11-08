package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.q;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b.b;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.c.e;
import com.facebook.ads.internal.view.f.c.i;
import com.facebook.ads.internal.view.f.c.k;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.hockeyapp.android.j;
import org.json.JSONException;
import org.json.JSONObject;

public class r extends ab {
    static final /* synthetic */ boolean e = (!r.class.desiredAssertionStatus());
    @Nullable
    protected c a;
    @Nullable
    protected a b;
    @Nullable
    protected JSONObject c;
    @Nullable
    protected Context d;
    private final f<b> f = new f<b>(this) {
        final /* synthetic */ r a;

        {
            this.a = r1;
        }

        public final Class<b> a() {
            return b.class;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.j != null) {
                this.a.j.d();
            }
        }
    };
    private final f<l> g = new f<l>(this) {
        final /* synthetic */ r a;

        {
            this.a = r1;
        }

        public final Class<l> a() {
            return l.class;
        }

        public final /* synthetic */ void a(d dVar) {
            this.a.l = true;
            if (this.a.j != null) {
                this.a.j.a(this.a);
            }
        }
    };
    private final f<com.facebook.ads.internal.view.f.b.d> h = new f<com.facebook.ads.internal.view.f.b.d>(this) {
        final /* synthetic */ r a;

        {
            this.a = r1;
        }

        public final Class<com.facebook.ads.internal.view.f.b.d> a() {
            return com.facebook.ads.internal.view.f.b.d.class;
        }

        public final /* bridge */ /* synthetic */ void a(d dVar) {
            if (this.a.j != null) {
                this.a.j.a(com.facebook.ads.a.e);
            }
        }
    };
    private final f<com.facebook.ads.internal.view.f.b.a> i = new f<com.facebook.ads.internal.view.f.b.a>(this) {
        final /* synthetic */ r a;

        {
            this.a = r1;
        }

        public final Class<com.facebook.ads.internal.view.f.b.a> a() {
            return com.facebook.ads.internal.view.f.b.a.class;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.j != null) {
                this.a.j.b();
            }
        }
    };
    @Nullable
    private com.facebook.ads.a.a j;
    @Nullable
    private String k;
    private boolean l = false;
    @Nullable
    private com.facebook.ads.internal.view.f.c m;
    @Nullable
    private String n;
    private boolean o = false;
    private com.facebook.ads.internal.d.b p;

    private String h() {
        Object obj = "";
        if (!(this.p == null || this.k == null)) {
            obj = this.p.b(this.k);
        }
        return TextUtils.isEmpty(obj) ? this.k : obj;
    }

    private String i() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("countdown") || jSONObject.isNull("countdown")) {
                    return null;
                }
                jSONObject = jSONObject.getJSONObject("countdown");
                return jSONObject.has("format") ? jSONObject.optString("format") : null;
            } catch (Exception e) {
                String.valueOf(r.class);
                return null;
            }
        }
        throw new AssertionError();
    }

    protected void a() {
        if (!e && this.d == null) {
            throw new AssertionError();
        } else if (e || this.c != null) {
            com.facebook.ads.internal.view.f.a.b cVar;
            LayoutParams layoutParams;
            JSONObject optJSONObject = this.c.optJSONObject("text");
            JSONObject jSONObject = optJSONObject == null ? new JSONObject() : optJSONObject;
            this.b.a(new k(this.d));
            com.facebook.ads.internal.view.f.a.b lVar = new com.facebook.ads.internal.view.f.c.l(this.d);
            this.b.a(lVar);
            this.b.a(new com.facebook.ads.internal.view.f.c.d(lVar, com.facebook.ads.internal.view.f.c.d.a.INVSIBLE));
            this.b.a(new com.facebook.ads.internal.view.f.c.b(this.d));
            String i = i();
            if (i != null) {
                cVar = new com.facebook.ads.internal.view.f.c.c(this.d, i);
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                cVar.setLayoutParams(layoutParams);
                cVar.setCountdownTextColor(-1);
                this.b.a(cVar);
            }
            if (this.c.has("cta") && !this.c.isNull("cta")) {
                JSONObject jSONObject2 = this.c.getJSONObject("cta");
                lVar = new e(this.d, jSONObject2.getString(j.FRAGMENT_URL), this.a, this.n, jSONObject2.getString("text"));
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(10);
                layoutParams2.addRule(11);
                lVar.setLayoutParams(layoutParams2);
                this.b.a(lVar);
            }
            Object c = c();
            if (!TextUtils.isEmpty(c)) {
                this.b.a(new com.facebook.ads.internal.view.f.c.a(this.d, c, this.n, new float[]{0.0f, 0.0f, 8.0f, 0.0f}));
            }
            int b = b();
            if (b > 0) {
                cVar = new i(this.d, b, jSONObject.optString("skipAdIn", "Skip Ad in"), jSONObject.optString("skipAd", "Skip Ad"));
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(11);
                cVar.setLayoutParams(layoutParams);
                cVar.setPadding(0, 0, 0, 30);
                this.b.a(cVar);
            }
        } else {
            throw new AssertionError();
        }
    }

    public final void a(Context context, com.facebook.ads.a.a aVar, Map<String, Object> map, c cVar, EnumSet<com.facebook.ads.d> enumSet) {
        try {
            JSONObject jSONObject = (JSONObject) map.get("data");
            com.facebook.ads.internal.h.d dVar = (com.facebook.ads.internal.h.d) map.get("definition");
            int k = dVar == null ? 200 : dVar.k();
            this.d = context;
            this.j = aVar;
            this.a = cVar;
            this.c = jSONObject;
            this.l = false;
            JSONObject jSONObject2 = jSONObject.getJSONObject("video");
            this.n = jSONObject.optString("ct");
            this.b = new a(context);
            this.b.setVideoProgressReportIntervalMs(k);
            a();
            this.b.a().a(this.f, this.g, this.h, this.i);
            List arrayList = new ArrayList();
            arrayList.add(new com.facebook.ads.internal.b.b(this) {
                final /* synthetic */ r a;

                {
                    this.a = r8;
                    com.facebook.ads.internal.b.b bVar = this;
                }

                protected final void a(boolean z) {
                    this.a.g();
                }
            });
            this.m = new com.facebook.ads.internal.view.f.b(context, cVar, this.b, arrayList, this.n);
            this.j.a();
            if (q.a(context) == q.a.MOBILE_INTERNET && jSONObject2.has("videoHDURL") && !jSONObject2.isNull("videoHDURL")) {
                this.k = jSONObject2.getString("videoHDURL");
            } else {
                this.k = jSONObject2.getString("videoURL");
            }
            if (enumSet.contains(com.facebook.ads.d.VIDEO)) {
                this.p = new com.facebook.ads.internal.d.b(context);
                this.p.a(this.k);
                this.p.a(new com.facebook.ads.internal.d.a(this) {
                    final /* synthetic */ r a;

                    {
                        this.a = r1;
                    }

                    public final void a() {
                        this.a.b.setVideoURI(this.a.h());
                    }

                    public final void b() {
                        this.a.b.setVideoURI(this.a.h());
                    }
                });
                return;
            }
            this.b.setVideoURI(h());
        } catch (JSONException e) {
            aVar.a(com.facebook.ads.a.e);
        }
    }

    protected final int b() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("skipButton") || jSONObject.isNull("skipButton")) {
                    return -1;
                }
                jSONObject = jSONObject.getJSONObject("skipButton");
                return jSONObject.has("skippableSeconds") ? jSONObject.getInt("skippableSeconds") : -1;
            } catch (Exception e) {
                String.valueOf(r.class);
                return -1;
            }
        }
        throw new AssertionError();
    }

    @Nullable
    protected final String c() {
        if (e || this.c != null) {
            try {
                JSONObject jSONObject = this.c.getJSONObject("capabilities");
                if (!jSONObject.has("adChoices") || jSONObject.isNull("adChoices")) {
                    return null;
                }
                jSONObject = jSONObject.getJSONObject("adChoices");
                return jSONObject.has(j.FRAGMENT_URL) ? jSONObject.getString(j.FRAGMENT_URL) : null;
            } catch (Exception e) {
                String.valueOf(r.class);
                return null;
            }
        }
        throw new AssertionError();
    }

    public void e() {
        if (this.b != null) {
            this.b.j();
            this.b.s();
        }
        this.j = null;
        this.a = null;
        this.k = null;
        this.l = false;
        this.n = null;
        this.b = null;
        this.m = null;
        this.c = null;
        this.d = null;
        this.o = false;
    }

    public final boolean f() {
        if (!this.l || this.b == null) {
            return false;
        }
        if (this.m.i() > 0) {
            this.b.a(this.m.i());
        }
        this.b.a(com.facebook.ads.internal.view.f.a.a.AUTO_STARTED);
        return true;
    }

    protected final void g() {
        if (this.a != null && !this.o) {
            this.o = true;
            this.a.a(this.n, new HashMap());
            if (this.j != null) {
                this.j.c();
            }
        }
    }
}
