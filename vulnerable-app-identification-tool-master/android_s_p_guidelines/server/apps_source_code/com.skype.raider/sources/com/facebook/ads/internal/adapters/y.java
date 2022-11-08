package com.facebook.ads.internal.adapters;

import android.content.Intent;
import android.os.Bundle;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.a.e.a;
import com.facebook.ads.internal.j.c;
import com.facebook.ads.internal.q.a.k;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class y implements a {
    private final String a;
    private final String b;
    private final d c;
    private final Collection<String> d;
    private final Map<String, String> e;
    private final String f;
    private final int g;
    private final int h;
    private final int i;
    private final String j;

    private y(String str, String str2, d dVar, Collection<String> collection, Map<String, String> map, String str3, int i, int i2, int i3, String str4) {
        this.a = str;
        this.b = str2;
        this.c = dVar;
        this.d = collection;
        this.e = map;
        this.f = str3;
        this.g = i;
        this.h = i2;
        this.i = i3;
        this.j = str4;
    }

    public static y a(Bundle bundle) {
        return new y(c.a(bundle.getByteArray("markup")), null, d.NONE, null, null, bundle.getString("request_id"), bundle.getInt("viewability_check_initial_delay"), bundle.getInt("viewability_check_interval"), bundle.getInt("skip_after_seconds", 0), bundle.getString("ct"));
    }

    public static y a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        JSONArray jSONArray;
        String optString = jSONObject.optString("markup");
        String optString2 = jSONObject.optString("activation_command");
        String optString3 = jSONObject.optString("request_id");
        String a = k.a(jSONObject, "ct");
        d a2 = d.a(jSONObject.optString("invalidation_behavior"));
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONArray = null;
        }
        Collection a3 = e.a(jSONArray);
        JSONObject optJSONObject = jSONObject.optJSONObject("metadata");
        Map hashMap = new HashMap();
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, optJSONObject.optString(str));
            }
        }
        int i2 = Constants.ONE_SECOND;
        int parseInt = hashMap.containsKey("viewability_check_initial_delay") ? Integer.parseInt((String) hashMap.get("viewability_check_initial_delay")) : 0;
        if (hashMap.containsKey("viewability_check_interval")) {
            i2 = Integer.parseInt((String) hashMap.get("viewability_check_interval"));
        }
        if (hashMap.containsKey("skip_after_seconds")) {
            i = Integer.parseInt((String) hashMap.get("skip_after_seconds"));
        }
        return new y(optString, optString2, a2, a3, hashMap, optString3, parseInt, i2, i, a);
    }

    public static y b(Intent intent) {
        return new y(c.a(intent.getByteArrayExtra("markup")), intent.getStringExtra("activation_command"), d.NONE, null, null, intent.getStringExtra("request_id"), intent.getIntExtra("viewability_check_initial_delay", 0), intent.getIntExtra("viewability_check_interval", Constants.ONE_SECOND), intent.getIntExtra("skipAfterSeconds", 0), intent.getStringExtra("ct"));
    }

    public final d a() {
        return this.c;
    }

    public final void a(Intent intent) {
        intent.putExtra("markup", c.a(this.a));
        intent.putExtra("activation_command", this.b);
        intent.putExtra("request_id", this.f);
        intent.putExtra("viewability_check_initial_delay", this.g);
        intent.putExtra("viewability_check_interval", this.h);
        intent.putExtra("skipAfterSeconds", this.i);
        intent.putExtra("ct", this.j);
    }

    public final Collection<String> b() {
        return this.d;
    }

    public final String c() {
        return this.j;
    }

    public final String d() {
        return this.a;
    }

    public final String e() {
        return this.b;
    }

    public final Map<String, String> f() {
        return this.e;
    }

    public final String g() {
        return this.f;
    }

    public final int h() {
        return this.g;
    }

    public final int i() {
        return this.h;
    }

    public final Bundle j() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("markup", c.a(this.a));
        bundle.putString("request_id", this.f);
        bundle.putInt("viewability_check_initial_delay", this.g);
        bundle.putInt("viewability_check_interval", this.h);
        bundle.putInt("skip_after_seconds", this.i);
        bundle.putString("ct", this.j);
        return bundle;
    }
}
