package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.l;
import com.facebook.ads.internal.q.a.d;
import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdNative;
import com.flurry.android.ads.FlurryAdNativeListener;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class x extends ai implements ac {
    private static volatile boolean a;
    private aj b;
    private FlurryAdNative c;
    private boolean d;
    private String e;
    private String f;
    private String g;
    private f h;
    private f i;
    private f j;

    public final List<e> A() {
        return null;
    }

    public final int B() {
        return 0;
    }

    public final int C() {
        return 0;
    }

    public final n D() {
        return n.YAHOO;
    }

    public final void a(int i) {
    }

    public final void a(final Context context, aj ajVar, c cVar, Map<String, Object> map, e.c cVar2) {
        JSONObject jSONObject = (JSONObject) map.get("data");
        String optString = jSONObject.optString("api_key");
        String optString2 = jSONObject.optString("placement_id");
        synchronized (x.class) {
            if (!a) {
                d.a(context, af.a(n.YAHOO) + " Initializing");
                FlurryAgent.setLogEnabled(true);
                FlurryAgent.init(context, optString);
                a = true;
            }
        }
        d.a(context, af.a(n.YAHOO) + " Loading");
        this.b = ajVar;
        this.c = new FlurryAdNative(context, optString2);
        this.c.setListener(new FlurryAdNativeListener(this) {
            final /* synthetic */ x b;
        });
        this.c.fetchAd();
    }

    public final void a(View view, List<View> list) {
        if (this.c != null) {
            this.c.setTrackingView(view);
        }
    }

    public final void a(aj ajVar) {
        this.b = ajVar;
    }

    public final void a(Map<String, String> map) {
    }

    public final void b(Map<String, String> map) {
    }

    public final String c() {
        return null;
    }

    public final void e() {
        f_();
        this.b = null;
        if (this.c != null) {
            this.c.destroy();
            this.c = null;
        }
    }

    public final boolean f() {
        return false;
    }

    public final void f_() {
        if (this.c != null) {
            this.c.removeTrackingView();
        }
    }

    public final boolean g() {
        return false;
    }

    public final boolean g_() {
        return this.d;
    }

    public final boolean h() {
        return true;
    }

    public final int i() {
        return 0;
    }

    public final int j() {
        return 0;
    }

    public final int k() {
        return 0;
    }

    public final f l() {
        return this.h;
    }

    public final f m() {
        return this.i;
    }

    public final String n() {
        return null;
    }

    public final String o() {
        return this.e;
    }

    public final String p() {
        return this.f;
    }

    public final String q() {
        return this.g;
    }

    public final String r() {
        return null;
    }

    public final f s() {
        return this.j;
    }

    public final String t() {
        return null;
    }

    public final String u() {
        return "Ad";
    }

    public final String v() {
        return null;
    }

    public final String w() {
        return null;
    }

    public final l x() {
        return l.DEFAULT;
    }

    public final int y() {
        return 0;
    }

    public final String z() {
        return null;
    }
}
