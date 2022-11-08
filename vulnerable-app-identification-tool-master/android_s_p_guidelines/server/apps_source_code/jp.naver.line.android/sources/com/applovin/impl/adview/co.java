package com.applovin.impl.adview;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class co extends WebViewClient {
    private final AppLovinSdk a;
    private final AppLovinLogger b;
    private WeakReference<cp> c;

    public co(AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk;
        this.b = appLovinSdk.getLogger();
    }

    void a(WebView webView, String str) {
        StringBuilder stringBuilder = new StringBuilder("Processing click on ad URL \"");
        stringBuilder.append(str);
        stringBuilder.append("\"");
        this.b.i("WebViewButtonClient", stringBuilder.toString());
        if (str != null && (webView instanceof cn)) {
            cn cnVar = (cn) webView;
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            String host = parse.getHost();
            String path = parse.getPath();
            cp cpVar = (cp) this.c.get();
            if (AppLovinSdk.URI_SCHEME.equalsIgnoreCase(scheme) && AppLovinSdk.URI_HOST.equalsIgnoreCase(host) && cpVar != null) {
                if ("/track_click".equals(path)) {
                    cpVar.a(cnVar);
                } else if ("/close_ad".equals(path)) {
                    cpVar.b(cnVar);
                } else if ("/skip_ad".equals(path)) {
                    cpVar.c(cnVar);
                } else {
                    this.b.w("WebViewButtonClient", "Unknown URL: ".concat(String.valueOf(str)));
                    this.b.w("WebViewButtonClient", "Path: ".concat(String.valueOf(path)));
                }
            }
        }
    }

    public void a(WeakReference<cp> weakReference) {
        this.c = weakReference;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        a(webView, str);
        return true;
    }
}
