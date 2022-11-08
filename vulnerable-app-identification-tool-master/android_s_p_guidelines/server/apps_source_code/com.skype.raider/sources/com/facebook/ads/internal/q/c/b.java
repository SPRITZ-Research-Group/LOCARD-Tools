package com.facebook.ads.internal.q.c;

import android.text.TextUtils;
import android.webkit.WebView;
import com.facebook.ads.internal.t.a;

public final class b {
    public static String a() {
        if (TextUtils.isEmpty(a.a())) {
            return "https://www.facebook.com/";
        }
        return String.format("https://www.%s.facebook.com", new Object[]{a.a()});
    }

    public static void a(WebView webView) {
        webView.loadUrl("about:blank");
        webView.clearCache(true);
    }
}
