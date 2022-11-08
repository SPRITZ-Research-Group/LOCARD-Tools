package com.facebook.ads.internal.r;

public enum e {
    UNKNOWN,
    BANNER,
    INTERSTITIAL,
    NATIVE,
    REWARDED_VIDEO;

    public static e a(h hVar) {
        switch (hVar) {
            case NATIVE_UNKNOWN:
                return NATIVE;
            case WEBVIEW_BANNER_50:
            case WEBVIEW_BANNER_90:
            case WEBVIEW_BANNER_LEGACY:
            case WEBVIEW_BANNER_250:
                return BANNER;
            case WEBVIEW_INTERSTITIAL_HORIZONTAL:
            case WEBVIEW_INTERSTITIAL_VERTICAL:
            case WEBVIEW_INTERSTITIAL_TABLET:
            case WEBVIEW_INTERSTITIAL_UNKNOWN:
                return INTERSTITIAL;
            case REWARDED_VIDEO:
                return REWARDED_VIDEO;
            default:
                return UNKNOWN;
        }
    }

    public final b a() {
        switch (this) {
            case INTERSTITIAL:
                return b.INTERSTITIAL;
            case BANNER:
                return b.BANNER;
            case NATIVE:
                return b.NATIVE;
            case REWARDED_VIDEO:
                return b.REWARDED_VIDEO;
            default:
                return b.UNKNOWN;
        }
    }
}
