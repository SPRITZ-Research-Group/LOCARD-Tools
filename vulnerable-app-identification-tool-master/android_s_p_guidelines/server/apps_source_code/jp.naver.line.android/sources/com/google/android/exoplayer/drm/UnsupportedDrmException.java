package com.google.android.exoplayer.drm;

public final class UnsupportedDrmException extends Exception {
    public static final int REASON_INSTANTIATION_ERROR = 2;
    public static final int REASON_UNSUPPORTED_SCHEME = 1;
    public final int reason;

    public UnsupportedDrmException(int i) {
        this.reason = i;
    }

    public UnsupportedDrmException(int i, Exception exception) {
        super(exception);
        this.reason = i;
    }
}
