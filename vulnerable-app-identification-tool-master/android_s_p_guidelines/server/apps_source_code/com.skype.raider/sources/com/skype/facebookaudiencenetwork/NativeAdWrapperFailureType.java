package com.skype.facebookaudiencenetwork;

public enum NativeAdWrapperFailureType {
    NOT_FOUND(1, "Pre-loaded ad cannot be retrieved in current adsManager"),
    REGISTER_EMPTY_VIEW(2, "Cannot register the view on a null native ad. This is most likely a wrong usage of wrapper."),
    AD_NOT_LOADED(3, "Cannot register the view on a native ad which is not already loaded. This is most likely a wrong usage of wrapper."),
    AD_INVALIDATED(4, "Cannot register the view on a native ad which has been invalidated by FAN."),
    AD_MEDIA_MISSING(5, "Cannot register the view on a native ad which has been invalidated by FAN.");
    
    private final int f;
    private final String g;

    private NativeAdWrapperFailureType(int errorCode, String errorMessage) {
        this.f = errorCode;
        this.g = errorMessage;
    }

    public final int a() {
        return this.f;
    }

    public final String b() {
        return this.g;
    }
}
