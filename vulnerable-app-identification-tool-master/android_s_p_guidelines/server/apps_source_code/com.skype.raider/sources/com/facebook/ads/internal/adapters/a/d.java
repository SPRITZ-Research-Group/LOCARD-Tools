package com.facebook.ads.internal.adapters.a;

import android.graphics.Color;
import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONObject;

public final class d implements Serializable {
    public static final int a = Color.parseColor("#90949c");
    public static final int b = Color.parseColor("#4b4f56");
    public static final int c = Color.parseColor("#f6f7f9");
    public static final int d = Color.parseColor("#ff4080ff");
    public static final int e = Color.parseColor("#23272F");
    public static final int f = Color.parseColor("#ff4080ff");
    private int g = a;
    private int h = b;
    private int i = -16777216;
    private int j = c;
    private int k = d;
    private int l = -1;
    private int m = -16777216;

    public static d a(JSONObject jSONObject) {
        d dVar = new d();
        if (jSONObject != null) {
            Object optString = jSONObject.optString("accent_color");
            Object optString2 = jSONObject.optString("body_color");
            Object optString3 = jSONObject.optString("subtitle_color");
            Object optString4 = jSONObject.optString("bg_color");
            Object optString5 = jSONObject.optString("cta_color");
            Object optString6 = jSONObject.optString("cta_text_color");
            Object optString7 = jSONObject.optString("title_color");
            if (!TextUtils.isEmpty(optString)) {
                dVar.g = Color.parseColor(optString);
            }
            if (!TextUtils.isEmpty(optString2)) {
                dVar.h = Color.parseColor(optString2);
            }
            if (!TextUtils.isEmpty(optString3)) {
                dVar.i = Color.parseColor(optString3);
            }
            if (!TextUtils.isEmpty(optString4)) {
                dVar.j = Color.parseColor(optString4);
            }
            if (!TextUtils.isEmpty(optString5)) {
                dVar.k = Color.parseColor(optString5);
            }
            if (!TextUtils.isEmpty(optString6)) {
                dVar.l = Color.parseColor(optString6);
            }
            if (!TextUtils.isEmpty(optString7)) {
                dVar.m = Color.parseColor(optString7);
            }
        }
        return dVar;
    }

    public final int a(boolean z) {
        return z ? -1 : this.g;
    }

    public final int b(boolean z) {
        return z ? -1 : this.h;
    }

    public final int c(boolean z) {
        return z ? -1 : this.i;
    }

    public final int d(boolean z) {
        return z ? e : this.j;
    }

    public final int e(boolean z) {
        return z ? -1 : this.k;
    }

    public final int f(boolean z) {
        return z ? f : this.l;
    }

    public final int g(boolean z) {
        return z ? -1 : this.m;
    }
}
