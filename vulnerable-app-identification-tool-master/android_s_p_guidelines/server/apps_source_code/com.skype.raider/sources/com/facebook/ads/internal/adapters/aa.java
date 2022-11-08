package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.l;
import com.facebook.ads.internal.q.a.d;
import com.facebook.ads.internal.r.a;
import com.inmobi.ads.InMobiNative;
import com.inmobi.ads.InMobiNative.NativeAdListener;
import com.inmobi.sdk.InMobiSdk;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class aa extends ai implements ac {
    private aj a;
    private InMobiNative b;
    private boolean c;
    private View d;
    private String e;
    private String f;
    private f g;
    private f h;

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
        return n.INMOBI;
    }

    public final void a(int i) {
    }

    public final void a(final Context context, aj ajVar, c cVar, Map<String, Object> map, e.c cVar2) {
        d.a(context, af.a(n.INMOBI) + " Loading");
        JSONObject jSONObject = (JSONObject) map.get("data");
        Object optString = jSONObject.optString("account_id");
        Long valueOf = Long.valueOf(jSONObject.optLong("placement_id"));
        if (TextUtils.isEmpty(optString) || valueOf == null) {
            ajVar.a(this, new com.facebook.ads.internal.r.c(a.MEDIATION_ERROR, "Mediation Error"));
            return;
        }
        this.a = ajVar;
        InMobiSdk.init(context, optString);
        this.b = new InMobiNative(valueOf.longValue(), new NativeAdListener(this) {
            final /* synthetic */ aa b;
        });
        this.b.load();
    }

    public final void a(View view, List<View> list) {
        this.d = view;
        if (g_()) {
            InMobiNative.bind(this.d, this.b);
        }
    }

    public final void a(aj ajVar) {
        this.a = ajVar;
    }

    public final void a(Map<String, String> map) {
        this.a.a();
    }

    public final void b(Map<String, String> map) {
        if (g_()) {
            this.a.b();
            this.b.reportAdClickAndOpenLandingPage(null);
        }
    }

    public final String c() {
        return null;
    }

    public final void e() {
        f_();
        this.b = null;
        this.a = null;
    }

    public final boolean f() {
        return false;
    }

    public final void f_() {
        if (g_()) {
            InMobiNative.unbind(this.d);
        }
        this.d = null;
    }

    public final boolean g() {
        return false;
    }

    public final boolean g_() {
        return this.b != null && this.c;
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
        return this.g;
    }

    public final f m() {
        return this.h;
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
        return null;
    }

    public final String r() {
        return null;
    }

    public final f s() {
        return null;
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
