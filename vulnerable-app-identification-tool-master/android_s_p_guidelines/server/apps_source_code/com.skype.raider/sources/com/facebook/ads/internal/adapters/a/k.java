package com.facebook.ads.internal.adapters.a;

import android.text.TextUtils;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.ads.internal.adapters.a.i.a;
import com.facebook.ads.internal.j.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.hockeyapp.android.j;
import org.json.JSONArray;
import org.json.JSONObject;

public final class k implements Serializable {
    private final i a;
    private final c b;
    private final e c;
    private final a d;
    private final b e;
    private final f f;
    private final String g;
    private int h = 200;

    private k(i iVar, c cVar, e eVar, a aVar, b bVar, f fVar, String str) {
        this.a = iVar;
        this.b = cVar;
        this.c = eVar;
        this.d = aVar;
        this.e = bVar;
        this.f = fVar;
        this.g = str;
    }

    public static k a(JSONObject jSONObject) {
        j jVar = null;
        a c = new a().a(jSONObject.optString("advertiser_name")).b(jSONObject.optJSONObject("icon") != null ? jSONObject.optJSONObject("icon").optString(j.FRAGMENT_URL) : "").c(jSONObject.optString("ad_choices_link_url"));
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        i a = c.d(optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored")).a();
        c a2 = new c.a().a(jSONObject.optString("title")).b(jSONObject.optString("subtitle")).c(jSONObject.optString("body")).a();
        e eVar = new e(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        JSONObject optJSONObject2 = jSONObject.optJSONObject("layout");
        a aVar = new a(d.a(optJSONObject2 != null ? optJSONObject2.optJSONObject("portrait") : null), d.a(optJSONObject2 != null ? optJSONObject2.optJSONObject("landscape") : null));
        optJSONObject = jSONObject.optJSONObject("playable_data");
        if (optJSONObject != null) {
            String optString = optJSONObject.optString(ReactVideoViewManager.PROP_SRC_URI);
            int optInt = jSONObject.optInt("skippable_seconds", 0);
            optJSONObject = optJSONObject.optJSONObject("generic_text");
            jVar = new j(optString, optInt, optJSONObject == null ? "Rewarded Play" : optJSONObject.optString("rewarded_play_text", "Rewarded Play"));
        }
        return new k(a, a2, eVar, aVar, new a().a(jSONObject.optString("video_url")).b(jSONObject.optJSONObject("image") != null ? jSONObject.optJSONObject("image").optString(j.FRAGMENT_URL) : "").a(jSONObject.optInt("skippable_seconds")).b(jSONObject.optInt("video_duration_sec")).a(jVar).a(), new f(c.a(jSONObject.optString("end_card_markup")), jSONObject.optString("activation_command"), a(jSONObject.optJSONArray("end_card_images"))), jSONObject.optString("ct"));
    }

    private static List<String> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList();
        }
        List<String> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            CharSequence optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    public final i a() {
        return this.a;
    }

    public final void a(int i) {
        this.h = i;
    }

    public final void a(String str) {
        this.f.a(str);
    }

    public final c b() {
        return this.b;
    }

    public final void b(String str) {
        this.e.a(str);
    }

    public final e c() {
        return this.c;
    }

    public final a d() {
        return this.d;
    }

    public final b e() {
        return this.e;
    }

    public final f f() {
        return this.f;
    }

    public final String g() {
        return this.g;
    }

    public final int h() {
        return this.h;
    }
}
