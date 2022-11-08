package com.facebook.ads.internal.adapters;

import android.text.TextUtils;
import java.util.Locale;

public enum n {
    UNKNOWN,
    AN,
    ADMOB,
    INMOBI,
    YAHOO;

    public static n a(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        try {
            return (n) Enum.valueOf(n.class, str.toUpperCase(Locale.getDefault()));
        } catch (Exception e) {
            return UNKNOWN;
        }
    }
}
