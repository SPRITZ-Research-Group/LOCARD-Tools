package com.github.alinz.reactnativewebviewbridge;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ar;
import com.facebook.react.uimanager.events.RCTEventEmitter;

final class b {
    private WebView a;

    public b(WebView webView) {
        this.a = webView;
    }

    @JavascriptInterface
    public final void send(String message) {
        ar event = new WritableNativeMap();
        event.putString("message", message);
        ((RCTEventEmitter) ((ai) this.a.getContext()).a(RCTEventEmitter.class)).receiveEvent(this.a.getId(), "topChange", event);
    }
}
