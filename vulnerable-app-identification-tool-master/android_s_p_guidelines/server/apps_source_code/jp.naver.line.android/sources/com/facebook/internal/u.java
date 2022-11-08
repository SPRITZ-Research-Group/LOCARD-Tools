package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.GraphRequest;
import com.facebook.s;
import defpackage.anl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class u {
    private static final String a = u.class.getCanonicalName();
    private static final String[] b = new String[]{"supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "gdpv4_chrome_custom_tabs_enabled", "android_dialog_configs", "android_sdk_error_categories", "app_events_session_timeout", "app_events_feature_bitmask", "auto_event_mapping_android", "seamless_login", "smart_login_bookmark_icon_url", "smart_login_menu_icon_url"};
    private static Map<String, s> c = new ConcurrentHashMap();
    private static AtomicBoolean d = new AtomicBoolean(false);
    private static boolean e = false;

    public static void a() {
        final Context f = s.f();
        final String j = s.j();
        boolean compareAndSet = d.compareAndSet(false, true);
        if (!bj.a(j) && !c.containsKey(j) && compareAndSet) {
            final String format = String.format("com.facebook.internal.APP_SETTINGS.%s", new Object[]{j});
            s.d().execute(new Runnable() {
                public final void run() {
                    SharedPreferences sharedPreferences = f.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0);
                    s sVar = null;
                    String string = sharedPreferences.getString(format, null);
                    if (!bj.a(string)) {
                        JSONObject jSONObject;
                        try {
                            jSONObject = new JSONObject(string);
                        } catch (Exception e) {
                            bj.a("FacebookSDK", e);
                            jSONObject = null;
                        }
                        if (jSONObject != null) {
                            sVar = u.b(j, jSONObject);
                        }
                    }
                    JSONObject b = u.c(j);
                    if (b != null) {
                        u.b(j, b);
                        sharedPreferences.edit().putString(format, b.toString()).apply();
                    }
                    if (sVar != null) {
                        String l = sVar.l();
                        if (!(u.e || l == null || l.length() <= 0)) {
                            u.e = true;
                            Log.w(u.a, l);
                        }
                    }
                    anl.a();
                    g.a(i.InAppPurchase.a(), new h(f) {
                        public final boolean a(final int i, final Intent intent) {
                            s.d().execute(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 c;

                                public final void run() {
                                    anl.a(r2, i, intent);
                                }
                            });
                            return true;
                        }
                    });
                    u.d.set(false);
                }
            });
        }
    }

    public static s a(String str) {
        return str != null ? (s) c.get(str) : null;
    }

    public static s a(String str, boolean z) {
        if (!z && c.containsKey(str)) {
            return (s) c.get(str);
        }
        JSONObject c = c(str);
        if (c == null) {
            return null;
        }
        return b(str, c);
    }

    private static s b(String str, JSONObject jSONObject) {
        p a;
        JSONObject jSONObject2 = jSONObject;
        JSONArray optJSONArray = jSONObject2.optJSONArray("android_sdk_error_categories");
        if (optJSONArray == null) {
            a = p.a();
        } else {
            a = p.a(optJSONArray);
        }
        p pVar = a;
        int optInt = jSONObject2.optInt("app_events_feature_bitmask", 0);
        s sVar = new s(jSONObject2.optBoolean("supports_implicit_sdk_logging", false), jSONObject2.optString("gdpv4_nux_content", ""), jSONObject2.optBoolean("gdpv4_nux_enabled", false), jSONObject2.optBoolean("gdpv4_chrome_custom_tabs_enabled", false), jSONObject2.optInt("app_events_session_timeout", 60), bh.a(jSONObject2.optLong("seamless_login")), a(jSONObject2.optJSONObject("android_dialog_configs")), (optInt & 8) != 0, pVar, jSONObject2.optString("smart_login_bookmark_icon_url"), jSONObject2.optString("smart_login_menu_icon_url"), (optInt & 16) != 0, (optInt & 32) != 0, jSONObject2.optJSONArray("auto_event_mapping_android"), jSONObject2.optString("sdk_update_message"));
        c.put(str, sVar);
        return sVar;
    }

    private static JSONObject c(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", new ArrayList(Arrays.asList(b))));
        GraphRequest a = GraphRequest.a(str);
        a.b();
        a.a(bundle);
        return GraphRequest.a(a).b();
    }

    private static Map<String, Map<String, t>> a(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    t a = t.a(optJSONArray.optJSONObject(i));
                    if (a != null) {
                        String a2 = a.a();
                        Map map = (Map) hashMap.get(a2);
                        if (map == null) {
                            map = new HashMap();
                            hashMap.put(a2, map);
                        }
                        map.put(a.b(), a);
                    }
                }
            }
        }
        return hashMap;
    }
}
