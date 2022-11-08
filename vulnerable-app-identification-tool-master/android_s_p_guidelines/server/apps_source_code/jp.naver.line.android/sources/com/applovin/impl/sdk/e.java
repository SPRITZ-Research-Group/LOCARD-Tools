package com.applovin.impl.sdk;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class e {
    private final String a;
    private final String b;
    private final String c;
    private final long d;
    private final Map<String, Long> e;

    private e(String str, String str2, String str3) {
        this.e = new HashMap();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = System.currentTimeMillis();
    }

    /* synthetic */ e(String str, String str2, String str3, d dVar) {
        this(str, str2, str3);
    }

    private JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pk", this.a);
        jSONObject.put("ts", this.d);
        if (!TextUtils.isEmpty(this.b)) {
            jSONObject.put("sk1", this.b);
        }
        if (!TextUtils.isEmpty(this.c)) {
            jSONObject.put("sk2", this.c);
        }
        for (Entry entry : this.e.entrySet()) {
            jSONObject.put((String) entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    private String b() throws JSONException {
        return a().toString();
    }

    void a(String str, long j) {
        this.e.put(str, Long.valueOf(j));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[AdEventStats pk: ");
        stringBuilder.append(this.a);
        stringBuilder.append(", size: ");
        stringBuilder.append(this.e.size());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
