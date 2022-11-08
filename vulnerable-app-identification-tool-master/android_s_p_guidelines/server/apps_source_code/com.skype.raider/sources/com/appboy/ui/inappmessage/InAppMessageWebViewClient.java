package com.appboy.ui.inappmessage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appboy.e.b;
import com.appboy.f.a;
import com.appboy.f.c;
import com.appboy.f.i;
import com.appboy.ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.ui.support.UriUtils;
import java.util.Map;

public class InAppMessageWebViewClient extends WebViewClient {
    private static final String TAG = c.a(InAppMessageWebViewClient.class);
    private Context mContext;
    private final b mInAppMessage;
    private IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener;

    public InAppMessageWebViewClient(Context context, b inAppMessage, IInAppMessageWebViewClientListener inAppMessageWebViewClientListener) {
        this.mInAppMessageWebViewClientListener = inAppMessageWebViewClientListener;
        this.mInAppMessage = inAppMessage;
        this.mContext = context;
    }

    public void onPageFinished(WebView view, String url) {
        appendBridgeJavascript(view);
    }

    private void appendBridgeJavascript(WebView view) {
        String javascriptString = a.a(this.mContext.getAssets(), "appboy-html-in-app-message-javascript-component.js");
        if (javascriptString == null) {
            AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(false);
            c.g(TAG, "Failed to get HTML in-app message javascript additions");
            return;
        }
        view.loadUrl("javascript:" + javascriptString);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (this.mInAppMessageWebViewClientListener == null) {
            c.d(TAG, "InAppMessageWebViewClient was given null IInAppMessageWebViewClientListener listener. Returning true.");
        } else if (i.c(url)) {
            c.d(TAG, "InAppMessageWebViewClient.shouldOverrideUrlLoading was given null or blank url. Returning true.");
        } else {
            Uri uri = Uri.parse(url);
            Bundle queryBundle = getBundleFromUrl(url);
            if (uri.getScheme().equals("appboy")) {
                String authority = uri.getAuthority();
                if (authority.equals("close")) {
                    this.mInAppMessageWebViewClientListener.onCloseAction(this.mInAppMessage, url, queryBundle);
                } else if (authority.equals("feed")) {
                    this.mInAppMessageWebViewClientListener.onNewsfeedAction(this.mInAppMessage, url, queryBundle);
                } else if (authority.equals("customEvent")) {
                    this.mInAppMessageWebViewClientListener.onCustomEventAction(this.mInAppMessage, url, queryBundle);
                }
            } else {
                this.mInAppMessageWebViewClientListener.onOtherUrlAction(this.mInAppMessage, url, queryBundle);
            }
        }
        return true;
    }

    @VisibleForTesting
    static Bundle getBundleFromUrl(String url) {
        Bundle queryBundle = new Bundle();
        if (!i.c(url)) {
            Map<String, String> queryParameterMap = UriUtils.getQueryParameters(Uri.parse(url));
            for (String queryKeyName : queryParameterMap.keySet()) {
                queryBundle.putString(queryKeyName, (String) queryParameterMap.get(queryKeyName));
            }
        }
        return queryBundle;
    }
}
