package com.facebook.ads.internal.r;

import android.text.TextUtils;
import java.util.Locale;

public enum b {
    BANNER("banner"),
    INSTREAM("instream"),
    INTERSTITIAL("interstitial"),
    NATIVE("native"),
    NATIVE_BANNER("native_banner"),
    REWARDED_VIDEO("rewarded_video"),
    UNKNOWN("unknown");
    
    private String h;

    private b(String str) {
        this.h = str;
    }

    public static b a(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

    public final String toString() {
        return this.h;
    }
}
