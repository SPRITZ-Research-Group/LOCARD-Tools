package com.skype.externalbrowser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.b;
import android.support.customtabs.b.a;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;

public class ExternalBrowserModule extends ReactContextBaseJavaModule {
    public ExternalBrowserModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ExternalBrowser";
    }

    @ReactMethod
    public void openUrl(String url, ae promise) {
        try {
            b customTabsIntent = new a().a();
            Bundle headers = new Bundle();
            headers.putString("x-embedded-browser", "true");
            customTabsIntent.a.putExtra("com.android.browser.headers", headers);
            Context reactApplicationContext = getReactApplicationContext();
            customTabsIntent.a.setData(Uri.parse(url));
            android.support.v4.content.a.a(reactApplicationContext, customTabsIntent.a, customTabsIntent.b);
            promise.a(null);
        } catch (Throwable e) {
            promise.a(e);
        }
    }
}
