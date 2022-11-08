package com.google.ads.interactivemedia.v3.api;

import org.apache.http.HttpStatus;

public final class AdError extends Exception {
    private final AdErrorCode a;
    private final AdErrorType b;

    public enum AdErrorCode {
        INTERNAL_ERROR(-1),
        VAST_MALFORMED_RESPONSE(100),
        UNKNOWN_AD_RESPONSE(1010),
        VAST_LOAD_TIMEOUT(HttpStatus.SC_MOVED_PERMANENTLY),
        VAST_TOO_MANY_REDIRECTS(HttpStatus.SC_MOVED_TEMPORARILY),
        VIDEO_PLAY_ERROR(HttpStatus.SC_BAD_REQUEST),
        VAST_MEDIA_LOAD_TIMEOUT(402),
        VAST_LINEAR_ASSET_MISMATCH(403),
        OVERLAY_AD_PLAYING_FAILED(500),
        OVERLAY_AD_LOADING_FAILED(HttpStatus.SC_BAD_GATEWAY),
        VAST_NONLINEAR_ASSET_MISMATCH(HttpStatus.SC_SERVICE_UNAVAILABLE),
        COMPANION_AD_LOADING_FAILED(603),
        UNKNOWN_ERROR(900),
        VAST_EMPTY_RESPONSE(1009),
        FAILED_TO_REQUEST_ADS(1005),
        VAST_ASSET_NOT_FOUND(1007),
        ADS_REQUEST_NETWORK_ERROR(1012),
        INVALID_ARGUMENTS(1101),
        PLAYLIST_NO_CONTENT_TRACKING(1205);
        
        private final int a;

        private AdErrorCode(int i) {
            this.a = i;
        }

        public final int getErrorNumber() {
            return this.a;
        }

        static AdErrorCode a(int i) {
            for (AdErrorCode adErrorCode : values()) {
                if (adErrorCode.getErrorNumber() == i) {
                    return adErrorCode;
                }
            }
            if (1204 == i) {
                return INTERNAL_ERROR;
            }
            return UNKNOWN_ERROR;
        }

        public final boolean equals(int i) {
            return this.a == i;
        }

        public final String toString() {
            String name = name();
            int i = this.a;
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(name).length() + 41);
            stringBuilder.append("AdErrorCode [name: ");
            stringBuilder.append(name);
            stringBuilder.append(", number: ");
            stringBuilder.append(i);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public enum AdErrorType {
        LOAD,
        PLAY
    }

    public AdError(AdErrorType adErrorType, int i, String str) {
        this(adErrorType, AdErrorCode.a(i), str);
    }

    public AdError(AdErrorType adErrorType, AdErrorCode adErrorCode, String str) {
        super(str);
        this.b = adErrorType;
        this.a = adErrorCode;
    }

    public final AdErrorType getErrorType() {
        return this.b;
    }

    public final AdErrorCode getErrorCode() {
        return this.a;
    }

    public final int getErrorCodeNumber() {
        return this.a.getErrorNumber();
    }

    public final String getMessage() {
        return super.getMessage();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.b);
        String valueOf2 = String.valueOf(this.a);
        String message = getMessage();
        StringBuilder stringBuilder = new StringBuilder(((String.valueOf(valueOf).length() + 45) + String.valueOf(valueOf2).length()) + String.valueOf(message).length());
        stringBuilder.append("AdError [errorType: ");
        stringBuilder.append(valueOf);
        stringBuilder.append(", errorCode: ");
        stringBuilder.append(valueOf2);
        stringBuilder.append(", message: ");
        stringBuilder.append(message);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
