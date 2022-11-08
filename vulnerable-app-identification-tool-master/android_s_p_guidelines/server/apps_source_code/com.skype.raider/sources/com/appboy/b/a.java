package com.appboy.b;

import com.appboy.e.e;
import java.util.Locale;

public enum a implements e<String> {
    GOOGLE_PLAY_STORE,
    KINDLE_STORE;

    public static String a(String serverString) {
        return serverString.replace(" ", "_").toUpperCase(Locale.US);
    }
}
