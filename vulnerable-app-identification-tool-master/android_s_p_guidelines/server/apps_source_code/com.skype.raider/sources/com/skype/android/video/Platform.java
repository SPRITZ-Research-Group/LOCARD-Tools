package com.skype.android.video;

import android.content.Context;

public class Platform {
    public static void initialize(Context context) {
        try {
            Class.forName("com.microsoft.media.RtcPalEnvironment").getDeclaredMethod("setContext", new Class[]{Context.class}).invoke(null, new Object[]{context});
        } catch (Exception e) {
        }
    }

    public static void shutdown() {
    }
}
