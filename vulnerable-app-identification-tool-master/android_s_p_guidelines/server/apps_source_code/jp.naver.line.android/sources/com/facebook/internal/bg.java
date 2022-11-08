package com.facebook.internal;

import android.os.Bundle;
import com.facebook.ai;
import com.facebook.s;
import java.util.Collection;
import jp.naver.android.npush.common.NPushIntent;
import org.json.JSONException;
import org.json.JSONObject;

public final class bg {
    public static final Collection<String> a = bj.a("service_disabled", "AndroidAuthKillSwitchException");
    public static final Collection<String> b = bj.a("access_denied", "OAuthAccessDeniedException");
    private static final String c = "com.facebook.internal.bg";

    public static final String d() {
        return "v3.0";
    }

    public static final String a() {
        return String.format("m.%s", new Object[]{s.e()});
    }

    public static final String b() {
        return String.format("https://graph.%s", new Object[]{s.e()});
    }

    public static final String c() {
        return String.format("https://graph-video.%s", new Object[]{s.e()});
    }

    public static Bundle a(String str, int i, Bundle bundle) {
        String c = s.c(s.f());
        if (bj.a(c)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("android_key_hash", c);
        bundle2.putString("app_id", s.j());
        bundle2.putInt(NPushIntent.EXTRA_VERSION, i);
        bundle2.putString("display", "touch");
        Bundle bundle3 = new Bundle();
        bundle3.putString("action_id", str);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            JSONObject a = e.a(bundle3);
            JSONObject a2 = e.a(bundle);
            bundle2.putString("bridge_args", a.toString());
            bundle2.putString("method_args", a2.toString());
        } catch (JSONException e) {
            ar.a(ai.DEVELOPER_ERRORS, 6, c, "Error creating Url -- ".concat(String.valueOf(e)));
            bundle2 = null;
        }
        return bundle2;
    }
}
