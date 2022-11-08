package com.facebook.ads.internal.q.a;

import android.app.KeyguardManager;
import android.content.Context;
import java.util.Map;

public class w {
    private static final String a = w.class.getSimpleName();

    public static boolean a(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        String str3 = (String) map.get("kgr");
        return str != null && str.equals("1") && str2 != null && str2.equals("1") && str3 != null && str3.equals("true");
    }

    public static boolean b(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        if ((str != null && str.equals("1")) || (str2 != null && str2.equals("1"))) {
            return false;
        }
        str = (String) map.get("kgr");
        return str != null && str.equals("true");
    }
}
