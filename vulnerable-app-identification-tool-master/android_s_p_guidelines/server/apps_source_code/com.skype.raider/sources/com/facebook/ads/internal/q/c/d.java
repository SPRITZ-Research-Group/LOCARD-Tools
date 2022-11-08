package com.facebook.ads.internal.q.c;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.ads.internal.g.b;
import com.facebook.ads.internal.i.c;
import com.facebook.ads.internal.p.a.a;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class d {
    private static String a = null;
    private static final Set<String> b = new HashSet(1);
    private static final Set<String> c = new HashSet(2);

    static {
        b.add("1ww8E0AYsR2oX5lndk2hwp2Uosk=\n");
        c.add("toZ2GRnRjC9P5VVUdCpOrFH8lfQ=\n");
        c.add("3lKvjNsfmrn+WmfDhvr2iVh/yRs=\n");
        c.add("B08QtE4yLCdli4rptyqAEczXOeA=\n");
        c.add("XZXI6anZbdKf+taURdnyUH5ipgM=\n");
    }

    public static a a(Context context, boolean z) {
        a aVar = new a();
        a(context, aVar, z);
        Object a = com.facebook.ads.internal.t.a.a();
        a = (TextUtils.isEmpty(a) || !a.endsWith(".sb")) ? null : 1;
        if (a == null) {
            aVar.b(c);
            aVar.a(b);
        }
        return aVar;
    }

    private static String a(Context context, String str, String str2) {
        Class cls = Class.forName(str);
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(str2)});
        declaredConstructor.setAccessible(true);
        try {
            String str3 = (String) cls.getMethod("getUserAgentString", new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{context, null}), new Object[0]);
            return str3;
        } finally {
            declaredConstructor.setAccessible(false);
        }
    }

    private static void a(Context context, a aVar, boolean z) {
        b bVar = new b(context);
        aVar.b(30000);
        aVar.a(3);
        if (!com.facebook.ads.internal.c.b.c) {
            aVar.a("X-FB-Pool-Routing-Token", new c(context, true).a());
        }
        aVar.a("user-agent", b(context, z) + " [FBAN/AudienceNetworkForAndroid;FBSN/Android;FBSV/" + b.a + ";FBAB/" + bVar.f() + ";FBAV/" + bVar.g() + ";FBBV/" + bVar.h() + ";FBVS/4.99.1;FBLC/" + Locale.getDefault().toString() + "]");
    }

    public static a b(Context context) {
        return a(context, true);
    }

    private static String b(Context context, boolean z) {
        if (context == null) {
            return "Unknown";
        }
        if (z) {
            return System.getProperty("http.agent");
        }
        if (a != null) {
            return a;
        }
        synchronized (d.class) {
            String str;
            if (a != null) {
                str = a;
                return str;
            }
            if (VERSION.SDK_INT >= 17) {
                try {
                    str = WebSettings.getDefaultUserAgent(context);
                    a = str;
                    return str;
                } catch (Exception e) {
                }
            }
            try {
                a = a(context, "android.webkit.WebSettings", "android.webkit.WebView");
            } catch (Exception e2) {
                try {
                    a = a(context, "android.webkit.WebSettingsClassic", "android.webkit.WebViewClassic");
                } catch (Exception e3) {
                    WebView webView = new WebView(context.getApplicationContext());
                    a = webView.getSettings().getUserAgentString();
                    webView.destroy();
                }
            }
            return a;
        }
    }

    public static a a(Context context) {
        a aVar = new a();
        a(context, aVar, true);
        return aVar;
    }
}
