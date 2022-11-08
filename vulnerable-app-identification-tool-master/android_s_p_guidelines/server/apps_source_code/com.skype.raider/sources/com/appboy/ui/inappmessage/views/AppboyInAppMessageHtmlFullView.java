package com.appboy.ui.inappmessage.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.appboy.f.c;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.jsinterface.AppboyInAppMessageHtmlJavascriptInterface;

public class AppboyInAppMessageHtmlFullView extends AppboyInAppMessageHtmlBaseView {
    private static final String TAG = c.a(AppboyInAppMessageHtmlFullView.class);
    private WebView mMessageWebView;

    public AppboyInAppMessageHtmlFullView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    public WebView getMessageWebView() {
        if (this.mMessageWebView == null) {
            this.mMessageWebView = (AppboyInAppMessageWebView) findViewById(R.id.com_appboy_inappmessage_html_full_webview);
            if (this.mMessageWebView != null) {
                WebSettings webSettings = this.mMessageWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setDisplayZoomControls(false);
                this.mMessageWebView.setLayerType(2, null);
                this.mMessageWebView.setBackgroundColor(0);
                this.mMessageWebView.setWebChromeClient(new WebChromeClient() {
                    public boolean onConsoleMessage(ConsoleMessage cm) {
                        c.b(AppboyInAppMessageHtmlFullView.TAG, "Html In-app log. Line: " + cm.lineNumber() + ". SourceId: " + cm.sourceId() + ". Log Level: " + cm.messageLevel() + ". Message: " + cm.message());
                        return true;
                    }
                });
                this.mMessageWebView.addJavascriptInterface(new AppboyInAppMessageHtmlJavascriptInterface(getContext()), "appboyInternalBridge");
            }
        }
        return this.mMessageWebView;
    }
}
