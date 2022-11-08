package com.facebook.ads.internal.h;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.hockeyapp.android.j;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a {
    private final String a;
    private final JSONObject b;
    private final Map<e, List<String>> c = new HashMap();

    public a(String str, JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        int i = 0;
        this.a = str;
        this.b = jSONObject;
        if (jSONArray != null && jSONArray.length() != 0) {
            int i2;
            for (Object put : e.values()) {
                this.c.put(put, new LinkedList());
            }
            while (true) {
                i2 = i;
                if (i2 < jSONArray.length()) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        String string = jSONObject2.getString("type");
                        CharSequence string2 = jSONObject2.getString(j.FRAGMENT_URL);
                        e valueOf = e.valueOf(string.toUpperCase(Locale.US));
                        if (!(valueOf == null || TextUtils.isEmpty(string2))) {
                            ((List) this.c.get(valueOf)).add(string2);
                        }
                    } catch (Exception e) {
                    }
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public final String a() {
        return this.a;
    }

    public final List<String> a(e eVar) {
        return (List) this.c.get(eVar);
    }

    public final JSONObject b() {
        return this.b;
    }
}
