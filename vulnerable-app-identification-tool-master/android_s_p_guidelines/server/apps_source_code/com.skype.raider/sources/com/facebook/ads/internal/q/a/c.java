package com.facebook.ads.internal.q.a;

import org.json.JSONArray;

public enum c {
    APP_AD(0),
    LINK_AD(1),
    APP_AD_V2(2),
    LINK_AD_V2(3),
    APP_ENGAGEMENT_AD(4),
    AD_CHOICES(5),
    JS_TRIGGER(6),
    JS_TRIGGER_NO_AUTO_IMP_LOGGING(7),
    VIDEO_AD(8),
    INLINE_VIDEO_AD(9),
    BANNER_TO_INTERSTITIAL(10),
    NATIVE_CLOSE_BUTTON(11),
    UNIFIED_LOGGING(16),
    HTTP_LINKS(17);
    
    public static final c[] o = null;
    private static final String q = null;
    private final int p;

    static {
        o = new c[]{LINK_AD_V2, APP_ENGAGEMENT_AD, AD_CHOICES, JS_TRIGGER_NO_AUTO_IMP_LOGGING, NATIVE_CLOSE_BUTTON, UNIFIED_LOGGING, HTTP_LINKS};
        JSONArray jSONArray = new JSONArray();
        c[] cVarArr = o;
        int length = cVarArr.length;
        int i;
        while (i < length) {
            jSONArray.put(cVarArr[i].p);
            i++;
        }
        q = jSONArray.toString();
    }

    private c(int i) {
        this.p = i;
    }

    public static String a() {
        return q;
    }

    public final String toString() {
        return String.valueOf(this.p);
    }
}
