package com.applovin.impl.adview;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;

class m extends WebChromeClient {
    private final AppLovinLogger a;

    public m(AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk.getLogger();
    }

    public void onConsoleMessage(String str, int i, String str2) {
        StringBuilder stringBuilder = new StringBuilder("console.log[");
        stringBuilder.append(i);
        stringBuilder.append("] :");
        stringBuilder.append(str);
        this.a.w("AdWebView", stringBuilder.toString());
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(consoleMessage.sourceId());
        stringBuilder.append(": ");
        stringBuilder.append(consoleMessage.lineNumber());
        stringBuilder.append(": ");
        stringBuilder.append(consoleMessage.message());
        this.a.d("AdWebView", stringBuilder.toString());
        return true;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        this.a.w("AdWebView", "Alert attempted: ".concat(String.valueOf(str2)));
        return true;
    }

    public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        this.a.w("AdWebView", "JS onBeforeUnload attempted: ".concat(String.valueOf(str2)));
        return true;
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        this.a.w("AdWebView", "JS confirm attempted: ".concat(String.valueOf(str2)));
        return true;
    }
}
