package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.i;
import com.facebook.ads.internal.n.j;
import com.facebook.ads.internal.n.l;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.w;
import com.facebook.ads.internal.r.b;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class v extends ai implements a {
    private static final String a = v.class.getSimpleName();
    private int A;
    private String B;
    private String C;
    private l D;
    private int E = 200;
    private String F;
    private f G;
    private String H;
    private String I;
    private j J;
    private List<e> K;
    private int L = -1;
    private int M;
    private String N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private long T = 0;
    private com.facebook.ads.internal.j.a.a U = null;
    @Nullable
    private c V;
    private e.c W;
    private Context b;
    private aj c;
    private Uri d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private f p;
    private f q;
    private i r;
    private String s;
    private d t;
    private Collection<String> u;
    private boolean v;
    private boolean w;
    private int x;
    private int y;
    private int z;

    private void E() {
        if (!this.S) {
            if (this.V != null) {
                this.V.a(this.s);
            }
            this.S = true;
        }
    }

    private void a(JSONObject jSONObject, String str) {
        JSONArray jSONArray = null;
        if (this.P) {
            throw new IllegalStateException("Adapter already loaded data");
        } else if (jSONObject != null) {
            i iVar;
            j jVar;
            l lVar;
            v vVar;
            int length;
            List arrayList;
            int i;
            ai vVar2;
            Context context;
            JSONObject jSONObject2;
            c cVar;
            boolean z;
            com.facebook.ads.internal.q.a.d.a(this.b, "Audience Network Loaded");
            this.N = str;
            Object a = k.a(jSONObject, "fbad_command");
            this.d = TextUtils.isEmpty(a) ? null : Uri.parse(a);
            this.e = k.a(jSONObject, "advertiser_name");
            this.f = k.a(jSONObject, "title");
            this.g = k.a(jSONObject, "subtitle");
            this.h = k.a(jSONObject, "headline");
            this.i = k.a(jSONObject, "body");
            this.j = k.a(jSONObject, "call_to_action");
            if (TextUtils.isEmpty(this.j)) {
                this.j = null;
            }
            this.k = k.a(jSONObject, "social_context");
            this.l = k.a(jSONObject, "link_description");
            this.m = k.a(jSONObject, "sponsored_translation");
            this.n = k.a(jSONObject, "ad_translation");
            this.o = k.a(jSONObject, "promoted_translation");
            this.p = f.a(jSONObject.optJSONObject("icon"));
            this.q = f.a(jSONObject.optJSONObject("image"));
            JSONObject optJSONObject = jSONObject.optJSONObject("star_rating");
            if (optJSONObject == null) {
                iVar = null;
            } else {
                double optDouble = optJSONObject.optDouble(PropertiesEntry.COLUMN_NAME_VALUE, 0.0d);
                double optDouble2 = optJSONObject.optDouble("scale", 0.0d);
                iVar = (optDouble == 0.0d || optDouble2 == 0.0d) ? null : new i(optDouble, optDouble2);
            }
            this.r = iVar;
            this.s = k.a(jSONObject, "used_report_url");
            this.v = jSONObject.optBoolean("enable_view_log");
            this.w = jSONObject.optBoolean("enable_snapshot_log");
            this.x = jSONObject.optInt("snapshot_log_delay_second", 4);
            this.y = jSONObject.optInt("snapshot_compress_quality", 0);
            this.z = jSONObject.optInt("viewability_check_initial_delay", 0);
            this.A = jSONObject.optInt("viewability_check_interval", Constants.ONE_SECOND);
            JSONObject optJSONObject2 = jSONObject.optJSONObject("ad_choices_icon");
            JSONObject optJSONObject3 = jSONObject.optJSONObject("native_ui_config");
            if (optJSONObject3 != null) {
                try {
                    if (optJSONObject3.length() != 0) {
                        jVar = new j(optJSONObject3);
                        this.J = jVar;
                        if (optJSONObject2 != null) {
                            this.G = f.a(optJSONObject2);
                        }
                        this.H = k.a(jSONObject, "ad_choices_link_url");
                        this.I = k.a(jSONObject, "request_id");
                        this.t = d.a(jSONObject.optString("invalidation_behavior"));
                        jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
                        this.u = com.facebook.ads.internal.a.e.a(jSONArray);
                        this.B = k.a(jSONObject, "video_url");
                        this.C = k.a(jSONObject, "video_mpd");
                        if (!jSONObject.has("video_autoplay_enabled")) {
                            lVar = l.DEFAULT;
                            vVar = this;
                        } else if (jSONObject.optBoolean("video_autoplay_enabled")) {
                            lVar = l.OFF;
                            vVar = this;
                        } else {
                            lVar = l.ON;
                            vVar = this;
                        }
                        vVar.D = lVar;
                        this.F = k.a(jSONObject, "video_report_url");
                        jSONArray = jSONObject.optJSONArray("carousel");
                        if (jSONArray != null && jSONArray.length() > 0) {
                            length = jSONArray.length();
                            arrayList = new ArrayList(length);
                            for (i = 0; i < length; i++) {
                                vVar2 = new v();
                                context = this.b;
                                jSONObject2 = jSONArray.getJSONObject(i);
                                cVar = this.V;
                                vVar2.O = true;
                                vVar2.b = context;
                                vVar2.V = cVar;
                                vVar2.L = i;
                                vVar2.M = length;
                                vVar2.a(jSONObject2, str);
                                arrayList.add(new e(this.b, vVar2, this.W));
                            }
                            this.K = arrayList;
                        }
                        this.P = true;
                        z = ((this.O && !TextUtils.isEmpty(this.e)) || (!TextUtils.isEmpty(this.f) && this.O)) && ((this.p != null || this.O) && (this.q != null || d() == b.NATIVE_BANNER));
                        this.Q = z;
                    }
                } catch (JSONException e) {
                    this.J = null;
                }
            }
            jVar = null;
            this.J = jVar;
            if (optJSONObject2 != null) {
                this.G = f.a(optJSONObject2);
            }
            this.H = k.a(jSONObject, "ad_choices_link_url");
            this.I = k.a(jSONObject, "request_id");
            this.t = d.a(jSONObject.optString("invalidation_behavior"));
            try {
                jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.u = com.facebook.ads.internal.a.e.a(jSONArray);
            this.B = k.a(jSONObject, "video_url");
            this.C = k.a(jSONObject, "video_mpd");
            if (!jSONObject.has("video_autoplay_enabled")) {
                lVar = l.DEFAULT;
                vVar = this;
            } else if (jSONObject.optBoolean("video_autoplay_enabled")) {
                lVar = l.OFF;
                vVar = this;
            } else {
                lVar = l.ON;
                vVar = this;
            }
            vVar.D = lVar;
            this.F = k.a(jSONObject, "video_report_url");
            try {
                jSONArray = jSONObject.optJSONArray("carousel");
                length = jSONArray.length();
                arrayList = new ArrayList(length);
                for (i = 0; i < length; i++) {
                    vVar2 = new v();
                    context = this.b;
                    jSONObject2 = jSONArray.getJSONObject(i);
                    cVar = this.V;
                    vVar2.O = true;
                    vVar2.b = context;
                    vVar2.V = cVar;
                    vVar2.L = i;
                    vVar2.M = length;
                    vVar2.a(jSONObject2, str);
                    arrayList.add(new e(this.b, vVar2, this.W));
                }
                this.K = arrayList;
            } catch (JSONException e3) {
            }
            this.P = true;
            if (this.O) {
            }
            this.Q = z;
        }
    }

    public final List<e> A() {
        return !g_() ? null : this.K;
    }

    public final int B() {
        return this.L;
    }

    public final int C() {
        return this.M;
    }

    public final boolean D() {
        return this.O;
    }

    public final d a() {
        return this.t;
    }

    public final void a(int i) {
        if (g_() && i == 0 && this.T > 0 && this.U != null) {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.T, this.U, this.I));
            this.T = 0;
            this.U = null;
        }
    }

    public final void a(Context context, aj ajVar, c cVar, Map<String, Object> map, e.c cVar2) {
        this.b = context;
        this.c = ajVar;
        this.V = cVar;
        this.W = cVar2;
        JSONObject jSONObject = (JSONObject) map.get("data");
        com.facebook.ads.internal.h.d dVar = (com.facebook.ads.internal.h.d) map.get("definition");
        this.E = dVar != null ? dVar.k() : 200;
        a(jSONObject, k.a(jSONObject, "ct"));
        if (com.facebook.ads.internal.a.e.a(context, this, cVar)) {
            ajVar.a(this, new com.facebook.ads.internal.r.c(com.facebook.ads.internal.r.a.NO_FILL, "No Fill"));
            return;
        }
        ajVar.a(this);
        com.facebook.ads.internal.j.a.a = this.I;
    }

    public final void a(View view, List<View> list) {
    }

    public final void a(aj ajVar) {
        this.c = ajVar;
    }

    public final void a(Map<String, String> map) {
        if (g_() && !this.R) {
            if (this.c != null) {
                this.c.a();
            }
            final Map hashMap = new HashMap();
            if (map != null) {
                hashMap.putAll(map);
            }
            if (this.O) {
                hashMap.put("cardind", String.valueOf(this.L));
                hashMap.put("cardcnt", String.valueOf(this.M));
            }
            if (!(TextUtils.isEmpty(this.N) || this.V == null)) {
                this.V.a(this.N, hashMap);
            }
            if (g() || f()) {
                try {
                    final Map hashMap2 = new HashMap();
                    if (map.containsKey("view")) {
                        hashMap2.put("view", map.get("view"));
                    }
                    if (map.containsKey("snapshot")) {
                        hashMap2.put("snapshot", map.get("snapshot"));
                    }
                    new Handler().postDelayed(new Runnable(this) {
                        final /* synthetic */ v c;

                        public final void run() {
                            if (!TextUtils.isEmpty(this.c.N)) {
                                Map hashMap = new HashMap();
                                hashMap.putAll(hashMap);
                                hashMap.putAll(hashMap2);
                                if (this.c.V != null) {
                                    this.c.V.e(this.c.N, hashMap);
                                }
                            }
                        }
                    }, (long) (this.x * Constants.ONE_SECOND));
                } catch (Exception e) {
                }
            }
            this.R = true;
        }
    }

    public final Collection<String> b() {
        return this.u;
    }

    public final void b(Map<String, String> map) {
        if (!g_()) {
            return;
        }
        if (!com.facebook.ads.internal.l.a.c(this.b) || !w.a((Map) map)) {
            Map hashMap = new HashMap();
            hashMap.putAll(map);
            com.facebook.ads.internal.q.a.d.a(this.b, "Click logged");
            if (this.c != null) {
                this.c.b();
            }
            if (this.O) {
                hashMap.put("cardind", String.valueOf(this.L));
                hashMap.put("cardcnt", String.valueOf(this.M));
            }
            com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(this.b, this.V, this.N, this.d, hashMap);
            if (a != null) {
                try {
                    this.T = System.currentTimeMillis();
                    this.U = a.a();
                    a.b();
                } catch (Exception e) {
                }
            }
        }
    }

    public final String c() {
        return this.N;
    }

    public final void e() {
    }

    public final boolean f() {
        return g_() && this.w;
    }

    public final void f_() {
        if (this.K != null && !this.K.isEmpty()) {
            for (e u : this.K) {
                u.u();
            }
        }
    }

    public final boolean g() {
        return g_() && this.v;
    }

    public final boolean g_() {
        return this.P && this.Q;
    }

    public final boolean h() {
        return true;
    }

    public final int i() {
        return (this.y < 0 || this.y > 100) ? 0 : this.y;
    }

    public final int j() {
        return this.z;
    }

    public final int k() {
        return this.A;
    }

    public final f l() {
        return !g_() ? null : this.p;
    }

    public final f m() {
        return !g_() ? null : this.q;
    }

    public final String n() {
        if (!g_()) {
            return null;
        }
        E();
        return this.e;
    }

    public final String o() {
        if (!g_()) {
            return null;
        }
        E();
        String str = this.i;
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, " ", true);
            if (str.length() > 90 && (str.length() > 93 || !str.endsWith("..."))) {
                int i = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    int length = stringTokenizer.nextToken().length();
                    if (i + length < 90) {
                        i += length;
                    }
                }
                return i == 0 ? str.substring(0, 90) + "..." : str.substring(0, i) + "...";
            }
        }
        return str;
    }

    public final String p() {
        if (!g_()) {
            return null;
        }
        E();
        return this.j;
    }

    public final String q() {
        if (!g_()) {
            return null;
        }
        E();
        return this.k;
    }

    public final String r() {
        if (!g_()) {
            return null;
        }
        E();
        return this.m;
    }

    public final f s() {
        return !g_() ? null : this.G;
    }

    public final String t() {
        return !g_() ? null : this.H;
    }

    public final String u() {
        return !g_() ? null : "AdChoices";
    }

    public final String v() {
        return !g_() ? null : this.B;
    }

    public final String w() {
        return !g_() ? null : this.C;
    }

    public final l x() {
        return !g_() ? l.DEFAULT : this.D;
    }

    public final int y() {
        return this.E;
    }

    public final String z() {
        return this.F;
    }
}
