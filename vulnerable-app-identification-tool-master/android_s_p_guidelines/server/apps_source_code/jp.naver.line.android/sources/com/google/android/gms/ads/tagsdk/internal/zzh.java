package com.google.android.gms.ads.tagsdk.internal;

import android.webkit.WebView;
import java.util.concurrent.Callable;

final /* synthetic */ class zzh implements Callable {
    private final WebView zzcd;

    private zzh(WebView webView) {
        this.zzcd = webView;
    }

    static Callable zza(WebView webView) {
        return new zzh(webView);
    }

    public final Object call() {
        return this.zzcd.getUrl();
    }
}
