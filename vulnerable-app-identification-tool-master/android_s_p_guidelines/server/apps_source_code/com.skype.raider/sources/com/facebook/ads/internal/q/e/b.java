package com.facebook.ads.internal.q.e;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.view.Window;
import com.facebook.ads.internal.q.a.w;
import com.facebook.ads.internal.q.d.a;
import java.util.HashMap;
import java.util.Map;

public class b {
    private static final String a = b.class.getSimpleName();

    public static Map<String, String> a(Context context) {
        Map<String, String> hashMap = new HashMap();
        if (context == null) {
            return hashMap;
        }
        try {
            String str = "kgr";
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            boolean z = keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
            hashMap.put(str, String.valueOf(z));
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    int i = window.getAttributes().flags;
                    hashMap.put("wt", Integer.toString(window.getAttributes().type));
                    hashMap.put("wfdkg", (4194304 & i) > 0 ? "1" : "0");
                    hashMap.put("wfswl", (524288 & i) > 0 ? "1" : "0");
                }
            }
        } catch (Exception e) {
            a.a(context, "risky", com.facebook.ads.internal.q.d.b.r, e);
        }
        return hashMap;
    }

    public static boolean b(Context context) {
        return !w.b(a(context));
    }
}
