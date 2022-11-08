package com.facebook.ads.internal.adapters.a;

import com.facebook.ads.internal.adapters.a.c.a;
import java.io.Serializable;
import net.hockeyapp.android.j;
import org.json.JSONObject;

public final class h implements Serializable {
    private final c a;
    private final e b;
    private final b c;

    private h(c cVar, e eVar, b bVar) {
        this.a = cVar;
        this.b = eVar;
        this.c = bVar;
    }

    static h a(JSONObject jSONObject) {
        int i = 0;
        c a = new a().a(jSONObject.optString("title")).b(jSONObject.optString("subtitle")).c(jSONObject.optString("body")).a();
        e eVar = new e(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        boolean optBoolean = jSONObject.optBoolean("video_autoplay_enabled");
        a b = new a().a(jSONObject.optString("video_url")).a(optBoolean).b(jSONObject.optBoolean("video_autoplay_with_sound"));
        if (optBoolean) {
            i = jSONObject.optInt("unskippable_seconds", 0);
        }
        a a2 = b.a(i);
        JSONObject optJSONObject = jSONObject.optJSONObject("image");
        if (optJSONObject != null) {
            a2.b(optJSONObject.optString(j.FRAGMENT_URL)).c(optJSONObject.optInt("width")).d(optJSONObject.optInt("height"));
        }
        return new h(a, eVar, a2.a());
    }

    public final c a() {
        return this.a;
    }

    public final e b() {
        return this.b;
    }

    public final b c() {
        return this.c;
    }
}
