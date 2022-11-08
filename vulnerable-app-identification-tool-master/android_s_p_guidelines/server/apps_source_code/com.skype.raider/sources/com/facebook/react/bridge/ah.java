package com.facebook.react.bridge;

import com.facebook.soloader.SoLoader;

public final class ah {
    private static boolean a = false;

    public static void a() {
        if (!a) {
            SoLoader.a("reactnativejni");
            a = true;
        }
    }
}
