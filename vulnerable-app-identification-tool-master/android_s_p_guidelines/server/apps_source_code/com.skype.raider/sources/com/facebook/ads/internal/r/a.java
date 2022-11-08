package com.facebook.ads.internal.r;

import com.adjust.sdk.Constants;

public enum a {
    UNKNOWN_ERROR(-1, "unknown error", false),
    NETWORK_ERROR(Constants.ONE_SECOND, "Network Error", true),
    NO_FILL(1001, "No Fill", true),
    LOAD_TOO_FREQUENTLY(1002, "Ad was re-loaded too frequently", true),
    DISABLED_APP(1005, "App is disabled from making ad requests", true),
    SERVER_ERROR(2000, "Server Error", true),
    INTERNAL_ERROR(2001, "Internal Error", true),
    CACHE_FAILURE_ERROR(2002, "Pre Caching failure", true),
    START_BEFORE_INIT(2004, "initAd must be called before startAd", true),
    BROKEN_MEDIA_ERROR(2100, "Failed to load Media for Native Ad", true),
    AD_REQUEST_FAILED(1111, "Facebook Ads SDK request for ads failed", false),
    AD_REQUEST_TIMEOUT(1112, "Facebook Ads SDK request for ads timed out", false),
    PARSER_FAILURE(1201, "Failed to parse Facebook Ads SDK delivery response", false),
    UNKNOWN_RESPONSE(1202, "Unknown Facebook Ads SDK delivery response type", false),
    ERROR_MESSAGE(1203, "Facebook Ads SDK delivery response Error message", true),
    NO_AD_PLACEMENT(1302, "Facebook Ads SDK returned no ad placements", false),
    MEDIATION_ERROR(3001, "Mediation Error", true),
    BID_IMPRESSION_MISMATCH(4001, "Bid payload does not match placement", true),
    BID_PAYLOAD_ERROR(4002, "Invalid bid payload", false),
    NO_ADAPTER_ON_LOAD(5001, "Adapter is null onLoad Ad", false),
    NO_ADAPTER_ON_START(5002, "Adapter is null onStart Ad", false),
    INTERSTITIAL_CONTROLLER_IS_NULL(5003, "Interstitial Controller is null show Ad", false),
    NO_MEDIAVIEW_IN_NATIVEAD(6001, "MediaView is missing in NativeAd", true),
    NO_ICONVIEW_IN_NATIVEBANNERAD(6002, "IconView is missing in NativeBannerAd", true),
    UNSUPPORTED_AD_ASSET_NATIVEAD(6003, "unsupported type of ad assets", true),
    AD_ALREADY_STARTED(7001, "Ad already started", true),
    LOAD_CALLED_WHILE_SHOWING_AD(7002, "Ad cannot be loaded while being displayed", true);
    
    private final int B;
    private final String C;
    private final boolean D;

    private a(int i, String str, boolean z) {
        this.B = i;
        this.C = str;
        this.D = z;
    }

    public static a a(int i) {
        return a(i, UNKNOWN_ERROR);
    }

    public static a a(int i, a aVar) {
        for (a aVar2 : values()) {
            if (aVar2.B == i) {
                return aVar2;
            }
        }
        return aVar;
    }

    public final int a() {
        return this.B;
    }

    public final String b() {
        return this.C;
    }

    public final boolean c() {
        return this.D;
    }
}
