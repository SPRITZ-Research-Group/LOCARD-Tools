package com.google.android.exoplayer;

public final class TrackInfo {
    public final long durationUs;
    public final String mimeType;

    public TrackInfo(String str, long j) {
        this.mimeType = str;
        this.durationUs = j;
    }
}
