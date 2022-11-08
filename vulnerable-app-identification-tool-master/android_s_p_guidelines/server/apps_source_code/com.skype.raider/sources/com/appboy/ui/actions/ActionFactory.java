package com.appboy.ui.actions;

import android.net.Uri;
import android.os.Bundle;
import com.appboy.b.d;
import com.appboy.f.i;

public class ActionFactory {
    public static UriAction createUriActionFromUrlString(String url, Bundle extras, boolean openInWebView, d channel) {
        if (i.c(url)) {
            return null;
        }
        return createUriActionFromUri(Uri.parse(url), extras, openInWebView, channel);
    }

    public static UriAction createUriActionFromUri(Uri uri, Bundle extras, boolean openInWebView, d channel) {
        if (uri != null) {
            return new UriAction(uri, extras, openInWebView, channel);
        }
        return null;
    }
}
