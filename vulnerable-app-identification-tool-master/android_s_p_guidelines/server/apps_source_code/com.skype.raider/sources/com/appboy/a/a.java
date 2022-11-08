package com.appboy.a;

import a.a.bd;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.appboy.b.h;
import com.appboy.f.c;
import com.appboy.f.g;
import com.appboy.f.i;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

public class a extends b {
    private static final String c = c.a(a.class);
    private final Context d;

    enum a {
        SMALL,
        LARGE
    }

    public a(Context context) {
        super(context);
        this.d = context;
    }

    public final String a() {
        if ("STAGING".equals(a("com_appboy_server_target", "PROD").toUpperCase(Locale.US))) {
            return "https://sondheim.appboy.com/api/v3/";
        }
        return "https://dev.appboy.com/api/v3/";
    }

    private String a(Locale localeToMatch) {
        Throwable e;
        String[] split;
        String toLowerCase;
        boolean equals;
        if (localeToMatch == null) {
            c.b(c, "Passed in a null locale to match.");
            return null;
        }
        String[] strArr;
        int i;
        if (this.b.a("com_appboy_locale_api_key_map")) {
            try {
                JSONArray jSONArray = new JSONArray(this.b.a("com_appboy_locale_api_key_map", null));
                strArr = new String[jSONArray.length()];
                i = 0;
                while (i < jSONArray.length()) {
                    try {
                        strArr[i] = jSONArray.getString(i);
                        i++;
                    } catch (JSONException e2) {
                        e = e2;
                    }
                }
            } catch (JSONException e3) {
                e = e3;
                strArr = null;
                c.d(c, "Caught exception creating locale to api key mapping from override cache", e);
                if (strArr != null) {
                    for (String str : strArr) {
                        if (i.a(str, ",") == 1) {
                            split = str.split(",");
                            if (split.length != 2) {
                                continue;
                            } else {
                                toLowerCase = split[0].trim().toLowerCase(Locale.US);
                                equals = toLowerCase.equals(localeToMatch.toString().toLowerCase(Locale.US));
                                if (toLowerCase.equals(localeToMatch.getCountry().toLowerCase(Locale.US))) {
                                }
                                return split[1].trim();
                            }
                        }
                    }
                    return null;
                }
                c.b(c, "Locale to api key mappings not present in XML.");
                return null;
            }
        }
        strArr = a("com_appboy_locale_api_key_map");
        if (strArr != null) {
            c.b(c, "Locale to api key mappings not present in XML.");
            return null;
        }
        for (String str2 : strArr) {
            if (i.a(str2, ",") == 1) {
                split = str2.split(",");
                if (split.length != 2) {
                    toLowerCase = split[0].trim().toLowerCase(Locale.US);
                    equals = toLowerCase.equals(localeToMatch.toString().toLowerCase(Locale.US));
                    if (toLowerCase.equals(localeToMatch.getCountry().toLowerCase(Locale.US)) || equals) {
                        return split[1].trim();
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    public final bd b() {
        bd bdVar = (bd) this.a.get("com_appboy_api_key");
        if (bdVar == null) {
            String a = this.b.a("com_appboy_api_key", null);
            if (a != null) {
                c.d(c, "Found an override api key. Using it to configure Appboy.");
            } else {
                a = a(Locale.getDefault());
                if (a != null) {
                    c.d(c, "Found a locale that matches the current locale in appboy.xml. Using the associated api key");
                } else {
                    a = b("com_appboy_api_key", null);
                }
            }
            if (a != null) {
                bdVar = new bd(a);
                this.a.put("com_appboy_api_key", bdVar);
            }
        }
        if (bdVar != null) {
            return bdVar;
        }
        c.g(c, "****************************************************");
        c.g(c, "**                                                **");
        c.g(c, "**                 !! WARNING !!                  **");
        c.g(c, "**                                                **");
        c.g(c, "**     No API key set in res/values/appboy.xml    **");
        c.g(c, "** No cached API Key found from Appboy.configure  **");
        c.g(c, "**          Braze functionality disabled          **");
        c.g(c, "**                                                **");
        c.g(c, "****************************************************");
        throw new RuntimeException("Unable to read the Braze API key from the res/values/appboy.xml file. See log for more details.");
    }

    public final boolean c() {
        return a("com_appboy_push_gcm_messaging_registration_enabled", false);
    }

    public final boolean d() {
        return a("com_appboy_push_adm_messaging_registration_enabled", false);
    }

    public final boolean e() {
        return !a("com_appboy_disable_location_collection", false);
    }

    public final boolean f() {
        return a("com_appboy_enable_background_location_collection", false);
    }

    public final long g() {
        return 1000 * ((long) a("com_appboy_location_update_time_interval", -1));
    }

    public final float h() {
        return (float) a("com_appboy_location_update_distance", -1);
    }

    public final int i() {
        return a(a.SMALL);
    }

    public final int j() {
        return a(a.LARGE);
    }

    public final long k() {
        return (long) a("com_appboy_trigger_action_minimum_time_interval_seconds", 30);
    }

    public final int l() {
        return a("com_appboy_session_timeout", 10);
    }

    public final int m() {
        if (this.a.containsKey("version_code")) {
            return ((Integer) this.a.get("version_code")).intValue();
        }
        int i;
        try {
            i = this.d.getPackageManager().getPackageInfo(g.a(this.d), 0).versionCode;
        } catch (Throwable e) {
            c.d(c, "Unable to read the version code.", e);
            i = -1;
        }
        this.a.put("version_code", Integer.valueOf(i));
        return i;
    }

    public final String n() {
        return a("com_appboy_push_gcm_sender_id", null);
    }

    public final String o() {
        return a("com_appboy_custom_endpoint", null);
    }

    public final boolean p() {
        return a("com_appboy_handle_push_deep_links_automatically", false);
    }

    public final boolean q() {
        return a("com_appboy_newsfeed_unread_visual_indicator_on", true);
    }

    public final boolean r() {
        return a("com_appboy_enable_fresco_library_use", false);
    }

    public final String s() {
        return a("com_appboy_default_notification_channel_name", "General");
    }

    public final String t() {
        return a("com_appboy_default_notification_channel_description", "");
    }

    public final int u() {
        int i = 0;
        if (this.a.containsKey("application_icon")) {
            return ((Integer) this.a.get("application_icon")).intValue();
        }
        String packageName = this.d.getPackageName();
        try {
            i = this.d.getPackageManager().getApplicationInfo(packageName, 0).icon;
        } catch (NameNotFoundException e) {
            c.g(c, "Cannot find package named " + packageName);
            try {
                i = this.d.getPackageManager().getApplicationInfo(this.d.getPackageName(), 0).icon;
            } catch (NameNotFoundException e2) {
                c.g(c, "Cannot find package named " + packageName);
            }
        }
        this.a.put("application_icon", Integer.valueOf(i));
        return i;
    }

    @TargetApi(21)
    public final int v() {
        return a("com_appboy_default_notification_accent_color", 0);
    }

    public final h w() {
        h hVar = null;
        String a = a("com_appboy_sdk_flavor", (String) hVar);
        if (i.c(a)) {
            return hVar;
        }
        try {
            return h.valueOf(a.toUpperCase(Locale.US));
        } catch (Throwable e) {
            c.d(c, "Exception while parsing stored SDK flavor. Returning null.", e);
            return hVar;
        }
    }

    public final boolean x() {
        return a("com_appboy_push_deep_link_back_stack_activity_enabled", true);
    }

    public final String y() {
        return a("com_appboy_push_deep_link_back_stack_activity_class_name", "");
    }

    public final boolean z() {
        return a("com_appboy_session_start_based_timeout_enabled", false);
    }

    public final boolean A() {
        return a("com_appboy_firebase_cloud_messaging_registration_enabled", false);
    }

    public final String B() {
        return a("com_appboy_firebase_cloud_messaging_sender_id", null);
    }

    private int a(a aVar) {
        String str = aVar.equals(a.LARGE) ? "com_appboy_push_large_notification_icon" : "com_appboy_push_small_notification_icon";
        if (this.a.containsKey(str)) {
            return ((Integer) this.a.get(str)).intValue();
        }
        int identifier;
        if (this.b.a(str)) {
            String a = this.b.a(str, "");
            identifier = this.d.getResources().getIdentifier(a, "drawable", g.a(this.d));
            this.a.put(str, Integer.valueOf(identifier));
            c.b(c, "Using runtime override value for key: " + str + " and value: " + a);
            return identifier;
        }
        identifier = this.d.getResources().getIdentifier(str, "drawable", g.a(this.d));
        this.a.put(str, Integer.valueOf(identifier));
        return identifier;
    }
}
