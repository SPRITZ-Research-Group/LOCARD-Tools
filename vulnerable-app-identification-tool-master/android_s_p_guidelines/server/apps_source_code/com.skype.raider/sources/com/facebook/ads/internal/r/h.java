package com.facebook.ads.internal.r;

public enum h {
    UNKNOWN(0),
    WEBVIEW_BANNER_LEGACY(4),
    WEBVIEW_BANNER_50(5),
    WEBVIEW_BANNER_90(6),
    WEBVIEW_BANNER_250(7),
    WEBVIEW_INTERSTITIAL_UNKNOWN(100),
    WEBVIEW_INTERSTITIAL_HORIZONTAL(101),
    WEBVIEW_INTERSTITIAL_VERTICAL(102),
    WEBVIEW_INTERSTITIAL_TABLET(103),
    NATIVE_UNKNOWN(200),
    NATIVE_BANNER(500),
    NATIVE_250(201),
    REWARDED_VIDEO(400),
    INSTREAM_VIDEO(300);
    
    private final int o;

    private h(int i) {
        this.o = i;
    }

    public final int a() {
        return this.o;
    }
}
