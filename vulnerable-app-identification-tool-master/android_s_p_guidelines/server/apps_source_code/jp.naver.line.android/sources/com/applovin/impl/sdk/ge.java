package com.applovin.impl.sdk;

import android.webkit.WebSettings.PluginState;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import org.json.JSONObject;

public final class ge {
    private AppLovinSdk a;
    private JSONObject b;

    ge(JSONObject jSONObject, AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk;
        this.b = jSONObject;
    }

    public final Integer a() {
        String a = bu.a(this.b, "mixed_content_mode", null, this.a);
        if (AppLovinSdkUtils.isValidString(a)) {
            int i;
            if ("always_allow".equalsIgnoreCase(a)) {
                i = 0;
            } else if ("never_allow".equalsIgnoreCase(a)) {
                i = 1;
            } else if ("compatibility_mode".equalsIgnoreCase(a)) {
                i = 2;
            }
            return Integer.valueOf(i);
        }
        return null;
    }

    public final PluginState b() {
        String a = bu.a(this.b, "plugin_state", null, this.a);
        if (AppLovinSdkUtils.isValidString(a)) {
            if ("on".equalsIgnoreCase(a)) {
                return PluginState.ON;
            }
            if ("on_demand".equalsIgnoreCase(a)) {
                return PluginState.ON_DEMAND;
            }
            if ("off".equalsIgnoreCase(a)) {
                return PluginState.OFF;
            }
        }
        return null;
    }

    public final Boolean c() {
        return bu.a(this.b, "allow_file_access", null, this.a);
    }

    public final Boolean d() {
        return bu.a(this.b, "load_with_overview_mode", null, this.a);
    }

    public final Boolean e() {
        return bu.a(this.b, "use_wide_view_port", null, this.a);
    }

    public final Boolean f() {
        return bu.a(this.b, "allow_content_access", null, this.a);
    }

    public final Boolean g() {
        return bu.a(this.b, "use_built_in_zoom_controls", null, this.a);
    }

    public final Boolean h() {
        return bu.a(this.b, "display_zoom_controls", null, this.a);
    }

    public final Boolean i() {
        return bu.a(this.b, "save_form_data", null, this.a);
    }

    public final Boolean j() {
        return bu.a(this.b, "geolocation_enabled", null, this.a);
    }

    public final Boolean k() {
        return bu.a(this.b, "need_initial_focus", null, this.a);
    }

    public final Boolean l() {
        return bu.a(this.b, "allow_file_access_from_file_urls", null, this.a);
    }

    public final Boolean m() {
        return bu.a(this.b, "allow_universal_access_from_file_urls", null, this.a);
    }

    public final Boolean n() {
        return bu.a(this.b, "offscreen_pre_raster", null, this.a);
    }
}
