package com.facebook.ads.internal.adapters.a;

import android.content.Context;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.adapters.a.i.a;
import com.facebook.ads.internal.q.d.b;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.hockeyapp.android.j;
import org.json.JSONArray;
import org.json.JSONObject;

public final class g implements Serializable {
    private final i a;
    private final a b;
    private final List<h> c;
    private final int d;
    private final int e;
    private int f = 200;
    private final String g;
    private final String h;

    private g(i iVar, a aVar, List<h> list, String str, String str2, int i, int i2) {
        this.a = iVar;
        this.b = aVar;
        this.c = list;
        this.g = str;
        this.h = str2;
        this.d = i;
        this.e = i2;
    }

    public static g a(JSONObject jSONObject, Context context) {
        JSONObject jSONObject2 = null;
        a c = new a().a(jSONObject.optString("title")).b(jSONObject.optJSONObject("icon") != null ? jSONObject.optJSONObject("icon").optString(j.FRAGMENT_URL) : "").c(jSONObject.optString("ad_choices_link_url"));
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        i a = c.d(optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored")).a();
        JSONObject optJSONObject2 = jSONObject.optJSONObject("layout");
        d a2 = d.a(optJSONObject2 != null ? optJSONObject2.optJSONObject("portrait") : null);
        if (optJSONObject2 != null) {
            jSONObject2 = optJSONObject2.optJSONObject("landscape");
        }
        a aVar = new a(a2, d.a(jSONObject2));
        int optInt = jSONObject.optInt("viewability_check_initial_delay", 0);
        int optInt2 = jSONObject.optInt("viewability_check_interval", Constants.ONE_SECOND);
        String optString = jSONObject.optString("ct");
        String optString2 = jSONObject.optString("request_id", "");
        JSONArray optJSONArray = jSONObject.optJSONArray("carousel");
        List arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            arrayList.add(h.a(jSONObject));
        } else {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList.add(h.a(optJSONArray.getJSONObject(i)));
                } catch (Exception e) {
                    com.facebook.ads.internal.q.d.a.a(context, "parsing", b.u, e);
                    e.printStackTrace();
                }
            }
        }
        return new g(a, aVar, arrayList, optString, optString2, optInt, optInt2);
    }

    public final i a() {
        return this.a;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final a b() {
        return this.b;
    }

    public final String c() {
        return this.g;
    }

    public final List<h> d() {
        return Collections.unmodifiableList(this.c);
    }

    public final String e() {
        return this.h;
    }

    public final int f() {
        return this.d;
    }

    public final int g() {
        return this.e;
    }

    public final int h() {
        return this.f;
    }
}
